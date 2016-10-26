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
import ibm.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

/**
 * Description : Class for manipulating a GEF Palette<p>
 *
 * 
 * @author KEndres
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class GEFPalette extends Widget {

	/********************************************
	 * Fields
	 * ******************************************/		
	private TestObject viewer;

//*******************************************Constructors**********************************************
	/**
	 * Constructor for GEFPaletteTestObject <p>
	 * @param to				the ScrollTestObject that references the figure canvas that contains the palette.
	 * @param paletteViewer	the TestObject that references the palette viewer of the GEF editor.
	 */
	public GEFPalette(ScrollTestObject to, TestObject paletteViewer) {
		super(to);
		viewer = paletteViewer;	
	}

//***********************Instance Methods*******************************************************************************
	/**
	* Finds a specified entry in a palette drawer, if it exists.<p>
	* @param drawerLabel	the label of the drawer that contains the entry to be found
	* @param entryLabel	the label of the entry that is to be found
	* @return the specified entry if present, null otherwise 
	* */
	public GEFPaletteEditPart findDrawerEntry(String drawerLabel, String entryLabel) {
		GEFPaletteEditPart drawerEditPart = findDrawer(drawerLabel);	
		GEFPaletteEditPart entryEditPart = findEntry(drawerEditPart, entryLabel);
		drawerEditPart.unregister();	
		return entryEditPart;
	}

	/**
	* Selects the specified palette entry in the specified drawer, if both exist.
	* If neither one exists an exception is thrown.<p>
	* @param drawerLabel	the label of the drawer that contains the entry that is to be selected
	* @param entryLabel	the label of the palette entry that is to be selected
	* */
	public void selectDrawerEntry(String drawerLabel, String entryLabel) {	
		GEFPaletteEditPart rootPaletteEditPart = getRootPaletteEditPart();
		TestObject children = rootPaletteEditPart.getChildParts();
		rootPaletteEditPart.unregister();
		
		TestObject iterator = (TestObject)children.invoke("listIterator");
		boolean drawerNotFound = true;
		
		while (((Boolean)iterator.getProperty("next")).booleanValue() && drawerNotFound) {
			 TestObject child = (TestObject)iterator.invoke("next");
			 
			 if (child.getObjectClassName().equals("org.eclipse.gef.internal.ui.palette.editparts.DrawerEditPart")) {
			 	 GEFPaletteEditPart drawerEditPart = new GEFPaletteEditPart(child);
				 
				 if (drawerEditPart.getLabel().equals(drawerLabel)) {
				 	drawerNotFound = false;
				 			 	
				 	// if the drawer isn't expanded, click on it to expand it (expose its entries)
				    // NOTE: Pratik opened a Bugzilla defect (Bug 48021) to request that the reveal() method automatically
				    //       open a drawer if its not expanded.  When this happens this method can be simplified to the following:
				    //       GEFPaletteEditPart entryEditPart = findDrawerEntry(drawerLabel, entryLabel);
				    //       selectEntry(entryEditpart);
				    //       entryEditPart.unregister();
				    // 
					if (!drawerEditPart.isExpanded()) {
						selectEntry(drawerEditPart);
					}

					GEFPaletteEditPart entryEditPart = findEntry(drawerEditPart, entryLabel);	
					if (entryEditPart != null) {															 	
						// call reveal() to autoscroll to the palette entry
						// This failed when tried it saying:
						//     com.rational.test.ft.InvalidSignatureException: class not found: org.eclipse.gef.EditPart
						// Sent a note to Rational contacts
//		 				viewer.invoke("reveal", "(Lorg/eclipse/gef/EditPart;)V", new Object[] {entryEditPart});	

						// select the entry	within the palette	 							
		 				selectEntry(entryEditPart);
		 				entryEditPart.unregister();
					}
					else {
						PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "GEFPalette::selectDrawerEntry: " + entryLabel + " not found in palette drawer, " + drawerEditPart.getLabel() + ". No selection could be made.");						
//						System.out.println(entryLabel + " not found in palette for drawer, "+drawerEditPart.getLabel());
					}	 											
				 }
				 else {
				 	// if the drawer is expanded, click on it to collapse it
				 	// This can be removed when can get past the InvalidSignatureException on the reveal() call.
				 	if (drawerEditPart.isExpanded())
				 		selectEntry(drawerEditPart);
				 }
			 }			 
			 child.unregister();
		}
		if (drawerNotFound)
			PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "GEFPalette::selectDrawerEntry: " + drawerLabel + " not found in palette. No selection could be made.");								
