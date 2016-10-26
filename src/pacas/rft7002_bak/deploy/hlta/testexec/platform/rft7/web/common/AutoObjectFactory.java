/*
 * Created on Jan 5, 2006
 *
 */
package hlta.testexec.platform.rft7.web.common;

import hlta.testexec.platform.rft7.sap.Sap;
import hlta.testexec.platform.rft7.web.mvec.MTable;
import hlta.testexec.platform.rft7.web.mvec.MVec;
import hlta.testexec.platform.rft7.web.mvec.UIObj;
import ibm.util.BrowserOps;
import ibm.widgets.ObjectFactory;
import ibm.widgets.WBrowser;
import ibm.widgets.WButton;
import ibm.widgets.WCheckBox;
import ibm.widgets.WComboBox;
import ibm.widgets.WFrame;
import ibm.widgets.WImage;
import ibm.widgets.WLink;
import ibm.widgets.WListBox;
import ibm.widgets.WRadioButton;
import ibm.widgets.WStaticText;
import ibm.widgets.WTable;
import ibm.widgets.WTextField;


import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import com.rational.test.ft.ObjectIsDisposedException;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.DomainTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ITopWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Anchor;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.Index;
import com.rational.test.ft.script.Property;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Row;
import com.rational.test.util.regex.Regex;
import com.rational.test.ft.object.interfaces.IScreen;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.script.IOptionName;

/*
 * Jeffrey's notes
 * 
 * Note on dynamic object finding:: There are 4 ways for finding an object,
 * which are exclusive to each other. Two switches, "ignoreCase" and
 * "fuzzyMatch" take effect on the following attributes marked up with asteris
 * (*). other attribute values 
 * 1. Find by title only 
 * type 
 * classID 
 * by * - The string text. 
 * 2. Find by a certain property 
 * value * 
 * type 
 * propertyName 
 * classID
 * 3. Find by a certain property and another reference object 
 * value * 
 * type
 * propertyName 
 * classID 
 * ref - The name of another GUI object defined in the object mapping 
 * 4. Find by a certain property and the relative position index
 * value * 
 * type 
 * propertyName 
 * classID 
 * pos - The string that represents the relative position of the object 
 * 		to locate.
 * 
 * How do we find an object down along an object tree? The answer is to split
 * the finding procedure into several continuous steps. The result of the
 * previous createGuiObject will be the parameter "context" to the next
 * createGuiObject call.
 * 
 * However, the matching for object names in the mappings must be exact match!
 * 
 */

/**
 * @author luoling
 * @author Jeffrey: Rewrite the whole class, keeping only following methods of
 *         the original class. createObject Element ExactElement
 */
public class AutoObjectFactory {
	private static AutoObjectFactory me = null;

	private static String prevMappingfile = "";

	/**Global string for Html string*/
	protected static final String gsHtml = "Html";

	/**
	 * 
	 * Judge if the specified BrowserTestObject is ready.
	 * 
	 * @author jeffreybian
	 * @param timeout
	 *            Timeout value in sec.
	 * @param interval
	 *            Interval, in sec.
	 * @return If the browser is ready with the DOM object, returns true;
	 *         otherwise false.
	 */
	public static boolean browserReady(BrowserTestObject bto, int timeout,
			int interval) {
		boolean result = false;
		int n = timeout / interval;
		int _interval = interval * 1000;
		Property[] props = { new Property(".class", "Html.HtmlDocument") };
		Property[] securityProps = { new Property(".class", "Html.Dialog") };
		Anchor anchor = new Anchor(true, props);
		Anchor securityAnchor = new Anchor(false, securityProps);
		// Find the document object
		if (bto != null) {
			TestObject obj = null;

			for (int i = 0; i < n; ++i) {
				// Check for Security Alert box
				//			    
				// try {
				// ITopWindow g = (ITopWindow)getHtmlDialog("ITopWindow");
				// if (g != null) {
				// g.inputKeys("Y");
				// Thread.sleep(100);
				// g.inputKeys("~");
				// Thread.sleep(100);
				// }
				// } catch (Exception e) {
				// // Do nothing, just continue
				// System.out.println("No security alert found.");
				// }
				try {
					Thread.sleep(_interval);
					obj = bto.find(anchor)[0];
					if (obj != null && obj.exists()) {
						// Wait another 1 secs.
						bto.maximize();
						Thread.sleep(1000);
						result = true;
						break;
					}
				} catch (InterruptedException e) {
					result = false;
				} catch (ArrayIndexOutOfBoundsException e) {
					result = false;
					continue;
				} catch (NullPointerException e) {
					result = false;
					continue;
				} catch (Exception e) {
					result = false;
					continue;
				}
			}
		} else {
			System.err.println("Browser object is null.");
			result = false;
		}
		if (result == false) {
			System.out.println("Browser object is not ready.");
		}
		return result;
	}

