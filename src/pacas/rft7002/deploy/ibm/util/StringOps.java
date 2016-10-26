// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import com.rational.test.ft.vp.IFtVerificationPoint;
import com.rational.test.util.regex.Regex;
import ibm.util.ItclSuperHelper;
import java.util.Vector;
import com.rational.test.util.regex.Regex;


/**
 * Description: Performs string operations
 * 
 * @author Chris Carlson
 * 
 * @version 2.2
 * Last Modified: 09/10/04 
 */
public class StringOps extends ItclSuperHelper {

    //******************************************************************************************************
    /**
    * Returns the result of a text check on a string-- checks for strings being equal; VP performed
    * @param firstString string being checked in
    * @param secondString pattern being checked for
    * @param vpName Verification point name
    * @return true if strings equal, false if not
    * @author Chris Carlson  
    * */
    //******************************************************************************************************

    public boolean doStringsMatchVp(String firstString, String secondString, String vpName) {
        boolean actual = firstString.equals(secondString);
        return performTest(vpName, true, actual);

    }

    //******************************************************************************************************
    /**
    * Returns the result of a text check on a string-- checks for strings being equal;  no VP performed
    * @param firstString string being checked in
    * @param secondString pattern being checked for
    * @return true if strings equal, false if not
    * @author Chris Carlson  
    * */
    //******************************************************************************************************

    public static boolean doStringsMatch(String firstString, String secondString) {
        return firstString.equals(secondString);
    }

    //******************************************************************************************************
    /**
    * Returns the result of a text check on a string- checks for pattern occurring in a string; VP performed
    * @param searchString string being checked in
    * @param subString pattern being checked for
    * @param vpName Verification point name
    * @return Returns boolean - returns true if present, false if not
    * @author Chris Carlson  
    * */
    //******************************************************************************************************

    public boolean isSubstringVp(String searchString, String subString, String vpName) {
        boolean actual = isSubstring(searchString, subString);
        return performTest(vpName, true, actual);
    }

    //******************************************************************************************************
    /**
    * Returns the result of a text check on a string- checks for pattern occurring in a string; no VP perf.
    * @param searchString string being checked in
    * @param subString pattern being checked for
    * @return true if present, false if not
    * @author Chris Carlson  
    * */
    //******************************************************************************************************

    public static boolean isSubstring(String searchString, String substring) {
        Regex r = new Regex(substring);
        return r.matches(searchString);
    }

    //******************************************************************************************************
    /**
    * Returns the string with the space characters "fixed"-- this was coded for difficulties with weird spaces
    *     screwing up string matching
    * @param s string being fixed
    * @return string with fixed space characters
    * @author Travis Grigsby  
    * */
    //******************************************************************************************************

