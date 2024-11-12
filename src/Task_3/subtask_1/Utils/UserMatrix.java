package Task_3.subtask_1.Utils;


import Task_3.subtask_1.Matrix;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMatrix {
    public static Matrix getUserMatrix() {
        Scanner scanner = new Scanner(System.in);
        Matrix dimensions;

        System.out.println("Вітаю в цікавій програмі по обробці масиву. Оберіть розміри масиву:");
        while (true) {
            try {
                System.out.println("Введіть кількість рядків:");
                int rows = scanner.nextInt();

                System.out.println("Введіть кількість колонок:");
                int cols = scanner.nextInt();

                dimensions = new Matrix(rows, cols);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Введіть коректні цілі числа!");
                scanner.next();
            }
        }

        return dimensions;
    }
}
