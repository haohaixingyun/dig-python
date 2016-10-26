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
 * Interface to guarantee certain methods are in each logger<br>
 * If you create a custom logger, implement this interface.<p>
 *
 * @author tsnow
 * 
 * @version 2.2
 * Last Modified: 04/06/04
 */
public interface IBaseState {
	
	/**
	 * Puts the application to its base state, at the initial state to start testing.
	 */
	public void callBaseState();
}
