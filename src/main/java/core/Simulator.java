package core;

import javafx.scene.shape.Ellipse;
import physics.Vector2D;
import shapes.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.random;
import static utils.Path2DUtils.pathVertices;
import static utils.Path2DUtils.shift;

public class Simulator extends JPanel {

    int width;
    int height;
    List<Entity> entities = new LinkedList<Entity>();


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
            y += 1;
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
                g2d.setColor(circle.getColor());
                Ellipse2D circle2 = new Ellipse2D.Double(circle.getPosition().getX(), circle.getPosition().getY(), 10, 10);
                g2d.fill(circle2);
            }

            else
            {
                g2d.setColor(entity.getColor());
                g2d.fill(shift(entity.getShape(), entity.getPosition()));

                g2d.setColor(Color.BLACK);
                g2d.draw(shift(entity.getShape(), entity.getPosition()));

            }
        }
    }
}
