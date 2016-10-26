/*
 * Created on Sep 29, 2007
 * @author kevin.huangfu
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.sap.*;
import com.rational.test.ft.object.interfaces.TestObject;
import hlta.testexec.testcontrol.java.TestCase;
public class SapTextFieldClick extends ActionBase {

	public SapTextFieldClick(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		try{
			Sap sap = Sap.getInstance();
			sap.textFieldClick((TestObject)object);
		}catch(NullPointerException e){
			System.err.println("SapTextFieldClick:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapTextFieldClick:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapTextFieldClick:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
