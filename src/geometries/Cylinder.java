package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
//an empty line
public class Cylinder extends Tube implements Geometry{

	double height;

	/**
	 * cylinder constructor
	 * @param h
	 * @param rad
	 * @param ray
	 */
	Cylinder(double h, double rad, Ray ray){
		super(rad, ray);
		this.height=h;
	}

	public Vector getNormal(Point p) {
		return null;
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Cylinder [height=" + height + "]";
	}
}