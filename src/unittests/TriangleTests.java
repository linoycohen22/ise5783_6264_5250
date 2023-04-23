/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import geometries.Triangle;

/**
 * @author linoi
 *
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */

	public void testGetNormalPoint() {
		Point a = new Point(6, 8, 0);
		Point b = new Point(0, 0, 0);
		Point c = new Point(9, 0, 0);

		Triangle triangle = new Triangle(a, b, c);
		Vector normalizeVector = new Vector(0, 0, 1);
		Vector resultNormal = triangle.getNormal(a);

		/* ============ Equivalence Partitions Tests ============== */

		/* TC01: Check normal in specific point. */
		assertTrue(normalizeVector.isSameNormal(resultNormal), "ERROR: getNormal() doesn't work correctly.");

	}
}
