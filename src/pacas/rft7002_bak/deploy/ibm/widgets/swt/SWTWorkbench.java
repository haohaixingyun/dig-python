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
import ibm.widgets.ancestors.*;

/**
 * Description : Class for querying the workbench in an Eclipse-based product<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 08/03/04
 */
public class SWTWorkbench extends TopLevelWidget {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTWorkbench.
	 * @param TestObject to - the TestObject that references the org.eclipse.swt.widgets.Shell instance
	 */
	public SWTWorkbench(TestObject to) {
		super(to);	
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Provides access to the editor that is currently active <p>
	 * @return the editor that has focus; 
	 * 			if none have focus (are active in the
	 *    		active page), then a null is returned.
	 *    		If a null is not returned, then the 
	 * 			returned object should be unregistered
	 * 			(when no longer needed) to avoid memory 
	 * 			leaks.
	 */	
	public SWTEditor getActiveEditor() {
		TestObject activeWin = getActiveWorkbenchWindow();	
		
		TestObject activePage = (TestObject)(activeWin.invoke("getActivePage"));
		activeWin.unregister();
		
		TestObject editor = (TestObject)(activePage.invoke("getActiveEditor"));
		activePage.unregister();		
			
		SWTEditor editorObj = new SWTEditor(this, editor);
		return (editorObj);
	}
	
	
    /**
	 * Provides access to the workbench window that currently has focus <p>
	 * @return the workbench window that has 
	 * 			focus; if none have focus (are active),
	 *    		then a null is returned.  If a null is 
	 * 			not returned, then the returned
	 *    		object should be unregistered (when 
	 * 			no longer needed) to avoid memory leaks.
	 */
	public TestObject getActiveWorkbenchWindow() {
		// the following commented code returns a null, so couldn't use it
//       TestObject wb = getWorkbench();		
//		 WSTestObject testObj = new WSTestObject(wb);
//		 testObj.printMethods();
//		 TestObject obj = (TestObject)(wb.invoke("getActiveWorkbenchWindow"));
//       wb.unregister();
//		 if (obj == null) return null;
//		 return new WSWorkbenchWindowTestObject(obj);
		
		TestObject[] openWindows = getWorkbenchWindows();
		int count = openWindows.length;
		boolean notFound = true;
		TestObject shell;
		TestObject window = null;
		for (int i=0; i < count; i++) {
			if (notFound) {
				shell = (TestObject)(openWindows[i].getProperty("shell"));
//				System.out.println(shell.getProperty("text"));
				if (shell.equals(getTopParent())) {
					window = openWindows[i];
					notFound = false;
				}
				shell.unregister();
			}
			if (notFound) 
				openWindows[i].unregister();				
		}
		return window;
	}

	/**
	 * Identifies the perspective that is currently open in the workbench (e.g., "Java") <p>
	 * @return the perspective that is currently open in the workbench
	 */
	public String getCurrentPerspective() {
		String perspective = null;
		perspective = (String)(this.getProperty("text"));
        int endIndex = perspective.indexOf("-");
        perspective = perspective.substring(0, endIndex-1);
        return perspective;
	}

//***********************Helper Methods*******************************************************************************
	/**
	 * Helper method that provides access to the workbench windows that are open <p>
	 * @return the set of open workbench windows 
	 * 			as an array of TestObject;
	 * 			these objects should be unregistered when
	 * 			no longer needed to avoid memory leaks.
	 */
	private TestObject[] getWorkbenchWindows() {
		TestObject wb = getWorkbench();
		TestObject[] windows = (TestObject[])wb.invoke("getWorkbenchWindows" );
		wb.unregister();
		return windows;
	}	
	
	/**
	 * Helper method that provides access to the workbench <p>
	 * @return the workbench as a TestObject.  
	 * 			This object should be unregistered when
	 * 			no longer needed to avoid memory leaks.
	 */
	private TestObject getWorkbench() {
		DomainTestObject domainObject = (DomainTestObject)(this.getDomain());
//@KAE - modified/added the following 3 lines on 6/13		
		TestObject wb = (TestObject)(domainObject.invokeStaticMethod("org.eclipse.ui.PlatformUI", "getWorkbench"));		
		domainObject.unregister();
		return wb;
	}
}