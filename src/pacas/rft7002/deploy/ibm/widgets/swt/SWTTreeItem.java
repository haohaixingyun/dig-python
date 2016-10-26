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
 * Description : Class for querying a TreeItem in a SWT Tree<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTTreeItem extends TestObject {
	
//**************************************Constructors****************************************************************	
	/**
	 * Constructor for SWTTreeItem <p>
	 * @param to		the TestObject that references the SWT TreeItem
	 */
	public SWTTreeItem(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Identifies how many immediate subnodes appear under the TreeItem <p>
	 * @return the number of children that the TreeItem contains.
	 */
	public int getItemCount() {	
		return ((Integer)(this.getProperty("itemCount"))).intValue();
	}

	/**
	 * Gets the text of the TreeItem <p>
	 * @return the text property value of the TreeItem.
	 */
	public String getText() {	
		return (String)(this.getProperty("text"));
	}
	
	/**
	 * Identifies whether or not a checkbox that appears in front of the TreeItem is selected or not <p>
	 * @return whether or not the checkbox 
	 * 			that appears for the TableItem is selected or not.
	 */
	public boolean isChecked() {	
		return ((Boolean)(this.getProperty("checked"))).booleanValue();
	}

	/**
	 * Identifies whether or not the TreeItem is expanded within its tree <p>
	 * @return whether or not the the tree item is expanded.
	 */
	public boolean isExpanded() {	
		return ((Boolean)(this.getProperty("expanded"))).booleanValue();
	}


}
