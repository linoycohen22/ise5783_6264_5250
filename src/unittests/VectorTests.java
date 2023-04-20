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
class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	
	void testAddVector() {
		// Create a Vector object with xyz values (1, 2, 3)
	    Vector vector1 = new Vector(1, 2, 3);

	    // Create another Vector object with xyz values (4, 5, 6)
	    Vector vector2 = new Vector(4, 5, 6);

	    // Call the add method
	    Vector result = vector1.add(vector2);

	    // Expected result
	    Vector expected = new Vector(vector1.xyz.d1 + vector2.xyz.d1, vector1.xyz.d2 + vector2.xyz.d2, vector1.xyz.d3 + vector2.xyz.d3);

	    // Test case 1: Check x, y, z values of the result
	    System.out.println("Test case 1: Check x, y, z values of the result");
	    System.out.println("Expected result: x=" + expected.xyz.d1 + ", y=" + expected.xyz.d2 + ", z=" + expected.xyz.d3);
	    System.out.println("Actual result: x=" + result.xyz.d1 + ", y=" + result.xyz.d2 + ", z=" + result.xyz.d3);
	    System.out.println("Test result: " + (result.equals(expected) ? "PASS" : "FAIL"));
	    System.out.println();	
	    }

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	
	void testScale() {
		// Create a Vector object with xyz values (1, 2, 3)
	    Vector vector = new Vector(1, 2, 3);

	    // Call the scale method with a scaling factor of 2.5
	    double scaleFactor = 2.5;
	    Vector result = vector.scale(scaleFactor);

	    // Expected result
	    Vector expected = new Vector(vector.xyz.d1 * scaleFactor, vector.xyz.d2 * scaleFactor, vector.xyz.d3 * scaleFactor);

	    // Test case 1: Check x, y, z values of the result
	    System.out.println("Test case 1: Check x, y, z values of the result");
	    System.out.println("Expected result: x=" + expected.xyz.d1 + ", y=" + expected.xyz.d2 + ", z=" + expected.xyz.d3);
	    System.out.println("Actual result: x=" + result.xyz.d1 + ", y=" + result.xyz.d2 + ", z=" + result.xyz.d3);
	    System.out.println("Test result: " + (result.equals(expected) ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vector is unchanged
	    System.out.println("Test case 2: Check if the original vector is unchanged");
	    System.out.println("Original vector: x=" + vector.xyz.d1 + ", y=" + vector.xyz.d2 + ", z=" + vector.xyz.d3);
	    System.out.println("Test result: " + (vector.equals(new Vector(1, 2, 3)) ? "PASS" : "FAIL"));
	    System.out.println();	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	
  void testCrossProduct() {
	    // Create two Vector objects with xyz values
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(4, 5, 6);

	    // Call the crossProduct method with the two Vector objects
	    Vector result = vector1.crossProduct(vector2);

	    // Expected result
	    double x = vector1.xyz.d2 * vector2.xyz.d3 - vector1.xyz.d3 * vector2.xyz.d2;
	    double y = vector1.xyz.d3 * vector2.xyz.d1 - vector1.xyz.d1 * vector2.xyz.d3;
	    double z = vector1.xyz.d1 * vector2.xyz.d2 - vector1.xyz.d2 * vector2.xyz.d1;
	    Vector expected = new Vector(x, y, z);

	    // Test case 1: Check x, y, z values of the result
	    System.out.println("Test case 1: Check x, y, z values of the result");
	    System.out.println("Expected result: x=" + expected.xyz.d1 + ", y=" + expected.xyz.d2 + ", z=" + expected.xyz.d3);
	    System.out.println("Actual result: x=" + result.xyz.d1 + ", y=" + result.xyz.d2 + ", z=" + result.xyz.d3);
	    System.out.println("Test result: " + (result.equals(expected) ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vectors are unchanged
	    System.out.println("Test case 2: Check if the original vectors are unchanged");
	    System.out.println("Original vector 1: x=" + vector1.xyz.d1 + ", y=" + vector1.xyz.d2 + ", z=" + vector1.xyz.d3);
	    System.out.println("Original vector 2: x=" + vector2.xyz.d1 + ", y=" + vector2.xyz.d2 + ", z=" + vector2.xyz.d3);
	    System.out.println("Test result: " + (vector1.equals(new Vector(1, 2, 3)) && vector2.equals(new Vector(4, 5, 6)) ? "PASS" : "FAIL"));
	    System.out.println();
	  }

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	
	void testDotProduct() {
		// Create two Vector objects with xyz values
	    Vector vector1 = new Vector(1, 2, 3);
	    Vector vector2 = new Vector(4, 5, 6);

	    // Call the dotProduct method with the two Vector objects
	    double result = vector1.dotProduct(vector2);

	    // Expected result
	    double xx = vector1.xyz.d1 * vector2.xyz.d1;
	    double yy = vector1.xyz.d2 * vector2.xyz.d2;
	    double zz = vector1.xyz.d3 * vector2.xyz.d3;
	    double expected = xx + yy + zz;

	    // Test case 1: Check if the dot product result is correct
	    System.out.println("Test case 1: Check if the dot product result is correct");
	    System.out.println("Expected result: " + expected);
	    System.out.println("Actual result: " + result);
	    System.out.println("Test result: " + (result == expected ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vectors are unchanged
	    System.out.println("Test case 2: Check if the original vectors are unchanged");
	    System.out.println("Original vector 1: x=" + vector1.xyz.d1 + ", y=" + vector1.xyz.d2 + ", z=" + vector1.xyz.d3);
	    System.out.println("Original vector 2: x=" + vector2.xyz.d1 + ", y=" + vector2.xyz.d2 + ", z=" + vector2.xyz.d3);
	    System.out.println("Test result: " + (vector1.equals(new Vector(1, 2, 3)) && vector2.equals(new Vector(4, 5, 6)) ? "PASS" : "FAIL"));
	    System.out.println();
	}


	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	
	void testLengthSquared() {
		// Create a Vector object with xyz values
	    Vector vector = new Vector(3, 4, 5);

	    // Call the lengthSquared method on the Vector object
	    double result = vector.lengthSquared();

	    // Expected result
	    double xx = vector.xyz.d1 * vector.xyz.d1;
	    double yy = vector.xyz.d2 * vector.xyz.d2;
	    double zz = vector.xyz.d3 * vector.xyz.d3;
	    double expected = xx + yy + zz;

	    // Test case 1: Check if the length squared result is correct
	    System.out.println("Test case 1: Check if the length squared result is correct");
	    System.out.println("Expected result: " + expected);
	    System.out.println("Actual result: " + result);
	    System.out.println("Test result: " + (result == expected ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vector is unchanged
	    System.out.println("Test case 2: Check if the original vector is unchanged");
	    System.out.println("Original vector: x=" + vector.xyz.d1 + ", y=" + vector.xyz.d2 + ", z=" + vector.xyz.d3);
	    System.out.println("Test result: " + (vector.equals(new Vector(3, 4, 5)) ? "PASS" : "FAIL"));
	    System.out.println();	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	
	void testLength() {
		// Create a Vector object with xyz values
	    Vector vector = new Vector(3, 4, 5);

	    // Call the length method on the Vector object
	    double result = vector.length();

	    // Expected result
	    double expected = Math.sqrt(vector.lengthSquared());

	    // Test case 1: Check if the length result is correct
	    System.out.println("Test case 1: Check if the length result is correct");
	    System.out.println("Expected result: " + expected);
	    System.out.println("Actual result: " + result);
	    System.out.println("Test result: " + (result == expected ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vector is unchanged
	    System.out.println("Test case 2: Check if the original vector is unchanged");
	    System.out.println("Original vector: x=" + vector.xyz.d1 + ", y=" + vector.xyz.d2 + ", z=" + vector.xyz.d3);
	    System.out.println("Test result: " + (vector.equals(new Vector(3, 4, 5)) ? "PASS" : "FAIL"));
	    System.out.println();	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	
	void testNormalize() {
		 // Create a Vector object with xyz values
	    Vector vector = new Vector(3, 4, 5);

	    // Call the normalize method on the Vector object
	    Vector result = vector.normalize();

	    // Expected result
	    double len = vector.length();
	    Vector expected = new Vector(vector.xyz.d1 / len, vector.xyz.d2 / len, vector.xyz.d3 / len);

	    // Test case 1: Check if the normalized vector is correct
	    System.out.println("Test case 1: Check if the normalized vector is correct");
	    System.out.println("Expected result: x=" + expected.xyz.d1 + ", y=" + expected.xyz.d2 + ", z=" + expected.xyz.d3);
	    System.out.println("Actual result: x=" + result.xyz.d1 + ", y=" + result.xyz.d2 + ", z=" + result.xyz.d3);
	    System.out.println("Test result: " + (result.equals(expected) ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vector is unchanged
	    System.out.println("Test case 2: Check if the original vector is unchanged");
	    System.out.println("Original vector: x=" + vector.xyz.d1 + ", y=" + vector.xyz.d2 + ", z=" + vector.xyz.d3);
	    System.out.println("Test result: " + (vector.equals(new Vector(3, 4, 5)) ? "PASS" : "FAIL"));
	    System.out.println();	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	void testSubtractVector() {
		// Create two Vector objects
	    Vector vector1 = new Vector(3, 4, 5);
	    Vector vector2 = new Vector(1, 2, 3);

	    // Call the subtract method on vector1 with vector2 as argument
	    Vector result = vector1.subtract(vector2);

	    // Expected result
	    Vector expected = new Vector(2, 2, 2);

	    // Test case 1: Check if the subtracted vector is correct
	    System.out.println("Test case 1: Check if the subtracted vector is correct");
	    System.out.println("Expected result: x=" + expected.xyz.d1 + ", y=" + expected.xyz.d2 + ", z=" + expected.xyz.d3);
	    System.out.println("Actual result: x=" + result.xyz.d1 + ", y=" + result.xyz.d2 + ", z=" + result.xyz.d3);
	    System.out.println("Test result: " + (result.equals(expected) ? "PASS" : "FAIL"));
	    System.out.println();

	    // Test case 2: Check if the original vectors are unchanged
	    System.out.println("Test case 2: Check if the original vectors are unchanged");
	    System.out.println("Original vector1: x=" + vector1.xyz.d1 + ", y=" + vector1.xyz.d2 + ", z=" + vector1.xyz.d3);
	    System.out.println("Original vector2: x=" + vector2.xyz.d1 + ", y=" + vector2.xyz.d2 + ", z=" + vector2.xyz.d3);
	    System.out.println("Test result: " + (vector1.equals(new Vector(3, 4, 5)) && vector2.equals(new Vector(1, 2, 3)) ? "PASS" : "FAIL"));
	    System.out.println();
	    }

}
