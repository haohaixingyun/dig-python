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
import ibm.widgets.*;
/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetText extends ActionBase {

	/**
	 * @param object	GuiTestObject.
	 * @param param		String. The value to be inputted.
	 * @param extParam	Boolean.If check to see value is right after input.
	 */
	public SetText(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// Three types of controls could be accepted
		// textfield, checkbox, radiobox
		try {
			if (object==null) {
				System.err.println("SetText: object is null");
			} else {
				String text = param.toString();
				((WTextField)object).setTextThruClipboard(text);
				//((WTextField)object).setText((String)param);
			}
		} catch (NullPointerException e) {
			
		} catch (ClassCastException e) {
			System.err.println("Could not perform action \'" + desc + "\'");
		}
		
	}

	public static void main(String[] args) {
	}
}