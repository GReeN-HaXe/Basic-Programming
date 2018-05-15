package imageFilter;

/**
 * 
 * @author Florian Kadner
 * @version 1.0 20170913
 */
public class Filter {

	public Filter() {

	}

	/**
	 * This method implements a linear filter with discrete convolution
	 * 
	 * @param image
	 *            the image which should be filtered
	 * @param kernel
	 *            the kernel used for the linear filter
	 * @return the filtered image
	 */
	public Image linearFilter(Image image, float[][] kernel) {

		Image filtered = image.clone();

		// TODO
		int kernelSize = kernel.length;
		int a1 = (int) kernelSize / 2;
		int a2 = (int) kernelSize / 2;

		int iHeight = image.getHeight();
		int iWidth = image.getWidth();
		for (int i = 0 ; i < iHeight ; i++) {// iterates over lines
			for (int j = 0 ; j < iWidth ; j++) { //iterates over columns

				float pixelResult = 0f;// variable to store result of pixel calculation
				/**
				 *
				 * for that iterates the kernel matrix and the values of the corresponding
				 * image matrix
				 */
				for (int x = 0; x < kernelSize; x++) { // iterates over line
					for (int y = 0; y < kernelSize; y++) { // iterates over column
							int xIndex = i + x;//X index for scalar multiplication with kernel matrix
							int yIndex = j + y;//Y index for scalar multiplication with kernel matrix
							/**
							 * border adaptation, if the index is out of bounds
							 * it uses the value close to it
							 */
							if(xIndex - a1 < 0) a1 = xIndex;
							if(xIndex - a1 > iHeight - 1) a1 = xIndex - iHeight + 1;
							if(yIndex - a2 < 0) a2 = yIndex;
							if(yIndex - a2 > iWidth - 1) a2 = yIndex - iWidth + 1;
							// result of convolution is stored in pixelResult
							pixelResult += image.getPixel(xIndex - a1, yIndex - a2) * kernel[x][y];
							a1 = (int) kernelSize / 2;
							a2 = (int) kernelSize / 2;
						}
					}
					if(pixelResult < 0) pixelResult = 0;
					if(pixelResult > image.getMax()) pixelResult = image.getMax();
					filtered.setPixel((float) Math.floor(pixelResult), i, j);
		}
	}
		return filtered;
}

	/**
	 * This method implements a nonlinear filter with median filter
	 * 
	 * @param image
	 *            the image which should be filtered
	 * @param filtersize
	 *            the size of the median filter
	 * @return the filtered image
	 */
	public Image nonLinearFilterMedian(Image image, int filtersize) {

		Image filtered = image.clone();
		int iHeight = image.getHeight();
		int iWidth = image.getWidth();
		int a1 = (int) filtersize / 2;
		int a2 = (int) filtersize / 2;

		// TODO
		for(int i = 0; i < iHeight; i++){ // iterates over lines
			for(int j = 0; j < iWidth; i++) { // iterates over columns

				LocalNeighbours localN = new LocalNeighbours();

				for(int x = 0; x < filtersize; x++){
					for(int y = 0; y < filtersize; y++) {
						int xIndex = i + x;//X index
						int yIndex = j + y;//Y index

						if(xIndex - a1 < 0) a1 = xIndex;
						if(xIndex - a1 > iHeight - 1) a1 = xIndex - iHeight + 1;
						if(yIndex - a2 < 0) a2 = yIndex;
						if(yIndex - a2 > iWidth - 1) a2 = yIndex - iWidth + 1;

						localN.addPixel(image.getPixel(xIndex - a1, yIndex - a2));
						a1 = filtersize / 2;
						a2 = filtersize / 2;

					}
				}
				filtered.setPixel(localN.getMedian(), i, j);
			}
		}

		return filtered;
	}

}
