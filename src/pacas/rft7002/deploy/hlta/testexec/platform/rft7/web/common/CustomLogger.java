/**
 * Created on Apr 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.common;

import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.interfaces.ITestLogger;
import ibm.loggers.StackTraceLogger;
import ibm.loggers.control.RTSLoggingWrapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import com.rational.test.ft.script.IOptionName;
import com.rational.test.ft.script.RationalTestScript;

/**
 * This is a custom logger to fit dsw's test case report requirements
 * 
 * @author yangjia
 * @version 1.0
 */
public class CustomLogger extends StackTraceLogger implements ITestLogger {

	/** Failed cases count */
	private static int failedCases = 0;

	/** Executed cases count */
	private static int casesExecuted = 0;

	/** Verification point count */
	private static int totalVPCount = 0;

	/** Passed verification point count */
	private static int passedVPCount = 0;

	/** Verification point count in current case */
	private static int currTestcaseVPCount = 0;

	/** Passed verification point count in current case */
	private static int currTestcasePassedVPCount = 0;

	private static boolean isCurrTestcasePassed = true;

	/** Cases start time */
	private static Date mainScriptStartTime;

	/** Current case start time */
	private static Date testCaseStartTime;

	/** Screen image counter */
	private static int screenImageCounter = 0;

	/**	The logging channel */
	private static int logMethod = LM_HTML;
	/**
	 * Unique instance
	 */
	public static CustomLogger instance;
	

	/**
	 * get an instance of this class
	 * 
	 * @return instance of CustomLogger
	 */
	public static CustomLogger getInstance() {
		if (instance == null)
			instance = new CustomLogger();
		return instance;
	}

	/** Current test case steps information */
	private StringBuffer testcaseStepsInfo = null;

	/** Test case xml file operations class */
	// private TestcaseXmlOps testcaseXmlOps;
	/** Step counter */
	private int stepCounter = 1;

	/** Images link text */
	private StringBuffer imagesLink;

	private TestCase theCase = null; // Current test case

	private TestCase.Step theStep = null; // Current step

	/**
	 * Constructor
	 * 
	 */
	public CustomLogger() {
		setLogLevel(70);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ibm.loggers.StackTraceLogger#compareAndLogVP(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public boolean compareAndLogVP(String vpName, Object expected, Object actual) {
		return compareAndLogVP(vpName, expected, actual, null);
		/*
		 * Deleted by jeffreybian boolean result = super.compareAndLogVP(vpName,
		 * expected, actual); if (!result) { logScreenImageInfo("screen",
		 * ScreenImageOps.captureScreenImage()); } //Get next step //
		 * testcaseXmlOps.getNextStep(); step = theCase.getStep(stepCounter++);
		 * //Generate step information genStepInfo(result); //Call logScriptInfo
		 * to log step logStep();
		 * 
		 * return result;
		 */
	}

	/**
	 * Capture screens, Compare and log verification point
	 * 
	 * @param vpName
	 * @param expected
	 * @param actual
	 * @param screenImageArray
	 * @return
	 */
	public boolean compareAndLogVP(String vpName, Object expected,
			Object actual, ArrayList screenImageArray) {
		return compareAndLogVP(vpName, expected, actual, screenImageArray, -1);
		/*
		 * Deleted by jeffreybian boolean result = super.compareAndLogVP(vpName,
		 * expected, actual); //Log snapshot logScreenImageInfo("screen",
		 * screenImageArray); //Get next step testcaseXmlOps.getNextStep();
		 * stepCounter++; //Generate step information genStepInfo(result);
		 * //Call logScriptInfo to log step logStep(); return result;
		 */
	}

	/**
	 * Capture screens, compare and log verification points in a specified step
	 * 
	 * @param vpName
	 * @param expected
	 * @param actual
	 * @param screenImageArray
	 * @param step
	 * @return boolean
	 */
	public boolean compareAndLogVP(String vpName, Object expected,
			Object actual, ArrayList screenImageArray, int step) {
		// Super function compareAndLogVP
		boolean result = super.compareAndLogVP(vpName, expected, actual);
		// Log snapshot
		if (screenImageArray == null) {
			// Log a screen when failed, even no capture screen is specified
			if (result == false) {
				logScreenImageInfo("screen", ScreenImageOps
						.captureScreenImage());
			}
		} else {
			logScreenImageInfo("screen", screenImageArray);
		}

		// Get a step
		if (step != -1) {
			theStep = theCase.getStep(step);
		} else {
			theStep = theCase.getStep(stepCounter);
			System.out.println("Step counter :" + stepCounter);
		}
		// Generate step information
		genStepInfo(result,0);
		// Call logScriptInfo to log step
		logStep();
		stepCounter++;
		return result;
	}
	//for db2 testcase summary
	public boolean compareAndLogVP(String vpName, Object expected,
			Object actual,int step) {
		// boolean result = super.compareAndLogVP(vpName, expected, actual);
		boolean result = true;
		// Get a step
		if (step != -1) {
			theStep = theCase.getStep(step);
		} else {
			theStep = theCase.getStep(stepCounter);
			System.out.println("Step counter :" + stepCounter);
		}
		// Generate step information
		genStepInfo(result,1);
		// Call logScriptInfo to log step
		logStep();
		stepCounter++;
		return result;
	}

