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
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	
	public Vector getNormal(Point p) {
	    // Implementation of getNormal(Point p) function
	    // Replace this with your actual implementation
	    return null;
	}

     void testGetNormal() {
	    // Create a Point object for testing
	    Point testPoint = new Point(3.0, 4.0,0.0); // Example: creating a Point with x = 3.0 and y = 4.0

	    // Call the getNormal() function with the testPoint
	    Vector normalVector = getNormal(testPoint);

	    // Check if the returned normalVector is not null
	    if (normalVector != null) {
	        System.out.println("Normal vector: " + normalVector.toString());
	    } else {
	        System.out.println("Error: Normal vector is null.");
	    }
	}


}
