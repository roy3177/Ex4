
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point_2D _p1;
    private Point_2D _p2;
    private ArrayList<Point_2D> Polygon_Points = new ArrayList<>();
	private Comparator<GUI_Shape> byString;
	private Comparator<GUI_Shape> byAntiString;
	private Comparator<GUI_Shape> byTag;
	private Comparator<GUI_Shape> byAntiTag;
	private Comparator<GUI_Shape> byArea;
	private Comparator<GUI_Shape> byAntiArea;
	private Comparator<GUI_Shape> byPerimeter;
	private Comparator<GUI_Shape> byAntiPerimeter;
    private static Ex4 _winEx4 = null;

    private Ex4() {
        init(null);
    }

    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s.copy();
        }// //should be s.copy();}
        _gs = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    /**
     * checks the instance of gs, converts gs to shape, checks if filled (to the relevent shapes)
     * then draw the shape with stdDraw.
     */
    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex4.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();

        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }
        //segment
        if (gs instanceof Segment_2D) {
            Segment_2D segment = new Segment_2D((Segment_2D) gs);
            Point_2D a = segment.get_p1();
            Point_2D b = segment.get_p2();
            //there is no fill for segment.
            StdDraw_Ex4.line(a.x(), a.y(), b.x(), b.y());
        }
        if (gs instanceof Rect_2D) {
        	Rect_2D c = (Rect_2D) gs;
			Point_2D p1 = c.getPoints()[0];
			Point_2D p2 = c.getPoints()[1];
			Point_2D p3 = c.getPoints()[2];
			Point_2D p4 = c.getPoints()[3];
			double x[] = { p1.x(), p2.x(), p3.x(), p4.x() };
			double y[] = { p1.y(), p2.y(), p3.y(), p4.y() };
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			} else {
				StdDraw_Ex4.polygon(x, y);
			}
        }
        //triangle
        if (gs instanceof Triangle_2D) {
        	Triangle_2D c = (Triangle_2D) gs;
			Point_2D p1 = c.getAllPoints()[0];
			Point_2D p2 = c.getAllPoints()[1];
			Point_2D p3 = c.getAllPoints()[2];
			double x[] = { p1.x(), p2.x(), p3.x() };
			double y[] = { p1.y(), p2.y(), p3.y() };
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			} else {
				StdDraw_Ex4.polygon(x, y);
			}
		
        }
        if (gs instanceof Polygon_2D) {
            //get data
            Polygon_2D polygon = (Polygon_2D) gs;
            Point_2D[] c1 = polygon.getAllPoints();
            //builds array of points
            double[] x = new double[c1.length];
            double[] y = new double[c1.length];

            //set values
            for (int i = 0; i < c1.length; i++) {
                x[i] = c1[i].x();
                y[i] = c1[i].y();
            }

            if (isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            } else {
                StdDraw_Ex4.polygon(x, y);
            }
        }
    }

    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    public void actionPerformed(String p) {
    	_mode = p;
		if (p.equals("Blue")) {_color = Color.BLUE;setColor(_color);}
		if (p.equals("Red")) {	_color = Color.RED;	setColor(_color);}
		if (p.equals("Green")) {	_color = Color.GREEN;	setColor(_color);}
		if (p.equals("White")) {	_color = Color.WHITE;setColor(_color);}
		if (p.equals("Black")) {	_color = Color.BLACK;setColor(_color);}
		if (p.equals("Yellow")) {	_color = Color.YELLOW;	setColor(_color);}
		if (p.equals("Fill")) {	_fill = true;	setFill();}
		if (p.equals("Empty")) {	_fill = false;	setFill();	}
		if (p.equals("Clear")) {	_shapes.removeAll();	}
		if (p.equals("Remove")) {	remove();}
		if (p.equals("All")) {	selectAll();}
		if (p.equals("None")) {	selectNone();}
		if (p.equals("Anti")) {	selectAnti();	}
		if (p.equals("Save")) {		save();	}
		if (p.equals("Load")) {		load();	}
		if (p.equals("Info")) {	getInfo();}
		if (p.equals("ByArea")) {	sort(shapeComperator.CompByArea);	}
		if (p.equals("ByAntiArea")) {	sort(shapeComperator.CompByAntiArea);	}
		if (p.equals("ByPerimeter")) {	sort(shapeComperator.CompByPerimeter);	}
		if (p.equals("ByAntiPerimeter")) {	sort(shapeComperator.CompByAntiPerimeter);}
		if (p.equals("ByToString")) {	sort(shapeComperator.CompByToString);	}
		if (p.equals("ByAntiToString")) {	sort(shapeComperator.CompByAntiToString);	}
		if (p.equals("ByTag")) {		sort(shapeComperator.CompByTag);	}
		if (p.equals("ByAntiTag")) {		sort(shapeComperator.CompByAntiTag);	}
		
		drawShapes();

    }



    //chekc if there is still selected shapes that needs to be removed
    private boolean stillSelectedLeft() {
        for (int i = 0; i < _shapes.size(); i++) {
            if (_shapes.get(i).isSelected()) {
                return true;
            }

        }
        return false;

    }

    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);

        if (_mode.equals("Circle") || _mode.equals("Segment") || _mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Triangle")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else if (_p2 == null) {
                _p2 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
            }
        }
        if (_mode.equals("Polygon")) {
            if (_gs == null) {
                Polygon_Points.add(p);
                _p1 = new Point_2D(p);
                System.out.println("asdf");
            }
                Polygon_Points.add(p);
        }

        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                // GUIShape.copy(_p1);// we make copies
                _shapes.add(_gs.copy());
                _p1 = null;
            }
        }
        //scale by 90%
        if (_mode.equals("Scale_90%")) {
            for (int i = 0; i < _shapes.size(); i++)
                if (_shapes.get(i).isSelected())
                    _shapes.get(i).getShape().scale(p, 0.9);
        }
        if (_mode.equals("Scale_110%")) {
            for (int i = 0; i < _shapes.size(); i++)
                if (_shapes.get(i).isSelected())
                    _shapes.get(i).getShape().scale(p, 1.1);
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p2 = new Point_2D(p);
                Degree(_p1);
                _p2 = null;
                _p1 = null;
            }
        }

        if (_mode.equals("Point")) {
            select(p);
        }
        drawShapes();
    }

    //calculate the angle between two points
    private void Degree(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            // if ut is the chosen one do rotate.
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                //calculating
                double x = Math.abs(_p2.x() - p.x());
                double y = Math.abs(_p2.y() - p.y());
                double offset = 0;
                if (x < 0) {
                    offset = Math.PI;
                }
                double degrees = Math.atan(y / x) + offset;
                //rotate g.
                g.rotate(p, degrees);
            }
        }
    }

    private void save() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(null);
		File fileToSave = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileToSave = chooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());

		}
		_shapes.save(fileToSave.getAbsolutePath());
	}

    private void load() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			_shapes.load(chooser.getSelectedFile().getPath());
			drawShapes();
		}
	}

    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

	private void move() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				g.translate(_p1);
			}
		}
	}

	private void copy() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				GUI_Shape sCopy = s.copy();
				sCopy.getShape().translate(_p1);
				_shapes.add(sCopy);
			}
		}
	}

	private void scale(double ratio) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				g.scale(_p1, ratio);
			}
		}
	}

	private void rotate(double angleDegrees) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				g.rotate(_p1, angleDegrees);
			}
		}
	}

	private void remove() {
		int i = 0;
		while (i < _shapes.size()) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				_shapes.removeElementAt(i);
			} else {
				i++;
			}
		}
	}

	private void selectAll() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (g != null) {
				s.setSelected(true);
			}
		}
	}

	private void selectNone() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (g != null) {
				s.setSelected(false);
			}
		}
	}

	private void selectAnti() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (g != null) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
		drawShapes();
	}
    public void mouseRightClicked(Point_2D p) {
        System.out.println("right click!");
//for polygon to stop need to fix
        if (_mode.equals("Polygon")) {
            _gs.setColor(_color);
            _gs.setFilled(_fill);
            _shapes.add(_gs);
            _gs = null;
            _p1 = null;
            Polygon_Points.clear();
            drawShapes();
        }
    }

    /**
     * define the next click of mouse on screen for the shape.
     *
     * @param e provides information about the mouse's position and other relevant data.
     */
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShape gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point_2D p = new Point_2D(x1, y1);//the second click of mouse.
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);//_p1 first click, p2 the new click.
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
            }
            if (_mode.equals("Triangle")) {
                if (_p2 == null) {
                    gs = new Segment_2D(_p1, p);
                } else {
                    gs = new Triangle_2D(_p1, _p2, p);
                }
            }
            if (_mode.equals("Polygon")) {
                Polygon_Points.set(Polygon_Points.size()-1, p);
                Polygon_2D poly = new Polygon_2D(Polygon_Points);
                gs = poly;
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    @Override
    public GUI_Shape_Collection getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }
}
