package aigilas.states;

import aigilas.net.Client;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

        Label.LabelStyle lblStyle = new Label.LabelStyle(Assets.get().font(),Color.WHITE);
        Label label = new Label("Server IP:",lblStyle);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.get().font();
        style.cursor = UiAssets.getNewCursor();

        style.fontColor = Color.WHITE;
        style.background = UiAssets.getNewBtnBg();
        final TextField ipIn = new TextField("",style);
        stage.setKeyboardFocus(ipIn);



        ipIn.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                ipIn.setBlinkTime(1);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(ipIn).minWidth(300);

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
