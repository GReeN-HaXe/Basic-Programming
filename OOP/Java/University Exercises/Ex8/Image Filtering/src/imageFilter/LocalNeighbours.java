package imageFilter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Florian Kadner
 * @version 1.0 20170913
 */
public class LocalNeighbours {

	public LocalNeighbours() {

	}

	private ArrayList<Float> pixels = new ArrayList<Float>();

	public Float[] getPixels() {
		Float[] pixelsArray = new Float[pixels.size()];
		pixelsArray = pixels.toArray(pixelsArray);
		return pixelsArray;
	}

	public int getSize() {
		return pixels.size();
	}

	public void addPixel(float Pixel) {
		pixels.add(Pixel);
	}

	/**
	 * sorts the local neighbourhood with a specific sorting algorithm
	 */
	public void sort() {
		Float[] A = getPixels();

		// TODO: 
		int begin = -1;
		int end = A.length;

		while(begin < end) {
			begin += 1;
			end -= 1;
			for(int i = begin; i < end; i++) {
				if(A[i] > A[i + 1]){
					float middleValue = A[i];
					A[i] = A[i + 1];
					A[i + 1] = middleValue;
				}
			}
			for(int i = end; --i >= begin; i--){
				if(A[i] > A[i + 1]) {
					float middleValue = A[i];
					A[i] = A[i + 1];
					A[i + 1] = middleValue;
				}
			}
		}
		//

		this.pixels = new ArrayList<>(Arrays.asList(A));

	}

	/**
	 * 
	 * @return returns the median of the current local neighbourhood
	 */
	public float getMedian() {
		sort(); // sorts the numbers in the array in a ascending order
		Float[] A = getPixels(); // stores sorted array
		int arrayLength = A.length; // stores length of array
		float median = 0f; // sets median to zero

		// TODO
		if(arrayLength % 2 == 0) { // if size of array is even, do:
			median =((float) A[arrayLength / 2] + (float) A[arrayLength / 2 - 1]) / 2;
		} else { // if size of array is uneven, do:
			median =(float) A[arrayLength / 2];
		}
		return median;

	}

}
