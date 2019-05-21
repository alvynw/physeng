package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Simulator extends JPanel {

    int width;
    int height;
    List<Entity> entities = new LinkedList<Entity>(); //Once a circle or square is created, each shape will be put into this list.
    // go through list and add each entity to the panel

    int x;
    int y;

    public Simulator(int width, int height, List<Entity> entities) {

        this.width = width;
        this.height = height;
        this.entities = entities;

        setSize(width, height);
        setVisible(true);

        Timer timer = new Timer(33, (ActionEvent actionEvent) -> {
            x += 1;
            y+=1;
            repaint();
            //((Timer) actionEvent.getSource()).start();
        });

        timer.start();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g2d);


    }
}

