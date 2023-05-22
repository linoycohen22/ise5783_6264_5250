package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * class RayTracerBasic in package renderer
 * 
 * @author Linoy Cohen and Yedida Cohen
 */
public class RayTracerBasic extends RayTracerBase 
{

	/**
	 * constructor
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param myscene Scene value
	 */
	
	public RayTracerBasic(Scene myscene) 
	{
		super(myscene);
		//return null;	
	}
	/**
	 * Function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @author Tamar Gavrieli & Odeya Sadoun
	 * @param ray Ray value
	 * @return Color
	 * @throws Exception

	 *  */
	public Color traceRay(Ray ray) throws Exception
	{
			List<Point> intersections = myscene.geometries.findIntsersections(ray);
			if(intersections == null)
				return  myscene.background;
			Point closestPoint = ray.findClosestPoint(intersections);
			return calcColor(closestPoint);
	}
	/**
	 * Function for calculating a point color	
	 * 
	 * @author Tamar Gavrieli & Odeya Sadoun
	 * @param point Point3D value
	 * @return Color
	 * */
	private Color calcColor(Point point)
	{
		return myscene.ambientLight.getIntensity();
	}
	
}
