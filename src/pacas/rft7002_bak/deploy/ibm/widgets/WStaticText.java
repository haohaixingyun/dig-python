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
import com.rational.test.util.regex.Regex;

/**
 * Description : Class for manipulating StaticText Objects in XDE<p>
 *  
 * The preferred method for using these objects is to create a new instance from either a mapped or a dynamically located object, like so:
 * 		<dd><code>WStaticText myStaticText = new WStaticText(StaticText_MappedName());</code><br>
 * OR<br>
 * 		<dd><code>WStaticText myStaticText = new WStaticText(sStaticTextName, sProperty, sClass, BrowserPage());</code><br>
 * 
 * and then use it with the non-static methods, e.g.<br>
 * 		<dd><code>myStaticText.click()</code><p>
 * 
 * Note that since this class inherits from Rational's GuiTestObject class, you can use all the methods available from GuiTestObject on a WStaticText object.
 * Thus, you will not lose any functionality by using the widgets. For example, you can also call:
 *		<dd><code>myStaticText.getScreenRectangle()</code><p>
 * 
 * @author TSnow
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class WStaticText extends Widget {
	
	/**Global string for XDE Tester HTML static text property (".text")*/	
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlStaticTextProp;
	/**Global string for XDE Tester HTML static text class ("Html.TextNode")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlStaticTextClass;
	
	/**Global string for XDE Tester AWT Static Text class ("java.awt.Label")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtStaticTextClass;
	/**Global string for XDE Tester AWT Static Text property (".label")*/
	public static String gsAwtDefaultProp = ObjectFactory.gsAwtStaticTextProp;
	
	/**Global string for XDE Tester Swing Static Text class ("javax.swing.JLabel")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingStaticTextClass;
	/**Global string for XDE Tester Swing Static Text property (".name")*/
	public static String gsSwingDefaultProp = ObjectFactory.gsSwingStaticTextProp;
	
	
//***************************Constructors*********************************************
	/**		
	* Constructor to find and store a dynamically located static text object <p>
	* 
	* Call with <br>
  	*		<dd><code>StaticText myStaticText = new StaticText(sStaticTextName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the static text and the property of that class that sStaticTextText represents 
	* @param sStaticText	text of static text to find
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.TextNode") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WStaticText(String sStaticText, String sProperty, String sClass, TestObject parent) {
		//find the object
		super(findDynamically(sStaticText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to find and store a dynamically located static text object <p>
	* 
	* Call with <br>
  	*		<dd><code>StaticText myStaticText = new StaticText(sStaticTextName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the static text and the property of that class that sStaticTextText represents 
	* @param reStaticText	regular expression that identifies the text you are searching for
	* @param sStaticText	text of static text to find
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.TextNode") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WStaticText(Regex reStaticText, String sProperty, String sClass, TestObject parent) {
		//find the object
		super(findDynamically(reStaticText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly static text object <p>
	* @param statictext 	the GuiTestObject from which to construct the widget.
	*/
	public WStaticText(TestObject statictext) {
		super(statictext);
	}
	
//****************************Instance Methods******************************************************	
	
	/**		
	* Gets the text of the text field stored in this particular Label object <p>
	* @return the text of the text field
	* @author Ann Kopren
	*/
	public String getText() {
		if (ObjectFactory.isHTML(this))
			return getProperty(gsHtmlDefaultProperty).toString();
		else
			return getProperty("text").toString();
	}
	
//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "statictext";
	}
	
//*********************Static Methods for dynamically-found objects***********************
	
	/**
	* Returns a WStaticText widget found dynamically with the specified text property
	* @param staticText	text property to search for 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return  a WStaticText object that matches criteria
	*/
	public static WStaticText findHtmlStaticTextFromTextProp(String staticText, TestObject parent)
	{
		return new WStaticText(staticText, gsHtmlDefaultProperty, gsHtmlDefaultClass, parent);
	}
	
	/**
	* Returns a WStaticText widget object found dynamically with the specified text property
	* @param staticText	regular expression identifying text property to search for 
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return WStaticText - a WStaticText object that matches criteria
	*/
	public static WStaticText findHtmlStaticTextFromTextProp(Regex staticText, TestObject parent)
	{
		return new WStaticText(staticText, gsHtmlDefaultProperty, gsHtmlDefaultClass, parent);
	}

	/**
	* Returns a WStaticText widget found dynamically with the specified name property
	* @param staticText 	the name property to search for
	* @param parent		Parent TestObject from which to search (e.g MainPanel() or MainFrame()). 
	* @return a WStaticText object that matches criteria
	* @author Ann Kopren
	*/
	public static WStaticText findSwingStaticTextFromNameProp(String staticText, TestObject parent)
	{
		return new WStaticText(staticText, "name", gsHtmlDefaultClass, parent);
	}
	
	/**
	* Returns a WStaticText widget found dynamically with the specified text property
	* @param staticText	regular expression identifying the name property value to search for 
	* @param parent		Parent TestObject from which to search (e.g MainPanel() or MainFrame()). 
	* @return a WStaticText object that matches criteria
	* @author Ann Kopren
	*/
	public static WStaticText findSwingStaticTextFromNameProp(Regex staticText, TestObject parent)
	{
		return new WStaticText(staticText, "name", gsSwingDefaultClass, parent);
	}
}

