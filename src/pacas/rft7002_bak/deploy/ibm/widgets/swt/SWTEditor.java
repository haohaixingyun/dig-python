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
import com.rational.test.ft.object.interfaces.TopLevelTestObject;

/**
 * Description : Class for querying an Eclipse-based editor<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTEditor extends TestObject {

	SWTWorkbench oWorkbench;

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTEditor <p>
	 * @param to	the TopLevelTestObject that references the workbench frame.  This should appear as the root in
	 * any appObjects that are captured.
	 */	
	public SWTEditor(TopLevelTestObject to) {
		this(new SWTWorkbench(to));
	}
	
	/**
	 * Constructor for SWTEditor <p>
	 * @param wb	the TestObject that represents the workbench
	 */		
	public SWTEditor(SWTWorkbench wb) {
		this(wb, wb.getActiveEditor());
	}
	
	/**
	 * Constructor for SWTEditor <p>
	 * @param wb	the TestObject that represents the workbench
	 * @param to	an IEditorPart (for the active editor)
	 */	
	public SWTEditor(SWTWorkbench wb, TestObject to) {
		super(to);
		oWorkbench = wb;
	}	
		
//***********************Instance Methods*******************************************************************************		
	/**
	 * Gets the TestObject that represents the workbench so it can be queried <p>
	 * @return an instance of SWTWorkbench that can subsequently be queried for information
	 * on the workbench (e.g., what editor is currently active).
	 */
	public SWTWorkbench getWorkbench() {
		return oWorkbench;
	}
	
}
