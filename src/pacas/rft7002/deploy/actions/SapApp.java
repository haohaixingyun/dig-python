/*
 * Created by Kevin.Huangfu on Sept. 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.platform.rft7.sap.Sap;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.rational.test.ft.script.RationalTestScript;

import hlta.testexec.testcontrol.java.ActionBase;

public class SapApp extends ActionBase {
	
	public SapApp(Object object, Object param, Object extParam,Object... args){
		super(object, param, extParam,args);
	}
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		Browse.setBrowserStart(false);
		Sap sap = Sap.getInstance();
		try{
			sap.startSap((String)object,(String)param);
		}catch(NullPointerException e){
			System.err.println("SapApp:NullPointerException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(ClassCastException e){
			System.err.println("SapApp:ClassCastException --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}catch(Exception e){
			System.err.println("SapApp:Exception --- object["+object+"] : param["+param+"] : extParam["+extParam+"]");
		}
		
	}
	

}
