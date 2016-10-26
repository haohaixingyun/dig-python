/*
 * Created on Sep 28, 2007
 * @author kevin.huangfu
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.object.interfaces.TestObject;
import hlta.testexec.platform.rft7.sap.Sap;

public class SapClickButton extends ActionBase {

	public SapClickButton(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		Sap sap = Sap.getInstance();
		try{
			sap.clickButton((TestObject)object);
		}catch(NullPointerException e){
			System.err.println("SapClickButton:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapClickButton:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapClickButton:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
