package Task_3.Utils;

import java.util.concurrent.TimeUnit;

public class SimulateDelay {
    public static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
