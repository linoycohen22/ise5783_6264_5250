package lighting;
import primitives.Color;
import primitives.Point;

public class Light {
	 
	private Color intensity;
	   /**
		 * constructor for light
		 * 
		 * @author linoy and yedida
		 * @return the intensity
		 */
		protected Light(Color intensity)
		{
			this.intensity=intensity;
		}
		/**
		 * getter to intensity
		 * 
		 * @author linoy and yedida
		 * @return intensity Color 
		 * */
		public Color getIntensity() 
		{
			return intensity;
		}
		

}
