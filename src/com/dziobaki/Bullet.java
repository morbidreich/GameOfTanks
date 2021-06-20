package com.dziobaki;

import javax.swing.*;

public class Bullet extends GameObject {

    int speed = 7;
    double damage = 15;

    public Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        y -= speed;

    }
}
