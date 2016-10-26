// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description: Performs operations on the active window, including Windows domain windows.
 *
 * @author CCarlson
 * 
 * @version 2.2
 * Last Modified: 08/23/04
 */

public class WindowOps {

	/**
	* Closes active window (includes Windows domain windows, for example, a windows-generated popup)<p>
	* @author Chris Carlson
	*/

	public static void closeActiveWindow()
	{
		IWindow activeWindow = RationalTestScript.getScreen().getActiveWindow();
		if ( activeWindow != null )
			activeWindow.close();
	}

	/**
	 * Pass an {ENTER} key code to the active window (including a windows-generated popup or other
	 * Windows domain window).  This will typically trigger the default button with focus
	 * (commonly the "OK" or "Cancel" button).
	 * @author Chris Carlson
	 */
	public static void acceptActiveWindow()
	{
		IWindow activeWindow = RationalTestScript.getScreen().getActiveWindow();
		if ( activeWindow != null )
			activeWindow.inputKeys("{ENTER}");
	}

}