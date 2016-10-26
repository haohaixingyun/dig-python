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
import ibm.recovery.control.PackageRecoveryController;
import ibm.util.BitmapOps;
import ibm.util.BrowserOps;
import ibm.util.FileOps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.ScriptUtilities;

/**
 * Description   : Performs logging operations, writing actions simulataneously to a text file, 
 * the system console and to the default XDE Tester log. The logged test results can then be analyzed
 * using the ResultBrowser java tool which graphically displays all scripts and testcases that were executed.
 * It displays passes in green and fails in red for easy identification. In order to implement the VisualReporter
 * logging you must instantiate the VisualReporter class in your test script and add onInitialize() and onTerminate() methods before
 * the testMain() portion of your script. Also, all testcases must be prefaced by the method logTestcaseInfo(). All error
 * handling should be done by the method errorHandler(). See the following test script example: <p>
 * 
 * <code>
 * public class TestLogger extends TestLoggerHelper<BR>
		{<BR>
	<BR>
		//1. Instantiate the VisualReporter logger<BR>
		VisualReporter log = new VisualReporter();<BR>
	<BR>
	<BR>
		//2. Add your testcases here<BR>
		public void MyTestCase()<BR>
		{<BR>
			//Insert a testcase info header before each testcase in the script<BR>
			log.logTestCaseInfo("This is a testcase to test Logging defaults");<BR>
    	<BR>    
			//actual test actions here<BR>
			BrowserOps.closeAllBrowsers();<BR>
			startBrowser("www.yahoo.com");<BR>
			BrowserOps.waitForReady(Browser_htmlBrowser());<BR>
			new WTextField(Text_p()).setText("W.V.O. Quine");<BR>
			new WButton(Button_YahooSearchsubmit()).click();<BR>	 
		}<BR>
	<BR>
	<BR>
	//3. Example testcase for the error handler<BR>
	public void MyErrExampleCase()<BR>
		{<BR>
			//Insert a testcase info header before each testcase in the script<BR>
			log.logTestCaseInfo("Test the error handler");<BR>
			<BR>	
			//insert an error here to test error handling    <BR>
			log.errorHandler("FAIL - This is an error");<BR>
		}<BR>	
	<BR>
	<BR>
	
	
	//4. initializes test script information and sets global variables<BR>
	public void onInitialize()<BR>
	{<BR>
		//Enter Script Information here<BR>
		log.gsScriptAuthor = "Tony Venditti";<BR>
		log.gsScriptDescription = "Test Logging stuff";<BR>
		log.gsScriptTestArea = "Logging";<BR>
		<BR>
		//Setup automation variables and initialize test script<BR>
		log.onInitialize();	<BR>
	}<BR>
	<BR>
	<BR>
	//5. cleans up after script execution, captures and displays test result metrics <BR>
	public void onTerminate()<BR>
	{<BR>
		//Script cleanup and get script/testcase metrics<BR>
		log.onTerminate();	<BR>	
	} <BR>   	
	<BR>
	<BR>
	
	//the testMain() should contain onInitialize() at the top and onTerminate() at the bottom<BR>
	public void testMain (Object[] args) <BR>
		{<BR>
			//call the testcase here<BR>
			MyTestCase();	 <BR>
			MyErrExampleCase();<BR>
			<BR>
		}<BR>
	<BR>
	<BR>
	<BR>	
	}<BR>

	</code>
	
	<p>
	The VisualReporter can also be used as a super helper class by extending the associated Script Helper file. 
	If you use the VisualReporter as a super helper then the onInitialize() and onTermniate() methods are not needed
	in the testMain(). You can use the VisualReporter as a super helper by changing the script helper file from this: <p>
	
	<p>
	public abstract class TestLoggerHelper extends <b>RationalTestScript</b>
	
	<P>
	
	to this: <p>
	<p>
	
	public abstract class TestLoggerHelper extends <b>VisualReporter</b>
	
	<p>
	
	The result log output from the script example above should look like this: <p>
	
	<p>
	
	##############################################################################<BR>
	# Script Name:           TestLogger2<BR>
	# Script Author:         Tony Venditti<BR>
	# Script Description:    Test Logging stuff<BR>
	# Script Test Area:      Logging<BR>
	# Test Server:           www.ibm.com<BR>
	# Test Client:           \\tonyv_laptop\c$\temp\<BR>
	# Test Client OS:        WinNT Version 5.0  Build 2195 (Service Pack 4)<BR>
	# Browser:               InternetExplorer<BR>
	# Current Date and Time: Feb 4, 2004 11:15:18 AM<BR>
	##############################################################################<BR>
	==============================================================================<BR>
	Testcase -> This is a testcase to test Logging defaults - 11:15:18 AM<BR>
	==============================================================================<BR>
	11:15:27 AM - 00:00:09:093 - PASS - Set text "W.V.O. Quine" in TextField "p"<BR>
	11:15:28 AM - 00:00:09:534 - PASS - Clicked on Button "Yahoo! Search"<BR>
	==============================================================================<BR>
	Testcase -> Test the error handler - 11:15:28 AM<BR>
	==============================================================================<BR>
	<BR>
	11:15:28 AM - 00:00:09:674 - FAIL - This is an error<BR>
	<BR>
	Stack Trace:<BR>
	java.lang.Exception<BR>
	at ibm.loggers.VisualReporter.getStackTrace(VisualReporter.java:824)<BR>
	at ibm.loggers.VisualReporter.errorHandler(VisualReporter.java:742)<BR>
	at TestLogger2.MyErrExampleCase(TestLogger2.java:54)<BR>
	at TestLogger2.testMain(TestLogger2.java:66)<BR>
	at java.lang.reflect.Method.invoke(Native Method)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethod2Ext(Unknown Source)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)<BR>
	at com.rational.test.ft.script.RationalTestScript.runMain(Unknown Source)<BR>
	at java.lang.reflect.Method.invoke(Native Method)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethod2Ext(Unknown Source)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)<BR>
	at com.rational.test.ft.sys.FtReflection.invokeMethodExt(Unknown Source)<BR>
	at com.rational.test.ft.application.ScriptPlayback.runScript(Unknown Source)<BR>
	at com.rational.test.ft.application.ScriptPlayback.run(Unknown Source)<BR>
	at com.rational.test.ft.rational_ft.run(Unknown Source)<BR>
	at com.rational.test.ft.rational_ft.main(Unknown Source)<BR>
<BR>
<BR>
	Error Bitmap Saved to: \\tonyv_laptop\c$\temp\TestLogger2_0204111528.jpg<BR>
<BR>
	##############################################################################<BR>
	# Testcases Executed:              2<BR>
	# Testcases Passed:                1<BR>
	# Testcases Failed:                1<BR>
	# Percent Testcases Passed:        50.0%<BR>
	# Percent Testcases Failed:        50.0%<BR>
	# Number of Test Actions Executed: 3<BR>
	# Number of Errors found:          1<BR>
	# Elapsed Time:                    00:00:10:545<BR>
	##############################################################################<BR>

	<p>
	The VisualReporter class has an associated properties file called VisualReporter.properties.  Place the 
	VisualReporter.properties file in your client system's home directory. The home directory is usually the 
	c:\Documents and Settings\<your name> folder. You can find out what your home directory is by executing
	the following java command: System.out.printn(System.getProperty("user.home")); <p> 
	Edit the properties in the VisualReporter.properties file to represent your local system and automation settings.
	If you do not have this file, the VisualReporter will use default variables.

	<p>
 * 
 *    
 * @author avenditti
 * 
 * @version 2.2
 * Last Modified: 12/08/04
 */
