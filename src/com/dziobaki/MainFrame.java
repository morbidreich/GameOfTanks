package com.dziobaki;

import javax.swing.*;

public class MainFrame extends JFrame {


    public MainFrame() {

        add(new Board());

        //basic setup stuff

        setTitle("Insert game title here");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200,200);
        setSize(500,500);
        setVisible(true);
    }

}
