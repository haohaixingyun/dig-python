/*
 * Created on May 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.web.common.ScreenImageOps;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
/**
 * @author Wangzheng
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CaptureScreen extends ActionBase {

	/**
	 * @param object The object.
	 * @param param	String Ignored.
	 * @param extParam Ignored.
	 */
	public CaptureScreen(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		// Ignore the extParam
		
		try 
		{
			if (object!=null)
			{
				GuiTestObject gto = (GuiTestObject)object;
				gto.hover();
			}
			Log.imageList.add(ScreenImageOps.captureScreenImage());
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

	public static void main(String[] args) {
	}
}
