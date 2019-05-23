package core;

import shapes.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
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

        Path2D path = new Path2D.Double();

        path.moveTo(0, 0);
        path.lineTo(100, 0);
        path.lineTo(100, 200);
        path.lineTo(50, 100);
        path.lineTo(0, 200);
        path.closePath();

        entities.add(new Entity(10, path));

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

    public void rotation() {
        //use transform method from Path2D
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        g2d.draw(new Ellipse2D.Double(100, 100, 250, 250));

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            if (entity instanceof Circle) {
                Circle circle = (Circle) entity;
                g2d.draw(new Ellipse2D.Double(circle.getPosition().getX(), circle.getPosition().getY(), 250, 250));
            }

            /*if(entity instanceof Rectangle)
            {
                Rectangle rect = (Rectangle) entity;
                g2d.draw(new Rectangle2D.Double(entity.getPosition().getX(), 50.5, 200, 200));
            }*/

            else {
                g2d.draw(entity.getShape());

            }

            /*List<Point2D> segList=new ArrayList<Point2D>();
            Shape eShape = entity.getShape();
            PathIterator pShape = eShape.getPathIterator(null);

            while (!pShape.isDone())
            {
                final double[] seg = new double[6];

                pShape.currentSegment(seg);
                segList.add(new Point2D.Double(seg[0], seg[1]));
                pShape.next();
            }

            for(int j = 0; i < segList.size(); j++)
            {


            }*/


            // g2d.draw(new Rectangle2D.Double(entity.getPosition().getX(), 50.5, 200, 200));
            //  g2d.draw(new Arc2D.Double(entity.getPosition().getX(), entity.getPosition().getY(),
            //  50.5, 50.5, 20.0, 20.0, 20));


            //if() sees what shape the entity is. if circle,
            // g2d.draw(entity.getPosition().getX(), 50, 50, 50);
            //use pathIterator

        }
    }
}
