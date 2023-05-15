package geometries;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable {
	//field:
		private List<Intersectable> geometriesInScene;

		/**
		 * A default constructor that create new empty arrayList
		 * 
		 */
		public Geometries() {
		
			//we chosen in ArrayList because this class works better when the application demands storing the data and accessing it.
			geometriesInScene = new ArrayList<Intersectable>();
		}
		
		/**
		 * Constructor  list of geometries and put them in new arrayList
		 * 
		 * @param geometries
		 * */
		public Geometries(Intersectable... geometries) {
		
			geometriesInScene =  new ArrayList<Intersectable>(Arrays.asList(geometries));
		}
		
		
		/**
		 * A function that add the geometries the receive to the list.
		 * 
		 * @param geometries 
		 * */
		public void add(Intersectable... geometries) {
		
			if (geometries != null)
			{
				geometriesInScene.addAll(Arrays.asList(geometries));
			}
		}
		@Override
		public List<Point> findIntsersections(Ray ray) throws Exception {
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
}
		

