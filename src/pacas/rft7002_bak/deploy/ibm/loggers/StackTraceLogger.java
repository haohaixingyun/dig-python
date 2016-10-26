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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * Custom logger that does verifications 
 *
 * @author jeisinger\tsnow
 *
 * @version 2.2
 * Last Modified: 12/08/04
 */
public class StackTraceLogger extends TargettedLogger implements IPackageLogger {
	private RTSLoggingWrapper testScript = new RTSLoggingWrapper();
	private String contextPrefix = " *** CONTEXT INFORMATION ***";
	private String	stackPrefix = "Log method called ";
	private String [] unloggablePackagePrefixes =
		new String [] { "java.", "com.rational", "org.apache", StackTraceLogger.class.getName()};
	private int vpNum = 0;
	private int vpNameMaxLength = 30;
	private String lineSeparator = System.getProperty("line.separator");//"\n";	
	
//***********************************Constructors****************************************************************

	/**
	 * This constructor creates a stack trace logger which produces output to the results file.<br>
	 * It also sets the package to use this object for any logging.<p>
	 */
	public StackTraceLogger()
	{
		PackageLoggingController.setLogger(this);
	}
	
	/**
	 * This constructor creates a generic logger with a specified log level set.<br>
	 * It also sets the package to use this object for any logging.<p>
	 * 
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param logLevel the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public StackTraceLogger(int iLogLevel)
	{
		setLogLevel(iLogLevel);	
		PackageLoggingController.setLogger(this);
	}

//***************************Overrides******************************************************

	/** 	
	* Logs an error message from the package
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logPackageError(String sErrorMessage)
	{
		logScriptTestResult(sErrorMessage, false); //this will log the context after logging the result

	}

	/** 	
	* Logs an error message 
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logScriptError(String sErrorMessage)
	{
		logScriptTestResult(sErrorMessage, false); //this will log the context after logging the result

	}
	
	/** 	
	* Logs a test result<p>
	* @param headline the heading for the test result
	* @param passed whether the test passed or not
	*/		
	public void logScriptTestResult(String headline, boolean passed)
	{
		logScriptTestResult(headline, passed, " "); //this will log the context after logging the result
	}
	
	/** 	
	* Logs a test result<p>
	* @param headline the heading for the test result
	* @param passed whether the test passed or not
	* @param additionalInfo additional info about the test to log (enter null if none)
	*/		
	public void logScriptTestResult(String headline, boolean passed, String additionalInfo)
	{
		additionalInfo = additionalInfo + lineSeparator + getContext();
		
		super.logScriptTestResult(headline, passed, additionalInfo);
	}

//DDDDDDDDDDDDDDDDDDD verification point methods DDDDDDDDDDDDDDDDDDDDDDD
	/**
	 * Compares two objects using a manual verification point.<p>
	 * Call performVP() if you want the actual and expected values being compared saved to a file (under _logs) in addition
	 *    to outputting to the log the result of comparing the 2 things. 
	 * See com.rational.test.ft.vp.IFtVerificationPoint#performTest for more details.
	 */
	public boolean performVP(String vpName, Object expected, Object actual, boolean compareTrueEqualsPass)
	{
		boolean pass = RTSLoggingWrapper.vpManual(generateVPName(vpName), expected, actual).performTest(compareTrueEqualsPass);
		String oldLineSeparator = lineSeparator;
		lineSeparator = "<br>";	//assume they are using HTML? This is bad, fix this. tbs	
		RTSLoggingWrapper.logInfo("The context of the above is: " + lineSeparator + getContext());
		lineSeparator = oldLineSeparator;
		return pass;
	}
	/**
	 * Compares two objects using a manual verification point.<p>
	 * Call performVP() if you want the actual and expected values being compared saved to a file (under _logs) in addition
	 *    to outputting to the log the result of comparing the 2 things. 
	 * See com.rational.test.ft.vp.IFtVerificationPoint#performTest for more details.
	 */	
	public boolean performVP(String vpName, Object expected, Object actual)
	{
		return performVP(vpName, expected, actual, true);
	}

	/**
	 * Compares two objects using a manual verification point.<p>
	 * Call compareVP() if you just want to know how the 2 things compare and don't want to log anything.
	 * See com.rational.test.ft.vp.IFtVerificationPoint#compare for more details.
	 */	
	public boolean compareVP(String vpName, Object expected, Object actual)
	{
		return RTSLoggingWrapper.vpManual(generateVPName(vpName), expected, actual).compare();
	}

	/**
	 * Compares two objects using a manual verification point.<p>
	 * Call compareAndLogVP() if you want to output to the log the result of comparing the 2 things (passed/failed and results).
	 * See com.rational.test.ft.vp.IFtVerificationPoint#compareAndLog for more details.
	 */
	public boolean compareAndLogVP(String vpName, Object expected, Object actual, boolean compareTrueEqualsPass)
	{
		boolean pass = RTSLoggingWrapper.vpManual(generateVPName(vpName), expected, actual).compareAndLog(compareTrueEqualsPass);
				String oldLineSeparator = lineSeparator;
		lineSeparator = "<br>";		
		RTSLoggingWrapper.logInfo("The context of the above is: " + lineSeparator + getContext());
		lineSeparator = oldLineSeparator; //assume they are using HTML? This is bad, fix this. tbs
		return pass;
	}

