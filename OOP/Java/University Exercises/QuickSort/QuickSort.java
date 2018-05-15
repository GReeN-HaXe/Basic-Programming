package lab;

import frame.SortArray;

/**
 * Abstract superclass for the Quicksort algorithm.
 * 
 * @author NAJI
 */
public abstract class QuickSort {

	// DO NOT modify this method
	public abstract void Quicksort(SortArray records, int left, int right);

	// You may add additional methods here

	/**
	 * An auxiliary method used to swap two values inside the array
	 * given their indexes.
	 * Used to partition the array correctly.
	 *
	 * @param records the array which contains the values
	 * @param x index of first item
	 * @param y index of second item
	 */
	public void swap(SortArray records, int x, int y) {
		SortingItem temp = records.getElementAt(x); // stores one value in a temporary variable for the swap
		records.setElementAt(x, records.getElementAt(y));
		records.setElementAt(y, temp);
	}

	/**
	 * Calculates the median value of three array values by finding
	 * the middle index and comparing it with the leftmost and
	 * rightmost values. It then swaps the leftmost value with
	 * the found median in order to apply QuickSortA with the best
	 * as the pivot.
	 *
	 * @param records array that contains the values
	 * @param left leftmost value of the array
	 * @param right rightmost value of the array
	 */
	public void medianSwap(SortArray records, int left, int right) {
		int middle = (left + right) / 2; // stores the middle index

		// if the middle value is bigger(comes after) than the left value swap them:
		if(records.getElementAt(middle).BookSerialNumber.compareTo(records.getElementAt(left).BookSerialNumber) < 0) swap(records, left, middle);
		// if the right value is bigger(comes after) than the left value swap them:
		if(records.getElementAt(right).BookSerialNumber.compareTo(records.getElementAt(left).BookSerialNumber) < 0) swap(records, left, right);
		// if the right value is bigger(comes after) than the middle value swap them:
		if(records.getElementAt(right).BookSerialNumber.compareTo(records.getElementAt(middle).BookSerialNumber) < 0) swap(records, right, middle);
		// In the end the median will be stored in the middle index and we can swap it with the left value:
		swap(records, left, middle);
	}

	/**
	 * Sets the pivot as the left value and sort the array into
	 * values that are greater than the pivot on one side and
	 * values that are smaller than the pivot on the other
	 * while placing the pivot in the middle index, returning
	 * that index at the end.
	 *
	 * @param records array to partition
	 * @param left leftmost value of records
	 * @param right rightmost value of records
	 * @return the index of the pivot
	 */
	public int Partition(SortArray records, int left, int right) {
		SortingItem pivot = records.getElementAt(left);// sets the pivot as the leftmost element
		int i = right + 1; // sets the pointer for the subarray of values greater then the pivot

		for(int j = right; j > left; j--){
			if(records.getElementAt(j).BookSerialNumber.compareTo(pivot.BookSerialNumber) > 0) { // if j element comes after pivot element
				// decrease pointer for values greater than pivot
				i = i - 1;
				swap(records, i, j);
			} else if(records.getElementAt(j).BookSerialNumber.compareTo(pivot.BookSerialNumber) == 0
					&& records.getElementAt(j).ReaderID.compareTo(pivot.ReaderID) > 0) {
				// if book serial number is the same, compare readerID
				i = i - 1;
				swap(records, i, j);
			}
		}
		// place pivot in the middle
		swap(records, left, i - 1);
		return i - 1;

	}

}
