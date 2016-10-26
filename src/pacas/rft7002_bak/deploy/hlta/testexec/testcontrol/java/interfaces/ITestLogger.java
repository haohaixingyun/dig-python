/*
 * Created on Jan 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java.interfaces;
import java.lang.reflect.*;
/**
 * ITestLog:
 * 	Interface for logging test information. 
 * 	There are five levels of logging:
 * 		INFO, WARNING, ERROR, FATAL_ERROR, USER.
 * 	And 4 supported methods:
 * 		CON, TXT, HTML, USER.
 * @version 1.0
 * @author Jeffrey Bian
 *
 *
 */
public interface ITestLogger extends InvocationHandler {
	final int LVL_INFO = 0,
			LVL_WARNING = 1,
			LVL_ERROR = 2,
			LVL_FATAL_ERROR = 3,
			LVL_USER = 15;
	final int LM_CON = 0,
			LM_TXT = 1,
			LM_HTML = 2,
			LM_XML = 3,
			LM_USER =15;

	void setDefaultLogLevel(int level);
	int getDefaultLogLevel();
	void setDefaultLogMethod(int method);
	int getDefaultLogMethod();
	
	void log(String info); 
	void log(String info, int level); 
	void log(String info, int level, int method);
	void logError(String errMsg);
	void logError(String errMsg, boolean isFatal);
	void logError(String errMsg, boolean isFatal,int method);
	void logError(String[] msgs);
	void logWarning(String warnMsg);
	void logWarning(String warnMsg, int method);
	
}


