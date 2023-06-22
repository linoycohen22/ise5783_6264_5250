package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * interface to LightSource
 * 
 *@author linoy and yedida
 */

public interface LightSource {

	/**
	 * A function that return the intensity at a point
	 * 
	 * @author linoy and yedida
	 * @param p Point value
	 * @return intensity color in this point
	 */
	public Color getIntensity(Point p);
	/**
	 * A function that return the  vector L of the lighting direction at a point
	 * 
	 * @author linoy and yedida
	 * @param p Point value
	 * @return the lighting direction on a point
	 */
	public Vector getL(Point p);
	
    double getDistance(Point point);

}
