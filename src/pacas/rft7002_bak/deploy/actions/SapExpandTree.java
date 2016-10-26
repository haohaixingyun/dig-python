/*
 * Created on Sep 28, 2007
 * @author kevin.huangfu
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.sap.*;
import com.rational.test.ft.object.interfaces.TestObject;
public class SapExpandTree extends ActionBase {

	
	public SapExpandTree(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		try{
		Sap sap = Sap.getInstance();
		sap.expandTreeByKey((TestObject)object, (String)param);
		}catch(NullPointerException e){
			System.err.println("SapExpandTree:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapExpandTree:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapExpandTree:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
	}

}
