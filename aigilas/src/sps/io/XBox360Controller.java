package sps.io;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;

public class XBox360Controller {
    private static XBox360Controller instance = null;

    public static XBox360Controller get() {
        if (instance == null) {
            instance = new XBox360Controller();
        }
        return instance;
    }

    private XBox360Controller() {

    }

    private float deadZone = .5f;
    private float zeroPoint = -.5f;

    public boolean isActive(Buttons button, int controllerIndex) {
        if (button == null || Controllers.getControllers().size <= controllerIndex) {
            return false;
        }
        Controller controller = Controllers.getControllers().get(controllerIndex);
        switch (button) {
            case LeftStickButton:
                return controller.getButton(9);
            case LeftStickUp:
                return controller.getAxis(1) < -deadZone;
            case LeftStickDown:
                return controller.getAxis(1) > deadZone;
            case LeftStickLeft:
                return controller.getAxis(0) < -deadZone;
            case LeftStickRight:
                return controller.getAxis(0) > deadZone;
            case RightStickButton:
                return controller.getButton(10);
            case RightStickUp:
                return controller.getAxis(4) < -deadZone;
            case RightStickDown:
                return controller.getAxis(4) > deadZone;
            case RightStickLeft:
                return controller.getAxis(3) < -deadZone;
            case RightStickRight:
                return controller.getAxis(3) > deadZone;
            case RightTrigger:
                return controller.getAxis(5) > zeroPoint;
            case LeftTrigger:
                return controller.getAxis(2) > zeroPoint;
            case RightShoulder:
                return controller.getButton(5);
            case LeftShoulder:
                return controller.getButton(4);
            case Start:
                return controller.getButton(7);
            case Back:
                return controller.getButton(6);
            case X:
                return controller.getButton(3);
            case A:
                return controller.getButton(0);
            case Y:
                return controller.getButton(2);
            case B:
                return controller.getButton(1);
            case DPadUp:
                return controller.getPov(0) == PovDirection.north;
            case DPadDown:
                return controller.getPov(0) == PovDirection.south;
            case DPadLeft:
                return controller.getPov(0) == PovDirection.west;
            case DPadRight:
                return controller.getPov(0) == PovDirection.east;
        }
        return false;
    }
}
