package xna.wrapper; import com.badlogic.gdx.graphics.Color;public class Environment {	public static int TickCount(){		return (int)System.currentTimeMillis();	}	public static void Exit(int status) {		System.exit(status);	}	public static void Exit() {		Exit(0);	}}