public class VisualReporter extends RationalTestScript implements IPackageLogger
{

	//******************************************************************************************************			
	//System specific automation variables
	//These items can be set in the Teamspace INI file
	//which is kept on local system drive
	//******************************************************************************************************			

	//******************************************************************************************************			
	//Global Automation & System variables
	//******************************************************************************************************			

	/**Global string for automation directory*/
	public static String gsAutoPath = "c:\\";

	/**Global string for automation result directory*/
	public static String gsAutoResultPath = "c:\\temp\\";

	/**Global string for automation result file suffix*/
	public static String gsAutoResultFileSuffix = ".txt";

	/**Global string for automation result image suffux*/
	public static String gsAutoResultErrorImageSuffix = ".jpg";

	/**Global string for automation support docs path*/
	public static String gsAutoSupportDocs = "c:\\";

	/**Global long for ms diff between GMT and EST*/
	public static long glCalendarOffset = 18000000;

	//******************************************************************************************************				
	//Global script test server/client info
	//******************************************************************************************************			
	
	/**Global string for test server*/
	public static String gsTestBaseServer; // = "maple.iris.com";

	/**Global string for test server URL*/
	public static String gsBaseURL = "about:blank";

	/**Global string for test browser*/
	public static String gsTestBrowser; // = "Internet Explorer 6.0";

	/**Global string for Websphere login user name*/
	public static String gsUsername; // = "wpsadmin";

	/**Global string for Websphere login user pasword*/
	public static String gsPassword; // = "wpsadmin";

	/**Global string for Existing Teamspace*/
	public static String gsUseExistingTeamSpace;

	//******************************************************************************************************			
	//Global time-out variables
	//******************************************************************************************************			
	/**Global Time-out variable - 1 Second*/
	public static int giPause1TO; // = 1;	

	/**Global Time-out variable - 2 Seconds*/
	public static int giPause2TO; // = 2;	

	/**Global Time-out variable - 5 Seconds*/
	public static int giShortTO; // = 5;	

	/**Global Time-out variable - 10 Seconds*/
	public static int giNormalTO; // = 10;	

	/**Global Time-out variable - 15 Seconds*/
	public static int giWaitTO; // = 15;	

	/**Global Time-out variable - 30 Seconds*/
	public static int giMedTO; // = 30;	

	/**Global Time-out variable - 60 Seconds*/
	public static int giLongTO; // = 60;	

	//******************************************************************************************************			
	//Log Type formats
	//******************************************************************************************************				
	/**Global Pass-Fail output Log type*/
	public static final int LOGTYPE_TIME_PASS_FAIL = 1;

	/**Global Script output Log type*/
	public static final int LOGTYPE_SCRIPT_OUTPUT = 2;

	/**Global Simple output Log type*/
	public static final int LOGTYPE_SIMPLE = 3;

	/**Global No output Log type*/
	public static final int LOGTYPE_NONE = 4;

	/**Global Error output Log type*/
	public static final int LOGTYPE_ERROR_OUTPUT = 5;

	//******************************************************************************************************			
	//global Default Log Type
	//******************************************************************************************************			
	/**Global default Log type*/
	public static int giLogType = LOGTYPE_TIME_PASS_FAIL;

	//******************************************************************************************************			
	//global Error Handling Options
	//******************************************************************************************************			
	/**Global default Error Handling Stack Trace output Option*/
	public static boolean gbStackTrace = true;

	/**Global default Error Handling Image capture output option*/
	public static boolean gbImageCapture = true;

	//******************************************************************************************************				

	
	//******************************************************************************************************			
	//Common GUI name Strings for Lotus Workplace components
	//******************************************************************************************************
	/**Global string for Team Spaces project type*/
	public static String gsTeamSpaces = "Team Spaces";
	
	/**Global string for Applications project type*/	
	public static String gsApplications = "Applications";
	
	/**Global string for Documents project type*/	
	public static String gsDocuments = "Documents";
	
	/**Global string for Documents project type*/	
	public static String gsCalendar = "Calendar";
	
	/**Global string for Default project type*/
	public static String gsComponent = gsTeamSpaces;
	//public static String gsComponent = gsApplications;
	//public static String gsComponent = gsDocuments;
	//public static String gsComponent = gsCalendar";
	
	
	
	/**Globals for type of project to create*/
	public int GI_TEAMSPACES = 0;
	public int GI_APPLICATIONS = 1;
	public int GI_DOCUMENTS = 2;
	public int GI_CALENDAR = 3;
	public int GI_WEBCONFERENCE = 4;
	public int GI_SEARCH = 5;
	

	//******************************************************************************************************			
	//Global testcase logging information
	//******************************************************************************************************		
	/**Global int for tracking number of testcases executed*/
	public static int giTestCaseCounter;

	/**Global int for tracking number of failures flagged in the entire script*/
	public static int giErrorCounter;

	/**Global boolean checkiing for failed testcase*/
	public static boolean gbTestCaseFailed = false;

