package geometries;
import primitives.*;

/**
 * interface Geometry for all the geometries that have normal
 * 
 * @author Linoy Cohen and Yedida Cohen
 * */

public abstract class Geometry extends Intersectable
//public interface Geometry extends Intersectable
{
	protected  Color emission = Color.BLACK;
	private Material material=new Material();
	/**
	 * getter function for the Material filed in geometry class
	 * 
	 * @author  Linoy Cohen and Yedida Cohen
	 * @return the material
	 */
	public Material getMaterial() 
	{
		return material;
	}

	/**
	 * setter function for the Material filed 
	 * 
	 * @author  Linoy Cohen and Yedida Cohen
	 * @param material the material to set
	 * @return the object - builder
	 */
	public Geometry setMaterial(Material material) 
	{
		this.material = material;
		return this;
	}
	
	
	/**
	 * A function that return the normal of the geometry
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param point
	 * @return Vector value
	 * */
	public abstract Vector getNormal(Point point) throws IllegalArgumentException; // function get normal
	/**
	 * getter function for the color filed in geometry class
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @return emission Color value
	 * */
	public Color getEmission() 
	{
		return emission;
	}
	/**
	 * setter function for the color filed and return this- geometry class
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @return the geometry-this
	 * */
	public Geometry setEmission(Color emission) 
	{
		this.emission = emission;
		return this;
	}
}