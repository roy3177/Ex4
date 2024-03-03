import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class geoTests {
	Point_2D p1=new Point_2D(1,1);
	Point_2D p2=new Point_2D(2,1);
	Point_2D p3=new Point_2D(34,12);
	Point_2D p4=new Point_2D(34,52);
	Point_2D p5=new Point_2D(2,12);

	public static final double EPS = 0.001;



	//Circle_2D:

	/*
	 * Tests the Circle_2D toString function:
	 */
	@Test
	public void testToStringCircle_2D() {
		double radius=10.0;
		Point_2D center=new Point_2D(p1);
		GeoShape  c=new Circle_2D(center,radius);
		String ans="Circle_2D:,(1.0,1.0),10.0";
		assertEquals(ans,c.toString());

	}
	/*
	 * Tests the circle_2D contains function:
	 */
	@Test
	public void testContainsCircle_2D() {
		//My checking point:
		Point_2D ot=new Point_2D(p1);
		Point_2D center=new Point_2D(p2);
		double radius=5.0;
		GeoShape  c=new Circle_2D(center,radius);
		assertTrue(c.contains(ot));

	}
	/*
	 * Tests the area function of the circle_2D class:
	 */
	@Test
	public void testAreaFunction() {
		double radius=6.0;
		//Area of circle is radius*radius*PAI:
		double area = Math.pow(radius, 2) * Math.PI;
		Point_2D center=new Point_2D(0,0);
		GeoShape  c=new Circle_2D(center,radius);
		double ans=c.area();
		assertEquals(area,ans,EPS);
	}
	/*
	 * Tests the perimeter function  of the circle_2D class:
	 */
	@Test
	public void testPerimeterFunction() {
		double radius=4.0;
		//Perimeter of circle is 2*radius*PAI:
		double perimeter=2*radius*Math.PI;
		Point_2D center=new Point_2D(2,2);
		GeoShape  c=new Circle_2D(center,radius);
		double ans=c.perimeter();
		assertEquals(perimeter,ans,EPS);
	}
	/*
	 * Tests the translate function of the circle_2D class:
	 */
	@Test
	public void testTranslateFunction() {
		Point_2D center = new Point_2D(0, 0);
		double radius = 4.0;
		Circle_2D circle = new Circle_2D(center, radius);

		Point_2D translationVector = new Point_2D(1, 1);
		circle.translate(translationVector);

		Point_2D expectedCenter = new Point_2D(1,1);
		Point_2D actualCenter = circle.getCenter();

		assertEquals(expectedCenter, actualCenter);
	}
	/*
	 * Tests the copy function of the circle_2D class:
	 */
	@Test
	public void testCopyFunction() {
		Point_2D center = new Point_2D(0, 0);
		double radius = 5.0;
		Circle_2D circle = new Circle_2D(center, radius);
		//Make a deep GeoShape of the circle values:
		GeoShape copy = circle.copy();
		assertEquals(circle.getCenter(), ((Circle_2D) copy).getCenter());
	}
	/*
	 * Tests the scale function of the circle_2D class:
	 */
	@Test
	public void testScaleFunction() {
		Point_2D center = new Point_2D(0, 0);
		double radius=2.0;
		Circle_2D circle1=new Circle_2D(center,radius); //The original circle.
		//Defining the scaling values:
		double ratio=0.5;
		Point_2D center2 = new Point_2D(1, 1);
		circle1.scale(center2, ratio);//Scaling the circle.
		//Assert the center and the radius:
		assertEquals(new Point_2D(0.5, 0.5), circle1.getCenter());
		assertEquals(1, circle1.getRadius(), EPS);


	}
	/*
	 * Tests the rotate function of the circle_2D class:
	 */
	@Test
	public void testRotateFunction() {
		// Create a circle(My original circle):
		Point_2D center = new Point_2D(0, 0);
		double radius = 5.0;
		Circle_2D originalCircle = new Circle_2D(center, radius);

		// Define the rotation parameters:
		Point_2D rotationCenter = new Point_2D(2, 2);
		double angleDegrees = 45;
		// Make the rotation:
		originalCircle.rotate(rotationCenter, angleDegrees);
		// Shows the  new center point:
		Point_2D expectedCenter = new Point_2D(2.0,-0.8284271247461903);
		assertEquals(expectedCenter, originalCircle.getCenter());
		// Shows the new radius:
		double expectedRadius = 5;
		assertEquals(expectedRadius, originalCircle.getRadius()); 
	}

	//Triangle_2D:
	/*
	 * Tests the getAllPoints function in the triangle_2D class:
	 */
	@Test
	public void testGetAllPoints() {
		Point_2D p1=new Point_2D(1,2);
		Point_2D p2=new Point_2D(2,3);
		Point_2D p3=new Point_2D(3,4);

		Triangle_2D trg=new Triangle_2D(p1,p2,p3);
		Point_2D[]points=trg.getAllPoints();
		//Assert the length of the arrays:
		assertEquals(3,points.length);
		// Check if the points in the returned array match the expected values:
		assertEquals(p1, points[0]);
		assertEquals(p2, points[1]);
		assertEquals(p3, points[2]);

	}
	/*
	 * Tests the contains function in the triangle_2D class:
	 */
	@Test
	public void testContainsFunction() {
		// Create a triangle with three points:
		Point_2D p1 = new Point_2D(0, 0);
		Point_2D p2 = new Point_2D(4, 0);
		Point_2D p3 = new Point_2D(2, 4);
		Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

		// Create a point,that is  inside the triangle:
		Point_2D pointOt = new Point_2D(2, 2);

		// Check if the triangle contains the point:
		boolean ans = triangle.contains(pointOt);

		// Assert that the point is inside the triangle
		assertTrue(ans);
	}
	/*
	 * Tests the area function in the triangle_2D class:
	 */
	@Test
	public void testAreaFunction1() {
		// Define the vertices of the triangle
		Point_2D p1 = new Point_2D(0, 0);
		Point_2D p2 = new Point_2D(0, 4);
		Point_2D p3 = new Point_2D(3, 0);

		// Create an instance of the Triangle_2D class:
		GeoShape triangle = new Triangle_2D(p1, p2, p3);

		// Calculate the expected area manually
		double expectedArea = 6.0;

		// Calculate the actual area using the method
		double actualArea = triangle.area();

		// Assert that the actual area matches the expected area
		assertEquals(expectedArea, actualArea, 0.0001);
	}
	/*
	 * Tests the perimeter function in the triangle_2D class:
	 */
	@Test
	public void testPerimeterFunction1() {
		Point_2D p1 = new Point_2D(4.0,3.0);
		Point_2D p2 = new Point_2D(0.0, 0.0);
		Point_2D p3 = new Point_2D(4.0, 3.0);
		GeoShape triangle = new Triangle_2D(p1, p2, p3);


		double per=10.0;//The perimeter of the triangle.
		double ansPer=triangle.perimeter();
		assertEquals(per,ansPer);
	}
	/*
	 * Tests the translate function in the triangle_2D class:
	 */
	public void testTranslateFunction1() {
		// Create the triangle,with original points:
		Point_2D p1 = new Point_2D(0, 0);
		Point_2D p2 = new Point_2D(0, 4);
		Point_2D p3 = new Point_2D(3, 0);
		Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

		// Define the moving vector:
		Point_2D movingVec = new Point_2D(2, 3);

		// Moving the triangle by the vector:
		triangle.translate(movingVec);

		// Define the expected vertices after translation
		Point_2D expectedP1 = new Point_2D(2, 3);
		Point_2D expectedP2 = new Point_2D(2, 7);
		Point_2D expectedP3 = new Point_2D(5, 3);

		// Get the actual vertices after translation
		Point_2D actualP1 = triangle.getAllPoints()[0];
		Point_2D actualP2 = triangle.getAllPoints()[1];
		Point_2D actualP3 = triangle.getAllPoints()[2];

		// Assert that the actual vertices match the expected vertices
		assertEquals(expectedP1, actualP1);
		assertEquals(expectedP2, actualP2);
		assertEquals(expectedP3, actualP3);

	}
	/*
	 * Tests the copy function of the triangle_2D class:
	 */
	@Test
	public void testCopyFunction1() {
		// Create a triangle
		Point_2D p1 = new Point_2D(1, 2);
		Point_2D p2 = new Point_2D(3, 4);
		Point_2D p3 = new Point_2D(5, 6);
		Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

		// Make a copy of the triangle above:
		Triangle_2D copy =triangle.copy();
		// Check if the copy is equal to the original triangle
		assertArrayEquals(triangle.getAllPoints(), copy.getAllPoints());
	}
	/*
	 * Tests the scale function of the triangle_2D function:
	 */
	@Test
	public void testScaleFunction1() {
		// Create a triangle
		Point_2D p1 = new Point_2D(0, 0);
		Point_2D p2 = new Point_2D(3, 0);
		Point_2D p3 = new Point_2D(0, 4);
		Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

		// Scale the triangle
		Point_2D center = new Point_2D(1, 1);
		double scaleFactor = 2.0;
		triangle.scale(center, scaleFactor);
		// Assert the scaled points
		Point_2D[] expectedPoints = {
				new Point_2D(-1, -1),
				new Point_2D(5, -1),
				new Point_2D(-1, 7)
		};
		Point_2D[] actualPoints = triangle.getAllPoints();
		assertArrayEquals(expectedPoints, actualPoints);
	}
	/*
	 * Tests the rotate function of the triangle_2D class:
	 */
	@Test
	public void testRotateFunction1() {
		// Create a triangle
		Point_2D p1 = new Point_2D(1, 1);
		Point_2D p2 = new Point_2D(4, 1);
		Point_2D p3 = new Point_2D(2.5, 4);
		Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

		// Define the center of rotation and the angle
		Point_2D center = new Point_2D(2.5, 2.5);
		double angleDegrees = 90;

		// Rotate the triangle
		triangle.rotate(center, angleDegrees);

		// Check the new positions of the points after rotation
		Point_2D expectedP1 = new Point_2D(4, 1);
		Point_2D expectedP2 = new Point_2D(4, 4);
		Point_2D expectedP3 = new Point_2D(1, 2.5);

		// Assert that the points have been correctly rotated
		assertEquals(expectedP1, triangle.getAllPoints()[0]);
		assertEquals(expectedP2, triangle.getAllPoints()[1]);
		assertEquals(expectedP3, triangle.getAllPoints()[2]);
	}
	//Rect_2D:
	/*
	 * Tests the contains function in the rect_2D class:
	 */
	@Test
	public void testContainsFunction2() {
		Point_2D p1=new Point_2D(0,0);
		Point_2D p2=new Point_2D(4,4);
		Point_2D isContain=new Point_2D(2,2);
		Point_2D isNotContain=new Point_2D(6,6);

		Rect_2D rectangle = new Rect_2D(p1,p2);
		// Test points inside the rectangle:
		assertTrue(rectangle.contains(isContain));
		assertFalse(rectangle.contains(isNotContain));	
	}
	/*
	 * Tests the area function in the rect_2D class:
	 */
	@Test
	public void testAreaFunction2() {
		Point_2D p1=new Point_2D(0,4);
		Point_2D p2=new Point_2D(6,2);
		Point_2D p3=new Point_2D(0,2);
		Rect_2D rectangle = new Rect_2D(p1,p2);
		double dis1=p1.distance(p3);
		double dis2=p3.distance(p2);
		double area=12.0;
		assertEquals(area,rectangle.area());
	}
	/*
	 * Tests the perimeter function in the rect_2D class:
	 */
	@Test
	public void testPerimeterFunction2() {
		Point_2D p1=new Point_2D(0,0);
		Point_2D p2=new Point_2D(4,2);
		Point_2D p3=new Point_2D(0,2);
		Rect_2D rectangle = new Rect_2D(p1,p2);
		double dis1=p1.distance(p3);
		double dis2=p3.distance(p2);
		double per=12.0;
		assertEquals(12.0,rectangle.perimeter());
	}
	/*
	 * Tests the translate function in the rect_2D class:
	 */
	@Test
	public void testTranslateFunction2() {
		// Create a rectangle
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(4, 0);
        Point_2D p3 = new Point_2D(4, 2);
        Point_2D p4 = new Point_2D(0, 2);
        Rect_2D rect = new Rect_2D(p1, p2, p3, p4);

        // Define the translation vector
        Point_2D translation = new Point_2D(1, 1);

        // Translate the rectangle
        rect.translate(translation);

        // Verify the updated positions of the rectangle's points
        Point_2D[] expectedPoints = {
            new Point_2D(1, 1),
            new Point_2D(5, 1),
            new Point_2D(5, 3),
            new Point_2D(1, 3)
        };
        assertArrayEquals(expectedPoints, rect.getPoints());
	}
	/*
	 * Tests the copy function of the rect_2D class:
	 */
	@Test
	public void testCopyFunction2() {
		// Create a rectangle
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(4, 0);
        Point_2D p3 = new Point_2D(4, 2);
        Point_2D p4 = new Point_2D(0, 2);
        Rect_2D rect1 = new Rect_2D(p1, p2, p3, p4);

        // Make a copy of the rectangle
        Rect_2D rect2 = (Rect_2D) rect1.copy();

        // Verify that the copied rectangle is equal to the original
        assertNotSame(rect1, rect2);
        assertArrayEquals(rect1.getPoints(), rect2.getPoints());
	}
	/*
	 * Tests the scale function of the rect_2D class:
	 */
	@Test
	public void testScaleFunction2() {
		// Create a rectangle
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(4, 0);
        Point_2D p3 = new Point_2D(4, 2);
        Point_2D p4 = new Point_2D(0, 2);
        Rect_2D rect = new Rect_2D(p1, p2, p3, p4);

        // Define the center of scaling
        Point_2D center = new Point_2D(2, 1);

        // Define the scaling ratio
        double ratio = 2;

        // Scale the rectangle
        rect.scale(center, ratio);

        // Verify the updated positions of the rectangle's points
        Point_2D[] expectedPoints = {
            new Point_2D(-2, -1),
            new Point_2D(6, -1),
            new Point_2D(6, 3),
            new Point_2D(-2, 3)
        };
        assertArrayEquals(expectedPoints, rect.getPoints());
    }
	
	/*
	 * Tests the rotate function in the rect_2D class:	 
	 */
	@Test
	public void testRotateFunction2() {
		 // Create a rectangle
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(4, 0);
        Point_2D p3 = new Point_2D(4, 2);
        Point_2D p4 = new Point_2D(0, 2);
        Rect_2D rect = new Rect_2D(p1, p2, p3, p4);

        // Define the center of rotation
        Point_2D center = new Point_2D(2, 1);

        // Define the rotation angle (in radians)
        double angleRadians = Math.PI / 2;

        // Rotate the rectangle
        rect.rotate(center, angleRadians);

        // Verify the updated positions of the rectangle's points
        Point_2D[] expectedPoints = {
            new Point_2D(0.028163699873081205,-0.05444848404357039),
            new Point_2D(4.026660567311008,0.05520005032460695),
            new Point_2D(3.971836300126919,2.0544484840435704),
            new Point_2D(-0.02666056731100719,1.9447999496753936)
        };
        assertArrayEquals(expectedPoints, rect.getPoints());
    }
	
	//Segment_2D class:
	/*
	 * Tests the perimeter function in segment_2D class:
	 */
	@Test
	public void testPerimeterFunction3() {
		Point_2D p1=new Point_2D(0,0);
		Point_2D p2=new Point_2D(6,0);
		Segment_2D seg=new Segment_2D(p1,p2);
		double per=6.0;
		assertEquals(per,seg.perimeter());

	}
	/*
	 * Tests the translate function of the segment_2D class:
	 */
	@Test
	public void testTranslteFunction3() {
		// Create the triangle,with original points:
		Point_2D p1=new Point_2D(0,0);
		Point_2D p2=new Point_2D(5,5);
		Segment_2D seg=new Segment_2D(p1,p2);
		// Define the moving vector:
		Point_2D movingPoint=new Point_2D(2,2);
		seg.translate(movingPoint);
		// Check if the segment's points have been correctly translated
		Point_2D expectedMin = new Point_2D(2, 2);
		Point_2D expectedMax = new Point_2D(7, 7);
		// Get the actual vertices after translation
		// Get the actual vertices after translation
		Point_2D actualp1 = seg.getMinimumPoint();
		Point_2D actualp2 = seg.getMaximumPoint();

		// Assert that the actual vertices match the expected vertices
		assertEquals(expectedMin, actualp1);
		assertEquals(expectedMax, actualp2);		
	}
	/*
	 * Tests the copy function of the segment_2D class:
	 */
	@Test
	public void testCopyFunction3() {
		Point_2D p1=new Point_2D(1,0);
		Point_2D p2=new Point_2D(4,2);
		Segment_2D seg=new Segment_2D(p1,p2);
		// Make a copy of the original rectangle
		Segment_2D copySeg = (Segment_2D) seg.copy();
		Point_2D newPoint=new Point_2D(7,3);
		// Verify that the copied rectangle remains unchanged
		assertEquals(seg.getMinimumPoint(), copySeg.getMinimumPoint());
		assertEquals(seg.getMaximumPoint(), copySeg.getMaximumPoint());
	}
	/*
	 * Tests the scale function in the segment_2D class:
	 */
	@Test
	public void testScaleFunction3() {
		//Create the segment:
		Point_2D p1=new Point_2D(1,1);
		Point_2D p2=new Point_2D(6,6);
		Segment_2D seg=new Segment_2D(p1,p2);
		Point_2D cen=new Point_2D(2,2);
		double ratio=3.0;

		seg.scale(cen, ratio);
		Point_2D expectedA = new Point_2D(-5.5, -5.5);
		Point_2D expectedB = new Point_2D(9.5, 9.5);

		// Verify that the segment has been scaled correctly
		assertEquals(expectedA.x(), seg.getMinimumPoint().x(), EPS);
		assertEquals(expectedA.y(), seg.getMinimumPoint().y(), EPS);
		assertEquals(expectedB.x(), seg.getMaximumPoint().x(), EPS);
		assertEquals(expectedB.y(), seg.getMaximumPoint().y(), EPS);

	}
	/*
	 * Tests the rotate function in the segment_2D class:
	 */
	@Test
	public void testRotateFunction3() {
		//Create the segment:
		Point_2D p1=new Point_2D(2,3);
		Point_2D p2=new Point_2D(5,6);
		Segment_2D seg=new Segment_2D(p1,p2);

		// Define the rotation center and angle in degrees:
		Point_2D center = new Point_2D(3.5, 4.5);
		double angleDegree = 45.0;

		// Apply rotation to the segment:
		seg.rotate(center, angleDegree);

		// Calculate the expected coordinates after rotation:
		Point_2D expectedA = new Point_2D(3.5, 2.378679656440357);
		Point_2D expectedB = new Point_2D(3.5, 6.621320343559643);

		// Verify that the segment has been rotated correctly:
		assertEquals(expectedA.x(), seg.getMinimumPoint().x());
		assertEquals(expectedA.y(), seg.getMinimumPoint().y());
		assertEquals(expectedB.x(), seg.getMaximumPoint().x());
		assertEquals(expectedB.y(), seg.getMaximumPoint().y());
	}
	//Polygon_2D class:

	/*
	 * Tests the add  function in the Polygon_2D class:
	 */
	@Test
	public void testAddFunction() {
		Polygon_2D pol=new Polygon_2D();

		//The vertices of my polygon:
		Point_2D p1=new Point_2D(1,1);
		Point_2D p2=new Point_2D(2,3);
		Point_2D p3=new Point_2D(6,7);

		//Adding the points:
		pol.add(p1);
		pol.add(p2);
		pol.add(p3);

		//Getting all the points of the polygon_2D class:
		Point_2D[]points=pol.getAllPoints();

		assertEquals(p1, points[0]);
		assertEquals(p2, points[1]);
		assertEquals(p3, points[2]);
	}
	/*
	 * Tests the toString function in the polygon_2D class:
	 */
	@Test
	public void testToString() {
		// Create a polygon
		Polygon_2D polygon = new Polygon_2D();

		// Add vertices to the polygon
		Point_2D p1 = new Point_2D(1, 1);
		Point_2D p2 = new Point_2D(2, 3);
		Point_2D p3 = new Point_2D(4, 5);
		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);

		// Convert the polygon to a string
		String polygonString = polygon.toString();

		// Verify that the string representation is correct
		String expectedString = "Polygon_2D { points =[1.0,1.0, 2.0,3.0, 4.0,5.0]}";
		assertEquals(expectedString, polygonString);
	}
	/*
	 * Tests the contains function in the polygon_2D class: 
	 */
	@Test
	public void testContainsFunction4() {
		// Create a polygon with some points
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(1, 1));
		polygon.add(new Point_2D(3, 1));
		polygon.add(new Point_2D(2, 3));

		// Test with points inside the polygon
		Point_2D point1 = new Point_2D(2, 2);
		assertTrue(polygon.contains(point1));
	}
	/*
	 * Tests the area function of the polygon_2D class:
	 */
	@Test
	public void testAreaFunction4() {
		// Create a polygon with 3 points
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(1, 1));
		polygon.add(new Point_2D(3, 1));
		polygon.add(new Point_2D(2, 3));


		// Calculate the area
		double area = 2.0;

		// Calculate the actual area
		double actualArea = polygon.area();

		// Verify that the calculated area matches the expected area
		assertEquals(area, actualArea, EPS);

	}
	/*
	 * Tests the perimeter function of the polygon_2D class:
	 */
	@Test
	public void testPerimeterFunction4() {
		// Create a polygon with three points
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(1, 1));
		polygon.add(new Point_2D(3, 1));
		polygon.add(new Point_2D(2, 3));

		// Calculate the expected perimeter :
		double edge1Length = polygon.getAllPoints()[0].distance(polygon.getAllPoints()[1]);
		double edge2Length = polygon.getAllPoints()[1].distance(polygon.getAllPoints()[2]);
		double edge3Length = polygon.getAllPoints()[2].distance(polygon.getAllPoints()[0]);
		double expectedPerimeter = edge1Length + edge2Length + edge3Length;

		double actualPerimeter = polygon.perimeter();
		assertEquals(expectedPerimeter, actualPerimeter, 0.0001);
	}
	/*
	 * Tests the translate function of the polygon_2D class:
	 */
	@Test
	public void testTranslateFunction4() {
		// Create a polygon with three points
		Polygon_2D poly = new Polygon_2D();
		poly.add(new Point_2D(1, 1));
		poly.add(new Point_2D(3, 1));
		poly.add(new Point_2D(2, 3));

		// Create a translation vector
		Point_2D movVector = new Point_2D(2, 2);

		// Apply translation
		poly.translate(movVector);

		// Check if the polygon's vertices have been translated correctly
		Point_2D[] expectedPoints = {
				new Point_2D(3, 3),
				new Point_2D(5, 3),
				new Point_2D(4, 5)
		};
		Point_2D[] actualPoints = poly.getAllPoints();
		assertArrayEquals(expectedPoints, actualPoints);
	}
	/*
	 * Tests the copy function of the Polygon_2D class:
	 */
	@Test
	public void testCopyFunction4() {
		// Create a polygon with 3 vertices
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(1, 2));
		polygon.add(new Point_2D(3, 4));
		polygon.add(new Point_2D(5, 6));

		// Make a copy of the polygon
		GeoShape copyPolygon = polygon.copy();

		// Check if the copied polygon is an instance of Polygon_2D
		assertTrue(copyPolygon instanceof Polygon_2D);

		// Cast the copied polygon back to Polygon_2D for further testing
		Polygon_2D copiedPolygon2D = (Polygon_2D) copyPolygon;

		// Get the vertices of the original polygon
		Point_2D[] originalPoints = polygon.getAllPoints();

		// Get the vertices of the copied polygon
		Point_2D[] copiedPoints = copiedPolygon2D.getAllPoints();

		// Check if the number of vertices is the same
		assertEquals(originalPoints.length, copiedPoints.length);

		// Check if each vertex in the copied polygon is a separate copy
		for (int i = 0; i < originalPoints.length; i++) {
			Point_2D originalPoint = originalPoints[i];
			Point_2D copiedPoint = copiedPoints[i];

			// Check if the coordinates of the vertices are the same
			assertEquals(originalPoint.x(), copiedPoint.x());
			assertEquals(originalPoint.y(), copiedPoint.y());

		}
	}
	/*
	 * Tests the scale function of the Polygon_2D class:
	 */
	@Test
	public void testScaleFunction4() {
		// Create a polygon with 3 points:
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(0, 0));
		polygon.add(new Point_2D(1, 0));
		polygon.add(new Point_2D(0, 1));

		// Define the center point for scaling:
		Point_2D center = new Point_2D(0.5, 0.5);

		// Scale the polygon by a ratio of 2
		double ratio = 2.0;
		polygon.scale(center, ratio);

		// Get the scaled vertices of the polygon
		Point_2D[] scaledPoints = polygon.getAllPoints();

		// Define the expected scaled vertices
		Point_2D[] expectedPoints = {
				new Point_2D(-0.5, -0.5000000000000002),
				new Point_2D(1.5000000000000002, -0.5),
				new Point_2D(-0.5, 1.5000000000000002)
		};

		// Assert that the scaled vertices match the expected vertices
		assertArrayEquals(expectedPoints, scaledPoints);
	}
	/*
	 * Tests the rotate function of the Polygon_2D class:
	 */
	@Test
	public void testRotateFunction4() {
		// Create a polygon with 4 vertices:
		Polygon_2D polygon = new Polygon_2D();
		polygon.add(new Point_2D(0, 0));
		polygon.add(new Point_2D(2, 0));
		polygon.add(new Point_2D(2, 2));
		polygon.add(new Point_2D(0, 2));

		// Define the center of rotation and the rotation angle
		Point_2D center = new Point_2D(1, 1);
		double angleDegrees = 90.0;

		// Apply rotation
		polygon.rotate(center, angleDegrees);

		// Define the expected rotated polygon
		Polygon_2D expectedPolygon = new Polygon_2D();
		expectedPolygon.add(new Point_2D(2, 0));
		expectedPolygon.add(new Point_2D(2, 2));
		expectedPolygon.add(new Point_2D(0, 2));
		expectedPolygon.add(new Point_2D(-2.220446049250313E-16,0.0));

		// Check if the vertices of the polygon match the expected vertices
		Point_2D[] actualPoints = polygon.getAllPoints();
		Point_2D[] expectedPoints = expectedPolygon.getAllPoints();
		for (int i = 0; i < actualPoints.length; i++) {
			assertEquals(expectedPoints[i], actualPoints[i]);
		}
	}
}



