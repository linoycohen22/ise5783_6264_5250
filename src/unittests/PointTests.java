/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import primitives.*;

/**
 * Tests of class {@link primitives.Point}
 * @author linoi
 */
class PointTests {

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1, 2, 3);

        /* ============ Equivalence Partitions Tests ============== */

        /* TC01: Add valid point to valid vector. */
        assertEquals(p1.add(new Vector(-1, -2, -3)),
                     new Point(0, 0, 0),
                     "ERROR: Point + Vector doesn't work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);

        /* ============ Equivalence Partitions Tests ============== */

        /* TC01: Subtract valid point to valid vector. */
        assertEquals(new Vector(1, 1, 1),
                     new Point(2, 3, 4).subtract(p1),
                     "ERROR: Point - Point doesn't work correctly");

        /* =============== Boundary Values Tests ================== */

        /* TC02: Check if the zero vector result handle correctly */
        assertThrows(IllegalArgumentException.class,
                     () -> p1.subtract(p1),
                     "ERROR: Point - Point doesn't work correctly when the result is zero vector");
	}
	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		 /* ============ Equivalence Partitions Tests ============== */

        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(5, 4, 32);

        /* TC01: Check the distance squared to two valid points */
        assertTrue(Util.isZero(p1.distanceSquared(p2) - 861),
                   "ERROR: distanceSquared() doesn't work correctly");

        /* =============== Boundary Values Tests ================== */

        /* TC02: Check the distance squared to the same point. */
        assertTrue(Util.isZero(p1.distanceSquared(p1) - 0),
                   "ERROR: distanceSquared() doesn't work correctly if they are the same point");
	}
	

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(5, 4, 32);

        /* ============ Equivalence Partitions Tests ============== */

        /* TC01: Check with valid points. */
        assertTrue(Util.isZero(p1.distance(p2) - 29.34280150224242),
                   "ERROR: distance() doesn't work correctly");

        /* =============== Boundary Values Tests ================== */

        /* TC02: Check the distance to the same point. */
        assertTrue(Util.isZero(p1.distance(p1) - 0),
                   "ERROR: distance() doesn't work correctly if they are the same point");
	}

}
