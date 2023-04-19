package primitives;

public class Point {

	final Double3 xyz;

	/**
	 * point constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(double x, double y, double z){
		xyz = new Double3(x, y, z);
	}

	/**
	 * overrides 'equals'
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point)obj;
		return this.xyz.equals(other.xyz);
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {

		return "Point [xyz=" + xyz + "]";
	}

	/**
	 * returns the result of the addition of vector 'v' to the current point
	 * @param v
	 * @return
	 */
	public Point add(Vector v) {
		Double3 d3Ret = this.xyz.add(v.xyz);
		Point pReturn = new Point(d3Ret.d1, d3Ret.d2, d3Ret.d3);
		return pReturn;
	}

	/**
	 * returns the result of the difference between point 'p' and the current point, which is a vector from 'p' to the current
	 * @param p
	 * @return
	 */
	public Vector subtract(Point p) {
		Double3 d3Ret = p.xyz.subtract(this.xyz);
		Vector vReturn = new Vector(d3Ret.d1, d3Ret.d2, d3Ret.d3);
		return vReturn;
	}

	/**
	 * returns the squared distance between 2 points
	 */
	public double distanceSquared(Point p) {
		double xx = this.xyz.d1 - p.xyz.d1;
		double yy = this.xyz.d2 - p.xyz.d2;
		double zz = this.xyz.d3 - p.xyz.d3;
		return (xx * xx) + (yy * yy) + (zz * zz);
	}

	/**
	 * returns the distance between 2 points
	 */
	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}
}



















