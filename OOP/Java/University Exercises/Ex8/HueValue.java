package exerciseGroup8;

/**
 * @author Rúben Costa
 */
public class HueValue {

    /**
     *
     * Exercise 4.1 - Hue value
     */


    public static final int COLOR_MAX = 255; //constant used to normalize the value of a color

    /**
     * Calculates the hue of a color given in rgb code
     *
     * @param red value of red.
     * @param green value of green.
     * @param blue value of blue.
     *
     * @return The hue value of a color.
     */
    public static double getHue(int red, int green, int blue) {
        double r = red / COLOR_MAX; //Norming red value
        double g = green / COLOR_MAX; //Norming green value
        double b = blue / COLOR_MAX; //Norming blue value
        double maxTotal = Math.max(Math.max(r, g), b); //max value of the 3 parameters
        double minTotal = Math.min(Math.min(r, g), b); //min value of the 3 parameters
        double divisor = maxTotal - minTotal;

        double result; // variable to store the Hue value

        /**
         * Mathematical formula to calculate Hue value.
         * Given by the exercise.
         */
        if(maxTotal == minTotal) result = 0.0;
        else if(r == maxTotal) result = (60 * ((g - b) / divisor));
        else if(g == maxTotal) result = (120 + (60 *((b - r) / divisor)));
        else if(b == maxTotal) result = (240 + (60 *((r - g) / (divisor))));
        else result = -1; // to recognise some error in calculation

        if(result < 0) result += 360;
        return result;
    }
}
