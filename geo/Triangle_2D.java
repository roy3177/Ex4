import java.util.ArrayList;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D _p1;
	private Point_2D _p2;
	private Point_2D _p3;

	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1=new Point_2D(p1);
		this._p2=new Point_2D(p2);
		this._p3=new Point_2D(p3);
	}
	//Defines a constructor in the class that takes another Triangle_2D object (t1) as a parameter:
	public Triangle_2D(Triangle_2D t1) {
		this._p1=new Point_2D(t1._p1);
		this._p2=new Point_2D(t1._p2);
		this._p3=new Point_2D(t1._p3);
	}
	//Puts the points into a array:
	public Point_2D[] getAllPoints() {
		Point_2D[]points=new Point_2D[3];
		points[0]=_p1;
		points[1]=_p2;
		points[2]=_p3;

		return points;

	}
	//Checking if there is a point that is inside the triangle:
	@Override
	public boolean contains(Point_2D ot) {
		//The coordinates of the ot point:
		double otX=ot.x();
		double otY=ot.y();

		// Get the coordinates of the triangle points:
		double p1X = _p1.x();
		double p1Y = _p1.y();
		double p2X = _p2.x();
		double p2Y= _p2.y();
		double p3X = _p3.x();
		double p3Y = _p3.y();

		// Calculate the vectors from each vertex to the point
		double v0X = p3X -p1X ;
		double v0Y = p3Y -p1Y ;
		double v1X = p2X -p1X ;
		double v1Y = p2Y- p1Y ;
		double v2X = otX - p1X;
		double v2Y = otY - p1Y;

		//The calculations represent the point doubling between different vectors. Point doubling is a mathematical 
		//operation that takes two vectors as input and returns a scalar value:
		double PD00 = v0X * v0X + v0Y * v0Y;
		double PD01 = v0X * v1X + v0Y * v1Y;
		double PD02 = v0X * v2X + v0Y * v2Y;
		double PD11 = v1X * v1X + v1Y * v1Y;
		double PD12 = v1X * v2X + v1Y * v2Y;

		//Calculating the coordinates of u and w,of a given point with respect to a triangle. 
		//With the way of barycentric coordinates,
		//that is a way to represent a point within a triangle using a set of weights:

		//The reciprocal of the determinant of the 2x2 matrix formed by the point doubling:
		double determinantReciprocal = 1 / (PD00 * PD11 - PD01 * PD01);
		//Represents the proportion of the vertex _p1 in the overall point location:
		double A = (PD11 * PD02 - PD01 * PD12) * determinantReciprocal;
		//Represents the proportion of the vertex _p2 in the overall point location:
		double B = (PD00 * PD12 - PD01 * PD02) * determinantReciprocal;
		// Check if the point is inside the triangle
		return (A>= 0) && (B >= 0) && (A + B <= 1); 
	}

	@Override
	//Calculating the area of the triangle:
	public double area() {
		//The coordinates of the points in the triangle:
		double x1=_p1.x();
		double x2=_p2.x();
		double x3=_p3.x();
		double y1=_p1.y();
		double y2=_p2.y();
		double y3=_p3.y();
		//Took the coordinates and bring my points:
		Point_2D p1=new Point_2D(x1,y1);
		Point_2D p2=new Point_2D(x2,y2);
		Point_2D p3=new Point_2D(x3,y3);
		//Computes the distances between all the points of the triangle:
		double s1=p1.distance(p2);
		double s2=p2.distance(p3);
		double s3=p3.distance(p1);

		//Calculate half of the perimeter:
		double helfPer=(s1+s2+s3)/2.0;

		//Calculate the area of the triangle by the formula of Heron.
		//Link for the formula:
		//https://en.wikipedia.org/wiki/Heron%27s_formula

		return Math.sqrt((helfPer)*(helfPer-s1)*(helfPer-s2)*(helfPer-s3));



	}

	@Override
	//Calculate the perimeter of triangle,making distances and sum them:
	public double perimeter() {
		double dis1=_p1.distance(_p2);
		double dis2=_p2.distance(_p3);
		double dis3=_p3.distance(_p1);
		return (dis1+dis2+dis3);
	}

	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public Triangle_2D copy() {
		return new Triangle_2D(_p1,_p2,_p3);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		// Calculate the scaling factor
		double scaleFactor = Math.abs(ratio);

		// Scale each point of the triangle with respect to the center+connection to 
		//  the helper function scalePoint:
		_p1 = scalePoint(center, _p1, scaleFactor);
		_p2 = scalePoint(center, _p2, scaleFactor);
		_p3 = scalePoint(center, _p3, scaleFactor);
	}

	// Helper method to scale a point with respect to a center point.
	//the scalePoint method calculates the scaled coordinates of a point by
	//considering the displacement of the point from the center and scaling that
	//displacement according to the provided scaling factor.
	//The method then returns a new Point_2D object with the scaled coordinates.
	private Point_2D scalePoint(Point_2D center, Point_2D point, double scaleFactor) {
		double dx = point.x() - center.x();
		double dy = point.y() - center.y();
		double scaledX = center.x() + dx * scaleFactor;
		double scaledY = center.y() + dy * scaleFactor;
		return new Point_2D(scaledX, scaledY);
	}
	//The rotate method in the  this code is responsible for rotating
	//the triangle around a given center point by a specified angle in degrees.
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		//Convert the angle from degrees to radians:
		double angleRadians = Math.toRadians(angleDegrees);
		//Calculating the sine and cosine of the angle:
		//These values will be used to perform the rotation transformation.
		double cosTheta = Math.cos(angleRadians);
		double sinTheta = Math.sin(angleRadians);

		//Translate the points of the triangle to the center of rotation: 
		double dx1 = _p1.x() - center.x();
		double dy1 = _p1.y() - center.y();
		double dx2 = _p2.x() - center.x();
		double dy2 = _p2.y() - center.y();
		double dx3 = _p3.x() - center.x();
		double dy3 = _p3.y() - center.y();

		//Apply the rotation transformation to each point using the formulas:		
		double newX1 = dx1 * cosTheta - dy1 * sinTheta;
		double newY1 = dx1 * sinTheta + dy1 * cosTheta;
		double newX2 = dx2 * cosTheta - dy2 * sinTheta;
		double newY2 = dx2 * sinTheta + dy2 * cosTheta;
		double newX3 = dx3 * cosTheta - dy3 * sinTheta;
		double newY3 = dx3 * sinTheta + dy3 * cosTheta;

		// Translate points back to their original position:
		_p1 = new Point_2D(center.x() + newX1, center.y() + newY1);
		_p2 = new Point_2D(center.x() + newX2, center.y() + newY2);
		_p3 = new Point_2D(center.x() + newX3, center.y() + newY3);
	}
	@Override
	public String toString() { //creating an ArrayList of shape type and Point2D triangle points 
		ArrayList<String> sArr = new ArrayList<>();
		sArr.add(this.getClass().getSimpleName());
		Point_2D[] points = this.getAllPoints();
		for (int i = 0; i < points.length; i++) {
			sArr.add(points[i].toString());
		}
		return String.join(",", sArr);
	}
}
