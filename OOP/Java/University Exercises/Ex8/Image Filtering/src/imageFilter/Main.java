package imageFilter;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
	
		// ACHTUNG: Überschreiben Sie nicht die von uns mitgelieferten Bilder, sonst laufen Ihre Tests am Ende nicht mehr.
		
		Image beer = new Image("Beer.pgm");
		Image mandarine = new Image("Mandarine.pgm");
		Image cat = new Image("cat.pgm");

		Filter filter = new Filter();
		float[][] sharp = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
		float[][] blur = { { 1 * 1 / 16f, 2 * 1 / 16f, 1 * 1 / 16f }, { 2 * 1 / 16f, 4 * 1 / 16f, 2 * 1 / 16f }, { 1 * 1 / 16f, 2 * 1 / 16f, 1 * 1 / 16f } };
		float[][] edgeKernel = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
		
		Image mandarine_sharp = filter.linearFilter(mandarine, sharp);
		Image beer_median3 = filter.nonLinearFilterMedian(beer, 3);
		Image cat_edge = filter.linearFilter(cat, edgeKernel);
		
		beer_median3.save("Beer_median3.pgm");
		cat_edge.save("Cat_edge.pgm");
	}

}
