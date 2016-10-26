// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import java.awt.datatransfer.*;
import java.awt.*;
import java.util.*;


/**
 * This class can be used to convert a single byte String to it's double byte
 * equivalent, copy a String to the clipboard, or both convert and copy a String to 
 * the clipboard. A user can then use the inputKeys method of XDE tester to
 * paste the double byte string into any field of the AUT.
 * 
 * Example use:
 * 
 *
 * 1. In the script that you want to convert Strings, import the class.
 * and declare an object, eg.
 * 
 * NLSHlpr nlshelper = new NLSHlpr();
 * 
 * 2. Program or record a mouse click in the target edit field and then 
 * program or record entering contol-v (paste) from the keyboard.
 * 
 * 3. In the script editor, enter a blank line between the call to the click 
 * method and the call to the control-v from the inputKeys method.
 * 
 * 4. Add a call to the nlshelper in the blank line. eg.
 *
 *	nlshelper.setDBtoClipboard("any string");
 * 
 * where any string is the text you want converted to double width and pasted 
 * to the clipboard. The call to inputKeys ctl-v will paste it into the field.
 * 
 * eg. The code will look similar to the following:
 * 
 * 	Text_mailSubject().click(atPoint(122,1));
 * 	nlshelper.setDBtoClipboard("any string");
 * 	Browser_htmlBrowser(Document_ComposeMessage(),DEFAULT_FLAGS).inputKeys("^v")
 * 
 * @author Bill Mooney
 * @version 2.2
 * Last Modified: 04/06/04
 */
  public class NLSHlpr {
	
		private Clipboard _clipboard; 

	
	/**
	 * Class constructor.
	 * Gets Windows system clipboard.
	 */
    public NLSHlpr()
	{
			_clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}
	
	/**
	 * Converts inputString to dbl byte and copy's to the clipboard.
	 * 
	 * @param inputString the string to be converted copied to the clipboard.
	 */
	public void setDBtoClipboard(String inputString)
	{
		String dblBString = "";
		dblBString = convertToDoubleByte(inputString);
		copyToClipboard(dblBString);
		
	}
	
	
	/**
	 * Copies inputString to the clipboard.
	 * 
	 * @param inputString the string to be copied to the clipboard.
	 */
	public void copyToClipboard(String inputString)
	{
		
		StringSelection selection = new StringSelection(inputString);
		_clipboard.setContents(selection, null);	
	}
	
	/**
	 * Converts inputString to a double byte equivalent.
	 * 
	 * @param inputString 	the String to be converted.
	 * @return String 		the converted String.
	 */
	public String convertToDoubleByte(String inputString)
	{
		char inChar;
		char dblBChar;
		Vector charVector = new Vector();
		String convertedString = "";
		
		for(int c=0; c<inputString.length(); c++)
		{
			inChar = inputString.charAt(c);
			
			int intVal = (int)inChar;
			if((intVal>96 && intVal<123)||(intVal>64 && intVal<91))
			{
				dblBChar = convertToDoubleByte(inChar);
				convertedString = convertedString + dblBChar;
			}
			else
			{
				convertedString = convertedString + inChar;
			}		
		}
		
		return convertedString;		
	}
	
 
 
	/**
	 * Converts inputChar to it's double byte equivalent.
	 * 
	 * @param inputChar	the char to be converted.
	 * @return char		the converted char.
	 */
	public char convertToDoubleByte(char inputChar)
	{

		int intVal = (int)inputChar;
		int newVal = intVal + 65248;
		char dblBChar = (char)newVal;
		return dblBChar;
		
	}

}
