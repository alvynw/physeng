package core;

import shapes.Circle;
import shapes.Ellipse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;

import static utils.Path2DUtils.shift;

public class Simulator extends JPanel {

    int width;
    int height;
    List<Entity> entities;


    public Simulator(int width, int height, List<Entity> entities) {

        this.width = width;
        this.height = height;
        this.entities = entities;

        setSize(width, height);
        setVisible(true);

        Timer timer = new Timer(33, (ActionEvent actionEvent) -> {
            repaint();
            //((Timer) actionEvent.getSource()).start();
        });

        timer.start();
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        for (int i = 0; i < entities.size(); i++) {

            Entity entity = entities.get(i);

            if (entity instanceof Circle) {
                Circle circle = (Circle) entity;
                Ellipse2D circle2D = new Ellipse2D.Double(circle.getPosition().getX() - circle.getRadius(), circle.getPosition().getY() - circle.getRadius(), circle.getHeight(), circle.getHeight());
                g2d.setColor(circle.getColor());
                g2d.fill(circle2D);

                g2d.setColor(Color.BLACK);
                g2d.draw(circle2D);
            } else {
                g2d.setColor(entity.getColor());
                g2d.fill(shift(entity.getShape(), entity.getPosition()));

                g2d.setColor(Color.BLACK);
                g2d.draw(shift(entity.getShape(), entity.getPosition()));

            }
        }
    }
}