	/**
	 * Use this static method to generate singleton of this object.
	 * Note: The AutoObjectFactory must be created with at least a
	 * mapping file on initial.
	 * @author jeffreybian
	 * @param mappingPath
	 *            This can either be a file name or a series of file names, separated by "|". 
	 *            If given a list of names, the AutoObjectFactory searches for all the mapping 
	 *            files structure.
	 * @return
	 */
	public static AutoObjectFactory createInstance(String mappingfile, String path) {
		if (prevMappingfile.compareToIgnoreCase(mappingfile) == 0) {
			// No new object
		} else {
			prevMappingfile = mappingfile;
			me = new AutoObjectFactory(mappingfile,path);
		}

		return me;
	}

	/**
	 * 
	 * @author luoling
	 * @param obj
	 * @param item
	 * @return
	 */
	protected static Object createObject(GuiTestObject obj, ObjectItem item) {
		if (obj == null)
			return null;
		if (item.type.equalsIgnoreCase("browser"))
			return new WBrowser(obj);
		if (item.type.equalsIgnoreCase("button"))
			return new WButton(obj);
		if (item.type.equalsIgnoreCase("checkbox"))
			return new WCheckBox(obj);
		if (item.type.equalsIgnoreCase("select"))
			return new WComboBox(obj);
		if (item.type.equalsIgnoreCase("frame"))
			return new WFrame(obj);
		if (item.type.equalsIgnoreCase("image"))
			return new WImage(obj);
		if (item.type.equalsIgnoreCase("link"))
			return new WLink(obj);
		if (item.type.equalsIgnoreCase("listbox"))
			return new WListBox(obj);
		if (item.type.equalsIgnoreCase("radiobutton"))
			return new WRadioButton(obj);
		if (item.type.equalsIgnoreCase("statictext"))
			return new WStaticText(obj);
		if (item.type.equalsIgnoreCase("table"))
			return new WTable(obj);
		if (item.type.equalsIgnoreCase("textfield"))
			return new WTextField(obj);
		return obj;
	}

	/**
	 * @author luoling
	 * @param item
	 * @param parent
	 * @return
	 */
	protected static Object Element(ObjectItem item, TestObject parent) {
		GuiTestObject obj = null;
		String value = new String();
		value = (String) item.value;
		obj = (GuiTestObject) ObjectFactory.findTestObject(new Regex(value
				.trim()), item.propertyName, item.classID, item.classIndex,
				parent);
		return createObject(obj, item);
	}

	/**
	 * @author luoling
	 * @param item
	 * @param parent
	 * @return
	 */
	protected static Object ExactElement(ObjectItem item, TestObject parent) {
		GuiTestObject obj = null;
		String value = new String();
		value = (String) item.value;
		obj = (GuiTestObject) ObjectFactory.findTestObject(value.trim(),
				item.propertyName, item.classID, item.classIndex, parent);
		return createObject(obj, item);
	}

