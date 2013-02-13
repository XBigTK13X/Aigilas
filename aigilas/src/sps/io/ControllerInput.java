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
    private Device device;
    private Float threshold;
    private Boolean greaterThan;

    private ControllerInput(Integer buttonIndex, Integer axisIndex, Integer povIndex, PovDirection direction, Boolean nonzero, Boolean positive, Float threshold, Boolean greaterThan) {
        pov = povIndex;
        povDirection = direction;
        axis = axisIndex;
        button = buttonIndex;
        this.positive = positive;
        nonZero = nonzero;
        if (pov != null) {
            device = Device.Pov;
        }
        else if (axis != null) {
            device = Device.Axis;
        }
        else {
            device = Device.Button;
        }
        this.threshold = threshold;
        this.greaterThan = greaterThan;
    }

    public static ControllerInput createButton(int index) {
        return new ControllerInput(index, null, null, null, null, null, null, null);
    }

    public static ControllerInput createNonZeroAxis(int index) {
        return new ControllerInput(null, index, null, null, true, null, null, null);
    }

    public static ControllerInput createPositiveAxis(int index) {
        return new ControllerInput(null, index, null, null, null, true, null, null);
    }

    public static ControllerInput createNegativeAxis(int index) {
        return new ControllerInput(null, index, null, null, null, false, null, null);
    }

    public static ControllerInput createGreaterThanAxis(int index, float threshold) {
        return new ControllerInput(null, index, null, null, null, null, threshold, true);
    }

    public static ControllerInput createLessThanAxis(int index, float threshold) {
        return new ControllerInput(null, index, null, null, null, null, threshold, false);
    }

    public static ControllerInput createPov(int index, PovDirection direction) {
        return new ControllerInput(null, null, index, direction, null, null, null, null);
    }

    public static ControllerInput parse(String source) {
        String[] parts = source.split("/");
        //Wired Xbox 360 controllers are the only supported, non-serialized input
        if (parts.length == 1) {
            return XBox360ControllerInputs.get(parts[0]).Input;
        }
        else {
            Device device = Device.get(parts[0]);
            int index = Integer.parseInt(parts[1]);
            switch (device) {
                case Button:
                    return createButton(index);
                case Pov:
                    int povIndex = index;
                    PovDirection direction = getDirection(parts[2]);
                    return createPov(povIndex, direction);
                case Axis:
                    String trigger = parts[2];
                    if (trigger.equalsIgnoreCase("nonzero")) {
                        return createNonZeroAxis(index);
                    }
                    else if (trigger.equalsIgnoreCase("positive")) {
                        return createPositiveAxis(index);
                    }
                    else {
                        return createNegativeAxis(index);
                    }
            }
        }
        return null;
    }

    private static PovDirection getDirection(String name) {
        for (PovDirection direction : PovDirection.values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                return direction;
            }
        }
        return null;
    }

    public String serialize() {
        String result = "";
        result += device.name() + "/";
        switch (device) {
            case Button:
                result += button;
                break;
            case Pov:
                result += pov + "/";
                result += povDirection.name();
                break;
            case Axis:
                result += axis + "/";
                result += (positive == null) ? "NonZero" : (positive) ? "Positive" : "Negative";
                break;
        }
        return result;
    }

    public boolean isActive(Controller controller) {
        if (threshold != null) {
            if (greaterThan) {
                return ControllerAdapter.get().isAxisGreaterThan(controller, axis, threshold);
            }
            else {
                return !ControllerAdapter.get().isAxisGreaterThan(controller, axis, threshold);
            }
        }
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

    private enum Device {
        Button,
        Pov,
        Axis;

        public static Device get(String name) {
            for (Device device : values()) {
                if (device.name().equalsIgnoreCase(name)) {
                    return device;
                }
            }
            return null;
        }
    }

}