package com.dziobaki;

import java.awt.*;

public class Enemy extends Vehicles {


    public Enemy(int x, int y, double hitPoints) {
        super(x, y, hitPoints);
    }

    @Override
    public void move() {

    }

    @Override
    public void receiveDamage(double hit) {

    }

    @Override
    public void draw(Graphics g) {
        //remember old color
        Color old = g.getColor();
        //set new color
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x-15, y-15, 30, 30);
        //revert back to old color for other elements
        g.setColor(old);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x-15, y-15, 30, 30);
    }
}
