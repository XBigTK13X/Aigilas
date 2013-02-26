package aigilas;

import aigilas.hud.HudRenderer;
import aigilas.net.Client;
import aigilas.net.LocalClient;
import aigilas.states.MainMenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import sps.audio.MusicPlayer;
import sps.bridge.Bridge;
import sps.bridge.SpriteTypes;
import sps.bridge.Sps;
import sps.core.DevConsole;
import sps.core.Logger;
import sps.graphics.FrameStrategy;
import sps.graphics.Renderer;
import sps.graphics.SpriteSheetManager;
import sps.io.Input;
import sps.particles.ParticleEngine;
import sps.states.StateManager;
import sps.text.TextPool;

public class Aigilas implements ApplicationListener {
    private boolean IsRunning = true;
    private boolean built = false;

    private void setIsRunning(boolean isRunning) {
        IsRunning = isRunning;
    }

    @Override
    public void create() {
        Bridge.get();
        Sps.setup();
        Renderer.get().setWindowsBackground(Color.BLACK);
        Renderer.get().setStrategy(new FrameStrategy());
        Renderer.get().setRefreshInstance(this);
        Client.reset(new LocalClient());
        Input.get().setup(Client.get());
        SpriteSheetManager.setup(SpriteTypes.getDefs());
        StateManager.loadState(new MainMenuState());
        ParticleEngine.reset();
        MusicPlayer.get(new DefaultMusicPlayer());
        StateManager.loadContent();
    }

    @Override
    public void resize(int width, int height) {
        Renderer.get().resize(width, height);
    }

    @Override
    public void render() {
        try {
            //$$$ Logger.devConsole("" + Gdx.graphics.getFramesPerSecond() + ": " + Gdx.graphics.getDeltaTime());

            // Update
            Input.get().update();
            if (Input.get().isActive(sps.bridge.Commands.get(Commands.ToggleDevConsole), Client.get().getFirstPlayerIndex())) {
                DevConsole.get().toggle();
            }
            if (Input.get().isActive(sps.bridge.Commands.get(Commands.Back), Client.get().getFirstPlayerIndex())) {
                Gdx.app.exit();
            }
            if (Input.get().isActive(sps.bridge.Commands.get(Commands.Music), Client.get().getFirstPlayerIndex())) {
                MusicPlayer.get().toggle();
            }
            if (Input.get().isActive(sps.bridge.Commands.get(Commands.ToggleFullScreen), Client.get().getFirstPlayerIndex())) {
                Renderer.get().toggleFullScreen();
            }

            StateManager.asyncUpdate();

            if (Client.get().nextTurn()) {
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

    public static class Entities {

        //Ent
        public static final String Altar = "Altar";
        public static final String Combo_Marker = "Combo_Marker";
        public static final String Darkness = "Darkness";
        public static final String Downstairs = "Downstairs";
        public static final String Upstairs = "Upstairs";
        public static final String Skill_Effect = "Skill_Effect";
        public static final String Item = "Item";
        public static final String Wall = "Wall";
    }

    public static class Actors {

        public static final String Breaking_Wheel = "Breaking_Wheel";
        public static final String Envy = "Envy";
        public static final String Envy_Acolyte = "Envy_Acolyte";
        public static final String Gluttony = "Gluttony";
        public static final String Gluttony_Acolyte = "Gluttony_Acolyte";
        public static final String Greed = "Greed";
        public static final String Greed_Acolyte = "Greed_Acolyte";
        public static final String Hand = "Hand";
        public static final String Lust = "Lust";
        public static final String Lust_Acolyte = "Lust_Acolyte";
        public static final String Pride = "Pride";
        public static final String Pride_Acolyte = "Pride_Acolyte";
        public static final String Serpent = "Serpent";
        public static final String Sloth = "Sloth";
        public static final String Sloth_Acolyte = "Sloth_Acolyte";
        public static final String Wrath = "Wrath";
        public static final String Wrath_Acolyte = "Wrath_Acolyte";
        public static final String Dummy = "Dummy";
        public static final String Minion = "Minion";
    }

    public static class Commands {

        public static final String Back = "Back";
        public static final String Cancel = "Cancel";
        public static final String Confirm = "Confirm";
        public static final String CycleLeft = "Cycle_Left";
        public static final String CycleRight = "Cycle_Right";
        public static final String Hot_Skill_1 = "Hot_Skill_1";
        public static final String Hot_Skill_2 = "Hot_Skill_2";
        public static final String Hot_Skill_3 = "Hot_Skill_3";
        public static final String Inventory = "Inventory";
        public static final String LockSkill = "Lock_Skill";
        public static final String MoveDown = "Move_Down";
        public static final String MoveLeft = "Move_Left";
        public static final String MoveRight = "Move_Right";
        public static final String MoveUp = "Move_Up";
        public static final String Music = "Music";
        public static final String Skill = "Skill";
        public static final String Start = "Start";
        public static final String ToggleDevConsole = "Toggle_Dev_Console";
        public static final String ToggleFullScreen = "Toggle_Full_Screen";
    }

    public static class DrawDepths {
        public static final String Base_Skill_Effect = "Base_Skill_Effect";
        public static final String Creature = "Creature";
        public static final String Stairs = "Stairs";
        public static final String Hud_BG = "Hud_BG";
    }
}
