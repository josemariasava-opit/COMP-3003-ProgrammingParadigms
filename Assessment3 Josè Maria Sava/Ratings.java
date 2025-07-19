/*
 * Task: Work with one-dimensional and two-dimensional arrays to compute average ratings of programming languages.
Instructions:
    Create a one-dimensional array of 5 programming language names.
    Create a 2D array (5×3) to store ratings (0–10) for these languages from 3 users.
    Write a method to calculate and print the average rating for each language.
 */
public class Ratings {

    public static void main(String[] args) {

        /* One dimensional array ( String type ) for programming languages */
        String[] progLang = { "Python", "Java", "C++", "Javascript", "Ruby" };

        /* 2D array for ratings (5 rows for languages and 3 column for 3 users) ( Double type ) */
        double[][] ratingsStorage = {
                { 10.0, 6.5, 4.2 }, // Python 
                { 9.8, 5.7, 9.0 }, // Java
                { 8.5, 10.0, 9.5 }, // C++  
                { 6.5, 7.5, 8.0 }, // Javascript
                { 6.0, 6.0, 6.5 }, // Ruby
        };

        /* Calling method to calculate average rating */
        calculateAverageRating(progLang, ratingsStorage);
    }
    
    /* Method to calculate and print the average rating for each language */
    public static void calculateAverageRating(String[] languages, double[][] ratings ) {
        
        /* Outer loops for  programming languages */
        for (int i = 0; i < languages.length; i++) {
            /* init sum variable */
            double sum = 0;
            /* Inner loop for ratings */
            for (int j = 0; j < ratings[i].length; j++) {
                /* Sum ratings of same programming languages */
                sum += ratings[i][j];
            }
            /* Init average */
            double average = sum / ratings[i].length;
            /* Notify user and print out reults for programming language of i */
            System.out.printf("Average rating for %s: %.2f \n", languages[i], average);
        } // end of inner loop         
    }// end of outer loop     
}
