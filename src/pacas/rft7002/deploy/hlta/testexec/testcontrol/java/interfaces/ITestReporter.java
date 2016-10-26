/*
 * Created on Jan 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java.interfaces;

/**
 * ITestReport:
 * 	Interface for generating overall or detailed report. 
 * 	Singleton.
 * @version 1.0
 * @author Jeffrey Bian
 *
 *
 */
public interface ITestReporter {
	void setDefaultReportMethod(int method);
	int getDefaultReportMethod();
	void addStepResult(String transText, String passedText, String failedText, int opt);
	void generateSummaryReport();
	
}
