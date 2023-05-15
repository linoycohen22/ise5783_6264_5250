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
	public Plane(Point point1,Point point2,Point point3)
	{
		
		if (point1.equals(point2)|| point2.equals(point3)|| point3.equals(point1))//Check if two points coalesce
			throw new IllegalArgumentException("Two points converge");
		this.q0 = point1;
		Vector myVec1=(point2.subtract(point1));// vector p2p1
		Vector myVec2=(point3.subtract(point1));//vector p3p1	
		this.normal = myVec1.crossProduct(myVec2).normalize();
				
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