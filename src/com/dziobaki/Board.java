package com.dziobaki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Board extends JPanel implements KeyListener, ActionListener {

    JLabel label, label2, label3;
    Player player;
    List<Enemy> enemyList;

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

        generateEnemies();


    }

    private void generateEnemies() {
        enemyList.add(new Enemy(100, 200, 30));
        enemyList.add(new Enemy(400, 300, 30));
        enemyList.add(new Enemy(300, 100, 30));
    }

    private void setUi() {
        this.setSize(500, 500);
        addKeyListener(this);

        label = new JLabel("Trololo");
        add(label);

        label2 = new JLabel("Diagnostics");
        add(label2);

        label3 = new JLabel("KURWA");
        add(label3);

        enemyList = new ArrayList<>();

        //without this panel is not able to listen to keyevents
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //set antialiasing
        Graphics2D g2d = (Graphics2D) g;
        setAntialiasing(g2d);

        player.draw(g2d);

        drawBullets(g2d);

        drawEnemies(g2d);

        repaint();
    }

    private void setAntialiasing(Graphics2D g) {
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void drawEnemies(Graphics2D g2d) {
        for (Enemy e : enemyList)
            e.draw(g2d);
    }

    private void drawBullets(Graphics g) {
        for (Bullet b : player.getBulletList())
            b.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
        updateEnemies();

        checkCollisions();
    }

    private void checkCollisions() {

        if (player.getBounds().intersects(label3.getBounds()))
            label3.setText("AAA DOSTALAM!");


        List<Bullet> bl = player.getBulletList();

        for (Bullet b : bl) {
            if (b.getBounds().intersects(label3.getBounds()))
                label3.setVisible(false);
        }


        //old approach, causes error - index out of bounds
//        for (int i = 0; i<bl.size(); i++)
//            for (int j = 0; j<enemyList.size();j++) {
//                if (bl.get(i).getBounds().intersects(enemyList.get(j).getBounds())); {
//                    bl.remove(i);
//                    enemyList.remove(j);
//                }
//            }

        for (Bullet b : player.getBulletList()) {
            for (Enemy e : enemyList) {
                if (b.getBounds().intersects(e.getBounds())) {
                    b.setVisible(false);
                    e.setVisible(false);
                }
            }
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

    private void updateEnemies() {
        // here will be enemy movement logic.. soon tm

        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isVisible())
                enemyList.remove(i);
        }
    }
}
