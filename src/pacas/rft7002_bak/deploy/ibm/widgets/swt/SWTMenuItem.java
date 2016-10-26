// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.widgets.ancestors.Widget;
import java.util.Hashtable;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for querying a menu item in a SWT Menu<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTMenuItem extends Widget {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTMenuItem.
	 * @param to	the Test Object that references the SWT menu item
	 */
	public SWTMenuItem(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the accelerator/mneumonic that's defined for the menu item
	 * @return The accelerator for the menu item
	 */	
	public int getAccelerator() {
		return ((Integer)invoke("getAccelerator")).intValue();
	}

	/**
	 * Gets the text of the menu item excluding its accelerator
	 * @return The text for the menu item, excluding its accelerator
	 */		
	public String getTextWithoutMneumonic() {
		Hashtable table = getRecognitionProperties();
		return (String)table.get("text");
	}

	/**
	 * Gets the text of the menu item including its accelerator
	 * @return The text for the menu item, including its accelerator
	 */	
	public String getText() {
		// FOUND A BUG IN RATIONAL XDE TESTER - although printProperties() was
		// showing a property of text=&File, calling getProperty("text") was
		// returning File (not &File).  Workaround is to invoke the getter method
		// for this property.
		return (String)invoke("getText");
	}

}
