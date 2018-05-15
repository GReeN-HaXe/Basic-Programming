package exerciseGroup8;

/**
 * @author Rúben Costa
 */
public class ScalarProduct {

    /**
     * Computes the scalar product of two vectors given two
     * integer arrays with the vector numbers
     *
     * @param a array of first int vector numbers
     * @param b array of second int vector numbers
     * @return and array with the scalar product of two vectors
     */
    int computeScalar(int[] a, int[]b) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
}
