package geometries;
import static primitives.Util.*;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Plane implements Geometry  {

	Point q0;
	Vector normal;

	/**
	 * plane constructor #1
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point p1, Point p2, Point p3){
		q0 = p1;
		/*Vector v21 = p2.subtract(p1);//calculate 2 vectors of the plane
		Vector v31 = p3.subtract(p1);
		normal = v21.crossProduct(v31).normalize();*/
		normal = null;
	}
	/**
	 * plane constructor #2
	 */
	public Plane(Point p, Vector norm){
		q0 = p;
		normal = norm.normalize();
	}

	/**
	 * returns a copy of 'q0'
	 */
	public Point getQ0() {
		return this.q0;
	}

	/**
	 * returns a copy of 'normal'
	 */
	public Vector getNormal() {
		return this.normal;
	}

	/**
	 * returns the normal to the plane un point 'p'
	 * @param p
	 * @return
	 */
	public Vector getNormal(Point p) {
		return getNormal();//all points of a flat plane have the same normal!!
	}
	/**
	 * getter for p0
	 * @return p0
	 */
	public Point getq0()
	{
		return q0;
	}

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}
	
	/**
	 * A function that find all the intersection points in the plane
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * */
	
	public List<Point> findIntersections(Ray ray) throws Exception 
	{
		double nv = normal.dotProduct(ray.getDir());
		if (isZero(nv))
		{
			return null;
		}

		try 
		{
			Vector pSubtractP0 = q0.subtract(ray.getp0());
			double t = alignZero((normal.dotProduct(pSubtractP0))/nv);

			if(t <= 0)
			{
				return null;
			}
			return List.of(ray.getPoint(t));
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
	@Override
	public List<Point> findIntsersections(Ray ray) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}