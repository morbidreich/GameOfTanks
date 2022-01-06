package com.dziobaki;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        Board board = new Board();
        add(board);

        //basic setup stuff

        setTitle("GameOfTanks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200,200);

        Dimension d = new Dimension(800,700);

        setPreferredSize(d);

        setResizable(false);

        setVisible(true);
        pack();
    }

}
