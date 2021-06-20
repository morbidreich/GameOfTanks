package com.dziobaki;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author w.maciejewski
 */
public class oko extends JFrame implements ActionListener{

    private Jwykres jw;
    private Timer time;
    private  int pozycja=0;
    public oko(){
        setSize(600,200);
        setVisible(true);
        jw =new Jwykres();
        time=new Timer(400,this);
        jw.dlugosc=398;
        getContentPane().add(jw,BorderLayout.CENTER);
        Dimension dim=getContentPane().getSize();
        jw.dim=dim;
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==time){
            jw.pozycja2=pozycja;
            pozycja++;
            repaint();
        }

    }

    private static class Jwykres extends JPanel {
        private Graphics2D bufor;
        private Color kolorlini=Color.DARK_GRAY;
        private Dimension dim;
        public int dlugosc;
        public int pozycja2;
        @Override

        public void paint( Graphics g ){
            if( g==null ) return;
            bufor = (Graphics2D)g;

            bufor.setPaintMode();
            bufor.setColor(kolorlini);
            Stroke drawingStroke = new BasicStroke(2);
            bufor.setStroke(drawingStroke);
            bufor.drawRect(0, 0, dim.width-1,dim.height-1);
            float stosunek=(float)dim.width/(float)dlugosc;
            double pom=stosunek*pozycja2;
            bufor.drawLine((int)pom, 0, (int)pom, dim.height);
            int[] xp={(int)pom-5,(int)pom+5,(int)pom};
            int[] yp={0,0,5};
            bufor.fillPolygon(xp,yp,3);
        }
    }
}
