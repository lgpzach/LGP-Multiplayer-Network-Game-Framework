package com.lgposse.tests;

public class CircleCoordsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CircleCoordsTest().run();
	}
	
	public void run() {
		int a = 640 / 2;
        int b = 480 / 2;
        int m = Math.min(a, b);
        int r = 4 * m / 5;
		int n = 5;
		for (int i = 0; i < n; i++) {
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            System.out.println(x);
            System.out.println(y);
        }
	}

}
