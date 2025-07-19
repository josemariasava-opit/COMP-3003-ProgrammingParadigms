/* 
 * Testing class for Animal Class 
 */
public class AnimalTest {
    
    public static void main(String[] args) {
        Dog pippo = new Dog(); // Animal reference but Dog object 
        Cat silvestro = new Cat(); // Animal reference but Cat object 

        // Set name and age for each animal 
        pippo.setName("Pippo");
        pippo.setAge(12);

        silvestro.setName("Silvestro");
        silvestro.setAge(2);

        // call makesound for cat and dog object 
        pippo.makeSound();
        silvestro.makeSound();
    }
}
