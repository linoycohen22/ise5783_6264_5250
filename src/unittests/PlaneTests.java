/**
 * 
 */
package unittests;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * @author linoi
 *
 */
class PlaneTests {
	
	public Vector getNormal() {
	    // Implementation of getNormal() function
	    return this.getNormal();
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	
	void testGetNormal() {
	    // Create an instance of the class that contains the getNormal() function
	    PlaneTests myObject = new PlaneTests(); 

	    // Call the getNormal() function on the object
	    Vector normalVector = myObject.getNormal();

	    // Check if the returned normalVector is not null
	    if (normalVector != null) {
	        System.out.println("Normal vector: " + normalVector.toString());
	    } else {
	        System.out.println("Error: Normal vector is null.");
	    }
	}

}
