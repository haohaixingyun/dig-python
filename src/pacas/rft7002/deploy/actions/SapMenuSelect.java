/*
 * Created on Sep 28, 2007
 * @author kevin.huangfu
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.sap.*;
import com.rational.test.ft.object.interfaces.TestObject;

public class SapMenuSelect extends ActionBase {

	public SapMenuSelect(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		try{
			Sap sap = Sap.getInstance();
			sap.menuSelect((String)object);
		}catch(NullPointerException e){
			System.err.println("SapMenuSelect:NullPointerException: object["+object+"]");
		}catch(ClassCastException e){
			System.err.println("SapMenuSelect:ClassCastException: object["+object+"]");
		}catch(Exception e){
			System.err.println("SapMenuSelect:Exception: object["+object+"]");
		}
	}

}
