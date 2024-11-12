package Task_3.subtask_1;

import java.util.Random;

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
        matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM_VALUE);
            }
        }
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
        return "Matrix[rows: " + rows + "; cols: " + cols + "]";
    }
}
