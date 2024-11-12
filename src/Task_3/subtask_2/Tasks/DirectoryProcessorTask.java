package Task_3.subtask_2.Tasks;

import Task_3.subtask_2.FileCharactersCount;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DirectoryProcessorTask extends RecursiveTask<List<FileCharactersCount>> {
    private final File directory;

    public DirectoryProcessorTask(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<FileCharactersCount> compute() {
        List<RecursiveTask<FileCharactersCount>> fileTasks = new ArrayList<>();
        List<RecursiveTask<List<FileCharactersCount>>> directoryTasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectoryProcessorTask task = new DirectoryProcessorTask(file);
                    directoryTasks.add(task);
                    task.fork();
                } else if (file.isFile() && file.getName().endsWith(".txt")) {
                    FileProcessorTask task = new FileProcessorTask(file);
                    fileTasks.add(task);
                    task.fork();
                }
            }
        }

        List<FileCharactersCount> results = new ArrayList<>();
        for (RecursiveTask<FileCharactersCount> task : fileTasks) {
            results.add(task.join());
        }
        for (RecursiveTask<List<FileCharactersCount>> task : directoryTasks) {
            results.addAll(task.join());
        }

        return results;
    }
}
