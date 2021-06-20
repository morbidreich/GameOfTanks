package com.dziobaki;

public abstract class Vehicles extends GameObject {

    double hitPoints;

    public Vehicles(int x, int y, double hitPoints) {
        super(x, y);
        this.hitPoints = hitPoints;
    }

    @Override
    public void move() {

    }

    public abstract void receiveDamage(double hit);


}
