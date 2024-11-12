package Task_3.subtask_1;


import Task_3.subtask_1.Utils.UserMatrix;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = UserMatrix.getUserMatrix();
        matrix.displayMatrix();

        int firstElement = matrix.matrix[0][0];
        int targetValue = firstElement * 2;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long startTimeStealing = System.currentTimeMillis();
        int resultStealing = forkJoinPool.invoke(new WorkSteal(matrix.matrix, 0, matrix.matrix.length, targetValue));
        long endTimeStealing = System.currentTimeMillis();

        System.out.println("Намагаюсь найти " + targetValue + ":");

        if (resultStealing == Integer.MAX_VALUE) {
            System.out.println("Work Stealing нічого не найшов");
        } else {
            System.out.println("Work Stealing результат: " + resultStealing);
        }
        System.out.println("Work Stealing час виконання: " + (endTimeStealing - startTimeStealing) + " ms");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long startTimeDealing = System.currentTimeMillis();
        int resultDealing = WorkDeal.findMinWithWorkDealing(executorService, matrix.matrix, targetValue);
        long endTimeDealing = System.currentTimeMillis();

        if (resultStealing == Integer.MAX_VALUE) {
            System.out.println("Work Dealing нічого не найшов");
        } else {
            System.out.println("Work Dealing результат: " + resultDealing);
        }
        System.out.println("Work Dealing час виконання: " + (endTimeDealing - startTimeDealing) + " ms");

        executorService.shutdown();
    }
}
