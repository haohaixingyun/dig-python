package actions;

import java.io.File;
import java.io.FileWriter;

import com.rational.test.ft.object.interfaces.TestObject;

import hlta.testexec.platform.rft7.sap.Sap;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;
public class SapGetStatusInfo extends ActionBase {

	public SapGetStatusInfo(Object object, Object param, Object extParam, Object[] args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		Sap sap = Sap.getInstance();
		try{
			String returnedNumber = sap.getStatusInfo((TestObject)object, Integer.parseInt(param.toString()), Integer.parseInt(extParam.toString()));
			System.out.println("path="+args[0]);
			FileWriter fw = new FileWriter(new File(args[0].toString()), true);
			fw.write("returnedNumber="+returnedNumber+"\n");
			fw.close();
		}catch(NullPointerException e){
			System.err.println("SAPGetStatusInfo:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SAPGetStatusInfo:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SAPGetStatusInfo:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}
}

