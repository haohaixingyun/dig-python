// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import com.rational.test.ft.object.interfaces.*;
import ibm.widgets.*;

/**
 * Description : Class for manipulating a SWT Label<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTLabel object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTLabel extends WStaticText {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTLabel <p>
	 * @param to	the TestObject that references the SWT label
	 */	
	public SWTLabel(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the text of the SWT label <p>
	 * @return the text of the SWT label
	 */		
	public String getText() {
		return (String)this.getProperty("text");
	}

}