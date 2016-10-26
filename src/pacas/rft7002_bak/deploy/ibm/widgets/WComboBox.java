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

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Class for manipulating ComboBox Objects in XDE<p>
 * 
 * The preferred method for using these objects is to create a new instance from either a mapped or a dynamically located object, like so:
 * 		<dd><code>WListBox myListBox = new WListBox(ListBox_MappedName());</code><br>
 * OR<br>
 * 		<dd><code>WListBox myListBox = new WListBox(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
 * 
 * and then use it with the non-static methods, e.g.<br>
 * 		<dd><code>myListBox.getSelected()</code><p>
 * 
 * Note that since this class inherits from Rational's GuiSubitemTestObject and GuiTestObject class, you can use all the methods available from these classes on a WListBox object.
 * So, for example, you can also call:
 *		<dd><code>myListBox.drag(pt1, pt2)</code><p>
 *
 * @author TSnow
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class WComboBox extends WListBox {
	
	//*******************************************Constructors**********************************************
	/**		
	* Constructor to find and store a dynamically located combobox object <p>
	* Note that comboboxes are listboxes on which you can type in new values,<br>
	* so this class is just adds one additional method to listbox: setText()<p>
	* 
	* Call with <br>
  	*		<dd><dd><code>WComboBox myComboBox = new WComboBox(sComboBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sComboBoxText represents 
	* @param String sComboBox - name of list box to find
	* @param String sProperty - property value to search for (e.g. ".name")
	* @param String sClass - class type identifier (e.g. "Html.SELECT") 
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WComboBox(String sComboBox, String sProperty, String sClass, TestObject parent) {
		super(sComboBox, sProperty, sClass, parent);
	}
	
	/**		
	* Constructor to store an explicitly specified listbox object <p>
	* @param combobox		the GuiSubitemTestObject from which to construct the widget.
	*/
	public WComboBox(TestObject combobox) {
		super(combobox);
	}
	
//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "combobox";
	}

//*****************************************Instance Methods**********************************************	
	/** 
	* Sets the text of the text field stored in this particular WComboBox object <p>
	* (Note: if this isn't "really" a combobox (i.e. if I can't type text into it), this method will attempt to select the value entered
	* @param text		text to set the combobox to
	*/
	public void setText(String text) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
		String sWidgetType = getWidgetType();
		String sWidgetName = getName();
		
		boolean realCombo = false; //only a few cases of "real" comboboxes, that can actually type text into
		
		//check whether this is a "real" combobox
		if (!ObjectFactory.isHTML(this)) //no html combobox
		{ 
			if (this.getProperty("class").toString().equals("javax.swing.JComboBox")) //swing combobox 	
			{ 
				realCombo = true;
			}
			else if (this.getProperties().containsKey("style")) //SWT combobox (from Kathy Engles)
			{
				final int DROP_DOWN = 33554444;		// not typeable
				final int COMBO     = 33554436;		// typeable
				
				if (((Integer)this.getProperty("style")).intValue() == COMBO) {
					realCombo=true;
				}
			}
		}
		
		//if it is a "real" combo, then set the text; otherwise, select the item
		if (realCombo) {
				this.click();
				RationalTestScript.getScreen().inputKeys("^{home}^+{end}{del}");
				RationalTestScript.getScreen().inputChars(text);
				this.click();		// needed to close the drop-down list
		} else {
			select(text);
		}
		
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + text + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
}
