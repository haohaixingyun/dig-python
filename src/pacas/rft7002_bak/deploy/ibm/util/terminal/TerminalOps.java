// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util.terminal;

import ibm.loggers.control.RTSLoggingWrapper;
import ibm.util.StringOps;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description: Functional Tester Terminal Tester utility methods.
 * <p>
 * <b>Note</b>:  all of these methods require at least one TestObject argument:
 * <br>
 * 
 * 1.  All require the TestObject Screen argument, which 
 *  is the object of .class "com.ibm.eNetwork.beans.HOD.Screen",
 * and can be obtained via the code snippet 
      	<code><dir>public TestObject getTerminalScreen() {<br>
 			TestObject to = Screen(ANY,LOADED);<br>
			return new TestObject(to);	<br>
	    }</dir></code>
 * from a script associated with an object map containing this object.  Note:  when creating
 * the object map for this object, modify the property value weights to be all 0 except for .class,
 * since the intention is for this to be a fully generic object.
 * <br><br>
 * 2.  The second TestObject argument that may be required is
 *  the TopLevelTestObject Frame, which is needed to send any keystrokes
 *  to the terminal.  It is of the .class "com.ibm.terminal.tester.gui.panel.TFrame",
 *  and can be obtained via the code snippet
	 	<code><dir>public TopLevelTestObject getFrame() {<br>
			TestObject to = Frame(ANY,LOADED);<br>
			return new TopLevelTestObject(to);<br>
		}</dir></code>
 *  from a script associated with an object map containing this object.
 * @author Ann Kopren and Chris Carlson
 *
 * @version 2.2
 * Last Modified: 09/10/04
 */
public class TerminalOps 

{

	/**
	 * Searches in the displayed screen for occurrences of the search string
	 * @param searchstring  the string to search for
	 * @param Screen  the screen testobject to search in
	 * @return boolean  true if string is found, false if not
	 */
	public static boolean isStringOnScreen(String searchstring, TestObject Screen) {

		String screenContents = TerminalLookOps.getScreenTextAsString(Screen);
		return StringOps.findString(searchstring, screenContents);

	}

	/**
	 * Searches in the displayed screen for occurrences of the search pattern
	 * @param search  the string/pattern to search for
	 * @param Screen  the screen testobject to search in
	 * @param pattern  true for a Regex search pattern, false for a search string
	 * @return boolean  true if string is found, false if not
	 */
	public static boolean isStringOnScreen(String search, TestObject Screen, boolean pattern) {

		String screenContents = TerminalLookOps.getScreenTextAsString(Screen);
		if (pattern)
			return StringOps.findPattern(search, screenContents);
		else
			return StringOps.findString(search, screenContents);
	}


	/**
	 * Searches through the available screens for occurrences of the search string.  If
	 *      it finds the string, returns true.  
	 *      If doesn't find the string, looks for "More..." indicator and if it's there,
	 *      scrolls down to the next screen.  If it can't find it and the "More..." indicator
	 *      is not there, returns false.  When the routine is done, the screen remains on the
	 *      screen where the string was found (if returning true) or on the bottom screen (if returning false).
	 * @param searchstring  the string to search for
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @return boolean  true if string is found, false if not
	 */

	public static boolean searchThroughScreens(String searchstring, TestObject Screen, TopLevelTestObject Frame) {

		TerminalHelperMethods th = new TerminalHelperMethods();
		boolean more = false;
		
		if (isStringOnScreen(searchstring, Screen)) {
			System.out.println("String '"+searchstring+"' found on screen.");
			return true;
		} else if (isStringOnScreen("More...", Screen)) {
			more = true;
			while (more) {
				Frame.inputKeys("{ExtPgDn}");
				th.wait(2);
				if (isStringOnScreen(searchstring, Screen)) {
					System.out.println("String '"+searchstring+"' found on screen.");
					return true;
				}
				more = isStringOnScreen("More...", Screen);
			}

		}
		System.out.println("String '"+searchstring+"' not found on screens.");
		return false;

	}
	
	/**
	 * Searches through the available screens for occurrences of the search string.  If
	 *      it finds the string, returns true.  
	 *      If doesn't find the string, looks for "More..." indicator and if it's there,
	 *      scrolls down to the next screen.  If it can't find it and the "More..." indicator
	 *      is not there, returns false.  When the routine is done, the screen remains on the
	 *      screen where the string was found (if returning true) or on the bottom screen (if returning false).
	 * @param search  the string/pattern to search for
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @param pattern  true for a Regex search pattern, false for a search string
	 * @return boolean  true if string is found, false if not
	 */

	public static boolean searchThroughScreens(String search, TestObject Screen, TopLevelTestObject Frame, boolean pattern) {

		TerminalHelperMethods th = new TerminalHelperMethods();
		boolean more = false;
		
		if (isStringOnScreen(search, Screen, pattern)) {
			System.out.println("String '"+search+"' found on screen.");
			return true;
		} else if (isStringOnScreen("More...", Screen)) {
			more = true;
			while (more) {
				Frame.inputKeys("{ExtPgDn}");
				th.wait(2);
				if (isStringOnScreen(search, Screen, pattern)) {
					System.out.println("String '"+search+"' found on screen.");
					return true;
				}
				more = isStringOnScreen("More...", Screen);
			}

		}
		System.out.println("String '"+search+"' not found on screens.");
		return false;

	}

