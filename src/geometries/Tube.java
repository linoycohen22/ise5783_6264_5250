package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube {

	double radius;
	Ray ray;

	/**
	 * tube constructor
	 */
	public Tube(double rad, Ray ray){
		this.radius=rad;
		this.ray=ray;
	}

	public Vector getNormal(Point p) {
		return null;
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Tube [radius=" + radius + ", ray=" + ray + "]";
	}
}