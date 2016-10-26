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
import ibm.widgets.WComboBox;

import com.rational.test.ft.RationalTestException;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Text;

/**
 * Description : Class for manipulating a SWT ComboBox<p>
 *
 * Note that since this class inherits from Rational's GuiSubitemTestObject class, 
 * you can use all the methods available from GuiSubitemTestObject on a SWTComboBox object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTComboBox extends WComboBox {

//**************************************Constructors****************************************************************
	/**
	 * Constructor for SWTComboBox <p>
	 * @param to	the TestObject that references the SWTComboBox
	 */
	public SWTComboBox(TestObject to) {
		super(to);
	}
	
//***********************Instance Methods*******************************************************************************	
	/**
	 * This method sets the text value for a Combo widget.
	 * If the combo box is typeable (i.e. allows the user to type in 
	 * any text) then the specified text will be entered in the combo box.
	 * If the combo box is not typeable (i.e. a restricted drop-down list)
	 * then the specified text must match an entry in the drop-down list.<p>
	 * @param text		the text to enter/select
	 */
	public void setText(String text) {
		final int DROP_DOWN = 33554444;		// not typeable
		final int COMBO     = 33554436;		// typeable
		
		int style = ((Integer)this.getProperty("style")).intValue();
		switch (style) {
			case DROP_DOWN:
				this.click();
				this.click(new Text(text));
				break;
			case COMBO:
				this.click();
				RationalTestScript.getScreen().inputKeys("^{home}^+{end}{del}");
				RationalTestScript.getScreen().inputChars(text);
				this.click();		// needed to close the drop-down list
				break;
			default:
				PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "SWTComboBox does not support a style of \"" + style + "\". Please submit an RFE.");
				return;
		}
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + text + "\" in " + getWidgetType() + " \"" + getName() + "\"");		
	}
		
/*	
------------------------------------------------------------------

INTERFACE INFORMATION FOR CLASS: org.eclipse.swt.widgets.Combo

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
     	DeclaringClass: org.eclipse.swt.widgets.Combo
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
     	DeclaringClass: org.eclipse.swt.widgets.Control
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
     	DeclaringClass: org.eclipse.swt.widgets.Control
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
     	DeclaringClass: org.eclipse.swt.widgets.Scrollable
     	Signature: (IIII)Lorg/eclipse/swt/graphics/Rectangle;
     ------------------------------------------------

     ------------------------------------------------
     Method: getClientArea
     	DeclaringClass: org.eclipse.swt.widgets.Scrollable
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
     Method: add
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: add
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addModifyListener
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Lorg/eclipse/swt/events/ModifyListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: addSelectionListener
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: clearSelection
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: copy
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: cut
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: deselect
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: deselectAll
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: getItem
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (I)Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: getItemCount
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getItemHeight
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getItems
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()[Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: getOrientation
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getSelection
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()Lorg/eclipse/swt/graphics/Point;
     ------------------------------------------------

     ------------------------------------------------
     Method: getSelectionIndex
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getText
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()Ljava/lang/String;
     ------------------------------------------------

     ------------------------------------------------
     Method: getTextHeight
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: getTextLimit
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()I
     ------------------------------------------------

     ------------------------------------------------
     Method: indexOf
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;)I
     ------------------------------------------------

     ------------------------------------------------
     Method: indexOf
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;I)I
     ------------------------------------------------

     ------------------------------------------------
     Method: paste
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: remove
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: remove
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (II)V
     ------------------------------------------------

     ------------------------------------------------
     Method: remove
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeAll
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ()V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeModifyListener
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Lorg/eclipse/swt/events/ModifyListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: removeSelectionListener
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Lorg/eclipse/swt/events/SelectionListener;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: select
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setItem
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (ILjava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setItems
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: ([Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setOrientation
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (I)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setSelection
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Lorg/eclipse/swt/graphics/Point;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setText
     	DeclaringClass: org.eclipse.swt.widgets.Combo
     	Signature: (Ljava/lang/String;)V
     ------------------------------------------------

     ------------------------------------------------
     Method: setTextLimit
     	DeclaringClass: org.eclipse.swt.widgets.Combo
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
     Properties: {enabled=true, textLimit=2147483647, bounds=java.awt.Rectangle[x=73,y=80,width=94,height=21], foreground=java.awt.Color[r=0,g=0,b=0], clientArea=java.awt.Rectangle[x=0,y=0,width=94,height=21], visible=true, location=java.awt.Point[x=73,y=80], reparentable=true, toolTipText=null Object, textHeight=21, selectionIndex=1, borderWidth=0, orientation=33554432, .itemText={1.2,1.3}, size=java.awt.Point[x=94,y=21], selection=java.awt.Point[x=0,y=3], item=[Ljava.lang.Object;@51a769e4, disposed=false, text=1.3, itemHeight=13, font=com.rational.test.ft.value.FontInfo[name=Tahoma,style=0,size=8], .itemCount=2, style=33554444, focusControl=false, class=org.eclipse.swt.widgets.Combo, background=java.awt.Color[r=255,g=255,b=255], itemCount=2}
     NonValue Properties: {menu=org.eclipse.swt.widgets.Menu, accessible=org.eclipse.swt.accessibility.Accessible, parent=org.eclipse.swt.widgets.Composite, horizontalBar=org.eclipse.swt.widgets.ScrollBar, layoutData=java.lang.Object, shell=org.eclipse.swt.widgets.Shell, verticalBar=org.eclipse.swt.widgets.ScrollBar, display=org.eclipse.swt.widgets.Display, layout=org.eclipse.swt.widgets.Layout, data=java.lang.Object}
     Standard Properties: {textLimit=2147483647, enabled=true, style=33554444, toolTipText=null Object, item=[Ljava.lang.Object;@5f8529e4, font=com.rational.test.ft.value.FontInfo[name=Tahoma,style=0,size=8], reparentable=true, selectionIndex=1, orientation=33554432, visible=true, itemCount=2, background=java.awt.Color[r=255,g=255,b=255], size=java.awt.Point[x=94,y=21], location=java.awt.Point[x=73,y=80], text=1.3, clientArea=java.awt.Rectangle[x=0,y=0,width=94,height=21], focusControl=false, textHeight=21, borderWidth=0, disposed=false, itemHeight=13, foreground=java.awt.Color[r=0,g=0,b=0], selection=java.awt.Point[x=0,y=3], bounds=java.awt.Rectangle[x=73,y=80,width=94,height=21], class=org.eclipse.swt.widgets.Combo}
     NOTE: standard properties are properties that apply across platforms.
     Recognition Properties: {.itemCount=2, .itemText={1.2,1.3}, .priorLabel=J2EE level:, .classIndex=1}
     ------------------------------------------------
*/
}
