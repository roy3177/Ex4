/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */

import java.awt.Color;



public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	private Circle_2D circle;
	private Point_2D point;
	private Polygon_2D polygon;
	private Rect_2D rect;
	private Segment_2D segment;
	private Triangle_2D triangle;


	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	@Override
	public GeoShape getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		return "GUIShape,"+(-_color.getRGB()+1)+","+isFilled()+","+getTag()+","+getShape().toString();
	}
	private void init(String[] ww) {

	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShape g) {
		// TODO Auto-generated method stub
		_g = g.copy();

	}
	@Override
	public Integer getLayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
