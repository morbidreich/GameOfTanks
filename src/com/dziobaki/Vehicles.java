package com.dziobaki;

import java.awt.*;

public abstract class Vehicles extends GameObject {

    double hitPoints;

    public Vehicles(int x, int y, double hitPoints) {
        super(x, y);
        this.hitPoints = hitPoints;
    }

    public abstract void move();

    public void receiveDamage(Projectile projectile) {
        this.hitPoints -= projectile.getDamage();
    }

    public double getHitPoints() {
        return this.hitPoints;
    }


}
