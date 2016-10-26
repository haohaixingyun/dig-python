// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets;

import ibm.widgets.ancestors.StatelessWidget;

import com.rational.test.ft.enabler.Browser;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.IScreen;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.util.regex.Regex;

/**
 * Description : Class for manipulating Table Objects in XDE<p>
 * 
 * The preferred method for using these objects is to create a new instance from either a mapped or a dynamically located object, like so:
 * 		<dd><code>WTable myTable = new WTable(Table_MappedName());</code><br>
 * OR<br>
 * 		<dd><code>WTable myTable = new WTable(sTableName, sProperty, sClass, BrowserPage());</code><br>
 * 
 * and then use it with the non-static methods, e.g.<br>
 * 		<dd><code>myTable.click()</code><p>
 * 
 * Note that since this class inherits from Rational's StatelessGuiSubitemTestObject and GuiTestObject class, you can use all the methods available from these classes on a WTable object.
 * So, for example, you can also call:
 *		<dd><code>myTable.drag(pt1, pt2)</code><p>
 * 
 * @version 2.2
 * Last Modified: 08/05/04
 */
public class WTable extends StatelessWidget {
	
//***************************Constructors***************************************************************************
	/**
	* Constructor to store an explicitly specified table object <p>
	* @param table 	the StatelessGuiSubitemTestObject from which to construct the widget.
	*/
	public WTable(TestObject table) {
		super(table);
	}

//***********************************Instance methods*************************************************************
	/**
	* Returns the index of a record / row in the particular table stored in this object<p>
	* @param pattern	pattern being checked for
	* @param column		column being checked in
	* @return index if present; -1 if not
	* @author Chris Carlson
	*/
	public int getTableRow(String pattern, int column) {
		ITestDataTable myTable = getITestDataTable();
		boolean overallcheck = false;
		int findrow = -1;
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String contents = myTable.getCell(row, column).toString();
			boolean match = pattern.equals(contents);
			if (match) {
				System.out.println("Match found in row " + row);
				overallcheck = true;
				findrow = row;
			}
		}

