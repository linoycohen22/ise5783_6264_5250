package lighting;

import primitives.Color;
import static  primitives.Util.alignZero;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight
{
	private Vector direction;
	/**
	 * constructor for spotlight that receives 3 params
	 * 
	 * @author linoy and yedida
	 * @param direction Vector value 
	 * @param intensity Color value
	 * @param position Point3D value
	 */
	public SpotLight(Color intensity, Point position, Vector direction)  
	{
		super(intensity, position);
		this.direction=direction.normalize();
	}
	
	@Override
	public Color getIntensity(Point p) throws IllegalArgumentException
	{
		double pl = alignZero(direction.dotProduct(getL(p)));
		if(getL(p) == null)
			return Color.BLACK;
		if (pl <= 0)
			return Color.BLACK;
		return super.getIntensity(p).scale(pl);
	}

	public void setNarrowBeam(double angle) {
//	    // Calculate the new direction vector based on the angle
//	    Vector newDirection = direction.scale(Math.cos(Math.toRadians(angle)));
//	    
//	    // Update the direction vector
//	    direction = newDirection.normalize();
	}
}
