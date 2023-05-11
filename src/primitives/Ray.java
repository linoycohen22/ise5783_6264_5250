package primitives;
import java.util.List;
 

public class Ray {

	Point p0;
	Vector dir;

	/**
	 * ray constructor
	 */
	public Ray(Point p,Vector d){
		p0 = p;
		dir = d.normalize();
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
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return this.dir.equals(other.dir) && this.p0.equals(other.p0);
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}
	public Point getPoint(double t) throws IllegalArgumentException
	{
		return p0.add(dir.scale(t));
	}
	public Vector getDir() 
	{
		return dir.normalize();
	}
	
	public Point getp0() {
		// TODO Auto-generated method stub
		return p0;
	}
}
