package geometries;
import primitives.Vector;
import primitives.Point;
/**
 * interface Geometry for all the geometries that have normal
 * 
 * @author Linoy Cohen and Yedida Cohen
 * */
public interface Geometry extends Intersectable
{
	/**
	 * A function that return the normal of the geometry
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param point
	 * @return Vector value
	 * */
	Vector getNormal(Point point) throws Exception; // function get normal

}