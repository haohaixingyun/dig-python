package hlta.testexec.platform.win32;

import ibm.loggers.control.PackageLoggingController;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.DocumentTestObject;
import com.rational.test.ft.object.interfaces.DomainTestObject;
import com.rational.test.ft.object.interfaces.IScreen;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.Anchor;
import com.rational.test.ft.script.IOptionName;
import com.rational.test.ft.script.Property;
import com.rational.test.ft.script.RationalTestScript;

/**
 * All methods in this class are specific to the IE windows, no other browsers
 * supported. All the windows are identified by an "int" as its handle. Because
 * the native DLL runs some specific COM functions, so maybe the Administrator
 * priviledge needed.
 * 
 * @author JeffreyBian
 * 
 */
public class Win32IEHelper {
	private static Hashtable<Long, Object> iftable = null;

	private static Hashtable<String, Integer> charkeyCodeTable = new Hashtable<String, Integer>();

	private static Hashtable<String, String> keyCodeTable = new Hashtable<String, String>();

	static {
		String path = System.getProperty("java.library.path").toString();
		path = path + ";" + System.getProperty("user.dir");
		System.setProperty("java.library.path", path);
		System.loadLibrary("w32helper");
		// Build the hash table
		charkeyCodeTable.put("VK_LBUTTON", 0X01);
		charkeyCodeTable.put("VK_RBUTTON", 0X02);
		charkeyCodeTable.put("VK_CANCEL", 0X03);
		charkeyCodeTable.put("VK_MBUTTON", 0X04);
		charkeyCodeTable.put("VK_BACK", 0X08);
		charkeyCodeTable.put("TAB", 0X09);
		charkeyCodeTable.put("ENTER", 0X0D);
		charkeyCodeTable.put("VK_SHIFT", 0X10);
		charkeyCodeTable.put("VK_CONTROL", 0X11);
		charkeyCodeTable.put("VK_ALT", 0X12);
		charkeyCodeTable.put("VK_PAUSE", 0X13);
		charkeyCodeTable.put("VK_CAPITAL", 0X14);
		charkeyCodeTable.put("ESC", 0X1B);
		charkeyCodeTable.put("VK_SPACE", 0X20);
		charkeyCodeTable.put("VK_PGUP", 0X21);
		charkeyCodeTable.put("VK_PGDN", 0X22);
		charkeyCodeTable.put("VK_END", 0X23);
		charkeyCodeTable.put("VK_HOME", 0X24);
		charkeyCodeTable.put("VK_LEFT", 0X25);
		charkeyCodeTable.put("VK_UP", 0X26);
		charkeyCodeTable.put("VK_RIGHT", 0X27);
		charkeyCodeTable.put("VK_DOWN", 0X28);
		charkeyCodeTable.put("VK_SNAPSHOT", 0X2C);
		charkeyCodeTable.put("VK_INSERT", 0X2D);
		charkeyCodeTable.put("VK_DELETE", 0X2E);
		charkeyCodeTable.put("VK_NUMPAD0", 0X60);
		charkeyCodeTable.put("VK_NUMPAD1", 0X61);
		charkeyCodeTable.put("VK_NUMPAD2", 0X62);
		charkeyCodeTable.put("VK_NUMPAD3", 0X63);
		charkeyCodeTable.put("VK_NUMPAD4", 0X64);
		charkeyCodeTable.put("VK_NUMPAD5", 0X65);
		charkeyCodeTable.put("VK_NUMPAD6", 0X66);
		charkeyCodeTable.put("VK_NUMPAD7", 0X67);
		charkeyCodeTable.put("VK_NUMPAD8", 0X68);
		charkeyCodeTable.put("VK_NUMPAD9", 0X69);
		charkeyCodeTable.put("VK_MULTIPLY", 0X6A);
		charkeyCodeTable.put("VK_ADD", 0X6B);
		charkeyCodeTable.put("VK_SEPARATOR", 0X6C);
		charkeyCodeTable.put("VK_SUBTRACT", 0X6D);
		charkeyCodeTable.put("VK_DECIMAL", 0X6E);
		charkeyCodeTable.put("VK_DIVIDE", 0X6F);
		charkeyCodeTable.put("VK_F1", 0X70);
		charkeyCodeTable.put("VK_F2", 0X71);
		charkeyCodeTable.put("VK_F3", 0X72);
		charkeyCodeTable.put("VK_F4", 0X73);
		charkeyCodeTable.put("VK_F5", 0X74);
		charkeyCodeTable.put("VK_F6", 0X75);
		charkeyCodeTable.put("VK_F7", 0X76);
		charkeyCodeTable.put("VK_F8", 0X77);
		charkeyCodeTable.put("VK_F9", 0X78);
		charkeyCodeTable.put("VK_F10", 0X79);
		charkeyCodeTable.put("VK_F11", 0X7A);
		charkeyCodeTable.put("VK_F12", 0X7B);
		charkeyCodeTable.put("VK_NUMLOCK", 0X90);
		charkeyCodeTable.put("VK_SCROLL", 0X91);
		charkeyCodeTable.put("VK_LSHIFT", 0XA0);
		charkeyCodeTable.put("VK_RSHIFT", 0XA1);
		charkeyCodeTable.put("VK_LCONTROL", 0XA2);
		charkeyCodeTable.put("VK_RCONTROL", 0XA3);
		charkeyCodeTable.put("VK_LALT", 0XA4);
		charkeyCodeTable.put("VK_RALT", 0XA5);
		/*
		 * keyCodeTable.put("VK_LBUTTON",0X01);
		 * keyCodeTable.put("VK_RBUTTON",0X02);
		 * keyCodeTable.put("VK_CANCEL",0X03);
		 * keyCodeTable.put("VK_MBUTTON",0X04);
		 * keyCodeTable.put("VK_BACK",0X08); keyCodeTable.put("VK_TAB",0X09);
		 * keyCodeTable.put("VK_RETURN",0X0D);
		 * keyCodeTable.put("VK_SHIFT",0X10);
		 * keyCodeTable.put("VK_CONTROL",0X11); keyCodeTable.put("VK_ALT",0X12);
		 * keyCodeTable.put("VK_PAUSE",0X13);
		 * keyCodeTable.put("VK_CAPITAL",0X14);
		 * keyCodeTable.put("VK_ESCAPE",0X1B);
		 * keyCodeTable.put("VK_SPACE",0X20); keyCodeTable.put("VK_PGUP",0X21);
		 * keyCodeTable.put("VK_PGDN",0X22); keyCodeTable.put("VK_END",0X23);
		 * keyCodeTable.put("VK_HOME",0X24); keyCodeTable.put("VK_LEFT",0X25);
		 * keyCodeTable.put("VK_UP",0X26); keyCodeTable.put("VK_RIGHT",0X27);
		 * keyCodeTable.put("VK_DOWN",0X28);
		 * keyCodeTable.put("VK_SNAPSHOT",0X2C);
		 * keyCodeTable.put("VK_INSERT",0X2D);
		 * keyCodeTable.put("VK_DELETE",0X2E);
		 * keyCodeTable.put("VK_NUMPAD0",0X60);
		 * keyCodeTable.put("VK_NUMPAD1",0X61);
		 * keyCodeTable.put("VK_NUMPAD2",0X62);
		 * keyCodeTable.put("VK_NUMPAD3",0X63);
		 * keyCodeTable.put("VK_NUMPAD4",0X64);
		 * keyCodeTable.put("VK_NUMPAD5",0X65);
		 * keyCodeTable.put("VK_NUMPAD6",0X66);
		 * keyCodeTable.put("VK_NUMPAD7",0X67);
		 * keyCodeTable.put("VK_NUMPAD8",0X68);
		 * keyCodeTable.put("VK_NUMPAD9",0X69);
		 * keyCodeTable.put("VK_MULTIPLY",0X6A);
		 * keyCodeTable.put("VK_ADD",0X6B);
		 * keyCodeTable.put("VK_SEPARATOR",0X6C);
		 * keyCodeTable.put("VK_SUBTRACT",0X6D);
		 * keyCodeTable.put("VK_DECIMAL",0X6E);
		 * keyCodeTable.put("VK_DIVIDE",0X6F); keyCodeTable.put("VK_F1",0X70);
		 * keyCodeTable.put("VK_F2",0X71); keyCodeTable.put("VK_F3",0X72);
		 * keyCodeTable.put("VK_F4",0X73); keyCodeTable.put("VK_F5",0X74);
		 * keyCodeTable.put("VK_F6",0X75); keyCodeTable.put("VK_F7",0X76);
		 * keyCodeTable.put("VK_F8",0X77); keyCodeTable.put("VK_F9",0X78);
		 * keyCodeTable.put("VK_F10",0X79); keyCodeTable.put("VK_F11",0X7A);
		 * keyCodeTable.put("VK_F12",0X7B); keyCodeTable.put("VK_NUMLOCK",0X90);
		 * keyCodeTable.put("VK_SCROLL",0X91);
		 * keyCodeTable.put("VK_LSHIFT",0XA0);
		 * keyCodeTable.put("VK_RSHIFT",0XA1);
		 * keyCodeTable.put("VK_LCONTROL",0XA2);
		 * keyCodeTable.put("VK_RCONTROL",0XA3);
		 * keyCodeTable.put("VK_LALT",0XA4); keyCodeTable.put("VK_RALT",0XA5);
		 */
		keyCodeTable.put("{TAB}", "VK_TAB");
		keyCodeTable.put("{ENTER}", "VK_RETURN");

		String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		try {
			byte[] b = a.getBytes("ASCII");
			for (byte c : b) {
				charkeyCodeTable.put(String.valueOf(c), (int) c);
			}
		} catch (Exception e) {

		}
	}

