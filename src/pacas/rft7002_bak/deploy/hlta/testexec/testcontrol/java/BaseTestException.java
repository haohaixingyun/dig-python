/*
 * Created on Mar 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.testcontrol.java;

import hlta.testexec.testcontrol.java.interfaces.*;

/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTestException extends Throwable implements ITestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int exceptCode = 0;
	protected String what = null;
	protected String reason = null; 
	protected int level = 0;
	protected Object handlerPattern = null;
	// The index of the ITestException is 1-based
	protected int index = 0;
	// The reflection
	protected String[] cl = null;
	protected String[] me = null;
	protected int[] line_no = null;

	protected static int count = 0;
	/*
	 * ---------------------------------------
	 * ITestException
	 */
	/* (non-Javadoc)
	 * @see experiments.ITestException#handle()
	 */
	public int handle() {
		// Dummy, a place holder
		if (this instanceof BaseTestException) {
			System.err.println("BaseTestException does not implement a valid handle() method.");
		}
		if (handlerPattern==null) {
			return 0;
		} else { 
			return 1;
		}
	}
	/* (non-Javadoc)
	 * @see experiments.ITestException#what()
	 */
	public String what() {
		// Classify excepts according to the exceptCode
		return what;
	}

	/* (non-Javadoc)
	 * @see experiments.ITestException#reason()
	 */
	public String reason() {
		// TODO Auto-generated method stub
		return reason;
	}
	/* (non-Javadoc)
	 * @see experiments.ITestException#getLevel()
	 */
	public int getLevel() {
		return level;
	}
	
	public void dump (){
		StackTraceElement ste = null ;
		// Get line number, method name and class name
		System.out.println("------ Exception ---------");
		System.out.println("Reason: " + reason());
		System.out.println("Desc: " + what());
		System.out.println("Level: " + getLevel());
		System.out.println("Except Code: " + exceptCode);
		System.out.println("------ Caller ---------");
		System.out.println(getMethodName(0));
		System.out.println(getClassName(0));
		System.out.println(getLineNo(0));
		System.out.println("------ Topmost ---------");
		System.out.println(getMethodName());
		System.out.println(getClassName());
		System.out.println(getLineNo());
			
	}
	/*
	 * ITestException
	 * ---------------------------------------
	 */
	/*
	 * ---------------------------------------
	 * Class methods below
	 */
	protected BaseTestException(int exceptCode, String reason, int level, int index, Object handler) {
		this.index = index;
		this.level = level;
		this.exceptCode = exceptCode;
		this.reason = reason;
		this.handlerPattern = handler;
		// Get stack information
		try {
			StackTraceElement[] stes = this.getStackTrace();
			cl = new String[stes.length];
			me = new String[stes.length];
			line_no = new int[stes.length];
			for (int i = 0; i<stes.length;++i) {
				 cl[i] = stes[i].getClassName();
				 me[i] = stes[i].getMethodName();
				 line_no[i] = stes[i].getLineNumber();
			}
		} catch (Exception e) {
			System.err.println("Error getting stack trace and other reflect information.");
			System.exit(1);
		}
	}
		
	public BaseTestException(int exceptCode, String reason, int level, Object handler) {
		this(exceptCode, reason, level, count, handler);
		count++;	
	}
	
	public BaseTestException(int exceptCode, String reason, int level) {
		this(exceptCode, reason, level, count, null);
		count++;
	}
	
	public String getLevelStr () {
		return levels[level];
	}
	public String getClassName() {
		return getClassName(cl.length-1);
	}
	public String getMethodName() {
		return getMethodName(me.length-1);
	}
	public int getLineNo() {
		return getLineNo(line_no.length-1);
	}
	public String getClassName(int level) {
		String ret = null;
		if (cl!=null) {
			try {
				ret = cl[level];
			} catch (Exception e) {
				
			}
		}
		return ret;
	}
	public String getMethodName(int level) {
		String ret = null;
		if (cl!=null) {
			try {
				ret = me[level];
			} catch (Exception e) {
				
			}
		}
		return ret;
	}
	public int getLineNo(int level) {
		int ret = -1;
		if (cl!=null) {
			try {
				ret = line_no[level];
			} catch (Exception e) {
				
			}
		}
		return ret;
	}
	public static void main(String[] args) {
	}
}
