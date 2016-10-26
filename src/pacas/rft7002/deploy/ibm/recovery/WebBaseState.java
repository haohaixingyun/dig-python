// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.recovery;

import ibm.recovery.control.IBaseState;
import ibm.recovery.control.PackageRecoveryController;
import ibm.util.BrowserOps;

import com.rational.test.ft.script.RationalTestScript;


/**
 * Description: This class defines a BaseState for web applications. <p>
 * It can be used to get back to an initial state of your application so that it is ready for testing.<p>
 * It must be used as a superhelper, since startBrowser won't work otherwise.<p>
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class WebBaseState extends RationalTestScript implements IBaseState {
	
	protected String gsBaseURL = "about:blank";

	/**
	 * Default constructor<br>
	 * Creates the object and sets the PackageRecoveryController to this instance of WebBaseState
	 */	
	public WebBaseState()
	{
		PackageRecoveryController.setBaseState(this);
	}

	/**
	 * Constructor which sets the base URL for this instance of WebBaseState<br>
	 * Also, sets the PackageRecoveryController to this instance of WebBaseState
	 * @param sURL the URL which you want your testing to start from
	 */	
	
	public WebBaseState(String sURL)
	{
		gsBaseURL = sURL;
		PackageRecoveryController.setBaseState(this);
	}
	

	/**
	 * Puts the application in its base state, at the initial state to start testing.
	 */
	public void callBaseState() {
		BrowserOps.closeAllBrowsers();
		BrowserOps.startBrowser(gsBaseURL);
		BrowserOps.waitForReady();		
	}

	/**
	 * Sets the URL which the BaseState will bring up<br>
	 * @param sURL the URL which you want your testing to start from
	 */	
	public void setBaseBrowserURL(String sURL)
	{
		gsBaseURL = sURL;
	}
		

}
