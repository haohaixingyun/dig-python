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
import com.rational.test.ft.object.interfaces.*;
import ibm.widgets.ancestors.*;
import java.awt.*;

/**
 * Description : Class for manipulating a SWT CoolBar<p>
 *
 * Note that since this class inherits from Rational's GuiTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTCoolBar object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTCoolBar extends Widget {

//*******************************************Constructors**********************************************
	/**
	 * Constructor for SWTCoolBar <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.CoolBar, which has a role of ToolBar and a .java.swt.CoolBarProxy
	 */
	public SWTCoolBar(TestObject to) {
		super(to);
	}

//************************ For Logging ****************************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "coolbar";
	}
	
	/**
	 * Clicks at the specified point within the CoolBar<p>
	 * @param p	the point in the CoolBar to click on
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
	 * Provides access to the CoolItem that has the specified text <p>
	 *
	 * @return the CoolItem with the specified text.  If no
	 * CoolItem within the CoolBar has the specified text, 
	 * a null is returned. If a null is not returned, then the 
	 * returned object should be unregistered
	 * (when no longer needed) to avoid memory leaks.
	 */	
	public SWTCoolItem getCoolItem(String text) {
		SWTCoolItem item = null;
		boolean notFound = true;
		
		// get the CoolItems in the CoolBar
		TestObject[] coolItems = (TestObject[])invoke("getItems");
		for (int i = 0; i < coolItems.length; i++) {
			if (notFound) {
				SWTCoolItem coolItem =
					new SWTCoolItem((GuiTestObject)coolItems[i]);
						
				String itemText = coolItem.getText();
				
				if (itemText.indexOf(text) >= 0) {
					// found the appropriate CoolItem
					notFound = false;
					item = coolItem;
				}
				else
					coolItems[i].unregister();
			} 
			else 
				coolItems[i].unregister();
		}	
		return item;	
	}
	
	/**
	 * Gets the TestObject that references the tool item with the specified tool tip text.
	 * This assumes the CoolBar contains CoolItems that in turn contain a ToolBar of ToolItems
	 * as each of its control property (which is the case for the CoolBar that appears within the workbench frame) <p>
	 * @param toolTipText	the toolTipText of a ToolItem that's contained
	 * in the ToolBar of a CoolItem in the CoolBar
	 * @return the ToolItem that has the specified toolTipText.
     * If the CoolItems of the CoolBar do not contain a ToolBar
	 * as their control or if the ToolItem is not found, a null is returned.
	 * If a null is not returned, the returned object should be unregistered when
	 * no longer needed to avoid memory leaks.
	 */
	public SWTToolItem getToolItem(String toolTipText) {
		SWTToolItem toolItem = null;
		
		if (toolTipText != null) {			
			// get the CoolItems of the CoolBar
			TestObject[] items = (TestObject[]) (invoke("getItems"));
			boolean notFound = true;
			
			for (int i = 0; i < items.length; i++) {
				if (notFound) {
					// get the next CoolItem in the CoolBar
					TestObject item = items[i];

					// get the ToolBar contained in the CoolItem
					TestObject control = (TestObject)item.invoke("getControl");
					if (control.getObjectClassName().equals("org.eclipse.swt.widgets.ToolBar")) {
						SWTToolBar tb = new SWTToolBar((TestObject)control);						
						toolItem = tb.getToolItem(toolTipText);
						if (toolItem != null) notFound = false;
					}
					control.unregister();
				}
				items[i].unregister();
			}
		}
		return toolItem;
	}	

}