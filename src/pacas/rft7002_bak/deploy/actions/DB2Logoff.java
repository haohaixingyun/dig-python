package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import db.*;

public class DB2Logoff extends ActionBase {

	public DB2Logoff(Object object, Object param, Object extParam, Object[] args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		AutoQuery query = new AutoQuery();
		query.disconnect();
	}

}
