package Bank;

import Bank.Utils.RandomTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank(3);

        // Loop that manages people arriving to bank
        Thread peopleLoop = new Thread(() -> {
            int userCount = 0;
            try {
                while (true) {
                    Thread.sleep(RandomTime.generateRandomMS(500, 2000));
                    new Thread(new User(String.valueOf(userCount))).start();
                    userCount += 1;
                }
            } catch (InterruptedException e) {
                System.out.println("Loop is interrupted.");
            }
        });
        peopleLoop.start();

        int maxDays = 5;
        for (int currentDay = 0; currentDay <= maxDays; currentDay++) {
            bank.open();
            Thread.sleep(Bank.workDayDurationMS);
            bank.close();
            if (currentDay < maxDays) {
                Thread.sleep(Bank.nonWorkDayDurationMS);
            }
        }
        peopleLoop.interrupt();
    }
}