	/**
	 * @author jeffreybian
	 * @param title
	 *            The title by which to find the browser.
	 * @return The specified browser object.
	 */
	public static BrowserTestObject getBrowserByTitle(String title) {
		BrowserTestObject bto = null;
		if (title == null) {
			bto = BrowserOps.findBrowser();
		} else {
			if (title.length() == 0) {
				bto = BrowserOps.findBrowser();
			} else {
				/*ClassCastException
				 *bto = (BrowserTestObject) (BrowserOps.findParticularBrowser(
						title, ".title", ""));*/
				/*TestObject to = BrowserOps.findParticularBrowser(
						title, ".title", "");
				if(to == null){
					bto = null;
				}else
					bto = new BrowserTestObject(to);*/
				
				
				BrowserHelperMethods bhm = new BrowserHelperMethods();

				//Get all domains and search each domain (html windows) for the right browser
				DomainTestObject domains[] = bhm.rtsGetDomains();
				for (int i = 0; i < domains.length; ++i) {
					if (domains[i].getName().equals(gsHtml)) {
						//We found an Html domain.
						TestObject[] topObjects = domains[i].getTopObjects();
						if (topObjects != null) {
							try {
								for (int j = 0; j < topObjects.length; ++j) {
									bto = new BrowserTestObject(topObjects[j]);
									bto.activate();
									//found a browser Dialog window
									if (BrowserOps.getActiveBrowserCaption().matches(title)) {								
										//System.out.println("found match");
										return bto;
									}else{
										bto = null;
									}
								}
							} catch (Exception e) {
								System.out.println(
									"Exception in AutoObjectFactory::getBrowserByTitle(): "
										+ e);
							}
						}
					}
				}//for
			}
		}
		return bto;
	}

