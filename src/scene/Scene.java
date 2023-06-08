package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

public class Scene 
{
	
	
	public String name;
	public Color background=Color.BLACK;
	public AmbientLight ambientLight = AmbientLight.NONE;
	public Geometries geometries;
	public List<LightSource>lights=new LinkedList<LightSource>() ;

	/**
	 * constructor 
	 * 
	 * @author linoy & yedida
	 * @param name
	 * */
	public Scene(String name)
	{
		geometries = new Geometries();
	}
	
	
	public Scene setName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * setter function to lights  and return this for builder pattern
	 * 
	 * @author linoy & yedida
	 * @param lights the lights to set
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
	/**
	 * setter function to background, and return this for builder pattern
	 * 
	 * @author linoy & yedida
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) 
	{
		this.background = background;
		return this;
	}

	/**
	 * setter function to ambientLight, and return this for builder pattern
	 * 
	 * @author linoy & yedida
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) 
	{
		this.ambientLight = ambientLight;
		return this;
	}
	/**
	 * setter function to geometries, and return this for builder pattern
	 * 
	 * @author linoy & yedida
	 * @param geometries the geometries to set
	 */
	public Scene setGeometries(Geometries geometries) 
	{
		this.geometries = geometries;
		return this;
	}
	
}