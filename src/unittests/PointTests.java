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

class PointTests {

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	
	void testAdd() {
		// Create a Point object with xyz values (1, 2, 3)
	    Point point = new Point(1, 2, 3);

	    // Create a Vector object with xyz values (4, 5, 6)
	    Vector vector = new Vector(4, 5, 6);

	    // Call the add method
	    Point result = point.add(vector);

	    // Expected result
	    double expectedX = point.xyz.d1 + vector.xyz.d1;
	    double expectedY = point.xyz.d2 + vector.xyz.d2;
	    double expectedZ = point.xyz.d3 + vector.xyz.d3;

	    // Test case 1: Check x component
	    System.out.println("Test case 1: Check x component");
	    System.out.println("Expected result: " + expectedX);
	    System.out.println("Actual result: " + result.xyz.d1);
	    System.out.println("Test result: " + (result.xyz.d1 == expectedX ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check y component
	    System.out.println("Test case 2: Check y component");
	    System.out.println("Expected result: " + expectedY);
	    System.out.println("Actual result: " + result.xyz.d2);
	    System.out.println("Test result: " + (result.xyz.d2 == expectedY ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 3: Check z component
	    System.out.println("Test case 3: Check z component");
	    System.out.println("Expected result: " + expectedZ);
	    System.out.println("Actual result: " + result.xyz.d3);
	    System.out.println("Test result: " + (result.xyz.d3 == expectedZ ? "PASS" : "FAIL"));
	    System.out.println();
	}
	

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	
	void testSubtract() {
		 // Create a Point object with xyz values (1, 2, 3)
	    Point point1 = new Point(1, 2, 3);

	    // Create another Point object with xyz values (4, 5, 6)
	    Point point2 = new Point(4, 5, 6);

	    // Expected result
	    double expectedX = point1.xyz.d1 - point2.xyz.d1;
	    double expectedY = point1.xyz.d2 - point2.xyz.d2;
	    double expectedZ = point1.xyz.d3 - point2.xyz.d3;

	    // Call the subtract method
	    Vector result = point1.subtract(point2);

	    // Test case 1: Check x component
	    System.out.println("Test case 1: Check x component");
	    System.out.println("Expected result: " + expectedX);
	    System.out.println("Actual result: " + result.xyz.d1);
	    System.out.println("Test result: " + (result.xyz.d1 == expectedX ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check y component
	    System.out.println("Test case 2: Check y component");
	    System.out.println("Expected result: " + expectedY);
	    System.out.println("Actual result: " + result.xyz.d2);
	    System.out.println("Test result: " + (result.xyz.d2 == expectedY ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 3: Check z component
	    System.out.println("Test case 3: Check z component");
	    System.out.println("Expected result: " + expectedZ);
	    System.out.println("Actual result: " + result.xyz.d3);
	    System.out.println("Test result: " + (result.xyz.d3 == expectedZ ? "PASS" : "FAIL"));
	    System.out.println();
	}
	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	
	void testDistanceSquared() {
		// Create a Point object with xyz values (1, 2, 3)
	    Point point1 = new Point(1, 2, 3);

	    // Create another Point object with xyz values (4, 5, 6)
	    Point point2 = new Point(4, 5, 6);

	    // Test case 1: Calculate squared distance between Point1 and Point2
	    double result = point1.distanceSquared(point2);
	    double expected = Math.pow(point1.xyz.d1 - point2.xyz.d1, 2)
	            + Math.pow(point1.xyz.d2 - point2.xyz.d2, 2)
	            + Math.pow(point1.xyz.d3 - point2.xyz.d3, 2);
	    System.out.println("Test case 1: Calculate squared distance between Point1 and Point2");
	    System.out.println("Expected result: " + expected);
	    System.out.println("Actual result: " + result);
	    System.out.println("Test result: " + (result == expected ? "PASS" : "FAIL"));
	    System.out.println();
	}
	

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	
	void testDistance() {
		// Create a Point object with xyz values (1, 2, 3)
	    Point point1 = new Point(1, 2, 3);

	    // Create another Point object with xyz values (4, 5, 6)
	    Point point2 = new Point(4, 5, 6);

	    // Call the distance method
	    double result = point1.distance(point2);

	    // Expected result
	    double expected = Math.sqrt(point1.distanceSquared(point2));

	    // Test case 1: Check distance result
	    System.out.println("Test case 1: Check distance result");
	    System.out.println("Expected result: " + expected);
	    System.out.println("Actual result: " + result);
	    System.out.println("Test result: " + (result == expected ? "PASS" : "FAIL"));
	    System.out.println();
	}

}
