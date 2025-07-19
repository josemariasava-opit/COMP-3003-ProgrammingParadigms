/*
Question 3: Parallel Code Techniques
Modify the program from "Question 2 Implementing Parallel Code in Java" to use Java's ForkJoinPool and parallel streams. Analyze the performance improvements and discuss the benefits of using these techniques.

Take the solution from Question 2 and improve it using:

ForkJoinPool
Java 8+ parallelStream()
Tasks:

Modify your code to use ForkJoinPool (for matrix multiplication or sorting).
Analyze the performance improvements and discuss the benefits of using these techniques.
Java Tip: Use RecursiveTask for custom solutions.

Sava Josè Maria 
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelMatrixMultiplication {
    /* Dimension of matrix rows and cols */
    private static final int SIZE = 1024;
    /* Minimum size of a matrix block to allow recursive splitting */
    private static final int THRESHOLD = 64;
    /* Java thread pool to manage parallel tasks using divide-and-conquer recursion */
    private static final ForkJoinPool pool = new ForkJoinPool();

    public static void main(String[] args) {
        int[][] a = generateMatrix(SIZE); /* matrix A filled with random numbers  */
        int[][] b = generateMatrix(SIZE); /* matrix B filled with random numbers  */
        /* results matrix */
        int[][] resultForkJoin = new int[SIZE][SIZE];
        int[][] resultParallelStream = new int[SIZE][SIZE];
        int[][] resultSingle = new int[SIZE][SIZE];

        /*  ForkJoin calling */
        long start = System.currentTimeMillis();
        pool.invoke(new ForkJoinMultiply(a, b, resultForkJoin, 0, SIZE, 0, SIZE));
        long forkJoinTime = System.currentTimeMillis() - start;

        /* Parallel Stream calling */
        start = System.currentTimeMillis();
        parallelStreamMultiply(a, b, resultParallelStream);
        long parallelStreamTime = System.currentTimeMillis() - start;

        /*Single-thread execution */
        start = System.currentTimeMillis();
        multiplySingle(a, b, resultSingle);
        long singleThreadTime = System.currentTimeMillis() - start;

        /* Output and compare results */
        System.out.println("\nForkJoinPool: " + forkJoinTime + " ms \n");
        System.out.println("Parallel Stream: " + parallelStreamTime + " ms \n");
        System.out.println("Single Thread: " + singleThreadTime + " ms \n");
    }

    /*
    * Function name     :   ForkJoinMultiply 
    * Arguments         :   int[][] a       = first  matrix
    *                       int[][] b       = second  matrix
    *                       int[][] result  = matrix to store the result
    *                       int rowStart    = starting row index (inclusive)
    *                       int rowEnd      = ending row index (exclusive)
    *                       int colStart    = starting column index (inclusive)
    *                       int colEnd      = ending column index (exclusive)
    * Return value/s    :   
    * Remarks           :   Initializes a ForkJoinMultiply task for multiplying a block of the result matrix.
    */
    static class ForkJoinMultiply extends RecursiveTask<Void> {
        private final int[][] a, b, result;
        private final int rowStart, rowEnd, colStart, colEnd;

        public ForkJoinMultiply(int[][] a, int[][] b, int[][] result, 
                              int rowStart, int rowEnd, int colStart, int colEnd) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.rowStart = rowStart;
            this.rowEnd = rowEnd;
            this.colStart = colStart;
            this.colEnd = colEnd;
        }
        
        /*
        * Function name     :   compute
        * Arguments         :   (overrides RecursiveTask<Void>)
        * Return value/s    :   Void 
        * Remarks           :   If the block is small enough, computes the result sequentially; otherwise, splits the block into
        *                       four sub-tasks and processes them in parallel using ForkJoinPool.
        */
        @Override
        protected Void compute() {
            int size = rowEnd - rowStart;
            if (size <= THRESHOLD) {
                for (int i = rowStart; i < rowEnd; i++) {
                    for (int j = colStart; j < colEnd; j++) {
                        int sum = 0;
                        for (int k = 0; k < SIZE; k++) {
                            sum += a[i][k] * b[k][j];
                        }
                        result[i][j] = sum;
                    }
                }
            } else {
                int mid = size / 2;
                invokeAll(
                    new ForkJoinMultiply(a, b, result, rowStart, rowStart + mid, colStart, colStart + mid),
                    new ForkJoinMultiply(a, b, result, rowStart, rowStart + mid, colStart + mid, colEnd),
                    new ForkJoinMultiply(a, b, result, rowStart + mid, rowEnd, colStart, colStart + mid),
                    new ForkJoinMultiply(a, b, result, rowStart + mid, rowEnd, colStart + mid, colEnd)
                );
            }
            return null;
        }
    }

    /*
    * Function name     :   parallelStreamMultiply
    * Arguments         :   int[][] a       = first  matrix
    *                       int[][] b       = second  matrix
    *                       int[][] result  = matrix to store the result
    * Return value/s    :   void
    * Remarks           :   Performs matrix multiplication using Java 8 parallel streams for parallelism.
    */
    private static void parallelStreamMultiply(int[][] a, int[][] b, int[][] result) {
        IntStream.range(0, SIZE).parallel().forEach(i -> {
            for (int j = 0; j < SIZE; j++) {
                final int row = i;
                final int col = j;
                result[row][col] = IntStream.range(0, SIZE)
                        .parallel()
                        .map(k -> a[row][k] * b[k][col])
                        .sum();
            }
        });
    }

    /*
    * Function name     :   multiplySingle
    * Arguments         :   int[][] a       = first  matrix
    *                       int[][] b       = second  matrix
    *                       int[][] result  = matrix to store the result
    * Return value/s    :   void
    * Remarks           :   Performs the matrix multiplication using a single thread
    */
    private static void multiplySingle(int[][] a, int[][] b, int[][] result) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int sum = 0;
                for (int k = 0; k < SIZE; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
    }

    /*
    * Function name     :   generateMatrix
    * Arguments         :   int rows = number of rows in the matrix
    *                       int cols = number of columns in the matrix
    * Return value/s    :   int[][]  = filled matrix 
    * Remarks           :   Creates and returns a matrix of the size[rows][columns] filled with random number in range 0-500
    */
    private static int[][] generateMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }
}
