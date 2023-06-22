package renderer;
import java.util.List;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    /**
     * @param sc
     * Ctor using super class constructor
     */
    public RayTracerBasic(Scene sc) {
        super(sc);
    }

    private static final double INITIAL_K = 1.0;
   // private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
  //max recursion for super-sampling process
  	private int maxRecSampling;
  	
  	/**
  	 * @param rec
  	 * @return the rayTracerItself to allow concatenate
  	 */
  	public RayTracerBasic setmaxRecSampling(int rec)
  	{
  		maxRecSampling=rec;
  		return this;
  	}
    /**
     * Function that calculates the color for the nearest intersection point,
     * if no intersection points are returned the color of the background
     * @param ray Ray value
     * @return Color
     **/
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? myscene.background : calcColor(closestPoint, ray);
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray)
    {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(myscene.ambientLight.getIntensity());
    }



    /**
     * Function for calculating a point color
     *
     * @return Color
     * */

    public Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = point.geometry.getEmission();
        color = color.add(calcLocalEffects(point, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));
    }

    /**
     * calc Global Effects
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return The color with the global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
       /* Vector n = gp.geometry.getNormal(gp.point);*/
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.Kr.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point, ray), level, material.Kr, kkr);

        Double3 kkt = material.Kt.product(k);;
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.geometry.getNormal(ray.getp0()), gp.point, ray), level, material.Kt, kkt));

        return color;
    }

    /**
     * help func for calcGlobalEffects
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? myscene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }


    /**
     * Calculate the effects of lights
     *
     * @param intersection
     * @param ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().KD;
        Double3 ks = intersection.geometry.getMaterial().KS;
        Color color = Color.BLACK;
        for (LightSource lightSource : myscene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if sign(nl) == sing(nv)
                //if (unshaded(lightSource, l, n, intersection))
                Double3 ktr = transparency(lightSource, l, n, intersection);
                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * help function that calculate the difusive effect
     *
	 * @author Linoy Cohen and Yedida Cohen
     * @param kd Double3 value
     * @param l Vector value

     * @return double value for calcDiffusive
     * */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }

    /**
     * Calculate specular light
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1.0).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }

    /**
     * search for shadow shape
     * @param l from light source to point
     * @param n the normal
     * @param geopoint the GeoPoint
     * @return if the point is unshaded. it means- if there is no shade on it- it should have light.
     */
   // private static final double EPS = 0.1;

   /* private boolean unshaded(Vector l, Vector n, GeoPoint geopoint, LightSource ls)
    {
        Vector lightDirection = l.scale(-1.0); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = myscene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;

        double lightDistance = ls.getDistance(geopoint.point);
        for (GeoPoint geop : intersections) {
            if (alignZero(geop.point.distance(geopoint.point) - lightDistance) <= 0)
                if(geopoint.geometry.getMaterial().Kt.equals(Double3.ZERO))
                    return false;
        }
        return true; //in case all intersections are in between lightDistance and gp.
    }*/


    /**
     * this func returns a new ray- the refracted ray comes from the point because of the light- inRay
     * @param pointGeo
     * @param inRay
     * @param n
     * @return RefractedRay
     */
   /* private Ray constructRefractedRay(Point pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDir(), n);
    }*/


    /**
     * Calculate the reflection ray
     * @param n
     * @param point
     * @param inRay
     * @return The new ray after the reflection calculate
     */
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        Vector r = v.subtract(n.scale(alignZero(2 * (n.dotProduct(v)))));
        return new Ray( point,r.normalize(), n);
    }

    /**
     * Calculate the refracted ray
     * @param n
     * @param point
     * @param inRay
     * @return The new ray refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray( point,inRay.getDir(), n);
    }

    /**
     * Gets the discount-factor of the half or full shading on the intersection-geoPoint regarding one of the light-sources.
     * @param light the light-source
     * @param l the light-vector of the light-source
     * @param n the intersected-geometry's normal
     * @param geoPoint the intersection-geoPoint
     * @return The discount-factor of the shading on this intersection-geoPoint
     */
    private Double3 transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1.0); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection,n);
        List<GeoPoint> intersections = myscene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return new Double3(1);
        Double3 ktr = new Double3(1);
        double lightDistance = light.getDistance(geoPoint.point);
        for (GeoPoint gp : intersections) {
            if(Util.alignZero(gp.point.distance(geoPoint.point)-lightDistance)<=0) {
                ktr= ktr.product(gp.geometry.getMaterial().Kt);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
	 * @param list of rays
	 * @return the color of the pixel that the rays pass through it- the average color calculated by the intersection points of the rays.
	 */
	public Color traceRays(List<Ray> rays)
	{		
		int count =1;
		Ray r = rays.remove(0);
		
		GeoPoint closestPoint = findClosestIntersection(r);
		Color color = (closestPoint == null ? myscene.background: calcColor(closestPoint, r));//if no intersection- return background color
		for(Ray ray: rays)
		{
			closestPoint = findClosestIntersection(ray);
			Color c = closestPoint == null ? myscene.background: calcColor(closestPoint, ray);
			if(!(c.getColor().equals(color.getColor())))
			{
				color = color.add(c);//add the color created by this ray's intersection
				count++;
			}
		}
		return color.reduce(count);//reduce by the count of rays- to find the average color
	}

    /**
     * @param ray
     * @return The closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = myscene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.getClosestGeoPoint(intersections);
    }
    /**
	 * recursive function that calls itself,
	 * which calculate the color of the pixel, 
	 * by checking if all corners are equal, if not 
	 * -calculate the color of each quarter 
	 * 
	 * @param points
	 * @param level
	 * @param focalPoint
	 * @return color pixel
	 */
	public Color calcColorPixel4(List<Point> points, int level, Point focalPoint, double width, double height, Vector vUp, Vector vRight, Camera camera)
	{
		List<Ray> rays = camera.constructRaysThroughPixel(points, focalPoint);
		Ray r = rays.remove(0);
		GeoPoint closestPoint = findClosestIntersection(r);
		Color cr = (closestPoint == null ? myscene.background: calcColor(closestPoint, r));
		
		//the level starts from 1 and grows till maxRecSampling (set by user) 
		//if passed the max level of sampling recursion- return the color.
		if(level>=maxRecSampling)
		{
			return cr;
		}
		
		//the color is reduced by 4-> because of dividing to 4 points in the edges
		cr.reduce(4);
		Color color = cr;
		for(Ray ray:rays)
		{
			closestPoint = findClosestIntersection(ray);
			Color c = (closestPoint == null ? myscene.background: calcColor(closestPoint, ray)).reduce(4);
			
			//if the color in the points is not equal- keep sending rays to corners of quarters
			if(!c.getColor().equals(cr.getColor()))
			{
				//the center points of new quarters:
				List<Point> centerP = camera.findCenterNewPixels(points, vUp, vRight);
				Color colors = new Color(0,0,0);
				for(Point p: centerP)
				{
					//get the new points- the quarters edges. each time the num of points grows bechezkat 2 in the relevant quarter.
					List<Point> newPoints = camera.getPointsPixel(p, width/(Math.pow(2, level)), height/(Math.pow(2, level)), vUp, vRight);
					colors = colors.add(calcColorPixel4(newPoints, level+1, focalPoint, width, height, vUp, vRight, camera).reduce(4));
				}
				return colors;
			}
			color = color.add(c);// add the calculated colors to the result color
		}
		return color;
	}
}

