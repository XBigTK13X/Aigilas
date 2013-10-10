package targets;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector3;
import sps.core.SpsConfig;

public class GamepadTest extends InputAdapter implements ApplicationListener {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.width = SpsConfig.get().resolutionWidth;
        cfg.height = SpsConfig.get().resolutionHeight;
        new LwjglApplication(new GamepadTest(), cfg);
    }

    String descriptor;

    @Override
    public void create() {
        // print the currently connected controllers to the console
        print("Controllers: " + Controllers.getControllers().size);
        int i = 0;
        for (Controller controller : Controllers.getControllers()) {
            print("#" + i++ + ": " + controller.getName());
        }
        if (Controllers.getControllers().size == 0) {
            print("No controllers attached");
        }

        // setup the listener that prints events to the console
        Controllers.addListener(new ControllerListener() {
            public int indexOf(Controller controller) {
                return Controllers.getControllers().indexOf(controller, true);
            }

            @Override
            public void connected(Controller controller) {
                print("connected " + controller.getName());
                int i = 0;
                for (Controller c : Controllers.getControllers()) {
                    print("#" + i++ + ": " + c.getName());
                }
            }

            @Override
            public void disconnected(Controller controller) {
                print("disconnected " + controller.getName());
                int i = 0;
                for (Controller c : Controllers.getControllers()) {
                    print("#" + i++ + ": " + c.getName());
                }
                if (Controllers.getControllers().size == 0) {
                    print("No controllers attached");
                }
            }

            @Override
            public boolean buttonDown(Controller controller, int buttonIndex) {
                print("#" + indexOf(controller) + ", button " + buttonIndex + " down" + controller.getButton(buttonIndex));
                return false;
            }

            @Override
            public boolean buttonUp(Controller controller, int buttonIndex) {
                print("=============================clear=================");
                print("#" + indexOf(controller) + ", button " + buttonIndex + " up");
                return false;
            }

            @Override
            public boolean axisMoved(Controller controller, int axisIndex, float value) {
                print("#" + indexOf(controller) + ", axis " + axisIndex + ": " + value);
                return false;
            }

            @Override
            public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
                print("#" + indexOf(controller) + ", pov " + povIndex + ": " + value);
                return false;
            }

            @Override
            public boolean xSliderMoved(Controller controller, int sliderIndex, boolean value) {
                print("#" + indexOf(controller) + ", x slider " + sliderIndex + ": " + value);
                return false;
            }

            @Override
            public boolean ySliderMoved(Controller controller, int sliderIndex, boolean value) {
                print("#" + indexOf(controller) + ", y slider " + sliderIndex + ": " + value);
                return false;
            }

            @Override
            public boolean accelerometerMoved(Controller controller, int accelerometerIndex, Vector3 value) {
                // not printing this as we get to many values
                return false;
            }
        });
    }

    @Override
    public void resize(int i, int i2) {
    }

    @Override
    public void render() {
        //Macair was running loud and hot without this throttle
        try {
            Thread.sleep(50L);
        }
        catch (Exception e) {

        }
        //Macair screen was garbled without this clearing
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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

    private void print(String message) {
        System.out.println(message);
    }
}