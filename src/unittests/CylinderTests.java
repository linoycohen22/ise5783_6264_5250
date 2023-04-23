/**
 * 
 */
package unittests;



import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * @author linoi
 *
 */
class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	public Vector getNormal(Point p) {
        // Implementation of getNormal() function
        // Replace this with your actual implementation
        return null;
    }
	
	//@Test
    public void testGetNormal() {

        Vector dir = new Vector(0, 0, 1);
        Point p0 = new Point(0, 0, -1);
        Ray axisRay = new Ray(p0, dir);
        Cylinder cylinder = new Cylinder(1.0, 2.0,axisRay);

        Point sidePoint = new Point(0, 1, 0);
        Vector exceptVectorSide = new Vector(0, 2, 0).normalize();

        Point topBaseCenterPoint = new Point(0, 0, 1);
        Vector exceptVectorCenterTopBase = new Vector(0, 0, 2).normalize();

        Point bottomBaseCenterPoint = new Point(0, 0, -1);
        Vector exceptVectorCenterBottomBase = new Vector(0, 0, -2).normalize();

        Point topBasePoint = new Point(-0.5, 0, 1);
        Vector exceptVectorTopBase = new Vector(0, 0, 1);

        Point bottomBasePoint = new Point(0.5, 0, -1);
        Vector exceptVectorBottomBase = new Vector(0, 0, -1);

        /* ============ Equivalence Partitions Tests ============== */

        /* TC01: point on the sidePoint. */
        assertEquals(exceptVectorSide,
                     cylinder.getNormal(sidePoint),
                     "ERROR: getNormal() point on the side doesn't work correctly.");

        /* TC02: point the top base. */
        assertEquals(exceptVectorTopBase,
                     cylinder.getNormal(topBasePoint),
                     "ERROR: getNormal() point on the top base doesn't work correctly.");

        /* TC03: point the bottom base. */
        assertEquals(exceptVectorBottomBase,
                     cylinder.getNormal(bottomBasePoint),
                     "ERROR: getNormal() point on the top base doesn't work correctly.");

        /* =============== Boundary Values Tests ================== */

        /* TC04: point in the center the top base. */
        assertEquals(exceptVectorCenterTopBase,
                     cylinder.getNormal(topBaseCenterPoint),
                     "ERROR: getNormal() point in the center on the top base " +
                     "doesn't work correctly.");

        /* TC05: point in the center the bottom base. */
        assertEquals(exceptVectorCenterBottomBase,
                     cylinder.getNormal(bottomBaseCenterPoint),
                     "ERROR: getNormal() point in the center on the top base " +
                     "doesn't work correctly.");
    }

	private void assertEquals(Vector exceptVectorSide, Vector normal, String string) {
		// TODO Auto-generated method stub
		
	}

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
  
}

	

