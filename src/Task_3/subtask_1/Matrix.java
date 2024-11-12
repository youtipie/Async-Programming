package Task_3.subtask_1;

import java.util.Random;
import java.util.stream.IntStream;

public class Matrix {
    private static final int MAX_RANDOM_VALUE = 1000;

    int rows;
    int cols;

    int[][] matrix;

    public Matrix(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Значення колонок та рядків не можуть бути менше 1!");
        }
        this.rows = rows;
        this.cols = cols;
        generateMatrix();
    }

    public void generateMatrix() {
        Random random = new Random();
        matrix = IntStream.range(0, rows).mapToObj(i ->
                random.ints(cols, 0, MAX_RANDOM_VALUE).toArray()
        ).toArray(int[][]::new);
    }

    public void displayMatrix() {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.printf("%4d", elem);
            }
            System.out.println();
        }
    }

    public String toString() {
        return "[rows: " + rows + "; cols: " + cols + "]";
    }
}
