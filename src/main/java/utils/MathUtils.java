package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static Boolean rndBool() {
        return Math.random() < 0.5;
    }
    public static Integer rndNb(Integer start, Integer end) {
        Double value = Math.random() * (end-start) + start;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.intValue();
    }
}
