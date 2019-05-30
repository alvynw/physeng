package core;

/**
 * An {@link Entity} with a bounding shape of a square
 */
public class Square extends Rectangle{

    /**
     * Creates a <code>Square</code> with the specified mass and side length
     * @param mass mass of the <code>Square</code>
     * @param sideLength sideLength of the <code>Square</code>
     */
    public Square(double mass, double sideLength){
        super(mass, sideLength, sideLength);
    }

    /**
     * access the side length of the square
     * @return height of the rectangle
     */
    public double getSide(){
        return getHeight();
    }

}
