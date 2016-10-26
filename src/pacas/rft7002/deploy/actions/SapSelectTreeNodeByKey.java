/*
 * Created on October 31, 2007
 * @author kevin.huangfu
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.sap.*;
import com.rational.test.ft.object.interfaces.TestObject;

public class SapSelectTreeNodeByKey extends ActionBase {

	public SapSelectTreeNodeByKey(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		Sap sap = Sap.getInstance();
		try{
			sap.selectTreeNodeByKey((TestObject)object, (String)param);
		}catch(NullPointerException e){
			System.err.println("SapSelectTreeNodeByKey:NullPointerException: object["+object+"]");
			e.printStackTrace();
		}catch(ClassCastException e){
			System.err.println("SapSelectTreeNodeByKey:ClassCastException: object["+object+"]");
			e.printStackTrace();
		}catch(Exception e){
			System.err.println("SapSelectTreeNodeByKey:Exception: object["+object+"]");
			e.printStackTrace();
		}

	}
// can not perform to click a link in Tree throuth API that RFT provided
}
