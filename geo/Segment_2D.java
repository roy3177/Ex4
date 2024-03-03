import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private Point_2D _a;
	private Point_2D _b;

	public Segment_2D(Point_2D a, Point_2D b) {
		this._a=new Point_2D(a);
		this._b=new Point_2D(b);
	}
	public Segment_2D(Segment_2D t1) {
		this._a=new Point_2D(t1._a);
		this._b=new Point_2D(t1._b);
	}

	
	public Point_2D get_p1() {
		return _a;
	}
	public Point_2D get_p2() {
		return _b;
	}
	@Override
	public String toString() { //creating an ArrayList of shape type and Point2D segment points 
		ArrayList<String> sArr = new ArrayList<>();
		sArr.add(this.getClass().getSimpleName());
		Point_2D[] points = this.getPoints();
		for (int i = 0; i < points.length; i++) {
			sArr.add(points[i].toString());
		}
		return String.join(",", sArr);
	}

	@Override
	//Checking if the point ot is in the segment:
	public boolean contains(Point_2D ot) {
		//calculates the cross-products of the slopes of the two line segments.
		//If the cross-products are equal, it indicates that the slopes are equal,
		//and hence the point ot lies on the line.
		if((_a.x() - ot.x()) * (ot.y() - _b.y()) == (ot.x() - _b.x()) * (_a.y() - ot.y())) {
			return true;
		}
		return false;


	}

	@Override
	public double area() {
		return 0;//There is not area for segment.
	}

	@Override
	//The distance between the two points in the segment
	public double perimeter() {
		return _a.distance(_b);
	}

	@Override
	public void translate(Point_2D vec) {
		_a.move(vec);
		_b.move(vec);
	}


	@Override
	public GeoShape copy() {
		return new Segment_2D(_a,_b);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		double dx = _b.x() - _a.x();
		double dy = _b.y() - _a.y();
		double scaledDx = dx * ratio;
		double scaledDy = dy * ratio;
		_a = new Point_2D(center.x() - scaledDx / 2, center.y() - scaledDy / 2);
		_b = new Point_2D(center.x() + scaledDx / 2, center.y() + scaledDy / 2);
	}


	@Override
	public void rotate(Point_2D center, double angleDegrees) {

		double angleRadians = Math.toRadians(angleDegrees);
		double cosTheta = Math.cos(angleRadians);
		double sinTheta = Math.sin(angleRadians);

		double dx1 = _a.x() - center.x();
		double dy1 = _a.y() - center.y();
		double dx2 = _b.x() - center.x();
		double dy2 = _b.y() - center.y();

		double newX1 = dx1 * cosTheta - dy1 * sinTheta;
		double newY1 = dx1 * sinTheta + dy1 * cosTheta;
		double newX2 = dx2 * cosTheta - dy2 * sinTheta;
		double newY2 = dx2 * sinTheta + dy2 * cosTheta;

		_a = new Point_2D(center.x() + newX1, center.y() + newY1);
		_b = new Point_2D(center.x() + newX2, center.y() + newY2);
	}
	public Point_2D getMinimumPoint() {
		// TODO Auto-generated method stub
		return _a;
	}
	public Point_2D getMaximumPoint() {
		// TODO Auto-generated method stub
		return _b;
	}
	public Point_2D[] getPoints() {
		// TODO Auto-generated method stub
		Point_2D[] ans = new Point_2D[2];
		ans[0] = new Point_2D(_a);
		ans[1] = new Point_2D(_b);
		return ans;
	}
}