	/**
	 * Compares two objects using a manual verification point.<p>
	 * Call compareAndLogVP() if you want to output to the log the result of comparing the 2 things (passed/failed and results).
	 * See com.rational.test.ft.vp.IFtVerificationPoint#compareAndLog for more details.
	 */	
	public boolean compareAndLogVP(String vpName, Object expected, Object actual)
	{
		return compareAndLogVP(vpName, expected, actual, true);
	}

//DDDDDDDDDDDDDDDDDDDDDDDDD context log DDDDDDDDDDDDDDDDDDDDDDDDDDDDD
	
	/**
	 * returns the context of the call - where the call was made
	 * @return the stack trace indicating where the call was made
	 */
	public String getContext()
	{
				
		return lineSeparator + "\t" + contextPrefix + lineSeparator + "\t" + getBriefStackTrace(getStackTrace()) + lineSeparator;
	}

//DDDDDDDDDDDDDDDDDDDDDD private context utilities DDDDDDDDDDDDDDDDDDDD
	/**
	 * returns a string of the stack trace
	 * @return the stack trace as represented by an exception
	 */
	private String getStackTrace()
	{
		Exception e= new Exception();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(outputStream));
		return outputStream.toString();
	}
	
	/**
	 * gets a brief stack trace for easier debugging 
	 * @return the stack trace with all system/java/rational calls stripped out.
	 */
	private String getBriefStackTrace(String fullTrace)
	{
		//sample stack trace
//		java.lang.Exception
//		at LogUtility.getStackTrace(LogUtility.java:46)
//		at LogUtility.logContext(LogUtility.java:39)
//		at LogUtility.logWarning(LogUtility.java:24)
//		at ParentScript.testMain(ParentScript.java:29)
//		at java.lang.reflect.Method.invoke(Native Method)
//		at com.rational.test.ft.sys.FtReflection.invokeMethod2Ext(Unknown Source)
//		at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)
//		at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)
//		at com.rational.test.ft.script.RationalTestScript.runMain(Unknown Source)
//		at java.lang.reflect.Method.invoke(Native Method)
//		at com.rational.test.ft.sys.FtReflection.invokeMethod2Ext(Unknown Source)
//		at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)
//		at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)
//		at com.rational.test.ft.application.ScriptPlayback.runScript(Unknown Source)
//		at com.rational.test.ft.application.ScriptPlayback.run(Unknown Source)
//		at com.rational.test.ft.rational_ft.run(Unknown Source)
//		at com.rational.test.ft.rational_ft.main(Unknown Source)
		
		String	briefTrace, currentCall, currentPackage;
				
		StringTokenizer tokens = new StringTokenizer(fullTrace, "\n");
		
		//remove the calls put on by the logger
		tokens.nextToken();		//get rid of the exception

		//add the prefix
		briefTrace = stackPrefix;
		
		//get the rest of the stack
		while (tokens.hasMoreTokens())
		{
			currentCall = tokens.nextToken();
			currentPackage = getCallLocation(currentCall);
			
			if (isPackageLoggable(currentPackage))
				briefTrace += currentCall + lineSeparator;
			//else move on to next token					
		}
		
		return briefTrace;
	}
	
	/**
	 * this method determines if the package in that stack trace should be logged
	 * @param packageName the full package name to the method
	 * @return true iff the line should be logged
	 */
	private boolean isPackageLoggable(String packageName)
	{
		for (int i = 0; i < unloggablePackagePrefixes.length; ++i)
		{
			if (packageName.startsWith(unloggablePackagePrefixes[i]))
				return false;
		}
		
		//else it doesn't match - we are in the clear
		return true;		
	}
	
	/**
	 * gets the package name of the exception
	 * @param stackTraceLine a line in a stack trace in this format:
	 * ASSUMPTION: line format is " at " + fullPackageName + "(" + fileName + ":" + lineNumber + ")"
	 * \tat \<package name\>(\<location\>)		
	 * @return the package
	 */
	private String getCallLocation(String stackTraceLine)
	{
		int packageBeginIdx, packageEndIdx;
		
		packageBeginIdx =stackTraceLine.indexOf(" ") + 1;
		packageEndIdx = stackTraceLine.indexOf("(");
		
		return stackTraceLine.substring(packageBeginIdx, packageEndIdx);
	}

//DDDDDDDDDDDDDDDDDDDDDDDDDDD private verification point DDDDDDDDDDDDDDDDDDDDDDDD

	/**
	 * create a Verification Point name that is valid:
	 * the VP name must be less than 30 characters, and must
	 * contain only ascii letters.  All other characters will
	 * be removed
	 * @param text the text that will be morphed into a VP name
	 * @return the _valid_ Verification Point name
	 */
	/*----------------------------------------------------------------
	 * Idea/structure of code taken from Kathy Endre's team
	 * highly modified by Jacob
	*---------------------------------------------------------------*/
	public String generateVPName(String text)
	{
		// NOTE: the VP name must be less than 30 characters with no unprintable characters
		String name = "";
		vpNum++;			//update the vp count
		//create the suffix
		String suffix = "VP" + vpNum;
		
		//add only letters or digits to the vp name
		int i = 0;
		while ((i < text.length()) &&
				(name.length() < (vpNameMaxLength - suffix.length())))
		{
			//note, this code might not work... it allows letters
			//that are not basic ascii letters - no docs saying
			//what is allowed for the name of vps in
			//NEED VERIFICATION
			//Rational API doc.
			if (name.length() >= 1)	//if started
			{
				if (Character.isJavaIdentifierPart(text.charAt(i)))
				{
					name += text.charAt(i);
				}
			}
			else
			{
				
				if (Character.isJavaIdentifierStart(text.charAt(i)))
				{
					name += text.charAt(i);
				}
			}

			i++;
		}
		
		//return the vpName
		return name + suffix;
	}
}