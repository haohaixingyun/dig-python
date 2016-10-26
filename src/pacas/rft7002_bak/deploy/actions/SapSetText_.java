package actions;

import com.rational.test.ft.object.interfaces.TestObject;

import hlta.testexec.platform.rft7.sap.Sap;
import hlta.testexec.testcontrol.java.ActionBase;

public class SapSetText_ extends ActionBase {
	public SapSetText_(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		Sap sap = Sap.getInstance();
		try{
		String tempString = Sap.getVariableValueByName((String)param);
		sap.setText((TestObject)object, tempString);
		}catch(NullPointerException e){
			System.err.println("SapSetText:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapSetText:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapSetText:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
