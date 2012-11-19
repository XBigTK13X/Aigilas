package aigilas.states;

import aigilas.Common;
import aigilas.net.Client;
import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.core.Core;
import sps.core.Logger;
import sps.graphics.Assets;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

import java.util.ArrayList;

public class MainMenuState implements State {
    private int _selection = 0;

    private Stage stage;
    private ArrayList<SelectableButton> buttons = new ArrayList<SelectableButton>();

    public MainMenuState() {
        Input.setContext(Contexts.get(Core.Non_Free), Client.get().getFirstPlayerIndex());
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Assets.get().font();

        style.overFontColor = Color.YELLOW;
        style.downFontColor = Color.GREEN;
        style.fontColor = Color.WHITE;
        style.down = UiAssets.getNewBtnBg();
        style.up = UiAssets.getNewBtnBg();
        style.over = UiAssets.getNewBtnBg();

        Table table = new Table();
        table.setFillParent(true);

        final SelectableButton startGameBtn = new SelectableButton("Start", style);
        startGameBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Logger.info("Starting the game");
                Client.get().startGame();
            }
        });

        table.add(startGameBtn);
        buttons.add(startGameBtn);

        SelectableButton connectToServerBtn = new SelectableButton("Join Game", style);
        connectToServerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new ServerConnectState());
            }
        });
        table.add(connectToServerBtn);
        buttons.add(connectToServerBtn);

        SelectableButton optionsBtn = new SelectableButton("Options", style);
        optionsBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //$$$ StateManager.loadState(new OptionsState());
            }
        });
        table.add(optionsBtn);
        buttons.add(optionsBtn);

        SelectableButton exitBtn = new SelectableButton("Exit", style);
        exitBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        table.add(exitBtn);
        buttons.add(exitBtn);

        stage.addActor(table);
        startGameBtn.setSelected(true);
    }

    @Override
    public void update() {
        int selectionVelocity = (Input.isActive(Commands.get(Common.MoveRight), Client.get().getFirstPlayerIndex()) ? 1 : 0) + (Input.isActive(Commands.get(Common.MoveLeft), Client.get().getFirstPlayerIndex()) ? -1 : 0);
        _selection += selectionVelocity;
        _selection %= buttons.size();
        if (_selection < 0) {
            _selection = buttons.size() - 1;
        }

        if (Client.get().isGameStarting()) {
            for (int ii = 0; ii < Client.get().getPlayerCount(); ii++) {
                Input.setContext(Contexts.get(Common.Free), ii);
            }

            StateManager.loadState(new LoadingState());
        }
        else {
            if (Input.isActive(Commands.get(Common.Confirm), Client.get().getFirstPlayerIndex())) {
                switch (_selection) {
                    case 0:
                        Logger.info("Starting the game");
                        Client.get().startGame();
                        return;
                    case 2:
                        //$$$ StateManager.loadState(new OptionsState());
                        return;
                    case 1:
                        StateManager.loadState(new ServerConnectState());
                        return;
                    case 3:
                        System.exit(0);
                        return;
                    default:
                        break;
                }
            }
        }

        if (selectionVelocity != 0) {
            for (int ii = 0; ii < 3; ii++) {
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
