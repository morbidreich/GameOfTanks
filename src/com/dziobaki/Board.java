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



        Graphics2D g2d = (Graphics2D)g;

        AffineTransform old = g2d.getTransform();

        g2d.rotate(Math.toRadians(90));
        //main body
        g2d.drawRect(player.getX(), player.getY(), 30, 40);
        //turret
        g2d.drawOval(player.getX()+5, player.getY()+5, 20, 20);
        //barrel
        g2d.drawRect(player.getX()+15, player.getY() - 12, 2, 18);

        g2d.setTransform(old);

        //repaint();

        //put your code here

        /*
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0,0,80,130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg =0; deg <360; deg+=5) {
            AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }*/

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
