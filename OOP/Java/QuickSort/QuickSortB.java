package lab;

import frame.SortArray;


public class QuickSortB extends QuickSort {

	/**
	 * Quicksort algorithm implementation to sort a SorrtArray by choosing the
	 * pivot as the median of the elements at positions (left,middle,right)
	 * 
	 * @param records
	 *            - list of elements to be sorted as a SortArray
	 * @param left
	 *            - the index of the left bound for the algorithm
	 * @param right
	 *            - the index of the right bound for the algorithm
	 * @return Returns the sorted list as SortArray
	 */
	@Override
	public void Quicksort(SortArray records, int left, int right) {
		// implement the Quicksort B algorithm to sort the records
		// (choose the pivot as the median value of the elements at position
		// (left (first),middle,right(last)))
		if(left < right) {
			medianSwap(records, left, right); // Places median in the left index so the array can work on the best case
			int q = Partition(records, left, right);
			Quicksort(records, left, q - 1); // applies Quicksort recursively on the subarray of the smaller than the pivot values
			Quicksort(records, q + 1, right); // applies Quicksort recursively on the subarray of the bigger than the pivot values
		}
	}

	// You may add additional methods here
}
