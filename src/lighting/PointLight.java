package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
	private Point position;
	private double KC = 1;
	private double KL = 0;
	private double KQ = 0;

	
	/**
	 * constructor of PointLight that receives two params
	 * kc,kl,kq are with default value
	 * 
	 * @author linoy and yedida
	 * @param intensity Color value
	 * @param position Point3D value
	 */
	public PointLight(Color intensity, Point position)
	{
		super(intensity);
		this.position = position;
	}
	
	
	
	/**
	 * setter to filed position
	 * 
	 * @author linoy and yedida
	 * @param position the position to set
	 * @return the object - builder
	 */
	public PointLight setPosition(Point position) 
	{
		this.position = position;
		return this;
	}	
	/**
	 * setter to filed kc
	 * 
	 * @author linoy and yedida
	 * @param kC the kC to set
	 * @return the object - builder
	 */
	public PointLight setKC(double kC) 
	{
		KC = kC;
		return this;
	}

	/**
	 * setter to filed kl
	 * 
	 * @author linoy and yedida
	 * @param kL the kL to set
	 * @return the object - builder
	 */
	public PointLight setKL(double kL) 
	{
		KL = kL;
		return this;
	}


	/**
	 * setter to filed kq
	 * 
	 * @author linoy and yedida
	 * @param kQ the kQ to set
	 * @return the object - builder
	 */
	public PointLight setKQ(double kQ) 
	{
		KQ = kQ;
		return this;
	}
	
	@Override
	public Color getIntensity(Point p)
	{
		return getIntensity().reduce((KC + KL * p.distance(position)+ KQ * p.distanceSquared(position)));	
	}
	/**
	 * A function that return the  vector L of the lighting direction at a point
	 * 
	 * @author linoy and yedida
	 * @param p Point3D value
	 * @return the lighting direction on a point
	 */
	@Override
	public Vector getL(Point p)
	{
		if (p.equals(position))
			return null; //In order not to reach a state of exception due to the zero vector
		return p.subtract(position).normalize();
	}
	
}
