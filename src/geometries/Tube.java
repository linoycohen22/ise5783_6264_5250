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

public class Tube implements Geometry {

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
		return null;
	}
	

	/**
	 * overrides 'toString'
	 */
	@Override
	public String toString() {
		return "Tube [radius=" + radius + ", ray=" + ray + "]";
	}
	
	public List<Point> findIntersections(Ray ray) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> findIntsersections(Ray ray) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}