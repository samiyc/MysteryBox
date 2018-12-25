package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MathUtils {

    /**
     * Return a random boolean 0/1
     *
     * @return Boolean
     */
    public static Boolean rndBool() {
        return Math.random() < 0.5;
    }

    /**
     * Return a random number bettween start and end
     * @param start Integer
     * @param end Integer
     * @return Integer
     */
    public static Integer rndNb(Integer start, Integer end) {
        Double value = Math.random() * (end-start) + start;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.intValue();
    }

    /**
     * Convertie une liste de boolean en un chaine de caractère
     * @param booleans List<Boolean>
     * @return String
     */
    public static String listBoolToString(List<Boolean> booleans) {
        StringBuilder retour = new StringBuilder();
        for(Boolean bool : booleans) {
            String strBool = bool ? "1" : ".";
            retour.append(strBool);
        }
        return retour.toString();
    }
}