	/**Global int for tracking number of failed testcases*/
	public static int giTestCaseFailCounter;

	/**Global int for tracking number of test actions*/
	public static int giTestActionCounter;

	/**Global string for Testcase header*/
	public static String gsTestcaseHeader = "Testcase -> ";

	//******************************************************************************************************			
	//Global script header information
	//******************************************************************************************************		
	/**Global string for script author*/
	public static String gsScriptAuthor;

	/**Global string for script description*/
	public static String gsScriptDescription;

	/**Global string for script test area*/
	public static String gsScriptTestArea;

	/**Global string for script name*/
	public static String gsScriptName;

	//******************************************************************************************************			
	//Automation INI filename and path	
	//******************************************************************************************************				
	/**Automation properties file name*/
	public static String gsAutoPropFile = System.getProperty("user.home") + "\\VisualReporter.properties";

	/**Automation suite statistic tally ini file name*/
	public static String gsSuiteStatFile = System.getProperty("user.home") + "\\suitestats.ini";

	/**Global long for script start time*/
	public static long glStartTime = 0;

	/**Global string for line separator*/
	public static String gsNewline = System.getProperty("line.separator");

	/**Global string for automation result file*/
	public static String gsResultFile;

	/**Global string for automation suite result file*/
	public static String gsSuiteResultFile = null;

	/**Global string for default Browser Title*/
	public static String gsBrowserBaseStatePage = "about:blank";
	
	/**Global int for Base State*/
	public static int giBaseState = 0;

//TBS - 02/07/04	
	private int giLogLevel = PACKAGELOGLEVEL_ERRORS_ONLY;
	
	/**Sets the Log Level for this logger.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * Default is errors only<br>
	 * Use the constants in this class (inherited from IPackageLoggerConstants) to avoid unpredictable results.<br>
	 * e.g.: <br>
	 * log.setLogLevel(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS)
	 * 
	 * @param logLevel		the log level to set the package to (use the LOGLEVEL constants stored in this class)
	 */
	public void setLogLevel(int logLevel)
	{
		giLogLevel = logLevel;
	}
	
	/**
	 * Returns the Log Level that this logger is set to.<p>
	 * The Log Level is the threshold at which the package will log information<br>
	 * 
	 * @return the log level the package is set to
	 */
	public int getLogLevel()
	{
		return giLogLevel;
	}
	
// end tbs
	
	
	//******************************************************************************************************
	/**
	 * Contructor for VisualReporter logging without script header variables
	 */
	//******************************************************************************************************
	public VisualReporter()
	{
	}
	
	//******************************************************************************************************
	/**
	 * Constructor for VisualReporter Looging with script header variables
	 * @param sAuthor				script author
	 * @param sScriptDescription	script description
	 * @param sTestArea			script test functional area
	 */
	//******************************************************************************************************
	public VisualReporter(String sAuthor, String sScriptDescription, String sTestArea)
	{
	//assign script variables
	gsScriptAuthor = sAuthor;
	gsScriptDescription = sScriptDescription;
	gsScriptTestArea = sTestArea;
	}
	
	//******************************************************************************************************
	/**
	 * Initializes test script and system variables at script startup
	 */
	//******************************************************************************************************
	public void onInitialize()
	{
		
		autoSetup(true);
	}
	
	//******************************************************************************************************
	/**
	 * Executes script cleanup and test metric gathering functions upon script termination
	 */
	//******************************************************************************************************
	public void onTerminate()
	{	
		try{
		autoCleanup();
		}
		catch(Exception e)
			{
			errorHandler("FAIL - error occurred at script termination",e);
			}		
	}
	
	
	//******************************************************************************************************
	/**
	 * Puts the application in its base state
	 * @param iBaseState		integer representing the base state to call;<br>
	 * 		0=none,<br>
	 * 		1=close all browsers except main page,<br>
	 * 		2=whatever is set in package recovery controller (see ibm\recovery)
	 * 
	 */
	//******************************************************************************************************
	public void callBaseState(int iBaseState) {
		
		
		try{
		switch (iBaseState)
			{
			case 0:
			break;
			
			case 1:
			BrowserOps.closeAllBrowsersExcept(gsBrowserBaseStatePage);
			break;
			
			case 2:
			PackageRecoveryController.callBaseState();
			break;
			}
		}
		catch(Exception e)
			{
			errorHandler("FAIL - error occurred in BaseState setup",e);
			}		
			
	}	
	
	//******************************************************************************************************
	/** 	
	* Sets up, initializes automation environment and logs script header info
	* @param bShowHeaderInfo	true displays script header information, false does not display script header information
	*/
	///*******************************************************************************************************/
	public void autoSetup(boolean bShowHeaderInfo)
	{
		//set this script name to the testscript which is running when the logger is created, 
		//so that logging to the results file has the appropriate info (otherwise prints "script: null" with no line numbers)	
		gsScriptName = getTopScriptName();
		
		if (getScriptName() == null)
		{
			//set this script name to the testscript which is running when the logger is created, 
			// 		so that logging to the results file has the appropriate info (otherwise prints "script: null" with no line numbers)
			try 
			{
				setScriptName(gsScriptName);
			} 
			catch(Exception e) //in case the calling script doesn't have a matching script definition in the resources directory for some reason, just bail
			{ 
				//noop
			}
		}
	
		//Set the global logging package to VisualReporter logging
		PackageLoggingController.setLogger(this);
		

		//Setup and initialize automation environment	
		Date d = new Date();
		DateFormat dtformat = DateFormat.getDateInstance();
		DateFormat tmformat = DateFormat.getTimeInstance();
		ScriptUtilities sys = new ScriptUtilities();

		//Get system specific global variables
		loadAutoPropSettings(gsAutoPropFile);

		//create unique result file based on script name and date
		gsResultFile = gsAutoResultPath + gsScriptName + "_" + genDateBasedRandVal() + gsAutoResultFileSuffix;
		FileOps.writeFileContents(gsResultFile, "");

		//Write Script Header info to result log
		if (bShowHeaderInfo == true)
		{
			logScriptInfo("******************************************************************************", 3);
			logScriptInfo("* Script Name:           " + gsScriptName, 3);
			logScriptInfo("* Script Author:         " + gsScriptAuthor, 3);
			logScriptInfo("* Script Description:    " + gsScriptDescription, 3);
			logScriptInfo("* Script Test Area:      " + gsScriptTestArea, 3);
			logScriptInfo("* Test Server:           " + gsBaseURL, 3);
			logScriptInfo("* Test Client:           " + gsAutoPath, 3);
			logScriptInfo("* Test Client OS:        " + ScriptUtilities.getOperatingSystemVersion(), 3);
			logScriptInfo("* Browser:               " + getCurrentBrowser(), 3);
			logScriptInfo("* Current Date and Time: " + dtformat.format(new Date()) + " " + tmformat.format(new Date()), 3);
			logScriptInfo("******************************************************************************", 3);
		}

		//Start teamspace script clock
		setStartTime();

		//Clear any registered test objects
		unregisterAll();

		//Clear testcase counter
		giTestCaseCounter = 0;

		//Clear error counter
		giErrorCounter = 0;

		//Clear failed testcase counter
		giTestCaseFailCounter = 0;

		//Set testcase failed boolean to false
		gbTestCaseFailed = false;

		//Clear test action counter
		giTestActionCounter = 0;

	}

