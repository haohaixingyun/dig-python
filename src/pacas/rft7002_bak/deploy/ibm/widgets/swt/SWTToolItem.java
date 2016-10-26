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
 * Description : Class for querying a ToolItem for example in a SWT ToolItem button or checkbox<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 08/03/04
 */
public class SWTToolItem extends Widget {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTToolItem <p>
	 * @param to	the TestObject that references the org.eclipse.swt.widgets.ToolItem, which supports various roles (e.g., Button and CheckBox) and a .java.swt.ToolItemProxy
	 */
	public SWTToolItem(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Gets the top right coordinate point of a tool item, relative to whatever control
	 * its toolbar is contained in. For example, if you want to select a tool item that's nested within the workbench's coolbar,
	 * you could do the following: <br>
	 * GenericWorkbench wb = new GenericWorkbench(); // where GenericWorkbench is an appobject<br>
	 * SWTCoolBar cb = wb.getCoolBar(); <br>
	 * SWTToolItem ti = cb.getToolItem("Open Type"); <br>
	 * cb.click(ti.getPointWithinToolBarContainer()); <br>
	 * ti.unregister();<br>
	 * SWTToolItem item = aoGenericWorkbench.getCoolBar().getToolItem("Open Type"); <br>
	 * getWorkbenchCoolBar().click(item.getPointWithinToolBarContainer());<br>
	 * item.unregister(); <p>
	 * @return the top right coordinate of the ToolItem, relative to
	 * 			whatever its ToolBar is contained in (e.g., within a CoolBar).
	 */	
	public Point getPointWithinToolBarContainer() { 	    		
		return getPointWithinToolBarContainer("topRight");		
	}
	
	/**
	 * Gets the specified coordinate point of a tool item, relative to whatever control
	 * its toolbar is contained in.
	 * @param position		indicates what position is desired: <br>
	 * Choices are "topRight", "topLeft", "bottomRight", "bottomLeft".
	 * @return the coordinates of the ToolItem relative to
	 * whatever its parent ToolBar is contained in
	 * @see getPointWithinToolBarContainer() for more information
	 */	
	public Point getPointWithinToolBarContainer(String position) {
		Point p = null;
//@KAE comment out the following line -- THIS CAN BE REMOVED
//		SWTToolBar parent = (SWTToolBar)getParent();
//@KAE added the following 3 lines
		SWTToolBar tb = null;
		TestObject parent = getParent();
		if (parent != null)
		    tb = new SWTToolBar(parent);
//@KAE comment out the following 2 lines -- THESE CAN BE REMOVED
//		Point barPoint = parent.getPointWithinParent();
//		parent.unregister(); 
//@KAE and replaced with the following 2 lines
		Point barPoint = tb.getPointWithinParent();
		tb.unregister();      			     	
    	Rectangle bounds = (Rectangle)getProperty("bounds");	
    	if (position.equals("topRight")) 
			p = new Point(barPoint.x + bounds.x + bounds.width - 1, 
		                  barPoint.y + bounds.y);    		
    	else if (position.equals("topLeft"))
			p = new Point(barPoint.x + bounds.x, 
		                   barPoint.y + bounds.y);      		
    	else if (position.equals("bottomRight"))
			p = new Point(barPoint.x + bounds.x + bounds.width - 1, 
		                   barPoint.y + bounds.y + bounds.height - 1);      	    		
    	else if (position.equals("bottomLeft")) 	
			p = new Point(barPoint.x + bounds.x, 
		                   barPoint.y + bounds.y + bounds.height - 1);   	    		
		return p;		
	}

	/**
	 * Provides access to the parent control of the tool item <p>
	 * @return the parent of the tool item as a SWTToolBar instance
	 */		
	public TestObject getParent() {
//@KAE - comment out the following lines (WHICH CAN BE REMOVED) and added the one line
//		SWTToolBar parent = null;
//		TestObject obj = super.getParent();
		TestObject parent = super.getParent();
//		if (obj != null) {		
//			parent = new SWTToolBar((ScrollTestObject)obj);		
//		}
		return parent;
	}

	/**
	 * Gets the top right coordinate point of a tool item, relative to its parent (that is, within the ToolBar that contains it) <p>
	 * @return the top right coordinate of the ToolItem
	 *          (relative to the ToolBar that it's contained in)
	 */	
	public Point getPointWithinParent() {
		return getPointWithinParent("topRight");
	}

	/**
	 * Gets the specified coordinate point of a tool item, relative to its parent (that is, within the ToolBar that contains it) <p>
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
	 * Gets the tool tip text of the tool item <p>
	 * @return the toolTipText property value as a String
	 */			
	public String getToolTipText() {
		return (String)getProperty("toolTipText");	
	}

/*
------------------------------------------------------------------

INTERFACE INFORMATION FOR CLASS: org.eclipse.swt.widgets.ToolItem

------------------------------------------------------------------

     ------------------------------------------------
     Method: equals
     	DeclaringClass: java.lang.Object
     	Signature: (Ljava/lang/Object;)Z
     ------------------------------------------------

     ------------------------------------------------
     Method: hashCode
     	DeclaringClass: java.lang.Object
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: toString
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: addListener
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (ILorg/eclipse/swt/widgets/Listener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addDisposeListener
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (Lorg/eclipse/swt/events/DisposeListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: dispose
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: getData
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()Ljava/lang/Object;
     ------------------------------------------------

     ------------------------------------------------
     Method: getData
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (Ljava/lang/String;)Ljava/lang/Object;
     ------------------------------------------------

     ------------------------------------------------
     Method: getDisplay
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/widgets/Display;
     ------------------------------------------------

     ------------------------------------------------
     Method: getStyle
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: isDisposed
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: notifyListeners
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (ILorg/eclipse/swt/widgets/Event;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeListener
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (ILorg/eclipse/swt/widgets/Listener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeDisposeListener
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (Lorg/eclipse/swt/events/DisposeListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setData
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (Ljava/lang/Object;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setData
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: (Ljava/lang/String;Ljava/lang/Object;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: getImage
     	DeclaringClass: org.eclipse.swt.widgets.Item
     	Signature: ()Lorg/eclipse/swt/graphics/Image;
     ------------------------------------------------

     ------------------------------------------------
     Method: getText
     	DeclaringClass: org.eclipse.swt.widgets.Item
     	Signature: ()Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: setImage
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/graphics/Image;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setText
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addSelectionListener
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: getBounds
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/graphics/Rectangle;
     ------------------------------------------------

     ------------------------------------------------
     Method: getControl
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/widgets/Control;
     ------------------------------------------------

     ------------------------------------------------
     Method: getDisabledImage
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/graphics/Image;
     ------------------------------------------------

     ------------------------------------------------
     Method: getEnabled
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: getHotImage
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/graphics/Image;
     ------------------------------------------------

     ------------------------------------------------
     Method: getParent
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Lorg/eclipse/swt/widgets/ToolBar;
     ------------------------------------------------

     ------------------------------------------------
     Method: getSelection
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: getToolTipText
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: getWidth
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: isEnabled
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: removeSelectionListener
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setControl
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/widgets/Control;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setEnabled
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setDisabledImage
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/graphics/Image;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setHotImage
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Lorg/eclipse/swt/graphics/Image;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelection
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setToolTipText
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setWidth
     	DeclaringClass: org.eclipse.swt.widgets.ToolItem
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: getClass
     	DeclaringClass: java.lang.Object
     	Signature: ()Ljava/lang/Class;
     ------------------------------------------------

     ------------------------------------------------
     Method: notify
     	DeclaringClass: java.lang.Object
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: notifyAll
     	DeclaringClass: java.lang.Object
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: wait
     	DeclaringClass: java.lang.Object
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: wait
     	DeclaringClass: java.lang.Object
     	Signature: (J)V
     ------------------------------------------------

     ------------------------------------------------
     Method: wait
     	DeclaringClass: java.lang.Object
     	Signature: (JI)V
     ------------------------------------------------

     ------------------------------------------------
     Properties: {width=23, text=, enabled=true, toolTipText=Create a JSP File, disposed=false, bounds=java.awt.Rectangle[x=23,y=0,width=23,height=22], selection=false, style=8, class=org.eclipse.swt.widgets.ToolItem}
     NonValue Properties: {parent=org.eclipse.swt.widgets.ToolBar, disabledImage=org.eclipse.swt.graphics.Image, control=org.eclipse.swt.widgets.Control, image=org.eclipse.swt.graphics.Image, hotImage=org.eclipse.swt.graphics.Image, display=org.eclipse.swt.widgets.Display, data=java.lang.Object}
     Standard Properties: {width=23, text=, enabled=true, toolTipText=Create a JSP File, disposed=false, bounds=java.awt.Rectangle[x=23,y=0,width=23,height=22], selection=false, style=8, class=org.eclipse.swt.widgets.ToolItem}
     NOTE: standard properties are properties that apply across platforms.
     Recognition Properties: {text=, toolTipText=Create a JSP File}
     ------------------------------------------------
*/	

}
