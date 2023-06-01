package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import primitives.Point;
import primitives.Ray;

/**
 * Class for the Collection of Bodies in the Scene
 * 
 * @author Linoy Cohen and Yedida Cohen
 */
public class Geometries implements Intersectable 
{
	//field:
	private List<Intersectable> geometriesInScene;

	/**
	 * A default constructor that create new empty arrayList
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 */
	public Geometries()
	{
		//we chosen in ArrayList because this class works better when the application demands storing the data and accessing it.
		geometriesInScene = new ArrayList<Intersectable>();
	}
	
	/**
	 * Constructor that recives list of geomeries and put them in new arrayList
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * */
	public Geometries(Intersectable... geometries)
	{
		geometriesInScene =  new ArrayList<Intersectable>(Arrays.asList(geometries));
	}
	
	/**
	 * Iterator function
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * */
	public  Iterator<Intersectable> iterator()
	{
		return geometriesInScene.iterator();
	}
	
	/**
	 * A function that add the geometries the receive to the list.
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * */
	public void add(Intersectable... geometries)
	{
		if (geometries != null)
		{
			geometriesInScene.addAll(Arrays.asList(geometries));
		}
	}
	
	/**
	 * 
	 * A function that find all the intersection points in the scene
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @return List<Point> for the points
	 * @throws Exception 
	 * */

	@Override
	public List<Point> findIntsersections(Ray ray) throws Exception 
	{
		List<Point> temp = new ArrayList<Point>();
		for (Intersectable intersectable : geometriesInScene) 
		{
			List<Point> intersection = intersectable.findIntsersections(ray);
			if (intersection != null)
				temp.addAll(intersection); 
		}
		
		if (temp.isEmpty())
			return null;
		return temp;	
	}
	
	/**
	 * A getter function for geometriesInScene
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @return List<Intersectable> value for geometriesInScene
	public List<Intersectable> getIntsersectionPoints() 
	{
		return geometriesInScene;
	}
	
	/***********************************************************************************/
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geometriesInScene == null) ? 0 : geometriesInScene.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometries other = (Geometries) obj;
		if (geometriesInScene == null) {
			if (other.geometriesInScene != null)
				return false;
		} else if (!geometriesInScene.equals(other.geometriesInScene))
			return false;
		return true;
	}

}
