package aigilas;

import aigilas.management.Commands;
import aigilas.management.InputInitializer;
import aigilas.management.SpriteInitializer;
import aigilas.states.MainMenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import spx.core.Settings;
import spx.core.SpxManager;
import spx.devtools.DevConsole;
import spx.graphics.SpriteSheetManager;
import spx.io.Input;
import spx.io.PlayerIndex;
import spx.net.Client;
import spx.particles.ParticleEngine;
import spx.states.StateManager;
import spx.text.TextManager;

public class Aigilas implements ApplicationListener {
    private boolean IsRunning = true;

    private void setIsRunning(boolean isRunning) {
        IsRunning = isRunning;
    }

    @Override
    public void create() {
        SpxManager.setup();
        Input.setup(new InputInitializer());
        SpriteSheetManager.setup(new SpriteInitializer());
        StateManager.loadState(new MainMenuState());
        ParticleEngine.reset();
        StateManager.loadContent();
        // //$$$MediaPlayer.Play(Content.Load<Song>("MainTheme"));
        // //$$$MediaPlayer.IsRepeating = true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        if (Settings.get().consoleLogging) {
            DevConsole.get().add("" + Gdx.graphics.getFramesPerSecond() + ": " + Gdx.graphics.getDeltaTime());
        }

        // Update
        Input.update();
        if (Input.isActive(Commands.ToggleDevConsole, Client.get().getFirstPlayerIndex())) {
            DevConsole.get().toggle();
        }
        if (Client.get().nextTurn()) {
            for (int ii = 0; ii < 4; ii++) {
                PlayerIndex player = PlayerIndex.values()[ii];
                /*
                     * //$$$ if (GamePad.GetState(player).IsPressed(Buttons.Back) &&
                     * GamePad.GetState(player).IsPressed(Buttons.Start)) {
                     * setIsRunning(false); }
                     */
            }
            ParticleEngine.update();
            StateManager.update();
            TextManager.update();
            Client.get().prepareForNextTurn();
        } else {
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
