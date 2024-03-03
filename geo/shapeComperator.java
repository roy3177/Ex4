import java.util.Comparator;

/**
 * This class represents a Comparator for GUI_Shapes. It provides a linear order over GUI_Shapes based on different criteria.
 * The criteria for comparison are defined as constants in the Ex4_Const class.
 */
public class shapeComperator implements Comparator<GUI_Shape> {

	// Define comparators for different sorting criteria
	public static final Comparator<GUI_Shape> CompByToString = new shapeComperator(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shape> CompByAntiToString = new shapeComperator(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shape> CompByArea = new shapeComperator(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shape> CompByAntiArea = new shapeComperator(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shape> CompByPerimeter = new shapeComperator(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shape> CompByAntiPerimeter = new shapeComperator(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shape> CompByTag = new shapeComperator(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shape> CompByAntiTag = new shapeComperator(Ex4_Const.Sort_By_Anti_Tag);

	private int _flag;

	/**
	 * Creates a new shapeComperator with the specified flag.
	 * The flag represents the sorting criterion.
	 *
	 * @param flag The sorting criterion flag.
	 */
	public shapeComperator(int flag) {
		_flag = flag;
	}

	@Override
	public int compare(GUI_Shape o1, GUI_Shape o2) {
		int ans = 0;
		// Compare based on the specified sorting criterion
		if (_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		// Add your code for other sorting criteria below
		if (_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = -o1.toString().compareTo(o2.toString());
		}
		if (_flag == Ex4_Const.Sort_By_Area) {
			ans = getCompareValue(o1.getShape().area(), o2.getShape().area());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Area) {
			ans = -getCompareValue(o1.getShape().area(), o2.getShape().area());
		}
		if (_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = getCompareValue(o1.getShape().perimeter(), o2.getShape().perimeter());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			ans = -getCompareValue(o1.getShape().perimeter(), o2.getShape().perimeter());
		}
		if (_flag == Ex4_Const.Sort_By_Tag) {
			ans = getCompareValue(o1.getTag(), o2.getTag());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
			ans = -getCompareValue(o1.getTag(), o2.getTag());
		}
		// Return the result of the comparison
		return ans;
	}

	/**
	 * Helper method to compare two double values.
	 *
	 * @param d1 The first double value.
	 * @param d2 The second double value.
	 * @return 0 if d1 equals d2, 1 if d1 is greater than d2, -1 if d1 is smaller than d2.
	 */
	private int getCompareValue(double d1, double d2) {
		if (d1 == d2) {
			return 0;
		} else if (d1 > d2) {
			return 1;
		} else {
			return -1;
		}
	}
}