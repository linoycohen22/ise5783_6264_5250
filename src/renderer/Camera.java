package renderer;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static  primitives.Util.isZero;

import primitives.*;

public class Camera {
	
	

	private ImageWriter imageWriter;
	private RayTracerBase rayTrace;
	private static final String RESOURCE_ERROR = "Camera resource not set";
	private static final String RENDER_CLASS = "Camera";
	private static final String IMAGE_WRITER_COMPONENT = "Image writer";
	//private static final String RAY_TRACER_COMPONENT = "Ray tracer";
	//private Camera camera;
   //	private int numOfRays;

	
	
	//camera:
		private Point p0;		//camera's position
		private Vector vTo;		//directed forward to camera
		private Vector vUp;		//directed upwards to camera
		private Vector vRight;	//the right direction of camera

		//view plane:
		private double width;	//the view plane's width
		private double height;	//the view plane's height
		private double distance;//the distance of the camera from the view plane

		//aperture, depth of width:
		private double apWidth;
		private double apHeight;
		
		private double focalPlaneDistance;	//depth of field- the distance from camera to focal plane

		private int beamRays;	//num of rays in the beam

		/* ********* Constructor ***********/
		/**
		 * A new Camera
		 * @param _p0 camera location
		 * @param _vUp- the upwards vector
		 * @param _vTo- the forward vector
		 */
		public Camera(Point _p0, Vector _vTo, Vector _vUp )
		{
			if(!Util.isZero(_vUp.dotProduct(_vTo)))//if they are orthogonal-the dot product=0. vUp and vTo must be orthogonal
				throw new IllegalArgumentException("Vectors are not orthogonal");

			p0 = new Point(_p0.getXyz().getD1(),_p0.getXyz().getD2(),_p0.getXyz().getD3());//location of camera
			vTo = _vTo.normalize();	//camera's vectors must be normalized
			vUp = _vUp.normalize();	//camera's vectors must be normalized
			vRight = vTo.crossProduct(vUp).normalize();//we used crossProduct, since vRight is the normal to vTo and vUp plane.
		}

		/* ************* Getters/Setters *******/
		/**
		 * @return the position point
		 */
		public Point getp0() 
		{
			return p0;
		}

		/**
		 * @return the forward vector
		 */
		public Vector getvTo() 
		{
			return vTo;
		}

		/**
		 * @return the upwards vector
		 */
		public Vector getvUp() 
		{
			return vUp;
		}

		/**
		 * @return the right-directed vector
		 */
		public Vector getvRight() 
		{
			return vRight;
		}

		/**
		 * @return the dis of canera from v-p
		 */
		public double getDistance() 
		{
			return distance;
		}
		/**
		 * @return the width of v-p
		 */
		public double getwidth() 
		{
			return width;
		}
		/**
		 * @return the height of v-p
		 */
		public double getheight() 
		{
			return height;
		}


		/**
		 * @param _width of view plane
		 * @param _height of view plane
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setViewPlaneSize(double _width, double _height)//set the viewplane's size
		{
			width=_width;
			height=_height;
			return this;
		}

		/**
		 * @param _distance of camera from view plane
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setDistance(double _distance)//set the distance from the camera to viewPlane
		{
			distance=_distance;
			return this;
		}

	
		/**
		 * @param apWidth
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setApWidth(double apWidth) 
		{
			this.apWidth = apWidth;
			return this;
		}

		/**
		 * @return apHeight
		 */
		public double getApHeight() 
		{
			return apHeight;
		}

		/**
		 * @param apHeight
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setApHeight(double apHeight) 
		{
			this.apHeight = apHeight;
			return this;
		}
		
		/**
		 * set distance of focal plane
		 * @param d
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setFocalPlaneDistance(double d)
		{
			focalPlaneDistance = d;
			return this;
		}
		
		/**
		 * set beamRays
		 * @param num
		 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
		 */
		public Camera setBeamRays(int num)
		{
			beamRays = num;
			return this;
		}
		
		/**
		 * @return focalPlaneDistance
		 */
		public double getFocalPlaneDistance()
		{
			return focalPlaneDistance;
		}

