package core;

/**
 * Creates a square of specified side length
 */
public class Square extends Rectangle{
    /**
     * constructs a square
     * @param mass of entity
     * @param side length of square
     */
    public Square(double mass, double side){
        super(mass, side, side);

    }

    /**
     * access the side length of the square
     * @return height of the rectangle
     */
    public double getSide(){
        return getHeight();
    }

}
