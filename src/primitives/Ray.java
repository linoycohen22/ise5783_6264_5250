package primitives;
import java.util.List;
import geometries.Intersectable.GeoPoint;


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
	    return points == null || points.isEmpty() ? null
	           : getClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}
	/**
	 * The function returns the point closest to the beginning of the beam
	 * from all the intersection points of the resulting list.
	 * 
	 * @author linoy and yedida
	 * @param points List<GeoPoint> value
	 * @return Point value
	 * */
	
	public GeoPoint getClosestGeoPoint(List<GeoPoint> intersections)
	{
		
		if(intersections == null)
			return null;
		GeoPoint closet = intersections.get(0);
		for (GeoPoint geoPoint : intersections) 
		{
			if(geoPoint.point.distance(p0) < closet.point.distance(p0))
				closet= geoPoint;
			
		}
		return closet;
	}
}
