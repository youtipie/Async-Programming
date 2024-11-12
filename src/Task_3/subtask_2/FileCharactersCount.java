package Task_3.subtask_2;

public class FileCharactersCount {
    private final String fileName;
    private final long charCount;

    public FileCharactersCount(String fileName, long charCount) {
        this.fileName = fileName;
        this.charCount = charCount;
    }

    public String getFileName() {
        return fileName;
    }

    public long getCharCount() {
        return charCount;
    }
}
