package geometries;
import static primitives.Util.alignZero;

import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * @author linoy cohen and yedida cohen
 * Class for Triangle
 */
public class Triangle extends Polygon implements Geometry
{
	/**
	 * Constructor that receives 3 points and calls to the constructor of the base class
	 * 
	 * @param p1 Point3D
	 * @param p2 Point3D
	 * @param p3 Point3D
	 * @throws Exception 
	 * */
	public Triangle(Point p1,Point p2,Point p3) throws Exception 
	{
		super(p1, p2, p3);

	}
	public String toString() 
	{
		return "Triangle: "+super.toString();
	}
	@Override
	public List<Point> findIntersections(Ray ray) throws Exception 
	{
		List<Point> rayPoints = plane.findIntersections(ray);
		if (rayPoints == null)
			return null;
		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getp0());
		Vector v2 = vertices.get(1).subtract(ray.getp0());
		Vector v3 = vertices.get(2).subtract(ray.getp0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		//The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)

		if (alignZero(n1.dotProduct(plane.getNormal())) > 0 && alignZero(n2.dotProduct(plane.getNormal())) > 0 && alignZero(n3.dotProduct(plane.getNormal())) > 0)
		if (alignZero(n1.dotProduct(ray.getDir())) > 0 && alignZero(n2.dotProduct(ray.getDir())) > 0 && alignZero(n3.dotProduct(ray.getDir())) > 0)
		{
			return rayPoints;
		}
		else if (alignZero(n1.dotProduct(plane.getNormal())) < 0 && alignZero(n2.dotProduct(plane.getNormal())) < 0 && alignZero(n3.dotProduct(plane.getNormal())) < 0)
	     if (alignZero(n1.dotProduct(ray.getDir())) < 0 && alignZero(n2.dotProduct(ray.getDir())) < 0 && alignZero(n3.dotProduct(ray.getDir())) < 0)
		{
			return rayPoints;
		}
		return null; //there is no instruction point
	}
	@Override
	public List<Point> findIntsersections(Ray ray) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
