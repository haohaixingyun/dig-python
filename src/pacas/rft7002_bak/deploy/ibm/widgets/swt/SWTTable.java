// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;


import ibm.loggers.control.PackageLoggingController;
import ibm.widgets.WTable;
import java.awt.Point;

import com.rational.test.ft.RationalTestException;
import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.List;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.util.regex.Regex;

/**
 * Description : Class for manipulating a SWT Table<p>
 *
 * Note that since this class inherits from Rational's StatelessGuiSubitemTestObject class, 
 * you can use all the methods available from StatelessGuiSubitemTestObject on a SWTTable object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTTable extends WTable {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTTable <p>
	 * @param to	the TestObject that references the SWT Table
	 */
	public SWTTable(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************	
	/**
	 * Retrieves the TableItem with the specified text, where the text represents text contained in any columns of the desired row <p>
	 * @param itemText		the text of the TableItem
	 * @return the table item as a SWTTableItem.  If a null is not returned, the returned object
	 * should be unregistered when no longer needed to avoid memory leaks.
	 * @see SWTTableItem for subsequent methods that can be called such as getChecked(), getGrayed(), and getText().
	 */
	public SWTTableItem getItem(String itemText) {
		return getItem(findRow(itemText));	
	}


	/**
	 * Retrieves the TableItem at the specified index <p>
	 * @param itemIndex	the 0-based index of the table item
	 * @return the table item as a SWTTableItem.  If a null is not returned, the returned object
	 * should be unregistered when no longer needed to avoid memory leaks.
	 * @see SWTTableItem for subsequent methods that can be called such as
	 * getChecked(), getGrayed(), and getText()
	 */
	public SWTTableItem getItem(int itemIndex) {
		Object[] parms = {new Integer(itemIndex)};
		TestObject item = (TestObject)(invoke("getItem",
									   "(I)Lorg/eclipse/swt/widgets/TableItem;",
										parms));	
		return new SWTTableItem(item);		
	}

	/**
	 * Identifies the number of TableItems that are contained in the table
	 * @return the number of TableItems contained in the table
	 */
	public int getItemCount() {		
		return ((Integer)getProperty("itemCount")).intValue();			
	}
	
	/**
	* Returns the ITestDataTable object for the particular table in this object <p>
	* @return ITestDataTable 
	* @see ITestDataTable for methods that can call, such as getRowCount(), getColumnCount(), and getColumnHeader()
	* */
	// NOTE: needed to override Table's getITestDataTable because received
	//       com.rational.test.ft.InvalidTestDataTypeException: Invalid Type: grid.
	//       when ran printContents().  So, changed getTestData("grid") to getTestData("contents")
	public ITestDataTable getITestDataTable() {
		ITestDataTable myTable;
		myTable = (ITestDataTable)getTestData("contents");
		return myTable;		
	}
	
	/**
	 * This method tries to locate a table row by first seeing if the
	 * argument, pattern, matches the text property of any table items in the table.
	 * If not, then the columns of each row are searched to see if pattern matches
	 * the content of those columns.<p>
	 * @param itemText		pattern being checked for               
	 * @return the 0-based index of the specified
	 * 			table item.  If the table item can not
	 * 			be located based on the specified text,
	 * 			-1 is returned.
	 */
	public int findRow(String pattern) {
		int index = -1;
		int count = getItemCount();
		SWTTableItem item;
		String text;
		boolean notFound = true;
		
//		for (int i = 0; (i <= count - 1) && notFound; i++) {
//			item = getItem(i);
//			text = item.getText();
//			if (text != null) {
//				boolean match = new Regex(pattern).matches(text);
//				if (match) {
//					index = i;
//					notFound = false;
//				}
//			}
//			item.unregister();
//		}
		if (notFound)
			index = super.findRow(pattern);  // call the super's method to find it	

		return index;
		 
	}	

	/**
	 * This method can be used to return data for a table
	 * cell, where the row is based on looking up a string that matches
	 * the text property of a table item or the content of a cell in a row. <p>
	 * 
	 * @param tableEntry	the string that matches the text
	 *         property of a table item or the content of a table cell
	 * @param cellColumn	0-based index of the column
	 *         for which want to retrieve data
	 * @return the data that is contained in the specified
	 *          table cell; null if the cell is not found
	 */
	public String getCellContent(String tableEntry, 
	                              int cellColumn)
	{
		String data = null;
		int r = findRow(tableEntry);
		if (r == -1) {
			throw new SubitemNotFoundException("Cannot locate a table cell based on the String, " + tableEntry);
		}
		else {
			ITestDataTable dataTable = getITestDataTable();	
			if (cellColumn >= 0 && cellColumn < dataTable.getColumnCount())
				data = 	(String)(dataTable.getCell(r, cellColumn).toString());
			else
				throw new SubitemNotFoundException("Cell column, " + cellColumn + ", is not valid");
		}
		return data;		
	}	

	/**
	 * Selects the specified cell within the table <p>
	 * @param cell		the cell that is to be selected
	 */	
	public void selectCell(Cell cell) {
		this.click(cell, new Point(5,5));
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
			"Clicked within cell \"" + cell.toString() + "\" in " + getWidgetType() + " \"" + getName() + "\"");									
	}

	/**
	 * Selects the specified row in the table <p>
	 * @param itemText		the string that matches the text 
	 *         property for a table item or as the content of a table cell
	 */	
	public void selectRow(String itemText) {
		try {
			this.click(new Text(itemText), new Point(5,5));
			PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
				"Selected the row that contains \"" + itemText + "\" in " + getWidgetType() + " \"" + getName() + "\"");					
		} catch (SubitemNotFoundException e) {		
			selectCell(new Cell(new Row(findRow(itemText)), 
						        new Column(0)));
		} catch (RationalTestException e) {		
			selectCell(new Cell(new Row(findRow(itemText)), 
						        new Column(0)));
		}		
	}
		
	/**
	 * For tables with checkboxes, this method toggles the checkbox for
	 * the specified entry. <p>
	 * 
	 * @param entry	the text of the table entry as it appears in the GUI
	 */
	public void toggleTableEntryCheckbox(String entry) {
		this.click(new List(entry + "->Location(CHECKBOX)"));
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, 
				"Toggled \"" + entry + "\" in " + getWidgetType() + " \"" + getName() + "\"");			
	}		

}
