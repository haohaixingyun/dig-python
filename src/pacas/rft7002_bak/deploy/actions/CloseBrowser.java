/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import ibm.util.*;

import hlta.testexec.platform.rft7.web.common.*;
/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CloseBrowser extends ActionBase {

	/**
	 * @param object	String. The URL. Or maybe one of the following patterns:
	 *                  "forward", "back".
	 * @param param		String. Specifies whether to close all browsers. 
	 * 					"close all".
	 *                  
	 * @param extParam  if the "param" parameter is one of "forward" or "back", this 
     *                  could be the number to specify how many pages to forward or back.
	 */
	public CloseBrowser(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		int j = 1, i = 1;
		while (i<4 || BrowserOps.findBrowser()!=null) {
			try {
				++i;
				BrowserOps.closeAllBrowsers();
				Thread.sleep(1000);
				if (BrowserOps.findBrowser()!=null) {
					BrowserOps.findBrowser().close();
				}
			} catch (Exception e) {
				continue;
			}
			break;
		}
		
	}

	public static void main(String[] args) {
	}
}