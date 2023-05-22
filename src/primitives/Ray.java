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
	/**
	 * The function returns the point closest to the beginning of the beam
	 * from all the intersection points of the resulting list.
	 * 
	 * @author linoy and yedida
	 * @param points List<Point> value
	 * @return Point value
	 * */
	
	public Point findClosestPoint(List<Point> points) 
	{
		if(points == null)
			return null;
		Point closet = points.get(0);
		for (Point point3d : points) 
		{
			if(point3d.distance(p0) < closet.distance(p0))
				closet= point3d;
		}
		return closet;
	}
}
