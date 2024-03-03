
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}
	//This method is removes and returns the GUI_Shape object at the specified
	//index i from the collection of shapes.
	//It  takes an integer parameter i representing the index of the element to be removed
	@Override
	public GUI_Shape removeElementAt(int i) {
		//This removes the element at index i from the ArrayList:
		GUI_Shape removedItem=_shapes.remove(i);
		return removedItem;
	}
	//This method adds a GUI_Shape object at a specified index i in the collection of shapes:
	@Override
	public void addAt(GUI_Shape s, int i) {
		_shapes.add(i,s);
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	//The copy method creates a new instance of
	//ShapeCollection to store the copied shapes.
	@Override
	public GUI_Shape_Collection copy() {
		//Creates a new instance of ShapeCollection called copyCollection to store the copied shapes:
		ShapeCollection copyCollection = new ShapeCollection();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape shape = _shapes.get(i);
			//It calls the copy method on each GUI_Shape object to create a copy of the shape:
			GUI_Shape copyShape = shape.copy();
			copyCollection.add(copyShape);
		}
		return copyCollection;

	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {

		Collections.sort(_shapes,comp);

	}

	@Override
	public void removeAll() {
		_shapes.clear();
	}

	//	The save function is responsible for saving the shapes to a file.
	//Iterates over the shapes, converts each shape to a string representation using the toString() method,
	//and writes the strings to the file,finally, it closes the file. 
	//This function is used to persist the shapes to a file for future retrieval or usage.
	//It takes a file parameter, which is the name or path of the file where the shapes will be saved.
	//The function is use the try block,that his role is to enclose the section of a code that may throw
	//an exception.

	@Override
	public void save(String file) {
		//The try block is used to wrap the code that performs file I/O operations. 
		//It is used to catch any exceptions that may be thrown during the execution of that code.
		try {
			//Passes the file parameter to its constructor. 
			//The false parameter passed to the constructor indicates that the file should 
			//be overwritten if it already exists:
			FileWriter fileWrite=new FileWriter(file,false);
			for(int i=0;i<_shapes.size();i++) {
				//Inside the loop, it calls the toString() method on each shape and writes the resulting
				//string to the file using the write method of the FileWriter object:
				fileWrite.write(_shapes.get(i).toString()+"\n");
			}
			//The close method is ensures that all the data is 
			//written to the file and releases any system resources associated with the file:
			fileWrite.close();
		}
		//If an IOException occurs during the file operations,it catches the exception and prints
		//the stack trace using the printStackTrace() method:
		catch(IOException e) {
			e.printStackTrace();
		}	

	}

	@Override
	public void load(String file) {
		this.removeAll();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String s = br.readLine();
			while (s != null) {
				String[] sArr = s.split(",");

				if (sArr.length > 4) {
					Color c = new Color(Integer.valueOf(sArr[1]));
					boolean f = Boolean.valueOf(sArr[2]);
					int t = Integer.valueOf(sArr[3]);
					GeoShape g;
					GUI_Shape gs;
					Point_2D p1;
					Point_2D p2;
					Point_2D p3;
					Point_2D p4;
					ArrayList<Point_2D> pArr = new ArrayList<>();
					Point_2D p;
					Rect_2D rect;
					Triangle_2D triangle;

					switch (sArr[4]) {
					case "Circle_2D":
						if (sArr.length >= 8) {
							p1 = new Point_2D(Double.valueOf(sArr[5]), Double.valueOf(sArr[6]));
							double radius = Double.valueOf(sArr[7]);
							g = new Circle_2D(p1, radius);
							gs = new GUIShape(g, f, c, t);
							this.add(gs);
						}
						break;

					case "Triangle_2D":
						if (sArr.length >= 8) {
							p1 = new Point_2D(Double.valueOf(sArr[5]), Double.valueOf(sArr[6]));
							p2 = new Point_2D(Double.valueOf(sArr[7]), Double.valueOf(sArr[8]));
					        p3 = new Point_2D(Double.valueOf(sArr[9]), Double.valueOf(sArr[10]));

							pArr.add(p1);
							pArr.add(p2);
							pArr.add(p3);

							g = new Triangle_2D(p1,p2,p3);
							gs = new GUIShape(g, f, c, t);
							this.add(gs);
						}
						break;

					case "Rect_2D":
						if (sArr.length >= 10) {
							p1 = new Point_2D(Double.valueOf(sArr[5]), Double.valueOf(sArr[6]));
							p2 = new Point_2D(Double.valueOf(sArr[7]), Double.valueOf(sArr[8]));
							p3 = new Point_2D(Double.valueOf(sArr[9]), Double.valueOf(sArr[10]));
					        p4 = new Point_2D(Double.valueOf(sArr[11]), Double.valueOf(sArr[12]));

							pArr.add(p1);
							pArr.add(p2);
							pArr.add(p3);
							pArr.add(p4);

							g = new Rect_2D(p1,p2,p3,p4);
							gs = new GUIShape(g, f, c, t);
							this.add(gs);
						}
						break;




					case "Point_2D":
						if (sArr.length >= 7) {
							p1 = new Point_2D(Double.valueOf(sArr[5]), Double.valueOf(sArr[6]));
							g = (GeoShape) new Point_2D(p1);
							gs = new GUIShape(g, f, c, t);
							this.add(gs);
						}
						break;

					case "Polygone_2D":
						if (sArr.length >= 6) {
							int numPoints = Integer.valueOf(sArr[5]);
							if (sArr.length == 5 + 2 * numPoints) {
								for (int i = 0; i < numPoints; i++) {
									int xIndex = 5 + 2 * i;
									int yIndex = xIndex + 1;
									double x = Double.valueOf(sArr[xIndex]);
									double y = Double.valueOf(sArr[yIndex]);
									pArr.add(new Point_2D(x, y));
								}
								g = new Polygon_2D(pArr);
								gs = new GUIShape(g, f, c, t);
								this.add(gs);
							}
						}
						break;

					default:
						break;
					}
				}

				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}    }





	public int compare(GUI_Shape shape1, GUI_Shape shape2) {
		return Integer.compare(shape1.getTag(), shape2.getTag());
	}



	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}
