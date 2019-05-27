package core;

import physics.Vector2D;
import utils.Path2DUtils;

import java.awt.geom.Path2D;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ellipse extends Entity{
        private double width, height;
        public Ellipse(double mass, double width, double  height){
            super(mass, createPath(width, height));
            this.width = width;
            this.height = height;

            //  super(mass, path);

        }
        private static Path2D createPath(double width, double height){
            double halfY = height/2;
            double halfX = width/2;
            int counter = 0;
            ArrayList<Vector2D> coords = new ArrayList<>();
            double radius = Math.max(width,height)/2;
            if(radius == halfY) {
                for (double y = -1 * radius; y <= radius; y += .01) {
                    coords.add(new Vector2D(Math.sqrt(Math.abs(halfX*halfX*(1-y*y/halfY/halfY))), y));
                    //System.out.println(coords.get(counter));
                    counter++;
                }
                for(int i = counter-1; i >=0; i--){
                    coords.add(new Vector2D(coords.get(i).getX()*-1, coords.get(i).getY()));
                   System.out.println(coords.get(i).getX()*coords.get(i).getX()/(halfX*halfX)+
                            coords.get(i).getY()*coords.get(i).getY()/(halfY*halfY));
                }

            }
            else{
                for (double x = -1 * radius; x <= radius; x += .01) {
                    coords.add(new Vector2D(x,Math.sqrt(halfY*halfY*Math.abs(1-x*x/(halfX*halfX)))));
                    counter++;
                }
                for(int i = counter-1; i >=0; i--){
                    coords.add(new Vector2D(coords.get(i).getX(), coords.get(i).getY()*-1));
                   System.out.println(coords.get(i).getX()*coords.get(i).getX()/(halfX*halfX)+
                           coords.get(i).getY()*coords.get(i).getY()/(halfY*halfY));
                }
            }

            return Path2DUtils.generatePath(coords.toArray(new Vector2D[counter]));
        }
        public static void main (String args[]){
            Ellipse test = new Ellipse(10, 8, 4);
        }
}
