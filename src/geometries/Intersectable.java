package geometries;

import primitives.Ray;

import java.util.List;

import primitives.Point;
/**
 * Interface for Intsersections
 * @author Linoy Cohen and Yedida Cohen
 *
 */
public abstract class Intersectable 
{
	/**
	 * Static Internal Auxiliary Department (as a completely passive data structure - PDS)
	 * 
     * @author Linoy Cohen and Yedida Cohen
	 * @param geometry Geometry value
	 * @param point Point value
	 * */
	public static class GeoPoint
	{
	    public  Geometry geometry;
	    public  Point point;
	    
	    /**
	     * constructor for geo point
	     * 
         * @author Linoy Cohen and Yedida Cohen
	     * @param geometry Geometry
	     * @param point Point
	     * */
	    public GeoPoint(Geometry geometry,Point point)
	    {
	    	this.geometry = geometry;
	    	this.point = point;
	    }
	    
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof GeoPoint)) return false;
			GeoPoint other = (GeoPoint)obj;
			return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
		
	}
	/***
	 * 
	 * @param ray
	 * @return the cutting between ray and object. 
	 */
	public  List<Point> findIntsersections(Ray ray)
    {		
		    var geoList = findGeoIntersections(ray);
		    return geoList == null ? null
		                           : geoList.stream().map(gp -> gp.point).toList();
		
    }
	
	public List<GeoPoint> findGeoIntersections (Ray ray)
	{
		return findGeoIntersectionsHelper(ray)	;
		
	}
	
	protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	 {
			return null;
	 }

}