		/* ************* construct ray *******/
		/**
		 * @param nX= number of pixels on tzir x of view plane
		 * @param nY= number of pixels on tzir y of view plane
		 * @param i= row index
		 * @param j= column index
		 * @return the ray that is created from camera to cross the given pixel of view plane
		 */
		public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
		{
			if (isZero(distance)) //we assume that distance between camera and view plane must be > than 0.
			{
				throw new IllegalArgumentException("distance cannot be 0");
			}
			
			Point Pij= getCenterOfPixel(nX, nY, j, i);//pij is the center of the pixel

			Vector Vij = Pij.subtract(p0);//Vij is the vector from the camera to Pij

			return new Ray(p0, Vij);//create the ray
		}
		
		/**
		 * build rays for each point- needed for super sampling, 
		 * a few rays through each pixel
		 * 
		 * @param points
		 * @param focalPoint
		 * @return rays list
		 */
		public List<Ray> constructRaysThroughPixel(List<Point> points, Point focalPoint)
		{	
			List<Ray> rays = new LinkedList <Ray>();
			for(Point point:points)
			{
				 Vector tmp = focalPoint.subtract(point);
				 Vector v=new Vector(tmp.getXyz().getD1(),tmp.getXyz().getD2(),tmp.getXyz().getD3());
				 rays.add(new Ray(point,v));
			}
		    
			return rays;
		}
		
		/**
		 * Creates focus rays
		 * 
		 * @param focalPoint
		 * @param dis
		 * @param pixelCenter
		 * @return list of rays from the specific pixel to the focal point. 
		 */
		public List<Ray> constructFocalRays(Point focalPoint, Point pixelCenter)
		{
			//we restart new linked lists because of the low running time of adding new items O(1), and we want to add many items:
			List<Ray> beam = new LinkedList<Ray>();	
			List<Point> points = new LinkedList<Point>();
			
			//find the range of the pixel on x and y :
			double xStart = pixelCenter.getXyz().getD1() - apWidth / 2;//start of aperture on tzir x
			double xEnd = pixelCenter.getXyz().getD1() + apWidth / 2;	//end of aperture on tzir x
			double yStart = pixelCenter.getXyz().getD2() - apHeight / 2;//start of aperture on tzir y
			double yEnd = pixelCenter.getXyz().getD2() + apHeight / 2;//end of aperture on tzir x
			
			//the z value of all the points we'll create is equal to z of the pixelCenter on the view plane:
			double z = pixelCenter.getXyz().getD3();
			//create random xxx points in the pixel:
			for(int i = 0; i < beamRays; i ++)
			{
				double x = (double) ((Math.random()*(xEnd - xStart + 1)) + xStart);
				double y = (double) ((Math.random()*(yEnd - yStart + 1)) + yStart);
				points.add(new Point(x, y, z));//add new point to list
			}

			//add the edges of the pixel to the points list:
			points.add(new Point(xEnd, yEnd, z));
			points.add(new Point(xEnd, yStart, z));
			points.add(new Point(xStart, yEnd, z));
			points.add(new Point(xStart, yStart, z));

			//create a ray from each point in the pixel to the focal point:
			for(Point point: points)
			{
				beam.add(new Ray(point, new Vector(focalPoint.subtract(point).getXyz())));
			}	

			return beam;//beam of rays from the pixel to the focal point of the pixel
		}


		/**
		 * Finds the center point of a particular pixel
		 * 
		 * @param nX
		 * @param nY
		 * @param j
		 * @param i
		 * @return the center point
		 */
		public Point getCenterOfPixel(int nX, int nY, int j, int i)
		{
			Point Pc = p0.add(vTo.scale(distance));//Pc is the center point in view plane. exactly in front of camera- vTo.
			//Pc might be in a pixel (if the numbers of pixels are uneven)
			//or between the pixels (if the numbers of pixels are even).
			//we will test the 2 cases in "CameraTests".

			double Ry = height / nY;//the y ratio on view plane
			double Rx = width / nX;	//the x ratio on view plane

			double yi = ((i - nY / 2d) * Ry + Ry / 2d);//calc the y value- the scalar of hazaza on tzir y
			double xj = ((j - nX / 2d) * Rx + Rx / 2d);//calc the x value- the scalar of hazaza on tzir x

			Point Pij = Pc;		//start nevigating from the center point on view plane

			//we only have to do hazaza on the tzir when it's not 0. 
			//if the y or x value=0, we don't need to do hazaza in that tzir, vRight (x) or vUp- y.
			if (!isZero(xj)) 
			{
				Pij = Pij.add(vRight.scale(xj));
			}
			if (!isZero(yi)) 
			{
				Pij = Pij.add(vUp.scale(-yi)); //we use -yi and not yi since we start counting the rows (i) 
				//from up to down (like a matrix), opposite to tzir y in reality and to vUp.
			}
			//now Pij is the center of the wanted pixel
			return Pij;
		}
		
