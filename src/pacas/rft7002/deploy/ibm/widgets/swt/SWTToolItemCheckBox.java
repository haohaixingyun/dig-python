// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.widgets.WCheckBox;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;

/**
 * Description : Class for manipulating a SWT ToolItem checkbox<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTToolItemCheckBox object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTToolItemCheckBox extends WCheckBox {
	
//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTToolItemCheckBox <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.ToolItem, which has a role of CheckBox and a .java.swt.ToolItemProxy
	 */
	public SWTToolItemCheckBox(TestObject to) {
		super(to);	
	}
	
//***********************Instance Methods*******************************************************************************
	/**
	 * Provides access to the tool item that this SWTToolItemCheckBox object references <p>
	 * @return the tool item that this SWTToolItemCheckBox object references.
	 * The returned object should be unregistered (when no longer needed) to avoid memory leaks.
	 */		
	public SWTToolItem getToolItem() {
		return new SWTToolItem(this);
	}	

}