	//********************************************************************************************************
	/** 	
	* Logs an informational message from the package
	* @param sMessage		the text of the message to log
	*
	* @author tsnow
	*/
	//*******************************************************************************************************
	public void logPackageInfo(String sMessage)
	{
		logScriptInfo(sMessage, giLogType);
	}
	
	//********************************************************************************************************
	/** 	
	* Logs a warning message from the package
	* @param sMessage		the text of the message to log
	*
	* @author tsnow
	*/
	//*******************************************************************************************************
	public void logPackageWarning(String sMessage)
	{
		logScriptInfo(sMessage, LOGTYPE_ERROR_OUTPUT);
	}
	
	//********************************************************************************************************
	/** 	
	* Logs an error message from the package
	* @param sMessage		the text of the message to log
	*
	* @author tsnow
	*/
	//*******************************************************************************************************
	public void logPackageError(String sMessage)
	{
		logScriptInfo(sMessage, LOGTYPE_ERROR_OUTPUT);
	}
	

	//********************************************************************************************************
	/** 	
	* Overloaded to explicitly state which log format type to use. Writes info to log in any of a number of specified formats <p>
	* @param sLog		Log message
	* @param iType		type of log information (i.e. 1=PASS/FAIL, 2=SCRIPT OUTPUT, etc.)
	* @return   	
	*/
	//*******************************************************************************************************
	public void logScriptInfo(String sLog, int iType)
	{
		//Overloaded to explicitly state which log format type to use. Writes info to log in any of a number of specified formats 	
		Date d = new Date();
		DateFormat dtformat = DateFormat.getDateInstance();
		DateFormat tmformat = DateFormat.getTimeInstance();
		String s = "";

		switch (iType)
		{
			case LOGTYPE_TIME_PASS_FAIL : //Verbose - PASS-FAIL format output
				s = tmformat.format(new Date()) + " - " + getElapsedTime(glStartTime) + " - " + "PASS - " + sLog; //format
				giTestActionCounter++; //add one to test action counter
				break;
			case LOGTYPE_SCRIPT_OUTPUT : //Verbose - Manual script format output
				s = "[ ] - " + sLog; //format
				giTestActionCounter++; //add one to test action counter
				break;
			case LOGTYPE_SIMPLE : //Verbose - Log only what is entered	
				s = sLog;
				break;
			case LOGTYPE_NONE : //Do NOT log actions info to log file
				break;
			case LOGTYPE_ERROR_OUTPUT :
				s = sLog;
				System.err.println(s);
				logError(s);
				FileOps.appendStringToFile(gsResultFile, s);
				break;

		}

		//write results to XDE Tester default log, text file (gsResultFile) and to console
		if (iType != 4 && iType != 5)
		{
			logInfo(s); //XDE Tester default log
			FileOps.appendStringToFile(gsResultFile, s); //text file (see gsResultFile variable for location)
			System.out.println(s); //console
		}
	}

	//********************************************************************************************************
	/**	
	* Gets elapsed time from the specified start time. Returns string in "HH:mm:ss:SSS" format. <p>
	* @param startTime		the start time
	* @return A string containing the time difference between the script start time and the script elapsed time. 
	*/
	//******************************************************************************************************
	public String getElapsedTime(long startTime)
	{
		//end time long format
		long endTime = System.currentTimeMillis();
		//System.out.println("End Time:   " + String.valueOf(endTime));

		//elapsed time long format
		long time = endTime - startTime;
		//System.out.println("Elapsed Time: " + String.valueOf(time));

		//Format the Date time information
		String s = getFormattedDateTime(time, "HH:mm:ss:SSS");

		return s;
	}

	//******************************************************************************************************
	/**	
	* returns long data type of elapsed script time to do arithmetic timing operations   <p>
	* @return Long containing the time difference between the script start time and the script elapsed time. 
	*/
	//******************************************************************************************************
	public long getElapsedTimeLong(long startTime)
	{
		//end time long format
		long endTime = System.currentTimeMillis();
		//System.out.println("End Time:   " + String.valueOf(endTime));

		//elapsed time long format
		long time = endTime - startTime;
		//System.out.println("Elapsed Time: " + String.valueOf(time));

		return time;
	}

	//******************************************************************************************************
	/** 
	* Generates String containing unique random date-based value	 <p>
	* @return a String containing a date-based unique value
	*/
	///*******************************************************************************************************/
	public String genDateBasedRandVal()
	{
		//generates String containing unique random date-based value	
		String s;
		Date d = new Date();
		long l;

		//format
		SimpleDateFormat tmformat = new SimpleDateFormat("MMddHHmmss");

		l = d.getTime();
		s = tmformat.format(d);

		return s;
	}

	//******************************************************************************************************
	/**	
	* Sets script clock start time - by default this is started automatically at script startup when the
	* webAutoSetup() function is used. The function sets a global variable called glStartTime which is then 
	* used as an argument to the getElapsedTime(glStartTime) function which is called in the logScriptInfo() fucntion   <p>
	*/
	//******************************************************************************************************
	public void setStartTime()
	{
		long lTime = System.currentTimeMillis();
		glStartTime = lTime;
	}

