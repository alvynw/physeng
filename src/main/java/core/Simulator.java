package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.*;
import java.util.LinkedList;
import java.util.List;
import static utils.Path2DUtils.shift;

public class Simulator extends JPanel {

    int width;
    int height;
    List<Entity> entities = new LinkedList<Entity>();


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


            /*

            Coming soon


            if (entity instanceof Circle) {
                Circle circle = (Circle) entity;
                g2d.setColor(circle.getColor());
                Ellipse2D circle2 = new Ellipse2D.Double(circle.getPosition().getX(), circle.getPosition().getY(), 10, 10);
                g2d.fill(circle2);
            }
            */

//            else
//            {
                g2d.setColor(entity.getColor());
                g2d.fill(shift(entity.getShape(), entity.getPosition()));

                g2d.setColor(Color.BLACK);
                g2d.draw(shift(entity.getShape(), entity.getPosition()));


            }

            //entity.rotate(45);
            //entity.setPosition(entity.getPosition().add(new Vector2D(   Math.random() * 10, Math.random() * 10)));



        }
    }

