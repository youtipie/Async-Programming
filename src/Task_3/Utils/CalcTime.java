package Task_3.Utils;

public class CalcTime {
    private static long currentTime;

    public static void startTracking() {
        currentTime = System.currentTimeMillis();
    }

    public static void printElapsedTime(String task) {
        long elapsed = System.currentTimeMillis() - currentTime;
        System.out.println(task + " took " + elapsed + " ms");
    }
}
