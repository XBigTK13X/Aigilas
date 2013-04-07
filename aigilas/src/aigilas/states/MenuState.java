package aigilas.states;

import aigilas.Aigilas;
import aigilas.net.Client;
import aigilas.ui.SelectableButton;
import aigilas.ui.UiSelection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.bridge.Sps;
import sps.io.Input;
import sps.states.State;

import java.util.ArrayList;

public abstract class MenuState implements State {
    protected final int horizDelta = 1;
    protected final int verticalDelta = 3;
    protected int _selection = 0;
    protected Stage stage;
    protected ArrayList<SelectableButton> buttons = new ArrayList<SelectableButton>();
    protected Table table;

    private static final int internalWidth = 1920;
    private static final int internalHeight = 1080;

    public MenuState() {
        Input.get().setContext(Contexts.get(Sps.Contexts.Non_Free), Client.get().getFirstPlayerIndex());
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    protected void add(SelectableButton button) {
        buttons.add(button);
        table.add(button);
    }

    @Override
    public void update() {
        int selectionVelocity = (Input.get().isActive(Commands.get(Aigilas.Commands.MoveRight), Client.get().getFirstPlayerIndex()) ? horizDelta : 0) + (Input.get().isActive(Commands.get(Aigilas.Commands.MoveLeft), Client.get().getFirstPlayerIndex()) ? -horizDelta : 0);
        selectionVelocity += (Input.get().isActive(Commands.get(Aigilas.Commands.MoveUp), Client.get().getFirstPlayerIndex()) ? -verticalDelta : 0) + (Input.get().isActive(Commands.get(Aigilas.Commands.MoveDown), Client.get().getFirstPlayerIndex()) ? verticalDelta : 0);
        _selection += selectionVelocity;
        if (buttons.size() > 0) {
            _selection %= buttons.size();
            if (_selection < 0) {
                _selection = buttons.size() - 1;
            }

            if (!Client.get().isGameStarting()) {
                if (UiSelection.commandActive()) {
                    buttons.get(_selection).fire(new ChangeListener.ChangeEvent() {
                    });
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
    }

    @Override
    public void load() {

    }

    @Override
    public void asyncUpdate() {
    }

    @Override
    public void unload() {

    }

    @Override
    public void draw() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(internalWidth, internalHeight, false);
    }
}
