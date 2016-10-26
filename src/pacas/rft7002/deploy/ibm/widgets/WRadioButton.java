// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets;

import ibm.loggers.control.PackageLoggingController;
import ibm.widgets.ancestors.ToggleWidget;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.State;

/**
 * Description : Class for manipulating RadioButton Objects in XDE<p>
 *  
 * The preferred method for using these objects is to create a new instance from either a mapped or a dynamically located object, like so:
 * 		<dd><code>WRadioButton myRadioButton = new WRadioButton(RadioButton_MappedName());</code><br>
 * OR<br>
 * 		<dd><code>WRadioButton myRadioButton = new WRadioButton(sRadioButtonName, sProperty, sClass, BrowserPage());</code><br>
 * 
 * and then use it with the non-static methods, e.g.<br>
 * 		<dd><code>myRadioButton.isSelected()</code><p>
 * 
 * Note that since this class inherits from Rational's ToggleGUITestObject and GuiTestObject class, you can use all the methods available from these classes on a WRadioButton object.
 * Thus, you will not lose any functionality by using the widgets. For example, you can also call:
 *		<dd><code>myRadioButton.drag(pt1, pt2)</code><p>
 * 
 * @author TSnow
 * @version 2.2
 * Last Modified: 04/07/04
 */

public class WRadioButton extends ToggleWidget {
	
	/**Global string for XDE Tester HTML radio button property (".id")*/
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlRBProp;
	/**Global string for XDE Tester HTML radio button class ("Html.INPUT.radio")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlRBClass;
	
	//java
	/**Global string for XDE Tester Swing Button property ("name")*/
	public static String gsSwingDefaultProperty = ObjectFactory.gsSwingRBProp;
	/**Global string for XDE Tester Swing Button class ("javax.swing.JButton")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingRBClass;
		
	/**Global string for XDE Tester AWT Button property ("label")*/
	public static String gsAwtDefaultProperty = ObjectFactory.gsAwtRBProp;
	/**Global string for XDE Tester AWT Button class ("java.awt.Button")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtRBClass;
	
//***************************Constructors***************************************************************************
	/**		
	* Constructor to find and store a dynamically located radio button object <p>
	* 
	* Call with <br>
  	*		<dd><code>RadioButton myRadioButton = new RadioButton(sRadioButtonName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the radio button and the property of that class that sRadioButtonText represents 
	* @param sRadioButton	text of radio button to find
	* @param sProperty		property value to search for (e.g. ".id")
	* @param sClass		class type identifier (e.g. "Html.INPUT.radio") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WRadioButton(String sRadioButton, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sRadioButton, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly specified radio radio button object <p>
	* @param radiobutton 	the ToggleGUITestObject from which to construct the widget.
	*/
	public WRadioButton(TestObject radiobutton) {
		super(radiobutton);
	}	

//*********************************Instance Methods*********************************************************************
	
	/**		
	* Selects this RadioButton <p>
	* 
	* */
	public void select()
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
		String sWidgetType = getWidgetType();
		String sWidgetName = getName();
		
		this.clickToState(State.selected());
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Selected " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	/**		
	* Determines whether the radio button stored in this particular RadioButton object is selected<p>
	* @return true if radio button item is selected; false otherwise
	* */
	public boolean isSelected()
	{
		return this.getState().isSelected();	
	}
	
	/**		
	* Determines whether the radio button stored in this particular RadioButton object is <u>not</u> selected<p>
	* @return true if Radio Button is <u>not</u> selected; false otherwise
	* */
	public boolean isNotSelected()
	{
		return !isSelected();	
	}

//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "radiobutton";
	}
			
//*********************************Static Methods*********************************************************************

	/**		
	* Selects an explicitly specified RadioButton <p>
	* @param to	RadioButton TestObject
	* 
	* */
	public static void select(TestObject to)
	{
		new WRadioButton(to).select();
	}	
	/**		
	* Selects an explicitly specified RadioButton <p>
	* @param to	RadioButton TestObject
	* 
	* */
	public static void click(TestObject to)
	{
		new WRadioButton(to).click();
	}	
		
	/**		
	* Determines if an explicitly specified RadioButton is selected<p>
	* @param to	RadioButton TestObject in question
	* @return true if radio button item is selected; false otherwise
	* */
	public static boolean isSelected(TestObject to)
	{
		return new WRadioButton(to).isSelected();	
	}
	
	/**		
	* Determines if an explicitly specified Radio Button is <u>not</u> selected<p>
	* @param to	RadioButton TestObject in question
	* @return true if Radio Button is <u>not</u> selected; false otherwise
	* */
	public static boolean isNotSelected(TestObject to)
	{
		return !isSelected(to);	
	}

//*********************Static dynamic methods*******************	
	/**		
	* Selects a dynamically-located RadioButton item <p>
	* 
	* Call with <br>
  	*		<dd><code>RadioButton.click(sRadioButtonName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the radio button and the property of that class that sRadioButtonText represents 
	* @param sRadioButton	text of radio button to find
	* @param sProperty		property value to search for (e.g. ".id")
	* @param sClass		class type identifier (e.g. "Html.INPUT.radio") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void click(String sRadioButton, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			TestObject to = findDynamically(sRadioButton, sProperty, sClass, parent);
			
			to.waitForExistence(); //will throw ObjectNotFoundException to caller if not found within default timeout
			
			click(to);
	
			//unregister the object
			to.unregister();
	}	
	
	/**		
	* Determines if a dynamically-located Radio Button item is selected<p>
	* 
	* Call with <br>
  	*		<dd><code>RadioButton.isSelected(sRadioButtonName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the radio button and the property of that class that sRadioButtonText represents 
	* @param sRadioButton	text of radio button to find
	* @param sProperty		property value to search for (e.g. ".id")
	* @param sClass		class type identifier (e.g. "Html.INPUT.radio") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if Radio Button is selected; false otherwise 		
	*
	*/
	public static boolean isSelected(String sRadioButton, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			
		boolean bReturn;
		TestObject to = findDynamically(sRadioButton, sProperty, sClass, parent);
		
		to.waitForExistence(); //will throw ObjectNotFoundException to caller if not found within default timeout
			
		bReturn = isSelected(to);
		to.unregister();
		return bReturn;
	}
	
	/**		
	* Determines if a dynamically located Radio Button is <u>not</u> selected<p>
	* 
	* Call with <br>
  	*		<dd><code>RadioButton.isNotSelected(sRadioButtonName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the radio button and the property of that class that sRadioButtonText represents 
	* @param sRadioButton	text of radio button to find
	* @param sProperty		property value to search for (e.g. ".id")
	* @param sClass		class type identifier (e.g. "Html.INPUT.radio") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if Radio Button is <u>not</u> selected; false otherwise
	*
	*/
	public static boolean isNotSelected(String sRadioButton, String sProperty, String sClass, TestObject parent)
	{
		return !isSelected(sRadioButton, sProperty, sClass, parent);	
	}
	
}