import java.util.ArrayList;
import java.util.List;

public class Polygon_2D implements GeoShape{
	//To store the vertices of the polygon,we use a List of type Point_2D:
	private List<Point_2D> points;

	//Default constructor:
	public Polygon_2D() {
		points=new ArrayList<>();
	}

	public Polygon_2D(ArrayList<Point_2D> pArr) { // Constructor
		points = new ArrayList<>();
		for (int i = 0; i < pArr.size(); i++) {
			points.add(new Point_2D(pArr.get(i)));
		}
	}
	//Creating a new Polygon_2D object by copying the vertices from another Polygon_2D object:
	public Polygon_2D(Polygon_2D po) {
		points=new ArrayList<>(po.points);
	}

	//This method returns an array of Point_2D objects representing all the vertices of the polygon:
	public Point_2D[] getAllPoints() {
		return points.toArray(new Point_2D[0]);
	}
	//Method that add new vertex to the polygon:
	public void add(Point_2D p) {
		points.add(p);
	}
	@Override
	public String toString(){
		return "Polygon_2D { points =" + points + "}" ;
	}

	@Override
	public boolean contains(Point_2D ot) {
		//Variables used to iterate over the polygon's vertices:
		int i, j;
		boolean containsPoint = false;
		int numPoints = points.size();

		for (i = 0, j = numPoints - 1; i < numPoints; j = i++) {
			Point_2D vertexI = points.get(i);
			Point_2D vertexJ = points.get(j);

			// Check if the point equals on an edge of the polygon
			if (vertexI.equals(ot) || vertexJ.equals(ot))
				return true;
			/*
			 *This part implements the ray casting algorithm.
			 * It checks for intersection between the ray cast from the point
			 * and the edge formed by vertexI and vertexJ.

			 */
			if ((vertexI.y() > ot.y()) != (vertexJ.y() > ot.y()) &&
					ot.x() < (vertexJ.x() - vertexI.x()) * (ot.y() - vertexI.y()) / (vertexJ.y() - vertexI.y()) + vertexI.x()) {
				containsPoint = !containsPoint;
			}
		}

		return containsPoint;
	}

	@Override
	public double area() {
		//Stores the total number of vertices in the polygon:
		int numPoints = points.size();
		double area = 0.0;
		for (int i = 0; i < numPoints; i++) {
			Point_2D currentPoint = points.get(i);//Represents the current vertex.
			Point_2D nextPoint = points.get((i + 1) % numPoints); //Represents the next vertex in a cyclic manner.
			// Gives the signed area contribution
			// of the edge in the positive direction (from left to right) along the x-axis:
			area=area+ currentPoint.x() * nextPoint.y();
			// Gives the signed area contribution
			//of the edge in the negative direction (from right to left) along the x-axis:
			area=area- currentPoint.y() * nextPoint.x();

		}
		return Math.abs(area/2.0);
	}
	@Override
	public double perimeter() {
		int numPoints = points.size();//The number of the points in the polygon.
		double per = 0.0;
		for (int i = 0; i < numPoints; i++) {
			Point_2D currentPoint = points.get(i);//Recall the current vertex.
			Point_2D nextPoint = points.get((i + 1) % numPoints);//Recalling the next vertex in a cyclic manner.
			double edgeLength = currentPoint.distance(nextPoint);//Calculating the distance between the vertices
			per=per+ edgeLength;
		}
		return per;
	}
	//This method takes a Point_2D object representing the translation vector:
	@Override
	public void translate(Point_2D vec) {
		//Iterates over each vertex in the polygon:
		for (int i = 0; i < points.size(); i++) {
			Point_2D p = points.get(i);
			//Calculate the new Point with adding the vector:
			double newX = p.x() + vec.x();
			double newY = p.y() + vec.y();
			Point_2D newP=new Point_2D(newX,newY);
			//Set the new Point:
			points.set(i, newP);
		}
	}

	@Override
	public GeoShape copy() {
		//Creating a new polygon_2D object to store the copied polygon:
		Polygon_2D copyPolygon = new Polygon_2D();
		//Iterates over each vertex in the polygon:
		for (int i = 0; i < points.size(); i++) {
			Point_2D p = points.get(i);
			//This creates a copy of the vertex:
			Point_2D copyPoint = new Point_2D(p.x(), p.y());
			copyPolygon.add(copyPoint);
		}
		return copyPolygon;


	}

	@Override
	public void scale(Point_2D center, double ratio) {
		// Iterate over each vertex in the polygon:
		for (int i = 0; i < points.size(); i++) {
			Point_2D p = points.get(i);
			// Calculate the distance between the current vertex and the center:
			double distance = p.distance(center);
			// Calculate the scaled distance
			double scaledDistance = distance * ratio;
			//Calculate the direction between the current vertex to the center:
			double direction = Math.atan2(p.y() - center.y(), p.x() - center.x());
			// Calculate the new coordinates of the vertex after scaling:
			double newX = center.x() + scaledDistance * Math.cos(direction);
			double newY = center.y() + scaledDistance * Math.sin(direction);

			// Set the new coordinates for the vertex
			Point_2D newP = new Point_2D(newX, newY);
			points.set(i, newP);
		}

		}
		@Override
		public void rotate(Point_2D center, double angleDegrees) {
			// Convert the angle from degrees to radians:
		    double angleRadians = Math.toRadians(angleDegrees);

		    // Iterate over each vertex in the polygon:
		    for (int i = 0; i < points.size(); i++) {
		        Point_2D p = points.get(i);
		        
		        // Calculate the distance between the current vertex and the center:
		        double distance = p.distance(center);
		        // Calculate the direction between the current vertex and the center
		        double direction = Math.atan2(p.y() - center.y(), p.x() - center.x());
		        // Add the rotation angle to the current direction
		        double newDirection = direction + angleRadians;
		        
		        // Calculate the new coordinates of the vertex after rotation
		        double newX = center.x() + distance * Math.cos(newDirection);
		        double newY = center.y() + distance * Math.sin(newDirection);
		        
		        // Set the new coordinates for the vertex
		        Point_2D newP = new Point_2D(newX, newY);
		        points.set(i, newP);
		    }
		}
		public Point_2D[] getPoints() { 
			// TODO Auto-generated method stub
			Point_2D[] ans = new Point_2D[points.size()]; //creating an array of points
			for (int i = 0; i < points.size(); i++) {
				ans[i] = points.get(i);  //insert each point to appropriate position in the new array
			}
			return ans;
		}

	}
