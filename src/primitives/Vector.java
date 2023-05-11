package primitives;

public class Vector extends Point{

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vector [xyz=" + xyz + "]";
	}

	/**
	 * vector constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public  Vector(Double3 xyz) 
	{
		super(xyz);
		if(xyz.equals(new Double3(0, 0, 0)))
			throw new IllegalArgumentException("The function dosent support the zero vector");
		// TODO Auto-generated constructor stub
	}

  public  Vector(double x, double y, double z) 
	{
		super(x, y, z);
		
		if(xyz.equals(new Double3(0, 0, 0)))
			throw new IllegalArgumentException("The function dosent support the zero vector");
		// TODO Auto-generated constructor stub
	}
	/**
	 * returns the result of the addition of vector 'v' to the current vector
	 */
	public Vector add(Vector v) {
		Point p=new Point(super.add(v).xyz.d1, super.add(v).xyz.d2, super.add(v).xyz.d3);
		Vector vReturn=new Vector(p.xyz.d1, p.xyz.d2, p.xyz.d3);
		return vReturn;
	}

	/**
	 * returns a new vector which is the result of the production of the current vector with scalar 'sca' 
	 * @param sca
	 * @return
	 */
	public Vector scale(double sca) {
		double x = this.xyz.d1 * sca;
		double y = this.xyz.d2 * sca;
		double z = this.xyz.d3 * sca;
		Vector v = new Vector(x, y, z);
		return v;
	}

	/**
	 * returns the result of the cross product of vector 'v' and the current vector
	 */
	public Vector crossProduct(Vector v) {
		double x = this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
		double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3;
		double z = this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1;
		Vector vReturn=new Vector(x, y, z);
		return vReturn;
	}

	/**
	 * returns the result of the dot product of vector 'v' and the current vector
	 */
	public double dotProduct(Vector v) {
		double xx = this.xyz.d1 * v.xyz.d1;
		double yy = this.xyz.d2 * v.xyz.d2;
		double zz = this.xyz.d3 * v.xyz.d3;
		return xx + yy + zz;
	}

	/**
	 * returns the squared length of the current vector
	 */
	public double lengthSquared() {
		return this.dotProduct(this);
	}

	/**
	 * returns the length of the current vector
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * returns a copy of the current vector after normalization
	 */
	public Vector normalize()
	{
		double len = this.length();
		double x = this.xyz.d1 / len;
		double y = this.xyz.d2 / len;
		double z = this.xyz.d3 / len;		
		Vector vReturn = new Vector(x, y, z);
		return vReturn;
	}
	
	public Vector subtract(Vector v) {
		Double3 d3Ret = this.xyz.subtract(v.xyz);
		Vector vReturn = new Vector(d3Ret.d1, d3Ret.d2, d3Ret.d3);
		return vReturn;
	}
}




