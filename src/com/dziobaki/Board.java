package com.dziobaki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;


public class Board extends JPanel implements KeyListener, ActionListener {

    JLabel label;
    Player player;

    Timer timer;
    int tick = 0;

    List<GameObject> gameObjects = new ArrayList<>();

    //main game surface
    public Board() {

        setUi();

        timer = new Timer(20, this);
        timer.start();

        player = new Player(getWidth() / 2, getHeight() / 2, 10);
        add(player);


    }

    private void setUi() {
        this.setSize(500, 500);
        addKeyListener(this);
        label = new JLabel("Trololo");
        add(label);

        //without this panel is not able to listen to keyevents
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPlayer(g);
        drawBullets(g);

        repaint();
    }

    //some fancy graphics methods

    private void drawBullets(Graphics g) {
        g.setColor(Color.red);


        for (Bullet b : player.getBulletList()) {
            g.drawOval(b.getX(), b.getY(), 1, 1);
            g.drawOval(b.getX(), b.getY(), 2, 2);
            g.drawOval(b.getX(), b.getY(), 3, 3);
            g.drawOval(b.getX(), b.getY(), 4, 4);
        }
    }


    private void drawPlayer(Graphics g) {

        //cast g to g2d for transformation godness
        Graphics2D g2d = (Graphics2D) g;

        //remeber correct orientation
        AffineTransform preRotate = g2d.getTransform();



        //rotate tank around main body
        g2d.rotate(Math.toRadians(player.azimuth), player.getX() + 15, player.getY() + 20);
        //pass AffineTransport to player object for clculating starting point of bullets as getShearX/Y
        player.setAffineTransform(g2d.getTransform());
        //main body
        g.drawRect(player.getX(), player.getY(), 30, 40);
        //turret
        g.drawOval(player.getX()+5, player.getY()+5, 20, 20);
        //barrel
        g.drawRect(player.getX()+15, player.getY() - 12, 2, 18);



        //return to pre transform orientation of g2d
        g2d.setTransform(preRotate);





    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //label.setText("kliknieto " + e.getKeyCode());
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move();
        label.setText(String.valueOf(player.getAzimuth()));
        updateBullets();
        checkCollisions();

        //label.setText(player.getMotionDataString());


    }

    private void checkCollisions() {

        Rectangle rLabel = label.getBounds();

        List<Bullet> bl = player.getBulletList();

        for (Bullet b : bl) {
            Rectangle rB = b.getBounds();

            if (rLabel.intersects(rB))
                setBackground(Color.blue);
        }
    }

    private void updateBullets() {
        List<Bullet> bulletList = player.getBulletList();

        for (int i = 0; i < bulletList.size(); i++) {

            Bullet b = bulletList.get(i);

            b.move();

            if (!b.isVisible())
                bulletList.remove(i);
        }
    }
}
