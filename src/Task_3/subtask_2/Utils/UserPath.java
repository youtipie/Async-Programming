package Task_3.subtask_2.Utils;


import java.io.File;
import java.util.Scanner;

public class UserPath {
    public static File getUserPath() {
        Scanner scanner = new Scanner(System.in);
        File path;

        System.out.println("Вітаю в цікавій програмі по підрахунку папок. Оберіть шлях до директорії:");
        while (true) {
            path = new File(scanner.next());

            if (!path.exists()) {
                System.out.println("Такої директорії не існує, спробуйте ще раз:");
                continue;
            }

            return path;
        }
    }
}
