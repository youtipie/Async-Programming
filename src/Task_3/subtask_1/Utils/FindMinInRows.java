package Task_3.subtask_1.Utils;

public class FindMinInRows {
    public static int findMinInRows(int[][] matrix, int startRow, int endRow, int targetValue) {
        int min = Integer.MAX_VALUE;
        for (int i = startRow; i < endRow; i++) {
            for (int val : matrix[i]) {
                if (val >= targetValue && val < min) {
                    min = val;
                }
            }
        }
        return min;
    }
}
