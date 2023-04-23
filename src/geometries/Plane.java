package geometries;


import primitives.Point;
import primitives.Vector;

public class Plane {

	Point q0;
	Vector normal;

	/**
	 * plane constructor #1
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point p1, Point p2, Point p3){
		q0 = p1;
		/*Vector v21 = p2.subtract(p1);//calculate 2 vectors of the plane
		Vector v31 = p3.subtract(p1);
		normal = v21.crossProduct(v31).normalize();*/
		normal = null;
	}
	/**
	 * plane constructor #2
	 */
	public Plane(Point p, Vector norm){
		q0 = p;
		normal = norm.normalize();
	}

	/**
	 * returns a copy of 'q0'
	 */
	public Point getQ0() {
		return this.q0;
	}

	/**
	 * returns a copy of 'normal'
	 */
	public Vector getNormal() {
		return this.normal;
	}

	/**
	 * returns the normal to the plane un point 'p'
	 * @param p
	 * @return
	 */
	public Vector getNormal(Point p) {
		return getNormal();//all points of a flat plane have the same normal!!
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}
}