package core;

public class Square extends Rectangle{
    public Square(double mass, double side){
        super(mass, side, side);

    }
    public double getSide(){
        return getHeight();
    }

}
