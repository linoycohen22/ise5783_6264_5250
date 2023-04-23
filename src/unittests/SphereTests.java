/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.assertTrue;


import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import geometries.Sphere;


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
    	 Sphere sphere = new Sphere(new Point(0, 0, 0), 2);
         Vector normalizeVector = new Vector(1.73, 0, 1).normalize();
         Vector resultNormal = sphere.getNormal(new Point(1.73, 0, 1));

         /* ============ Equivalence Partitions Tests ============== */

         /* TC01: Check normal in specific point. */
         assertTrue(normalizeVector.isSameNormal(resultNormal),
                    "ERROR: getNormal() doesn't work correctly.");
	}


}