	//********************************************************************************************************
	/**	
	* returns formatted String version of date supplied as long  <p>
	* @param dDateTime			date and time in long data type
	* @param sDateTimeFormat	The string format you would like the date and time to be displayed as. (i.e. "HH:mm:ss:SSS")
	* @return String containing the formatted date time of the given long datetime. 
	*/
	//******************************************************************************************************
	public String getFormattedDateTime(long dDateTime, String sDateTimeFormat)
	{
		SimpleDateFormat tmformat = new SimpleDateFormat(sDateTimeFormat);

		Date tm = new Date(glCalendarOffset + dDateTime);

		String s = tmformat.format(tm);

		return s;
	}

	//********************************************************************************************************
	/**
	* Logs script testcase information and other metrics info <p>
	*/
	//******************************************************************************************************
	public void autoCleanup()
	{
		//Check if last testcase contained failures
		if (gbTestCaseFailed == true)
		{
			giTestCaseFailCounter++;
			//System.out.println(giTestCaseFailCounter);
		}

		int iTestCasePassedCounter;

		//calculate number of passed test cases
		iTestCasePassedCounter = (giTestCaseCounter - giTestCaseFailCounter);

		//calculate percentages
		double dPrctPassed = ((double) iTestCasePassedCounter / (double) giTestCaseCounter) * 100;
		double dPrctFailed = ((double) giTestCaseFailCounter / (double) giTestCaseCounter) * 100;

		//store elapsed time
		String sElapsedTime = getElapsedTime(glStartTime);
		long lElapsedTime = getElapsedTimeLong(glStartTime);

		//Write Script Testcase Information to result log
		logScriptInfo("******************************************************************************", 3);
		logScriptInfo("* Testcases Executed:              " + giTestCaseCounter, 3);
		logScriptInfo("* Testcases Passed:                " + iTestCasePassedCounter, 3);
		logScriptInfo("* Testcases Failed:                " + giTestCaseFailCounter, 3);
		logScriptInfo("* Percent Testcases Passed:        " + dPrctPassed + "%", 3);
		logScriptInfo("* Percent Testcases Failed:        " + dPrctFailed + "%", 3);
		logScriptInfo("* Number of Test Actions Executed: " + giTestActionCounter, 3);
		logScriptInfo("* Number of Errors found:          " + giErrorCounter, 3);
		logScriptInfo("* Elapsed Time:                    " + sElapsedTime, 3);
		logScriptInfo("******************************************************************************", 3);

		//open temporary file to tally test statistics for suite results
		String sTmp;
		int inTestCaseCounter = 0;
		int inTestCasePassedCounter = 0;
		int inTestCaseFailCounter = 0;
		double inPrctPassed = 0;
		double inPrctFailed = 0;
		int inTestActionCounter = 0;
		int inErrorCounter = 0;
		long inElapsedTime = 0;

		try
		{
			FileInputStream in = new FileInputStream(gsSuiteStatFile);
			Properties settings = new Properties();
			settings.load(in);

			sTmp = settings.getProperty("TestCaseCounter");
			inTestCaseCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("TestCasePassedCounter");
			inTestCasePassedCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("TestCaseFailCounter");
			inTestCaseFailCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("PrctPassed");
			inPrctPassed = Double.parseDouble(sTmp);

			sTmp = settings.getProperty("PrctFailed");
			inPrctFailed = Double.parseDouble(sTmp);

			sTmp = settings.getProperty("TestActionCounter");
			inTestActionCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("ErrorCounter");
			inErrorCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("ElapsedTime");
			inElapsedTime = Long.parseLong(sTmp);

			in.close();
		}
		catch (Exception e)
		{
			logScriptInfo("Error loading suite statistic INI variables in file: " + gsSuiteStatFile, 5);
		}

		//tally and update suite statistics file
		try
		{
			FileOutputStream out = new FileOutputStream(gsSuiteStatFile);
			Properties settings = new Properties();

			settings.put("TestCaseCounter", String.valueOf(inTestCaseCounter + giTestCaseCounter));
			settings.put("TestCasePassedCounter", String.valueOf(inTestCasePassedCounter + iTestCasePassedCounter));
			settings.put("TestCaseFailCounter", String.valueOf(inTestCaseFailCounter + giTestCaseFailCounter));
			settings.put("PrctPassed", String.valueOf((((double) inTestCasePassedCounter + (double) iTestCasePassedCounter) / ((double) inTestCaseCounter + (double) giTestCaseCounter)) * 100));
			settings.put("PrctFailed", String.valueOf((((double) inTestCaseFailCounter + (double) giTestCaseFailCounter) / ((double) inTestCaseCounter + (double) giTestCaseCounter)) * 100));
			settings.put("TestActionCounter", String.valueOf(inTestActionCounter + giTestActionCounter));
			settings.put("ErrorCounter", String.valueOf(inErrorCounter + giErrorCounter));
			settings.put("ElapsedTime", String.valueOf(inElapsedTime + lElapsedTime));

			//Close out properties file
			settings.store(out, "");
			out.close();

		}
		catch (IOException ioe)
		{
			logScriptInfo("FAIL - Error saving suite statistic INI variables in file: " + gsSuiteStatFile, 5);
		}

		//append results to suite
		if (gsSuiteResultFile != null)
		{
			FileOps.appendStringToFile(gsSuiteResultFile, FileOps.getFileContents(gsResultFile));
		}
	}

	//******************************************************************************************************
	/**
	* Logs and tracks testcase information <p>
	* @param sDesc		Description of testcase
	*/
	//******************************************************************************************************
	public void logTestCaseInfo(String sDesc)
	{
		DateFormat tmformat = DateFormat.getTimeInstance();

		//Display testcase description information
		logScriptInfo("==============================================================================", 3);
		logScriptInfo(gsTestcaseHeader + sDesc + " - " + tmformat.format(new Date()), 3);
		logScriptInfo("==============================================================================", 3);

		//Clear any registered test objects
		unregisterAll();

		//Add one to testcase counter
		giTestCaseCounter++;

		//Check if prior testcase contained failures
		if (gbTestCaseFailed == true)
		{
			giTestCaseFailCounter++;
			//System.out.println(giTestCaseFailCounter);
		}

		//reset testcase failed to false
		gbTestCaseFailed = false;
		//System.out.println(gbTestCaseFailed);	
	}