//			System.out.println(drawerLabel + " not found in palette");
		iterator.unregister();
		children.unregister();		
	}
	
	/**
	* Selects the specified group (non-drawer) entry in the palette, if it exists.
	* If it doesn't exist, an exception is thrown.<p>
	* @param entryLabel	the label of the group entry that is to be selected
	* */
	public void selectGroupEntry(String entryLabel) {
		GEFPaletteEditPart groupEditPart = findGroup();
		if (groupEditPart != null) {
			GEFPaletteEditPart entryEditPart = findEntry(groupEditPart, entryLabel);	
			if (entryEditPart != null) {															 	
				// select the entry	within the palette	 							
	 			selectEntry(entryEditPart);
	 			entryEditPart.unregister();
			}
			else {
				PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "GEFPalette::selectGroupEntry: " + entryLabel + " not found in palette. No selection could be made.");				
//				System.out.println(entryLabel + " not found in palette");
			}
			groupEditPart.unregister();
		} 											
		else {
			PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "GEFPalette::selectGroupEntry: " + entryLabel + " not found in palette. No selection could be made.");			
//			System.out.println(entryLabel + " not found in palette");
		}				
	}	

//***********************Helper Methods*******************************************************************************
	/**
	* Finds a specified drawer in a palette, if it exists.<p>
	* @param drawerLabel	the label of the drawer that is to be found
	* @return the specified drawer if present, null otherwise 
	* */
	private GEFPaletteEditPart findDrawer(String drawerLabel) {
		GEFPaletteEditPart editPart = null;
		GEFPaletteEditPart rootPaletteEditPart = getRootPaletteEditPart();
		TestObject children = rootPaletteEditPart.getChildParts();
		rootPaletteEditPart.unregister();
		
		TestObject iterator = (TestObject)children.invoke("listIterator");
		boolean drawerNotFound = true;
		
		while (((Boolean)iterator.getProperty("next")).booleanValue() && drawerNotFound) {
			 TestObject child = (TestObject)iterator.invoke("next");
			 
			 if (child.getObjectClassName().equals("org.eclipse.gef.internal.ui.palette.editparts.DrawerEditPart")) {
			 	 GEFPaletteEditPart drawerEditPart = new GEFPaletteEditPart(child);						 
				 if (drawerEditPart.getLabel().equals(drawerLabel)) {
				 	drawerNotFound = false;
				 	editPart = drawerEditPart;						
				 }
				 else {
				 	child.unregister();
				 }
			 }
		}
		if (drawerNotFound)
			System.out.println(drawerLabel + " not found in palette");
		iterator.unregister();
		children.unregister();	
						
		return editPart;
	}	
	
	/**
	* Finds a specified entry in a palette drawer or group, if it exists.<p>
	* @param entryLabel			the label of the entry that is to be found
	* @param containingEditPart	the edit part of the palette drawer or group
	* @return the specified entry if present, null otherwise 
	* */
	private GEFPaletteEditPart findEntry(GEFPaletteEditPart containingEditPart, String entryLabel) {
		GEFPaletteEditPart entryEditPart = null;
		
		// some drawers may contain a GroupEditPart so need to keep iterating through
		// children until obtain the leaf entries
		TestObject leafEntries = getLeafEntries(containingEditPart);
		TestObject entriesIterator = (TestObject)leafEntries.invoke("listIterator");
		boolean entryNotFound = true;
		while (((Boolean)entriesIterator.getProperty("next")).booleanValue() && entryNotFound) {
			GEFPaletteEditPart entry = new GEFPaletteEditPart((TestObject)entriesIterator.invoke("next"));
			if (entry.getLabel().equals(entryLabel)) {
				entryNotFound = false;	
				entryEditPart = entry;			
			}
			else {
				entry.unregister();
			}				
		}
		leafEntries.unregister();
		entriesIterator.unregister();
		
		return entryEditPart;
	}	

	/**
	* Finds a group contained in a palette, if it exists.<p>
	* @return the specified group if present, null otherwise 
	* */
	private GEFPaletteEditPart findGroup() {
		GEFPaletteEditPart groupEditPart = null;
		GEFPaletteEditPart rootPaletteEditPart = getRootPaletteEditPart();
		TestObject children = rootPaletteEditPart.getChildParts();
		rootPaletteEditPart.unregister();
		
		TestObject iterator = (TestObject)children.invoke("listIterator");
		boolean groupNotFound = true;
		
		while (((Boolean)iterator.getProperty("next")).booleanValue() && groupNotFound) {			
			TestObject child = (TestObject)iterator.invoke("next");
			 
			if (child.getObjectClassName().equals("org.eclipse.gef.internal.ui.palette.editparts.GroupEditPart")) {
			 	groupNotFound = false;
			 	groupEditPart = new GEFPaletteEditPart(child);
			}
			else
				child.unregister();
		} 											
		iterator.unregister();
		children.unregister();
						
		return groupEditPart;
	}	

	/**
	* Returns the root edit part of the palette viewer.<p>
	* @return the root palette edit part if present, null otherwise 
	* */	
	private GEFPaletteEditPart getRootEditPart() {
		GEFPaletteEditPart root = null;
		TestObject to = (TestObject)viewer.invoke("getRootEditPart");
		if (to != null) {
			root = new GEFPaletteEditPart(to);
		}		
		return root;
	}

	/**
	* Finds the root palette edit part, which should be a SliderPaletteEditPart.<p>
	* @return the root palette edit part
	* */	
	private GEFPaletteEditPart getRootPaletteEditPart() {
		GEFPaletteEditPart root = getRootEditPart();
		GEFPaletteEditPart rootPaletteEditPart = getRootPaletteEditPart(root);
		root.unregister();
		return rootPaletteEditPart;
	}

	/**
	* Finds the root palette edit part, which should be a SliderPaletteEditPart.<p>
	* @param parent	the root edit part of the palette viewer should be initially passed
	* @see getRootEditPart and getRootEditPart
	* @return  the root palette edit part
	* */	
	private GEFPaletteEditPart getRootPaletteEditPart(GEFPaletteEditPart parent) {
		GEFPaletteEditPart part = null;
		GEFPaletteEditPart firstChild = parent.getChildPart(0);
		
		if (firstChild.getObjectClassName().equals("org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart"))
			part = firstChild;
		else {
			part = getRootPaletteEditPart(firstChild);
			firstChild.unregister();
		}
	
		return part;
	}

	/**
	* Returns the leaf children of the specified edit part.
	* If no children exist, an empty list is returned.<p>
	* @param parent	the edit part for which children are to be returned
    * @return the children in a java.util.List
	* */	
	private TestObject getLeafEntries(GEFPaletteEditPart parent) {
		TestObject entriesList = null;
		
		GEFPaletteEditPart firstChild = parent.getChildPart(0);	
		
		TestObject grandChildren = firstChild.getChildParts();
		if (((Boolean)grandChildren.getProperty("empty")).booleanValue()) {
			firstChild.unregister();
			grandChildren.unregister();			
			entriesList = parent.getChildParts();
		}
		else {
			grandChildren.unregister();
			entriesList = getLeafEntries(firstChild);
			firstChild.unregister();
		}
		
		return entriesList;
	}

	/**
	* Selects the specified entry.<p>
	* @param editPart	the edit part of the entry that is to be selected
	* */	
	private void selectEntry(GEFPaletteEditPart editPart) {
		this.click(editPart.getLocation());
		PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Clicked on \"" + editPart.getLabel() + "\" in " + getWidgetType() + " \"" + getName() + "\"");									
	}			

	/**
	* Verifies a drawer contains the specified entries.
	* Any missing or extra entries are flagged as an error in the XDE Tester log.<p>
	* @param drawerLabel	the label of the drawer for which entries are to be verified
	* @param entryLabels	the label of the entries that are expected to be contained in the drawer
	* */	
//	public void validateDrawerEntries(String drawerLabel, String[] entryLabels) {
//		//navigate through the list of drawers
//		//as find an entryLabel, remove it from the list
//		//what labels remain in the list were not found
//		//what's not in the list, print out as being extra
//	}
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "palette";
	}		
}