// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.loggers.control.PackageLoggingController;
import ibm.widgets.ancestors.SubitemWidget;
import java.awt.Point;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.value.NameSet;

/**
 * Description : Class for manipulating a SWT CTabFolder<p>
 *
 * Note that since this class inherits from Rational's GuiSubitemTestObject class, 
 * you can use all the methods available from GuiTestObject on a SWTCTabFolder object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTCTabFolder extends SubitemWidget {

//*******************************************Constructors**********************************************
	/**
	 * Constructor for SWTCTabFolder <p>
	 * @param to	the TestObject that references the SWT CTabFolder
	 */
	public SWTCTabFolder(TestObject to) {
		super(to);
	}

//*****************************************Instance Methods**********************************************
	/**
	 * Identifies if a page with the specified tab text appears within the folder <p>
	 * @param tabName	the text of the tab to be located
	 * @return a boolean indicating whether the specified tab (page) is contained in the folder (true-it is, false-it isn't)
	 */
	public boolean exists (String tabName) {
		NameSet files = (NameSet)(this.getProperty(".tabs"));
		boolean exists = (files.contains(tabName)) ? true : isTabDirty(tabName);
		return exists;
	}	

	/**
	 * Returns SWTCTabItem instances that reference the CTabItems contained in the folder <p>
	 * @return an array of SWTCTabItem instances, each of which references a CTabItem contained in the folder.
	 * If a null is not returned, each of the SWTCTabItem instances or the array of them should be
	 * unregistered when no longer needed (visible) to avoid memory leaks.
	 */		
	public SWTCTabItem[] getItems() {
		TestObject[] objs = (TestObject[])invoke("getItems");
		SWTCTabItem[] items = null;
		if (objs != null) {
			items = new SWTCTabItem[objs.length];			
			for (int i=0; i<objs.length; i++)
				items[i] = new SWTCTabItem(objs[i]);
		}
		return items;		
	}

	/**
	 * Returns the number of pages (tabs) that are contained in the folder <p>
	 * @return the number of pages (tabs) in the folder
	 */			
	public int getItemCount() {
		return ((Integer)getProperty("itemCount")).intValue();
	}

	/**
	 * Returns the location of the folder <p>
	 * @return a java.awt.Point that represents the location of the folder
	 */		
	public Point getLocation() {			
		return (Point)getProperty("location");			
	}

	/**
	 * Returns an object that references the TabItem for the current page <p>
	 * @return an SWTCTabItem instance that references the TabItem for the current page.
	 * If a null is not returned, the returned object should be unregistered when 
	 * no longer needed to avoid memory leaks.
	 */	
	public SWTCTabItem getSelection() {
		SWTCTabItem item = null;
		TestObject obj = (TestObject)getProperty("selection");
		if (obj != null) {
			item = new SWTCTabItem(obj);
		}
		return item;			
	}

	/**
	 * Returns the index of the current page <p>
	 * @return the index of the current page in the folder
	 */		
	public int getSelectionIndex() {
		return ((Integer)getProperty("selectionIndex")).intValue();		
	}	

	/**
	 * Identifies if the specified page in the folder is dirty (was modified) <p>
	 * @param tabName	the text of the requested tab as it appears in the GUI
	 */	
	public boolean isTabDirty (String tabName) {
		NameSet files = (NameSet)(this.getProperty(".tabs"));
		return (files.contains("*"+tabName));		
	}

	/**
	 * Navigates to the page with the specified tab text <p>
	 * @param tabText	the text of the requested tab as it appears in the GUI
	 */	
	public void tabToPage(String tabText) {
//		int pageCount = getItemCount();
//		SWTCTabItem[] pages = new SWTCTabItem[pageCount];
//		pages = getItems();
//		boolean notFound = true;
//		for (int i=0; i<pageCount; i++) {
//			if (notFound) {
//				if (pages[i].getText().indexOf(tabText) >= 0) {
//					notFound = false;
//					click(pages[i].getLocation());
//				}
//			}
//			pages[i].unregister();
//		}
		click(new Text(tabText));
		// the following is needed since click(Subitem) is not supported in any widget ancestor class		
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
			"Clicked on the \"" + tabText + "\" tab in " + getWidgetType() + " \"" + getName() + "\"");
	}	
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "folder";
	}	
	
