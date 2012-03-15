package com.lgposse.thirdparty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleTest extends JPanel {

	private static final long serialVersionUID = 3260206542526016871L;
	private static final int SIZE = 256;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int n;

    /** @param n  the desired number of circles. */
    public CircleTest(int n) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n = n;
        this.setBackground(new Color(0,100,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;
        //g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        int c = 255;
        for (int i = 0; i < n; i++) {
        	g2d.setColor(new Color(c,255,255));
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            System.out.println("x=" + Integer.toString(x) + " y=" + Integer.toString(y));
            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
            c -= 50;
        }
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.add(new CircleTest(4));
        f.pack();
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }
}