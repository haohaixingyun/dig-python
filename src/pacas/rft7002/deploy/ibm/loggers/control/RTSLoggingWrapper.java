// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers.control;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.IFtVerificationPoint;

/**
 * This class merely wraps the logging methods from RationalTestScript, making them publically accessible to other loggers.<p>
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 12/08/04
 */
public class RTSLoggingWrapper {
	
	/**Global RTS object for exposing RTS methods without requiring us to inherit RTS, thereby limiting the amount of methods that are shown in type-ahead*/
	protected static LoggingHelperMethods helper = new LoggingHelperMethods();
	
	
//*********************************Wrappers for RTS methods**********************************************
	/** 	
	* This method simply wraps RationalTestScript's logInfo method, making it publically accessible<p>
	* @param sInfoMessage the text of the message to log
	*/	
	public static void logInfo(String sInfoMessage)
	{
		helper.logInfoRTS(sInfoMessage);
	}

	/** 	
	* This method simply wraps RationalTestScript's logWarning method, making it publically accessible<p>
	* @param sWarningMessage the text of the warning message to log
	*/	
	public static void logWarning(String sWarningMessage)
	{
		helper.logWarningRTS(sWarningMessage);
	}

	/** 	
	* This method simply wraps RationalTestScript's logError method, making it publically accessible<p>
	* @param sErrorMessage the text of the error message to log
	*/	
	public static void logError(String sErrorMessage)
	{
		helper.logErrorRTS(sErrorMessage);
	}
	
	/** 	
	* This method simply wraps RationalTestScript's vpManual method, making it publically accessible<p>
	* @param vpName the script-relative verification point, which must be unique
	* @param expected the expected data that is compared to the supplied actual data
	* @param actual the actual data that is compared to the supplied expected data
	* @author Jacob Eisinger
	*/	
	public static IFtVerificationPoint vpManual(String vpName, Object expected, Object actual)
	{
		return helper.vpManualRTS(vpName, expected, actual);
	}
	
	/** 	
	* This method simply wraps RationalTestScript's logTestResult method, making it publically accessible<p>
 	* @param headline the headline describing the test
 	* @param passed the result of the test, where true indicates a pass and false indicates a failure 
 	* @author Jacob Eisinger
	*/	
	public static void logTestResult(String headline, boolean passed)
	{
		helper.logTestResultRTS(headline, passed);
	}

	/** 	
	* This method simply wraps RationalTestScript's logTestResult method, making it publically accessible<p>
 	* @param headline the headline describing the test
 	* @param passed the result of the test, where true indicates a pass and false indicates a failure 
 	* @param additionalInfo additional information about the test
 	* @author Jacob Eisinger
	*/		
	public static void logTestResult(String headline, boolean passed, String additionalInfo)
	{
		helper.logTestResultRTS(headline, passed, additionalInfo);
	}
	
//**************************Inner Class****************************************************************************
	/**	
	* Description: this class is for separating out RationalTestScript methods and putting them in a format so that the Browser Ops can be static			
	* 
	* @author TSnow
	*/
	protected static class LoggingHelperMethods extends RationalTestScript {
		
		protected boolean DEBUG = true;
		
		public LoggingHelperMethods()
		{
			String caller = getTopScriptName();
			if (DEBUG)
				System.out.println(caller);
		
			//set this script name to the testscript which is running when the logger is created, 
			// 		so that logging to the results file has the appropriate info (otherwise prints "script: null" with no line numbers)
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
		* This method simply wraps RationalTestScript's logInfo method, making it publically accessible<p>
		* @param sInfoMessage the text of the message to log
		*/	
		public void logInfoRTS(String sInfoMessage)
		{
			super.logInfo(sInfoMessage);
		}

		/** 	
		* This method simply wraps RationalTestScript's logWarning method, making it publically accessible<p>
		* @param sWarningMessage the text of the warning message to log
		*/	
		public void logWarningRTS(String sWarningMessage)
		{
			super.logWarning(sWarningMessage);
		}

		/** 	
		* This method simply wraps RationalTestScript's logError method, making it publically accessible<p>
		* @param sErrorMessage the text of the error message to log
		*/	
		public void logErrorRTS(String sErrorMessage)
		{
			super.logError(sErrorMessage);
		}
	
		/** 	
		* This method simply wraps RationalTestScript's vpManual method, making it publically accessible<p>
		* @param vpName the script-relative verification point, which must be unique
		* @param expected the expected data that is compared to the supplied actual data
		* @param actual the actual data that is compared to the supplied expected data
		* @author Jacob Eisinger
		*/	
		public IFtVerificationPoint vpManualRTS(String vpName, Object expected, Object actual)
		{
			return super.vpManual(vpName, expected, actual);
		}
	
		/** 	
		* This method simply wraps RationalTestScript's logTestResult method, making it publically accessible<p>
		* @param headline the headline describing the test
 		* @param passed the result of the test, where true indicates a pass and false indicates a failure 
  		* @author Jacob Eisinger
		*/	
		public void logTestResultRTS(String headline, boolean passed)
		{
			super.logTestResult(headline, passed);
		}

		/** 	
		* This method simply wraps RationalTestScript's logTestResult method, making it publically accessible<p>
 		* @param headline the headline describing the test
 		* @param passed the result of the test, where true indicates a pass and false indicates a failure 
 		* @param additionalInfo additional information about the test
 		* @author Jacob Eisinger
		*/		
		public void logTestResultRTS(String headline, boolean passed, String additionalInfo)
		{
			super.logTestResult(headline, passed, additionalInfo);
		}
	}

}
