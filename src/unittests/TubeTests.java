/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.assertTrue;


import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import geometries.Tube;
/**
 * @author linoi
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	

	public void testGetNormal() {
		Point p0 = new Point(0, 0, 0.5);
        Vector dir = new Vector(0, 0, 1);
        Ray ray = new Ray(p0, dir);
        Tube tube = new Tube(2,ray);
        Vector exceptedVector = new Vector(0, -1, 0);
        Point point = new Point(0, -2, 2);
        Point point1 = new Point(0, -2, 0.5);

        /* TC01: normal situation normal vector to a point on the tube not paralleled to p0. */
        assertTrue(exceptedVector.isSameNormal(tube.getNormal(point)),
                   "ERROR: getNormal() doesn't work correctly.");

        /* TC02: edge situation normal vector to a point on the tube paralleled to p0. */
        assertTrue(exceptedVector.isSameNormal(tube.getNormal(point1)),
                   "ERROR: getNormal() doesn't work correctly when it's in the edge case.");
	}
}
