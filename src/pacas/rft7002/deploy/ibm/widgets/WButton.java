// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets;

import ibm.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for manipulating Button Objects in XDE<p>
 * 
 * The preferred method for using these objects is to create a new instance from either a mapped or a dynamically located object, like so:
 * 		<dd><code>WButton myButton = new WButton(Button_MappedName());</code><br>
 * OR<br>
 * 		<dd><code>WButton myButton = new WButton(sButtonName, sProperty, sClass, BrowserPage());</code><br>
 * 
 * and then use it with the non-static methods, e.g.<br>
 * 		<dd><code>myButton.click()</code><p>
 * 
 * Note that since this class inherits from Rational's GuiTestObject class, you can use all the methods available from GuiTestObject on a WButton object.
 * Thus, you will not lose any functionality by using the widgets. For example, you can also call:
 *		<dd><code>myButton.drag(pt1, pt2)</code><p>
 * 
 * @author TSnow
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class WButton extends Widget {
	
	//html
	/**Global string for XDE Tester HTML Input Submit Button property (".value")*/
	public static String gsHtmlSubmitButtonProperty = ObjectFactory.gsHtmlButtonProp;
	/**Global string for XDE Tester HTML Input Submit Button class ("Html.INPUT.submit")*/
	public static String gsHtmlSubmitButtonClass = ObjectFactory.gsHtmlButtonClass;
	/**Global string for XDE Tester HTML Input Image Button property (".name")*/
	public static String gsHtmlImageButtonProperty = ObjectFactory.gsHtmlButtonImageProp;
	/**Global string for XDE Tester HTML Input Image Button class ("Html.INPUT.image")*/
	public static String gsHtmlImageButtonClass = ObjectFactory.gsHtmlButtonImageClass;

	//java
	/**Global string for XDE Tester Swing Button property ("name")*/
	public static String gsSwingDefaultProperty = ObjectFactory.gsSwingRBProp;
	/**Global string for XDE Tester Swing Button class ("javax.swing.JRadioButton")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingRBClass;
		
	/**Global string for XDE Tester AWT Button property ("label")*/
	public static String gsAwtDefaultProperty = ObjectFactory.gsAwtRBProp;
	/**Global string for XDE Tester AWT Button class (""java.awt.RadioButton"")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtRBClass;
	
	

//***************************Constructors***************************************************************************
	/**		
	* Constructor to find and store a dynamically located button object <p>
	* 
	* Call with <br>
  	*		<dd><code>Button myButton = new Button(sButtonText, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents 
	* @param String sButton - text of button to find
	* @param String sProperty - property value to search for (e.g. ".value")
	* @param String sClass - class type identifier (e.g. "Html.INPUT.submit") 
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WButton(String sButton, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sButton, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly specified button object <p>
	* @param button	the GuiTestObject from which to construct the widget.
	* 
	*/
	public WButton(TestObject button) {
		super(button);
	}

//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 */		
	public String getWidgetType() {
		return "button";
	}

//****************************************Static Methods***************************************************	
	/**
	* Returns a WButton widget found dynamically with the specified .value property 
	* @param buttonValue	button's value property to search for 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WButton object that matches criteria
	*/
	public static WButton findHtmlSubmitButtonFromValueProp(String buttonValue, TestObject parent)
	{
		return new WButton(buttonValue, ".value", gsHtmlSubmitButtonClass, parent);
	}
	
	/**
	* Returns an "Html.INPUT.image" button object found dynamically with the specified .name property 
	* @param buttonName		button's name property to search for
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WButton object that matches criteria
	*/
	public static WButton findHtmlImageButtonFromNameProp(String buttonName, TestObject parent)
	{
		return new WButton(buttonName, ".name", gsHtmlImageButtonClass, parent);
	}
}
