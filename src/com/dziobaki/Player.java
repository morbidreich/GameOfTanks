package com.dziobaki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Vehicles {

    int dLinear = 0;
    int dOmega = 0;

    double azimuth = 0;

    int speed = 5;
    int rotationSpeed = 4;

    public BufferedImage tankImage;
    AffineTransform affineTransform;

    List<Bullet> bulletList = new ArrayList<>();

    public Player(int x, int y, double hitPoints) {
        super(x, y, hitPoints);

        tankImage = ImageLoader.loadImage("/textures/tank.png");
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
                fireWeapon();
                break;
        }
    }

    private void fireWeapon() {
        bulletList.add(calculateNewBullet());
    }

    private Bullet calculateNewBullet() {

        //calculate new starting point given rotation of vehicle
        //previously tried to do that using Graphics.getAffineTransform() (hence setAffineTransform method below)
        //but apparently im too stupid to understand how to use that lol
        double a = Math.toRadians(azimuth);

        //calculate coordinates of middle of vehicle
        double newX = x + 16;
        double newY = y + 18;

        //rotate around middle of vehicle
        newX += 28 * Math.sin(a);
        newY -= 28 * Math.cos(a);


        return new Bullet((int)newX, (int)newY, azimuth);


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
        //temp variable to handle passing through 0/360 degrees
        double tempAzimuth = azimuth;
        tempAzimuth += dOmega;
        if (tempAzimuth > 360)
            azimuth = tempAzimuth - 360;
        else if (tempAzimuth < 0)
            azimuth = tempAzimuth + 360;
        else
            azimuth += dOmega;
    }

    public void draw(Graphics g) {
        //cast g to g2d for transformation godness
        Graphics2D g2d = (Graphics2D) g;

        //remeber correct orientation
        AffineTransform preRotate = g2d.getTransform();




        //rotate tank around centre main body
        g2d.rotate(Math.toRadians(this.azimuth), this.getX() + 15, this.getY() + 20);

        g.drawImage(tankImage, x, y,null);
        //pass AffineTransport to player object for clculating starting point of bullets as getShearX/Y
        this.setAffineTransform(g2d.getTransform());
        //main body
        //g.drawRect(this.getX(), this.getY(), 30, 40);
        //turret
        //g.drawOval(this.getX() + 5, this.getY() + 5, 20, 20);
        //barrel
        //g.drawRect(this.getX() + 14, this.getY() - 12, 2, 18);


        //return to pre transform orientation of g2d
        g2d.setTransform(preRotate);
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

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), 30, 40);

    }
}
