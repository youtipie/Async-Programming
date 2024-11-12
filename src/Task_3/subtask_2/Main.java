package Task_3.subtask_2;

import Task_3.subtask_2.Tasks.DirectoryProcessorTask;
import Task_3.subtask_2.Utils.UserPath;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        File path = UserPath.getUserPath();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DirectoryProcessorTask task = new DirectoryProcessorTask(path);

        long startTime = System.currentTimeMillis();
        List<FileCharactersCount> results = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();

        if (results.size() > 0) {
            for (FileCharactersCount result : results) {
                System.out.println("Файл: " + result.getFileName() + ", Кількість символів: " + result.getCharCount());
            }
        } else {
            System.out.println("Текстових файлів не знайдено.");
        }

        System.out.println("Час виконання: " + (endTime - startTime) + " ms");
    }
}
