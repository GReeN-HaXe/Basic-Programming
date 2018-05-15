package exerciseGroup8;
import acm.program.DialogProgram; // acm library to create dialog windows

/**
 * @author Rúben Costa
 */
public class SelectionSort extends DialogProgram {

    /**
     *
     * Exercise 4.3 - Selection Sort
     *
     */

    /**
     * Method in acm library required to run the dialog program.
     *
     */
    public void run() {
        selectionSort(new int[] {1, 7, 2, 6, 3, 5, 4});
    }

    /**
     *
     *  Uses selection sort algorithm to sort an array of ints.
     *  Compares one element with the rest of the list, switches positions
     *  if a smaller element is found and then iterates to the next element
     *  and repeats the process.
     *
     * @param array array of int values
     */

    public void selectionSort(int[] array){
        printArray(array);

        int n = array.length; // stores the array length to avoid extra calculation

        /**
         * for loop that iterates over one element of the array
         * to allow comparison with the other elements in the
         * second iteration
         */
        for(int i= 0; i<n;i++ ){

            int min = array[i]; //stores the smallest value of the array

            int minPos = i; // stores the index of the smallest value to trade later if needed

            /**
             * second iteration to compare the first element with every element
             * of the array and determine the smaller number. It stores the newly found
             * smallest element in the previous variables to keep comparing.
             */
            for (int j = i + 1; j<n; j++) {
                if(array[j] < min) {
                    min = array[j];
                    minPos = j;
                }
            }
            array[minPos] = array[i]; // Places the bigger number in the new position
            array[i] = min; // Places the smaller number in the smaller position
            printArray(array);
        }

    }

    /**
     *
     * Method to print array. Uses StringBuilder to join array elements
     * in a string
     *
     * @param array int array to print
     */
    public void printArray(int[] array){
        StringBuilder sb = new StringBuilder(1024);
        for(int i=0; i<array.length -1; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append(array[array.length - 1]);
        println(sb.toString());
    }

    /**
     * Needed to run the class and test the methods.
     *
     * @param args array of ints given above in the run method
     */
    public static void main(String[] args) {
        new SelectionSort().start(args);
    }
}
