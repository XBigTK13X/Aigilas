package aigilas.states;

import aigilas.AigilasConfig;
import aigilas.net.Client;
import aigilas.net.IClient;
import aigilas.net.LanClient;
import aigilas.net.LocalClient;
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
import sps.states.StateManager;
import sps.util.Parse;

public class JoinServerAsGuestState extends MenuState {
    private static final String waitMessage = "Waiting for any player to start the game.";
    final TextField ipIn;
    final Label label;
    final SelectableButton joinBtn;
    final SelectableButton startGameBtn;
    final SelectableButton backBtn;
    private boolean connectStarted = false;
    private boolean readyToConnect = false;

    public JoinServerAsGuestState() {
        label = new Label("Server IP:", UiAssets.getLabelStyle());

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.get().font();
        style.cursor = UiAssets.getNewCursor();

        style.fontColor = Color.WHITE;
        style.background = UiAssets.getNewBtnBg();
        ipIn = new TextField("", style);
        stage.setKeyboardFocus(ipIn);

        backBtn = new SelectableButton("Back", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new MainMenuState());
            }
        });
        joinBtn = new SelectableButton("Join", UiAssets.getButtonStyle());
        joinBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                startGameIfReady();
            }
        });

        ipIn.addListener(new ChangeListener() {
            //@Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                ipIn.setBlinkTime(1);
            }
        });

        startGameBtn = new SelectableButton("Start", UiAssets.getButtonStyle());
        startGameBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Logger.info("Starting the game as a guest");
                Client.get().startGame();
            }
        });

        startGameBtn.setVisible(false);
        table.add(label);
        table.add(ipIn).minWidth(300);
        table.row();
        add(joinBtn);
        add(startGameBtn);
        table.row();
        add(backBtn);
    }

    private void startGameIfReady() {
        if (ipIn.getText() != null && !ipIn.getText().isEmpty()) {
            joinBtn.setVisible(false);
            String[] contents = ipIn.getText().split(":");
            String address = contents[0];
            if (contents.length > 1) {
                int port = Parse.inte(contents[1]);
                AigilasConfig.get().setPort(port);
            }

            label.setText(waitMessage);
            AigilasConfig.get().setServerIp(address);
            readyToConnect = true;
            ipIn.setVisible(false);
            startGameBtn.setVisible(true);
        }
    }

    //@Override
    public void update() {
        super.update();
        if (readyToConnect && !connectStarted) {
            IClient lanClient = new LanClient();
            if (!lanClient.isConnected()) {
                Client.reset(new LocalClient());
                StateManager.loadState(new JoinServerAsGuestState());
                return;
            }
            Client.reset(lanClient);
            Input.get().setup(Client.get());
            connectStarted = true;
            Logger.info("Connecting as guest");
        }
    }

    //@Override
    public String getName() {
        return "JoinServerAsGuestState";
    }
}
