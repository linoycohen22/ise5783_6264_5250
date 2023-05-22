/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author linoi
 *
 */
class Plane2Tests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
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
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	public void testfindIntersections() 
	{
		try
		{
			Plane myPlane = new Plane(new Point(0,5,0), new Point(-5,0,0), new Point(0,0,3));
			// =============== Boundary Values Tests ==================
			
			//Ray is parallel to the plane
			// TC01: the ray included in the plane
			Ray myRay= new Ray(new Point(0,5,0), new Vector(-5,0,0));//the plane include this ray
			assertNull("A included ray has zero intersection points", myPlane.findIntersections(myRay));
			// TC02: the ray not included in the plane
			myRay= new Ray(new Point(0,-5,0), new Vector(5,0,0));//the plane un included this ray
			assertNull("An un included ray has zero intersection points", myPlane.findIntersections(myRay));
			
			//Ray is orthogonal to the plane
			
			myRay= new Ray(new Point(2,4,0), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertEquals("Ray is orthogonal to the plane and starts before the plane",1, myPlane.findIntersections(myRay).size());

			myRay= new Ray(new Point(-5,0,0), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertNull("Ray is orthogonal to the plane and starts at the plane", myPlane.findIntersections(myRay));

			myRay= new Ray(new Point(-7,2,4), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertNull("Ray is orthogonal to the plane and starts after the plane",myPlane.findIntersections(myRay));
			
			//Ray is neither orthogonal nor parallel to and begins at the plane
			// TC06:
			myRay= new Ray(new Point(-1,-1,0), new Vector(1,0,0));//the ray is not orthogonal or parallel to the plane
			assertNull("Ray is neither orthogonal nor parallel to and begins at reference point in the plane", myPlane.findIntersections(myRay));
			
			//Ray is neither orthogonal nor parallel to the plane and begins in
			//the same point which appears as reference point in the plane
			// TC07:
			myRay= new Ray(new Point(0,0,3), new Vector(-5,4,-3));//the ray is not orthogonal or parallel to the plane but not intersects the plane
			assertNull("Ray is neither orthogonal nor parallel to and begins at the plane", myPlane.findIntersections(myRay));
			
			// ============ Equivalence Partitions Tests ================
			// TC08: The Ray must be neither orthogonal nor parallel to the plane
			//Ray does not intersect the plane
			myRay= new Ray(new Point(1,2,0), new Vector(-3,-7,0));
			assertNull("Ray is neither orthogonal nor parallel but doesnt intersects the plane", myPlane.findIntersections(myRay));
			
			// TC09:
			// Ray intersects the plane
			myRay= new Ray(new Point(4,3,0), new Vector(-5.75,3.57,0));//the ray is not orthogonal or parallel to the plane and intersects the plane
			assertEquals("Ray is neither orthogonal nor parallel and intersects the plane ",1, myPlane.findIntersections(myRay).size());
		}
		catch(Exception ex)
		{
			
		}
	}

}