    public static String fixString(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (((int) s.charAt(i)) == 160) {
                t += (char) 32;
            }
            else {
                t += s.charAt(i);
            }
        }
        return t;
    }

    //******************************************************************************************************
    /**
    * Prints the character values for 2 strings
    * @param s1 1st string
    * @param s2 2nd string
    * @author Travis Grigsby  
    * */
    //******************************************************************************************************

    public static void printCharVals(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            System.out.print("'" + s1.charAt(i) + "' == '" + (int) s1.charAt(i) + "'");
            System.out.print(":");
            System.out.println("'" + (int) s2.charAt(i) + "' == '" + s2.charAt(i) + "'");
        }
    }

	/**
	 * Searches the input array for an occurrence of the search pattern
	 * @param pattern  the string pattern to search for
	 * @param screenContents  the String array to search in
	 * @return boolean, true if found, false if not found
	 */
	public static boolean findPattern (String pattern, String[] screenContents)
	{
		boolean found = false;
		Regex re = new Regex(pattern);
		//search for a pattern in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 
				if (re.matches(screenContents[i]))
					return true;
			}
		}
		return found;
	}

	/**
	 * Searches the input array for an occurrence of the search pattern
	 * @param pattern  the string pattern to search for
	 * @param screenContents  the String to search in
	 * @return boolean, true if found, false if not found
	 */
	public static boolean findPattern (String pattern, String screenContents)
	{
		if (pattern == null || screenContents == null)
			return false;
		return new Regex(pattern).matches(screenContents);
	}

	/**
	 * Searches the input array for an occurrence of the search pattern
	 * @param pattern  the string pattern to search for
	 * @param screenContents  the String array to search in
	 * @return int  index of the first occurrence of the search pattern, or -1
	 */
	public static int findPatternRow (String pattern, String[] screenContents)
	{
		int row = -1;
		Regex re = new Regex(pattern);
		//search for a pattern in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 			
				if (re.matches(screenContents[i]))
					return i;
			}
		}
		return row;
	}

	/**
	 * Searches the input array for an occurrence of the search pattern
	 * @param pattern  the string pattern to search for
	 * @param fromIndex  the array index from which to start the search
	 * @param screenContents  the String array to search in
	 * @return int  index of the first occurrence of the search pattern after the index
	 */
	public static int findPatternRow (String pattern, int fromIndex, String[] screenContents)
	{
		int row = -1;
		Regex re = new Regex(pattern);
		//search for a pattern in the screen contents
		for (int i = fromIndex; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 
				if (re.matches(screenContents[i]))
					return i;
			}
		}
		return row;
	}

	/**
	 * Searches the input array for occurrences of the search pattern
	 * @param pattern  the string pattern to search for
	 * @param screenContents  the String array to search in
	 * @return int [] containing the indexes where the search pattern was found
	 */
	public static int [] findPatternRows (String pattern, String[] screenContents)
	{
		Vector v = new Vector();
		Regex re = new Regex(pattern);
		//search for a string in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 			
				if (re.matches(screenContents[i]))
					v.add(new java.lang.Integer(i));
			}
		}
		//creates the array of indexes found with the pattern
		Object [] objs = v.toArray();
		int [] rows = new int [objs.length];
		for (int i = 0; i < objs.length; ++i)
			rows[i] = ((java.lang.Integer) objs[i]).intValue();
		return rows;
	}

	/**
	 * Searches the input array for an occurrence of the search string
	 * @param string  the substring to search for
	 * @param screenContents  the String array to search in
	 * @return boolean, true if found, false if not found
	 */
	public static boolean findString (String string, String[] screenContents)
	{
		boolean found = false;
		//search for a string in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 			
				if (screenContents[i].indexOf(string) != -1)
					return true;
			}
		}
		return found;
	}

	/**
	 * Searches the input array for an occurrence of the search string
	 * @param string  the substring to search for
	 * @param screenContents  the String to search in
	 * @return boolean, true if found, false if not found
	 */
	public static boolean findString (String string, String screenContents)
	{
		if (string == null || screenContents == null)
			return false;

		if (screenContents.indexOf(string) != -1)
				return true;

		else return false;
	}



	/**
	 * Searches the input array for an occurrence of the search string
	 * @param string  the substring to search for
	 * @param screenContents  the String array to search in
	 * @return int  index of the first occurrence of the search string
	 */
	public static int findStringRow (String string, String[] screenContents)
	{
		int row = -1;
		//search for a string in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 
				if (screenContents[i].indexOf(string) != -1)
					return i;
			}
		}
		return row;
	}

	/**
	 * Searches the input array for an occurrence of the search string after the index
	 * @param string  the substring to search for
	 * @param fromIndex  the array index from which to start the search
	 * @param screenContents  the String array to search in
	 * @return int  index of the first occurrence of the search string after the index
	 */
	public static int findStringRow (String string, int fromIndex, String[] screenContents)
	{
		int row = -1;
		//search for a string in the screen contents
		for (int i = fromIndex; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 
				if (screenContents[i].indexOf(string) != -1)
					return i;
			}
		}
		return row;
	}

	/**
	 * Searches the input array for occurrences of the search string
	 * @param string  the substring to search for
	 * @param screenContents  the String array to search in
	 * @return int [] containing the indexes where the search string was found
	 */
	public static int [] findStringRows (String string, String[] screenContents)
	{
		Vector v = new Vector();
		//search for a string in the screen contents
		for (int i = 0; i < screenContents.length; ++i)
		{
			if (screenContents[i] != null)  { 			
				if (screenContents[i].indexOf(string) != -1)
					v.add(new java.lang.Integer(i));
			}
		}
		//creates the array of indexes found with the string
		Object [] objs = v.toArray();
		int [] rows = new int [objs.length];
		for (int i = 0; i < objs.length; ++i)   {
			rows[i] = ((java.lang.Integer) objs[i]).intValue();
		}
		return rows;
	}



}
