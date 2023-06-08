package geometries;

import primitives.Vector;

import java.util.List;
import static primitives.Util.*;

import primitives.Point;
import primitives.Ray;

public class Sphere extends Geometry {
	Point point;
	double radius;

	public Point getPoint() {
		return point;
	}
	public double getRadius() {
		return radius;
	}
	public Sphere(Point point,double radius) {
		this.point=point;
		if(radius<0)
			throw new IllegalArgumentException("the radius isnt correct");
		this.radius=radius;
	}
    public Vector getNormal(Point point) {
		return point.subtract(getPoint()).normalize();
	}
    @Override
    public String toString() {
    	return "Point:"+point.toString()+" "+"radius"+radius;
    }
	
	@Override
	protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	{
		if (ray.getp0().equals(point)) // if the begin of the ray in the center, the point, is on the radius
			return List.of(new GeoPoint(this,ray.getPoint(radius)));
		//List<Point3D> rayPoints = new ArrayList<Point3D>();
		Vector u = point.subtract(ray.getp0());
		double tM = alignZero(ray.getDir().dotProduct(u));
		double d = alignZero(Math.sqrt(u.length()*u.length()- tM * tM));
		double tH = alignZero(Math.sqrt(radius*radius - d*d));
		double t1 = alignZero(tM+tH);
		double t2 = alignZero(tM-tH);
		
		
		if (d > radius)
			return null; // there are no instructions

		
		if (t1 <=0 && t2<=0)
			return null;
		
		if (t1 > 0 && t2 >0)
			return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
		if (t1 > 0)
		{
			return List.of(new GeoPoint(this,ray.getPoint(t1)));
		}

		else
			return List.of(new GeoPoint(this,ray.getPoint(t2)));
	}
}
