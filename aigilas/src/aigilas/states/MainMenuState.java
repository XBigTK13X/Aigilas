package aigilas.states;

import aigilas.Aigilas;
import aigilas.net.Client;
import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import aigilas.ui.UiSelection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.apache.commons.io.FileUtils;
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.bridge.Sps;
import sps.core.Loader;
import sps.core.Logger;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

import java.io.File;
import java.util.ArrayList;

public class MainMenuState implements State {
    private int _selection = 0;
    private final int horizDelta = 1;
    private final int verticalDelta = 3;

    private Stage stage;
    private ArrayList<SelectableButton> buttons = new ArrayList<SelectableButton>();

    public MainMenuState() {
        Input.setContext(Contexts.get(Sps.Contexts.Non_Free), Client.get().getFirstPlayerIndex());
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle lblStyle = new Label.LabelStyle(Assets.get().font(), Color.WHITE);
        Label title = new Label("Aigilas", lblStyle);
        Table table = new Table();
        table.setFillParent(true);

        final SelectableButton startGameBtn = new SelectableButton("Start Local", UiAssets.getButtonStyle());
        startGameBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Logger.info("Starting the game");
                Client.get().startGame();
            }
        });

        SelectableButton startServerBtn = new SelectableButton("Host LAN", UiAssets.getButtonStyle());
        startServerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new StartHostServerState());
            }
        });

        SelectableButton connectToServerBtn = new SelectableButton("Join LAN", UiAssets.getButtonStyle());
        connectToServerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new JoinServerAsGuestState());
            }
        });

        SelectableButton optionsBtn = new SelectableButton("Options", UiAssets.getButtonStyle());
        optionsBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new OptionsState());
            }
        });

        SelectableButton exitBtn = new SelectableButton("Exit", UiAssets.getButtonStyle());
        exitBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(startGameBtn);
        buttons.add(startGameBtn);
        table.add(startServerBtn);
        buttons.add(startServerBtn);
        table.add(connectToServerBtn);
        buttons.add(connectToServerBtn);
        table.row();
        table.add(optionsBtn);
        buttons.add(optionsBtn);
        table.add(exitBtn);
        buttons.add(exitBtn);


        title.setY((int) (Renderer.get().VirtualHeight * .8));
        title.setX((int) (Renderer.get().VirtualWidth * .4));
        stage.addActor(title);
        stage.addActor(table);

        File versionDat = Loader.get().data("version.dat");
        if (versionDat.exists()) {
            try {
                String versionText = FileUtils.readFileToString(versionDat);
                Label version = new Label("Version: " + versionText, lblStyle);
                stage.addActor(version);
            }
            catch (Exception e) {
                Logger.info("WARNING: No version information found at " + versionDat.getAbsolutePath());
            }
        }
        startGameBtn.setSelected(true);
    }

    @Override
    public void update() {
        int selectionVelocity = (Input.isActive(Commands.get(Aigilas.Commands.MoveRight), Client.get().getFirstPlayerIndex()) ? horizDelta : 0) + (Input.isActive(Commands.get(Aigilas.Commands.MoveLeft), Client.get().getFirstPlayerIndex()) ? -horizDelta : 0);
        selectionVelocity += (Input.isActive(Commands.get(Aigilas.Commands.MoveUp), Client.get().getFirstPlayerIndex()) ? -verticalDelta : 0) + (Input.isActive(Commands.get(Aigilas.Commands.MoveDown), Client.get().getFirstPlayerIndex()) ? verticalDelta : 0);
        _selection += selectionVelocity;
        _selection %= buttons.size();
        if (_selection < 0) {
            _selection = buttons.size() - 1;
        }

        if (Client.get().isGameStarting()) {
            for (int ii = 0; ii < Client.get().getPlayerCount(); ii++) {
                Input.setContext(Contexts.get(Sps.Contexts.Free), ii);
            }

            StateManager.loadState(new LoadingState());
        }
        else {
            if (UiSelection.commandActive()) {
                switch (_selection) {
                    case 0:
                        Logger.info("Starting the game");
                        Client.get().startGame();
                        return;
                    case 1:
                        StateManager.loadState(new StartHostServerState());
                        return;
                    case 2:
                        StateManager.loadState(new JoinServerAsGuestState());
                        return;
                    case 3:
                        StateManager.loadState(new OptionsState());
                        return;
                    case 4:
                        System.exit(0);
                        return;
                    default:
                        break;
                }
            }
        }

        if (selectionVelocity != 0) {
            for (int ii = 0; ii < buttons.size(); ii++) {
                buttons.get(ii).setSelected(false);
            }
            buttons.get(_selection).setSelected(true);
        }
        boolean reset = false;
        for (int ii = 0; ii < buttons.size(); ii++) {
            if (buttons.get(ii).mouseIsOver() && _selection != ii) {
                _selection = ii;
                reset = true;
            }
        }
        if (reset) {
            for (int ii = 0; ii < buttons.size(); ii++) {
                buttons.get(ii).setSelected(_selection == ii);
            }
        }
    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    public void draw() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
