package com.xna.wrapper;

public class Environment {
	public static int TickCount(){
		return (int)System.currentTimeMillis();
	}

	public static void Exit(int status) {
		System.exit(status);
	}
}
