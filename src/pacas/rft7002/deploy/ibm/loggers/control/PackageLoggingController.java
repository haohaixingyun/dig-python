// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers.control;

import ibm.loggers.*;

/**
 * This class is used to set the Logger and LogLevel to be used for the ibm package.<br> 
 * By default, the Logger is set to use a GenericLogger and the LogLevel is set to LOGLEVEL_ERRORS_ONLY.<p> 
 * 
 * To create your own logger for use by this package, implement the IPackageLogger interface,<br>
 * and then set the logger for the package to an instance of your class by passing it into PackageLoggingController.setLogger.<br>
 * You can then set the LogLevel for the package by passing in one of the LogLevel constants in this class (inherited from IPackageLoggerConstants) into PackageLoggingController.setLogLevel()
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class PackageLoggingController implements IPackageLoggerConstants {
	private static IPackageLogger goLogger = new GenericLogger();

	/**Sets the logger to be used by the ibm package.<p>
	 * The logger must implement the IPackageLogger interface.
	 * 
	 * @param logger the logger to be used by the ibm package
	 */	
	public static void setLogger(IPackageLogger logger)
	{
		goLogger = logger;
	}
	
	/**
	 * Returns the logger which the ibm package is set to use.<p>
	 * 
	 * @return the logger the ibm package is using
	 */	
	public static IPackageLogger getLogger()
	{
		return goLogger;
	}

	/**
	 * This method calls logPackageInfo on the logger contained within this class if iMessageLevel is at or above the Log Level threshold set in this class<p>
	 * In other words, the message will be logged if the iMessageLevel passed in is greater than or equal to the package logging level<br>
	 * The package logging level is ordered with NONE is greater than ERRORS_ONLY is greater than ERRORS_WARNINGS, etc. <br>
	 * <p>
	 * 
	 * This method is intended for use by the ibm package, so that package class writers need not check the Package Log Level before logging, <br>
	 * but can just send their message, plus the level indicating at which point the message should be printed<br>
	 * @param iMessageLevel the importance of message
	 * @param sMessage the text of the message to be logged
	 */
	public static void logPackageInfo(int iMessageLevel, String sMessage)
	{
		if (goLogger != null)
			if (iMessageLevel >= goLogger.getLogLevel())
				goLogger.logPackageInfo(sMessage);
	}

	/**
	 * This method calls logPackageWarning on the logger contained within this class if iMessageLevel is at or above the Log Level threshold set in this class<p>
	 * In other words, the message will be logged if the iMessageLevel passed in is greater than or equal to the package logging level<br>
	 * The package logging level is ordered with NONE is greater than ERRORS_ONLY is greater than ERRORS_WARNINGS, etc. <br>
	 * <p>
	 * 
	 * This method is intended for use by the ibm package, so that package class writers need not check the Package Log Level before logging, <br>
	 * but can just send their message, plus the level indicating at which point the message should be printed<br>
	 * @param iMessageLevel the importance of message
	 * @param sMessage the text of the message to be logged
	 */	
	public static void logPackageWarning(int iMessageLevel, String sMessage)
	{
		if (goLogger != null)
			if (iMessageLevel >= goLogger.getLogLevel())
				goLogger.logPackageWarning(sMessage);
	}

	/**
	 * This method calls logPackageError on the logger contained within this class if iMessageLevel is at or above the Log Level threshold set in this class<p>
	 * In other words, the message will be logged if the iMessageLevel passed in is greater than or equal to the package logging level<br>
	 * The package logging level is ordered with NONE is greater than ERRORS_ONLY is greater than ERRORS_WARNINGS, etc. <br>
	 * <p>
	 * 
	 * This method is intended for use by the ibm package, so that package class writers need not check the Package Log Level before logging, <br>
	 * but can just send their message, plus the level indicating at which point the message should be printed<br>
	 * @param iMessageLevel the importance of message
	 * @param sMessage the text of the message to be logged
	 */	
	public static void logPackageError(int iMessageLevel, String sMessage)
	{
		if (goLogger != null)
			if (iMessageLevel >= goLogger.getLogLevel())
				goLogger.logPackageError(sMessage);
	}
		

}
