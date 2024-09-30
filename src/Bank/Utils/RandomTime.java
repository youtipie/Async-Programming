package Bank.Utils;

public class RandomTime {
    public static int generateRandomMS(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start must be lower than end");
        }
        return (int) ((Math.random() * (end - start)) + start);
    }
}
