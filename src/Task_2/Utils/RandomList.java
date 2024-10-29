package Task_2.Utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomList {
    public static ArrayList<Integer> generateRandomList(int minValue, int maxValue, int minSize, int maxSize) {
        Random random = new Random();
        int size = random.nextInt(maxSize - minSize + 1) + minSize;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(maxValue - minValue + 1) + minValue);
        }
        return list;
    }
}
