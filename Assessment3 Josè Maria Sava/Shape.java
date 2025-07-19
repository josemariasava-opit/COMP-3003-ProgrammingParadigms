/*
Task: Design a shape hierarchy applying OOP design principles.
Instructions:
    Create an abstract class Shape with the abstract method calculateArea().
    Implement two subclasses: Circle and Rectangle.
    Each class must implement the calculateArea() method.
    Demonstrate usage in a main() method by:
    Creating Circle and Rectangle objects.
    Printing their areas.

Design Guidelines:
    SRP (Single Responsibility Principle): Each class does one thing well.
    OCP (Open/Closed Principle): You can add new shapes without modifying existing classes.
    DRY (Don't Repeat Yourself): Avoid redundant code.
    KISS (Keep It Simple, Stupid): Maintain straightforward and readable design.
 */

import java.lang.Math;

// Abstract class shape 
// Interface for all the other classes 
public abstract class Shape {
    // abstract method calculateArea()
    public abstract double calculateArea();
}

// Class Circle extends Shape 
class Circle extends Shape {
    // private attribute for radius to ensure encapsulation
    private double radius;
    // constructor 
    public Circle(double radius) {
        this.radius = radius;
    }

    // Override method from Shape 
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    // Circle implements only circle and calculate its own area
}

class Rectangle extends Shape {
    // private attribute for radius to ensure encapsulation
    private double height, width;
    // constructor 
    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    
    // Override method from Shape 
    @Override
    public double calculateArea() {
        return height * width;
    }
    // Rectangle implements only rectangle and calculate its own area 
}


