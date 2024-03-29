package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;



/**
 * class DirectionalLight that extends from class light
 * and implement the interface LightSource
 * @author linoy and yedida
 */
public class DirectionalLight extends Light implements LightSource {

	private Vector direction;

	/**
	 * Constructor for DirectionalLight
	 * this c-tor is normalize the direction vector
	 * 
	 * @author linoy and yedida
	 * @param intensity Color value
	 * @param direction Vector value
	 */
	public DirectionalLight(Color intensity, Vector direction) 
	{
		super(intensity);
		this.direction=direction.normalize();
	}

	
	@Override
	public Color getIntensity(Point p)
	{
		return super.getIntensity();
	}
	
	@Override
	public Vector getL(Point p)
	{
		return direction.normalize();
	}
	@Override
	public double getDistance(Point point) 
	{
		return Double.POSITIVE_INFINITY;
	}
	
}
