package Pr2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
    private static final Map<Integer, BigInteger> factorialMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<Void>> futureList = Collections.synchronizedList(new ArrayList<>());

        Callable<Void> task1 = createFactorialTask(5);
        Callable<Void> task2 = createFactorialTask(10);
        Callable<Void> task3 = createFactorialTask(15);

        futureList.add(executorService.submit(task1));
        futureList.add(executorService.submit(task2));
        futureList.add(executorService.submit(task3));

        for (Future<Void> future : futureList) {
            if (future.isCancelled()) {
                System.out.println("Task was cancelled");
            } else {
                future.get();
            }
        }

        factorialMap.forEach((key, value) -> System.out.println("Factorial of " + key + " is " + value));

        executorService.shutdown();
    }

    public static Callable<Void> createFactorialTask(int number) {
        return () -> {
            BigInteger factorial = calculateFactorial(number);
            factorialMap.put(number, factorial);
            System.out.println("Thread finished: " + Thread.currentThread().getName());
            return null;
        };
    }

    public static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
