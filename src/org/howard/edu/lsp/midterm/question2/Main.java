package org.howard.edu.lsp.midterm.question2;

public class Main {
  public static void main(String[] args) {
    // Required exact outputs
    System.out.println("Circle radius 3.0 \u2192 area = " + AreaCalculator.area(3.0));
    System.out.println("Rectangle 5.0 x 2.0 \u2192 area = " + AreaCalculator.area(5.0, 2.0));
    System.out.println("Triangle base 10, height 6 \u2192 area = " + AreaCalculator.area(10, 6));
    System.out.println("Square side 4 \u2192 area = " + AreaCalculator.area(4));

    // Exception demo
    try {
      AreaCalculator.area(0.0);
      System.out.println("ERROR: exception not thrown");
    } catch (IllegalArgumentException e) {
      System.out.println("Caught expected error: " + e.getMessage());
    }
  }

  /*
   Overloading vs separate names:
   Overloading keeps one concept name, area, while parameter lists distinguish shapes.
   This improves API clarity and discoverability versus many differently named methods.
  */
}