	/**
	 * Searches through the available screens for occurrences of the search string.  If
	 *      it finds the string, returns row index where the string was found.
	 *      If doesn't find the string, looks for "More..." indicator and if it's there,
	 *      scrolls down to the next screen.  If it can't find it and the "More..." indicator
	 *      is not there, returns -1.  When the routine is done, the screen remains on the
	 *      screen where the string was found (if returning an index) or on the bottom screen (if returning -1).
	 * @param searchstring  the string to search for
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @return boolean  true if string is found, false if not
	 */
	public static int searchScreensForRow(String searchstring, TestObject Screen, TopLevelTestObject Frame) {

		boolean more = false;
		int result ;
		if (searchThroughScreens(searchstring, Screen, Frame)) {
			String [] screenContentsArray = TerminalLookOps.getScreenTextAsArray(Screen);
			result = StringOps.findStringRow(searchstring, screenContentsArray);
			System.out.println("Found string '"+searchstring+"' in row "+result);
			return result;
		}
		else return -1;
	}
	
	/**
	 * Searches through the available screens for occurrences of the search string.  If
	 *      it finds the string, returns row index where the string was found.
	 *      If doesn't find the string, looks for "More..." indicator and if it's there,
	 *      scrolls down to the next screen.  If it can't find it and the "More..." indicator
	 *      is not there, returns -1.  When the routine is done, the screen remains on the
	 *      screen where the string was found (if returning an index) or on the bottom screen (if returning -1).
	 * @param search  the string/pattern to search for
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @param pattern  true for a Regex search pattern, false for a search string
	 * @return boolean  true if string is found, false if not
	 */
	public static int searchScreensForRow(String search, TestObject Screen, TopLevelTestObject Frame, boolean pattern) {

		boolean more = false;
		int result ;
		if (searchThroughScreens(search, Screen, Frame, pattern)) {
			String [] screenContentsArray = TerminalLookOps.getScreenTextAsArray(Screen);
			if (pattern)
				result = StringOps.findPatternRow(search, screenContentsArray);
			else
				result = StringOps.findStringRow(search, screenContentsArray);
			System.out.println("Found string '"+search+"' in row "+result);
			return result;
		}
		else return -1;
	}


	/**
	 * Searches in the displayed screen for occurrences of the search string.  
	 *      Gathers the rows that match the search string, then checks to see
	 *      if one of the matches is in the target row that the match should be in.
	 * @param searchstring  the string to search for
	 * @param targetrow  the row the string should be found in
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @return boolean  true if string is found in the right row, false if not
	 */

	public static boolean searchScreenInRow(String searchstring, String targetrow, TestObject Screen, TopLevelTestObject Frame) {
		
		int row = Integer.parseInt(targetrow);
		String [] screenContentsArray = TerminalLookOps.getScreenTextAsArray(Screen);
		int [] result = StringOps.findStringRows(searchstring, screenContentsArray);
		int numberfound = result.length;

		System.out.println(numberfound+
			" matches to '"
				+ searchstring
				+ "' on screen. ");

		if (numberfound == 0) {
			System.out.println("No row match: no matches to '"+searchstring+"' found on screen.");
			return false;
		}
		
		for (int i = 0; i < numberfound; i++){
			if (result[i] ==  row) {
				System.out.println("Row match: Found '"+searchstring+"' in row "+row);	
				return true;	
			}
			else System.out.println("No row match: searching for '"+searchstring+"' in row "+targetrow+" but occurs in row "+result[i]);
		}
		System.out.println("No row match:  searching for '"+searchstring+"' in row "+targetrow+" but not found.");
		return false;
	}
	
	/**
	 * Searches in the displayed screen for occurrences of the search pattern string.  
	 *      Gathers the rows that match the search string, then checks to see
	 *      if one of the matches is in the target row that the match should be in.
	 * @param search  the string/pattern to search for
	 * @param targetrow  the row the string pattern should be found in
	 * @param Screen  the screen testobject to search in
	 * @param Frame  the top level Terminal Tester object, needed to send keystrokes
	 * @param pattern  true for a Regex search pattern, false for a search string
	 * @return boolean  true if string is found in the right row, false if not
	 */

	public static boolean searchScreenInRow(String search, String targetrow, TestObject Screen, TopLevelTestObject Frame, boolean pattern) {
		
		int row = Integer.parseInt(targetrow);
		String [] screenContentsArray = TerminalLookOps.getScreenTextAsArray(Screen);
		int [] result;
		//search for either a pattern, or a string
		if (pattern)
			result = StringOps.findPatternRows(search, screenContentsArray);
		else
			result = StringOps.findStringRows(search, screenContentsArray);
		int numberfound = result.length;

		System.out.println(numberfound+
			" matches to '"
				+ search
				+ "' on screen. ");

		if (numberfound == 0) {
			System.out.println("No row match: no matches to '"+search+"' found on screen.");
			return false;
		}
		
		for (int i = 0; i < numberfound; i++){
			if (result[i] ==  row) {
				System.out.println("Row match: Found '"+search+"' in row "+row);	
				return true;	
			}
			else System.out.println("No row match: searching for '"+search+"' in row "+targetrow+" but occurs in row "+result[i]);
		}
		System.out.println("No row match:  searching for '"+search+"' in row "+targetrow+" but not found.");
		return false;
	}
	
	
	/**	
	* Description: this class is for separating out RationalTestScript methods and putting them in a format so that the Terminal Ops can be static			
	* 
	*/
	protected static class TerminalHelperMethods extends RationalTestScript 
	{
			
		/**				
		* Waits the specified amount of time. (just a wrapper for the sleep method from RationalTestScript) <p>
		*/
		public void wait(int time) {
			super.sleep(time);
		}
	}
}
