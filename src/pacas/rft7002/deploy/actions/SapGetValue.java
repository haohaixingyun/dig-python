package actions;

import com.rational.test.ft.object.interfaces.TestObject;

import hlta.testexec.platform.rft7.sap.Sap;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;

public class SapGetValue extends ActionBase {

	public SapGetValue(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		
		Sap sap = Sap.getInstance();
		try{
			String value = sap.getValueFromControl((String)param, (TestObject)object);
			//ap.setVariables((String)extParam, value);
			// Added by jeffrey bian, use uniform mode to persist values
			DataHelper.setGlobalVar((String)extParam, value);
		}catch(NullPointerException e){
			System.err.println("SapClickButton:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapClickButton:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapClickButton:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
