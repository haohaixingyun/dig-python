// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.widgets.*;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Class for manipulating a SWT Text<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTText object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTText extends WTextField {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTText.
	 * @param to	the TestObject that references the SWT text field
	 */
	public SWTText(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************

// NEEDED THE FOLLOWING SINCE CTRL-A DOESN'T CLEAR A SWT TEXT FIELD
	/**
	* Deletes the contents of a text field<p>
	* @param to	test object
	* */
	public void clearText() 
	{
		//Java TextFields don't respond to Ctrl-Home correctly,
		//so do both Home and Ctrl-Home and then clear text so that works for All TextFields and TextAreas
		typeKeys("{ExtHome}+{ExtEnd}{ExtDelete}^{ExtHome}^+{ExtEnd}{ExtDelete}");
	}

}
