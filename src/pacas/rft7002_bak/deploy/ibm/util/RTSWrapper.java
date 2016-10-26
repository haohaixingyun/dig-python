// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import com.rational.test.ft.script.IOperatingSystem;
import com.rational.test.ft.script.RationalTestScript;

/**
 * This class merely wraps methods from RationalTestScript, making them publically accessible to other classes in the package.<p>
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 12/08/04
 */
public class RTSWrapper {
	
	/**				
	 * Waits the specified amount of time. 
	 */
	public static void sleep(int time)
	{
		new HelperMethods().sleep(time);
	}
	

	/**				
	 * Gets an IOperatingSystem object of the os the script is running on. <br>
	 * This object can then be used to get more info, like env variables, etc.<br>
	 * See IOperatingSystem in com.rational.test.ft.script.
	 */	
	public static IOperatingSystem getOperatingSystem()
	{
		return new HelperMethods().rtsGetOperatingSystem();
	}
	
	
	
	
	
	
	/**	
	* Description: inner class for exposing RationalTestScript methods			
	* 
	* @author TSnow
	*/
	protected static class HelperMethods extends RationalTestScript 
	{
			
		/**				
		* Waits the specified amount of time. (just a wrapper for the sleep method from RationalTestScript) <p>
		*/
		public void sleep(int time) {
			super.sleep(time);
		}
		
		/**				
		 * Gets an IOperatingSystem object of the os the script is running on. <br>
		 * This object can then be used to get more info, like env variables, etc.<br>
		 * See IOperatingSystem in com.rational.test.ft.script.
		 */	
		public IOperatingSystem rtsGetOperatingSystem()
		{
			return super.getOperatingSystem();
		}		
	}
}
