/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;
import geometries.Plane;
/**
 * @author linoi
 *
 */
class PlaneTests {
	
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	public void testGetNormal() {
		 Point a = new Point(1, 2, 3);
	     Point b = new Point(2, 1, 4);
	     Point c = new Point(2, 1, 1);

	        Plane plane = new Plane(a, b, c);
	        Vector expectedVector = new Vector(3, 3, 0).normalize();

	        /* ============ Equivalence Partitions Tests ============== */

	        /* TC01: Check normal in specific point. */
	        assertEquals(expectedVector, plane.getNormal(a),
	                   "ERROR: getNormal() doesn't work correctly.");
	}

}
