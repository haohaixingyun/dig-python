/*
 * Created on Jan 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java.interfaces;

/**
 * ITestException:
 * 	Interface exception and error handling on test level. 
 * 	
 * @version 1.0
 * @author Jeffrey Bian
 *
 *
 */
public interface ITestException  {
	//	 For general except level
	static int  TE_LVL_LOG = 0,
				TE_LVL_INFO = 1,
				TE_LVL_WARN = 2,
				TE_LVL_EXCEPT = 3,
				TE_LVL_FATAL = 4
				;
	// For general description for level: title			
	final String[] levels = {
				"LOG!", 
				"INFO!", 
				"WARNING!",
				"EXCEPTION!",
				"FATAL EXCEPTION"
				}
				;
	// methods
	int handle();
	String what();
	String reason();
	int getLevel();
	// For debug
	void dump();
}