		return findrow;
	}

	/**
	* Returns the cell coordinates of a string match, specifying where search should start, in the particular table in this object
	* @param pattern		pattern being checked for
	* @param cell			starting point for coordinates to start searching at
	* @return coordinates if present; -1,-1 if not present -
	* 			returns first encountered instance of string
	* @author Chris Carlson
	*/
	public Cell findCell(String pattern, Cell cell) {
		ITestDataTable myTable = getITestDataTable();
		boolean overallcheck = false;
		if (myTable == null) {
			return new Cell(new Row(-1),new Column(-1));
		} else {
			for (int row = cell.getRow().getIndex().getIndex(); row < myTable.getRowCount(); row++) {
				for (int col = cell.getColumn().getIndex().getIndex(); col < myTable.getColumnCount(); col++) {
					if (myTable.getCell(row,col) == null) {continue;}
					else {
						String contents = myTable.getCell(row,col).toString();
						boolean match = new Regex(pattern).matches(contents);
						if (match) {
							overallcheck = true;
							return new Cell(new Row(row),new Column(col));
						}
					}
				}
			}
			return new Cell(new Row(-1),new Column(-1));
		}
	}

	/**
	* Returns the cell coordinates of a string match in the particular table in this object, starting at (0,0)
	* @param pattern		pattern being checked for
	* @return coordinates if present; -1,-1 if not present -
	* 			returns first instance of string
	* @author Chris Carlson
	*/
	public Cell findCell(String pattern) {
 		return findCell(pattern, new Cell(new Row(0),new Column(0)));
	}

	/**
	* Returns the cell coordinates of a string match in the particular table in this object, starting at (0,0),
	* @param pattern		pattern being checked for
	* @return x coordinate as an int if present; -1 if not present
	* 			returns first instance of string
	* @author Chris Carlson
	*/
	public int findRow(String pattern) {
 		return findCell(pattern,new Cell(new Row(0),new Column(0))).getRow().getIndex().getIndex();
 	}

	/**
	* Returns the cell coordinates of a string match in the particular table in this object, starting at (0,0),
	* @param pattern		pattern being checked for
	* @return y coordinate as an int if present; -1 if not present
	* 			returns first instance of string
	* @author Chris Carlson
	*/
	public int findColumn(String pattern) {
 		return findCell(pattern,new Cell(new Row(0),new Column(0))).getColumn().getIndex().getIndex();
 	}


	/**
	* Prints the contents of the particular table in this object
	* @author Chris Carlson
	*/
	public void printTableContents() {
		ITestDataTable myTable = getITestDataTable();
		//Print out total rows & columns.
		System.out.println("Total Rows: " + myTable.getRowCount());
		System.out.println("Total Cols: " + myTable.getColumnCount());
		//Print out cell values
		for (int row = 0; row < myTable.getRowCount(); row++) {
			for (int col = 0; col < myTable.getColumnCount(); col++) {
			/*	System.out.println(
					"Value at cell ("
						+ row
						+ ","
						+ col
						+ ") is: "
						+ myTable.getCell(row, col));
						*/
			}
		}
	}


	/**
	* Returns the contents of a cell in the particular table in this object
	* @param cell		coordinates of desired cell
	* @return contents of the cell
	* @author Chris Carlson  -- modified 01/06/04 by CC (added null check from Ann Kopren)
	*/
	public String getAndPrintCellContents(Cell cell) {
		ITestDataTable myTable = getITestDataTable();
		System.out.println(
					"Value at cell ("
						+ cell.toString()
						+ ") is: "
						+ myTable.getCell(cell.getRow().getIndex().getIndex(), cell.getColumn().getIndex().getIndex()));
		//aek - null check
		Object obj = myTable.getCell(cell.getRow().getIndex().getIndex(), cell.getColumn().getIndex().getIndex());
		if (obj != null)
			return obj.toString();
		else
			return null;
	}

	/**
	* Returns the contents of a cell in the particular table in this object
	* @param cell		coordinates of desired cell
	* @return contents of the cell
	* @author Jon Davis -- modified 01/06/04 by CC (added null check from Ann Kopren)
	*/
	public String getCellContents(Cell cell) {
		ITestDataTable myTable = getITestDataTable();
		//aek - null check
		Object obj = myTable.getCell(cell.getRow().getIndex().getIndex(), cell.getColumn().getIndex().getIndex());
		if (obj != null)
			return obj.toString();
		else
			return null;
	}

	/**
	* Returns the contents of a cell in the particular table in this object
	* @param x		x coordinate of desired cell
	* @param y		y coordinate of desired cell
	* @return contents of the cell
	* @author Chris Carlson -- modified 01/06/04 by CC (added null check from Ann Kopren)
	*/
	public String getCellContents(int x,int y) {
		ITestDataTable myTable = getITestDataTable();
		/*System.out.println(
					"Value at cell ("
						+ x
						+ ","
						+ y
						+ ") is: "
						+ myTable.getCell(x, y));*/
		//aek - null check
		if (myTable.getCell(x,y) != null)
			return myTable.getCell(x, y).toString();
		else
			return null;
	}

	/**
	* Returns in an array the non-null, non-whitespace only cell contents of a row in the particular table in this object
	* @param row		row to investigate
	* @return String[100][2]: array of size 100 to store non-whitespace non-null cell contents in [x][1]
	* 			and column in [x][2]  and row in [x][3] and number of matches in [0][0]
	* @author Chris Carlson
	*/
	public String[][] getRowContents(int row) {
		ITestDataTable myTable = getITestDataTable();
		System.out.println("Total Columns: " + myTable.getColumnCount());
		Regex reg = new Regex("[:alnum:]|=|\\.|>|<|/|:|_|-");
		int entryno=0;
		String[][] entries=new String[100][4];
		for (int col = 0; col < myTable.getColumnCount(); col++) {
				if (myTable.getCell(row, col) == null)	{}
				else if (reg.matches(myTable.getCell(row, col).toString())) {
					String s = (myTable.getCell(row, col).toString());
					entries[entryno][1]=(myTable.getCell(row, col).toString().trim());
					entries[entryno][2]=Integer.toString(col);
					entries[entryno][3]=Integer.toString(row);
					entryno++;
					System.out.println(
					"Nonempty cell found at ("
						+ row
						+ ","
						+ col
						+ "): '"
						+ myTable.getCell(row, col)
						+ "'");
				}
				else {}
			}
		entries[0][0] = Integer.toString(entryno);
		System.out.println(entryno + " matches found in row.");
		return entries;
	}

	/**
	* Returns the ITestDataTable object for the particular table in this object
	* @return ITestDataTable
	* @author Chris Carlson -- modified 01/06/04 by CC (added null check from Ann Kopren)
	*/
	public ITestDataTable getITestDataTable() {
		ITestDataTable myTable = null;
		//aek - didn't work for swing
		if (getTestDataTypes().containsKey("grid"))
			myTable = (ITestDataTable) getTestData("grid");
		else if (getTestDataTypes().containsKey("contents"))
			myTable = (ITestDataTable) getTestData("contents");
		return myTable;
	}
	
	/**
	* Returns a link in this table that has text that matches a string
	* @param s		the search string, matched to the text of the link
	* @return link that matches the string
	* @author Jon Davis-- modified 01/06/04 by CC
	*/
	public TestObject getLinkFromTable(String s) {
		return getTestObjectInTable(s,ObjectFactory.gsHtmlLinkProp,ObjectFactory.gsHtmlLinkClass);
	}
	
	/**
	* Returns a link in this table that has text that matches this regular expression
	* @param re		the regular expression to match the text of the link
	* @return link that matches the string
	* @author tsnow
	*/
	public TestObject getLinkFromTable(Regex re) {
		return getTestObjectInTable(re, ObjectFactory.gsHtmlLinkProp,ObjectFactory.gsHtmlLinkClass);
	}
	
	/**
	* Returns HTML static text in this table that matches a string
	* @param s		the search string, matched to the text of the link
	* @return text that matches the string
	* @author tsnow
	*/
	public TestObject getHtmlStaticTextFromTable(String s) {
		return getTestObjectInTable(s,ObjectFactory.gsHtmlStaticTextProp,ObjectFactory.gsHtmlStaticTextClass);
	}
	
	/**
	* Returns HTML static text in this table that matches this regular expression
	* @param re		the regular expression to match the text of the link
	* @return link that matches the string
	* @author tsnow
	*/
	public TestObject getHtmlStaticTextFromTable(Regex re) {
		return getTestObjectInTable(re, ObjectFactory.gsHtmlStaticTextProp,ObjectFactory.gsHtmlStaticTextClass);
	}

	/**
	* Returns the first TestObject found in the table with the specified characteristics
	* @param sValue			value to search for
	* @param sPropertyName		property to search in
	* @param sClassID			TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class)
	* @return found TestObject that matches criteria
	* @author Chris Carlson
	*/
	public TestObject getTestObjectInTable(String sValue, String sPropertyName, String sClassID) {
				//find the object
		return ObjectFactory.findTestObject(sValue, sPropertyName, sClassID, this);
	}
	
	/**
	* Returns the first TestObject found in the table with the specified characteristics
	* @param sValue			value to search for
	* @param sPropertyName		property to search in
	* @param sClassID			TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class)
	* @param classIndex		property value of class index
	* @return found TestObject that matches criteria
	* @author Chris Carlson
	*/
	public TestObject getTestObjectInTable(String sValue, String sPropertyName, String sClassID, String classIndex) {
				//find the object
		return ObjectFactory.findTestObject(sValue, sPropertyName, sClassID, classIndex, this);
	}
	
	/**
	* Returns the first TestObject found in the table with the specified characteristics
	* @param value				value to search for
	* @param sPropertyName		property to search in
	* @param sClassID			TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class)
	* @return found TestObject that matches criteria
	* @author tsnow
	*/
	public TestObject getTestObjectInTable(Regex value, String sPropertyName, String sClassID) {
				//find the object
		return ObjectFactory.findTestObject(value, sPropertyName, sClassID, this);
	}
	
	/**
	* Returns the first TestObject found in the table with the specified characteristics
	* @param value				value to search for
	* @param sPropertyName		property to search in
	* @param sClassID			TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class)
	* @param classIndex		property value of class index
	* @return found TestObject that matches criteria
	* @author tsnow
	*/
	public TestObject getTestObjectInTable(Regex value, String sPropertyName, String sClassID, String classIndex) {
				//find the object
		return ObjectFactory.findTestObject(value, sPropertyName, sClassID, classIndex, this);
	}

	/**
	* Returns the TestObject on the same row as the test object passed in.<p>
	* 
	* This method is useful when you have the same object type in all the rows in a single column of the table 
	* and need to locate a one of these objects in particular by locating some verbiage in a different column of the table. on the same row as the test object passed in.<p>
	* For example, in the inbox of a web mail application (e.g. Yahoo! mail), you often have checkboxes in the first column of the inbox table, 
	* and each checkbox matches up with a corresponding mail message with other properties of the mail message displayed in the other columns of the table, 
	* like a link with text corresponding to the subject of the message. You can use this method to find the particular checkbox that matches the message with that particular subject
	* by finding the subject link (using WTable#findLinkInTable()), and then use this method to find the checkbox on the same row as that link in order to mark that particular message.
	* 
	* @param iChild			the child number of the TestObject you are looking for (0-based) (should be 0 unless there is more than one child in the cell)
	* @param iCol				the column of the object you are looking for (0-based).
	* @param sibling			the TestObject which identifies the row you are looking for.
	* @return the TestObject on the same row as the object entered (null if not found).
	* @author Tim Snow
	*/	
	public TestObject getTestObjectInSameRowAs(int iChild, int iCol, TestObject sibling)
	{
		try {
			//go up two levels to get the table row. The immediate parent is the cell (table data), the parent above that is the row of the table.
			TestObject row = sibling.getParent().getParent(); 
			//then get the cell (table data) by picking out the child of the appropriate column
			TestObject cell = row.getChildren()[iCol]; 
			//then get the particular object you are looking for in that cell by picking out the correct child
			return cell.getChildren()[iChild]; 
		} catch (Exception e) {
			return null;
		}
	}	

	/**
	* Returns the first TestObject of the indicated class in the indicated column of the row that contains the sibling object.<p>
	* 
	* This method is useful when you have the same object type in all the rows in a single column of the table 
	* and need to locate a one of these objects in particular by locating some verbiage in a different column of the table. on the same row as the test object passed in.<p>
	* For example, in the inbox of a web mail application (e.g. Yahoo! mail), you often have checkboxes in the first column of the inbox table, 
	* and each checkbox matches up with a corresponding mail message with other properties of the mail message displayed in the other columns of the table, 
	* like a link with text corresponding to the subject of the message. You can use this method to find the particular checkbox that matches the message with that particular subject
	* by finding the subject link (using WTable#findLinkInTable()), and then use this method to find the checkbox on the same row as that link in order to mark that particular message.
	* 
	* @param sClass			the class of the TestObject you are looking for 
	* @param iCol				the column of the object you are looking for (0-based).
	* @param sibling			the TestObject which identifies the row you are looking for.
	* @return the TestObject on the same row as the object entered (null if not found).
	* @author Tim Snow
	*/	
	public TestObject getTestObjectInSameRowAs(String sClass, int iCol, TestObject sibling)
	{
		//go up two levels to get the table row. The immediate parent is the cell (table data), the parent above that is the row of the table.
		TestObject row = sibling.getParent().getParent(); 
		//then get the cell (table data) by picking out the child of the appropriate column
		TestObject cell = row.getChildren()[iCol]; 
		//then get the particular object you are looking for in that cell
		return getFirstChildOfThisClass(sClass, cell);
	}
	
	protected TestObject getFirstChildOfThisClass(String sClass, TestObject to)
	{
		TestObject rto = null;
		TestObject[] children = to.getChildren(); 
		for (int i = 0; i < children.length; i++)
		{			
			if (ObjectFactory.getClassName(children[i]).equals(sClass))
				rto = children[i];
		}
		
		return rto;
	}
		
	
	
	
	
	/**
	* Clicks on the object found by the intersection of the x coord of the first object and y coord of the second<p>
	* 
	* Like the getTestObjectInSameRowAs, this method is useful when you have the same object type in all the rows in a single column of the table 
	* and need to locate a one of these objects in particular by locating some verbiage in a different column of the table. on the same row as the test object passed in.<p>
	* However, sometimes the object and the verbiage are in different, embedded tables, so they aren't actually in the same row of the table although they appear to be. 
	* This is often the case in WebSphere generated HTML apps. 
	* 
	* 
	* @param toInColumn		the TestObject the identifies the column of the window you want to click on 
	* @param toInRow				the TestObject the identifies the column of the window you want to click on
	* @author Tim Snow
	*/	
	public void clickTestObjectInSameColumnAsAndRowAs(GuiTestObject toInColumn, GuiTestObject toInRow)
	{
		//get the rectangle of the row object
		java.awt.Rectangle rRow = toInRow.getScreenRectangle();
		//get the rectangle of the column object
		java.awt.Rectangle rColumn = toInColumn.getScreenRectangle();
		//now calculate the point to click from the two rects
		java.awt.Point pPointToClick =	new java.awt.Point(rColumn.x + (rColumn.width/2), rRow.y + (rColumn.height/2));
		//click on that point which should click the checkbox corresponding to the item you want to delete
		IScreen screen = RationalTestScript.getScreen();
		screen.click(pPointToClick);

	}	

	/**
	* Returns the number of rows in this object<p>
	* @return number of rows
	* @author Jack Kipp
	*/
	public int getRowCount() {
		ITestDataTable myTable = getITestDataTable();
		return myTable.getRowCount();
	}

	/**
	* Returns the number of rows in this object<p>
	* @return number of rows
	* @author Jack Kipp
	*/
	public int getColumnCount() {
		ITestDataTable myTable = getITestDataTable();
		return myTable.getColumnCount();
	}	
	
	/**
	* Returns the object found in the cell specified
	* @param row		integer representing the row
	* @param col		integer representing the column
	* @param sClassID 	TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @return 			found TestObject
	* @author jimjones
	*/
	public TestObject getCellObject(int row, int col, String sClassID) {
		TestObject rto = null;

		String target = "Html.TBODY";
		boolean keepLooking = true;
		int bodyIndex = -1;
		
		// find tbody object
		TestObject[] body = this.getChildren();
		for (int a = 0; a < body.length && keepLooking; a++)
			if (body[a].getObjectClassName().equals(target)) {
				bodyIndex = a;
				keepLooking = false;
			}
		
		// find cell
		if (bodyIndex > -1) {
			TestObject[] rows = body[bodyIndex].getChildren();
			TestObject[] columns = rows[row].getChildren();
			TestObject[] cell = columns[col].getChildren();
			keepLooking = true;

			// find object in cell that matches objectClass
			for (int a = 0; a < cell.length && keepLooking; a++) {
				try {
					TestObject to = ObjectFactory.findTestObject(sClassID, ".class", sClassID, cell[a]);
					if (to.getObjectClassName().equals(sClassID))
						keepLooking = false;
					rto = to;
				} catch (java.lang.NullPointerException e) {
					/* ignoring*/
				}
			}
		}

		return rto;
	}

	/**
	 * Method getCellCheckbox.
	 * @param row
	 * @param col
	 * @return WCheckBox
	 * @author jimjones
	 */
	public WCheckBox getCellCheckbox(int row, int col) {
		TestObject to = getCellObject(row, col, ObjectFactory.gsHtmlCBClass);
		WCheckBox rto = null;
		if (to.getObjectClassName().equals(ObjectFactory.gsHtmlCBClass))
			rto = new WCheckBox(to);
		return rto;
	}
	
	/**
	 * Method getCellImage.
	 * @param row
	 * @param col
	 * @return WCheckBox
	 * @author jimjones
	 */
	public WImage getCellImage(int row, int col) {
		TestObject to = getCellObject(row, col,ObjectFactory.gsHtmlImageClass);
		WImage rto = null;
		if (to.getObjectClassName().equals(ObjectFactory.gsHtmlImageClass))
			rto = new WImage(to);
		return rto;
	}
	
	/**
	 * Method getCellLink.
	 * @param row
	 * @param col
	 * @return WCheckBox
	 * @author jimjones
	 */
	public WLink getCellLink(int row, int col) {
		TestObject to = getCellObject(row, col, ObjectFactory.gsHtmlLinkClass);
		WLink rto = null;
		if (to.getObjectClassName().equals(ObjectFactory.gsHtmlLinkClass))
			rto = new WLink(to);
		return rto;
	}
	
	/**
	 * Method getCellText.
	 * @param row
	 * @param col
	 * @return WCheckBox
	 * @author jimjones
	 */
	public WStaticText getCellText(int row, int col) {
		TestObject to = getCellObject(row, col, ObjectFactory.gsHtmlTAClass);
		WStaticText rto = null;
		if (to.getObjectClassName().equals(ObjectFactory.gsHtmlStaticTextClass))
			rto = new WStaticText(to);
		return rto;
	}

	
	
