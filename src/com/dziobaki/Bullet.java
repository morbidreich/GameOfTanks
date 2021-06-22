package com.dziobaki;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bullet extends Projectile {

    int speed = 10;
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

    @Override
    public void draw(Graphics g) {
        //remember old color
        Color old = g.getColor();
        //set new color
        g.setColor(Color.red);
        g.fillOval(x-2, y-2, 4, 4);
        //revert back to old color for other elements
        g.setColor(old);

    }

    @Override
    public double getDamage() {

        Random r = new Random();
        //modifier is random value between -3 and 3
        double modifier = Math.sin(Math.toRadians(r.nextDouble() * 360)) * 3;
        return damage + modifier;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x-2, y-2, 4,4);
    }
}
