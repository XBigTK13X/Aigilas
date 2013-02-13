package sps.io;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import sps.core.Logger;

public class ControllerInput {
    private PovDirection povDirection;
    private Integer pov;
    private Integer button;
    private Integer axis;
    private Boolean positive;
    private Boolean nonZero;

    public static ControllerInput createButton(int index) {
        return new ControllerInput(index, null, null, null, null, null);
    }

    public static ControllerInput createNonZeroAxis(int index) {
        return new ControllerInput(null, index, null, null, true, null);
    }

    public static ControllerInput createPositiveAxis(int index) {
        return new ControllerInput(null, index, null, null, null, true);
    }

    public static ControllerInput createNegativeAxis(int index) {
        return new ControllerInput(null, index, null, null, null, false);
    }

    public static ControllerInput createPov(int index, PovDirection direction) {
        return new ControllerInput(null, null, index, direction, null, null);
    }

    private ControllerInput(Integer buttonIndex, Integer axisIndex, Integer povIndex, PovDirection direction, Boolean nonzero, Boolean positive) {
        pov = povIndex;
        povDirection = direction;
        axis = axisIndex;
        button = buttonIndex;
        this.positive = positive;
        nonZero = nonzero;
    }

    public boolean isActive(Controller controller) {
        if (button != null) {
            return ControllerAdapter.get().isDown(controller, button);
        }
        if (axis != null) {
            if (positive != null) {
                if (positive) {
                    return ControllerAdapter.get().isPositive(controller, axis);
                }
                if (!positive) {
                    return ControllerAdapter.get().isNegative(controller, axis);
                }
            }
            else {
                return ControllerAdapter.get().isNotZero(controller, axis);
            }
            Logger.error("Invalid axis ControllerInput defined");
            return false;
        }
        return controller.getPov(pov) == povDirection;
    }
}