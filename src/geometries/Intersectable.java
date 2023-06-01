package geometries;

import primitives.Ray;

import java.util.List;

import primitives.Point;
/**
 * Interface for Intsersections
 * @author Linoy Cohen and Yedida Cohen
 *
 */
public interface Intersectable 
{
	/**
	 * A function that return all the intersection point with geometry
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param ray
	 * @return List<Point>
	 * @throws Exception
	 * */
	List<Point> findIntsersections(Ray ray)throws Exception;
}