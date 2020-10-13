/*
Filename: Fraction.java
Author: Stephen Jones
Date: 02DEC2018
Purpose: Project 3 class that defines the fractions that are created by the main
class.  Includes methods that recieve the String input and convert to a numerical
value, override the compareTo() method from the Comparable class, and a check to 
make sure the fraction String is in an appropriate form.

References used to help create code:

    Maneas, S. (2014, February 22). Java Comparable example. Retrieved November 28, 2018, 
from https://examples.javacodegeeks.com/java-basics/java-comparable-example/
 */
package sortingprogrammain;

public class Fraction implements Comparable<Fraction>{
    //Variables
    private String fractionItem;
    private String numerator;
    private String denominator;
    
    //Constructor
    public Fraction(String fractionItem){
        this.fractionItem = fractionItem;
    }
    //Methods
    //Method that compares the fraction value
    @Override
    public int compareTo(Fraction anotherFraction) {
        if(this.fractionValue() == anotherFraction.fractionValue()){
            return 0;
        }else{
            return this.fractionValue() > anotherFraction.fractionValue() ? 1 :-1;
        } 
    }
    //Method that converts the fraction string to a double
    public double fractionValue(){
        //Seperates a single fraction into an array with numerator and denominator
        String fractionElements[] = fractionItem.split("/");
        //Check if the fraction has more than one slash
        if (fractionElements.length > 2){
            throw new NumberFormatException();
        }
        
        numerator = fractionElements[0];
        denominator = fractionElements[1];
        
        double aNum = Double.parseDouble(numerator);
        double aDen = Double.parseDouble(denominator);
        
        return aNum/aDen;
    }
    //Check the fraction's numerator and denominator are integers
    //Will throw a NumberFormatException if not
    public void checkForBadInput(){
        
        String checkElements[] = fractionItem.split(" |/" );
        
        for(int i = 0; i<checkElements.length;i++){
            Integer.parseInt(checkElements[i]);
        }
        
        String fractionElements[] = fractionItem.split("/");
        
        if (fractionElements.length != 2){
            throw new NumberFormatException();
        }
        
    }
    @Override
    public String toString(){
        return fractionItem;
    }
    
}
