import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        System.out.println("Онлайн-магазин: Початок обробки замовлення");

        CompletableFuture<Void> startProcessing = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync(): Отримано нове замовлення. Початок обробки...");
        });

        CompletableFuture<Boolean> checkAvailability = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync(): Перевірка доступності товару на складі...");
            sleep(2000);
            return true;
        });

        CompletableFuture<Double> calculateTotal = checkAvailability.thenApplyAsync(available -> {
            if (!available) {
                throw new RuntimeException("thenApplyAsync(): Товар недоступний!");
            }
            System.out.println("thenApplyAsync(): Товар доступний. Розрахунок суми...");
            double basePrice = 100.0;
            double tax = basePrice * 0.2;
            return basePrice + tax;
        });

        CompletableFuture<Void> notifyCustomer = calculateTotal.thenAcceptAsync(total -> {
            System.out.printf("thenAcceptAsync(): Замовлення підтверджено. Сума до оплати: %.2f USD\n", total);
        });

        CompletableFuture<Void> finalizeProcessing = notifyCustomer.thenRunAsync(() -> {
            System.out.println("thenRunAsync(): Замовлення завершено. Повідомлення на склад про відправку товару.");
        });

        finalizeProcessing.join();
        System.out.println("Онлайн-магазин: Завершення роботи програми");
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
