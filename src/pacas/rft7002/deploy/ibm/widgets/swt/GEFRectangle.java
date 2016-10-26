// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for querying a GEF Rectangle<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class GEFRectangle extends TestObject {
	
//**************************************Constructors****************************************************************	
	/**
	 * Constructor for GEFRectangle <p>
	 * @param to	the TestObject that references a GEF Rectangle (org.eclipse.draw2d.geometry.Rectangle)
	 */
	public GEFRectangle(TestObject to) {
		super(to);
		if (!this.getObjectClassName().equals("org.eclipse.draw2d.geometry.Rectangle"))
			throw new ClassCastException("GEFRectangle does not reference an org.eclipse.draw2d.geometry.Rectangle");
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the mid bottom point of the GEF Rectangle <p>
	 * @return the mid bottom point of the GEF Rectangle
	 */
	public Point getBottomPoint() {
		return getPoint("Bottom");
	}
	/**
	 * Gets the bottom left point of the GEF Rectangle <p>
	 * @return the bottom left point of the GEF Rectangle
	 */	
	public Point getBottomLeftPoint() {
		return getPoint("BottomLeft");
	}
	/**
	 * Gets the bottom right point of the GEF Rectangle <p>
	 * @return the bottom right point of the GEF Rectangle
	 */	
	public Point getBottomRightPoint() {
		return getPoint("BottomRight");
	}
	/**
	 * Gets the center point of the GEF Rectangle <p>
	 * @return the center point of the GEF Rectangle
	 */	
	public Point getCenterPoint() {
		return getPoint("Center");
	}
	/**
	 * Gets the mid left point of the GEF Rectangle <p>
	 * @return the mid left point of the GEF Rectangle
	 */	
	public Point getLeftPoint() {
		return getPoint("Left");
	}
	/**
	 * Gets the mid right point of the GEF Rectangle <p>
	 * @return the mid right point of the GEF Rectangle
	 */	
	public Point getRightPoint() {
		return getPoint("Right");
	}
	/**
	 * Gets the mid top point of the GEF Rectangle <p>
	 * @return the mid top point of the GEF Rectangle
	 */	
	public Point getTopPoint() {
		return getPoint("Top");
	}
	/**
	 * Gets the top left point of the GEF Rectangle <p>
	 * @return the top left point of the GEF Rectangle
	 */	
	public Point getTopLeftPoint() {
		return getPoint("TopLeft");
	}
	/**
	 * Gets the top right point of the GEF Rectangle <p>
	 * @return the top right point of the GEF Rectangle
	 */		
	public Point getTopRightPoint() {
		return getPoint("TopRight");
	}	
	/**
	 * Gets the height of the GEF Rectangle <p>
	 * @return the height of the GEF Rectangle
	 */								
	public int getHeight() {
		return getBottomPoint().y - getTopPoint().y;	
	}
	/**
	 * Gets the width of the GEF Rectangle <p>
	 * @return the width of the GEF Rectangle
	 */	
	public int getWidth() {
		return getRightPoint().x - getLeftPoint().x;
	}	
	/**
	 * Helper method to retrieve an appropriate point for the GEF Rectangle <p>
	 * @param String pointLocation - the requested coordinate point (e.g., "TopLeft" or "Bottom").
	 * @return a requested coordinate point for the GEF Rectangle
	 * @see the get*Point methods
	 */	
	public Point getPoint(String pointLocation) {
		Point p;
		TestObject toPoint = (TestObject)(this.invoke("get"+pointLocation));
		p = (Point)toPoint.getProperty("sWTPoint");
		toPoint.unregister();
		return p;		
	}

}
