// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util.terminal;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

/**
 * Description: Functional Tester Terminal Tester methods which look at the terminal.
 * <p>
 * <b>Note</b>:  all of these methods require at least one TestObject argument:
 * <br>
 * 
 * 1.  All require the TestObject Screen argument, which 
 *  is the object of .class "com.ibm.eNetwork.beans.HOD.Screen",
 * and can be obtained via the code snippet 
      	<code><dir>public TestObject getTerminalScreen() {<br>
 			TestObject to = Screen(ANY,LOADED);<br>
			return new TestObject(to);	
	    }</dir></code>
 * from a script associated with an object map containing this object.  Note:  when creating
 * the object map for this object, modify the property value weights to be all 0 except for .class,
 * since the intention is for this to be a fully generic object.
 * <br><br>
 * 2.  The second TestObject argument that may be required is
 *  the TestObject TerminalOIA, which reflects system information.  
 *  It is of the .class "com.ibm.eNetwork.ECL.ECLOIA",
 *  and can be obtained via the code snippet
	<code><dir>public TestObject getTerminalOIA() {<br>
		TestObject to = TerminalOIA(ANY,LOADED);<br>
		return new TestObject(to);	<br>
	}</dir></code>
 *  from a script associated with an object map containing this object.
 * 
 * @author Ann Kopren and Chris Carlson
 *
 * @version 2.2
 * Last Modified: 08/24/04
 */
public class TerminalLookOps 
{
	
	/**
	 * Gets the text for the terminal and all of its children
	 *    in an array of one row of text per array index
	 * Note:  this method can be slow because it will search through the 
	 *    argument TestObject's children.
	 * Same as "get all field values" from the verification point wizard
	 * @param Screen  the TestObject for the Screen 
	 * @return String [] containing one row of text per array index
	 */
	public static String [] getScreenAndChildrenText (TestObject Screen) 
	{
		int rows = ((Integer) Screen.getProperty("rows")).intValue();
		String [] contents = new String [rows];
		for (int i = 0; i < contents.length; ++i)
			contents[i] = "";

		TestObject [] children = Screen.getChildren();
		for (int i = 0; i < children.length; ++i)
		{
			java.lang.Integer row = (java.lang.Integer) children[i].getProperty(".startRow");
			java.lang.Integer col = (java.lang.Integer) children[i].getProperty(".startCol");
			String text = (String) children[i].getProperty(".text");
			//System.out.println(row + "_" + col + " " + text);

			int length = contents[row.intValue()-1].length();
			String space = "";
			if (length != col.intValue()-1)
			{
				int value = (col.intValue() - length - 1);
				//System.out.println("col("+col.intValue()+") - length("+length+") - 1 = " + value);
				for (int j = 0; j < value; ++j)
					space += " ";
			}
			contents[row.intValue()-1] += space + text;
		}

		for (int i = 0; i < contents.length; ++i)
			System.out.println(contents[i]);
		return contents;
	}

