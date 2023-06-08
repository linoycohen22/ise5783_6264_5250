package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class for Tube in geometries package
 * 
 * @author Linoy Cohen and Yedida Cohen
 * Class for Tube
 */

public class Tube extends Geometry {

	double radius;
	Ray ray;

	/**
	 * Constructor that receives radius and ray
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param radius double
	 * @param ray Ray
	 * */
	public Tube(double rad, Ray ray){
		this.radius=rad;
		this.ray=ray;
	}

	public Vector getNormal(Point p) {
		double t=ray.getDir().dotProduct(p.subtract(ray.getp0()));
		Point pointO =ray.getp0().add(ray.getDir().scale(t));
		Vector myVec=p.subtract(pointO);
		return myVec.normalize();
	}
	

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Tube [radius=" + radius + ", ray=" + ray + "]";
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	{
  	return null;

	}


}