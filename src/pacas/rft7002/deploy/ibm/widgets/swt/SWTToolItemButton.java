// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.widgets.WButton;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for manipulating a SWT ToolItem button<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTToolItemButton object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTToolItemButton extends WButton {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTToolItemButton <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.ToolItem, which has a role of Button and a .java.swt.ToolItemProxy
	 */
	public SWTToolItemButton(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************	
	/**
	 * Provides access to the tool item that this SWTToolItemButton object references <p>
	 * @return  the tool item that this SWTToolItemButton object references.
	 * The returned object should be unregistered (when no longer needed) to avoid memory leaks.
	 */	
	public SWTToolItem getToolItem() {
		return new SWTToolItem(this);
	}

}
