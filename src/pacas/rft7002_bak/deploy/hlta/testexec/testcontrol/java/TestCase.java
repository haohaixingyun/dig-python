/*
 * Created on Jan 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java;

import java.util.ArrayList;

import java.util.regex.*;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.image.BufferedImage;
import java.lang.reflect.*;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import hlta.testexec.testcontrol.java.TestCase.IResult;
import hlta.testexec.testcontrol.java.TestCase.Step;
import hlta.testexec.testcontrol.java.interfaces.*;

/*
 * -------------------------------------------------------------------
 * The code follows are added by Jeffrey Bian.
 * Decription: Code for storing the testcase running information and
 * 	controlling the testing process.
 * Jan 19, 2007
 */
/**
 * Testcase:
 * 	Class for controling the Testcase. 
 * 	Note Testcase is a singleton, because this class object is responsible
 * 	for describing the testcase that is currently running.
 * @version 1.0
 * @author Jeffrey Bian
 *
 *
 */

public class TestCase  {
	// For instance control
	protected static TestCase tc = null;
	protected static int instanceNum = 0;
	// For testing logic
	/**
	 * Inner interface for storing the constants for results.
	 * @author Jeffrey Bian
	 *
	 */
	public interface IResult {
		int PASSED = 1,
			FAILED = 0,
			MANUAL = 2,
			NOT_RUN = 15,
			UNKNOWN_STATE = 255;
	}
	
	 public void removeSteps() {
			steps = null;
		}
	/**
	 * Enclosing class Verification
	 * @author Ryx
	 */
	/**
	 * Enclosing class Verification
	 * @author Ryx
	 */
	public static final class Verification {
		private String type;

		private int retry;

		private String object;

		private String value;

		private String param;
		
		public Verification(String type, String object,
				String value, String param, int retry) {
			this.type = type;
			this.object = object;
			this.value = value;
			this.param = param;
			this.retry = retry;
		}

		public String toString() {
			return "[type:" + type + "]" + "[object:" + object + "]" + "[value:" + value + "]" + "[retry:"
					+ retry + "]" + "[param:" + param + "]";
		}

		/**
		 * @return Returns the object.
		 */
		public String getObject() {
			return object;
		}
		/**
		 * @return Returns the param.
		 */
		public String getParam() {
			return param;
		}
		/**
		 * @return Returns the retry.
		 */
		public int getRetry() {
			return retry;
		}
		/**
		 * @return Returns the type.
		 */
		public String getType() {
			return type;
		}
		/**
		 * @return Returns the value.
		 */
		public String getValue() {
			return value;
		}

	
	}
	/** 	
	 * Enclosing class Step.
	 * 
	 * @author Jeffrey Bian
	 */
	public static final class Step {

		private String stepTransText = null;
		private String stepVpText = null;
		private String stepPassedText = null;
		private String stepFailedText = null;
		
		private String stepDataText = null;
		private Verification[] vers = null;
		
		private boolean needReRunIfSucceeded = true;
		private int stepResult = IResult.NOT_RUN;
		private StringBuffer stepStrResult = new StringBuffer();
		private int times = 0;
		
		public Step () {
			this("","","","","");
		}
		public Step (String transText, 
			String vpText, 
			String passedText, 
			String failedText, String dataText) {
			stepTransText = transText;
			stepVpText = vpText;
			stepPassedText = passedText;
			stepFailedText = failedText;
			stepDataText = dataText;
		}
		
		public void setReRun (boolean reRunIfSucceeded) {
			needReRunIfSucceeded = reRunIfSucceeded;
		}
		public boolean getReRun () {
			return needReRunIfSucceeded;
		}
		
		public void setResult (int result) {
			stepResult = result;
		}
		
		public int getResult() {
			return stepResult;
		}
		
		public void addStrResult(String result){
			stepStrResult.append(result);
		}
		public void clearStrResult(){
			stepStrResult = new StringBuffer();
		}
		public StringBuffer getStrResult(){
			return stepStrResult;
		}
		public int getTimes() {
			return times;
		}
		public void stepBegin () {
			times ++;
		}
		public void stepEnd() {
		}
		
			
		
		public void setStepDataText (String dataText) {
			stepDataText += dataText;
		}
		
		public String getStepDataText() {
			return stepDataText;
		}
		/**
		 * @return the stepFailedText
		 */
		public String getStepFailedText() {
			return stepFailedText;
		}
		/**
		 * @return the stepPassedText
		 */
		public String getStepPassedText() {
			return stepPassedText;
		}
		/**
		 * @return the stepTransText
		 */
		public String getStepTransText() {
			return stepTransText;
		}
		/**
		 * @return the stepVpText
		 */
		public String getStepVpText() {
			return stepVpText;
		}
		/**
		 * @return the vers
		 */
		public Verification[] getVers() {
			return vers;
		}
		
		
		
		
	}
	

	
	/*
	 * For case controls.
	 */
	public static int MAX_RETRIES = 32;
	protected String currentCaseName = null;
	protected String currentCasePackageName = null;
	protected int maxRetries = 5;
	protected ITestLogger testLog;
	protected ITestReporter testReport;
	protected ITestException testExcept;
	protected Step currentStep = null;
	protected int currentStepIndex = 0;
	protected String author = null;
	
	private boolean pairMatching = false;

	/*
	 * For step controls.
	 */
	protected int totalSteps = 0;
	protected ArrayList<Step> steps = null;
	private ArrayList dependentCases;
	