	/**
	 * Generate test case step information and store it in a StringBuffer
	 * instance
	 * 
	 * @param result
	 */
	private void genStepInfo(boolean result,int db2flag) {
		testcaseStepsInfo.append("<tr>").append("<td>" + stepCounter + "</td>")
				.append("<td>" + theStep.getStepTransText() + "</td>").append(
						"<td>" + theStep.getStepVpText() + "</td>");

		totalVPCount++;
		currTestcaseVPCount++;

		if (db2flag==1) {
			if (result == true) {
				passedVPCount++;
				currTestcasePassedVPCount++;
				testcaseStepsInfo.append("<td>" + theStep.getStepPassedText()
						+ "</td>");
				testcaseStepsInfo
						.append("<td><font color=\"green\">Log Manual - DB Result</font></td>");
			} else {
				isCurrTestcasePassed = false;
				testcaseStepsInfo.append("<td>" + theStep.getStepFailedText()
						+ "</td>");
				testcaseStepsInfo
						.append("<td><font color=\"red\">Log Manual - DB Error</font></td>");
			}
		} else {
			if (result == true) {
				passedVPCount++;
				currTestcasePassedVPCount++;
				testcaseStepsInfo.append("<td>" + theStep.getStepPassedText()
					+ "</td>");
				testcaseStepsInfo
					.append("<td><font color=\"green\">passed</font></td>");
			} else {
				isCurrTestcasePassed = false;
				testcaseStepsInfo.append("<td>" + theStep.getStepFailedText()
					+ "</td>");
				testcaseStepsInfo
					.append("<td><font color=\"red\">failed</font></td>");
			}
		}
		if(db2flag==0)
			testcaseStepsInfo.append("<td>").append(imagesLink.toString()).append(
				"</td>");
		else
			testcaseStepsInfo.append("<td>").append(theStep.getStrResult().toString()).append(
			"</td>");
		
		testcaseStepsInfo.append("<td>").append(theStep.getStepDataText()).append("</td>");
		testcaseStepsInfo.append("</tr>");
		imagesLink = new StringBuffer();
	}
	


