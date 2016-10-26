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
public class Click extends ActionBase {

	/**
	 * @param object
	 * @param param	String. 'Double' for double click.
	 * @param extParam Ignored.
	 */
	public Click(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam,TestCase.Step step,Object... args) {

		// TODO Auto-generated method stub
		// Ignore the extParam
		if (object!=null) {
			try {
				if (object==null) {
					System.err.println("Click: object is null.");
				} else {
					GuiTestObject gto = (GuiTestObject)object;
					if (param=="double") {
						gto.doubleClick();
					} else {
						gto.click();
					//	System.out.println("clicked!");
					}
				}
				
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				System.err.println("Cannot perform action \'" + desc + "\' on object " + object.toString() );
				resultBoolean = false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				resultBoolean = false;
			} catch (Exception e) {
				System.err.println("Unknown error!");
			}
		}
	
	}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		// Ignore the extParam
		if (object!=null) {
			try {
				if (object==null) {
					System.err.println("Click: object is null.");
				} else {
					GuiTestObject gto = (GuiTestObject)object;
					if (param=="double") {
						gto.doubleClick();
					} else {
						gto.click();
					//	System.out.println("clicked!");
					}
				}
				
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				System.err.println("Cannot perform action \'" + desc + "\' on object " + object.toString() );
				resultBoolean = false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				resultBoolean = false;
			} catch (Exception e) {
				System.err.println("Unknown error!");
			}
		}
	}

	public static void main(String[] args) {
	}
}
