package Task_2;

public class Range {
    int min;
    int max;

    public Range(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Мінімальне значення не може бути більшим за максимальне!");
        }
        this.min = min;
        this.max = max;
    }

    public String toString() {
        return "[" + min + "; " + max + "]";
    }
}
