package Task_3.subtask_1;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

import static Task_3.subtask_1.Utils.FindMinInRows.findMinInRows;

public class WorkDeal {
    public static int findMinWithWorkDealing(ExecutorService executorService, int[][] matrix, int targetValue) {
        int rowsPerTask = (matrix.length + Runtime.getRuntime().availableProcessors() - 1) /
                Runtime.getRuntime().availableProcessors();

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < matrix.length; i += rowsPerTask) {
            int startRow = i;
            int endRow = Math.min(i + rowsPerTask, matrix.length);
            futures.add(executorService.submit(() -> findMinInRows(matrix, startRow, endRow, targetValue)));
        }

        int globalMin = Integer.MAX_VALUE;
        for (Future<Integer> future : futures) {
            try {
                globalMin = Math.min(globalMin, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return globalMin;
    }
}
