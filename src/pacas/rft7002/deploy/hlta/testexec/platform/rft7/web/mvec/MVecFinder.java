/*
 * Created on Apr 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.mvec;

import java.awt.Rectangle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.ObjectIsDisposedException;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Anchor;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.Index;
import com.rational.test.ft.script.Property;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.vp.ITestDataTable;

import java.util.*;

/**
 * Description : Functional Test Script
 * 
 * @author Ryx
 */
public class MVecFinder {
	private boolean debug = false;
	private boolean profile = false;
	private long start = 0;
	private long end  = 0;
	private TestObject bto = null;

	public MVecFinder(TestObject toptestobj) {
		bto = toptestobj;
	}

	/**
	 * 
	 * @param propValue
	 *            String. The regular expression.
	 * @param propName
	 *            String. The property name to match.
	 * @param className
	 *            String. Type of the object to find.
	 * @return
	 */
	protected GuiTestObject findTestObject(String propValue, String propName,
			String className) {
		GuiTestObject obj = null;
		Property[] props = { new Property(".class", className) };
		Anchor anchor = new Anchor(false, props);
		TestObject[] toTBs = bto.find(anchor);

		try {
			String value = "";
			Pattern pat = Pattern.compile(propValue,Pattern.CASE_INSENSITIVE);
			Matcher mat = null;
			for (int i = 0; i < toTBs.length; ++i) {
				value = (String) toTBs[i].getProperty(propName);
				mat = pat.matcher(value);
				if (mat.matches()) {
					obj = new GuiTestObject(toTBs[i]);
					break;
				}
			}
		} catch (Exception e) {

		}

		return obj;

	}

	public UIObj[] getCandidateObjects(String className) {
		Property[] props = { new Property(".class", className),

		};
		TestObject[] tos = bto.find(new Anchor(false, props));
		UIObj[] objs = null;
		if (tos != null) {
			objs = new UIObj[tos.length];
			for (int i = 0; i < tos.length; ++i) {
				objs[i] = UIObj.newObj((GuiTestObject) (tos[i]));
			}
		}
		return objs;
	}

	public GuiTestObject getControlByTitle(String reg, String className,
			String objClassName) {
		UIObj origin = getOrigin(reg, className);
		UIObj[] objs = getCandidateObjects(objClassName);
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
	public UIObj getOrigin(String reg, String className) {
		UIObj objTarget = null;
		String propName = ".text";

		if (className == null || className.length() == 0) {
			// Assert the origin to be
			// Html.LABEL
			// Html.INPUT.*
			// Html.IMG
			// cell of Html.TABLE
			objTarget = getOrigin(reg, "Html.LABEL");
			if (objTarget == null)
				getOrigin(reg, "Html.INPUT.*");
			if (objTarget == null)
				getOrigin(reg, "Html.IMG");
			if (objTarget == null)
				getOrigin(reg, "Html.TABEL");
		} else {
			// Find origin by the class that user specifies
			try {
				if (className.matches(".+?TABLE.*") == true) {
					objTarget = getTableOrigin(reg.trim());
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
					objTarget = UIObj.newOriginObj(findTestObject(reg.trim(),
							propName, className));
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

	public UIObj getTableOrigin(String header) {
		UIObj obj = null;
		Rectangle cell = null;
		if (profile) {
			start = new java.util.Date().getTime();
		}
		// Get all tables and search for cell with specified text
		Property[] props = { new Property(".class", "Html.TABLE") };
		Anchor anchor = new Anchor(false, props);
		TestObject[] toTBs = bto.find(anchor);
		
		if (toTBs != null) {
			for (int k = 0; k < toTBs.length; ++k) {
				MTable tb = new MTable(toTBs[k]);
				if (tb != null) {
					// cell = searchTableForText(tb, header);
					cell = searchTableForTextMT(tb, header);
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
			System.out.println("Total " + (end - start) + " ms for finding the table origin: " + header);
			System.out.println("At Cell:" + cell.x + "," + cell.y +"," + cell.width +"," + cell.height);
		}
		return obj;
	}
	/**
	 * Slow version of search a text segment thru a table. 
	 * @param tb
	 * @param strtosearch
	 * @return
	 */
	public Rectangle searchTableForText(MTable tb, String strtosearch) {
		// Stores information in quadret:
		Rectangle retRect = null;
		String str = strtosearch.trim();
		int cc = tb.getColumnCount();
		int rc = tb.getRowCount();
		for (int j = 0; j < cc; ++j) {
			for (int i = 0; i < rc; ++i) {
				try {
					String match = tb.getCellContents(i, j).toString().trim();
					// Match cell string
					if (match.matches(strtosearch.trim()) == true) {
						Rectangle rect = tb.getScreenRectangle(new Cell(new Row(
								new Index(i)), new Column(new Index(j))));
						if (rect != null) {
							// System.err.println("Table cell found " + rect.x
							// +"," + rect.y);
							retRect = rect;
						}
						break;
					}
				} catch (Exception e) {
					// System.err.println(e.getMessage());
				}
			}
		}
	
		return retRect;
	}
	/**
	 * Fast version of search a text segment thru a table. Appox. 1/3 execution
	 * time of the slow version.
	 * @param tb
	 * @param strtosearch
	 * @return
	 */
	public Rectangle searchTableForTextMT(MTable tb, String strtosearch) {
		// Stores information in quadret:
		Rectangle retRect = null;
		// Compare 4 cells a time.
		int cc = tb.getColumnCount()-1, rc = tb.getRowCount()-1;
		String match1, match2, match3, match4;
		String str = strtosearch.trim();
		for (int j = 0; j < cc; j+=2) {
			for (int i = 0; i < rc; i+=2) {
				try {
					match1 = tb.getCellContents(i, j).toString().trim();
					if (match1.toLowerCase().matches(str.toLowerCase()) == true) {
						Rectangle rect = tb.getScreenRectangle(new Cell(new Row(
								new Index(i)), new Column(new Index(j))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					match2 = tb.getCellContents(i, j+1).toString().trim();
					if (match2.matches(str)==true) {
						Rectangle rect = tb.getScreenRectangle(new Cell(new Row(
								new Index(i)), new Column(new Index(j+1))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					match3 = tb.getCellContents(i+1, j).toString().trim();
					if (match3.matches(str)==true) {
						Rectangle rect = tb.getScreenRectangle(new Cell(new Row(
								new Index(i+1)), new Column(new Index(j))));
						if (rect != null) {
							retRect = rect;
						}
						break;
					}
					match4 = tb.getCellContents(i+1, j+1).toString().trim();
					if (match4.matches(str)==true) {
						Rectangle rect = tb.getScreenRectangle(new Cell(new Row(
								new Index(i+1)), new Column(new Index(j+1))));
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
	public static void getTableString(MTable tb) {
		ITestDataTable table = tb.getITestDataTable();
		System.out.println(table.getData().toString());
	}
	
}