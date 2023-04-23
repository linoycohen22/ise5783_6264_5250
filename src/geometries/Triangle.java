package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Plane implements Geometry{

	/**
	 * triangle constructor
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Triangle(Point p1, Point p2, Point p3){
		super(p1, p2, p3);
	}

	public Vector getNormal(Point p) {
		return null;
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Triangle []";
	}
}