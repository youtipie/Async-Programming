package Task_2.Utils;

import Task_2.Range;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserRange {
    public static Range getUserRange(int absoluteMin, int absoluteMax) {
        Scanner scanner = new Scanner(System.in);
        Range range;

        System.out.printf("Вітаю в цікавій програмі по обробці масиву. Оберіть значення в діапазоні [%d; %d]\n", absoluteMin, absoluteMax);
        while (true) {
            try {
                System.out.println("Введіть мінімальне значення для діапазону:");
                int min = scanner.nextInt();
                if (min < absoluteMin) {
                    System.out.println("Введене число менше за дозволене!");
                    continue;
                }

                System.out.println("Введіть максимальне значення для діапазону:");
                int max = scanner.nextInt();
                if (max > absoluteMax) {
                    System.out.println("Введене число більше за дозволене!");
                    continue;
                }

                range = new Range(min, max);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Введіть коректні цілі числа!");
                scanner.next();
            }
        }

        return range;
    }
}
