package Task_3.subtask_2;

import Task_3.Utils.CalcTime;
import Task_3.Utils.SimulateDelay;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        CompletableFuture<List<Double>> randomNumbersFuture = CompletableFuture.supplyAsync(() -> {
            CalcTime.startTracking();
            Random random = new Random();
            List<Double> numbers = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                numbers.add(random.nextDouble() * 100);
                SimulateDelay.sleep(10);
            }
            CalcTime.printElapsedTime("Generating random numbers");
            return numbers;
        });

        CompletableFuture<List<Double>> differencesFuture = randomNumbersFuture.thenApplyAsync(numbers -> {
            CalcTime.startTracking();
            List<Double> differences = new ArrayList<>();
            for (int i = 0; i < numbers.size() - 1; i++) {
                differences.add(Math.abs(numbers.get(i) - numbers.get(i + 1)));
                SimulateDelay.sleep(10);
            }
            CalcTime.printElapsedTime("Computing absolute differences");
            return differences;
        });

        CompletableFuture<Double> maxDifferenceFuture = differencesFuture.thenApplyAsync(differences -> {
            CalcTime.startTracking();
            double maxDifference = 0.0;
            for (double difference : differences) {
                if (difference > maxDifference) {
                    maxDifference = difference;
                    SimulateDelay.sleep(10);
                }
            }
            CalcTime.printElapsedTime("Finding maximum difference");
            return maxDifference;
        });

        CompletableFuture<Void> printResultsFuture = randomNumbersFuture.thenAcceptAsync(numbers -> {
            System.out.println("Generated numbers: " + Arrays.toString(numbers.toArray()));
        }).thenRunAsync(() -> {
            System.out.println("Random numbers printed successfully.");
        });

        CompletableFuture<Void> printMaxDifferenceFuture = maxDifferenceFuture.thenAcceptAsync(maxDifference -> {
            System.out.println("Maximum absolute difference: " + maxDifference);
        });

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(printResultsFuture, printMaxDifferenceFuture);
        allTasks.join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Total execution took " + elapsed + " ms");
    }
}
