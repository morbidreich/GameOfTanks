package com.dziobaki;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject extends JComponent {
    int x;
    int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move();

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public abstract void draw(Graphics g);
}
