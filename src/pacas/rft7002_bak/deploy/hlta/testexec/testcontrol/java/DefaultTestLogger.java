/*
 * Created on Apr 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java;
import hlta.testexec.testcontrol.java.interfaces.ITestLogger;

import java.lang.reflect.*;
/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DefaultTestLogger implements ITestLogger {
	protected int defaultLogLevel = LVL_INFO;
	protected int defaultLogMethod = LM_CON;
	Object delegate = null;
	public DefaultTestLogger (Object delegate) {
		this.delegate = delegate;
	}
	public void setDefaultLogLevel (int level) {
		
	}
	public int getDefaultLogLevel() {
		return defaultLogLevel;
	}
	public void setDefaultLogMethod (int method) {
		
	}
	public int getDefaultLogMethod () {
		return defaultLogMethod;
	}
	
	public void log(String info) {
		log(info, defaultLogLevel, defaultLogMethod);
	}
	
	public void log(String info, int level) {
		log(info,level,defaultLogMethod);
	}
	/**
	 * TODO Implement a sample log method for CON(SOLE);
	 */
	public void log(String info, int level, int method) {
		// Forward
		System.out.println("LOGGED: "+ info + " ++ Level " + level +" ++ to channel " +method);
		
	}
	public void logError(String errMsg) {
		logError(errMsg,false);
	}
	public void logError(String errMsg, boolean isFatal) {
		logError(errMsg,isFatal,defaultLogMethod);
	}
	public void logError(String errMsg, boolean isFatal,int method) {
		int level = LVL_ERROR;
		if (isFatal==true) {
			level = LVL_FATAL_ERROR;
		}
		log(errMsg,level,method);
	}
	public void logWarning(String warnMsg) {
		logWarning(warnMsg,defaultLogMethod);
	}
	public void logWarning(String warnMsg, int method) {
		log(warnMsg,LVL_WARNING,method);
	}
	public void snapShot() {
		
	}
	
	/*
	 * 
	 * @author Ryx
	 *	Implements the InvocationHandler interface	
	 */
	public Object invoke(Object obj, Method method, Object[] params) throws Throwable
	{
		log("Method begin. Name = "+ method.getName());
		method.invoke(delegate,params);
		log("Method end. Name = "+ method.getName());
		return null;
	}
	
	public void logError(String[] errMsgs) {
		// TODO Auto-generated method stub
		System.out.print("[ERROR]");
		for (int i = 0; i < errMsgs.length; i++) {
			System.out.print("[" + errMsgs[i] + "]");
		}
		System.out.println();
	}
	//	 Test stub
	interface ITest {
		void run();
	}
	
	public static void main(String[] args) {
		
		class Test implements ITest {
			public void run() {
				System.out.println("Method 'run' called");
			}
		}

		Test newTest = new Test();
		ITest test = (ITest)Proxy.newProxyInstance(newTest.getClass().getClassLoader(),
											newTest.getClass().getInterfaces(),
											(InvocationHandler)(new DefaultTestLogger(newTest)));
		
		test.run();
	}
	
}
