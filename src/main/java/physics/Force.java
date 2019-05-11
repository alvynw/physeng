package physics;

import core.*;

import java.util.ArrayList;

public class Force extends Vector2D {
    //Vector2D location;
    String functionX;
    String functionY;
    ArrayList<String> allCoeffsX;
    ArrayList<Character> signsBetweenY;
    ArrayList<String> allCoeffsY;
    ArrayList<String> signsBetweenX;

    public Force(double x, double y) {
        super(x, y);
        //location = loc;
    }
    public static void main(String args[]){
        Force forc = new Force("1/2x + 789y");
        //System.out.println(forc.allCoeffs);
        //System.out.println(forc.signsBetween);
    }

    public Force(String funcX, String funcY) {
        functionX = funcX;
        functionY = funcY;
        allCoeffsX = new ArrayList<>();
        signsBetweenX = new ArrayList<>();
        allCoeffsY = new ArrayList<>();
        signsBetweenY = new ArrayList<>();
        parseEquation();
    }

    public void actOn(Entity entity, double time) {

        entity.setAcceleration(new Vector2D(entity.getAcceleration().getX() + getX() / entity.getMass(), entity.getAcceleration().getX() + getY() / entity.getMass()));

    }

    //GUI that restricts only to numbers || prompts user(System.in) || restrict to certain order polynomial || follow guildlines of certain string
    public void update(double time) {
        double newX;
        double newY;
        for (int i = 0; i < allCoeffsX.size(); i++){

            if (allCoeffsX.get(i).contains("x")) {

            }
            else if (allCoeffsX.get(i).contains("y")){

    }
    else if(allCoeffsX.get(i).contains("t")){

            }
            else if(allCoeffsX.get(i).contains("v")){

            }
            else if(allCoeffsX.get(i).contains("a")){

            }

    }

    public void parseEquation() {
        int oldIndex = 0;
        for(int i = 0; i < functionX.length();i++){
            if(Character.isWhitespace(functionX.charAt(i))){
                allCoeffsX.add(functionX.substring(oldIndex, i));
                oldIndex = i+3;
                signsBetweenX.add(functionX.charAt(i+1));
                i = i+3;

            }
            else if(i == functionX.length()-1){
                allCoeffsX.add(functionX.substring(oldIndex, i+1));
                //oldIndex = i+3;
                //signsBetween.add(function.charAt(i+1));
                //i = i+3;
            }
        }
        oldIndex = 0;
        for(int i = 0; i < functionY.length();i++){
            if(Character.isWhitespace(functionY.charAt(i))){
                allCoeffsY.add(functionY.substring(oldIndex, i));
                oldIndex = i+3;
                signsBetweenY.add(functionY.charAt(i+1));
                i = i+3;

            }
            else if(i == functionY.length()-1){
                allCoeffsY.add(functionY.substring(oldIndex, i+1));
            }
        }

//        double temp;
//        double exponent;
//        //boolean reset;
//        for (int i = 0; i < function.length(); i++) {
//            char current = function.charAt(i);
//            if (Character.isDigit(function.charAt(i))) {
//                StringBuffer tempCoeff = new StringBuffer();
//                tempCoeff.append(function.charAt(i));
//                for (i = i; Character.isDigit(function.charAt(i)); i++) {
//                    tempCoeff.append(function.charAt(i));
//                }
//                temp = Double.parseDouble(tempCoeff.toString());
//            } else if (isAdd(current)){
//
//            }
//
//
//        }
    }

    public boolean isAdd(char c) {
        return c == '+';
    }
    public boolean isMult(char c) {
        return c == '*';
    }
    public boolean isDiv(char c) {
        return c == '/';
    }
    public boolean isMinus(char c) {
        return c == '-';
    }
    public boolean isExponent(char c){
        return c == '^';
    }

}