	protected TestCase () {
		
	}
	public static ITestLogger getDefaultLogger() {
		return new DefaultTestLogger(null);
	}
	public static ITestReporter getDefaultReporter() {
		return new DefaultTestReporter();
	}
	public static TestCase getCaseInstance(
				String currentCaseName,
				ITestLogger testLog,
				ITestReporter testReport) {
		return getCaseInstance(currentCaseName, null, testLog,testReport);
	}
	
	public static TestCase getCaseInstance(
			String currentCaseName,
			ArrayList dependentCases,
			ITestLogger testLog,
			ITestReporter testReport) {
		if (instanceNum==0) {
			tc = new TestCase();
			instanceNum++;
		}
		tc.testLog = testLog;
		tc.testReport = testReport;
		tc.initCase(currentCaseName, dependentCases);
		return tc;
	}
	
	public void initCase (String currentCaseName, ArrayList dependentCases) {
		this.currentCaseName = currentCaseName;
		if (dependentCases!=null) {
			tc.dependentCases = dependentCases;
		}	
	}
	public void setHeader(String key, String data) {
		// Set header information and format the strings
		
	}
	public String getHeader(String key) {
		// Returns the information by key
		
		return new String();
	}
	// ** Important **
	// Step index starts at 1
	// 
	public boolean addStep (Step step) {
		if (steps==null) {
			steps = new ArrayList<Step>();
		}
		int nPos = steps.size();
		if (nPos == 0) {
		    // Add a dummy at pos 0
		    steps.add(null);
		    steps.add(step);
		} else {
		    // Add to the tail
		    steps.add(step);
		}
		
		return true;		
	}
	public boolean addSteps(ArrayList steps) {
	    int nStep = steps.size();
		for (int i = 0; i < nStep;++i) {
		    addStep((Step)(steps.get(i)));
		}
		return true;
	}
	public boolean addSteps(Step[] steps) {
	    int nStep = steps.length;
		for (int i = 0; i < nStep;++i) {
		    addStep((Step)(steps[i]));
		}
		return true;
	}
	
	public void setMaxRetries (int maxRetries) {
		if (maxRetries<1 || maxRetries > MAX_RETRIES ) {
	//		testLog.logWarning(
	//				ITestException.gsE_WARNING + ITestException.gsE_OUT_OF_BOUND
	//				);
			this.maxRetries = 5; // Reset to default 
		}
		this.maxRetries = maxRetries;
	}
	public int getMaxRetries () {
		return maxRetries;
	}
	public void setStepReRun (int stepIndex, boolean needReRunIfSucceeded) {
		((Step)(steps.get(stepIndex))).setReRun(needReRunIfSucceeded);
	}
	public boolean getStepReRun (int stepIndex) {
		return ((Step)(steps.get(stepIndex))).getReRun();
	}
	
	public void setStepResult (int stepIndex, int stepResult) {
		((Step)(steps.get(stepIndex+1))).setResult(stepResult);
	}
	public void setStepResult(int stepIndex, int... is) {
		// For policy:
		// If any of the steps fails the whole result is fail
		// 
		
		for (int i : is) {
			int stepResult=steps.get(stepIndex).getResult();
			
			if (i==IResult.PASSED && stepResult!=IResult.MANUAL) {
				((Step)(steps.get(stepIndex))).setResult(IResult.PASSED);
			}
			else if (i==IResult.MANUAL) {
				((Step)(steps.get(stepIndex))).setResult(IResult.MANUAL);
			}
			else if (i==IResult.FAILED) {
				((Step)(steps.get(stepIndex))).setResult(IResult.FAILED);
				break;
			}
			else if (i==IResult.UNKNOWN_STATE) {
				((Step)(steps.get(stepIndex))).setResult(IResult.UNKNOWN_STATE);
				break;
			}
		}
		
		
	}
	public int getStepResult (int stepIndex) {
		return steps.get(stepIndex).getResult();
	}
	
	/**
	 * 
	 * @return An int indicating what the result of current case is.
	 */
	public int getResult () {
		int currentCaseResult = IResult.NOT_RUN;
		if (steps!=null && steps.size()>0) {
			// If all steps are PASSED, case is passed;
			// If one of the steps need MANUAL, case is manual;
			// If one of the steps is FALIED, case is failed.
			// 
			int size = steps.size();
			// Step index starts from 1 in the ArrayList
			// element at index 0 is null
			for (int i = 1; i<size; ++i) {
			    if (((Step)(steps.get(i))).getResult() == IResult.NOT_RUN) {
			        currentCaseResult = IResult.NOT_RUN;
			    }
				if ( ((Step)(steps.get(i))).getResult() == IResult.FAILED) {
					currentCaseResult = IResult.FAILED;
					break;
				} else if (((Step)(steps.get(i))).getResult() == IResult.MANUAL) {
					currentCaseResult = IResult.MANUAL;
				} else if (((Step)(steps.get(i))).getResult() == IResult.PASSED) {
					currentCaseResult = IResult.PASSED;
				}
			}
		} 
		return currentCaseResult;
	}
	
	public Verification[] getStepVerifications(int index) {
		return steps.get(index).getVers();
	}
	
	public Step getStep(int index) {
		if (steps!=null) {
			if (index>0 && index<steps.size()) {
				return steps.get(index);
			}
		}
		return null;
	}
	public ArrayList<Step> getSteps() {
		return steps;
	}
}



/*
 * -------------------------------------------------------------------
 */
