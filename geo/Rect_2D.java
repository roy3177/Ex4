import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This class represents a 2D axis-parallel rectangle.
 * The rectangle is defined by its four vertices: p1, p2, p3, and p4.
 * The vertices are ordered in a clockwise manner.
 * The class implements the GeoShape interface.
 * 
 * The class provides methods to perform geometric operations on the rectangle, such as translation, scaling, rotation,
 * as well as methods to calculate the area, perimeter, and determine if a point is inside the rectangle.
 * It also provides methods to get a deep copy of the rectangle and retrieve its vertices as an array of Point_2D objects.
 * 
 * Note: The Point_2D class used in this code is assumed to be a custom class representing 2D points.
 * The code assumes the availability of certain methods in the Point_2D class, such as distance, move, scale, and rotate.
 * Please ensure that the Point_2D class is defined and accessible before using this code.
 * 
 * @author I2CS
 */
public class Rect_2D implements GeoShape {

	private Point_2D _p1; // First vertex of the rectangle
	private Point_2D _p2; // Second vertex of the rectangle
	private Point_2D _p3; // Third vertex of the rectangle
	private Point_2D _p4; // Fourth vertex of the rectangle

	/**
	 * Constructs a new Rect_2D object with the given two vertices.
	 * The constructor ensures that the vertices are ordered in a clockwise manner.
	 * 
	 * @param p1 The first vertex of the rectangle.
	 * @param p2 The second vertex of the rectangle.
	 */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		// Determine the maximum and minimum x and y coordinates
		double maxX = p1.x() > p2.x() ? p1.x() : p2.x();
		double maxY = p1.y() > p2.y() ? p1.y() : p2.y();
		double minX = p1.x() < p2.x() ? p1.x() : p2.x();
		double minY = p1.y() < p2.y() ? p1.y() : p2.y();

