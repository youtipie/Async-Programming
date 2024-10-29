package Task_2.Utils;

import java.util.List;

public class Average {
    public static double calculateAverage(List<? extends Number> list) {
        return list.stream().mapToDouble(Number::doubleValue).average().orElse(0.0);
    }

}