		/**
		 * 
		 * @param pixelCenter
		 * @param dis
		 * @return focal point of the pixel- intersection point of ray from center of pixel to the focal plane
		 */
		public Point findFocalPoint(Point pixelCenter, double dis, Vector direction)
		{
			double t = dis/(direction.dotProduct(vTo));
			Point f=pixelCenter.add(direction.scale(t));
			return new Point(f.getXyz().getD1(),f.getXyz().getD2(),f.getXyz().getD3());//the focal point
		}
		
		/**
		 * return a list of 4 points- the edges of the pixel
		 * @param centerPixel
		 * @param nX
		 * @param nY
		 * @param screenWidth
		 * @param screenHeight
		 * @return list of the edges of shutter
		 */
		public List<Point> getPointsPixel(Point centerPixel, double width, double height, Vector vUp, Vector vRight)
		{
			//width of pixel
		    //double Rx = width / nX;
		    //height of pixel
		    //double Ry = height / nY;

			//Vector v1 = getVup().scale(Ry/2);
			//Vector v2 = getVright().scale(Rx/2);
			
			Vector v1 = vUp.scale(width/2);
			Vector v2 = vRight.scale(height/2);
			    
			List<Point> points = new LinkedList<Point>();
			points.add(centerPixel.add(v1).add(v2));
			points.add(centerPixel.add(v1.scale(-1.0)).add(v2.scale(-1.0)));
			points.add(centerPixel.add(v1.scale(-1.0)).add(v2));
			points.add(centerPixel.add(v1).add(v2.scale(-1.0)));    
			return points;
		}

		/**
		 * calculate the centers of the points in a quarter of the pixel 
		 * 
		 * @param points
		 * @return point list
		 */
		public List<Point> findCenterNewPixels(List<Point> points, Vector vUp, Vector vRight)
		{
			double w = points.get(0).distance(points.get(3));
			double h = points.get(0).distance(points.get(2));
			List<Point> centerPoints = new LinkedList<Point>();
			centerPoints.add(points.get(0).add(vRight.scale(w/-4)).add(vUp.scale(h/-4)));
			centerPoints.add(points.get(3).add(vRight.scale(w/4)).add(vUp.scale(h/-4)));
			centerPoints.add(points.get(2).add(vRight.scale(w/-4)).add(vUp.scale(h/4)));
			centerPoints.add(points.get(1).add(vRight.scale(w/4)).add(vUp.scale(h/4)));
			return centerPoints;
		}
		/**
		 * The function is responsible for creating the rays from the camera
		 * 
		 * @author Linoy Cohen and Yedida Cohen
		 * @param nX int value - resolution of pixel in X
		 * @param nY int value - resolution of pixel in Y
		 * @param j int value - index of column
		 * @param i int value - index of row
		 * @return Ray that created	 
		 * @throws Exception 
		 */
	/*	public Ray constructRayThroughPixel(int nX, int nY, int j, int i ) 
		{
			Point Pc;
			if (isZero(distance))
				Pc=p0;
			else
				Pc=p0.add(vTo.scale(distance));
			
			double Ry= height/nY;
			double Rx=width/nX;
			double Yi=(i-(nY-1)/2d)*Ry;
			double Xj=(j-(nX-1)/2d)*Rx;
			
			if(isZero(Xj) && isZero(Yi))
				return new Ray (p0, Pc.subtract(p0));
			
			Point Pij = Pc;
			
			if(!isZero(Xj))
				Pij = Pij.add(vRight.scale(Xj));
			
			if(!isZero(Yi))
				Pij = Pij.add(vUp.scale(-Yi));
			
			Vector Vij = Pij.subtract(p0);
			
			if(Pij.equals(p0))
				return new Ray(p0, new Vector(Pij.getXyz()));
			return new Ray(p0, Vij);

		}*/
		/*/**
		 * Cast ray from camera in order to color a pixel
		 * @param nX resolution on X axis (number of pixels in row)
		 * @param nY resolution on Y axis (number of pixels in column)
		 * @param col pixel's column number (pixel index in row)
		 * @param row pixel's row number (pixel index in column)
		 */
	/*	private void castRay(int Nx, int Ny, int column, int row) 
		{
			Ray ray = constructRayThroughPixel(Nx, Ny, column, row); //construct a ray through the pixel
			//imageWriter.writePixel(column, row, rayTracerBase.traceRay(ray));//paint the pixel with the right color

			//our specific pixel's center:
			Point p=getCenterOfPixel(Nx, Ny, column, row);
			Point pixelCenter = new Point(p.getXyz().getD1(),p.getXyz().getD2(),p.getXyz().getD3());

			//if the aperture is not restarted- no improve of depth of field:
			if(apWidth == 0 && getApHeight() == 0)
			{
				imageWriter.writePixel(column, row, traceRay(ray));//paint the pixel with the right color

			}
			else//only if the aperture is set- do the improve of depth of field
			{			
				//mini project part 1:
				//depth of field 
				
				//find the focal point of the pixel on the focal plane:
				Point focalPoint = findFocalPoint(pixelCenter, getFocalPlaneDistance()-getDistance(), ray.getDir().normelaize());

				/*if(superSamplingON)
				{
					//mini project part 2:
					//super sampling
					List<Point> ps = getPointsPixel(pixelCenter, apWidth, getApHeight(), getvUp(), getvRight()); // returns 4 points the edges of the pixel
					imageWriter.writePixel(column, row, RayTracerBase.calcColorPixel4(ps, 1, focalPoint,apWidth, getApHeight(),getvUp(), getvRight()));//paint the pixel with the right color

				}*/
				/*else
				{*/
					//mini project part 1 only:
					//create many rays from the specific pixel, around pixelCenter- many rays instead of one ray
			     /*	List<Ray> rays  = (constructFocalRays(focalPoint, pixelCenter));
					rays.add(ray);//add the main ray to the list of rays
					imageWriter.writePixel(column, row, rayTrace(rays));//paint the pixel with the right color

				//}

		/*	}
		}*/
		private void castRay(int nX, int nY, int col, int row) {
			Ray ray = constructRayThroughPixel(nX, nY, col, row);
			Color color = rayTrace.traceRay(ray);
			imageWriter.writePixel(col, row, color);
		}


