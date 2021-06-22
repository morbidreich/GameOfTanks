package com.dziobaki;

import java.awt.*;

public abstract class Projectile extends GameObject {

    int damage;

    public Projectile(int x, int y) {
        super(x, y);
    }

    public abstract void draw(Graphics g);

    public abstract double getDamage();
}