	/**
	 * 
	 * @return
	 */
	public static Object getHtmlDialog(String type) {
		DomainTestObject domains[] = RationalTestScript.getDomains();
		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals("Html")) {
				// We found an Html domain.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					try {
						for (int j = 0; j < topObjects.length; ++j) {

							if (topObjects[j] instanceof GuiTestObject) {
								if (topObjects[j].getProperty(".class")
										.toString().equalsIgnoreCase(
												"Html.Dialog")) {
									if (type == "ITopWindow") {
										return (ITopWindow) topObjects[j];
									} else {
										return (GuiTestObject) topObjects[j];
									}
								}
							}
						}
					} catch (Exception e) {
						System.out.println("Exception in findBrowser: " + e);
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

//	**************************Inner Class Start****************************************************************************
	/**	
	* Description: this class is for separating out RationalTestScript methods and putting them in a format so that the Browser Ops can be static			
	* 
	* @author TSnow
	*/
	protected static class BrowserHelperMethods extends RationalTestScript 
	{
		public boolean DEBUG = false;
		
		public BrowserHelperMethods()
		{
			//must set the script name in order for startBrowser to work
			String caller = getTopScriptName();
			if (DEBUG)
				System.out.println(caller);
			try 
			{
				setScriptName(caller);
			} 
			catch(Exception e) //in case the calling script doesn't have a matching script definition in the resources directory for some reason, just bail
			{ 
				//noop
			}
		}
			
		/**				
		* Waits the specified amount of time. (just a wrapper for the sleep method from RationalTestScript) <p>
		*/
		public void wait(int time) {
			super.sleep(time);
		}

		/**
		* Gets all the domains and returns them in an array<p>
		* Wraps the getDomains() method of RationalTestScript
		* @return array of DomainTestObjects including all the domains
		*/
		public DomainTestObject[] rtsGetDomains() {
			return super.getDomains();
		}

		/**				
		* Gets active window caption <p>
		* @return active window caption.
		*/
		public String getActiveWindowCaption() {
			//Get Active Window Caption
			IScreen wActiveWin = super.getScreen();
			IWindow win = wActiveWin.getActiveWindow();

			return win.getText();
		}

		/**				
		* starts Browser and sets to particular page <p>
		* @param sBrowserPage 	the page to set the new instance of the browser to
		*/
		public void rtsStartBrowser(String sBrowserPage) {
			super.startBrowser(sBrowserPage);
		}
	
		/**				
		* returns the Maximimum Wait For Existence time stored in the preferences of XDE <p>
		* @return maximum time that this version of XDE is set to wait for the existence of an object
		*/	
		public double getDefaultMaxWaitTime() {
			return Double.parseDouble(super.getOption(IOptionName.MAXIMUM_WAIT_FOR_EXISTENCE).toString());
		}
	}
//**************************Inner Class End****************************************************************************
	
	
	/**
	 * Get the singleton object.
	 * 
	 * @author jeffreybian
	 * @return The singleton object of AutoObjectFactory.
	 */
	public static AutoObjectFactory getInstance() {
		return me;
	}

	private boolean hiearchy = false;
	
	private boolean ignoreCase = true;

	private boolean fuzzyMatch = false;

	private boolean debug = false;

	protected String mapping_base = "";

	protected ObjectTreeData objectTreeData = null;

	protected ObjectItem item = null;

	/**
	 * 
	 * @param mappingPath
	 *            This can either be a file name or a path name. If multiple mapping files
	 *            under that directory and concat them into a large tree  structure.
	 */
	private AutoObjectFactory(String mappingfile, String path) {
		this.mapping_base = mappingfile;
		objectTreeData = new ObjectTreeData(this.mapping_base,path);
	}

	public Object createGuiObject (String name, TestObject context) {
		String[] objs= name.split(":");
		Object fin = null;
		if (hiearchy==true) {
			for (int i = 0;i< objs.length;++i) {
				fin = createGuiObjectInternal(objs[i],context);
				if (fin==null) {
					break;
				}
			}
		} else {
//			String rname = "";
//			if (objs.length>1) {
//				rname = objs[objs.length-1];
//			} else {
//				rname = name;
//			}
			fin = createGuiObjectInternal(name,context);			
		}
		return fin;
	}
	/**
	 * Added code to search for a contorl directly by its name defined in xml
	 * file. Added by Jeffrey Bian. Note: The index does not take effect for
	 * "finding by title" method.
	 * 
	 * @author jeffreybian
	 */
	protected Object createGuiObjectInternal(String name, TestObject context) {
		ObjectItem parent = null;
		GuiTestObject obj = null;
		boolean found = false;
		if (context == null) {
			// Find the root test object, the Browser
			return getBrowserByTitle(name);

		}
		if (objectTreeData != null) {
			// Find the item
			item = new ObjectItem();
			parent = new ObjectItem();
			found = objectTreeData.search(name, item, parent);
			if (found) {
				// See which one of the 4 types finding it will be
				// The matching priority from high to low is
				// "by", "ref", "pos"
				String by = item.getRefObj();
				String pos = item.getPosIndex();
				String pilot = item.getPilot();
				if (by.length() != 0) {
					// Find by title
					obj = (GuiTestObject) createGuiObjectByTitle(item
							.getValue(), context, by, item.getClassID(), 0);
				} else if (pilot.length() != 0) {
					// Find by piloting object (similar to origin)
					float[] diag = { 1, 1, 0.2f, 0.2f, 1.1f, 1.1f };
					GuiTestObject[] tos = findElementArrayByReg(item, context);
					if (tos != null) {
						// First, create the pilot object
						GuiTestObject origin = (GuiTestObject) createGuiObject(
								pilot, context);
						UIObj originObj = UIObj.newOriginObj(origin);
						MVec[] vs = new MVec[tos.length];
						for (int i = 0; i < tos.length; ++i) {
							UIObj uo = UIObj.newObj(tos[i]);
							vs[i] = MVec.makeMetricVect(uo.metricVector(),
									originObj.metricVector());

						}
						int x = MVec.findBestMVec(vs, diag);
						// x is the index!
						if (x != -1) {
							obj = tos[x];
						}
					}
				} else if (pos.length() != 0) {
					// Find the only one object
					GuiTestObject[] tos = findElementArrayByReg(item, context);
					obj = getAtPositionIndex(tos, pos);
				} else {
					// Find by its property directly
					obj = findElementByReg(item, context);
				}
				// Create the object!!
				if (obj != null) {
					obj.waitForExistence();
					return createObject(obj, item);
				}

			} else {
				System.err.println("Specified object " + name
						+ " not in object tree.");
			}
		} else {
			System.err.println("ObjectTreeData not found.");
		}
		if (obj == null) {
			// System.err.println("Specified object " + name + " in object tree,
			// but not found in context " + context.toString() + " for now.");
		}
		return obj;
	}

	/**
	 * This waitForObjectExistence method is only used to wait for object that
	 * are described in the object mapping file and has a related name.
	 * 
	 * @author jeffreybian
	 * @param obj
	 * @param timeout
	 * @param interval
	 * @return The object.
	 */
	public TestObject createGuiObjectAndWait(String name, TestObject context,
			int timeout, int interval) {
		boolean ret = false;
		int retries = timeout / interval;
		TestObject go = null;
		for (int i = 0; i < retries; i++) {
			try {
				Thread.sleep(interval * 1000);
				go = (TestObject) ((createGuiObject(name, context)));
				if (go != null && go.exists()) {
					return go;
				}
			} catch (Exception e) {
				continue;
			}

		}
		if (go == null) {
			System.err.println("The specified object " + name + " not found.");
		}
		return go;
	}

	/**
	 * @author jeffreybian
	 * @param titleRegex
	 * @param originClassId
	 * @param targetClassId
	 * @param nearest
	 *            Reserved for future use.
	 * @param bto
	 * @return
	 */
	public Object createGuiObjectByTitle(String titleRegex, TestObject context,
			String originClassId, String targetClassId, int nearest) {
		GuiTestObject obj = getControlByTitle(titleRegex, originClassId,
				targetClassId, context);
		// Map from class id to type
		item.type = getTypeFromClassId(targetClassId);
		return createObject(obj, item);

	}

	/**
	 * @author jeffreybian
	 * @param element
	 * @param context
	 * @return
	 */
	protected GuiTestObject[] findElementArrayByReg(ObjectItem element,
			Object context) {
		Vector<GuiTestObject> v = findElementArrayByReg(element.value,
				element.propertyName, element.classID, context);
		GuiTestObject[] a = new GuiTestObject[v.size()];
		return v.toArray(a);
	}

	/**
	 * Dynamic method for finding the element array.
	 * 
	 * @author jeffreybian
	 * @param reg
	 * @param propName
	 * @param className
	 * @return
	 */
	protected Vector<GuiTestObject> findElementArrayByReg(String reg,
			String propName, String className, Object context) {
		TestObject bto = null;
		TestObject[] tos = null;
		Vector<GuiTestObject> target = new Vector<GuiTestObject>();
		String rvalue = "";
		Property[] props = { new Property(".class", className) };
		Anchor anchor = new Anchor(false, props);
		try {
			bto = (TestObject) context;
			tos = bto.find(anchor);

		} catch (Exception e) {

		}
		if (tos != null) {
			Pattern pat;
			if (ignoreCase) {
				pat = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			} else {
				pat = Pattern.compile(reg);
			}
			for (int i = 0; i < tos.length; ++i) {
				// Compare each one of it
				try {
					rvalue = tos[i].getProperty(propName).toString().trim();
					// System.out.println("** "+tos[i].getProperty(".class"));
					if (pat.matcher(rvalue).matches()) {
						target.add(new GuiTestObject(tos[i]));
						if (debug) {
							System.out.println(tos[i].getProperty(".value"));
						}
					}
				} catch (Exception e) {

				}
			}
		}
		// Do not unregister, for maybe there are some objects already found
		// previously still in use!!!

		return target;
	}

	/**
	 * Dynamic method for finding the specified element.
	 * 
	 * @author jeffreybian
	 * @param element
	 * @return
	 */
	protected GuiTestObject findElementByReg(ObjectItem element,
			TestObject context) {
		return findElementByReg(element.value, element.propertyName,
				element.classID, context);
	}

	/**
	 * 
	 * Dynamic method for finding the specified element.
	 * 
	 * @author jeffreybian
	 * @param reg
	 * @param propName
	 * @param className
	 * @return
	 */
	protected GuiTestObject findElementByReg(String reg, String propName,
			String className, TestObject context) {
		TestObject bto = null;
		TestObject[] tos = null;
		GuiTestObject target = null;
		String rvalue = "";
		Property[] props = { new Property(".class", className) };
		Anchor anchor = new Anchor(false, props);
		try {
			bto = (TestObject) context;
			if (className.equalsIgnoreCase("Html.TABLE")) {
				return getTableOrigin(reg, context).guiTestObject();
			} else {
				tos = bto.find(anchor);
			}

		} catch (Exception e) {
			System.err.println("Error finding objects in specified context.");
		}
		if (debug) {
			System.out.print("Getting object item: name = " + item.getName()
					+ "\r" + "value = " + item.getValue() + "\r"
					+ "propertyName = " + item.getProperty() + "\r" + "by = "
					+ item.getRefObj());
		}
		if (tos != null) {
			Pattern pat;
			if (ignoreCase) {
				pat = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			} else {
				pat = Pattern.compile(reg);
			}

			for (int i = 0; i < tos.length; ++i) {
				// Compare each one of it
				try {
					rvalue = tos[i].getProperty(propName).toString().trim();
					if (pat.matcher(rvalue).matches()) {
						target = new GuiTestObject(tos[i]);
						break;
					}
				} catch (Exception e) {

				}
			}
		}
		// Do not unregister, for maybe there are some objects already found
		// previously still in use!!!

		return target;
	}

	/**
	 * @author jeffreybian
	 * @param tos
	 * @param index
	 * @return
	 */
	protected GuiTestObject getAtPositionIndex(GuiTestObject[] tos, String index) {
		// Enclosed class comparator
		class Comparer implements Comparator<GuiTestObject> {
			private int anchor = 0;

			public Comparer(int anchor) {
				this.anchor = anchor;
			}

			public int compare(GuiTestObject e1, GuiTestObject e2) {
				if (e1 == null || e2 == null) {
					return 0;
				}
				int result = 0;
				switch (anchor) {
				case 0:
					// Left
					result = (e1.getScreenPoint().x < e2.getScreenPoint().x) ? -1
							: 1;
					break;
				case 1:
					// Top
					result = (e1.getScreenPoint().y < e2.getScreenPoint().y) ? -1
							: 1;
					break;
				case 2:
					// Bottom
					result = (e1.getScreenPoint().y > e2.getScreenPoint().y) ? -1
							: 1;
					break;
				case 3:
					// Right
					result = (e1.getScreenPoint().x > e2.getScreenPoint().x) ? -1
							: 1;
					break;
				}
				return result;
			}
		}

		GuiTestObject obj = null;
		String x = index;
		int p = 0;
		String[] toks = x.split("\\+");
		if (toks.length > 1) {
			p = Integer.parseInt((String) toks[1]);
		}
		if (toks[0].matches("left.*")) {
			Arrays.sort(tos, new Comparer(0));
		} else if (toks[0].matches("top.*")) {
			Arrays.sort(tos, new Comparer(1));
		} else if (toks[0].matches("bottom.*")) {
			Arrays.sort(tos, new Comparer(2));
		} else if (toks[0].matches("right.*")) {
			Arrays.sort(tos, new Comparer(3));
		} else {

		}

		if (p == 0) {
			obj = new GuiTestObject(tos[0]);
		} else if (p >= 1 || p <= tos.length) {
			obj = new GuiTestObject(tos[p - 1]);
		} else {
			obj = new GuiTestObject(tos[0]);
		}

		return obj;
	}

	/**
	 * 
	 * @param className
	 * @param context
	 * @return
	 */
	protected UIObj[] getCandidateObjects(String className, TestObject context) {
		Property[] props = { new Property(".class", className),

		};
		TestObject[] tos = context.find(new Anchor(false, props));
		UIObj[] objs = null;
		if (tos != null) {
			objs = new UIObj[tos.length];
			for (int i = 0; i < tos.length; ++i) {
				objs[i] = UIObj.newObj((GuiTestObject) (tos[i]));
			}
		}
		return objs;
	}

	/**
	 * 
	 * @param reg
	 * @param className
	 * @param objClassName
	 * @param context
	 * @return
	 */
	public GuiTestObject getControlByTitle(String reg, String className,
			String objClassName, TestObject context) {
		UIObj origin = getOrigin(reg, className, context);
		UIObj[] objs = getCandidateObjects(objClassName, context);
		float[] diag = { 1, 1, 0.2f, 0.2f, 1.1f, 1.1f }; // Default diagonal
		// matrix
		// Make the diagonal matrix by the type of the title
		if (className.matches(".+?image.*") == true) {
			diag = new float[] { 1, 1, 1, 1, 0.1f, 0.1f };
		}
		if (origin == null) {
			// System.err.println("Error getting origin object.");
			return null;
		}
		if (objs == null) {
			// System.err.println("Error getting candidate objects.");
			return null;
		}
		MVec[] vs = new MVec[objs.length];
		for (int i = 0; i < objs.length; ++i) {
			vs[i] = MVec.makeMetricVect(objs[i].metricVector(), origin
					.metricVector());
			/*
			 * System.out.println("Determinant value of index " +
			 * i+"("+objs[i].getProperty(".id") +")" +" to '"+reg+ "': "+
			 * "Original ->" + vs[i].deter() +" | " + "Weighted ->" +
			 * vs[i].deter(diag));
			 */
		}

		int x = MVec.findBestMVec(vs, diag);
		// x is the index!
		if (x != -1) {
			// System.out.println("Object: " + x + " found!" + "(" +
			// objs[x].getProperty(".name")+")");
			return objs[x].guiTestObject();
		} else {
			System.err.println("Error. No best vector found!");
			return null;
		}
	}

	/**
	 * Get object used as origin.
	 * 
	 * @param reg
	 *            String. The regular expr.
	 * @param className
	 * @return
	 */
	public UIObj getOrigin(String reg, String className, TestObject context) {
		UIObj objTarget = null;
		String propName = ".text";

		if (className == null || className.length() == 0) {
			// Assert the origin to be
			// Html.LABEL
			// Html.INPUT.*
			// Html.IMG
			// cell of Html.TABLE
			objTarget = getOrigin(reg, "Html.LABEL", context);
			if (objTarget == null)
				getOrigin(reg, "Html.INPUT.*", context);
			if (objTarget == null)
				getOrigin(reg, "Html.IMG", context);
			if (objTarget == null)
				getOrigin(reg, "Html.TABEL", context);
		} else {
			// Find origin by the class that user specifies
			try {
				if (className.matches(".+?TABLE.*") == true) {
					objTarget = getTableOrigin(reg, context);
				} else {
					if (className.matches(".+?LABEL.")) {
						propName = ".text";
					} else if (className.matches(".+?INPUT.*") == true) {
						// For buttons, submits, resets and text fields
						propName = ".value";
						// For images
						if (className.matches(".+?IMG.*")) {
							propName = ".alt";
						}
					}
					objTarget = UIObj.newOriginObj(findElementByReg(reg,
							propName, className, context));
				}
			} catch (ObjectIsDisposedException e) {
				System.err.println("Warning: Object disposed is used again.");
			} catch (Exception e) {
				System.err
						.println("Warning: Property of the origin does not exist.");
				objTarget = null;
			}

		} // End of if-else

		return objTarget;
	}

	/**
	 * Added code to search for a control's properties in xml file, and return
	 * object appears in SAP under testing. Added by Kevin.Huangfu.
	 * 
	 * @param name
	 *            object name in map XML
	 * @param contextObj
	 *            find object based on this object
	 * @author kevin.Huangfu
	 */
	public TestObject getSapObjectByName(String name, TestObject contextObj) {
		ObjectItem parent = null;
		Sap sap = Sap.getInstance();
//		String[] objs= name.split(":");
//		String rname = "";
//		if (objs.length>1) {
//			rname = objs[objs.length-1];
//		} else {
//			rname = name;
//		}
		boolean found = false;
		if (objectTreeData != null) {
			try {
				item = new ObjectItem();
				parent = new ObjectItem();
				found = objectTreeData.search(name, item, parent);
				if (found) {
					return sap.findObject(".class", item.classID,
							item.propertyName, item.value);
				}
			} catch (NullPointerException e) {

			}
		}
		return null;

	}

	/**
	 * 
	 * @param header
	 * @param context
	 * @return
	 */
	protected UIObj getTableOrigin(String header, TestObject context) {
		UIObj obj = null;
		Rectangle cell = null;
		boolean profile = false;
		long start = 0, end = 0;
		if (profile) {
			start = new java.util.Date().getTime();
		}
		// Get all tables and search for cell with specified text
		Property[] props = { new Property(".class", "Html.TABLE") };
		Anchor anchor = new Anchor(false, props);
		TestObject[] toTBs = context.find(anchor);

		if (toTBs != null) {
			Pattern pat;
			if (ignoreCase) {
				pat = Pattern.compile(header, Pattern.CASE_INSENSITIVE);
			} else {
				pat = Pattern.compile(header);
			}
			for (int k = 0; k < toTBs.length; ++k) {
				MTable tb = new MTable(toTBs[k]);
				if (tb != null) {
					// cell = searchTableForText(tb, header);
					cell = searchTableForTextMT(tb, pat);
					if (cell != null) {
						obj = new UIObj(tb, cell.x + cell.width / 2, cell.y
								+ cell.height / 2, cell.x, cell.y, cell.x
								+ cell.width, cell.y + cell.height);
						break;
					}

				}
			}
		}
		if (profile) {
			end = new Date().getTime();
			System.out.println("Total " + (end - start)
					+ " ms for finding the table origin: " + header);
			System.out.println("At Cell:" + cell.x + "," + cell.y + ","
					+ cell.width + "," + cell.height);
		}
		return obj;
	}

	protected String getTypeFromClassId(String classId) {
		String result = "";
		if (classId.compareToIgnoreCase("Html.INPUT.submit") == 0)
			result = "button";
		if (classId.compareToIgnoreCase("Html.INPUT.button") == 0)
			result = "button";
		if (classId.compareToIgnoreCase("Html.INPUT.checkbox") == 0)
			result = "checkbox";
		if (classId.equalsIgnoreCase("Html.SELECT"))
			result = "listbox";
		if (classId.equalsIgnoreCase("Html.FRAME"))
			result = "frame";
		if (classId.equalsIgnoreCase("Html.IMG")
				|| classId.equals("Html.INPUT.image"))
			result = "image";
		if (classId.equalsIgnoreCase("Html.A"))
			result = "link";
		if (classId.equalsIgnoreCase("Html.INPUT.radio"))
			result = "radiobutton";
		if (classId.equalsIgnoreCase("Html.LABEL"))
			result = "statictext";
		if (classId.equalsIgnoreCase("Html.TABLE"))
			result = "table";
		if (classId.equalsIgnoreCase("Html.INPUT.text")
				|| classId.equalsIgnoreCase("Html.INPUT.password")
				|| classId.equalsIgnoreCase("Html.TEXTAREA"))
			result = "textfield";
		return result;
	}

	/**
	 * @return the fuzzyMatch
	 */
	public boolean isFuzzyMatch() {
		return fuzzyMatch;
	}

	/**
	 * Judge if the current methods for finding objects is in a case insensitive
	 * way.
	 * 
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * Fast version of search a text segment thru a table. Appox. 1/3 execution
	 * time of the slow version.
	 * 
	 * @param tb
	 * @param pat
	 * @return
	 */
	protected Rectangle searchTableForTextMT(MTable tb, Pattern pat) {
		// Stores information in quadret:
		Rectangle retRect = null;
		// Compare 4 cells a time.
		int cc = tb.getColumnCount() - 1, rc = tb.getRowCount() - 1;
		String match1 = "", match2 = "", match3 = "", match4 = "";

		for (int j = 0; j < cc; j += 2) {
			for (int i = 0; i < rc; i += 2) {
				try {
					if(tb.getCellContents(i, j) != null){
						match1 = tb.getCellContents(i, j).toString().trim();
					}
					if (pat.matcher(match1).matches()) {
						Rectangle rect = tb
								.getScreenRectangle(new Cell(new Row(new Index(
										i)), new Column(new Index(j))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					if(tb.getCellContents(i, j + 1) != null){
						match2 = tb.getCellContents(i, j + 1).toString().trim();
					}
					if (pat.matcher(match2).matches()) {
						Rectangle rect = tb.getScreenRectangle(new Cell(
								new Row(new Index(i)), new Column(new Index(
										j + 1))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					if(tb.getCellContents(i + 1, j) != null){
						match3 = tb.getCellContents(i + 1, j).toString().trim();
					}
					if (pat.matcher(match3).matches()) {
						Rectangle rect = tb.getScreenRectangle(new Cell(
								new Row(new Index(i + 1)), new Column(
										new Index(j))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					if(tb.getCellContents(i + 1, j + 1) != null){
						match4 = tb.getCellContents(i + 1, j + 1).toString().trim();
					}
					if (pat.matcher(match4).matches()) {
						Rectangle rect = tb.getScreenRectangle(new Cell(
								new Row(new Index(i + 1)), new Column(
										new Index(j + 1))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return retRect;
	}


	/**
	 * @param fuzzyMatch
	 *            the fuzzyMatch to set
	 */
	public void setFuzzyMatch(boolean fuzzyMatch) {
		this.fuzzyMatch = fuzzyMatch;
	}

	/**
	 * Set current methods for finding objects to case insensitive.
	 * 
	 * @param ignoreCase
	 *            the ignoreCase to set
	 */
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	/**
	 * If take hiearchy into consideration, that is, before finding the object
	 * first look at its parent (prefixed by ":"). If this flag is set to false
	 * all the contents before the last ":" are ignored. 
	 * @param hiearchy
	 */
	public void setHiearchy(boolean hiearchy)  {
		this.hiearchy = hiearchy;
	}

}
