package sps.io;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import org.apache.commons.lang3.SystemUtils;

public class XBox360Controller {
    private static XBox360Controller instance = null;

    public static XBox360Controller get() {
        if (instance == null) {
            instance = new XBox360Controller();
        }
        return instance;
    }

    private static boolean rightShoulderInit = false;

    public static void RightShoulderInit() {
        rightShoulderInit = true;
    }

    private static boolean leftShoulderInit = false;

    public static void LeftShoulderInit() {
        leftShoulderInit = true;
    }

    private XBox360Controller() {
    }

    public boolean isActive(XBox360ControllerInputs button, int controllerIndex) {
        if (button == null || Controllers.getControllers().size <= controllerIndex) {
            return false;
        }
        Controller controller = Controllers.getControllers().get(controllerIndex);
        switch (button) {
            case RightTrigger:
                if (SystemUtils.IS_OS_WINDOWS) {
                    return controller.getAxis(button.Index) < ControllerAdapter.ZeroPoint;
                }
                return controller.getAxis(button.Index) > ControllerAdapter.ZeroPoint && rightShoulderInit;
            case LeftTrigger:
                if (SystemUtils.IS_OS_WINDOWS) {
                    return controller.getAxis(button.Index) > -ControllerAdapter.ZeroPoint;
                }
                return controller.getAxis(button.Index) > ControllerAdapter.ZeroPoint && leftShoulderInit;
            default:
                return button.Input.isActive(controller);
        }
    }
}
