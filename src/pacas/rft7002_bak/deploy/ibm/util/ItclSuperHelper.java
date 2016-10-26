// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import ibm.tools.ClassGenerator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.rational.test.ft.vp.ITestDataTable;

/**
 * 
 * Description: This is a super helper class used by ITCL and made available for all of IBM.<p>
 * 
 * You can either use this directly as is, or if you want additional functionality,
 * 		you can inherit this into a separate super helper of your own, located in a local folder.
 * 		and then you can add methods or even override the methods herein.<p>
 * 
 * In any case, <b>DO NOT MODIFY THIS FILE DIRECTLY!<\b>
 * 
 *
 * @author - James L Jones
 * 
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class ItclSuperHelper extends RationalTestScript {

	private IWindow activeWindow;
	private ITestDataTable myTable;

	protected IWindow getActiveWindow() {
		return getScreen().getActiveWindow();
	}

	public void printLeakedChildren() {
		TestObject leaked[] = getRegisteredTestObjects();

		if (leaked.length != 0)
			System.err.println("CHILDREN HAVE BEEN LEAKED.");

		for (int i = 0; i < leaked.length; i++) {
			System.out.println(leaked[i] + "\t:\t" + leaked[i].getProperties());
		}
	}

	public static String getSubitemName(Subitem node) {
		// The following supports an atPath or atList Subitem

		// get the last embedded String in the node
		String name = node.toString();
		StringTokenizer st = new StringTokenizer(node.toString(), new Character('\"').toString());
		int count = st.countTokens();
		for (int i = 0; i < count - 1; i++) // get the 2nd last token
			name = st.nextToken().trim();

		// get the token following the last ->, if any		
		st = new StringTokenizer(name, "->");
		while (st.hasMoreTokens())
			name = st.nextToken().trim();

		return name;
	}

	public boolean performTest(String name, Object expected, Object actual) {
		IFtVerificationPoint vp = vpManual(name, expected, actual);
		return vp.performTest();
	}
	public boolean performTest(String name, boolean expected, boolean actual) {
		Boolean e = new Boolean(expected);
		Boolean a = new Boolean(actual);
		IFtVerificationPoint vp = vpManual(name, e, a);
		return vp.performTest();
	}
	/** Used to determine the current screen resolution width\
	 * @return the screen width
	 * @author - James L Jones
	 */
	public double getScreenWidth() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	/** Used to determine the current screen resolution height
	 * @return the screen height
	 * @author - James L Jones
	 */
	public double getScreenHeight() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}

	public String generateDateTimeStamp() {
		//generates String containing unique random date-based value	
		String s;
		Date d = new Date();
		long l;

		//format
		SimpleDateFormat tmformat = new SimpleDateFormat("MMddHHmmss");

		l = d.getTime();
		s = tmformat.format(d);

		return s;
	}
	public void updateClasses(Vector testScripts) {
		for (int i = 0; i < testScripts.size(); i++) {
			RationalTestScript script = (RationalTestScript) testScripts.elementAt(i);
			String newClassContents = new ClassGenerator().generateClass(script);
			new ClassGenerator().updateFile(script, newClassContents);
		}

	}
	public void updateClasses(RationalTestScript script) {

		String newClassContents = new ClassGenerator().generateClass(script);
		new ClassGenerator().updateFile(script, newClassContents);

	}
}
