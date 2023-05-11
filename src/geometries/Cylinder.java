package geometries;

import java.util.List;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Cylinder extends Tube{

	double height;

	/**
	 * cylinder constructor
	 * @param h
	 * @param rad
	 * @param ray
	 */
	public Cylinder(double h, double rad, Ray ray){
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

	@Override
	public List<Point> findIntsersections(Ray ray) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}