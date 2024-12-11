package Task_3.subtask_1.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomChar {
    public static Character generateRandomChar() {
        int type = ThreadLocalRandom.current().nextInt(3);
        return switch (type) {
            case 0 -> (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
            case 1 -> (char) ThreadLocalRandom.current().nextInt('a', 'z' + 1);
            case 2 -> (char) ThreadLocalRandom.current().nextInt(32, 127);
            default -> '?';
        };
    }
}
