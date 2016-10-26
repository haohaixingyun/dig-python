// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * This class does text replacements.  For instance, replacing variable names
 * like %myvar% with their  respective values.
 *
 * @author Weissjef
 * @version 2.2
 * Last Modified: 10/25/04
 */
public class Replace
{
    /**
     * This method replaces all occurences of one string with another, in a
     * third source string.
     *
     * @param strSource This is the source string, the one that is searched for
     *        replacements.
     * @param strFrom This is the string that is being searched for and
     *        replaced.
     * @param strTo This is the string that is used as the replacement.
     *
     * @return The original source string with all replacements completed.
     */
    public static String replace(String strSource, String strFrom, String strTo)
    {
        //Debug.print("")
        if ((strSource == null) || strSource.equals(""))
        {
            return "";
        }

        /* TODO example of a TO DO comment!  Shows up in eclipse tasks list.*/
        java.lang.String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;

        if (strTo == null)
        {
            return strSource;
        }

        while ((intPos = strSource.indexOf(strFrom)) != -1)
        {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }

        strDest = strDest + strSource;

        return strDest;
    }

    /**
     * This method performs multiple replacements in the same string.
     *
     * @param ht A hashtable whose keys are the strings to be searched for and
     *        replaced, and whose  values are the replacements strings.
     * @param s The source string to search.
     *
     * @return The original source string with all replacements completed.
     */
    public static String replace(Hashtable ht, String s)
    {
        Enumeration enum1 = ht.keys();
        String newstring = s;

        while (enum1.hasMoreElements())
        {
            String from = (String) enum1.nextElement();
            String to = (String) ht.get(from);
            newstring = replace(newstring, from, to);
        }

        //Debug.print("Old string: " + s + "  New String: "  + newstring);
        return newstring;
    }

    /**
     * Performs multiple replacements in multiple strings.
     *
     * @param ht A hashtable whose keys are the strings to be searched for and
     *        replaced, and whose  values are the replacements strings.
     * @param s The source strings (in an array) to search.
     *
     * @return The original source strings with all replacements completed.
     */
    public static String[] replaceAll(Hashtable ht, String[] s)
    {
        String[] newstring = s;

        for (int i = 0; i < s.length; i++)
        {
            newstring[i] = replace(ht, s[i]);
        }

        return newstring;
    }
}
