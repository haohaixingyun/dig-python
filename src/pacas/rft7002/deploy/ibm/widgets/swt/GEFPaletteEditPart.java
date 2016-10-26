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
import java.util.List;
import java.awt.Point;

/**
 * Description : Class for querying a GEF Palette Edit Part<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 08/02/04
 */
public class GEFPaletteEditPart extends TestObject {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for GEFPaletteEditPart <p>
	 * @param to	the TestObject that references the GEFPaletteEditPart
	 */
	public GEFPaletteEditPart(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	* Returns all the children of the edit part as a TestObject (java.util.List).
	* If no children exist, an empty list is returned.<p>
	* @return the children as a TestObject (java.util.List)
	* */		
	public TestObject getChildParts() {
		return new TestObject((TestObject)(this.invoke("getChildren")));
	}

	/**
	* Returns the child at the specified index <p>
	* @param childIndex	the 0-based index of the child edit part to be retrieved
	* @return a GEFPaletteEditPart instance if the specified child is present, null otherwise 
	* */		
	public GEFPaletteEditPart getChildPart(int childIndex) {
		GEFPaletteEditPart part = null;
		TestObject list = getChildParts();
		TestObject toPart = (TestObject)list.invoke("get", 
			                            "(I)Ljava/lang/Object;", 
			                            new Object[] {new Integer(childIndex)});
		list.unregister();
			                            
		if (toPart != null)
			part = new GEFPaletteEditPart(toPart);
			
		return part;		
	}

	/**
	* Returns the label of the edit part <p>
	* @return the label of the edit part
	* */				
	public String getLabel() {
		// get the model associated with the edit part
		TestObject model = getModel();
		
		// get the expanded property
		String label = (String)model.getProperty("label");
		model.unregister();
				
		return label;	
	}
				
	/**
	* Returns the location of the edit part (palette entry) within the palette <p>
	* That is, the location of the edit part within the FigureCanvas that represents the palette.<p>
	* @return the location of the edit part within its palette
	* */		
	public Point getLocation() {
		// get the figure associated with the edit part
		TestObject figure = getFigure();
		
		// get the location property, which is an org.eclipse.draw2d.geometry.Point instance
		TestObject location = (TestObject)figure.getProperty("location");
		figure.unregister();
		
		Point p = (Point)location.getProperty("sWTPoint");
		location.unregister();
		
		return p;
	}		

	/**
	* Identifies whether or not the edit part (palette entry) is expanded <p>
	* @return a boolean indicating if the edit part (palette entry) is expanded (true if expanded, false if not)
	* */	
	public boolean isExpanded() {
		// get the figure associated with the edit part
		TestObject figure = getFigure();
		
		// get the expanded property
		boolean result = ((Boolean)figure.getProperty("expanded")).booleanValue();	
		figure.unregister();
				
		return result;	
	}		

//***********************Helper Methods*******************************************************************************
	/**
	* Returns the figure associated with the edit part <p>
	* Since other methods call and unregister this, this is a private method.<p>
	* @return  the figure (view) associated with the edit part
	* */	
	private TestObject getFigure() {
		return new TestObject((TestObject)(this.invoke("getFigure")));
	}
	
	/**
	* Returns the model associated with the edit part <p>
	* Since other methods call and unregister this, this is a private method.<p>
	* @return the model associated with the edit part
	* */	
	private TestObject getModel() {
		return new TestObject((TestObject)(this.invoke("getModel")));
	}

}