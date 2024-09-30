package Bank;

import java.util.concurrent.Semaphore;

public class Bank {
    public static Semaphore atmSemaphore;
    private static boolean isOpen = false;
    // Duration of one working day in milliseconds
    public static int workDayDurationMS = 30000;
    // Duration of time that bank is closed for. In short - this is duration of night
    public static int nonWorkDayDurationMS = 10000;

    public Bank(int atmCount) {
        if (atmCount < 1) {
            throw new RuntimeException("ATM Count cannot be below 1");
        }
        atmSemaphore = new Semaphore(atmCount);
    }

    public static synchronized boolean getIsOpen() {
        return isOpen;
    }

    public synchronized void open() {
        System.out.println("Bank is open. People can use ATM");
        isOpen = true;
    }

    public synchronized void close() {
        System.out.println("Bank is closing.");
        isOpen = false;
    }
}
