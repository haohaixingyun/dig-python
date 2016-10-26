package actions;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.web.common.*;
import hlta.testexec.platform.win32.Win32IEHelper;


public class AcceptHtmlDialog2 extends ActionBase {

	/**
	 * 
	 * @param object The value to accept 
	 * @param param Key sequence to perform if not found.
	 * 				such as "{Enter}{tab}"
	 * @param extParam The max time to wait before the dialog shows up.
	 */
	public AcceptHtmlDialog2(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);

	}

	@Override
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		String match = "";
		String ks = "";
		int time = 3000;  // Default wait 3 secs before and after the dialog shows.

		try {
			Thread.sleep(time);
			IWindow activeWindow = RationalTestScript.getScreen().getActiveWindow();
			if ( activeWindow != null )
				activeWindow.inputKeys(param.toString());
			/*int hwnd = Win32IEHelper.getDialogTop();
			TopLevelTestObject too = null;
			TestObject[] foundTOs = RationalTestScript.find(RationalTestScript.atChild(".hwnd", (long)hwnd, ".domain", "Win"));
			if ( foundTOs!=null ) {
				// Maximize it
				if ( foundTOs[0]!=null) {
					too = new TopLevelTestObject(foundTOs[0]);
				}
			}
			if (too!=null) {
				too.inputKeys(param.toString());
			}*/
			Thread.sleep(time);
		} catch (NullPointerException e ) {			
		} catch (ClassCastException e) {
		} catch (Exception e) {
			System.err.println("Error finding the HTML dialog.");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	}

}