	/**
	 * @param scriptName
	 * @param summary
	 */
	private void genTestcaseLog(String scriptName, String callerName,
			String summary) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer
				.append("<html><head><title>")
				.append("Log for " + scriptName)
				.append(
						"</title><meta http-equiv=\"content-type\" content=\"text/html; charset=gb2312\"> </head><body>")
				.append(testcaseStepsInfo.toString()).append(summary).append(
						"</body></html>");
		String fullPath = (String) RationalTestScript
				.getOption(IOptionName.DATASTORE)
				+ "_logs\\" + callerName;
		String logFileName = scriptName.substring(
				scriptName.lastIndexOf(".") + 1, scriptName.length());
		FileWriter fw = null;
		try {
			File directory = new File(fullPath);
			if (directory.exists()) {
				if (directory.isDirectory()) {
					fw = new FileWriter(new File(directory, logFileName
							+ ".html"));
					fw.write(stringBuffer.toString());
					fw.close();
				}
			}
			directory = null;
		} catch (IOException ioe) {
			fw = null;
		}
	}

	public int getDefaultLogLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDefaultLogMethod() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Initialize or operate local static variables
	 * 
	 * @return
	 */
	public void initialize(TestCase tc) {
		// testcaseXmlOps = new TestcaseXmlOps(testcaseFileFullPathName);
		// testcaseXmlOps.parseTestcaseXmlFile();
		/*
		 * Added by jeffreybian
		 */
		this.theCase = tc;
		isCurrTestcasePassed = true;
		currTestcaseVPCount = 0;
		currTestcasePassedVPCount = 0;
		casesExecuted++;

		testcaseStepsInfo = new StringBuffer();
		testcaseStepsInfo
				.append("<br>**Testcase steps summary**")
				.append(
						"<TABLE WIDTH=\"100%\" border=\"1\" summary=\"Log testcase steps\">")
				.append("<tr>").append(
						"<td width=\"%2\" align=\"center\">Step</td>").append(
						"<td width=\"%10\" align=\"center\">Transaction</td>")
				.append("<td width=\"%15\" align=\"center\">Verification</td>")
				.append("<td width=\"%15\" align=\"center\">result</td>")
				.append("<td width=\"%8\" align=\"center\">summary</td>")
				.append("<td width=\"%30\" align=\"center\">snapshot</td>")
				.append("<td width=\"%20\" align=\"center\">data</td>")	//add data column
				.append("</tr>");

		stepCounter = 1;
		imagesLink = new StringBuffer();

		// initialize only once
		if (mainScriptStartTime == null)
			mainScriptStartTime = new Date();
		testCaseStartTime = new Date();
	}

	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// This stubby method is placeholder for further proxy callings.
		return null;
	}

	public void log(String info) {
		log (info, LVL_INFO);

	}

	public void log(String info, int level) {
		log (info, level);
	}
	/**
	 * For further modification
	 */
	public void log(String info, int level, int method) {
		
		System.out.println(info);
	}

	/**
	 * Summarize current testcase
	 * 
	 */
	public void logCurrTestcaseSummary(String scriptName, String callerName) {
		testcaseStepsInfo.append("</table><br>");
		logScriptInfo(testcaseStepsInfo.toString());

		String currTestcaseElapsedTime = "";
		String totalElapsedTime = "";

		long milliSeconds, hours, mins, seconds, leftMilliSeconds;

		milliSeconds = ((new Date()).getTime() - testCaseStartTime.getTime());
		hours = milliSeconds / (60 * 60 * 1000);
		leftMilliSeconds = milliSeconds % (60 * 60 * 1000);
		mins = leftMilliSeconds / (60 * 1000);
		leftMilliSeconds %= (60 * 1000);
		seconds = leftMilliSeconds / 1000;
		leftMilliSeconds %= 1000;

		currTestcaseElapsedTime = (new StringBuffer(Long.toString(hours)))
				.append("h").append(mins).append("min").append(seconds).append(
						"s").append(leftMilliSeconds).append("ms").toString();

		milliSeconds = ((new Date()).getTime() - mainScriptStartTime.getTime());
		hours = milliSeconds / (60 * 60 * 1000);
		leftMilliSeconds = milliSeconds % (60 * 60 * 1000);
		mins = leftMilliSeconds / (60 * 1000);
		leftMilliSeconds %= (60 * 1000);
		seconds = leftMilliSeconds / 1000;
		leftMilliSeconds %= 1000;

		totalElapsedTime = (new StringBuffer(Long.toString(hours))).append("h")
				.append(mins).append("min").append(seconds).append("s").append(
						leftMilliSeconds).append("ms").toString();

		String testcaseResult = "";
		if (isCurrTestcasePassed)
			testcaseResult = "<font color=green>passed</font>";
		else
			testcaseResult = "<font color=red>failed</font>";

		String summary = new StringBuffer("<br>**Testcase summary**<br>")
				.append(
						"<TABLE WIDTH=\"100%\" border=\"1\" summary=\"Log testcase steps\">")
				.append("<tr>").append(
						"<td>Current testcase elapsed time: </td>").append(
						"<td>" + currTestcaseElapsedTime + "</td>").append(
						"</tr>").append("<tr>").append(
						"<td>Total elapsed time: </td>").append(
						"<td>" + totalElapsedTime + "</td>").append("</tr>")
				.append("<tr>").append("<td>Current testcase steps: </td>")
				.append("<td>" + (theCase.getSteps().size() - 1) + "</td>")
				.append("</tr>").append("<tr>").append(
						"<td>Total verification points: </td>").append(
						"<td>" + currTestcaseVPCount + "</td>").append("</tr>")
				.append("<tr>").append("<td>Passed verification points: </td>")
				.append("<td>" + currTestcasePassedVPCount + "</td>").append(
						"</tr>").append("<tr>").append(
						"<td>Current testcase: </td>").append(
						"<td>" + testcaseResult + "</td>").append("</tr>")
				.append("</table>").toString();
		logScriptInfo(summary);

		genTestcaseLog(scriptName, callerName, summary);
	}

	/**
	 * Logs an error message
	 * 
	 * @param sErrorMessage
	 *            the text of the error message to log
	 */
	public void logError(String sErrorMessage) {
		logError(sErrorMessage, false, this.getDefaultLogMethod());
	}

	public void logError(String sErrorMessage, boolean isFatal) {
		logError(sErrorMessage, isFatal, this.getDefaultLogMethod());
	}

	public void logError(String sErrorMessage, boolean isFatal, int method) {
		isCurrTestcasePassed = false;
		failedCases++;
		sErrorMessage = formatMessage(sErrorMessage);

		if (isFatal) {
			RTSLoggingWrapper.logError(formatMessage("**** Fatal Error ****"));
		}
		RTSLoggingWrapper.logError(sErrorMessage);
		System.err.println(sErrorMessage);
	}

	public void logError(String[] msgs) {
		for (String x : msgs) {
			logError(x);
		}
	}

	/**
	 * If we should use manual test to check a step, we should log it.
	 * 
	 */
	public void logManualTest() {
		logManualTest(-1);
		/*
		 * Deleted by jeffreybian totalVPCount++; currTestcaseVPCount++;
		 * 
		 * stepCounter++; testcaseStepsInfo.append("<tr>").append("<td>" +
		 * stepCounter + "</td>") .append("<td>" +
		 * testcaseXmlOps.getTransaction() + "</td>") .append("<td>" +
		 * testcaseXmlOps.getVerification() + "</td>") .append("<td>&nbsp;</td>").append( "<td><font
		 * color=orange>manual test</font></td>") .append("<td>&nbsp;</td>").append("</tr>");
		 * logStep();
		 */
	}

	/**
	 * If we should use manual test to check a step, we should log it.
	 * 
	 */
	public void logManualTest(ArrayList<BufferedImage> screenImageArray) {
		logManualTest(screenImageArray, -1);
		/*
		 * logScreenImageInfo("screen", screenImageArray); totalVPCount++;
		 * currTestcaseVPCount++; theStep = theCase.getSteps().get(stepCounter);
		 * stepCounter++; testcaseStepsInfo.append("<tr>").append("<td>" +
		 * stepCounter + "</td>") .append("<td>" +
		 * theStep.getStepTransText() + "</td>").append( "<td>" +
		 * theStep.getStepVpText() + "</td>").append( "<td>&nbsp;</td>").append( "<td><font
		 * color=orange>manual test</font></td>") .append("<td>").append(imagesLink.toString()).append("</td>")
		 * .append("</tr>"); imagesLink = new StringBuffer(); logStep();
		 * 
		 */
	}

	/**
	 * If we should use manual test to check a specified step, we should log it.
	 * 
	 * @param screenImageArray
	 * @param step
	 */
	public void logManualTest(ArrayList<BufferedImage> screenImageArray,
			int step) {
		if (screenImageArray != null) {
			logScreenImageInfo("screen", screenImageArray);
		}
		totalVPCount++;
		currTestcaseVPCount++;
		if (step != -1) {
			theStep = theCase.getSteps().get(stepCounter);
		} else {
			theStep = theCase.getSteps().get(step);
		}
		
		testcaseStepsInfo.append("<tr>").append("<td>" + stepCounter + "</td>")
				.append("<td>" + theStep.getStepTransText() + "</td>").append(
						"<td>" + theStep.getStepVpText() + "</td>").append(
						"<td>&nbsp;</td>").append(
						"<td><font color=orange>manual test</font></td>");
		if (screenImageArray != null) {
			testcaseStepsInfo.append("<td>").append(imagesLink.toString())
					.append("</td>")
					.append("<td>" + theStep.getStepDataText()+"</td>")
					.append("</tr>");
		}
		stepCounter++;
		imagesLink = new StringBuffer();
		logStep();
	}

	/**
	 * If we should use manual test to check a specified step, we should log it.
	 * 
	 * @param step
	 */
	public void logManualTest(int step) {
		logManualTest(null, step);
		/*
		 * Deleted by jeffreybian totalVPCount++; currTestcaseVPCount++;
		 * stepCounter++; testcaseStepsInfo .append("<tr>") .append("<td>" +
		 * stepCounter + "</td>") .append("<td>" +
		 * theStep.getStepTransText() + "</td>") .append("<td>" +
		 * theStep.getStepVpText() + "</td>") .append("<td>&nbsp;</td>")
		 * .append("<td><font color=orange>manual test</font></td>")
		 * .append( "<td><a href=rational_ft_user" + screenImageCounter +
		 * ".jpg><IMG src=rational_ft_user" + screenImageCounter + ".jpg
		 * height=120 width=160 align=middle></IMG> Click to view full size</A></td>")
		 * .append("</tr>"); logStep();
		 */
		/*
		 * testcaseXmlOps.getStep(step); stepCounter++;
		 * testcaseStepsInfo.append("<tr>").append ("<td>"+stepCounter+"</td>").append ("<td>"+testcaseXmlOps.getTransaction()+"</td>").append ("<td>"+testcaseXmlOps.getVerification()+"</td>").append ("<td>&nbsp;</td>").append ("<td><font
		 * color=orange>manual test</font></td>").append ("<td><a
		 * href=rational_ft_user"+screenImageCounter+".jpg><IMG
		 * src=rational_ft_user"+screenImageCounter+".jpg height=120 width=160
		 * align=middle></IMG> Click to view full size</A></td>").append ("</tr>");
		 * logStep();
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ibm.loggers.control.IPackageLogger#logPackageInfo(java.lang.String)
	 */
	public void logPackageInfo(String sInfoMessage) {
		super.logPackageInfo(sInfoMessage);
		// logScriptInfo(sInfoMessage);
	}

	/**
	 * Capture one or more screens, and log it/them
	 * 
	 * @param screenImageName
	 * @param screenImageArray
	 */
	protected void logScreenImageInfo(String screenImageName,
			ArrayList screenImageArray) {
		imagesLink = new StringBuffer();
		if (screenImageArray != null) {
			for (int i = 0; i < screenImageArray.size(); i++) {
				RationalTestScript.logInfo(screenImageName,
						(BufferedImage) screenImageArray.get(i));
				screenImageCounter++;
				imagesLink
						.append(
								"<a href=rational_ft_user"
										+ screenImageCounter
										+ ".jpg><img src=rational_ft_user"
										+ screenImageCounter
										+ ".jpg height=120 width=160 align=middle></img> Click to view full size</a>")
						.append("<br>");
			}
		}
	}

	/**
	 * Capture screen and log it.
	 * 
	 * @param fileName
	 * @param bufferedImage
	 */
	protected void logScreenImageInfo(String screenImageName,
			BufferedImage bufferedImage) {
		RationalTestScript.logInfo(screenImageName, bufferedImage);
		screenImageCounter++;

		imagesLink = new StringBuffer();
		imagesLink
				.append("<a href=rational_ft_user"
						+ screenImageCounter
						+ ".jpg><IMG src=rational_ft_user"
						+ screenImageCounter
						+ ".jpg height=120 width=160 align=middle></IMG> Click to view full size</a>");
	}

	/*
	 * Deleted by jeffreybian, since there are no client to use this method
	 * public void logStepInfo(ArrayList screenImageArray){
	 * logScreenImageInfo("screen", screenImageArray);
	 * testcaseXmlOps.getNextStep(); stepCounter++; testcaseStepsInfo.append("<tr>").append ("<td>"+stepCounter+"</td>").append ("<td>"+testcaseXmlOps.getTransaction()+"</td>").append ("<td>&nbsp;</td>").append ("<td>&nbsp;</td>").append ("<td>&nbsp;</td>").append ("<td>").append
	 * (imagesLink.toString()).append ("</td>").append ("</tr>"); imagesLink =
	 * new StringBuffer(); logStep(); }
	 */
	/**
	 * 
	 */
	private void logStep() {
		StringBuffer stepInfo = new StringBuffer();
		String transaction = theStep.getStepTransText();
		stepInfo.append("End of step ").append(stepCounter).append(" : ")
				.append(transaction).append("<br>");
		logScriptInfo(stepInfo.toString());
	}

	/**
	 * Summarize all testcase executed and their verification points
	 * 
	 */
	public void logTestcasesSummary() {
		String summary = new StringBuffer("**Testcases summary**").append(
				"<br>Testcases executed:").append(casesExecuted).append(
				"<br>Testcases failed:").append(failedCases).append(
				"<br>Total verification points:").append(totalVPCount).append(
				"<br>Passed verification points:").append(passedVPCount)
				.toString();

		logScriptInfo(summary);
	}

	public void logWarning(String warnMsg) {
		log(warnMsg, LVL_WARNING);
		
	}

	public void logWarning(String warnMsg, int method) {
		log(warnMsg, LVL_WARNING, method);
	}

	public void setDefaultLogLevel(int level) {
		setLogLevel(level);	
	}

	public void setDefaultLogMethod(int method) {
		logMethod = method;
	}
}