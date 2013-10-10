package sps.util;

public class RealTime {
    private static RealTime instance = new RealTime();
    private long lastTime;
    private float delta;

    private RealTime() {
        lastTime = System.currentTimeMillis();
    }

    public static RealTime get() {
        return instance;
    }

    public float delta() {
        return delta;
    }

    public void update() {
        delta = (System.currentTimeMillis() - lastTime) / 1000f;
        lastTime = System.currentTimeMillis();
    }

}
