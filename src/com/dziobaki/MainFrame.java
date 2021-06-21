package com.dziobaki;

import javax.swing.*;

public class MainFrame extends JFrame {


    public MainFrame() {

        Board board = new Board();
        add(board);
        board.start();

        //basic setup stuff

        setTitle("GameOfTanks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200,200);
        setSize(500,500);
        setVisible(true);
    }

}