	/*
	 * ---- Here are the JNI function declarations ----
	 */
	private static native int[] _getDialogs(int hIEwnd);

	private static native int _getHScroll(int hwnd);

	private static native int _getIEClientHeight(int hwnd);

	private static native int _getIEClientWidth(int hwnd);

	private static native int _getIEDocumentHeight(int hwnd);

	private static native int _getIEDocumentWidth(int hwnd);

	private static native int[] _getIEWnds();

	private static native int _getVScroll(int hwnd);

	private static native int _bringForeground(int hwnd);

	private static native String _getWndTitle(int hwnd);

	private static native void _killIE();

	private static native int _sendKeys(int hwnd, long keyCode, long ext);

	private static native int _setHScroll(int hwnd, int pos);

	private static native int _setVScroll(int hwnd, int pos);

	/**
	 * Added April 2008, getting IE wnd's creation time
	 * 
	 * @return An int array. From lowest to highest are MILLISEC, SEC, MIN,
	 *         HOUR, DATE, MONTH and YEAR, in decimal format. The length of the
	 *         array is fixed to 7.
	 */
	private static native int[] _getIEWndCreationTime(int hwnd);

	/**
	 * Added April 2008, getting IE wnd's creation time String _getDocumentURL
	 * (int hwnd), returns the URL string with in the document of a certain
	 * browser.
	 */
	private static native java.lang.String _getDocumentURL(int hwnd);

