
/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public Circle_2D(Circle_2D g) {
		this._center=new Point_2D(g._center);
		this._radius=g.getRadius();	
	}
	public double getRadius() {
		return this._radius;}
	public Point_2D getCenter(){
		return _center;}
	 @Override
	    public String toString() {
		 String result=this.getClass().getSimpleName()+ ":,"+"("+ _center.toString()+")"+ "," + this._radius;
		 return result;
		}
	@Override
	public boolean contains(Point_2D ot) {
		//If the distance from ot(the point) to the center is bigger than the radius,the ot is not contains
		//in the circle:
		if(ot.distance(_center)>_radius) {
			return false;
		}
			return true;	
	}
	
	@Override
	public double area() {
		double area=Math.pow(_radius, 2)*Math.PI; // Calculate the area of circle :(r^2*3.14...)
		return area;
	}
	@Override
	public double perimeter() {
		//Calculate the perimeter of the circle:
		double perimeter=2*_radius*Math.PI;
		return perimeter;
	}
	@Override
	public void translate(Point_2D vec) {
		_center.move(vec); //Moving the vector.

	}
	@Override
	public GeoShape copy() {
		//Return a deep  copy of this GeoShape:
		return new Circle_2D(_center,_radius);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		//First we calculate the distance between the _center to the given center:
		double dis1=_center.distance(center);
		//Compute the new radius with the given ratio:
	    double newRadius = _radius * ratio;
	    // Calculate the scale factor for the distance to the center:
	    double scaleFactor = newRadius / _radius;
	    // Scale the distance to the center by the scaling factor:
	    double scaleDisToCenter = dis1 * scaleFactor;
	    
	    
	    // Calculate the new center,based on the scaled distance to the center:
	     double disX=_center.x()-center.x();
	     double disY=_center.y()-center.y();
	     double scaleX = center.x() + (disX * scaleFactor);
	     double scaleY = center.y() + (disY * scaleFactor);
	     Point_2D newCen=new Point_2D(scaleX,scaleY);
	    
	  // Update the center and radius of the circle
	     _center = newCen;
	     _radius = newRadius;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// First,we calculate the angle in radians:
	    double angleRadians = Math.toRadians(angleDegrees);
	    // Calculate the distance between the _center to the given center: 
	    double dis=_center.distance(center);
	    
	 // Calculating the angle between the current center and the given center:
	    double angleToCen = Math.atan2(_center.y() - center.y(), _center.x() - center.x());
	    
	    // Calculate the new angle ,by adding the rotation angle:
	    double newAngle = angleToCen + angleRadians;
	    
	    // Calculate the new center coordinates ,based on the rotated angle(angleRadians) and distance(dis):
	    double newX = center.x() + dis * Math.cos(newAngle);
	    double newY = center.y() + dis * Math.sin(newAngle);
	    
	    // Create a new Point_2D  with the new coordinates that I made above:
	    Point_2D newCen = new Point_2D(newX, newY);
	    
	    // Update the center of the circle:
	    _center = newCen;
	}
	public Point_2D[] getPoints() {
		Point_2D[] ans = new Point_2D[2];
		ans[0] = new Point_2D(this._center);
		ans[1] = new Point_2D(ans[0].x(), ans[0].y() + this._radius);
		return ans;
	}

	

}
