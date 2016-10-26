/*
 * Created on Sep 28, 2007
 * @author kevin.huangfu
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.sap.*;
import com.rational.test.ft.object.interfaces.TestObject;

public class SapComboboxSelect extends ActionBase {

	public SapComboboxSelect(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		try{
			Sap sap = Sap.getInstance();
			sap.comboboxSelect((TestObject)object, (String)param);
		}catch(NullPointerException e){
			System.err.println("SapComboboxSelect:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapComboboxSelect:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapComboboxSelect:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
