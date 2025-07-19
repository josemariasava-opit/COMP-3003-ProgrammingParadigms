/*
Task: Demonstrate core OOP principles by creating an abstract Animal class and subclasses Dog and Cat.
Instructions:
1. Create an abstract class Animal with:
    Private attributes name and age
    Public getter and setter methods
    An abstract method makeSound()
2. Create subclasses Dog and Cat :
    Implement the makeSound() method to print the respective animal sounds.
3. In main() :
    Create instances of Dog and Cat
    Set their names and ages using setters
    Call makeSound() to show polymorphic behavior
 */

public abstract class Animal {
    /* Attributes for class Animal */
    private String name;
    private int age;

    /* Getters  */
    public String getName() {
        return name; // return name of animal 
    }

    public int getAge() {
        return age; // return age of animal 
    }

    /* Setters */
    public void setName(String name) {
        this.name = name; // set the name of animal 
    }

    public void setAge(int age) {
        this.age = age; // set the age of animal 
    }

    // abstract method makeSound()
    public abstract void makeSound();
}

// Class dog extends Animal 
class Dog extends Animal {
    // Override abstarct method
    @Override
    public void makeSound() {
        System.out.println("Dog named " + getName() + " says: Woof!");
    }
}

// Class cat extends Animal 
class Cat extends Animal {
    // Override abstract method 
    @Override
    public void makeSound() {
        System.out.println("Cat named: " + getName() + " says: Meow! ");
    }
}
