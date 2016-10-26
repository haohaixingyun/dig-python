/*
 * Created on Jan 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;

/**
 * @author luoling
 * @author jeffreybian
 * Modified implementation of the class. Added methods:
 * 	appendMappingFile
 * 	buildTree
 * Added "Lazy sort" and Vector for storing ObjectItem data.
 */
public class ObjectTreeData {
	static public void main(String args[]) {
		System.out.println("start");

	}

	private Document doc = null;

	private DocumentBuilderFactory factory = null;

	private DocumentBuilder builder = null;

	private ErrorHandler errorhandle = null;

	private boolean sorted = false;

	private Vector<ObjectItem> itemArray = null;

	private boolean debug = false;

	public ObjectTreeData(String filelist, String path) {
		String[] files = filelist.split("\\|");
		//if (files.length>1) {
			System.out.println(files[0]);
			init(new File(path + "/" + files[0]));
			for (int i = 1 ; i< files.length; ++i) {
				System.out.println(files[i]);
				appendMappingFile(path + "/"+ files[i]);
				
			}
		//} else {
		//	init(new File(path));
		//}
		
		if (debug) {
			System.out.println("Constructor of ObjectTreeData: " + new File(path).getPath());
		}
		
	}

	/*
	 * Added by jeffreybian, given a path, search for all the xml files in the
	 * path and build a big tree to hold their data.
	 */
	public void init(File file) {
		try {
			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();
			builder.setErrorHandler(errorhandle);
			buildTree(file);
		} catch(NullPointerException e) {
			System.err.println("Null pointer.");
		} catch(FileNotFoundException e) {
			System.err.println("File/Directory " + file.getPath() + " not found.");
		} catch (ParserConfigurationException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void appendMappingFile(String fullName) {
		appendMappingFile(new File(fullName));
	}

	public void appendMappingFile(File file) {
		// If no root element, just create one
		// Need re-sort, :)
		sorted = false;
		try {
			if (itemArray == null) {
				itemArray = new Vector<ObjectItem>();
				if (debug) {
					System.out.println("ObjectItem vector created.");
				}
			}
			Document doclet = builder.parse(file);
			// Attach the doc tree to a sub node of the resulting tree
			String rootName = doclet.getDocumentElement().getNodeName();
			NodeList nls = doclet.getDocumentElement().getChildNodes();
			for (int m = 0; m < nls.getLength(); m++) {
				if (nls.item(m) instanceof Element) {
					Element element = (Element) nls.item(m);
					// Make the array
					ObjectItem item = new ObjectItem();
					item.setName(rootName+":"+element.getNodeName()); //modified by kevin on 2008/3/25, fixed one bug(if there are
					                                                  //same object name in different object file even through prefix is different, program will not figure out which one should be found)
					item.setProperty(element.getAttribute("propertyName"));
					item.setClassID(element.getAttribute("classID"));
					item.setType(element.getAttribute("type"));
					item.setValue(element.getAttribute("value"));
					item.setClassIndex(element.getAttribute("classIndex"));
					if (element.hasAttribute("by")) {
						item.setRefObj(element.getAttribute("by"));
					}
					if (element.hasAttribute("pos")) {
						item.setPosIndex(element.getAttribute("pos"));
					}
					if (element.hasAttribute("ref")) {
						item.setPilot(element.getAttribute("ref"));
					}
					itemArray.add(item);
					if (debug) {
						System.out.println("Node: "
								+ item.getName() + " added.");
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File "+ file.getName() +" not found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int buildTree(File file) throws FileNotFoundException {
		try {
			if (doc == null) {
				doc = builder.getDOMImplementation().createDocument(null,
						"Good", null);
			}
			if (!file.isFile()) {
				if (debug) {
					System.out.println("Input "+ file.getPath() +" is a FOLDER name.");
				}
				// If file is a directory, just read in all the xml files in
				// this directory
				File fe = file;
				// Filter all the xml's
				File[] files = fe.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						if (name.toLowerCase().endsWith(".xml")) {
							return true;
						} else {
							return false;
						}
					}
				});
				for (int i = 0; files!=null && i < files.length; ++i) {
					appendMappingFile(files[i]);
					if (debug) {
						System.out.println("Mapping file " + files[i].toString() + " appended.");
					}
				}
				if (files==null) {
					System.err.println("No mapping files found.");
				}
				if (debug) {
					NodeList nls = doc.getDocumentElement().getChildNodes();
					for (int m = 0; m < nls.getLength(); m++) {
						System.out.println(nls.item(m).getNodeName());
					}
				}
			} else {
				// If file is merely an xml file
				if (debug) {
					System.out.println("Input "+file.getPath()+" is a FILE name.");
				}
				appendMappingFile(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	private String getElementValue(String value) {
		if (parseValue(value))
			return value;
		else
			return "";
	}

	private boolean parseValue(String value) {
		if (value.indexOf("@") >= 0 || value.indexOf("$") >= 0)
			return false;
		else
			return true;

	}

	/*
	 * public void printDomTree(){ Element root = doc.getDocumentElement();
	 * System.out.println("Root Node : "+root.getTagName()+"\n"); NodeList
	 * nodeList = root.getChildNodes(); System.out.println("nodelist nodes
	 * number is :"+nodeList.getLength()); for(int i = 0; i <
	 * nodeList.getLength(); i++){ Node node = nodeList.item(i); if (node
	 * instanceof Element){ Element element = (Element)node; String a =
	 * element.getAttribute("value"); String b =
	 * element.getAttribute("propertyName"); String c =
	 * element.getAttribute("classID"); String d =
	 * element.getAttribute("classIndex");
	 * System.out.println(element.getNodeName()+a+b+c+d+"haschild"+element.hasChildNodes()) ; } } }
	 */
	/*
	 * Search the unique item in the portal tree nodename: search target item:
	 * object only to pass the reference value
	 */
	/* Edited by jeffreybian, changed return value to boolean */
	public boolean search(String nodeName, ObjectItem item) {
		return search(nodeName, item, null); /* jeffreybian */
	}

	/* Overload functoin added by jeffreybian, to retreive parent information */
	public boolean search(String nodeName, ObjectItem item,
			ObjectItem parentItem) {
		// Element root = doc.getDocumentElement();
		Element root = null;
		/* searchItem(root,nodeName,item); */
		return searchItem(root, nodeName, item, parentItem); /* jeffreybian */
	}

	/* Overload functoin added by jeffreybian, to retreive parent information */
	public boolean searchItem(Element root, String nodeName, ObjectItem item) {
		return searchItem(root, nodeName, item, null);
	}

	private boolean searchItem(Element root, String nodeName, ObjectItem item,
			ObjectItem parentItem) {
		boolean found = false;
		class Comparer implements Comparator<ObjectItem> {
			public int compare(ObjectItem item1, ObjectItem item2) {
				int re = item1.getName().compareTo(item2.getName());
				return re;
			}
		}
		// NodeList nodeList = root.getChildNodes();
		ObjectItem temp = new ObjectItem();
		// Lazy sort, only the first time we are to find some object, the
		// node-list is sorted
		if (!sorted) {
			// Sort the array
			Collections.sort(itemArray, new Comparer());
			if (debug) {
				for (ObjectItem c : itemArray) {
					System.out.println(c.getName());
				}
			}
		}
		temp.setName(nodeName);
		int index = Collections.binarySearch(itemArray, temp, new Comparer());
		if (debug) {
			System.out.println("Item " + nodeName + " found at " + index + " (" + itemArray.size()+ " in total)" );
		}
		if (index > -1) {
			item.setName(itemArray.get(index).getName());
			item.setValue(itemArray.get(index).getValue());
			item.setProperty(itemArray.get(index).getProperty());
			item.setClassID(itemArray.get(index).getClassID());
			item.setType(itemArray.get(index).getType());
			item.setClassIndex(itemArray.get(index).getClassIndex());
			if (itemArray.get(index).getRefObj().length() != 0) {
				item.setRefObj(itemArray.get(index).getRefObj());
			}
			if (itemArray.get(index).getPosIndex().length() != 0) {
				item.setPosIndex(itemArray.get(index).getPosIndex());
			}
			if (itemArray.get(index).getPilot().length() != 0) {
				item.setPilot(itemArray.get(index).getPilot());
			}
			found = true;
		}
		return found;
	}

	/*
	 * Edited by jeffreybian, return value changed to boolean, modified the arg
	 * list
	 */
	/* private void searchItem(Element root,String nodeName,ObjectItem item ){ */
	/*
	 * Two types of object map 1. Directly find by its properties <LinkSite01
	 * value="7067388" type="link" propertyName=".text" classID="Html.A"/> 2.
	 * Indirectly find by title controls <CompanySearch value="For.*"
	 * by="Html.TABLE" type="textfield" classID="Html.INPUT.text"/> value: for
	 * title text by: the class id of the title control type: The type of the
	 * target control classID: the class id of the target control
	 */
	private boolean _searchItem(Element root, String nodeName, ObjectItem item,
			ObjectItem parentItem) {
		boolean found = false;
		NodeList nodeList = root.getChildNodes();
		// Lazy sort, only the first time we are to find some object, the
		// nodelist is sorted
		if (!sorted) {
			// Sort the array
			Arrays.sort(itemArray.toArray(new ObjectItem[] {}),
					new Comparator<ObjectItem>() {
						public int compare(ObjectItem item1, ObjectItem item2) {
							int re = item1.getName().compareTo(item2.getName());
							return re;
						}
					});
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				// System.out.println(element.getNodeName()+"haschild"+element.hasChildNodes())
				// ;
				if (element.getNodeName().equals(nodeName)) {
					String tmpStr = getElementValue(element
							.getAttribute("value"));
					if (tmpStr != null)
						item.setValue(tmpStr);
					item.setProperty(element.getAttribute("propertyName"));
					item.setClassID(element.getAttribute("classID"));
					item.setType(element.getAttribute("type"));
					item.setClassIndex(element.getAttribute("classIndex"));
					if (element.hasAttribute("by")) {
						item.setRefObj(element.getAttribute("by"));
					}
					if (element.hasAttribute("pos")) {
						item.setPosIndex(element.getAttribute("pos"));
					}
					found = true;
					/*
					 * Added by jeffrey Bian Also retreive its parent ObjectItem
					 * if any
					 */
					if (parentItem != null) {
						Node parentNode = node.getParentNode();
						if (parentNode instanceof Element) {
							Element parentElement = (Element) (parentNode);
							String tmpStr2 = getElementValue(element
									.getAttribute("value"));
							if (tmpStr != null)
								item.setValue(tmpStr2);
							parentItem.setProperty(element
									.getAttribute("propertyName"));
							parentItem.setClassID(element
									.getAttribute("classID"));
							parentItem.setType(element.getAttribute("type"));
							parentItem.setClassIndex(element
									.getAttribute("classIndex"));
						}
					} // End of if (parentItem!=null)
				} // End of if(element.getNodeName().equals(nodeName))
				if (element.hasChildNodes() == true) {
					searchItem(element, nodeName, item);
				}
			}
		}

		return found;

	}
}
