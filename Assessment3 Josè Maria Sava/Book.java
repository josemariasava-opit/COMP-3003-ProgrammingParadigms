/* Question 1 
    Task: Create a class named usage.
    Book with specific attributes and methods. Demonstrate object creation and method
    Instructions:
    Define the class Book with the following attributes:
        title (String)
        author (String)
        isbn (String)
        price (double)
Create a constructor that initializes all attributes.
Implement a method displayDetails() that prints all book details in a clear format.
In the main method, instantiate a Book object and call displayDetails().
*/

// Class book 
public class Book {

    /* Encapsulate attribute */
    private String title;
    private String author;
    private String isbn;
    private double price;
    
    /* Constructor  to init attributes */
    public Book(String title, String author, String isbn, double price) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    /* Implementing display details method
     * With this method we are gonna display the private attribute  
    */
    public void displayDetails() {
        System.err.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Price: $" + String.format("%.2f", price));
    }

    /* Main method to test the class*/
    public static void main(String[] args) {
        /* Create a new book and init attributes */
        Book bookOne = new Book("Programming Paradigms", "Josè Maria Sava", "123980321", 90.55);

        /* calling methods displayDetails() */
        bookOne.displayDetails();
    
    }
}
