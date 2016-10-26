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
 * Interface to allow the inheritance of different Log Level constants.
 * 
 * @author TSnow
 * @version 2.2
 * Last Modified: 04/07/04
 */
public interface IPackageLoggerConstants {

	/**Constant to indicate no logging*/
	public static final int PACKAGELOGLEVEL_NONE = 100;

	/**Constant to indicate logging of errors only*/	
	public static final int PACKAGELOGLEVEL_ERRORS_ONLY = 90;

	/**Constant to indicate logging of errors and warnings only*/	
	public static final int PACKAGELOGLEVEL_ERRORS_WARNINGS = 80;

	/**Constant to indicate logging of errors, warnings, and basic information*/	
	public static final int PACKAGELOGLEVEL_BASICINFO = 70;
	
	/**Constant to indicate should log all widget setters that only involve clicks.<br>
	 * e.g. Wlink.click() will be logged, but WTextField.setText() will not
	 * Note: this includes checks and selects.
	 */
	public static final int PACKAGELOGLEVEL_WIDGET_CLICKS = 50;

	/**Constant to indicate should log all widget setters, including those that involve only clicks.<br>
	 * e.g. WTextField.setText() as well as WLink.click()
	 */					
	public static final int PACKAGELOGLEVEL_ALL_WIDGET_SETTERS = 40;

}
