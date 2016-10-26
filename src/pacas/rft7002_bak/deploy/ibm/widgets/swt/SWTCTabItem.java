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
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import ibm.widgets.ancestors.*;
import java.awt.*;

/**
 * Description : Class for querying a CTabItem in a SWT CTabFolder<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTCTabItem extends TestObject {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTCTabItem <p>
	 * @param to	the TestObject that references the CTabItem
	 */
	public SWTCTabItem(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the TestObject that references the containing CTabFolder for the CTabItem <p>
	 * @return the parent of the CTabItem as a SWTCTabFolder instance. If a null is
	 * not returned, the returned object should be unregistered when no longer needed (visible) to avoid
	 * memory leaks.
	 */		
	public TestObject getParent() {
		SWTCTabFolder parent = null;
		TestObject obj = super.getParent();
		if (obj != null) {
			parent = new SWTCTabFolder((GuiSubitemTestObject)obj);
		}
		return parent;
	}	
	
	/**
	 * Gets a coordinate point for the CTabItem relative to the CTabFolder that contains it <p>
	 * @return the coordinates of the CTabItem
     * (relative to the CTabFolder that it's contained in)
	 */	
	public Point getPointWithinParent() {
		Point p = null;	        	
    	Rectangle bounds = (Rectangle)getProperty("bounds");
	    if (bounds != null)
	    	p = new Point(bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2));
		return p;
	}	

	/**
	 * Gets the text that appears for the CTabItem (tab in the folder) <p>
	 * @return  the text for the CTabItem
	 */		
	public String getText() {
		return (String)getProperty("text");
	}		

}
