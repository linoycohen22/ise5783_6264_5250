package renderer;

import java.util.List;
import primitives.*;
import scene.Scene;

/**
 * class RayTracerBase of renderer package
 * 
 * @author Linoy Cohen and Yedida Cohen
 */
public abstract class RayTracerBase 
{

	protected Scene myscene;
	
	/**
	 * constructor 
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param myscene Scene value	
	 */
	public  RayTracerBase(Scene myscene)
	{
		this.myscene=myscene;
	}
	/**
	 * Statement of an abstract function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param ray Ray value
	 * @throws Exception
	 * @return Color
	 *  */
	public abstract Color traceRay(Ray ray) throws IllegalArgumentException ;
	/**
	 * abstract method
	 * @param list of rays
	 * @return the color of the pixel that the rays pass through it- the average color
	 */
	public abstract Color traceRays(List<Ray> rays);

	/**
	 * abstract method
	 * @param points
	 * @param level
	 * @param focalPoint
	 * @return color of the pixel by recursion for all quarters of pixel
	 */
	public abstract Color calcColorPixel4(List<Point> points, int level, Point focalPoint, double width, double height, Vector vUp, Vector vRight, Camera camera);
	
}