		// Set the vertices of the rectangle in clockwise order
		this._p1 = new Point_2D(minX, minY);
		this._p2 = new Point_2D(maxX, minY);
		this._p3 = new Point_2D(maxX, maxY);
		this._p4 = new Point_2D(minX, maxY);
	}

	/**
	 * Constructs a new Rect_2D object that is a deep copy of the given rectangle.
	 * 
	 * @param t1 The rectangle to be copied.
	 */
	public Rect_2D(Rect_2D t1) {
		this._p1 = new Point_2D(t1._p1);
		this._p2 = new Point_2D(t1._p2);
		this._p3 = new Point_2D(t1._p3);
		this._p4 = new Point_2D(t1._p4);
	}

	/**
	 * Constructs a new Rect_2D object with the given four vertices.
	 * 
	 * @param p1 The first vertex of the rectangle.
	 * @param p2 The second vertex of the rectangle.
	 * @param p3 The third vertex of the rectangle.
	 * @param p4 The fourth vertex of the rectangle.
	 */
	public Rect_2D(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D p4) {
		this._p1 = new Point_2D(p1);
		this._p2 = new Point_2D(p2);
		this._p3 = new Point_2D(p3);
		this._p4 = new Point_2D(p4);
	}

	// Getter and setter methods for the vertices

	public void set_p1(Point_2D _p1) {
		this._p1 = new Point_2D(_p1);
	}

	public void set_p2(Point_2D _p2) {
		this._p2 = new Point_2D(_p2);
	}

	public void set_p3(Point_2D _p3) {
		this._p3 = new Point_2D(_p3);
	}

	public void set_p4(Point_2D _p4) {
		this._p4 = new Point_2D(_p4);
	}

	/**
	 * Checks whether a given point is inside the rectangle.
	 * The method uses the crossing number algorithm to determine the point's position relative to the rectangle.
	 * 
	 * @param ot The point to check.
	 * @return true if the point is inside the rectangle, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		int countCross = 0;
		int isLeftRes;

		isLeftRes = this.isLeft(_p1, _p2, ot);
		if (isLeftRes == 0) {
			return true;
		} else if (isLeftRes < 0) {
			countCross++;
		}

		isLeftRes = this.isLeft(_p2, _p3, ot);
		if (isLeftRes == 0) {
			return true;
		} else if (isLeftRes < 0) {
			countCross++;
		}

		isLeftRes = this.isLeft(_p3, _p4, ot);
		if (isLeftRes == 0) {
			return true;
		} else if (isLeftRes < 0) {
			countCross++;
		}

		isLeftRes = this.isLeft(_p4, _p1, ot);
		if (isLeftRes == 0) {
			return true;
		} else if (isLeftRes < 0) {
			countCross++;
		}

		// Check the sum of crossing points with the rectangle.
		if (countCross % 2 == 0) {
			return false; // Even number of crossings - point is outside the rectangle
		} else {
			return true; // Odd number of crossings - rectangle contains the point
		}
	}

	/**
	 * Calculates the area of the rectangle.
	 * 
	 * @return The area of the rectangle.
	 */
	@Override
	public double area() {
		double dis1 = _p1.distance(_p4);
		double dis2 = _p1.distance(_p2);
		return (dis1 * dis2);
	}

	/**
	 * Calculates the perimeter of the rectangle.
	 * 
	 * @return The perimeter of the rectangle.
	 */
	@Override
	public double perimeter() {
		double dis1 = _p1.distance(_p4);
		double dis2 = _p1.distance(_p2);
		return (2 * dis1) + (2 * dis2);
	}

	/**
	 * Translates the rectangle by a given vector.
	 * 
	 * @param vec The translation vector.
	 */
	@Override
	public void translate(Point_2D vec) {
		// Move the 4 vertices of the rectangle
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}

	/**
	 * Creates a deep copy of the rectangle.
	 * 
	 * @return A new Rect_2D object that is a copy of the rectangle.
	 */
	@Override
	public GeoShape copy() {
		Rect_2D newRect = new Rect_2D(_p1, _p2, _p3, _p4);
		return newRect;
	}

	/**
	 * Scales the rectangle relative to a given center point and by a given ratio.
	 * 
	 * @param center The center point for scaling.
	 * @param ratio The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
		_p4.scale(center, ratio);
	}

	/**
	 * Rotates the rectangle around a given center point by a given angle in degrees.
	 * 
	 * @param center The center point for rotation.
	 * @param angleDegrees The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// Rotate each rectangle point according to the center and angle
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
		_p4.rotate(center, angleDegrees);
	}

	/**
	 * Retrieves the vertices of the rectangle as an array of Point_2D objects.
	 * The vertices are ordered in a clockwise manner.
	 * 
	 * @return An array containing the vertices of the rectangle.
	 */
	public Point_2D[] getPoints() {
		Point_2D[] ans = new Point_2D[4];
		ans[0] = new Point_2D(_p1);
		ans[1] = new Point_2D(_p2);
		ans[2] = new Point_2D(_p3);
		ans[3] = new Point_2D(_p4);
		return ans;
	}

	/**
	 * Helper method to determine whether a point p3 is to the left of the line segment formed by points p1 and p2.
	 * 
	 * @param p1 The first endpoint of the line segment.
	 * @param p2 The second endpoint of the line segment.
	 * @param p3 The point to check.
	 * @return 0 if the point is on the line, -1 if it is to the left, 1 otherwise.
	 */
	private int isLeft(Point_2D p1, Point_2D p2, Point_2D p3) {
		double maxY = p1.y() > p2.y() ? p1.y() : p2.y();
		double minY = p1.y() < p2.y() ? p1.y() : p2.y();
		double m = (p2.y() - p1.y()) / (p2.x() - p1.x());
		double y = m * (p3.x() - p1.x()) + p1.y();

		if (y == p3.y() || p1.equals(p3) || p2.equals(p3)) {
			return 0; // The point is on the line
		} else if (y < p3.y() && p3.y() <= maxY && p3.y() > minY) {
			return -1; // The point is to the left of the line
		} else {
			return 1; // The point is to the right of the line
		}
	}
}