	private static native int _faint ();
	
	public static int faint () {
		return _faint();
	}
	
	public static java.lang.String getDocumentURL(int hwnd) {
		return _getDocumentURL(hwnd);
	}

	public static TestObject getDocumentInBrowser(BrowserTestObject bto) {
		// Get the URL of the document it loads
		Property[] props = { new Property(".class", "Html.HtmlDocument") };
		Property[] securityProps = { new Property(".class", "Html.Dialog") };
		Anchor anchor = new Anchor(true, props);
		Anchor securityAnchor = new Anchor(false, securityProps);
		// Find the document object
		if (bto != null) {
			TestObject obj = null;
			for (int i = 0; i < 4; ++i) {
				try {
					Thread.sleep(1000);
					obj = bto.find(anchor)[0];
					if (obj != null && obj.exists()) {
						// Wait another 1 sec
						Thread.sleep(3000);
						break;
					}
				} catch (Exception e) {
					System.err.println("No document object found.");
				}
			}
			// Found the document
			return obj;
		} else {
			System.err.println("Browser object is null.");
		}
		return null;
	}

	public static int hash (String str) {
		     int hash = 5381;
		     byte[] array = str.getBytes();
		     int i = 0, c = (int)array[i];
		     while ( c!=0 ) {
		    	 c = (int)array[i];
		    	 hash = ((hash << 5) + hash) + c; /* hash * 33 + c */
		    	 i ++;
		     }   
		     return hash;
	}

