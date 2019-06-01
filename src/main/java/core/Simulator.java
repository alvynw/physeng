package core;

import javafx.util.Pair;
import physics.Vector2D;
import shapes.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;

import static utils.Path2DUtils.shift;

class Simulator extends JPanel {

    private double time = 0;
    private int width;
    private int height;
    private double dt;
    private int counter = 0;

    List<Entity> entities;

    CollisionHandler detector = new CollisionHandler();


    public Simulator(int width, int height, List<Entity> entities, double dt) {

        this.width = width;
        this.height = height;
        this.entities = entities;
        this.dt = dt;

        int counter = 0;

        setSize(width, height);
        setVisible(true);

        Timer timer = new Timer(33, (ActionEvent actionEvent) -> {

            tick();

            repaint();
            //((Timer) actionEvent.getSource()).start();
        });

        timer.start();
        repaint();
    }

    public void tick() {

        List<Pair<Entity, Entity>> possibleCollisions = detector.sortAndSweep(entities);
        for (Pair<Entity, Entity> pair : possibleCollisions) {
            Vector2D MVT = detector.SAT(pair.getKey(), pair.getValue());

            System.out.println(MVT);

            if (MVT == null) {
                System.out.println("no collision");
                continue;
            } else {
                detector.resolveCollisions(pair.getKey(), pair.getValue(), MVT);
            }
        }


        for (Entity e : entities) {
            e.tick(dt, time);
        }

        time += dt;
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

