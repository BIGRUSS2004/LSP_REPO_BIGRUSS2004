package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
    //Circle Area
    public static double area(double radius){
        validatePositive(radius);
        return Math.PI * radius * radius;
    }

    //Rectangle Area
    public static double area(double width, double height){
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
    // Square Area
    public static double area(int side){
        validatePositive(side);
        return side * side;
    }
    private static void validatePositive(double v) {
        if (v <= 0) throw new IllegalArgumentException("Dimensions must be > 0.");
    }
    private static void validatePositive(int v) {
        if (v <= 0) throw new IllegalArgumentException("Dimensions must be > 0.");
    }
}
