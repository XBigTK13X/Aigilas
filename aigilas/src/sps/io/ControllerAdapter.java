package sps.io;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

public class ControllerAdapter {
    public final static float DeadZone = .5f;
    public final static float ZeroPoint = -.5f;
    public final static float MaxInputsPerDevice = 50;
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
    private static ControllerAdapter instance;
    private Map<Controller, ControllerState> controllers;

    private ControllerAdapter() {
        if (controllers == null) {
            controllers = new HashMap<Controller, ControllerState>();
            for (Controller c : Controllers.getControllers()) {
                controllers.put(c, new ControllerState());
                for (int ii = 0; ii < MaxInputsPerDevice; ii++) {
                    try {
                        c.getButton(ii);
                        controllers.get(c).buttons.put(ii, false);
                    }
                    catch (Exception e) {

                    }
                    try {
                        c.getAxis(ii);
                        controllers.get(c).axes.put(ii, Float.MIN_VALUE);
                    }
                    catch (Exception e) {

                    }
                    try {
                        c.getPov(ii);
                        controllers.get(c).povs.put(ii, PovDirection.center);
                    }
                    catch (Exception e) {

                    }
                }
            }
        }
    }

    public static ControllerAdapter get() {
        if (instance == null) {
            Controllers.addListener(initTriggers);
            instance = new ControllerAdapter();
        }
        return instance;
    }

    public boolean isDown(Controller controller, Integer index) {
        return controllers.get(controller).buttons.get(index);
    }

    public boolean isPositive(Controller controller, Integer axis) {
        return controllers.get(controller).axes.get(axis) > DeadZone;
    }

    public boolean isNegative(Controller controller, Integer axis) {
        float value = controllers.get(controller).axes.get(axis);
        return value < -DeadZone && value != Float.MIN_VALUE;
    }

    public boolean isNotZero(Controller controller, Integer axis) {
        return controllers.get(controller).axes.get(axis) > ZeroPoint;
    }

    public boolean isAxisGreaterThan(Controller controller, Integer axis, Float threshold) {
        return controllers.get(controller).axes.get(axis) > threshold;
    }

    public boolean isAxisLessThan(Controller controller, Integer axis, Float threshold) {
        float value = controllers.get(controller).axes.get(axis);
        return value < threshold && value != Float.MIN_VALUE;
    }

    public boolean isPovActive(Controller controller, Integer index, PovDirection direction) {
        return controllers.get(controller).povs.get(index) == direction;
    }

    private class ControllerState {
        private Map<Integer, Float> axes = new HashMap<Integer, Float>();
        private Map<Integer, Boolean> buttons = new HashMap<Integer, Boolean>();
        private Map<Integer, PovDirection> povs = new HashMap<Integer, PovDirection>();
    }
}