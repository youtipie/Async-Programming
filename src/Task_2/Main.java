package Task_2;

import Task_2.Utils.Average;
import Task_2.Utils.RandomList;
import Task_2.Utils.UserRange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.*;

public class Main {
    private static final int rangeMin = 0;
    private static final int rangeMax = 1000;
    private static final int sizeMin = 40;
    private static final int sizeMax = 60;
    private static final int chunkSize = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Range range = UserRange.getUserRange(rangeMin, rangeMax);
        System.out.println("Введений діапазон: " + range);
        ArrayList<Integer> list = RandomList.generateRandomList(range.min, range.max, sizeMin, sizeMax);
        System.out.println("Згенерований список: " + list);

        CopyOnWriteArraySet<Double> results = new CopyOnWriteArraySet<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ArrayList<Future<Double>> futures = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i = 0; i < list.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, list.size());
            List<Integer> part = list.subList(i, end);

            Future<Double> future = executor.submit(() -> Average.calculateAverage(part));
            futures.add(future);
        }

        for (Future<Double> future : futures) {
            // Використовуємо цикл для перевірки чи завдання завершено.
            // Кращим варіантом було б використати CompletableFuture, але згідно з завданням,
            // потрібно використовувати тільки матеріали лекцій 3-4.
            while (true) {
                if (future.isDone()) {
                    double result = future.get();
                    results.add(result);
                    break;
                } else if (future.isCancelled()) {
                    System.out.println("Завдання скасовано.");
                    break;
                }
                Thread.sleep(5);
            }
        }

        System.out.println("Середні значення частин масиву: " + results);

        double globalAverage = Average.calculateAverage(results.stream().toList());
        System.out.printf("Загальне середнє значення масиву: %.2f\n", globalAverage);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Час виконання програми: " + duration + " мс");

        executor.shutdown();
    }
}
