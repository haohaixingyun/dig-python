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
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.script.Subitem;

/**
 * Description : Class for manipulating a SWT TableTree<p>
 *
 * Note that since this class inherits from Rational's GuiSubitemTestObject class, 
 * you can use all the methods available from GuiSubitemTestObject on a SWTTableTree object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTTableTree extends SubitemWidget {

//*******************************************Constructors**********************************************
	/**
	 * Constructor for SWTTableTree <p>
	 * @param to	the TestObject that references the SWT TableTree
	 */
	public SWTTableTree(TestObject to) {
		super(to);
		if (!to.getClass().getName().equals("com.rational.test.ft.object.interfaces.GuiSubitemTestObject"))
			throw new ClassCastException("The TestObject parameter must be an ancestor of GuiSubitemTestObject");			
	}
	
//***********************Instance Methods*******************************************************************************	
	/**
	 * Provides access to the table that is contained in the TableTree <p>
	 * @return access to the table that's
	 *          contained in the TableTree
	 */	
	public SWTTable getTable() {
		TestObject table = (TestObject)(invoke("getTable"));
		return new SWTTable(table);
	}		

	/**
	 * Retrieves the value for an entry in the TableTree (where the value is assumed to be in the 2nd column of
	 * the TableTree).   <p>
	 * @param entry	the entry in the TableTree for which a value is to be retrieved
	 * @return returns as a String the value that appears in the GUI for the specified entry
	 */	
	// NOTE: the getTreeNodeValue method is NOT used to implement this
	//       method since entries are unique within a Properties view
	//      (that is, to avoid having to worry about whether or not
	//       the Show Categories ToolItem is selected; which would affect
	//       how the path of a property is specfied).
	//       For example, if the ToolItem is selected, the path of the 
	//       property would need to include its category (e.g., 
	//       "Info->editable" or "Attributes->id"),and if the ToolItem
	//       is not selected, the path of the property would need to 
	//       exclude its category (e.g., "editable" or "id").                 
	public String getTextValue(String entry) {
	    // access the table in the TableTree
	    SWTTable table = getTable();
	    
	    // return the data in the value column	
	    return table.getCellContent(entry, 1);
	}	

	/**
	 * Sets the value for an entry in the TableTree (where the value is assumed to be in the 2nd column of
	 * the TableTree as is the case with the TableTree in the Properties view).  If the table is not
	 * editable, then this method will successfully execute, but no change will occur to the table. <p>
	 * @param entry	the entry in the TableTree for which a value is to be set (e.g., the
	 * name of the property in the Properties view for which a value is to be set)
	 * @param value	the value to be set
	 */		
	public void setTextValue(String entry,
	                       	  String value) {	                               			       	
	    // access the table in the TableTree
	    SWTTable table = getTable();
	    
	    // access the appropriate entry in the Table
	    int index = table.findRow(entry);
	    table.click(new Cell(new Row(index), new Column(1)), new Point(5,5));
	
		// modify the property value
        //obj.getParentFrame().inputKeys(value + "{ENTER}");
        RationalTestScript.getScreen().inputKeys(value + "{ENTER}");
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set value \"" + value + "\" for " + entry + " in " + getWidgetType() + " \"" + getName() + "\"");        
    }

	/**
	 * This method can be used to retrieve column data for a given entry/node in a TableTree.  This
	 * method should be used when a node/entry is not unique within the tree (that is, when a path to 
	 * a node is needed to uniquely identify it). <p>
	 * @param node			how to access the tree node in the tableTree (e.g., atPath(...) or atList(...)). 
	 * NOTE: in the Properties view, the path is dependent on whether or not the Show Categories ToolItem is selected or not.
	 * @param cellColumn	the 0-based index of the column for which data is to be retrieved
	 * @param value		the value to be set
	 */		                                   
	public String getTableTreeCellData(Subitem node,
	                                   int cellColumn) {                 	
	                                   
	    // access the appropriate node in the TableTree
		Object item = this.getSubitem(node);
		
		// retrieve the text that appears in the specified column
		Object[] obj = {new Integer(cellColumn)};
		String value = (String)(((TestObject)item).invoke("getText", "(I);V", obj));	
		if (item.getClass().getName().equals("com.rational.test.ft.object.interfaces.TestObject")) {
			((TestObject)item).unregister();
		}
		
		return value;		    
    }			
 
	/**
	 * This method can be used to retrieve the value for a given entry/node in a TableTree, assuming
	 * the value is contained in the 2nd column of the TableTree.  This
	 * method should be used when a node/entry is not unique within the tree (that is, when a path to 
	 * a node is needed to uniquely identify it). <p>
	 * @param node		how to access the tree node in the tableTree (e.g., atPath(...) or atList(...)). 
	 * NOTE: in the Properties view, the path is dependent on whether or not the Show Categories ToolItem is selected or not.
	 * @return the value for the specified node
	 */	    
	public String getTableTreeNodeValue(Subitem node) {
	                               			
		return getTableTreeCellData(node, 1);
	}	
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "tabletree";
	}		
}
