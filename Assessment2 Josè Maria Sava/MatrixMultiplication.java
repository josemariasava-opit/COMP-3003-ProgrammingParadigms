/* 
* Write a Java program to perform a computationally intensive task (choose either):
* Matrix multiplication
* Sorting a large array
* Tasks:
* Implement the task using multiple threads.
* Also implement a single-threaded version for comparison.
* Measure and display the execution time for both approaches.

Sava Josè Maria 
 */
import java.util.Arrays;
import java.util.Random;

public class MatrixMultiplication {
    
    /* Dimension of matrix rows and cols */
    private static final int MATRIX_SIZE = 1200;
    /* Number of threads */
    private static final int NUM_THREADS = 4;
    
    /* main method */
    public static void main(String[] args) {
        int[][] matrixA = generateMatrix(MATRIX_SIZE, MATRIX_SIZE); /* matrix A filled with random numbers  */
        int[][] matrixB = generateMatrix(MATRIX_SIZE, MATRIX_SIZE); /* matrix B filled with random numbers  */
        /* results matrix */
        int[][] resultMulti = new int[MATRIX_SIZE][MATRIX_SIZE]; 
        int[][] resultSingle = new int[MATRIX_SIZE][MATRIX_SIZE];

        /* Multi-thread execution */ 
        long startMulti = System.currentTimeMillis(); /* Record start time (ms) */
        multiplyMulti(matrixA, matrixB, resultMulti); /* Perform matrix multiplication using multiple threads */
        long endMulti = System.currentTimeMillis(); /* Record end time (ms) */

        /*Single-thread execution */
        long startSingle = System.currentTimeMillis(); /* Record start time (ms) */
        multiplySingle(matrixA, matrixB, resultSingle); /* Perform matrix multiplication using single thread */
        long endSingle = System.currentTimeMillis(); /* Record start time (ms) */

        /* Output and compare results */
        System.out.println("\nMulti-thread (" + NUM_THREADS + " threads) time: " + (endMulti - startMulti) + " ms");
        System.out.println("Single-thread time: " + (endSingle - startSingle) + " ms\n");

        /* checks if both methods produced the same matrix output */
        boolean isEqual = Arrays.deepEquals(resultMulti, resultSingle);
        System.err.println("Are the results between single and multi thread the same?  " + isEqual + "\n");
    }
    
    /*
    * Function name     :   generateMatrix
    * Arguments         :   int rows = number of rows in the matrix
    *                       int cols = number of columns in the matrix
    * Return value/s    :   int[][]  = filled matrix 
    * Remarks           :   Creates and returns a matrix of the size[rows][columns] filled with random number in range 0-500
    */
    private static int[][] generateMatrix(int rows, int columns) {
        /* Instance of random class */
        Random rand = new Random();
        /* matrix declaration  */
        int[][] generatedMatrix = new int[rows][columns];

        /* Iterate the matrix [rows][columns] */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                /* Populate matrix[rows][columns] with a random number in range 0-500 */
                generatedMatrix[i][j] = rand.nextInt(500);
            } /* end of inner loop */
        } /* end of outer loop */
        return generatedMatrix;
    }
    
    /*
    * Function name     :   multiplyMulti
    * Arguments         :   int[][] A       = first  matrix
    *                       int[][] B       = second  matrix
    *                       int[][] result  = matrix to store the result
    * Return value/s    :   void
    * Remarks           :   Divides the matrix multiplication task among multiple threads, each handle a subset of rows.
    *                       Waits for all threads to finish (thread.join())
    *                       split the computation across multiple threads
    */
    private static void multiplyMulti(int[][] A, int[][] B, int[][] result) {
        /* Array of threads */
        Thread[] threads = new Thread[NUM_THREADS];
        /* how many rows the thread has to process   */
        int rowsPerThread = MATRIX_SIZE / NUM_THREADS;

        /* loop to create and start each thread */
        for (int i = 0; i < NUM_THREADS; i++) {
            /* Starting row index for the thread with index i */
            int startRow = i * rowsPerThread;
            /*Ending row index for the thread with index i */
            int endRow = (i == NUM_THREADS - 1) ? MATRIX_SIZE - 1 : startRow + rowsPerThread - 1;

            /*Create a new thread that runs a MultiplicationWorker 
             * on a portion of the matrix within the range startRow-endRow
            */
            threads[i] = new Thread(new MultiplicationWorker(A, B, result, startRow, endRow));
            threads[i].start();
        }

        /* Wait for all threads to finish before continuing */
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                /* If the thread is interrupted while waiting, notify the error */
                e.printStackTrace();
            }
        }
    }
    
    /*
    * Function name     :   multiplySingle
    * Arguments         :   int[][] A       = first  matrix
    *                       int[][] B       = second  matrix
    *                       int[][] result  = matrix to store the result
    * Return value/s    :   void
    * Remarks           :   Performs the matrix multiplication using a single thread
    */
    private static void multiplySingle(int[][] A, int[][] B, int[][] result) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                for (int k = 0; k < MATRIX_SIZE; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    /*
    * Class name        :   MultiplicationWorker
    * Arguments         :   int[][] A       = first  matrix
    *                       int[][] B       = second  matrix
    *                       int[][] result  = matrix to store the result
    *                       int startRow    = starting row index for this task
    *                       int endRow      = ending row index for this task
    * Return value/s    :   (implements Runnable)
    * Remarks           :   Runnable task that multiplies a subset of rows in the result matrix.
    */
    static class MultiplicationWorker implements Runnable {
        private final int[][] A;
        private final int[][] B;
        private final int[][] result;
        private final int startRow;
        private final int endRow;

        /* Constructor to init the matrix and the range of rows */
        public MultiplicationWorker(int[][] A, int[][] B, int[][] result, int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        /* What this thread will do */
        @Override
        public void run() {
            for (int i = startRow; i <= endRow; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    for (int k = 0; k < MATRIX_SIZE; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                    } /* end of inner loop (multiply) */
                }/* end of middle loop (columns)*/
            }/* end of outer loop (rangle of rows) */
        }
    }
}
