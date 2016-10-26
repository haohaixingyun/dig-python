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
import ibm.loggers.targets.ConsoleTarget;
import ibm.loggers.targets.ILogTarget;
import ibm.loggers.targets.ResultsTarget;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This is a logger with which you can direct where you want to send the log output.<br>
 * By default, all the logging methods except logPackageInfo send output to both the console and the results file,
 * and logPackageInfo sends output to the console only.<p>
 * 
 * You can change this default behavoir by "appending" targets to any particular type of output.<br>
 * For example, you can set the logPackageInfo output to got to a file by creating a FileTarget object<br>
 *  and then using the appendPackageInfoTarget method to attach this additional output target to the class.<br>
 * like so:
 * 	<code><dir>	TargettedLogger log = new TargettedLogger(GenericLogger.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS); 
 * 			appendPackageInfoTarget(new FileTarget("c:\\temp\\log.txt"));</code></dir><br>
 * You can also use appendTargetToAll and appendTargetToAllExceptPackageInfo to append targets to the different logging methods in batch.<br>
 * Finally, if you want to clear the targets that have been added, use either the specific clear methods to clear a particular logging method of its targets or do them all at once with clearAllTargets().<p>
 * 
 * Note that the logPackageInfo messages will now be sent to both the console and the file specified. The targets are additive in this sense. <br>
 * (However, the class protects you from adding two of the same targets to any one output; e.g. even if you append two console objects to a particular logging method, it will only print a message once to the console.)
 * 
 * 
 * As with the GenericLogger from which this class inherits:<br>
 * Use the logScript* methods to log from your script.<br>
 * The logPackage* methods are used by the package to log messages<br>
 * You can use the setPackageLogLevel method in this class to set the messages you want to receive from the package (e.g. errors only)
 * 
 * (Note that you cannot create different log levels for the package by creating more than one logger with different log levels.
 *  Whichever is the last logger to set the log level will be the log level that the package is set to. 
 *  So if you want to change the log level, just use the setPackageLogLevel method at the point in your script where you want to change the logging level.)
 * 
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/07/04
 */
public class TargettedLogger extends GenericLogger implements IPackageLogger {
	
	private LinkedList packageInfoTargets = new LinkedList();
	private LinkedList packageWarningTargets = new LinkedList();
	private LinkedList packageErrorTargets = new LinkedList();

	private LinkedList scriptInfoTargets = new LinkedList();
	private LinkedList scriptWarningTargets = new LinkedList();
	private LinkedList scriptErrorTargets = new LinkedList();

//***********************************Constructors****************************************************************

	/**
	 * This constructor creates a targetted logger which produces output to the results file.<br>
	 * It also sets the package to use this object for any logging.<p>
	 * 
	 * The default targets are the console and results file for all logging except logPackageInfo which is set to the console only.
	 */
	public TargettedLogger()
	{
		setToDefaultTargets();
		PackageLoggingController.setLogger(this);
	}
	
	/**
	 * This constructor creates a targetted logger with a specified log level set.<br>
	 * It also sets the package to use this object for any logging.<p>
	 * The default targets are the console and results file for all logging except logPackageInfo which is set to the console only.<p>
	 *
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param iLogLevel the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public TargettedLogger(int iLogLevel)
	{
		setToDefaultTargets();
		setLogLevel(iLogLevel);	
		PackageLoggingController.setLogger(this);
	}
	
//*********************Append target methods************************
	/**
	 * Appends a target to which the logPackageInfo method will log output.<br>
	 * By default, the logPackageInfo method is set to log to the Console only.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */
	public void appendPackageInfoTarget(ILogTarget target)
	{
		//make sure don't get duplicate targets so don't get multiple printouts of the same message to the same log
		if (packageInfoTargets.indexOf(target) != -1)
				return;
		packageInfoTargets.add(target);
	}

	/**
	 * Appends a target to which the logPackageWarning method will log output.<br>
	 * By default, the logPackageWarning method is set to log to the Console and the Results File.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */
	public void appendPackageWarningTarget(ILogTarget target)
	{
		packageWarningTargets.add(target);
	}

	/**
	 * Appends a target to which the logPackageError method will log output.<br>
	 * By default, the logPackageError method is set to log to the Console and the Results File.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */	
	public void appendPackageErrorTarget(ILogTarget target)
	{
		packageErrorTargets.add(target);
	}	
	
	/**
	 * Appends a target to which the logScriptInfo method will log output.<br>
	 * By default, the logScriptInfo method is set to log to the Console and the Results File.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */	
	public void appendScriptInfoTarget(ILogTarget target)
	{
		scriptInfoTargets.add(target);
	}	

	/**
	 * Appends a target to which the logScriptWarning method will log output.<br>
	 * By default, the logScriptWarning method is set to log to the Console and the Results File.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */	
	public void appendScriptWarningTarget(ILogTarget target)
	{
		scriptWarningTargets.add(target);
	}	
	
	/**
	 * Appends a target to which the logScriptError method will log output.<br>
	 * By default, the logScriptError method is set to log to the Console and the Results File.<p>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */
	public void appendScriptErrorTarget(ILogTarget target)
	{
		scriptErrorTargets.add(target);
	}	

	/**
	 * Appends a target to all the logging methods<br>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */	
	public void appendTargetToAll(ILogTarget target)
	{
		appendPackageInfoTarget(target);
		appendTargetToAllExceptPackageInfo(target);		
	}

