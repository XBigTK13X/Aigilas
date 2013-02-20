package aigilas.states;

import aigilas.Config;
import aigilas.net.Client;
import aigilas.net.LanClient;
import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.core.Logger;
import sps.graphics.Assets;
import sps.io.Input;
import sps.util.Parse;

public class JoinServerAsGuestState extends MenuState {
    private static final String waitMessage = "Waiting for host to start the game.";
    final TextField ipIn;
    final Label label;
    private boolean connectStarted = false;
    private boolean readyToConnect = false;

    final SelectableButton joinBtn;

    public JoinServerAsGuestState() {


        label = new Label("Server IP:", UiAssets.getLabelStyle());

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.get().font();
        style.cursor = UiAssets.getNewCursor();

        style.fontColor = Color.WHITE;
        style.background = UiAssets.getNewBtnBg();
        ipIn = new TextField("", style);
        stage.setKeyboardFocus(ipIn);

        joinBtn = new SelectableButton("Join", UiAssets.getButtonStyle());
        joinBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                startGameIfReady();
            }
        });

        ipIn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                ipIn.setBlinkTime(1);
            }
        });

        table.add(label);
        table.add(ipIn).minWidth(300);
        table.row();
        add(joinBtn);
    }

    private void startGameIfReady() {
        if (ipIn.getText() != null && !ipIn.getText().isEmpty()) {
            joinBtn.setVisible(false);
            String[] contents = ipIn.getText().split(":");
            String address = contents[0];
            if (contents.length > 1) {
                int port = Parse.inte(contents[1]);
                Config.get().setPort(port);
            }

            label.setText(waitMessage);
            Config.get().setServerIp(address);
            readyToConnect = true;
            ipIn.setVisible(false);
        }
    }

    @Override
    public void update() {
        super.update();
        if (readyToConnect && !connectStarted) {
            Client.reset(new LanClient());
            Input.get().setup(Client.get());
            connectStarted = true;
            Logger.info("Connecting as guest");
        }
    }
}
