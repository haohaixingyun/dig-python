// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers.targets;

import ibm.loggers.control.RTSLoggingWrapper;

/**
 * Description: this class is used to tell a logger to log to the Results Log which is set in your XDE Preferences (for example, TestManager or HTML)<p>
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class ResultsTarget implements ILogTarget {
	
	/** 	
	* Logs an informational message <br>
	* @param sInfoMessage the text of the message to log
	*/	
	public void logInfo(String sInfoMessage)
	{
		RTSLoggingWrapper.logInfo(sInfoMessage);
	}

	/** 	
	* Logs a warning message <br>
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logWarning(String sWarningMessage)
	{
		RTSLoggingWrapper.logWarning(sWarningMessage);
	}

	/** 	
	* Logs an error message <br>
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logError(String sErrorMessage)
	{
		RTSLoggingWrapper.logError(sErrorMessage);
	}
	
	/** 	
	* Logs a test result<p>
	* @param headline the heading for the test result
	* @param passed whether the test passed or not
	* @param additionalInfo additional info about the test to log (enter null if none)
	*/		
	public void logTestResult(String headline, boolean passed, String additionalInfo)
	{
		RTSLoggingWrapper.logTestResult(headline, passed, additionalInfo);
	}
	
	/**
	 * Determines whether two targets are equivalent.<p>
	 * This is used to prevent a logger from logging twice to the exact same output target,<br>
	 * which would result in the exact same message appearing twice in the log.
	 * 
	 * @param obj the object to compare with this object
	 * @return true if the the object passed in is the same as this object; false otherwise 
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ResultsTarget)
			return true;
		else
			return false;
	}

}
