package core;

import javax.swing.*;
import java.util.ArrayList;

/**
 * An <code>Environment</code> to place {@link Entity}s
 */
public class Environment {

    ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Adds an {@link Entity} this this <code>Environment</code>
     * @param entity the {@link Entity} to add
     */
    public void add(Entity entity) {
        entities.add(entity);
    }

    /**
     * Runs a simulation of the <code>Environment</code>. The simulation appears on a {@link JPanel}
     * @param width the width of the <code>Environment</code> {@link JPanel}
     * @param height the height of the <code>Environment</code> {@link JPanel}
     * @param dt the time step to use
     */
    public void simulate(int width, int height, double dt) {
        Simulator s = new Simulator(width, height, entities, dt);
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s);
        //frame.pack();
        frame.setVisible(true);
    }

}
