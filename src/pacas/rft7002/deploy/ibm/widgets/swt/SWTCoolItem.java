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
import com.rational.test.ft.object.interfaces.*;
import java.awt.*;

/**
 * Description : Class for querying a CoolItem that's contained in a CoolBar<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTCoolItem extends Widget {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTCoolItem <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.ToolItem, which supports various roles (e.g., Button and CheckBox) and a .java.swt.ToolItemProxy
	 */
	public SWTCoolItem(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Provides access to the parent control of the cool item <p>
	 * @return the parent of the cool item as a SWTCoolBar instance
	 */		
	public TestObject getParent() {
		SWTCoolBar parent = null;
		TestObject obj = super.getParent();
		if (obj != null) {
			parent = new SWTCoolBar((ScrollTestObject)obj);
		}
		return parent;
	}

	/**
	 * Gets the top right coordinate point of a cool item, relative to its parent (that is, within the ToolBar that contains it) <p>
	 * @return the top right coordinate of the ToolItem
	 *          (relative to the ToolBar that it's contained in)
	 */	
	public Point getPointWithinParent() {
		return getPointWithinParent("topRight");
	}

	/**
	 * Gets the specified coordinate point of a cool item, relative to its parent (that is, within the CoolBar that contains it) <p>
	 * @param position		indicates what position is desired: <br>
	 * Choices are "topRight", "topLeft", "bottomRight", "bottomLeft".
	 * @return the coordinates of the ToolItem
	 * (relative to the ToolBar that it's contained in)
	 */	
	public Point getPointWithinParent(String position) {
		Point p = null;	        	
    	Rectangle bounds = (Rectangle)getProperty("bounds");
    	if (position.equals("topRight")) 
    		p = new Point(bounds.x + bounds.width - 1, bounds.y);
    	else if (position.equals("topLeft"))
    		p = new Point(bounds.x, bounds.y);
    	else if (position.equals("bottomRight"))
    		p = new Point(bounds.x + bounds.width - 1, bounds.y + bounds.height - 1);
    	else if (position.equals("bottomLeft"))
	    	p = new Point(bounds.x, bounds.y + bounds.height - 1);
		return p;
	}

	/**
	 * Gets the text of the cool item <p>
	 * @return the text property value as a String
	 */			
	public String getText() {
		return (String)getProperty("text");	
	}

/*
------------------------------------------------------------------

INTERFACE INFORMATION FOR CLASS: org.eclipse.swt.widgets.ToolItem

------------------------------------------------------------------
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=toString,signature=()Ljava/lang/String;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=addListener,signature=(ILorg/eclipse/swt/widgets/Listener;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=addDisposeListener,signature=(Lorg/eclipse/swt/events/DisposeListener;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=dispose,signature=()V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=getData,signature=()Ljava/lang/Object;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=getData,signature=(Ljava/lang/String;)Ljava/lang/Object;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getDisplay,signature=()Lorg/eclipse/swt/widgets/Display;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=getStyle,signature=()I]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=isDisposed,signature=()Z]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=notifyListeners,signature=(ILorg/eclipse/swt/widgets/Event;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=removeListener,signature=(ILorg/eclipse/swt/widgets/Listener;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=removeDisposeListener,signature=(Lorg/eclipse/swt/events/DisposeListener;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=setData,signature=(Ljava/lang/Object;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Widget,name=setData,signature=(Ljava/lang/String;Ljava/lang/Object;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Item,name=getImage,signature=()Lorg/eclipse/swt/graphics/Image;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Item,name=getText,signature=()Ljava/lang/String;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Item,name=setImage,signature=(Lorg/eclipse/swt/graphics/Image;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.Item,name=setText,signature=(Ljava/lang/String;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=addSelectionListener,signature=(Lorg/eclipse/swt/events/SelectionListener;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=computeSize,signature=(II)Lorg/eclipse/swt/graphics/Point;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getBounds,signature=()Lorg/eclipse/swt/graphics/Rectangle;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getControl,signature=()Lorg/eclipse/swt/widgets/Control;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getParent,signature=()Lorg/eclipse/swt/widgets/CoolBar;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setControl,signature=(Lorg/eclipse/swt/widgets/Control;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getPreferredSize,signature=()Lorg/eclipse/swt/graphics/Point;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setPreferredSize,signature=(II)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setPreferredSize,signature=(Lorg/eclipse/swt/graphics/Point;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getSize,signature=()Lorg/eclipse/swt/graphics/Point;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setSize,signature=(II)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setSize,signature=(Lorg/eclipse/swt/graphics/Point;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=getMinimumSize,signature=()Lorg/eclipse/swt/graphics/Point;]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setMinimumSize,signature=(II)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=setMinimumSize,signature=(Lorg/eclipse/swt/graphics/Point;)V]
com.rational.test.ft.domain.MethodInfo[declaringClass=org.eclipse.swt.widgets.CoolItem,name=removeSelectionListener,signature=(Lorg/eclipse/swt/events/SelectionListener;)V]
Hashtable key 1: minimumSize-java.awt.Point[x=105,y=22]
Hashtable key 2: text-
Hashtable key 3: size-java.awt.Point[x=120,y=22]
Hashtable key 4: disposed-false
Hashtable key 5: bounds-java.awt.Rectangle[x=0,y=0,width=120,height=22]
Hashtable key 6: preferredSize-java.awt.Point[x=120,y=22]
Hashtable key 7: style-4

*/	

}
