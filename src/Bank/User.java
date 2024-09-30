package Bank;

import Bank.Utils.RandomTime;

public class User implements Runnable {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("User №%s has arrived to ATM\n", this.name);
        try {
            Bank.atmSemaphore.acquire();
            // If bank is closed, ATMs are closed as well.
            if (!Bank.getIsOpen()) {
                System.out.printf("User №%S cant interact with ATM. Leaving now.\n", this.name);
                Bank.atmSemaphore.release();
                return;
            }
            System.out.printf("User №%s is starting to interact with ATM\n", this.name);
            // Doing something at ATM...
            Thread.sleep(RandomTime.generateRandomMS(5000, 10000));
            System.out.printf("User №%s has finished interacting with ATM\n", this.name);
            Bank.atmSemaphore.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
