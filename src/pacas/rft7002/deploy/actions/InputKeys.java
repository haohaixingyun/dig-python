/*
 * Created on May 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InputKeys extends ActionBase {

	/**
	 * @param object
	 * @param param	 String. keys for input.
	 * @param extParam Ignored.
	 */
	public InputKeys(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub

		String keys = (String)param;

		try {
			RationalTestScript.getScreen().inputKeys(keys);				
		} catch (NullPointerException e) {
			e.printStackTrace();
			resultBoolean = false;
		} catch (Exception e) {
			System.err.println("Unknown error!");
		}
	}

	public static void main(String[] args) {
	}
}
