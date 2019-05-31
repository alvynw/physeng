package core;

import javax.swing.*;
import java.util.ArrayList;

public class Environment {

    ArrayList<Entity> entities = new ArrayList<>();
    double time = 0;
    double dt;

    int height = 1000;
    int width = 1000;

    public Environment() {
        this.dt = 0.05;
    }

    public Environment(double dt) {
        this.dt = dt;
    }
    public Environment(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public Environment(double dt, int height, int width) {
        this.dt = dt;
        this.height = height;
        this.width = width;
    }


    public void add(Entity entity) {
        entities.add(entity);
    }

    public void simulate() {
        Simulator s = new Simulator(width, height, entities);
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s);
        //frame.pack();
        frame.setVisible(true);
    }

}
