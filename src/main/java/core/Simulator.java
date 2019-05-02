package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Simulator extends JPanel {

    int width;
    int height;
    List<Entity> entities;

    int x;

    public Simulator(int width, int height, List<Entity> entities) {

        this.width = width;
        this.height = height;
        this.entities = entities;

        setSize(width, height);
        setVisible(true);

        Timer timer = new Timer(33, (ActionEvent actionEvent) -> {
            x += 10;
            repaint();
            //((Timer) actionEvent.getSource()).start();
        });

        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.RED);
        g.drawLine(100 + x, 100, 200, 200);
    }
}
