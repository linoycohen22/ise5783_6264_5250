package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere {

	Point center;
	double radius;

	/**
	 * sphere constructor
	 */
	public Sphere(Point p, double r){
		center=p;
		radius=r;
	}

	public Vector getNormal(Point p) {
		return null;
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}
}