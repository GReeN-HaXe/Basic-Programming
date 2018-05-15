package Exercise9;

/**
 *  Class Car models an example class for
 *  exercising object class construction
 *  basics. It has two getters for the name
 *  and mileage of the drive a method which
 *  accepts a distance and updates the mileage
 *
 * @author Rúben Costa
 * @version 1.0
 */
public class Car {
    private String name;
    private double mileage;

    /**
     * constructor for the class Car
     * sets the name and resets the mileage
     * in object instance creation.
     *
     * @param carName the name for the car object
     */
    public Car(String carName){
        this.name = carName;
        this.mileage = 0.0;
    }

    /**
     * Getter for the mileage of an object
     * of the class Car
     *
     * @return the mileage of a Car object
     */
    public Double getMileage(){
        return this.mileage;
    }

    /**
     * Getter for the name of an object
     * of the class Car
     *
     * @return the name of a Car object
     */
    public String getName(){
        return this.name;
    }

    /**
     * Updates the mileage on a Car
     * object given the distance it drives
     *
     * @param distance distance that the car drove
     */
    public void drive(double distance){
        this.mileage += distance;
    }
}
