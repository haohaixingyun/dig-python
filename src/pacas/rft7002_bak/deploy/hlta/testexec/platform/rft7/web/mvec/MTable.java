/*
 * Table helper class
 */
package hlta.testexec.platform.rft7.web.mvec;

import com.rational.test.ft.object.interfaces.StatelessGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.ft.vp.ITestDataText;

public class MTable extends StatelessGuiSubitemTestObject {
	public MTable(TestObject to) {
		super(to);
	}

	/*
	 * Following methods are excerpted from WTable class, IBM package.
	 */
	/**
	 * Returns the contents of a cell in the particular table in this object
	 * 
	 * @param x
	 *            x coordinate of desired cell
	 * @param y
	 *            y coordinate of desired cell
	 * @return contents of the cell
	 * @author Chris Carlson -- modified 01/06/04 by CC (added null check from
	 *         Ann Kopren)
	 */
	public String getCellContents(int x, int y) {
		ITestDataTable myTable = getITestDataTable();
		/*
		 * System.out.println( "Value at cell (" + x + "," + y + ") is: " +
		 * myTable.getCell(x, y));
		 */
		// aek - null check
		if (myTable.getCell(x, y) != null)
			return myTable.getCell(x, y).toString();
		else
			return null;
	}

	/**
	 * Returns the number of rows in this object
	 * <p>
	 * 
	 * @return number of rows
	 * @author Jack Kipp
	 */
	public int getColumnCount() {
		ITestDataTable myTable = getITestDataTable();
		return myTable.getColumnCount();
	}

	/**
	 * Returns the ITestDataTable object for the particular table in this object
	 * 
	 * @return ITestDataTable
	 * @author Chris Carlson -- modified 01/06/04 by CC (added null check from
	 *         Ann Kopren)
	 */
	public ITestDataTable getITestDataTable() {
		ITestDataTable myTable = null;
		// aek - didn't work for swing
		if (getTestDataTypes().containsKey("grid"))
			myTable = (ITestDataTable) getTestData("grid");
		else if (getTestDataTypes().containsKey("contents"))
			myTable = (ITestDataTable) getTestData("contents");
		return myTable;
	}
	public ITestDataText getITestDataText() {
		return (ITestDataText)getTestData("text");
	}

	/**
	 * Returns the number of rows in this object
	 * <p>
	 * 
	 * @return number of rows
	 * @author Jack Kipp
	 */
	public int getRowCount() {
		ITestDataTable myTable = getITestDataTable();
		return myTable.getRowCount();
	}
}