	//******************************************************************************************************
	/**
	* Dumps non-exception based error information out to console, text result and xde result logs <p>
	* @param sLog		The text you want to write out to the log file
	* @return 		
	*/
	//******************************************************************************************************	
	public void errorHandler(String sLog)
	{

		Date d = new Date();
		DateFormat dtformat = DateFormat.getDateInstance();
		DateFormat tmformat = DateFormat.getTimeInstance();

		//spacer
		logScriptInfo("", 3);

		//Log error to output result logs
		String s = tmformat.format(new Date()) + " - " + getElapsedTime(glStartTime) + " - " + sLog;
		System.err.println(s);
		logError(s);
		giTestActionCounter++; //add one to test action counter
		FileOps.appendStringToFile(gsResultFile, s);

		//Add 1 to error counter and set tescase failed to true
		giErrorCounter++;
		gbTestCaseFailed = true;

		//Get stack and error info
		if (gbStackTrace == true)
		{
			logScriptInfo("", 3);
			//Thread.dumpStack();
			logScriptInfo("Stack Trace:", 5);
			logScriptInfo(getStackTrace(null), 5);
		}

		//Get screen bitmap
		if (gbImageCapture == true)
		{
			String sErrBmpFile = gsAutoResultPath + gsScriptName + "_" + genDateBasedRandVal() + gsAutoResultErrorImageSuffix;
			BitmapOps.captureScreen(sErrBmpFile);
			logScriptInfo("", 3);
			logScriptInfo("Error Bitmap Saved to: " + sErrBmpFile, 5);
		}

		//Spacer
		logScriptInfo("", 3);

		//go to base state	
		callBaseState(giBaseState);
		
	}

	//******************************************************************************************************
	/**	
	* Overloaded function Dumps exception based error information out to console, text result and xde result logs <p>
	* @param sLog		The text you want to write out to the log file
	* @param e 		Exception error info
	* @return 		
	*/
	//******************************************************************************************************	
	public void errorHandler(String sLog, Exception e)
	{

		Date d = new Date();
		DateFormat dtformat = DateFormat.getDateInstance();
		DateFormat tmformat = DateFormat.getTimeInstance();

		//spacer
		logScriptInfo("", 3);

		//Log error to output result logs
		String s = tmformat.format(new Date()) + " - " + getElapsedTime(glStartTime) + " - " + sLog;
		System.err.println(s);
		logError(s);
		giTestActionCounter++; //add one to test action counter
		FileOps.appendStringToFile(gsResultFile, s);

		//Add 1 to error counter and set tescase failed to true
		giErrorCounter++;
		gbTestCaseFailed = true;

		//Get stack and error info
		logScriptInfo("", 3);
		logScriptInfo("Stack Trace:", 5);
		logError(e.getMessage());
		logScriptInfo(getStackTrace(e), 5);

		//Get screen bitmap
		if (gbImageCapture == true)
		{
			String sErrBmpFile = gsAutoResultPath + gsScriptName + "_" + genDateBasedRandVal() + gsAutoResultErrorImageSuffix;
			BitmapOps.captureScreen(sErrBmpFile);
			logScriptInfo("", 3);
			logScriptInfo("Error Bitmap Saved to: " + sErrBmpFile, 5);
		}

		//spacer
		logScriptInfo("", 3);

		//go to base state	
		callBaseState(giBaseState);
	}

	//********************************************************************************************************
	/**
	* Gets stack trace information and returns it as a string <p>
	* @param e		Exception
	* @return String containing stack trace
	*/
	//******************************************************************************************************
	public String getStackTrace(Exception e)
	{
		if (e == null)
		{
			Exception f = new Exception();
			e = f;
		}

		StringWriter sw = null;
		PrintWriter pw = null;
		sw = new StringWriter();
		pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		return sw.toString();
	}

	//******************************************************************************************************
	/**
	* Initialize suite statistics file <p>
	*/
	//******************************************************************************************************
	public void initializeSuiteStats()
	{
		//get suite file name
		gsScriptName = getTopScriptName();
		try
		{
			setScriptName(gsScriptName);
		}
		catch (Exception e) //in case the calling script doesn't have a matching script definition in the resources directory for some reason, just bail
		{
			gsScriptName = "suite";
			setScriptName(gsScriptName);	
		}
		
		
		//Get system specific global variables
		loadAutoPropSettings(gsAutoPropFile);
		
		//create and initialize unique suite result file based on script name and date
		String sSuiteFile = gsScriptName;
		gsSuiteResultFile = gsAutoResultPath + sSuiteFile + "_" + genDateBasedRandVal() + gsAutoResultFileSuffix;
		FileOps.writeFileContents(gsSuiteResultFile, "");

		//Initialize suite stats
		try
		{
			FileOutputStream out = new FileOutputStream(gsSuiteStatFile);
			Properties settings = new Properties();

			settings.put("TestCaseCounter", String.valueOf(0));
			settings.put("TestCasePassedCounter", String.valueOf(0));
			settings.put("TestCaseFailCounter", String.valueOf(0));
			settings.put("PrctPassed", String.valueOf(0));
			settings.put("PrctFailed", String.valueOf(0));
			settings.put("TestActionCounter", String.valueOf(0));
			settings.put("ErrorCounter", String.valueOf(0));
			settings.put("ElapsedTime", String.valueOf(0));

			//Close out properties file
			settings.store(out, "");
			out.close();

		}
		catch (IOException ioe)
		{
			errorHandler("Error saving suite statistic INI variables in file: " + gsSuiteStatFile, ioe);
		}

	}

