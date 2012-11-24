package aigilas.states;

import aigilas.Common;
import aigilas.Config;
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
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.core.Core;
import sps.graphics.Assets;
import sps.io.Input;
import sps.states.State;
import sps.util.Parse;

public class StartServerState implements State {

    private Stage stage;
    final TextField ipIn;

    public StartServerState() {
        Input.setContext(Contexts.get(Core.Non_Free), Client.get().getFirstPlayerIndex());
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle lblStyle = new Label.LabelStyle(Assets.get().font(), Color.WHITE);
        Label label = new Label("Server IP:", lblStyle);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.get().font();
        style.cursor = UiAssets.getNewCursor();

        style.fontColor = Color.WHITE;
        style.background = UiAssets.getNewBtnBg();
        ipIn = new TextField("", style);
        stage.setKeyboardFocus(ipIn);

        ipIn.addListener(new ChangeListener() {

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
        if (Input.isActive(Commands.get(Common.Start), 0)) {
            if (ipIn.getText() != null && !ipIn.getText().isEmpty()) {
                String[] contents = ipIn.getText().split(":");
                String address = contents[0];
                if (contents.length > 1) {
                    int port = Parse.inte(contents[1]);
                    Config.get().setPort(port);
                }
                Config.get().setServerIp(address);
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
