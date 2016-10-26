// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets;

import ibm.widgets.ancestors.IWidget;
import ibm.widgets.ancestors.TopLevelWidget;
import java.awt.Point;
import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for manipulating Java Frames in XDE<p>
 *
 * 
 * @author endres
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class WFrame extends TopLevelWidget implements IWidget {

	/**
	 * Constructor for TopLevelWidget.
	 * @param to	 the TopLevelObject from which to construct the widget.
	 */
	public WFrame(TestObject to) {
		super(to);
	}

		
	/**
	 * Retrieves the text (title) of the object. <br>
	 * @return the text (title) of the object
	 * @see ibm.widgets.IToggleWidget#isMaximized()
	 */	
	public String getText() {
		return (String)getProperty("text");
	}

	/**
	 * Determines if the object is maximized or not. <br>
	 * @return true if object is maximized
	 * @see ibm.widgets.IToggleWidget#isMaximized()
	 */	
	public boolean isMaximized() {
		return ((Boolean)getProperty("maximized")).booleanValue();
	}

	/**
	 * Determines if the object is minimized or not. <br>
	 * @return true if object is minimized
	 * @see ibm.widgets.IToggleWidget#isMinimized()
	 */	
	public boolean isMinimized() {
		return ((Boolean)getProperty("minimized")).booleanValue();
	}

	/**
	 * Retrieves the location of the object as a Point. <br>
	 * @return the location of the object
	 * @see ibm.widgets.IToggleWidget#getLocation()
	 */			
	public Point getLocation() {
		Point p = null;
		Rectangle location = (Rectangle)getProperty("location");
		if (location != null) {
			p = new Point(location.x, location.y);
		}
		return p;
	}
}