package sps.io;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import org.apache.commons.lang3.SystemUtils;

import java.util.HashMap;
import java.util.Map;

public class XBox360Controller {
    private static XBox360Controller instance = null;

    private static ControllerListener initTriggers = new ControllerListener() {
        @Override
        public void connected(Controller controller) {
        }

        @Override
        public void disconnected(Controller controller) {
        }

        @Override
        public boolean buttonDown(Controller controller, int i) {
            get().controllers.get(controller).buttons.put(i, true);
            return false;
        }

        @Override
        public boolean buttonUp(Controller controller, int i) {
            get().controllers.get(controller).buttons.put(i, false);
            return false;
        }

        @Override
        public boolean axisMoved(Controller controller, int axisIndex, float value) {
            if (axisIndex == Buttons.RightShoulder.Index) {
                XBox360Controller.RightShoulderInit();
            }
            if (axisIndex == Buttons.LeftShoulder.Index) {
                XBox360Controller.LeftShoulderInit();
            }
            get().controllers.get(controller).axes.put(axisIndex, value);
            return false;
        }

        @Override
        public boolean povMoved(Controller controller, int i, PovDirection povDirection) {
            get().controllers.get(controller).povs.put(i, povDirection);
            return false;
        }

        @Override
        public boolean xSliderMoved(Controller controller, int i, boolean b) {
            return false;
        }

        @Override
        public boolean ySliderMoved(Controller controller, int i, boolean b) {
            return false;
        }

        @Override
        public boolean accelerometerMoved(Controller controller, int i, Vector3 vector3) {
            return false;
        }
    };

    public static XBox360Controller get() {
        if (instance == null) {
            Controllers.addListener(initTriggers);
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

    private class ControllerState {
        private Map<Integer, Float> axes = new HashMap<Integer, Float>();
        private Map<Integer, Boolean> buttons = new HashMap<Integer, Boolean>();
        private Map<Integer, PovDirection> povs = new HashMap<Integer, PovDirection>();
    }

    private Map<Controller, ControllerState> controllers = new HashMap<Controller, ControllerState>();

    private XBox360Controller() {
        for (Controller c : Controllers.getControllers()) {
            controllers.put(c, new ControllerState());
            for (Buttons btn : Buttons.values()) {
                controllers.get(c).buttons.put(btn.Index, false);
                controllers.get(c).axes.put(btn.Index, 0f);
                controllers.get(c).povs.put(btn.Index, PovDirection.center);
            }
        }
    }

    private float deadZone = .5f;
    private float zeroPoint = -.5f;

    private boolean isDown(Controller controller, Integer index) {
        return controllers.get(controller).buttons.get(index);
    }

    private boolean isPositive(Controller controller, Integer axis) {
        return controllers.get(controller).axes.get(axis) > deadZone;
    }

    private boolean isNegative(Controller controller, Integer axis) {
        return controllers.get(controller).axes.get(axis) < -deadZone;
    }

    public boolean isActive(Buttons button, int controllerIndex) {
        if (button == null || Controllers.getControllers().size <= controllerIndex) {
            return false;
        }
        Controller controller = Controllers.getControllers().get(controllerIndex);
        switch (button) {
            case LeftStickButton:
                return isDown(controller, button.Index);
            case LeftStickUp:
                return isNegative(controller, button.Index);
            case LeftStickDown:
                return isPositive(controller, button.Index);
            case LeftStickLeft:
                return isNegative(controller, button.Index);
            case LeftStickRight:
                return isPositive(controller, button.Index);
            case RightStickButton:
                return isDown(controller, button.Index);
            case RightStickUp:
                return isNegative(controller, button.Index);
            case RightStickDown:
                return isPositive(controller, button.Index);
            case RightStickLeft:
                return isNegative(controller, button.Index);
            case RightStickRight:
                return isPositive(controller, button.Index);
            case RightTrigger:
                if (SystemUtils.IS_OS_WINDOWS) {
                    return controller.getAxis(button.Index) < zeroPoint;
                }
                return controller.getAxis(button.Index) > zeroPoint && rightShoulderInit;
            case LeftTrigger:
                if (SystemUtils.IS_OS_WINDOWS) {
                    return controller.getAxis(button.Index) > -zeroPoint;
                }
                return controller.getAxis(button.Index) > zeroPoint && leftShoulderInit;
            case RightShoulder:
                return isDown(controller, button.Index);
            case LeftShoulder:
                return isDown(controller, button.Index);
            case Start:
                return isDown(controller, button.Index);
            case Back:
                return isDown(controller, button.Index);
            case X:
                return isDown(controller, button.Index);
            case A:
                return isDown(controller, button.Index);
            case Y:
                return isDown(controller, button.Index);
            case B:
                return isDown(controller, button.Index);
            case DPadUp:
                return controller.getPov(button.Index) == PovDirection.north;
            case DPadDown:
                return controller.getPov(button.Index) == PovDirection.south;
            case DPadLeft:
                return controller.getPov(button.Index) == PovDirection.west;
            case DPadRight:
                return controller.getPov(button.Index) == PovDirection.east;
        }
        return false;
    }
}
