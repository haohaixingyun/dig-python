// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers.control;

/**
 * Interface to guarantee certain methods are in each logger.<br> 
 * If you create a custom logger, implement this interface.<p>
 *
 * @author tsnow
 * 
 * @version 2.2
 * Last Modified: 04/07/04
 */
public interface IPackageLogger extends IPackageLoggerConstants {

	
//*******************************Setters And Getters******************************	
	/**Sets the Log Level for this logger.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param logLevel the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public void setLogLevel(int logLevel);
	
	/**
	 * Returns the Log Level that this logger is set to.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * 
	 * @return int: the log level the package is set to
	 */
	public int getLogLevel();	
	

//***********************************Package Logging****************************************************************
	
	/** 	
	* Logs an informational message from the package
	* @param sInfoMessage the text of the message to log
	*/	
	public void logPackageInfo(String sInfoMessage);

	/** 	
	* Logs a warning message from the package
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logPackageWarning(String sWarningMessage);
	
	/** 	
	* Logs an error message from the package 
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logPackageError(String sErrorMessage);
	

		
}
