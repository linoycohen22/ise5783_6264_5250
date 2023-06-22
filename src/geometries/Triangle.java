package geometries;
import static primitives.Util.*;

import java.util.List;
import primitives.*;

/**
 * @author linoy cohen and yedida cohen
 * Class for Triangle
 */
public class Triangle extends Polygon 
{
	/**
	 * Constructor that receives 3 points and calls to the constructor of the base class
	 * 
	 * @param p1 Point
	 * @param p2 Point
	 * @param p3 Point
	 * @throws Exception 
	 * */
	public Triangle(Point p1,Point p2,Point p3) //throws Exception 
	{
		super(p1, p2, p3);

	}
	public String toString() 
	{
		return "Triangle: "+super.toString();
	}
	
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxdistance) {
		//get Intersections of plane
		List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray);
		//double nv = vector.dotProduct(ray.getDir());
		

		if (planeIntersections == null)
			return null;

		Point p0 = ray.getp0();
		Vector rayDir = ray.getDir();

		//all the vectors ( (v1-p0)x(v2-p0) ) * (ray dir)  should be the same signe
		// else the ray outside the polygon

		//first check the sign of dot product the last and the first
		Vector v1 = vertices.get(0).subtract(p0);
		Vector vn = vertices.get(vertices.size() - 1).subtract(p0);

		double s1 = rayDir.dotProduct(vn.crossProduct(v1));

		//if the ray cross in the edge of the polygon
		if (isZero(s1))
			return null;

		double s2; //keep the next product
		Vector v2; //the next vector
		for (var vertex : vertices.subList(1, vertices.size())) {

			v2 = vertex.subtract(p0);
			s2 = rayDir.dotProduct(v1.crossProduct(v2));

			//if the ray cross in the edge of the polygon
			if (isZero(s2)) return null;

			//if they not the same sign
			if( s1 * s2 < 0  )
				return null;

			v1 = v2; //for the next round
		}
		//if the func not return null than we have Intersections with the polygon
		return planeIntersections.stream().map(gp->new GeoPoint(this,gp.point)).toList();
	}	
}