	/**
	 * Appends a target to all the logging methods except logPackageInfo<br>
	 * (Note that it will not append more than one instance of the same target, so that you cannot accidently cause it to write the same message multiple times to the same output target.)<p>
	 * 
	 * @param target the target to append
	 */		
	public void appendTargetToAllExceptPackageInfo(ILogTarget target)
	{
		appendPackageWarningTarget(target);
		appendPackageErrorTarget(target);
		
		appendScriptInfoTarget(target);
		appendScriptWarningTarget(target);
		appendScriptErrorTarget(target);		

	}

	/**
	 * Clears all the targets from the logPackageInfo method<br>
	 * (meaning the logPackageInfo method will not log any output until you append a new target to the method.)
	 */	
	public void clearPackageInfoTargets()
	{
		packageInfoTargets.clear();
	}

	/**
	 * Clears all the targets from the logPackageWarning method<br>
	 * (meaning the logPackageWarning method will not log any output until you append a new target to the method.)
	 */		
	public void clearPackageWarningTargets()
	{
		packageWarningTargets.clear();
	}

	/**
	 * Clears all the targets from the logPackageError method<br>
	 * (meaning the logPackageError method will not log any output until you append a new target to the method.)
	 */	
	public void clearPackageErrorTargets()
	{
		packageErrorTargets.clear();
	}	
	
	/**
	 * Clears all the targets from the logScriptInfo method<br>
	 * (meaning the logScriptInfo method will not log any output until you append a new target to the method.)
	 */
	public void clearScriptInfoTargets()
	{
		scriptInfoTargets.clear();
	}	
	
	/**
	 * Clears all the targets from the logScriptWarning method<br>
	 * (meaning the logScriptWarning method will not log any output until you append a new target to the method.)
	 */
	public void clearScriptWarningTargets()
	{
		scriptWarningTargets.clear();
	}	
	
	/**
	 * Clears all the targets from the logScriptError method<br>
	 * (meaning the logScriptError method will not log any output until you append a new target to the method.)
	 */
	public void clearScriptErrorTargets()
	{
		scriptErrorTargets.clear();
	}	

	/**
	 * Clears all the targets from all the logging methods<br>
	 * 
	 */		
	public void clearAllTargets()
	{
		clearPackageInfoTargets();
		clearPackageWarningTargets();
		clearPackageErrorTargets();
		
		clearScriptInfoTargets();
		clearScriptWarningTargets();
		clearScriptErrorTargets();
	}

	/**
	 * Sets the logging methods to their default targets<br>
	 * which are: <br>
	 * All the logging methods except logPackageInfo send output to both the console and the results file,<br>
	 * and logPackageInfo sends output to the console only.
	 */			
	public void setToDefaultTargets()
	{
		clearAllTargets();
		
		//For package info, log only to the Console to avoid verbose output
		ILogTarget console = new ConsoleTarget();
		appendPackageInfoTarget(console);
		
		//For the rest of the methods, log to both the Console and the Results File 
		appendTargetToAllExceptPackageInfo(console);
		appendTargetToAllExceptPackageInfo(new ResultsTarget()); 
	}	


//***********************************Package Logging****************************************************************
	
	/** 	
	* Logs an informational message from the package to the targets specified in this class
	* @param sInfoMessage the text of the message to log
	*/	
	public void logPackageInfo(String sInfoMessage)
	{
		sInfoMessage = formatMessage(sInfoMessage);
		ListIterator iterator = packageInfoTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logInfo(sInfoMessage);
		}
	}

	/** 	
	* Logs a warning message from the package to the targets specified in this class
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logPackageWarning(String sWarningMessage)
	{
		sWarningMessage = formatMessage(sWarningMessage);
		ListIterator iterator = packageWarningTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logWarning(sWarningMessage);
		}	
	}
	
	/** 	
	* Logs an error message from the package to the targets specified in this class
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logPackageError(String sErrorMessage)
	{
		sErrorMessage = formatMessage(sErrorMessage);
		ListIterator iterator = packageErrorTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logError(sErrorMessage);
		}
	}

//***********************************Script Logging****************************************************************
	/** 	
	* Logs an informational message  to the targets specified in this class <br>
	* @param sInfoMessage the text of the message to log
	*/	
	public void logScriptInfo(String sInfoMessage)
	{
		sInfoMessage = formatMessage(sInfoMessage);
		ListIterator iterator = scriptInfoTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logInfo(sInfoMessage);
		}
	}

	/** 	
	* Logs a warning message to the targets specified in this class <br>
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logScriptWarning(String sWarningMessage)
	{
		sWarningMessage = formatMessage(sWarningMessage);
		ListIterator iterator = scriptWarningTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logWarning(sWarningMessage);
		}
	}

	/** 	
	* Logs an error message to the targets specified in this class
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logScriptError(String sErrorMessage)
	{
		sErrorMessage = formatMessage(sErrorMessage);
		ListIterator iterator = scriptErrorTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			target.logError(sErrorMessage);
		}
	}
	
	/** 	
	* Logs a test result to the targets specified in this class<p>
	* @param headline the heading for the test result
	* @param passed whether the test passed or not
	* @param additionalInfo additional info about the test to log (enter null if none)
	*/		
	public void logScriptTestResult(String headline, boolean passed, String additionalInfo)
	{
		ListIterator iterator = scriptInfoTargets.listIterator();
		while (iterator.hasNext())
		{
			ILogTarget target = (ILogTarget)iterator.next();
			if (target instanceof ResultsTarget)
				((ResultsTarget)target).logTestResult(headline, passed, formatMessage(additionalInfo));
			else
			{
				if (passed)
					target.logInfo(formatMessage("Test Passed: " + headline + ": " + additionalInfo));
				else
					target.logError(formatMessage("Test Failed: " + headline + ": " + additionalInfo));
			}
		}	
	}
	
}
