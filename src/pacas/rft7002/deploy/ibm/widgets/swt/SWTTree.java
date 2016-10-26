// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.widgets.swt;

import ibm.loggers.GenericLogger;
import ibm.loggers.control.IPackageLogger;
import ibm.loggers.control.IPackageLoggerConstants;
import ibm.loggers.control.PackageLoggingController;
import ibm.widgets.ancestors.SubitemWidget;

import com.rational.test.ft.object.interfaces.IScreen;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.List;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.ITestDataTreeNodes;

/**
 * Description : Class for manipulating a SWT Tree<p>
 *
 * Note that since this class inherits from Rational's GuiSubitemTestObject class, 
 * you can use all the methods available from GuiSubitemTestObject on a SWTTree object.
 * Thus, you will not lose any functionality by using this widget. 
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class SWTTree extends SubitemWidget {

//*******************************************Constructors**********************************************
	/**
	 * Constructor for SWTTree <p>
	 * @param to	the TestObject that references the SWT Tree
	 */
	public SWTTree(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************
	/**
	 * Collapses the specified node <p>
	 * @param node		the node within the tree that is to be collapsed.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud be passed
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 */	
	public void collapseTreeNode(Subitem node) {
		selectTreeNode(node);
		collapseSelectedNode();
	}
	
	/**
	 * Expands the specified node <p>
	 * @param node		the node within the tree that is to be expanded.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 */	   
	public void expandTreeNode(Subitem node) {
		selectTreeNode(node);
		expandSelectedTreeNode();
	}	

	/**
	 * This method was created to allow access to the second column
	 * of text that appears for the various nodes of a tree.  
	 * Specifically, it was created to support verification
	 * of values for element leaf nodes and attributes for the tree
	 * that's contained in the Design page of the XML editor.<p>
	 * @param node		identifies how to access the node within the tree.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 * @return the value of the specified tree node.
	 */
	public String getTreeNodeValue(Subitem node) {		
        selectTreeNode(node);
		IScreen screen = RationalTestScript.getScreen();
        screen.inputKeys("{ENTER}");
        String text = this.getTextOfFocusControl();     
		screen.inputKeys("{ESCAPE}");        
        return text;		
	}

	/**
	 * Gets the first node that is selected in the tree <p>
	 * @return the first node that is selected.
	 */		
	public SWTTreeItem getFirstSelectedNode() {
		TestObject[] selectedNodes = (TestObject[])invoke("getSelection");	 	
	 	SWTTreeItem firstSelectedNode = new SWTTreeItem(selectedNodes[0]);			
	 	for (int i=1; i<selectedNodes.length; i++)
	 		selectedNodes[i].unregister();
	 	return firstSelectedNode;
	}
	
	/**
	 * Provides access to the object that is represented in the GUI by the specified node <p>
	 * @param node		identifies how to access the node within the tree.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 * @return the SWTTreeItem for the specified node so it can 
	 * be queried for additional information, such as whether its checked.
	 */		
	public SWTTreeItem getItem(Subitem node) {
	 	return new SWTTreeItem((TestObject)this.getSubitem(node));
	}	

	/**
	 * Identifies how many nodes in the tree are selected <p>
	 * @return the number of children/items the selected node contains
	 */		
	public int getSelectedNodeItemCount() {	
		int itemCount = 0;
		int count = ((Integer)getProperty("selectionCount")).intValue();
		if (count == 1) {
	 		SWTTreeItem selectedNode = getFirstSelectedNode();
			itemCount = selectedNode.getItemCount();
			selectedNode.unregister();
		} 
		else 
			throw new IllegalArgumentException("No node or more than one node is selected.");			
		return itemCount;
	}
	
	/**
	 * Gets the text of the node that is currently selected within the tree.  This method assumes only one node was selected <p>
	 * @return the text of the selected node
	 */		
	public String getSelectedNodeText() {
		String text = null;
		int count = ((Integer)getProperty("selectionCount")).intValue();
		if (count == 1) {
	 		SWTTreeItem selectedNode = getFirstSelectedNode();
			text = selectedNode.getText();
			selectedNode.unregister();
		} 
		else 
			throw new IllegalArgumentException("No node or more than one node is selected.");			
		return text;		
	}
	
	/**
	 * Gets the text of the control that currently has focus in the tree
	 * @return the text property value
	 * 			of the control that has current focus.
	 */		
	public String getTextOfFocusControl() {
		// more efficient ways of obtaining the control that has current focus:
		//    Display.getCurrent().getFocusControl ... but, getCurrent() results in a null at runtime
		//    this.getDisplay().getFocusControl() ... but, getFocusControl() results in an SWTException (invalid thread access)
		
        TestObject[] children = (TestObject[])invoke("getChildren");
        String text = null;
        //boolean notFound = true;
        
        if (children!= null) {
//	        for (int i=0; i<children.length && notFound; i++) {
	        for (int i=0; i<children.length; i++) {
	        	boolean hasFocus = ((Boolean)(children[i].getProperty("focusControl"))).booleanValue();   
	       		boolean isVisible = ((Boolean)(children[i].getProperty("visible"))).booleanValue();        	
	        	if (hasFocus && isVisible) {
	    			text = (String)children[i].getProperty("text");
//	    			notFound = false;
	        	}     				
	        	children[i].unregister();
	        } 
        } 
        return text;	
	}  

	/**
	 * Returns the hierarchy of the whole tree.<p>
	 * @return the hiearchy of the whole tree
	 */			
	public ITestDataTree getTreeHierarchy () {       
		// get the hierarchy of the whole tree	
        ITestDataTree cdTree = (ITestDataTree)this.getTestData("tree");
        return cdTree;		
	}

	/**
	 * Selects the specified node, expands all its children nodes except for those that are to be ignored (nodesToIgnore parm),
	 * selects all the expanded children nodes and then captures the selected hierarchy of the specified
	 * tree node so a task or testcase can validate it.<p>
	 * 
	 * @param node		identifies how to access the node within the tree.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 * @param nodesToIgnore	the nodes that should not be expanded
	 * @return the hierarchy of the specified node
	 */			
	public ITestDataTree getTreeNodeHierarchy (Subitem node,
	                                           String[] nodesToIgnore) {
	                                           	
	    IPackageLogger oldLogger = PackageLoggingController.getLogger();
	    GenericLogger genericLogger = new GenericLogger();
	    genericLogger.setLogLevel(IPackageLoggerConstants.PACKAGELOGLEVEL_NONE); 
		PackageLoggingController.setLogger(genericLogger);
		                                     	
		// select the specified node
		selectTreeNode(node);
		
		// iterate recursively through the children node, expanding
		// each for a maximum of 7 levels deep		
		int descendantCount = expandSelectedTreeNodeHierarchy(0, nodesToIgnore);
			
		// select all the nodes in the tree node hiearchy
		selectTreeNode(node);		
        selectVisibleTreeNodeHierarchy(descendantCount);
        
		// get the selected hierarchy	
        ITestDataTree cdTree = (ITestDataTree)this.getTestData("selected");
        
        PackageLoggingController.setLogger(oldLogger);
        
        return cdTree;		
	}

	/**
	 * Identifies whether the specified text equals the text of a root node in the tree.
	 * For example, if a project exists in the Project Navigator tree <p>
	 * @param nodes		the array of tree nodes to be searched.
	 * @param value		the node text value to be used for the search
	 * @return true if the specified text equals the text of a root node in the tree.
	 * Otherwise, returns false.
	 */
	public boolean isRootNode(String nodeText) {
      // Declare variables for tree
      ITestDataTree cdTree;
      ITestDataTreeNodes cdTreeNodes;
      ITestDataTreeNode[] cdTreeNode;
      
      //Variables to hold tree data
      cdTree = getTreeHierarchy();
      cdTreeNodes = cdTree.getTreeNodes();
      cdTreeNode = cdTreeNodes.getRootNodes();
      
      if (nodesContain(cdTreeNode, nodeText) != -1) {
          return true;
      }
      return false;	
	}	
	
	/**
	 * Identifies whether the specified text EQUALS the text of a node within the specified hierarchy.
	 * For example, if a particular file exists in the Project Navigator tree for a given project <p>
	 * @param searchHierarchy		the hierarchy to be searched.
	 * getTreeNodeHierarchy() can be called to determine the hierarchy of a given node or
	 * getTreeHierarchy() can be called to get the hierarchy of the whole tree.
	 * @param nodeText		the node text value to be used for the search
	 * @return true if the specified text equals the text of a node in the specified node's hiearchy.
	 * Otherwise, returns false.
	 * @see isSubNode(ITestDataTree, String, boolean), isSubNode(String, String), and isSubNode(String, String, boolean)
	 */
	public boolean isSubNode(ITestDataTree searchHierarchy, String nodeText) {
		return isSubNode(searchHierarchy, nodeText, false);     
	}
	
	/**
	 * Searches the specified hierarchy to determine if the text of any nodes matches
	 * or contains the specified text.  This method should be called if you plan
	 * to search for several nodes within the hierarchy. <p>
	 * @param searchHierarchy		the hierarchy to be searched.
	 * getTreeNodeHierarchy() can be called to determine the hierarchy of a given node or
	 * getTreeHierarchy() can be called to get the hierarchy of the whole tree.
	 * @param nodeText				the node text value to be searched for
	 * @param performFuzzySearch	indicates if should test to see if nodeText is just contained
	 * within the text of a node (true) or if should test to see if nodeText exactly matches the text of a node (false).
	 * @return true if the specified text equals the text of a node in the specified node's hiearchy.
	 * Otherwise, returns false.
	 */
	public boolean isSubNode(ITestDataTree searchHierarchy, String nodeText, boolean performFuzzySearch) {
	  ITestDataTree cdTree = searchHierarchy;
	  ITestDataTreeNodes cdTreeNodes = cdTree.getTreeNodes();
      ITestDataTreeNode[] cdTreeNode = cdTreeNodes.getRootNodes();  
      boolean found = false;
 
      //Iterate through tree branches until find a node's text matches nodeText; this is a recursive method.
      for (int i = 0;i<cdTreeNode.length && !found; ++i) {
          found = equalsNode(cdTreeNode[i], nodeText, performFuzzySearch);     	  
      } 
      
      return found;       
	}

	/**
	 * Searches the tree hierarchy for the specified root node and if found, searches through its children nodes
	 * to determine if the text of any of them exactly matches the specified nodeText.  Only those child nodes that
	 * are expanded (are visible) are searched.  So, it is recommended that you ensure the root node has been selected
	 * and its hierarchy expanded (by calling selectTreeNode() and expandSelectedTreeNodeHierarchy()) prior to 
	 * calling this method.<p>
	 * @param rootNodeText		the node text value of the root node
	 * @param nodeText			the node text value to be searched for (as a child of the root node)
	 * @return true if the specified text equals the text of a node in the specified root node's hieararchy.
	 * Otherwise, returns false.  
	 */
	public boolean isSubNode(String rootNodeText, String nodeText) {
	  return isSubNode(rootNodeText, nodeText, false);  
	}		
	
	/**
	 * Searches the tree hierarchy for the specified root node and if found, searches through its children nodes
	 * to determine if the text of any of them matches the specified nodeText.  Only those child nodes that
	 * are expanded (visible) are searched.  So, it is recommended that you ensure the root node has been selected
	 * and its hierarchy expanded (by calling selectTreeNode() and expandSelectedTreeNodeHierarchy() prior to 
	 * calling this method.<p>
	 * @param rootNodeText			the node text value of the root node
	 * @param nodeText				the node text value to be searched for (as a child of the root node)
	 * @param performFuzzySearch	indicates if should test to see if rootNodeText and nodeText are just contained
	 * within the text of a node (true) or if should test to see if they exactly match the text of a node (false).
	 * @return true if the specified nodeText equals the text of a node in the hierarchy of the specified root node.
	 * Otherwise, returns false.  
	 */
	public boolean isSubNode(String rootNodeText, String nodeText, boolean performFuzzySearch) {
      // Declare variables for tree
      ITestDataTree cdTree;
      ITestDataTreeNodes cdTreeNodes;
      ITestDataTreeNode[] cdTreeNode;  
      
      //Variables to hold tree data
      cdTree = getTreeHierarchy();
      cdTreeNodes = cdTree.getTreeNodes();
      cdTreeNode = cdTreeNodes.getRootNodes();
      
      // Find the specified root node
      for (int i=0; i<cdTreeNode.length; i++) {
      		String rootText = cdTreeNode[i].getNode().toString();
      	    if (!performFuzzySearch) {
      	    	if (rootText.equals(rootNodeText)) {
      	    		// search its children to see if any match nodeText
			   		return equalsNode(cdTreeNode[i], nodeText, performFuzzySearch);
      	    	}
      	    }
      	    else {
      	    	if (rootText.indexOf(rootNodeText) >= 0) {
        	    	// search its children to see if any match nodeText
			   		return equalsNode(cdTreeNode[i], nodeText, performFuzzySearch);  
      	    	}  	    		
      	    }
      }
	  return false;      
	}					
				
	/**
	 * Identifies whether or not a checkbox that appears for the selected node is checked.  This method assumes only one node was selected <p> <p>
	 * @return whether or not the selected node is checked or not.
	 */		
	public boolean isSelectedNodeChecked() {	
		boolean isChecked = false;
		
		int count = ((Integer)getProperty("selectionCount")).intValue();
		if (count == 1) {
	 		SWTTreeItem selectedNode = getFirstSelectedNode();
			isChecked = selectedNode.isChecked();
			selectedNode.unregister();
		} 
		else 
			throw new IllegalArgumentException("No node or more than one node is selected.");
			
		return isChecked;
	}	

	/**
	 * Identifies whether or not the selected node in the tree is expanded.  This method assumes only one node was selected <p>
	 * @return whether or not the selected node is expanded or not.
	 */		
	public boolean isSelectedNodeExpanded() {	
		boolean isExpanded = false;
		
		int count = ((Integer)getProperty("selectionCount")).intValue();
		if (count == 1) {
	 		SWTTreeItem selectedNode = getFirstSelectedNode();
			isExpanded = selectedNode.isExpanded();
			selectedNode.unregister();
		} 
		else 
			throw new IllegalArgumentException("No node or more than one node is selected.");
			
		return isExpanded;
	}        

	/**
	 * Identifies whether a set of nodes contain a specific node text value <p>
	 * @param ITestDataTreeNode[] nodes - the array of tree nodes to be searched.
	 * @param String value - the node text value to be used for the search
	 * @return  the index of the value found within nodes; -1 if 'nodes' does not contain 'value'. 
	 */
	public int nodesContain(ITestDataTreeNode[] nodes, String value) {
		for (int i=0; i<nodes.length; i++)
			if (nodes[i].getNode().toString().equals(value)) return i;		
		return -1;
	}
		
	/**
	 * This method is used to determine what types are available for the tree data <p>
	 */	
	public void printAvailableDataTypes() {
        System.out.println ("Available Tree Data Types: " + getTestDataTypes()); 
	}	

	/**
	 * This method is based on a section in the XDE Tester User Guide. It outputs information about the tree and each
	 * of its nodes to the Console <p>
	 */	
  	// learned that the TestDataTree only iterates through nodes that are expanded
	public void printTree() {    
      // Declare variables for tree
      ITestDataTree cdTree;
      ITestDataTreeNodes cdTreeNodes;
      ITestDataTreeNode[] cdTreeNode;
      
      //Variables to hold tree data
      cdTree = (ITestDataTree)getTestData("tree");
      cdTreeNodes = cdTree.getTreeNodes();
      cdTreeNode = cdTreeNodes.getRootNodes();
      
      //Print out total number of nodes
      System.out.println ("Tree Total Node Count: " + cdTreeNodes.getNodeCount());
      System.out.println ("Tree Root Node Count : " + cdTreeNodes.getRootNodeCount());
      System.out.println("Length of cdTreeNode: " + cdTreeNode.length);
      
      //Iterate through tree branches; this is a recursive method.
      for (int i = 0;i<cdTreeNode.length;++i)
          showTree(cdTreeNode[i], 0);
	} 

	/**
	 * This method supports selecting the specified tree node <p>
	 * @param node		identifies how to access the node within the tree.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 */	
	public void selectTreeNode(Subitem node) {
		this.click(node);
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, 
				"Clicked node \"" + node.toString() + "\" in " + getWidgetType() + " \"" + getName() + "\"");			
	}
	
	/**
	 * This method was created to allow values to be set in the second column
	 * of text that appears for the various nodes of a tree.
	 * Specifically, it was created to support setting values for
	 * element leaf nodes and attributes for the tree
	 * that's contained in the Design page of the XML editor.<p>
	 * @param node		identifies how to access the node within the tree.
	 * You can use the Record feature of XDE Tester to determine how a node
	 * can be represented as a Subitem.  For example, the following coud pass
	 * as Subitem instances: atPath("po:purchaseOrder->items->item->partNum")
	 * or atList(atText("po:purchaseOrder"),atText("items"),atText("item", 1)).
	 * NOTE: atList is used when the nodes in a tree are not unique.
	 * @param nodeValue	the value to be set for the specified tree node
	 */
	public void setTreeNodeValue(Subitem node, 
							      String nodeValue) {	
        selectTreeNode(node);
		RationalTestScript.getScreen().inputKeys("{ENTER}"+nodeValue+"{ENTER}");
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, 
			"Set the value of \"" + node.toString() + "\" in " + getWidgetType() + " \"" + getName() + "\" to " + nodeValue + ".");	 		
	}	
	
	/**
	 * For tree nodes with checkboxes, this method toggles the checkbox for
	 * the specified node <p>
	 * 
	 * @param entry	the text of the table entry as it appears in the GUI
	 */
	public void toggleTreeEntryCheckbox(Subitem node) {
		if (node instanceof List) {
			List toggleNode = (List)node;
			// the following result in a SubitemNotFound exception 
			// toggleNode.append(new Text("Location(CHECKBOX)"));	
			
			// workaround was to do the following		
			String path = toggleNode.getPath();
			String fullPath = path + ("->Location(CHECKBOX)");
			toggleNode = new List(fullPath);
			
			this.click(toggleNode);
			PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, 
				"Toggled the checkbox for \"" + path + "\" in " + getWidgetType() + " \"" + getName() + "\".");	 				
		}
		else
			throw new IllegalArgumentException("This method only supports a Subitem that is a List");
			
	}
	
	/**
	 * Returns tree root names
	 * 
	 * @return the root names as a string array
	 * @author Diane Weir
	 */
	public String[] docGetRootNames ()
	{
		ITestDataTree dtre 		= this.getTreeHierarchy();
		ITestDataTreeNodes dnod 	= dtre.getTreeNodes();
		ITestDataTreeNode dnods[] 	= dnod.getRootNodes();
		String LibNames[] 		= new String[dnods.length];
		Object o;
		
		for (int i = 0; i<dnods.length; i++)
		{
			o = dnods[i].getNode();
			LibNames[i] = o.toString();
		}
		return LibNames;
	}
				

