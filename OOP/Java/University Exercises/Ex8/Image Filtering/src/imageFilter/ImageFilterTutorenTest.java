package imageFilter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.testng.annotations.BeforeClass;

public class ImageFilterTutorenTest {

	Filter filter = new Filter();
	static Image cow;
	static float[][] sharpKernel = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
	static float[][] blurKernel = { { 1 * 1 / 16f, 2 * 1 / 16f, 1 * 1 / 16f },
			{ 2 * 1 / 16f, 4 * 1 / 16f, 2 * 1 / 16f }, { 1 * 1 / 16f, 2 * 1 / 16f, 1 * 1 / 16f } };

	@BeforeClass
	public static void loadImagesBeforeClass() throws IOException {
		cow = new Image("Kuh.pgm");
	}

	@Test
	public void testLinearFilterNormal() {
		Image cow_sharp = filter.linearFilter(cow, sharpKernel);
		Image cow_blur = filter.linearFilter(cow, blurKernel);
		assertEquals(54, cow_sharp.getPixel(10, 10), 0f);
		assertEquals(107, cow_blur.getPixel(56, 34), 0f);
	}

	@Test
	public void testLinearFilterEdge() {
		Image cow_sharp = filter.linearFilter(cow, sharpKernel);
		Image cow_blur = filter.linearFilter(cow, blurKernel);
		assertEquals(97, cow_blur.getPixel(0, 0), 0f);
		assertEquals(84, cow_sharp.getPixel(cow_sharp.getHeight() - 1, cow_sharp.getWidth() - 1), 0f);
	}

	@Test
	public void testLinearFilterPicture() throws IOException {
		Image cow_sharp_solution = new Image("Kuh_sharp.pgm");
		Image cow_sharp = filter.linearFilter(cow, sharpKernel);
		assertArrayEquals(cow_sharp.getData(), cow_sharp_solution.getData());
	}
	
	@Test
	public void testLinearBigKernel() throws IOException {
		float[][] bigKernel = { 
				{ 0.000036f,0.000363f,0.001446f,0.002291f,0.001446f,0.000363f,0.000036f}, 
				{ 0.000363f, 0.003676f, 0.014662f, 0.023226f, 0.014662f, 0.003676f, 0.000363f},  
				{ 0.001446f, 0.014662f,	0.058488f, 0.092651f, 0.058488f, 0.014662f, 0.001446f },
				{0.002291f, 0.023226f, 0.092651f, 0.146768f, 0.092651f, 0.023226f, 0.002291f }, 
				{ 0.001446f, 0.014662f,	0.058488f, 0.092651f, 0.058488f, 0.014662f, 0.001446f }, 
				{ 0.000363f, 0.003676f, 0.014662f, 0.023226f, 0.014662f, 0.003676f, 0.000363f} , 
				{0.000036f,0.000363f,0.001446f,0.002291f,0.001446f,0.000363f,0.000036f} };
		Image cow_big = filter.linearFilter(cow, bigKernel);
		Image cow_big_solution = new Image("Kuh_bigKernel.pgm");
		assertEquals(96, cow_big.getPixel(0, 0), 0f);
		assertEquals(85, cow_big.getPixel(cow_big.getHeight() - 1, cow_big.getWidth() - 1), 0f);
		assertArrayEquals(cow_big.getData(), cow_big_solution.getData());
	}

	@Test
	public void testSortingAlgo() {
		LocalNeighbours test_neigh = new LocalNeighbours();
		test_neigh.addPixel(12);
		test_neigh.addPixel(-1);
		test_neigh.addPixel(4);
		test_neigh.addPixel(4);
		test_neigh.addPixel(3);
		test_neigh.addPixel(2);
		test_neigh.addPixel(5);
		test_neigh.addPixel(5);
		test_neigh.sort();
		LocalNeighbours sorted = new LocalNeighbours();
		sorted.addPixel(-1);
		sorted.addPixel(2);
		sorted.addPixel(3);
		sorted.addPixel(4);
		sorted.addPixel(4);
		sorted.addPixel(5);
		sorted.addPixel(5);
		sorted.addPixel(12);
		assertArrayEquals(sorted.getPixels(), test_neigh.getPixels());
	}

	@Test
	public void testMedian() {
		LocalNeighbours test_neigh_even = new LocalNeighbours();
		test_neigh_even.addPixel(1);
		test_neigh_even.addPixel(1);
		test_neigh_even.addPixel(1);
		test_neigh_even.addPixel(1);
		assertEquals(1, test_neigh_even.getMedian(), 0f);

		LocalNeighbours test_neigh_odd = new LocalNeighbours();
		test_neigh_odd.addPixel(-4);
		test_neigh_odd.addPixel(5);
		test_neigh_odd.addPixel(3);
		test_neigh_odd.addPixel(1);
		test_neigh_odd.addPixel(0);
		test_neigh_odd.addPixel(0);
		test_neigh_odd.addPixel(1);
		assertEquals(1, test_neigh_odd.getMedian(), 0f);
	}

	@Test
	public void testNonLinearFilterNormal() {
		Image cow_median5 = filter.nonLinearFilterMedian(cow, 5);
		assertEquals(87, cow_median5.getPixel(4, 4), 0f);

	}

	@Test
	public void testNonLinearFilterEdge() {
		Image cow_median5 = filter.nonLinearFilterMedian(cow, 5);
		assertEquals(85, cow_median5.getPixel(cow_median5.getHeight() - 1, cow_median5.getWidth() -1), 0f);
	}

	@Test
	public void testNonLinearFilterPicture() throws IOException {
		Image cow_median3_solution = new Image("Kuh_median3.pgm");
		Image cow_median3 = filter.nonLinearFilterMedian(cow, 3);
		assertArrayEquals(cow_median3.getData(), cow_median3_solution.getData());
	}

}
