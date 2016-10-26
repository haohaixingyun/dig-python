// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.recovery.control;


/**
 * This class is used to set the BaseState Recovery System to be used by the ibm package.<br>
 * By default, the BaseState is set to NULL, meaning the package will not try to recover the system upon a fatal error.<br>
 * Some loggers will use this class, however, to call a basestate, so you may want to set your BaseSate here.
 * 
 * You can either use one of the BaseStates in ibm\recovery or create your own.<p> 
 * 
 * To create your own basestate, implement the IBaseState interface,<br>
 * and then set the basestate for the package to an instance of your class by passing it into PackageRecoveryController.setBaseState.<br>
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class PackageRecoveryController {
	private static IBaseState goBaseState = null;

//*******************Setters And Getters******************************	

	/**Sets the base state to be used by the ibm package.<p>
	 * The logger must implement the IPackageLogger interface.
	 * 
	 * @param basestate the base state object to be used by the ibm package
	 */	
	public static void setBaseState(IBaseState basestate)
	{
		goBaseState = basestate;
	}
	
	/**
	 * Returns the base state object which the ibm package is set to use.<p>
	 * 
	 * @return the base state the ibm package is using
	 */	
	public static IBaseState getBaseState()
	{
		return goBaseState;
	}
	
//****************Methods for use by package************************
	/**
	 * This method calls callBaseState on the base state contained within this class <br>
	 * If no BaseState is set, it will do nothing.
	 */
	public static void callBaseState()
	{
		if (goBaseState != null)
			goBaseState.callBaseState();
	}
}
