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
import ibm.widgets.ancestors.*;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import ibm.util.ItclSuperHelper;
import com.rational.test.ft.script.List;
import java.util.*;

/**
 * Description : Class for manipulating a SWT Menu<p>
 *
 * Note that since this class inherits from Rational's GuiSubitemTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTMenu object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTMenu extends SubitemWidget {

//*******************************************Constructors**********************************************
	/**
	 * Constructor for SWTMenuBar <p>
	 * @param to	the TestObject that references the SWT menubar
	 */
	public SWTMenu(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the list of menu items that appear for the menubar referenced by this SWTMenu instance <p>
	 * @return an array of SWTMenuItems that reference the menu items that appear on the menubar.
	 * If a null is not returned, each item in the returned array or the array itself should be unregistered
	 * when no longer needed to avoid memory leaks.
	 */
	public SWTMenuItem[] getItems() {
		TestObject[] objs = (TestObject[])invoke("getItems");
		SWTMenuItem[] items = null;
		if (objs != null) {
			items = new SWTMenuItem[objs.length];			
			for (int i=0; i<objs.length; i++)
				items[i] = new SWTMenuItem((GuiTestObject)objs[i]);
		}
		return items;
	}

	/**
	 * Gets the number of menu items that appear for the menubar referenced by this SWTMenu instance <p>
	 * @return an int that identifies the number of menu items that appear on the menubar.
	 */
	public int getItemCount() {
		return ((Integer)getProperty("itemCount")).intValue();
	}

	/**
	 * Indicates if the menubar referenced by this SWTMenu instance is visible or not <p>
	 * @return a boolean that identifies whether the menubar is visible or not
	 */	
	public boolean isVisible() {
		return ((Boolean)getProperty("visible")).booleanValue();
	}

	/**
	 * Parses the path parameter and calls click the appropriate number of times to support menu selection in Linux as well as Windows.
	 * For example, calling with a parameter path of "File->New->Project..." will result in the following 3 click invocations: <br>
	 * click(atPath("File"); <br>
	 * click(atPath("File->New"); <br>
	 * click(atPath("File->New->Project...");
	 * @param path		the path to the menu you want to select
	 */		
	public void select(String path) {
		String menuitem = "";
		StringTokenizer st = new StringTokenizer(path.trim(), "->");
		while (st.hasMoreTokens()) {
			if (menuitem.equals("")) menuitem = st.nextToken();
			else menuitem = menuitem + "->" + st.nextToken();	
			click(new List(menuitem));
			// the following is needed since click(Subitem) is not supported in any widget ancestor class
			PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
				"Clicked \"" + menuitem + "\" in " + getWidgetType() + " \"" + getName() + "\"");										
		}
	}	
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "menu";
	}	

}