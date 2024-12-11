package Task_3.subtask_1;

import Task_3.Utils.CalcTime;
import Task_3.Utils.SimulateDelay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static Task_3.subtask_1.Utils.RandomChar.generateRandomChar;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        CompletableFuture<Character[]> createArrayFuture = CompletableFuture.supplyAsync(() -> {
            CalcTime.startTracking();
            Character[] array = new Character[20];
            for (int i = 0; i < array.length; i++) {
                SimulateDelay.sleep(10);
                array[i] = generateRandomChar();
            }
            System.out.println("Initial array: " + Arrays.toString(array));
            CalcTime.printElapsedTime("Array creation");
            return array;
        });

        CompletableFuture<Void> processArrayFuture = createArrayFuture.thenApplyAsync(array -> {
            CalcTime.startTracking();
            List<Character> letters = new ArrayList<>();
            List<Character> whitespaces = new ArrayList<>();
            List<Character> others = new ArrayList<>();

            for (char ch : array) {
                if (Character.isLetter(ch)) {
                    letters.add(ch);
                } else if (Character.isWhitespace(ch)) {
                    whitespaces.add(ch);
                } else {
                    others.add(ch);
                }
                SimulateDelay.sleep(10);
            }

            CalcTime.printElapsedTime("Processing array");
            return Arrays.asList(letters, whitespaces, others);
        }).thenAcceptAsync(results -> {
            CalcTime.startTracking();
            System.out.println("Letters: " + results.get(0));
            System.out.println("Whitespaces: " + results.get(1));
            System.out.println("Others: " + results.get(2));
            CalcTime.printElapsedTime("Printing results");
        });

        processArrayFuture.thenRunAsync(() -> {
            System.out.println("All tasks completed.");
        });

        processArrayFuture.join();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Total execution took " + elapsed + " ms");
    }
}