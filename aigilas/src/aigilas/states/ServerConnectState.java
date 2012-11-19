package aigilas.states;

import aigilas.net.Client;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import sps.bridge.Contexts;
import sps.bridge.Spx;
import sps.core.Core;
import sps.core.Settings;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import sps.io.Input;
import sps.states.State;

public class ServerConnectState implements State {

    private Stage stage;

    public ServerConnectState() {
        Input.setContext(Contexts.get(Core.Non_Free), Client.get().getFirstPlayerIndex());
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.get().font();

        style.fontColor = Color.WHITE;
        style.background = UiAssets.getNewBtnBg();
        TextField ipIn = new TextField("Server IP: ",style);

        Table table = new Table();
        table.setFillParent(true);
        table.add(ipIn);

        stage.addActor(table);
    }

    @Override
    public void update() {

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
