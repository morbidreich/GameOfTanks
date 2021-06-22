package com.dziobaki;

import javax.swing.*;

public class Bullet extends GameObject {

    int speed = 7;
    double azimuth;

    double damage = 15;

    public Bullet(int x, int y, double azimuth) {
        super(x, y);
        this.azimuth = azimuth;
    }

    @Override
    public void move() {
        x += speed * Math.sin(Math.toRadians(azimuth));
        y -= speed * Math.cos(Math.toRadians(azimuth));

    }
}