//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return type of widget
	 */		
	public String getWidgetType() {
		return "table";
	}


//***************************************Static Methods***************************************************************
	/**
	* Returns the index of a record / row in a table
	* @param table		object (table) being checked in
	* @param pattern	pattern being checked for
	* @param column	column being checked in
	* @return index if present; -1 if not
	*/
	public static int getTableRow(TestObject table, String pattern, int column) {
		return new WTable(table).getTableRow(pattern, column);
	}


	/**
	* Returns the cell coordinates of a string match in a table, with specifying where search should start
	* @param table		object (table) being checked in
	* @param pattern	pattern being checked for
	* @param cell		starting point for coordinates to start searching at
	* @return coordinates if present; -1,-1 if not present -
	* 			returns first encountered instance of string
	*/
	public static Cell findCell(TestObject table, String pattern, Cell cell) {
		return new WTable(table).findCell(pattern, cell);
	}

	/**
	* Returns the cell coordinates of a string match in a table, starting at (0,0)
	* @param table		object(table) being checked in
	* @param pattern	pattern being checked for
	* @return coordinates if present; -1,-1 if not present -
	* 			returns first instance of string
	*/
	public static Cell findCell(TestObject table, String pattern) {
 		return findCell(table, pattern, new Cell(new Row(0),new Column(0)));
	}

	/**
	* Returns the cell coordinates of a string match in a table, starting at (0,0),
	* @param table		object(table) being checked in
	* @param pattern	pattern being checked for
	* @return x coordinate as an int if present; -1 if not present
	* 			returns first instance of string
	*/
	public static int findRow(TestObject table, String pattern) {
 		return new WTable(table).findRow(pattern);
	}

	/**
	* Returns the cell coordinates of a string match in a table, starting at (0,0),
	* @param table		object(table) being checked in
	* @param pattern	pattern being checked for
	* @return y coordinate as an int if present; -1 if not present
	* 			returns first instance of string
	*/
	public static int findColumn(TestObject table, String pattern) {
 		return new WTable(table).findColumn(pattern);
	}


	/**
	* Prints the contents of a table
	* @param table		object(table) being printed out
	*/
	public static void printTableContents(TestObject table) {
		new WTable(table).printTableContents();
	}

	/**
	* Returns the contents of a cell in a table
	* @param table		test object containing table
	* @param cell		coordinates of desired cell
	* @return contents of the cell
	*/
	public static String getAndPrintCellContents(TestObject table,Cell cell) {
		return new WTable(table).getAndPrintCellContents(cell);
	}


	/**
	* Returns the contents of a cell in a table
	* @param table		test object containing table
	* @param cell		coordinates of desired cell
	* @return contents of the cell
	*/
	public static String getCellContents(TestObject table,Cell cell) {
		return new WTable(table).getCellContents(cell);
	}

	/**
	* Returns the contents of a cell in a table
	* @param table		test object containing table
	* @param x			x coordinate of desired cell
	* @param y			y coordinate of desired cell
	* @return contents of the cell
	*/
	public static String getCellContents(TestObject table,int x,int y) {
		return new WTable(table).getCellContents(x,y);
	}


	/**
	* Returns in an array the non-null, non-whitespace only cell contents of a row in a table
	* @param table		test object containing table (will hold only 100 elements)
	* @param row		row to investigate
	* @return String[100][2] - array of size 100 to store non-whitespace non-null cell contents in [x][1]
	* 			and column in [x][2]  and row in [x][3] and number of matches in [0][0]
	*/
	public static String[][] getRowContents(TestObject table,int row) {
		return new WTable(table).getRowContents(row);
	}



	/**
	* Returns the ITestDataTable object for a table test object (doesn't work for swing)
	* @param table		test object
	* @return Returns ITestDataTable
	*/
	public static ITestDataTable getITestDataTable(TestObject table) {
		return new WTable(table).getITestDataTable();
	}

	/**
	* Returns a link in a table that has text that matches a string
	* @param table		table test object
	* @param s			the search string, matched to the text of the link
	* @return TestObject - link returned
	* */
	public static TestObject getLinkFromTable(TestObject table,String s) {
		return new WTable(table).getLinkFromTable(s);
	}


	/**
	* Returns the TestObject in the specified cell of a table
	* @param table				Table TestObject.
	* @param sValue			value to search for
	* @param sPropertyName		property to search in
	* @param sClassID			TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class)
	* @return found TestObject that matches criteria
	* */
	public static TestObject getTestObjectInTable(TestObject table, String sValue, String sPropertyName, String sClassID) {
		return new WTable(table).getTestObjectInTable(sValue, sPropertyName, sClassID);
	}

}