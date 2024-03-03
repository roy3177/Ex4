import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeCollectionTests {
	// Define example colors
	private static final Color yellow = null;
	private static final Color red = null;
	private static final Color pink = null;

	// Define example shapes
	private Point_2D point;
	private Polygon_2D polygon;
	private Segment_2D segment;
	private Triangle_2D triangle;

	// Example shapes for testing
	Point_2D p1 = new Point_2D(1, 1);
	Triangle_2D t1 = new Triangle_2D(p1, p1, p1);
	Rect_2D r1 = new Rect_2D(p1, p1, p1, p1);
	Circle_2D c1 = new Circle_2D(p1, 2);

	// Example GUIShapes for testing
	private GUIShape gs1 = new GUIShape(c1, false, Color.black, 0); // Example GUIShape 1
	private GUIShape gs2 = new GUIShape(r1, true, Color.blue, 0); // Example GUIShape 2
	private GUIShape gs3 = new GUIShape(t1, true, Color.blue, 0); // Example GUIShape 3

	// Define comparators for sorting
	public static final Comparator<GUI_Shape> CompByToString = new shapeComperator(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shape> CompByAntiToString = new shapeComperator(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shape> CompByArea = new shapeComperator(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shape> CompByAntiArea = new shapeComperator(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shape> CompByPerimeter = new shapeComperator(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shape> CompByAntiPerimeter = new shapeComperator(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shape> CompByTag = new shapeComperator(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shape> CompByAntiTag = new shapeComperator(Ex4_Const.Sort_By_Anti_Tag);

	private ShapeCollection shapeCollection;

	@BeforeEach
	public void setup() {
		// Create a new ShapeCollection instance before each test
		shapeCollection = new ShapeCollection();
	}

	/*
	 * Tests the save function on the ShapeCollection class:
	 */
	@Test
	public void testSaveFunction() throws IOException {
		// Create a list of shapes to save
		List<GeoShape> shapes = new ArrayList<>();
		// Add some shapes to the list...

		// Create an instance of the class under test
		ShapeCollection object = new ShapeCollection();

		// Define the file path for the test
		String filePath = "test.text";

		// Call the save function
		object.save(filePath);

		// Read the contents of the file
		Path path = Paths.get(filePath);
		String fileContent = Files.readString(path);

		// Verify that the file content is as expected
		String expectedContent = ""; // Define the expected content based on the shapes list
		assertEquals(expectedContent, fileContent);

		// Verify that the file was created
		assertTrue(Files.exists(path));

		// Clean up the test file
		Files.delete(path);
	}

	/*
	 * Tests the removeElement function of the class shapeCollections:
	 */
	@Test
	public void testRemoveElement() {
		// Create a new ShapeCollection instance for testing
		ShapeCollection shapeCollection = new ShapeCollection();

		// Add some shapes to the collection for testing
		shapeCollection.add(gs1);
		shapeCollection.add(gs2);
		shapeCollection.add(gs3);

		// Ensure the initial size is correct
		assertEquals(3, shapeCollection.size());

		// Remove the shape at index 1
		GUI_Shape removedShape = shapeCollection.removeElementAt(1);

		// Ensure the size is updated correctly
		assertEquals(2, shapeCollection.size());

		// Ensure the removed shape is the expected one
		assertEquals(shapeCollection.get(1), gs3);
	}

	/*
	 * Tests the addAt function in the shapeCollection class:
	 */
	@Test
	public void testAddAt() {
		// Create a new ShapeCollection instance for testing
		ShapeCollection shapeCollection = new ShapeCollection();

		// Add some shapes to the collection for testing:
		shapeCollection.add(gs1);
		shapeCollection.add(gs2);

		// Ensure the initial size is correct
		assertEquals(2, shapeCollection.size());

		// Add shape at index 2
		 shapeCollection.addAt(gs3, 2);

		// Ensure the size is updated correctly
		assertEquals(3, shapeCollection.size());
	}

	/*
	 * Tests the add function in the shapeCollection class:
	 */
	@Test
	public void testAdd() {
		// Create a new ShapeCollection instance for testing
		ShapeCollection shapeCollection = new ShapeCollection();

		// Add some shapes to the collection for testing:
		shapeCollection.add(gs2);

		// Add shape to the shapeCollection:
		shapeCollection.add(gs1);

		// Ensure the size is updated correctly
		assertEquals(2, shapeCollection.size());
	}

	/*
	 * Tests the copy function in the shapeCollection class:
	 */
	@Test
	public void testCopy() {
		// Create a new ShapeCollection instance for testing
		ShapeCollection shapeCollection = new ShapeCollection();

		// Add some shapes to the collection for testing
		shapeCollection.add(gs1);
		shapeCollection.add(gs2);
		shapeCollection.add(gs3);

		// Create a copy of the shapeCollection
		ShapeCollection copyshapes = (ShapeCollection) shapeCollection.copy();

		// Compare the sizes of the two collections
		assertEquals(shapeCollection.size(), copyshapes.size());

		// Compare the individual shapes in the collections
		for (int i = 0; i < shapeCollection.size(); i++) {
			GUI_Shape originalShape = shapeCollection.get(i);
			GUI_Shape copiedShape = copyshapes.get(i);

			// Compare the individual shapes based on their fields
			assertEquals(originalShape.isFilled(), copiedShape.isFilled());
			assertEquals(originalShape.getColor(), copiedShape.getColor());
			assertEquals(originalShape.getTag(), copiedShape.getTag());
		}
	}

	/*
	 * Tests the removeAll function in the shapeCollection class:
	 */
	@Test
	public void testRemoveAll() {
		// Create a new ShapeCollection instance for testing
		ShapeCollection shapeCollection = new ShapeCollection();

		// Add some shapes to the collection for testing
		shapeCollection.add(gs1);
		shapeCollection.add(gs2);
		shapeCollection.add(gs3);

		// Remove all shapes from the collection
		shapeCollection.removeAll();

		// Ensure the size is updated correctly
		assertEquals(0, shapeCollection.size());
	}

	/*
	 * Tests the load function in the shapeCollection class:
	 */
	@Test
	public void testLoad() {
		// Load the shapes from a file
		shapeCollection.load("C:\\Users\\royme\\Documents\\rect");

		// Ensure the size of the collection is correct
		assertTrue(shapeCollection.size() == 1);

		// Ensure the properties of the loaded shape are correct
		assertEquals(shapeCollection.get(0).getTag(), 0);
		assertTrue(((Rect_2D) shapeCollection.get(0).getShape()).getPoints()[0].equals(new Point_2D(2.671875, 6.5)));
		assertTrue(((Rect_2D) shapeCollection.get(0).getShape()).getPoints()[1].equals(new Point_2D(7.171875, 6.5)));
		assertTrue(((Rect_2D) shapeCollection.get(0).getShape()).getPoints()[2].equals(new Point_2D(7.171875, 9.0625)));
		assertTrue(((Rect_2D) shapeCollection.get(0).getShape()).getPoints()[3].equals(new Point_2D(2.671875, 9.0625)));
	}
	
	@Test
	public void testSort() {
		// Create three circles with different radii
		Circle_2D circle1 = new Circle_2D(new Point_2D(0, 0), 5);
		Circle_2D circle2 = new Circle_2D(new Point_2D(0, 0), 7);
		Circle_2D circle3 = new Circle_2D(new Point_2D(0, 0), 3);

		// Create GUI shapes from the circles
		GUI_Shape guiShape1 = new GUIShape(circle1, true, Color.RED, 1);
		GUI_Shape guiShape2 = new GUIShape(circle2, false, Color.BLUE, 2);
		GUI_Shape guiShape3 = new GUIShape(circle3, true, Color.GREEN, 3);

		// Add the GUI shapes to the shape collection
		shapeCollection.add(guiShape1);
		shapeCollection.add(guiShape2);
		shapeCollection.add(guiShape3);

		// Sort the shape collection based on the area of the shapes
		shapeCollection.sort(CompByArea);

		// Ensure the size of the collection is correct
		assertEquals(3, shapeCollection.size());

		// Ensure the shapes are sorted correctly based on the area
		assertEquals(guiShape3, shapeCollection.get(0));
		assertEquals(guiShape1, shapeCollection.get(1));
		assertEquals(guiShape2, shapeCollection.get(2));
	}
}