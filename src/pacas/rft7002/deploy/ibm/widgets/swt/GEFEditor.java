// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.widgets.ancestors.*;
import com.rational.test.ft.object.TestObjectReference;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.map.SpyMappedTestObject;
import com.rational.test.ft.sys.RegisteredObjectReference;

/**
 * Description : Class for querying a GEF editor<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public abstract class GEFEditor extends SWTEditor {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for GEFEditor <p>
	 * @param to	the TopLevelTestObject that references the workbench frame.  This should appear as the root in
	 * any appObjects that are captured.
	 */	
	public GEFEditor(TopLevelTestObject to) {
		super(to);
	}
	
	/**
	 * Constructor for GEFEditor <p>
	 * @param wb	the TestObject that represents the workbench
	 */		
	public GEFEditor(SWTWorkbench wb) {
		super(wb);
	}
	/**
	 * Constructor for GEFEditor <p>
	 * @param wb	the workbench test object
	 * @param to	an IEditorPart (for the active editor)
	 */	
	public GEFEditor(SWTWorkbench wb, TestObject to) {
		super(wb,to);
	}	
	
//***********************Instance Methods*******************************************************************************				
	/**
	 * Gets the TestObject that references the graphical viewer.  Subclasses that represent
	 * specific GEF editors should be sure to override this method.  For example, here's an
	 * example of what was written for one such editor within WebSphere Studio Application Developer: <br>
	 * 	// get the EditPartViewer class <br>
	 *	TestObject editPartViewerClass = <br>
	 *		EclipseOps.loadClass( <br>
	 *			getWorkbench(), <br>
	 *			"org.eclipse.gef", <br>
	 * 			"org.eclipse.gef.EditPartViewer"); <br>
     *  <br>
	 *	// get the graphical viewer <br>
	 *	TestObject graphicalViewer = <br>
	 *		(TestObject) (this <br>
	 *			.invoke( <br>
	 *				"getAdapter", <br>
	 *				"(Ljava/lang/Class;)Ljava/lang/Object;", <br>
	 *				new Object[] { <br>
	 *					editPartViewerClass.getObjectReference().getTarget()})); <br>
	 *	editPartViewerClass.unregister(); <br>
	 *	<br>
	 *	return graphicalViewer; <p>
	 * @return a TestObject that references the graphical viewer for the GEF editor
	 */
	public TestObject getGraphicalViewer() {
		return null;
	}

	/**
	 * Gets the TestObject that references the palette viewer Subclasses that represent
	 * specific GEF editors should be sure to override this method.  For example, here's an
	 * example of what was written for one such editor within WebSphere Studio Application Developer: <br>
	 *	// get the graphical viewer <br>
	 *	TestObject graphicalViewer = getGraphicalViewer(); <br>
	 *	
	 *	// get the edit domain <br>
	 *	TestObject editDomain = <br>
	 *		(TestObject) (graphicalViewer.invoke("getEditDomain")); <br>
	 *	graphicalViewer.unregister(); <br>
     *
	 *	// get the palette viewer <br>
	 *	TestObject paletteViewer = <br>
	 *		(TestObject) (editDomain.invoke("getPaletteViewer")); <br>
	 *	editDomain.unregister(); <br>
	 *	
	 *	TestObject obj = new TestObject(paletteViewer); <br>
	 *	return obj;	 <p>
	 * @return a TestObject that references the palette viewer for the GEF editor
	 * @see the constructor for GEFPalette
	 */	
	public TestObject getPaletteViewer() {
		return null;
	}

}