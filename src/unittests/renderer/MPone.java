/*
 * */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


import lighting.*;
import geometries.Sphere;
//import geometries.Traingle;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.*;
import scene.Scene;

class MPone {

	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() 
	{
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3(0.15) ));

		scene.geometries.add( //
//				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
//						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
//				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
//						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
				new Sphere(new Point(0, 0, 0), 80) //
						.setEmission(new Color(java.awt.Color.yellow)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setnShininess(30).setKt(new Double3(0.6))));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("miniproject1-from", 600, 600);
		Camera render = new Camera() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTrace(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
//		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//				.setViewPlaneSize(200, 200).setDistance(1000);
//
//		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
//
//		scene.geometries.add( //
//				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
//						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
//				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
//						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
//				new Sphere(new Point3D(60, 50, -50), 30) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setKD(0.2).setKS(0.2).setnShininess(30).setKT(0.6)));
//
//		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
//				.setKL(4E-5).setKQ(2E-7));
//
//		ImageWriter imageWriter = new ImageWriter("miniproject1-from"
//				+ "", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
	}

	@Test
	public void trianglesTransparentSphere2() 
	{
		int numOfRays=144;
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new  Double3( 0.15)));

		scene.geometries.add( //
				new Sphere(new Point(0, 0, 0), 80) //
				.setEmission(new Color(java.awt.Color.yellow)) //
				.setMaterial(new Material().setKD(0.2).setKS(0.2).setnShininess(30).setKt(new Double3(0.6))));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("miniproject1-after" + "", 600, 600);
		Camera render = new Camera().setNumOfRays(numOfRays) //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTrace(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}


}