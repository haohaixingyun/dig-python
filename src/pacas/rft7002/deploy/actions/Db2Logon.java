package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import db.*;
import hlta.testexec.testcontrol.java.TestCase;
public class Db2Logon extends ActionBase {

	public Db2Logon(Object object, Object param, Object extParam, Object[] args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		// object: dbName; param: userName; extParam: pwd; args: [address, instance] 
		AutoQuery query = null;
		if(args!=null&&args.length>=1){
			if(args[0]==null&&args[1]==null){
				query = new AutoQuery((String)object,(String)param,(String)extParam);
				query.connect();
			} else {
				query = new AutoQuery((String)args[0],(String)args[1],(String)object,(String)param,(String)extParam);
				query.remoteConnect();
			}
		}
	}
}
