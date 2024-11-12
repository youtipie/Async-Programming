package Task_3.subtask_2.Tasks;

import Task_3.subtask_2.FileCharactersCount;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.RecursiveTask;

public class FileProcessorTask extends RecursiveTask<FileCharactersCount> {
    private final File file;

    public FileProcessorTask(File file) {
        this.file = file;
    }

    @Override
    protected FileCharactersCount compute() {
        long charCount = 0;
        try {
            charCount = Files.readString(file.toPath()).length();
        } catch (IOException e) {
            System.err.println("Не вдалося прочитати файл: " + file.getAbsolutePath());
        }
        return new FileCharactersCount(file.getAbsolutePath(), charCount);
    }
}