	/**
	 * Gets the text for the terminal object itself in an single long String
	 * Note: this method is much faster than getScreenAndChildrenText because it does not search
	 *   through children.  BECAUSE OF THIS YOU MUST SEND THE METHOD THE CORRECT OBJECT.  In
	 *   Terminal Tester, the correct object level is the object with a .class property of 
	 *   "com.ibm.eNetwork.beans.HOD.Screen"
	 * Same as "get all field values" from the verification point wizard 
	 * @param Screen  the TestObject for the Screen 
	 * @return String [] containing all .textPlane values, or an empty array if values were not found
	 */
	public static String [] getScreenTextAsArray (TestObject Screen)  
	{
		
		String [] contents = new String [0];
		try
		{

			String screen = (String) Screen.getProperty(".textPlane");
			int col = Integer.parseInt(Screen.getProperty("columns").toString());
			int row = Integer.parseInt(Screen.getProperty("rows").toString());

			contents = new String [row];
			for (int i = 0, j = col; i < screen.length(); i += col, j += col)
			{
				if (j > screen.length())
					j = screen.length();
				contents[i/col] = screen.substring(i, j);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return contents;
	}

	/**
	 * Gets the text for the terminal object itself in an single long String
	 * Note: this method is much faster than getScreenAndChildrenText because it does not search
	 *   through children.  BECAUSE OF THIS YOU MUST SEND THE METHOD THE CORRECT OBJECT. In
	 *   Terminal Tester, the correct object level is the object with a .class property of 
	 *   "com.ibm.eNetwork.beans.HOD.Screen"
	 * Same as "get all field values" from the verification point wizard
	 * @param Screen  the TestObject for the Screen 
	 * @return String containing all .textPlane values, or null if the values were not found
	 */
	public static String getScreenTextAsString (TestObject Screen) 
	{
	
		try
		{
			return (String) Screen.getProperty(".textPlane");

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Gets the Operator Information Area (OIA) state for the terminal
	 * @param TerminalOIA  the TestObject for the Terminal OIA (has a .class
	 *     value of "com.ibm.eNetwork.ECL.ECLOIA")
	 * @return hashtable  containing the state properties and their values
	 */
	public static java.util.Hashtable getOIAState (TestObject TerminalOIA)
	{
		java.util.Hashtable properties = TerminalOIA.getProperties();
		return properties;
	}


	/**
	 * Gets the text for the input fields in a String array
	 * Gets the same data as the "Non-Static Field Values" Data Verification point wizard
	 * which are the text input areas
	 * @param Screen  the TestObject for the Screen 
	 * @return String [] containing the input field contents
	 */
	public static String [] getInputFieldValues (TestObject Screen) 
	{
		ITestData data = Screen.getTestData("NonStaticFieldsTest");
		String [] propkeys = data.getPropertyKeys();
		com.rational.test.ft.vp.impl.TestDataElementList datalist = (com.rational.test.ft.vp.impl.TestDataElementList) data.getProperty("data");
		java.util.Vector elist = datalist.getElements();

		//initialize contents
		String [] contents = new String [elist.size()];
		for (int i = 0; i < contents.length; ++i)
			contents[i] = "";

		//get the data
		for (int i = 0; i < elist.size(); ++i) {
			com.rational.test.ft.vp.impl.TestDataElement elem = (com.rational.test.ft.vp.impl.TestDataElement) elist.get(i);
			Object obj = elem.getElement();
			contents[i] = obj.toString();
		}
		return contents;
	}

	/**
	 * Gets the Field TestObject with the specified .startRow and .startCol values
	 * @param startRow  the .startRow to look for
	 * @param startCol  the .startCol to look for
	 * @param Screen  the TestObject for the Screen 
	 * @return TestObject with .startRow = row, .startCol = column, or null
	 */
	public static TestObject getObjectAtCoords (int startRow, int startCol, TestObject Screen) 
	{
		TestObject testObject = null;
		TestObject [] tos = Screen.getChildren();
		for (int i = 0; i < tos.length; ++i)
		{
			try
			{
				Integer c = (Integer) tos[i].getProperty(".startCol");
				Integer r = (Integer) tos[i].getProperty(".startRow");
				//System.out.println(r.toString() + " " + c.toString() +  " " + tos[i].getProperty(".text"));

				if (r.intValue() == startRow && c.intValue() == startCol)
					testObject = tos[i];
			}
			//catch any exception getProperty, or casting as Integer might throw
			catch (Exception e)
			{
				//don't need to do anything with this exception, if a child object doesn't have
				//.startRow and .startCol then it's not the one we're looking for
				//System.out.println(e.getMessage());
			}
		}
		return testObject;
	}

	/**
	 * Gets the text with the specified .startRow and .startCol values
	 * @param startRow  the .startRow to look for
	 * @param startCol  the .startCol to look for
	 * @param Screen  the TestObject for the Screen 
	 * @return String with .startRow = row, .startCol = column, or null
	 */
	public static String getTextAtCoords (int startRow, int startCol, TestObject Screen) 
	{
		
		String result=null;
		TestObject testObject = null;
		TestObject [] tos = Screen.getChildren();
		for (int i = 0; i < tos.length; ++i)
		{
			try
			{
				Integer c = (Integer) tos[i].getProperty(".startCol");
				Integer r = (Integer) tos[i].getProperty(".startRow");
				//System.out.println(r.toString() + " " + c.toString() +  " " + tos[i].getProperty(".text"));

				if (r.intValue() == startRow && c.intValue() == startCol)  {
					testObject = tos[i];
					result = (String) tos[i].getProperty(".text");
				}
			}
			//catch any exception getProperty, or casting as Integer might throw
			catch (Exception e)
			{
				//don't need to do anything with this exception, if a child object doesn't have
				//.startRow and .startCol then it's not the one we're looking for
				//System.out.println(e.getMessage());
			}
		}
		return result;
	}



}
