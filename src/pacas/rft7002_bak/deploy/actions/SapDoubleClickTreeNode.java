package actions;

import com.rational.test.ft.object.interfaces.TestObject;

import hlta.testexec.platform.rft7.sap.Sap;
import hlta.testexec.testcontrol.java.ActionBase;

public class SapDoubleClickTreeNode extends ActionBase {


	public SapDoubleClickTreeNode(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		try{
			Sap sap = Sap.getInstance();
			sap.doubleClickNodeByKey((TestObject)object, (String)param);
			}catch(NullPointerException e){
				e.printStackTrace();
			}catch(ClassCastException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
