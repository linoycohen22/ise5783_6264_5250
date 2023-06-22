package geometries;


import primitives.*;

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

}