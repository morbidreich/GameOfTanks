package com.dziobaki;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Player extends Vehicles {

    int dLinear = 0;
    int dOmega = 0;

    double azimuth = 0;

    int speed = 5;
    int rotationSpeed = 4;

    AffineTransform affineTransform;

    List<Bullet> bulletList = new ArrayList<>();

    public Player(int x, int y, double hitPoints) {
        super(x, y, hitPoints);
    }

    public void keyPressed(KeyEvent e) {

        //handling key events based on key pressed
        switch (e.getKeyCode()) {
            //left arrow - move left
            case 37:
                dOmega = -rotationSpeed;
                break;
            case 38:
                //up arrow - move up
                dLinear = speed;
                break;
            //right arrow - move right
            case 39:
                dOmega = rotationSpeed;
                break;
            //down arrow - move down
            case 40:
                dLinear = -speed;
                break;
            //spacebar - fire bullet
            case 32:
                bulletList.add(calculateNewBullet());
                break;
        }
    }

    private Bullet calculateNewBullet() {
        return new Bullet(x + 14, y - 6, azimuth);


    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37, 39:
                dOmega = 0;
                break;
            case 38, 40:
                dLinear = 0;
                break;
        }
    }

    public void move() {
        //calculate lateral motion

        x += dLinear * Math.sin(Math.toRadians(azimuth));
        y -= dLinear * Math.cos(Math.toRadians(azimuth));


        //calculate angular motion
        double tempAzimuth = azimuth;
        tempAzimuth += dOmega;
        if (tempAzimuth > 360)
            azimuth = tempAzimuth - 360;
        else if (tempAzimuth < 0)
            azimuth = tempAzimuth + 360;
        else
            azimuth += dOmega;
    }

    @Override
    public void receiveDamage(double hit) {

    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }


    public double getAzimuth() {
        return azimuth;
    }

    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }
}
