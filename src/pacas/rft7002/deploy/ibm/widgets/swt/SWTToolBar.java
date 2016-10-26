// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.loggers.control.PackageLoggingController;
import ibm.widgets.ancestors.Widget;
import java.awt.Point;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for manipulating a SWT ToolBar<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTToolBar object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTToolBar extends Widget {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTToolBar <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.ToolBar, which has a role of ToolBar and a .java.swt.ToolBarProxy
	 */
	public SWTToolBar(TestObject to) {
		super(to);
	}
	
//************************ For Logging ****************************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "toolbar";
	}

	/**
	 * Clicks at the specified point within the ToolBar<p>
	 * @param p	the point in the ToolBar to click on
	 */		
	public void click(Point p)
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
		String sWidgetType = getWidgetType();
		String sWidgetName = getName();
				
		super.click(p);
		
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
			"Clicked at point (" + p.x + "," + p.y + ") within " + sWidgetType + " " + sWidgetName + ".");
		
	}

//*****************************************Instance Methods**********************************************
	/**
	 * Gets the location of the tool bar relative to the control that contains it <p>
	 * @return the location of the ToolBar relative to its parent.
	 */		
	public Point getPointWithinParent() {			
		return (Point)getProperty("location");			
	}

	/**
	 * Provides access to the tool item that has the specified tool tip text <p>
	 *
	 * @return the tool item with the specified text.  If no
	 * tool item within the ToolBar has the specified tool tip text, 
	 * a null is returned. If a null is not returned, then the 
	 * returned object should be unregistered
	 * (when no longer needed) to avoid memory leaks.
	 */	
	public SWTToolItem getToolItem(String toolTipText) {
		SWTToolItem item = null;
		boolean notFound = true;
		
		// get the ToolItems in the ToolBar
		TestObject[] toolItems = (TestObject[])invoke("getItems");
		for (int i = 0; i < toolItems.length; i++) {
			if (notFound) {
				SWTToolItem toolItem =
					new SWTToolItem((GuiTestObject)toolItems[i]);
						
				String tip = toolItem.getToolTipText();
				
				if (tip.indexOf(toolTipText) >= 0) {
					// found the appropriate ToolItem
					notFound = false;
					item = toolItem;
				}
				else
					toolItems[i].unregister();
			} 
			else 
				toolItems[i].unregister();
		}	
		return item;	
	}

}