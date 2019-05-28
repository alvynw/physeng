package core;

import physics.Vector2D;
import utils.Path2DUtils;

import java.awt.geom.Path2D;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ellipse extends Entity {
    private double width, height;

    public Ellipse(double mass, double width, double height) {
        super(mass, createPath(width, height));
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private static Path2D createPath(double width, double height) {
        double halfY = height / 2;
        double halfX = width / 2;
        int counter = 0;
        ArrayList<Vector2D> coords = new ArrayList<>();
        for (double y = -1 * halfY; y <= halfY; y += height / 100) {
            double x = Math.sqrt(Math.abs(halfX * halfX * (1 - y * y / halfY / halfY)));
            coords.add(new Vector2D(x, y));
            coords.add(new Vector2D(-1 * x, y));
            counter += 2;
        }
        return Path2DUtils.generatePath(coords.toArray(new Vector2D[counter]));
    }
}
