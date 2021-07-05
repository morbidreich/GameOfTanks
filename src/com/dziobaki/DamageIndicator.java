package com.dziobaki;

import java.awt.*;

// this class is used when bullet hits enemy. If that happens
// then damage dealt is handled and displayed by this class
// as floating number that dissapears after a while
public class DamageIndicator extends GameObject {

    private double damageDealt;

    //this variable is reduced by one at each tick. When it reaches zero, then
    //this object is considered as expired, and will not be drawn anymore
    private int expirationTimer = 20;

    public DamageIndicator(int x, int y, double damageDealt) {
        super(x, y);
        this.damageDealt = damageDealt;
    }

    @Override
    // modify only y to show damage as digit floating upward
    // reduce expirationTimer
    // TODO implement floating upward with some sideways wavy motion
    public void move() {
        y = y - 2;
        expirationTimer--;
    }

    @Override
    public void draw(Graphics g) {
        if (expirationTimer > 0) {
            Color old = g.getColor();
            g.setColor(Color.red);

            g.drawString(String.valueOf(Math.round(damageDealt)), x, y - 20);
            g.setColor(old);
        }
    }
}