/*
------------------------------------------------------------------

INTERFACE INFORMATION FOR CLASS: org.eclipse.swt.custom.CTabFolder

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
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/widgets/Display;
     ------------------------------------------------

     ------------------------------------------------
     Method: getStyle
     	DeclaringClass: org.eclipse.swt.widgets.Widget
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: isDisposed
     	DeclaringClass: org.eclipse.swt.widgets.Control
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
     Method: addControlListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/ControlListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addFocusListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/FocusListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addHelpListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/HelpListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addKeyListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/KeyListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addMouseListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addMouseTrackListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseTrackListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addMouseMoveListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseMoveListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addPaintListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/PaintListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addTraverseListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/TraverseListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: computeSize
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (II)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: computeSize
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (IIZ)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: forceFocus
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: getAccessible
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/accessibility/Accessible;
     ------------------------------------------------

     ------------------------------------------------
     Method: getBackground
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Color;
     ------------------------------------------------

     ------------------------------------------------
     Method: getBorderWidth
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getBounds
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Rectangle;
     ------------------------------------------------

     ------------------------------------------------
     Method: getEnabled
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: getFont
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Font;
     ------------------------------------------------

     ------------------------------------------------
     Method: getForeground
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Color;
     ------------------------------------------------

     ------------------------------------------------
     Method: getLayoutData
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Ljava/lang/Object;
     ------------------------------------------------

     ------------------------------------------------
     Method: getLocation
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: getMenu
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/widgets/Menu;
     ------------------------------------------------

     ------------------------------------------------
     Method: getParent
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/widgets/Composite;
     ------------------------------------------------

     ------------------------------------------------
     Method: getShell
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/widgets/Shell;
     ------------------------------------------------

     ------------------------------------------------
     Method: getSize
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: getToolTipText
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: getVisible
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: internal_new_GC
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/GCData;)I
     ------------------------------------------------

     ------------------------------------------------
     Method: internal_dispose_GC
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (ILorg/eclipse/swt/graphics/GCData;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: isEnabled
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: isFocusControl
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: isReparentable
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: isVisible
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: moveAbove
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/widgets/Control;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: moveBelow
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/widgets/Control;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: pack
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: pack
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: redraw
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: redraw
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (IIIIZ)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeControlListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/ControlListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeFocusListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/FocusListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeHelpListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/HelpListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeKeyListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/KeyListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeMouseTrackListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseTrackListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeMouseListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeMouseMoveListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/MouseMoveListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removePaintListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/PaintListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeTraverseListener
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/events/TraverseListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setBackground
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/graphics/Color;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setBounds
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (IIII)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setBounds
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Rectangle;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setCapture
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setCursor
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Cursor;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setEnabled
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setFocus
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ()Z
     ------------------------------------------------

     ------------------------------------------------
     Method: setFont
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/graphics/Font;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setForeground
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Color;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setLayoutData
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Ljava/lang/Object;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setLocation
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (II)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setLocation
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Point;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setMenu
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/widgets/Menu;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setRedraw
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSize
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (II)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSize
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Point;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setToolTipText
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setVisible
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: toControl
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (II)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: toControl
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Point;)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: toDisplay
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (II)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: toDisplay
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/graphics/Point;)Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: traverse
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (I)Z
     ------------------------------------------------

     ------------------------------------------------
     Method: update
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: setParent
     	DeclaringClass: org.eclipse.swt.widgets.Control
     	Signature: (Lorg/eclipse/swt/widgets/Composite;)Z
     ------------------------------------------------

     ------------------------------------------------
     Method: computeTrim
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (IIII)Lorg/eclipse/swt/graphics/Rectangle;
     ------------------------------------------------

     ------------------------------------------------
     Method: getClientArea
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()Lorg/eclipse/swt/graphics/Rectangle;
     ------------------------------------------------

     ------------------------------------------------
     Method: getHorizontalBar
     	DeclaringClass: org.eclipse.swt.widgets.Scrollable
     	Signature: ()Lorg/eclipse/swt/widgets/ScrollBar;
     ------------------------------------------------

     ------------------------------------------------
     Method: getVerticalBar
     	DeclaringClass: org.eclipse.swt.widgets.Scrollable
     	Signature: ()Lorg/eclipse/swt/widgets/ScrollBar;
     ------------------------------------------------

     ------------------------------------------------
     Method: getChildren
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ()[Lorg/eclipse/swt/widgets/Control;
     ------------------------------------------------

     ------------------------------------------------
     Method: getLayout
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ()Lorg/eclipse/swt/widgets/Layout;
     ------------------------------------------------

     ------------------------------------------------
     Method: getTabList
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ()[Lorg/eclipse/swt/widgets/Control;
     ------------------------------------------------

     ------------------------------------------------
     Method: layout
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: layout
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setLayout
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: (Lorg/eclipse/swt/widgets/Layout;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setTabList
     	DeclaringClass: org.eclipse.swt.widgets.Composite
     	Signature: ([Lorg/eclipse/swt/widgets/Control;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addSelectionListener
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addCTabFolderListener
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabFolderListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: getTabHeight
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getItem
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (I)Lorg/eclipse/swt/custom/CTabItem;
     ------------------------------------------------

     ------------------------------------------------
     Method: getItem
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/graphics/Point;)Lorg/eclipse/swt/custom/CTabItem;
     ------------------------------------------------

     ------------------------------------------------
     Method: getItemCount
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getItems
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()[Lorg/eclipse/swt/custom/CTabItem;
     ------------------------------------------------

     ------------------------------------------------
     Method: getSelection
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()Lorg/eclipse/swt/custom/CTabItem;
     ------------------------------------------------

     ------------------------------------------------
     Method: getSelectionIndex
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getTopRight
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()Lorg/eclipse/swt/widgets/Control;
     ------------------------------------------------

     ------------------------------------------------
     Method: indexOf
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabItem;)I
     ------------------------------------------------

     ------------------------------------------------
     Method: removeSelectionListener
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeCTabFolderListener
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabFolderListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelectionBackground
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ([Lorg/eclipse/swt/graphics/Color;[I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelectionBackground
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/graphics/Image;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setBorderVisible
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelectionForeground
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/graphics/Color;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setInsertMark
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabItem;Z)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setInsertMark
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (IZ)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelection
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setTopRight
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/widgets/Control;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: showItem
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabItem;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: showSelection
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelection
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
     	Signature: (Lorg/eclipse/swt/custom/CTabItem;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setTabHeight
     	DeclaringClass: org.eclipse.swt.custom.CTabFolder
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
     Properties: {location=java.awt.Point[x=0,y=0], font=com.rational.test.ft.value.FontInfo[name=Tahoma,style=0,size=8], enabled=true, toolTipText=null Object, visible=true, class=org.eclipse.swt.custom.CTabFolder, borderWidth=0, disposed=false, tabHeight=19, bounds=java.awt.Rectangle[x=0,y=0,width=264,height=246], style=34604032, background=java.awt.Color[r=212,g=208,b=200], size=java.awt.Point[x=264,y=246], itemCount=2, reparentable=true, selectionIndex=1, clientArea=java.awt.Rectangle[x=1,y=1,width=260,height=222], foreground=java.awt.Color[r=0,g=0,b=0], focusControl=false}
     NonValue Properties: {menu=org.eclipse.swt.widgets.Menu, accessible=org.eclipse.swt.accessibility.Accessible, parent=org.eclipse.swt.widgets.Composite, horizontalBar=org.eclipse.swt.widgets.ScrollBar, topRight=org.eclipse.swt.widgets.Control, selection=org.eclipse.swt.custom.CTabItem, layoutData=java.lang.Object, shell=org.eclipse.swt.widgets.Shell, verticalBar=org.eclipse.swt.widgets.ScrollBar, display=org.eclipse.swt.widgets.Display, layout=org.eclipse.swt.widgets.Layout, data=java.lang.Object}
     Standard Properties: {location=java.awt.Point[x=0,y=0], font=com.rational.test.ft.value.FontInfo[name=Tahoma,style=0,size=8], enabled=true, toolTipText=null Object, visible=true, class=org.eclipse.swt.custom.CTabFolder, borderWidth=0, disposed=false, tabHeight=19, bounds=java.awt.Rectangle[x=0,y=0,width=264,height=246], style=34604032, background=java.awt.Color[r=212,g=208,b=200], size=java.awt.Point[x=264,y=246], itemCount=2, reparentable=true, selectionIndex=1, clientArea=java.awt.Rectangle[x=1,y=1,width=260,height=222], foreground=java.awt.Color[r=0,g=0,b=0], focusControl=false}
     NOTE: standard properties are properties that apply across platforms.
     Recognition Properties: {tabCount=2, .tabs={Struts Explorer,Project Navigator}, .classIndex=0}
     ------------------------------------------------
*/
}
