package aigilas.states;

import aigilas.Config;
import aigilas.net.Client;
import aigilas.net.LanClient;
import aigilas.net.Server;
import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.bridge.Contexts;
import sps.bridge.Sps;
import sps.core.Logger;
import sps.graphics.Assets;
import sps.io.Input;

public class StartHostServerState extends MenuState {
    private Label connectedPlayersLbl;

    public StartHostServerState() {

        //Server
        Logger.info("IP is " + Config.get().serverIp());
        Server.reset();
        Client.reset(new LanClient());

        //UI
        Input.get().setContext(Contexts.get(Sps.Contexts.Non_Free), Client.get().getFirstPlayerIndex());

        Label.LabelStyle lblStyle = new Label.LabelStyle(Assets.get().font(), Color.WHITE);
        connectedPlayersLbl = new Label("0 players connected", lblStyle);

        final SelectableButton startGameBtn = new SelectableButton("Start", UiAssets.getButtonStyle());
        startGameBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Logger.info("Starting the game as a host");
                Client.get().startGame();
            }
        });

        table.add(connectedPlayersLbl);
        table.row();
        add(startGameBtn);
    }

    private int connectedPlayers;

    @Override
    public void asyncUpdate() {
        if (connectedPlayers != Client.get().getPlayerCount()) {
            connectedPlayers = Client.get().getPlayerCount();
            Logger.info("Connected players: " + connectedPlayers);
            connectedPlayersLbl.setText(connectedPlayers + " players connected");
        }
    }

    @Override
    public String getName() {
        return "StartHostServerState";
    }
}
