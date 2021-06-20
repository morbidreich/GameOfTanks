package com.dziobaki;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Vehicles {

    int dx = 0;
    int dy = 0;
    List<Bullet> bulletList = new ArrayList<>();

    int speed = 4;

    public Player(int x, int y, double hitPoints) {
        super(x, y, hitPoints);
    }

    public void keyPressed(KeyEvent e) {

        //handling key events based on key pressed
        switch (e.getKeyCode()) {
            //left arrow - move left
            case 37:
                dx = -speed;
                break;
            case 38:
                //up arrow - move up
                dy = -speed;
                break;
                //right arrow - move right
            case 39:
                dx = speed;
                break;
                //down arrow - move down
            case 40:
                dy = speed;
                break;
            //spacebar - fire bullet
            case 32:
                bulletList.add(new Bullet(x+14,y-8));
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37, 39:
                dx = 0;
                break;
            case 38, 40:
                dy = 0;
                break;
        }
    }

    public void move() {
        x += dx;
        y += dy;
    }

    @Override
    public void receiveDamage(double hit) {

    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getSpeed() {
        return speed;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    //returns all data regarding move parameters of object
    public String getMotionDataString() {
        return "Current XY: " + x + " " + y + "\n" +
                "Currend dx dy: " + dx + " " + dy + "\n" +
                "Current speed: " + speed;
    }
}
