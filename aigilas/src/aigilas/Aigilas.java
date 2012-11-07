package aigilas;

import aigilas.hud.HudRenderer;
import sps.core.Commands;
import aigilas.management.SpriteInitializer;
import aigilas.states.MainMenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import sps.core.Logger;
import sps.core.Spx;
import sps.devtools.DevConsole;
import sps.graphics.Renderer;
import sps.graphics.SpriteSheetManager;
import sps.io.Input;
import sps.net.Client;
import sps.particles.ParticleEngine;
import sps.states.StateManager;
import sps.text.TextPool;

public class Aigilas implements ApplicationListener {
    private boolean IsRunning = true;

    private void setIsRunning(boolean isRunning) {
        IsRunning = isRunning;
    }

    @Override
    public void create() {
        Spx.setup();
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
        try {
            //$$$ Logger.devConsole("" + Gdx.graphics.getFramesPerSecond() + ": " + Gdx.graphics.getDeltaTime());

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
                Client.get().prepareForNextTurn();
            }
            else {
                Client.get().heartBeat();
            }
            if (!IsRunning) {
                System.exit(0);
            }

            TextPool.get().update();

            // Render
            Renderer.get().begin();
            StateManager.draw();
            ParticleEngine.draw();
            HudRenderer.get().draw();
            TextPool.get().draw();
            DevConsole.get().draw();
            Renderer.get().end();
        }
        catch (Exception e) {
            Logger.exception(e);
        }
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
