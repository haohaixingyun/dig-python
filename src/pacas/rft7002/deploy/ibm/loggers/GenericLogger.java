// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers;

import ibm.loggers.control.IPackageLogger;
import ibm.loggers.control.PackageLoggingController;
import ibm.loggers.control.RTSLoggingWrapper;

/**
 * This is a generic logger which logs to both the console 
 * and the log file set in XDE Tester preferences (for example, HTML or TestManager)<br>
 * It is the default logger for package logging.<br>
 * If you want to write a custom logger, the easiest way to do it is to extend this class<p>
 * This class also contains wrapper methods which expose the RationalTestScript logging methods for public access.<p>
 * 
 * Use the logScript* methods to log from your script.<br>
 * The logPackage* methods are used by the package to log messages<br>
 * You can use the setPackageLogLevel method in this class to set the messages you want to receive from the package (e.g. errors only)
 * 
 * (Note that you cannot create different log levels for the package by creating more than one logger with different log levels.
 *  Whichever is the last logger to set the log level will be the log level that the package is set to. 
 *  So if you want to change the log level, just use the setPackageLogLevel method at the point in your script where you want to change the loggin level.)
 * 
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class GenericLogger implements IPackageLogger {
	
	private int giLogLevel = PACKAGELOGLEVEL_ERRORS_ONLY;

//***********************************Constructors****************************************************************

	/**
	 * This constructor creates a generic logger which produces output to the results file.<br>
	 * It also sets the package to use this object for any logging.<p>
	 */
	public GenericLogger()
	{
		PackageLoggingController.setLogger(this);
	}
	
	/**
	 * This constructor creates a generic logger with a specified log level set.<br>
	 * It also sets the package to use this object for any logging.<p>
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param iLogLevel the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public GenericLogger(int iLogLevel)
	{
		setLogLevel(iLogLevel);	
		PackageLoggingController.setLogger(this);
	}
	
//*******************Setters And Getters******************************	
	/**Sets the Log Level for this logger.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * Default is errors only<br>
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param iLogLevel the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public void setLogLevel(int iLogLevel)
	{
		giLogLevel = iLogLevel;
	}
	
	/**
	 * Returns the Log Level that this logger is set to.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * 
	 * @return int: the log level the package is set to
	 */
	public int getLogLevel()
	{
		return giLogLevel;
	}


	
	/**
	 * This formats a text string for logging <br>
	 * This is provided for inheriting classes to make it easier to create custom loggers.<br>
	 * So in this class, it just returns the input string,<br>
	 * but if you create your own logger, you might want to override this method to add e.g a prefix with the date and time.
	 * 
	 * @param sMessage the message to format
	 * @return the modified string to print
	 */		
	protected String formatMessage(String sMessage)
	{
		return sMessage;
	}	
	

//***********************************Package Logging****************************************************************
	
	/** 	
	* Logs an informational message from the package
	* @param sInfoMessage the text of the message to log
	*/	
	public void logPackageInfo(String sInfoMessage)
	{
		sInfoMessage = formatMessage(sInfoMessage);
		
		RTSLoggingWrapper.logInfo(sInfoMessage);
		System.out.println(sInfoMessage);
	}

	/** 	
	* Logs a warning message from the package
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logPackageWarning(String sWarningMessage)
	{
		sWarningMessage = formatMessage(sWarningMessage);
		
		RTSLoggingWrapper.logWarning(sWarningMessage);
		System.err.println(sWarningMessage);		
	}
	
	/** 	
	* Logs an error message from the package 
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logPackageError(String sErrorMessage)
	{
		sErrorMessage = formatMessage(sErrorMessage);
		
		RTSLoggingWrapper.logError(sErrorMessage);
		System.err.println(sErrorMessage);	
	}

//***********************************Script Logging****************************************************************
	/** 	
	* Logs an informational message <br>
	* @param sInfoMessage the text of the message to log
	*/	
	public void logScriptInfo(String sInfoMessage)
	{
		sInfoMessage = formatMessage(sInfoMessage);
		
		RTSLoggingWrapper.logInfo(sInfoMessage);
		System.out.println(sInfoMessage);
	}

	/** 	
	* Logs a warning message <br>
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logScriptWarning(String sWarningMessage)
	{
		sWarningMessage = formatMessage(sWarningMessage);
		
		RTSLoggingWrapper.logWarning(sWarningMessage);
		System.err.println(sWarningMessage);		
	}

	/** 	
	* Logs an error message 
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logScriptError(String sErrorMessage)
	{
		sErrorMessage = formatMessage(sErrorMessage);
		
		RTSLoggingWrapper.logError(sErrorMessage);
		System.err.println(sErrorMessage);	
	}
	
	/** 	
	* Logs a test result<p>
	* @param headline the heading for the test result
	* @param passed whether the test passed or not
	* @param additionalInfo additional info about the test to log (enter null if none)
	*/		
	public void logScriptTestResult(String headline, boolean passed, String additionalInfo)
	{
		additionalInfo = formatMessage(additionalInfo);
		
		RTSLoggingWrapper.logTestResult(headline, passed, additionalInfo);
		String sResultLine = formatMessage(headline + ": " + additionalInfo);
		if (passed)
			System.out.println(sResultLine);
		else
			System.err.println(sResultLine);
	}
	
}
