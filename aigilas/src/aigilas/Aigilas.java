package aigilas;

import aigilas.management.Commands;
import aigilas.management.SpriteInitializer;
import aigilas.states.MainMenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import sps.core.Logger;
import sps.core.SpxManager;
import sps.devtools.DevConsole;
import sps.graphics.SpriteSheetManager;
import sps.io.Input;
import sps.net.Client;
import sps.particles.ParticleEngine;
import sps.states.StateManager;
import sps.text.TextManager;

public class Aigilas implements ApplicationListener {
    private boolean IsRunning = true;

    private void setIsRunning(boolean isRunning) {
        IsRunning = isRunning;
    }

    @Override
    public void create() {
        SpxManager.setup();
        Input.setup();
        SpriteSheetManager.setup(new SpriteInitializer());
        StateManager.loadState(new MainMenuState());
        ParticleEngine.reset();
        StateManager.loadContent();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Logger.devConsole("" + Gdx.graphics.getFramesPerSecond() + ": " + Gdx.graphics.getDeltaTime());

        // Update
        Input.update();
        if (Input.isActive(Commands.ToggleDevConsole, Client.get().getFirstPlayerIndex())) {
            DevConsole.get().toggle();
        }
        if (Input.isActive(Commands.Back, Client.get().getFirstPlayerIndex())) {
            Gdx.app.exit();
        }
        if (Client.get().nextTurn()) {
            /*for (int ii = 0; ii < 4; ii++) {
                PlayerIndex player = PlayerIndex.values()[ii];

                     //$$$ if (GamePad.GetState(player).IsPressed(Buttons.Back) &&
                      GamePad.GetState(player).IsPressed(Buttons.Start)) {
                      setIsRunning(false); }

            }*/
            ParticleEngine.update();
            StateManager.update();
            TextManager.update();
            Client.get().prepareForNextTurn();
        }
        else {
            Client.get().heartBeat();
        }
        if (!IsRunning) {
            System.exit(0);
        }

        // Render
        SpxManager.Renderer.begin();
        StateManager.draw();
        ParticleEngine.draw();
        TextManager.draw();
        DevConsole.get().draw();
        SpxManager.Renderer.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