	//******************************************************************************************************
	/**
	* Logs test suite statistics <p>
	*/
	//******************************************************************************************************
	public void logSuiteStats()
	{

		StringBuffer sSummary = new StringBuffer();

		//open temporary file to tally test statistics for suite results
		String sTmp;
		int inTestCaseCounter = 0;
		int inTestCasePassedCounter = 0;
		int inTestCaseFailCounter = 0;
		double inPrctPassed = 0;
		double inPrctFailed = 0;
		int inTestActionCounter = 0;
		int inErrorCounter = 0;
		long inElapsedTime = 0;

		try
		{
			FileInputStream in = new FileInputStream(gsSuiteStatFile);
			Properties settings = new Properties();
			settings.load(in);

			sTmp = settings.getProperty("TestCaseCounter");
			inTestCaseCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("TestCasePassedCounter");
			inTestCasePassedCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("TestCaseFailCounter");
			inTestCaseFailCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("PrctPassed");
			inPrctPassed = Double.parseDouble(sTmp);

			sTmp = settings.getProperty("PrctFailed");
			inPrctFailed = Double.parseDouble(sTmp);

			sTmp = settings.getProperty("TestActionCounter");
			inTestActionCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("ErrorCounter");
			inErrorCounter = Integer.parseInt(sTmp);

			sTmp = settings.getProperty("ElapsedTime");
			inElapsedTime = Long.parseLong(sTmp);

			in.close();

			//Get Suite Test Summary information
			sSummary.delete(0, sSummary.length()); //clear stringbuffer
			sSummary.append("******************************************************************************\n");
			sSummary.append("* Test Suite Summary\n");
			sSummary.append("******************************************************************************\n");
			sSummary.append("* Total Testcases Executed:                " + inTestCaseCounter + "\n");
			sSummary.append("* Total Testcases Passed:                  " + inTestCasePassedCounter + "\n");
			sSummary.append("* Total Testcases Failed:                  " + inTestCaseFailCounter + "\n");
			sSummary.append("* Total Percent Testcases Passed:          " + inPrctPassed + "%" + "\n");
			sSummary.append("* Total Percent Testcases Failed:          " + inPrctFailed + "%" + "\n");
			sSummary.append("* Total Number of Test Actions:            " + inTestActionCounter + "\n");
			sSummary.append("* Total Number of Errors found:            " + inErrorCounter + "\n");
			sSummary.append("* Total Elapsed Time:                      " + getFormattedDateTime(inElapsedTime, "HH:mm:ss:SSS") + "\n");
			sSummary.append("******************************************************************************\n");

			
			gsResultFile = gsSuiteResultFile;
			
			//Display suite totals
			logScriptInfo("******************************************************************************", 3);
			logScriptInfo("* Total Testcases Executed:                " + inTestCaseCounter, 3);
			logScriptInfo("* Total Testcases Passed:                  " + inTestCasePassedCounter, 3);
			logScriptInfo("* Total Testcases Failed:                  " + inTestCaseFailCounter, 3);
			logScriptInfo("* Total Percent Testcases Passed:          " + inPrctPassed + "%", 3);
			logScriptInfo("* Total Percent Testcases Failed:          " + inPrctFailed + "%", 3);
			logScriptInfo("* Total Number of Test Actions:            " + inTestActionCounter, 3);
			logScriptInfo("* Total Number of Errors found:            " + inErrorCounter, 3);
			logScriptInfo("* Total Elapsed Time:                      " + getFormattedDateTime(inElapsedTime, "HH:mm:ss:SSS"), 3);
			logScriptInfo("******************************************************************************", 3);

		}
		catch (Exception e)
		{
			errorHandler("FAIL - Error loading suite statistic property variables in file: " + gsSuiteStatFile, e);
		}

		//append results to suite
		if (gsSuiteResultFile != null)
		{
			//FileOps.appendStringToFile(gsSuiteResultFile, FileOps.getFileContents(gsResultFile));

			//Display completion message
			if (inTestCaseFailCounter >= 1)
			{

				displayMessageDlg(
					"Failures occurred during this automated test.\n See the result log "
						+ gsSuiteResultFile
						+ " for test details.\n The result log will be displayed automatically after you click the OK button.\n Hint: To find failed testcases in the result log search for the word \"FAIL\" \n \n"
						+ sSummary.toString(),
					"Automated Test - FAILED");
			}
			else
			{

				displayMessageDlg(
					"The automated test successfully executed without errors.\n See the result log " + gsSuiteResultFile + " for test details.\n The result log will be displayed automatically after you click the OK button.\n \n" + sSummary.toString(),
					"Automated Test - PASSED");
			}

			//display result text file when suite completes
			run("notepad " + gsSuiteResultFile, null);
		}

	}

	//******************************************************************************************************
	/**				
	* Displays interactive dialog message <p>
	*/
	//********************************************************************************************************/	
	public void displayMessageDlg(String sMsg, String sTitle)
	{
		JOptionPane.showMessageDialog(null, sMsg, sTitle, JOptionPane.INFORMATION_MESSAGE);
	}

	//******************************************************************************************************
	/** 	
	* Loads system specific global automation variables <p>
	* @param sFile		properties file to load
	*/
	//******************************************************************************************************
	public void loadAutoPropSettings(String sFile)
	{
		//Get machine specific global automation variables
		File propFile = new File(sFile);
		if (propFile.exists())
		{

			try
			{
				FileInputStream in = new FileInputStream(sFile);
				Properties settings = new Properties();
				settings.load(in);

				//General Automation Properties
				gsAutoPath = settings.getProperty("gsAutoPath");

				gsAutoSupportDocs = settings.getProperty("gsAutoSupportDocs");

				glCalendarOffset = Long.parseLong(settings.getProperty("glCalendarOffset"));

				giPause2TO = Integer.parseInt(settings.getProperty("giPause2TO"));

				giPause1TO = Integer.parseInt(settings.getProperty("giPause1TO"));

				giShortTO = Integer.parseInt(settings.getProperty("giShortTO"));

				giNormalTO = Integer.parseInt(settings.getProperty("giNormalTO"));

				giWaitTO = Integer.parseInt(settings.getProperty("giWaitTO"));

				giMedTO = Integer.parseInt(settings.getProperty("giMedTO"));

				giLongTO = Integer.parseInt(settings.getProperty("giLongTO"));

				//Result Logging Properties
				gsAutoResultPath = settings.getProperty("gsAutoResultPath");

				gsAutoResultFileSuffix = settings.getProperty("gsAutoResultFileSuffix");

				gsAutoResultErrorImageSuffix = settings.getProperty("gsAutoResultErrorImageSuffix");

				giLogType = Integer.parseInt(settings.getProperty("giLogType"));

				gbStackTrace = Boolean.valueOf(settings.getProperty("gbStackTrace")).booleanValue();

				gbImageCapture = Boolean.valueOf(settings.getProperty("gbImageCapture")).booleanValue();

				//Test Server/User Properties
				gsBaseURL = settings.getProperty("gsBaseURL");

				gsTestBrowser = settings.getProperty("gsTestBrowser");

				gsUsername = settings.getProperty("gsUsername");

				gsPassword = settings.getProperty("gsPassword");

				setLogLevel(Integer.parseInt(settings.getProperty("gsPackageLogLevel")));

				//Base State Properties
				gsBrowserBaseStatePage = settings.getProperty("gsBrowserBaseStatePage");
				
				giBaseState = Integer.parseInt(settings.getProperty("giBaseState"));
				
				gsUseExistingTeamSpace = settings.getProperty("gsUseExistingTeamSpace");
				if (gsUseExistingTeamSpace.equals(""))
					gsUseExistingTeamSpace = null;
				
				in.close();
			}
			catch (Exception e)
			{
				logScriptInfo("Error loading automation Property settings in file: " + gsAutoPropFile, 5);
			}
		}
		else
		{
			File resultsDir = new File(gsAutoResultPath);
			if (!resultsDir.exists())
			{
				resultsDir.mkdirs();
			}
		}
	}

