// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for querying a TableItem in a SWT Table<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTTableItem extends TestObject {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTTableItem <p>
	 * @param to	the TestObject that references the SWT TableItem
	 */
	public SWTTableItem(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Identifies if a checkbox that appears before the TableItem is checked or not <p>
	 * @return whether or not the checkbox 
	 * 			that appears for the TableItem is selected or not.
	 */
	public boolean isChecked() {	
		return ((Boolean)(this.getProperty("checked"))).booleanValue();
	}

	/**
	 * Identifies if the TableItem is grayed or not <p>
	 * @return whether or not the the TableItem is grayed.
	 */
	public boolean isGrayed() {	
		return ((Boolean)(this.getProperty("grayed"))).booleanValue();
	}

	/**
	 * Gets the text that appears for the TableItem <p>
	 * @return the text property value of the TableItem.
	 */
	public String getText() {	
		return (String)(this.getProperty("text"));
	}

}
