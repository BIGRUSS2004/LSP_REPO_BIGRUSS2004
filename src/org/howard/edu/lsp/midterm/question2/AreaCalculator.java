package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
    //Circle Area
    public static double area(double radius){
        validatePositive(radius);
        return Math.PI * radius * radius;
    }
    
    
}