//***********************Helper Methods*******************************************************************************
	/**
	 * This helper method collapses the node that is currently selected <p>
	 */	
	protected void collapseSelectedNode() {
		// collapse the node only if it's currently expanded         
		boolean isExpanded = this.isSelectedNodeExpanded();
		if (isExpanded) {                 
	        RationalTestScript.getScreen().inputKeys("{ExtLeft}");
			PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, 
				"Collapsed \"" + getSelectedNodeText() + "\" in " + getWidgetType() + " \"" + getName() + "\"");		        
		}
	}

	/**
	 * Recursive method to determine if the specified text exists as a node in the tree <p>
	 * @param node		a node in the tree
	 * @param text		the text that is to be compared against each node in the specified node's tree hierarchy
	 * @return true if the specified text equals the text of a node in the specified node's tree hierarchy.
	 * Otherwise, returns false.
	 */	
	protected boolean equalsNode(ITestDataTreeNode node, String text) {
		return equalsNode(node, text, true);
    }

	/**
	 * Recursive method to determine if the specified text exists as a node in the tree <p>
	 * @param node					a node in the tree
	 * @param text					the text that is to be compared against each node in the specified node's tree hierarchy
	 * @param performFuzzySearch	indicates if should test to see if nodeText is just contained
	 * within the text of a node (true) or if should test to see if nodeText exactly matches the text of a node (false).
	 * @return returns true if the specified text equals the text of a node in the specified node's tree hierarchy.
	 * Otherwise, returns false.
	 */	
	protected boolean equalsNode(ITestDataTreeNode node, String text, boolean performFuzzySearch) {
		boolean isEqual = false;
		String nodeText = node.getNode().toString();
		if (!performFuzzySearch) {				
			if (nodeText.equals(text)) {
				return true;
			}
		}
		else {
			if (nodeText.indexOf(text) >= 0) {
				return true;
			}
		}
		
	    //Determine if node has children; recursively call this same
	    //method to determine if nodeText equals any child nodes.
	    ITestDataTreeNode[] children = node.getChildren();
	    int childCount = ( children != null ? children.length : 0 );
	    for (int i = 0; i < childCount && !isEqual ; ++i ) {         
	    	isEqual = equalsNode(children[i], text, performFuzzySearch);
	    }	
	    		
		return isEqual;
    }

	/**
	 * This helper method expands the node that is currently selected <p>
	 */		
	protected void expandSelectedTreeNode() {
		// expand the node only if it's currently collapsed                
		boolean isExpanded = this.isSelectedNodeExpanded();
		if (!isExpanded) {        
	        RationalTestScript.getScreen().inputKeys("{ExtRight}");
			PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, 
				"Expanded \"" + getSelectedNodeText() + "\" in " + getWidgetType() + " \"" + getName() + "\"");	        
		}
	}

	/**
	 * This helper method expands all the nodes in the selected tree node heirarchy for a maximum depth level of 7 <p>
	 * @param level			the depth level to be expanded
	 * @param nodesToIgnore	the nodes that should not be expanded
	 * @return the number of descendant nodes that have been processed.
	 */	
	protected int expandSelectedTreeNodeHierarchy(int level, 
												String[] nodesToIgnore) {
		int descendantCount = 0;
		// iterate recursively through the children node(s), expanding
		// each for a maximum of 7 levels deep
		expandSelectedTreeNode();
		int count = this.getSelectedNodeItemCount();
		descendantCount = count;
	 	for (int i=0; (i < count) && (level < 7); i++) {
			selectNextTreeNode();
			boolean ignore = false;
			if (nodesToIgnore != null) {
				String text = this.getSelectedNodeText();
//				boolean ignore = false;
				for (int j=0; j < nodesToIgnore.length && !ignore; j++) {
					if (text.indexOf(nodesToIgnore[j]) >= 0) {
						ignore = true;
					}
				}
			}
			if (!ignore) {
				descendantCount += expandSelectedTreeNodeHierarchy(level+1, nodesToIgnore);					
			}
	 	}	
	 	return descendantCount;			
	}	


	/**
	 * This helper method supports selecting the next tree node as part of a multiple selection <p>
	 */	
	protected void multipleSelectNextTreeNode() {     		
		  RationalTestScript.getScreen().inputKeys("+{ExtDown}");	  
	}	

	
	/**
	 * This helper method supports selecting the next tree node as part of a single selection <p>
	 */			
	protected void selectNextTreeNode() {     		
		  RationalTestScript.getScreen().inputKeys("{ExtDown}");
	}		


	/**
	 * This helper method supports selecting all the nodes in the visible tree hierarchy <p>
	 * @param descendantCount		the number of descendants in the tree hieararchy that are to be selected.
	 */		
	protected void selectVisibleTreeNodeHierarchy(int descendantCount) {
		// iterate recursively through the descendant node(s), selecting each one
		// NOTE: this only works for a depth of up to 7
	 	for (int i=0; i < descendantCount; i++) {
			multipleSelectNextTreeNode();
	 	}					
	}	
	
	
	/**
	 * Recursive method to print out tree nodes with proper indenting.
	 * This method is based on a section in the XDE Tester User Guide. <p>
	 */	
	protected void showTree(ITestDataTreeNode node, int indent) {
      //Determine number of tabs to use - to properly indent tree
      int tabCount = ( indent < tabs.length() ? indent : tabs.length() );
      
      //Print out node name + number of children
      System.out.println(tabs.substring(0, tabCount) + node.getNode() + " (" + node.getChildCount() + " children)" );

      //Determine if node has children; recursively call this same
      //method to print out child nodes.
      ITestDataTreeNode[] children = node.getChildren();
            int childCount = ( children != null ? children.length : 0 );
            for ( int i = 0; i < childCount; ++i ) {         
                  showTree(children[i], indent+1);
            }
    }
    
    //String of tabs used to indent tree view
    final String tabs = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";		

	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "tree";
	}		
	
}
