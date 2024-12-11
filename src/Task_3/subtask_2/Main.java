package Task_3.subtask_2;

import Task_3.Utils.CalcTime;
import Task_3.Utils.SimulateDelay;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        double[] sequence = new double[20];
        Random rand = new Random();
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = rand.nextDouble() * 20;
        }

        System.out.println("Initial Sequence of Real Numbers:");
        System.out.println(Arrays.toString(sequence));

        CompletableFuture<Double> sumFuture = CompletableFuture.supplyAsync(() -> {
            CalcTime.startTracking();
            double sum = 0;
            for (double num : sequence) {
                sum += num;
                SimulateDelay.sleep(10);
            }
            CalcTime.printElapsedTime("Calculating sum");
            return sum;
        });

        CompletableFuture<Double> averageFuture = sumFuture.thenApplyAsync(sum -> {
            CalcTime.startTracking();
            double average = sum / sequence.length;
            SimulateDelay.sleep(500);
            CalcTime.printElapsedTime("Calculating average");
            return average;
        });

        sumFuture.thenAcceptAsync(sum -> {
            System.out.println("Sum of the sequence: " + sum);
        });

        averageFuture.thenAcceptAsync(average -> {
            System.out.println("Average of the sequence: " + average);
        });

        CompletableFuture.allOf(sumFuture, averageFuture).join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Total execution took " + elapsed + " ms");
    }
}