	public static TopLevelTestObject[] getTopLevelTestObjects(String domain) {
		TopLevelTestObject[] tos = null;
		BrowserHelperMethods helper = new BrowserHelperMethods();
		DomainTestObject[] domains = helper.rtsGetDomains();
		// Enumber TopLevelTestObject within the specific domain
		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals(domain)) {
				// We found an Html domain, now for the BrowserTestObject
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					tos = new TopLevelTestObject[topObjects.length];
					try {
						for (int j = 0; j < topObjects.length; ++j) {
							tos[j] = new TopLevelTestObject(topObjects[j]);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		return tos;
	}
	
	public static BrowserTestObject getBrowserTestObjectFromHWND(int hwnd) {
		Long lhwnd = new Long(hwnd);
		TestObject[] foundTOs = RationalTestScript.find(RationalTestScript.atChild(".hwnd", lhwnd, ".domain", "Win"));
		BrowserTestObject bto2 = null;
		if ( foundTOs!=null ) {
			// Maximize it
			if ( foundTOs[0]!=null) {
				bto2 = new BrowserTestObject(foundTOs[0]);
			}
		}
		return bto2;
	}
	
	public static BrowserTestObject _getBrowserTestObjectFromHWND(int hwnd) {
		BrowserHelperMethods helper = new BrowserHelperMethods();
		DomainTestObject[] domains = helper.rtsGetDomains();
		// Enumber BrowserTestObject
		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals("Html")) {
				// We found an Html domain, now for the BrowserTestObject
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					try {
						for (int j = 0; j < topObjects.length; ++j) {
							// Found a browser window
							if (topObjects[j].getProperty(".class").equals(
									"Html.HtmlBrowser")) {
								// Get the document
								TestObject dto = Win32IEHelper.getDocumentInBrowser(new BrowserTestObject(
										topObjects[j]));
								if (dto != null) {
									String url = dto.getProperty(".url")
											.toString();
									String urlFromDll = Win32IEHelper.getDocumentURL(hwnd);
									// String aaa = getWndTitle(hwnd);
									// Get the URL of the document and hash it!
									if (url.lastIndexOf('/')==url.length()-1) {
										url = url.substring(0,url.length()-1);
									}
									if (urlFromDll.equals(url)) {
										// Return the browser test object
										return new BrowserTestObject(topObjects[j]);
									}
									
								}

							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		return null;
	}

	public static int[] getIEWndCreationTime(int hwnd) {
		return _getIEWndCreationTime(hwnd);
	}

	public static int getDialogByTitle(String regex) {
		return matchHwndFromTitle(regex, getDialogs(0));
	}

	public static int[] getDialogs(int hIEWnd) {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		int[] hDialogs = _getDialogs(hIEWnd);
		return hDialogs;
	}

	/**
	 * Note: This method returns the first HTML dialog found.
	 * 
	 * @return
	 */
	public static int getDialogTop() {
		int hDialog = 0;
		int hDlgs[] = getDialogs(0);
		if (hDlgs.length > 0) {
			hDialog = hDlgs[0];
		}
		return hDialog;
	}

	public static int getHScroll(int hIEWnd) {
		int hScrollPos = -1;
		hScrollPos = _getHScroll(hIEWnd);
		return hScrollPos;
	}

	public static Point getIEClientSize(int hIEWnd) {
		Point size = new Point(0, 0);
		size.x = _getIEClientWidth(hIEWnd);
		size.y = _getIEClientHeight(hIEWnd);
		return size;
	}

	public static Point getIEDocumentSize(int hIEWnd) {
		Point size = new Point(0, 0);
		size.x = _getIEDocumentWidth(hIEWnd);
		size.y = _getIEDocumentHeight(hIEWnd);
		return size;
	}

	public static int getIEWndByTitle(String regex) {
		return matchHwndFromTitle(regex, getIEWnds());
	}

	public static int[] getIEWnds() {
		int hwnd[] = _getIEWnds();
		return hwnd;
	}

	public static int getVScroll(int hIEWnd) {
		int vScrollPos = _getVScroll(hIEWnd);
		return vScrollPos;
	}

	public static String getWndTitle(int hwnd) {
		String title = _getWndTitle(hwnd);
		return title;
	}

	public static void killIE() {
		_killIE();
	}

	private static int matchHwndFromTitle(String regex, int[] arr) {
		int hwnd = 0;
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		for (int h : arr) {
			String title = getWndTitle(h);
			if (p.matcher(title).matches()) {
				hwnd = h;
				break;
			}

		}
		return hwnd;
	}

	public static int setHScroll(int hIEWnd, int pos) {
		int hScrollPos = _setHScroll(hIEWnd, pos);
		return hScrollPos;
	}

	public static int setVScroll(int hIEWnd, int pos) {
		int vScrollPos = _setVScroll(hIEWnd, pos);
		return vScrollPos;
	}

	public static int bringForeground(int hwnd) {
		return _bringForeground(hwnd);
	}

	/**
	 * Convert the key string of format "{KEYA}{KEYB}{KEYC}" to array of string
	 * "KEYA", "KEYB", "KEYC".
	 * 
	 * @param keystr
	 * @return
	 */
	public static String[] keyStringToArr(String keystr) {
		ArrayList<String> stral = new ArrayList<String>();
		try {
			for (int index = -1; index < keystr.length(); index++) {
				int start = keystr.indexOf('{', index);
				int end = keystr.indexOf('}', start);
				String key = keystr.substring(start + 1, end).trim();
				stral.add(key);
				index = end;
			}
		} catch (Exception e) {
			System.err
					.println("Wrong key sequence format. Use {ENTER} for default");
			stral.clear();
			stral.add("ENTER");
		}
		return stral.toArray(new String[] {});

	}

	public static int sendInputKeysToWnd(int hwnd, String keys) {
		return sendInputKeysToWnd(hwnd, keys, 250);
	}

	public static int sendInputKeysToWnd(int hwnd, String keys, int millisecs) {
		if (hwnd == 0)
			return 0;
		sendKeysToWnd(hwnd, keyStringToArr(keys), millisecs);
		return 1;
	}

	private static int sendKeysToWnd(int hwnd, String[] keys) {
		return sendKeysToWnd(hwnd, keys, 250);
	}

	private static int sendKeysToWnd(int hwnd, String[] keys, int millisecs) {
		long keycode = 0;
		long ext = 0;
		if (hwnd == 0)
			return 0;
		try {
			for (String c : keys) {
				_sendKeys(hwnd, (long) charkeyCodeTable.get(c), ext);
				Thread.sleep(millisecs);
			}
		} catch (Exception e) {
			return -1;
		}
		return 1;
	}

	/**
	 * ************************Inner Class
	 * Start***************************************************************************
	 */
	/**
	 * Description: this class is for separating out RationalTestScript methods
	 * and putting them in a format so that the Browser Ops can be static
	 * 
	 * @author TSnow
	 */
	protected static class BrowserHelperMethods extends RationalTestScript {
		public boolean DEBUG = false;

		public BrowserHelperMethods() {
			// must set the script name in order for startBrowser to work
			String caller = getTopScriptName();
			if (DEBUG)
				System.out.println(caller);
			try {
				setScriptName(caller);
			} catch (Exception e) // in case the calling script doesn't have a
									// matching script definition in the
									// resources directory for some reason, just
									// bail
			{
				// noop
			}
		}

		/**
		 * Waits the specified amount of time. (just a wrapper for the sleep
		 * method from RationalTestScript)
		 * <p>
		 */
		public void wait(int time) {
			super.sleep(time);
		}

		/**
		 * Gets all the domains and returns them in an array
		 * <p>
		 * Wraps the getDomains() method of RationalTestScript
		 * 
		 * @return array of DomainTestObjects including all the domains
		 */
		public DomainTestObject[] rtsGetDomains() {
			return super.getDomains();
		}

		/**
		 * Gets active window caption
		 * <p>
		 * 
		 * @return active window caption.
		 */
		public String getActiveWindowCaption() {
			// Get Active Window Caption
			IScreen wActiveWin = super.getScreen();
			IWindow win = wActiveWin.getActiveWindow();

			return win.getText();
		}

		/**
		 * starts Browser and sets to particular page
		 * <p>
		 * 
		 * @param sBrowserPage
		 *            the page to set the new instance of the browser to
		 */
		public void rtsStartBrowser(String sBrowserPage) {
			super.startBrowser(sBrowserPage);
		}

		/**
		 * returns the Maximimum Wait For Existence time stored in the
		 * preferences of XDE
		 * <p>
		 * 
		 * @return maximum time that this version of XDE is set to wait for the
		 *         existence of an object
		 */
		public double getDefaultMaxWaitTime() {
			return Double.parseDouble(super.getOption(
					IOptionName.MAXIMUM_WAIT_FOR_EXISTENCE).toString());
		}
	}

	// **************************Inner Class
	// End****************************************************************************

	public static void main(String[] args) {
		int[] ies = getIEWnds();
		// System.out.println("---- IEs ----");
		for (int a : ies) {
			// getDocumentURL(a);
			// System.out.println(getWndTitle(a));
			String s = getDocumentURL(a);
			System.out.println(s);
		}
		// getBrowserTestObjectFromHWND(1);
		// int myIE = getIEWndByTitle(".*");
		//
		// System.out.println("---- S IE ----");
		// System.out.printf("0x%x\n",myIE);
		// System.out.println(" H: " + getHScroll(myIE));
		// System.out.println(" V: " + getVScroll(myIE));
		// System.out.println(" HS: " + setHScroll(myIE,500));
		// System.out.println(" VS: " + setVScroll(myIE,900));
		// Point size1 = getIEDocumentSize(myIE);
		// Point size2 = getIEClientSize(myIE);
		// System.out.println("Document :" + size1.x + ", " + size1.y);
		// System.out.println("Client :" + size2.x + ", " + size2.y);
		//		
		//		int hDlg = getDialogTop();
		//		sendInputKeysToWnd(hDlg,"{TAB}  {TAB}  {ENTER}",250);
		//killIE();

	}

}
