package Exercise9;

/**
 * Exercise 9
 * Practicing JUnit testing in java and
 * OOP basic concepts
 *
 * @author Rúben Costa
 * @version 1.0
 */

/**
 * imports for the assertEquals/assertFalse
 * methods used in the JUnit tests
 */

import static org.junit.Assert.assertEquals; // Compares two results
import static org.junit.Assert.assertFalse; // Test false results

/**
 * Import the annotations for the Junit test cases
 */
import org.junit.Test;
import org.junit.Before;

/**
 * public test class to test the Attributes and
 * methods of the previously defined Car class
 *
 */
public class TestCar {

    private Car testCar; // variable to hold the address for a Car object for the test

    /**
     * Before annotation and the method ensure
     * that an object is created before every
     * test with the previously created variable
     */
    @Before
    public void initTestCar(){
        testCar = new Car("Ford");
    }

    /**
     * Tests the correct declaration and initiation
     * of the constructor
     */
    @Test
    public void testCarAttributes(){
        assertEquals(testCar.getName(), "Ford"); // checks the set of name
        assertFalse("Car name is not BMW, but Ford", testCar.getName().equals("BMW")); // checks if a false value is returnd
        assertEquals(testCar.getMileage(), 0.0, 0.01); // tests the set of mileage
    }

    /**
     * Tests the drive method by giving two distance
     * and checking if the data update is correct
     */
    @Test
    public void testDriveMethod(){
        testCar.drive(74.3); // set a mileage
        assertEquals(testCar.getMileage(), 74.3, 0.01); // test getMileage
        testCar.drive(26.8); // give a new distance
        assertEquals(testCar.getMileage(), 101.1, 0.01); // test updated getMileage
    }
}
