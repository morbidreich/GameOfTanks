package com.dziobaki;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Vehicles {

    int moveIntent = 0;
    double azimuth = 0;
    int movementSpeed = 1;
    BufferedImage enemyImage;

    public Enemy(int x, int y, double hitPoints) {
        super(x, y, hitPoints);
        enemyImage = ImageLoader.loadImage("/textures/enemy1.png");
    }

    @Override
    public void move() {
        moveVer1();
    }

    private void moveVer2() {

    }

    private void moveVer1() {
        //some testing done, not the greatest AI xD tends to stick to
        //board bounds

        //ok so logic is as follows. Im generating move intents that lasts for
        // moveIntent ticks. each tick substract one. When moveIntent == 0 then
        //generate next random intent. In future add check if player is nearby.
        //when true i can modify movement behaviour to follow player or set
        //shoot intent
        // TODO add possibility of just standing still without moving
        if (moveIntent <= 0) {
            Random r = new Random();
            moveIntent = r.nextInt(400) - 200; //thats my movement length
            azimuth = r.nextDouble() * 359; //thats my movement direction
            movementSpeed = r.nextInt(2) - 1;
        }
        else {
            //generate motion based on enemy's speed and randomly selected azimuth

            x += movementSpeed * 2 * Math.sin(Math.toRadians(azimuth));
            y += movementSpeed * 2 * Math.cos(Math.toRadians(azimuth));

            //if entity is close to end of frame then stop and generate next move intent
            if (x < 20 || y < 20 || x > 780 || y > 680)
                moveIntent = 0;

            //decrease current movement intent duration
            moveIntent--;
        }
    }

    @Override
    public void draw(Graphics g) {
        //remember old color
        Color old = g.getColor();
        //set new color
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x-15, y-15, 30, 30);
        g.drawImage(enemyImage, x-15,y-15,null);

        drawHealthBar(g);

        //revert back to old color for other elements
        g.setColor(old);
    }

    private void drawHealthBar(Graphics g) {
        g.setColor(Color.black);
        //border
        g.drawRect(x-16, y-22, 31, 5);
        //actual health bar, first calculate percentage of full health
        double percentage = hitPoints / 30;
        //calculate part of bar to be painted red
        percentage *= 30;
        g.setColor(Color.red);
        g.fillRect(x-15, y-21, (int)percentage, 4);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x-15, y-15, 30, 30);
    }
}
