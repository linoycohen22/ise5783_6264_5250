package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
	private Color Ia; //the color
	private double Ka; //מקדם ההנחתה
	private Color intensity;
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
	/**
	 * constructor that save the intensity=Ia*Ka
	 * 
	 * @author linoy and yedida
	 * @param Ia Color value
	 * @param Ka double value
	 */
	public AmbientLight(Color Ia,double Ka ) 
	{
		this.intensity = Ia.scale(Ka);
	}

	public AmbientLight(Color Ia,Double3 Ka ) 
	{
		this.intensity = Ia.scale(Ka);
	}
    public Color getIntensity() {
        return intensity;
    }
}
