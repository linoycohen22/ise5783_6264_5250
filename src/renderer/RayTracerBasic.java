package renderer;

import java.util.List;

import primitives.*;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

/**
 * class RayTracerBasic in package renderer
 * 
 * @author Linoy Cohen and Yedida Cohen
 */
public class RayTracerBasic extends RayTracerBase
{
	/**
	 * constructor of RayTracerBasic
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param myscene Scene value
	 */
	public RayTracerBasic(Scene myscene) 
	{
		super(myscene);
	}

	
	@Override
	public Color traceRay(Ray ray) 
	{
		var intersections = myscene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return myscene.background;
		GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}
	
	
	private Color calcLocalEffects(GeoPoint intersection, Ray ray)
	{
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
		Double3 kd = intersection.geometry.getMaterial().KD , ks = intersection.geometry.getMaterial().KS;
		Color color = Color.BLACK;
		
		for (LightSource lightSource : myscene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}
	/**
	 * Function for calculating a point color - recursive function	
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param point Point3D value
	 * @return Color
	 * @throws IllegalArgumentException 
	 * */
	

	private Color calcColor(GeoPoint geo, Ray ray) {
		return myscene.ambientLight.getIntensity().add(geo.geometry.getEmission()).add(calcLocalEffects(geo, ray));
	}
	
	/*private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale(Math.abs(l.dotProduct(n))*(kd));
	}*/
	
	private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.add(n.scale(-2*n.dotProduct(l)));
		double result = -Util.alignZero(v.dotProduct(r));
		if (result <= 0)
			return Color.BLACK;
		return lightIntensity.scale(ks.scale(Math.pow(result, nShininess)));

	}
	/**
	 * help function that calculate the difusive effect
	 * 
	 * @author Linoy Cohen and Yedida Cohen
	 * @param kd double value
	 * @param nl double value
	 * @param lightIntensity Color value
	 * @return double value for calcDiffusive
	 * */
	private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) 
	{
		return lightIntensity.scale(kd.scale(Math.abs(l.dotProduct(n))));
	}
}