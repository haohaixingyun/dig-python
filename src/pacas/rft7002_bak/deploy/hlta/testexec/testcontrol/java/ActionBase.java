package hlta.testexec.testcontrol.java;

/**
 * Created on May 28, 2007
 * This ActionBase is important. For all the Actions must inherit from this base
 * class.
 * @author Jeffrey
 * @version 1.5
 */
public abstract class ActionBase {
	protected static boolean vrun = false;
	protected static boolean debug = false;
	// Different presentation of internal data
	protected boolean resultBoolean = false;
	protected int resultInt = 0;
	protected String resultString = "";
	protected Object resultObject = null;
	
	protected BaseTestException except = null;
	protected String desc = null;
	protected ACTION_STATE state = ACTION_STATE.NOT_ACTIVE;
	/**
	 * The constructor of ActionBase support varargs, but only using first 3 params are encouraged.
	 * @param object	The main parameter of the Action.
	 * @param param		The param / sub action of the action.
	 * @param extParam	The extended parameter.
	 * @param args		If necessary, more information is passed thru the rest of varargs.
	 */
	public ActionBase(Object object, Object param,  Object extParam, Object... args) {
		
		desc = this.getClass().getName();
		// From Object tag, param tag, and extParam tag, get the
		// real values
		if (vrun) {
			System.out.println("Virtual Run: " + this.toString());
		} else {
			doAction(object, param, extParam, args);
		}
	}
	/**
	 * This is the extend point for users. must be overridden.
	 * @param object
	 * @param param
	 * @param extParam
	 * @param args TODO
	 * @param args
	 */
	public abstract void doAction(Object object, Object param, Object extParam, Object... args);

	public boolean getResultBoolean() {
		return resultBoolean;
	}
	public int getResultInt() {
		return resultInt;
	}
	public Object getResultObj () {
		return resultObject;
	}
	public String getResultString() {
		return resultString;
	}
	protected synchronized void setState(ACTION_STATE state) {
		this.state = state;
	}
	/**
	 * 
	 * @return The state of the current action instance.
	 */
	public synchronized ACTION_STATE queryState() {
		return state;
	}
	public static String[] delist (String strlist) {
		String[] list = strlist.split(",");
		int length = list.length;
		if (debug) {
			for (int i =0 ;i<length;++i) {
				System.out.println(list[i].toString());
			}
		}
		return list;
	}
	public Object getArg (String strlist, int pos, String type) {
		String[] list = strlist.split(",");
		int length = list.length;
		if (pos>=length) {
			System.err.println("Index out of bounds. Null returned.");
			return null;
		}
		if (debug) {
			for (int i =0 ;i<length;++i) {
				System.out.print("Args: ");
				System.out.print(list[i].toString());
			}
			System.out.println();
		}
		if (type.compareTo("int")==0) {
			return new Integer(list[pos]);
		} else if (type.compareTo("boolean")==0) {
			return new Boolean(list[pos]);
		} else {
			// Treat as String
			return new String(list[pos]);
		}
		
	}
	public static void setVirtualRun (boolean ifvrun) {
		vrun = ifvrun;
	}
	public static void main(String[] args) {
		delist("");
		int i = Integer.parseInt("31231313213");
		
	}
}
