package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
    //Circle Area
    public static double area(double radius){
        validatePositive(radius);
        return Math.PI * radius * radius;
    }

    //Rectangle Area
    public static double area(doluble,width, double height){
        validatePositive(width);
        validatePositive(height);
        return width * height;
    }
    //Triangle Area
    public static double area(int base, int height){
        validatePositive(base);
        validatePositive(height);
        return 0.5 * base * height;
    }
    
}
