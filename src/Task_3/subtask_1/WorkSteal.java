package Task_3.subtask_1;

import java.util.concurrent.*;

import static Task_3.subtask_1.Utils.FindMinInRows.findMinInRows;

public class WorkSteal extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 1;
    private final int[][] matrix;
    private final int startRow, endRow, targetValue;

    public WorkSteal(int[][] matrix, int startRow, int endRow, int targetValue) {
        this.matrix = matrix;
        this.startRow = startRow;
        this.endRow = endRow;
        this.targetValue = targetValue;
    }

    @Override
    protected Integer compute() {
        if (endRow - startRow <= THRESHOLD) {
            return findMinInRows(matrix, startRow, endRow, targetValue);
        } else {
            int mid = (startRow + endRow) / 2;
            WorkSteal leftTask = new WorkSteal(matrix, startRow, mid, targetValue);
            WorkSteal rightTask = new WorkSteal(matrix, mid, endRow, targetValue);

            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return Math.min(leftResult, rightResult);
        }
    }
}