	//*******************************************************************************************************/
	/**				
	* Compares 2 boolean values and displays all result info <p>
	* @param bExpected		Expected Value
	* @param bActual		Actual Value
	* @param sDesc			Description of boolean comparison
	* @return true if matched false if no-match
	*/
	//*******************************************************************************************************/
	public boolean altVerify(boolean bExpected, boolean bActual, String sDesc)
	{
		if (bActual == bExpected)
		{
			logScriptInfo("Verify " + sDesc + " Expected: " + bExpected + " Actual: " + bActual, giLogType);
			return true;
		}
		else
		{
			errorHandler("FAIL - " + sDesc + " Expected: " + bExpected + " Actual: " + bActual);
			return false;
		}

	}

	//********************************************************************************************************
	/**
	* Compares 2 string values and displays all result info <p>
	* @param sExpected		Expected Value
	* @param sActual		Actual Value
	* @param bExactMatch	true means actual and expected must be exact, false means Expected must be contained within Actual
	* @return true if matched false if no-match
	*/
	//********************************************************************************************************/
	public boolean altVerify(String sExpected, String sActual, boolean bExactMatch)
	{
		if (bExactMatch == true)
		{
			if (sActual.toUpperCase().equals(sExpected.toUpperCase()))
			{
				logScriptInfo("Verify Expected: \"" + sExpected + "\" Actual: \"" + sActual + "\"", giLogType);
				return true;
			}
			else
			{
				errorHandler("FAIL - Expected: \"" + sExpected + "\" Actual: \"" + sActual + "\"");
				return false;
			}
		}
		else
		{
			if (sActual.toUpperCase().indexOf(sExpected.toUpperCase()) != -1)
			{
				logScriptInfo("Verify Expected: \"" + sExpected + "\" Actual: \"" + sActual + "\"", giLogType);
				return true;
			}
			else
			{
				errorHandler("FAIL - Expected: \"" + sExpected + "\" Actual: \"" + sActual + "\"");
				return false;
			}
		}

	}

	
	//*******************************************************************************************************/
	/**				
	* Compares 2 integer values and displays all result info <p>
	* @param iExpected		Expected Value
	* @param iActual		Actual Value
	* @param sDesc			Description of boolean comparison
	* @return true if matched false if no-match
	*/
	//*******************************************************************************************************/
	public boolean altVerify(int iExpected, int iActual, String sDesc)
	{
		if (iActual == iExpected)
		{
			logScriptInfo("Verify " + sDesc + " Expected: " + iExpected + " Actual: " + iActual,giLogType);
			return true;
		}
		else
		{
			errorHandler("FAIL - " + sDesc + " Expected: " + iExpected + " Actual: " + iActual);
			return false;
		}

	}

	
	
	//********************************************************************************************************
	/**
	* Prints log file properties to console
	*/
	//********************************************************************************************************/
	public void printLoggingProperties()
	{
		//Datastore directory);
		System.out.println("gsAutoPath=" + gsAutoPath);

		//Support document directory);
		System.out.println("gsAutoSupportDocs=" + gsAutoSupportDocs);

		//Calendar offset for EST used in timing methods such as getElapsedTime());
		System.out.println("glCalendarOffset=" + glCalendarOffset);

		//Global Sleep (wait) values in seconds);
		System.out.println("giPause1TO=" + giPause1TO);
		System.out.println("giPause2TO=" + giPause2TO);
		System.out.println("giShortTO=" + giShortTO);
		System.out.println("giNormalTO=" + giNormalTO);
		System.out.println("giWaitTO=" + giWaitTO);
		System.out.println("giMedTO=" + giMedTO);
		System.out.println("giLongTO=" + giLongTO);

		//****************************************************);
		//Logging Properties);	
		//****************************************************);
		//Result log directory);
		System.out.println("gsAutoResultPath=" + gsAutoResultPath);

		//Result log file suffix);
		System.out.println("gsAutoResultFileSuffix=" + gsAutoResultFileSuffix);

		//Result log error image suffix);
		System.out.println("gsAutoResultErrorImageSuffix=" + gsAutoResultErrorImageSuffix);

		//Log Level);
		System.out.println("giLogType=" + giLogType);

		//Capture Stack Trace upon failure);
		System.out.println("gbStackTrace=" + gbStackTrace);

		//Capture Screen Image upon failure);
		System.out.println("gbImageCapture=" + gbImageCapture);

		//****************************************************);
		//Test Server/User Properties
		//****************************************************);
		//Test Server URL);
		System.out.println("gsBaseURL=" + gsBaseURL);

		//Test Browser Name);
		System.out.println("gsTestBrowser=" + gsTestBrowser);

		//Test user name);
		System.out.println("gsUsername=" + gsUsername);

		//Test user password);
		System.out.println("gsPassword=" + gsPassword);

		//****************************************************);
		//Test Base State information properties
		//****************************************************);
		//Browser Base State Page
		System.out.println("gsBrowserBaseStatePage=" + gsBrowserBaseStatePage);
		
		System.out.println("giBaseState=" + giBaseState);
		
		System.out.println("gsUseExistingTeamSpace=" + gsUseExistingTeamSpace);

	}
	
	/**
	 * Prints out the directory into which you should put the properties file
	 */
	public void printDirForPropertiesFile()
	{
		System.out.println(System.getProperty("user.home"));
	}

}
