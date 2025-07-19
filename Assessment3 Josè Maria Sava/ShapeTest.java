/* 
 * Testing class for Shape Class 
 */
public class ShapeTest {

    public static void main(String[] args) {
        
        Circle circle = new Circle(3.36); // Shape reference but Circle object 
        Rectangle rectangle = new Rectangle(4, 30); // Shape reference but Rectangle object 
        
        System.out.printf("Area of circle: %.1f \n", circle.calculateArea() );
        System.out.printf("Area of rectangle: %.1f \n", rectangle.calculateArea());
    }
    
}