		/**
		 * This function renders image's pixel color map from the scene included with
		 * the Renderer object
		 */
		public void renderImage()
		{
			if(this.p0==null || this.vTo==null|| this.vUp==null || this.rayTrace==null || this.vRight==null||this.imageWriter==null)
				throw new MissingResourceException("one of the properties contains empty value", null, null);
			//throw new UnsupportedOperationException();
			for (int i = 0; i <imageWriter.getNy() ; i++) {
				for (int j = 0; j < imageWriter.getNx(); j++) {
					castRay(imageWriter.getNx(),imageWriter.getNy(),j,i);
				}
			}
		}
		/**
		 * Create a grid [over the picture] in the pixel color map. given the grid's
		 * step and color.
		 * 
		 * @param step  grid's step
		 * @param color grid's color
		 */
		public void printGrid(int interval, Color color) {
			if (imageWriter == null)
				throw new MissingResourceException (RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

			int nX = imageWriter.getNx();
			int nY = imageWriter.getNy();

			for (int i = 0; i < nY; ++i)
				for (int j = 0; j < nX; ++j)
					if (j % interval == 0 || i % interval == 0)
						imageWriter.writePixel(j, i, color);
		}

		/**
		 * Produce a rendered image file
		 */
		public void writeToImage() {
			if (imageWriter == null)
				throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

			imageWriter.writeToImage();
		}
		/**
		 * A seter function for parameter rayTracer
		 * this function return the object - this for builder pattern
		 * 
		 * @param rayTracer RayTracerBase value
		 * */
		/*public Camera setNumOfRays(int numOfRays)
		{
			if(numOfRays == 0)
				this.numOfRays=1;
			else
			 this.numOfRays = numOfRays;
			return this;
		}
		*/
		
		public Camera setImageWriter(ImageWriter imageWriter) {
			this.imageWriter = imageWriter;
			return this;
		}

		public Camera setRayTrace(RayTracerBasic rayTrace) {
			this.rayTrace = rayTrace;
			return this;
		}

}
	




	
	