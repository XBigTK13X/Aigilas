package sps.util;

public class MathHelper {

    public static int clamp(float value, int min, int max) {
        return (int) Math.max(Math.min(value, max), min);
    }

    public static int wrap(float value, int min, int max){
        if(value > max){
            return min;
        }
        if(value < min){
            return max;
        }
        return (int)value;
    }
}
