/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;

import java.util.Hashtable;
import hlta.testexec.platform.rft7.web.common.AutoObjectFactory;
import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import ibm.util.*;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.platform.rft7.web.common.*;
/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Browse extends ActionBase {
	/**
	 * Begin: Added by Jeffrey bian
	 * 	Put the hashtable storing different browser instances into current
	 * class.
	 */
	private static Hashtable<String, BrowserTestObject> bto_table = 
				new Hashtable<String, BrowserTestObject>();
	private static boolean everStarted = false;
	
	public static void setBrowserStart (boolean started) {
		everStarted = started;
	}
	
	public static boolean browserStarted () {
		return everStarted;
	}
	
	public static BrowserTestObject get(String browserID) {
		BrowserTestObject t = null;
		if (browserID!=null) {
			t = bto_table.get(browserID);
		} else {
			System.err.println("Null browser ID.");
		}
		if (t==null) {
			System.err.println("No browser by id : " + browserID);
		}
		return t;
	}
	public static BrowserTestObject put(String browserID, BrowserTestObject bto) {
		bto_table.put(browserID,bto);
		return bto;
	}
	
	/**
	 * @author Jeffrey
	 * @param object	String. The URL. 
	 * @param param		String. Specifies actions of browsers. To be implemented
	 * 					Open - 
	 * 					Close - Close all the browsers
	 * 					Exist -  
	 * 					Wait - 
	 *                  
	 * @param extParam  String. This parameter has different meanings according to the param.
     *                  
	 */
	public Browse(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {

		// TODO Auto-generated method stub
		int j = 1, i = 1;
		if (((String)param).equalsIgnoreCase("Open") || ((String)param).length()==0) {
			while (i<8 || BrowserOps.findBrowser()!=null) {
				try {
					++i;
					BrowserOps.closeAllBrowsers();
					Thread.sleep(500);
					if (BrowserOps.findBrowser()!=null) {
						BrowserOps.findBrowser().close();
					}
				} catch (Exception e) {
					continue;
				}
				break;
			}
			try {
				BrowserTestObject bto = null;
				BrowserOps.startBrowser((String)object);
				/*------------- Begin: Added by Jeffrey again -------------*/
				// Wait for 5 secs
				Thread.sleep(5000);
				// Look for Security alert if any
	            try {
	                 ITopWindow g = (ITopWindow)AutoObjectFactory.getHtmlDialog("ITopWindow");
	                 if (g != null) {
	                     g.inputKeys("Y");
	                     Thread.sleep(100);
	                     g.inputKeys("~");
	                     Thread.sleep(100);
	                 }
	            } catch (Exception e) {
	                //  Do nothing, just continue
	                //  System.out.println("No security alert found.");
	            }
	            /*------------- End: Added by Jeffrey again -------------*/
	            // Continue to find browser
				while (bto==null && i < 30 ) {
					Thread.sleep(2000);
					bto=BrowserOps.findBrowser();
					i++;
				}
				if(bto != null){
					Thread.sleep(1000);
					bto.maximize();
				}
				AutoObjectFactory.browserReady(bto,30,3);
				Browse.setBrowserStart(true);
			} catch (Exception e) {
				System.out.println("Error occured while opening browser.");
			}
			
		} else if (((String)param).equalsIgnoreCase("Exist")) {
			try {
				BrowserTestObject bto = null;
				/*------------- Begin: Added by Jeffrey again -------------*/
				// Wait for 2 secs
				Thread.sleep(2000);
//				// Look for Security alert if any
//	            try {
//	                 ITopWindow g = (ITopWindow)AutoObjectFactory.getHtmlDialog("ITopWindow");
//	                 if (g != null) {
//	                     g.inputKeys("Y");
//	                     Thread.sleep(100);
//	                     g.inputKeys("~");
//	                     Thread.sleep(100);
//	                 }
//	            } catch (Exception e) {
//	                //  Do nothing, just continue
//	                //  System.out.println("No security alert found.");
//	            }
	            /*------------- End: Added by Jeffrey again -------------*/
	            // Continue to find browser
				while ((bto=BrowserOps.findBrowser())==null && i < 30) {
					Thread.sleep(1000);
					i++;
				}
				if(bto != null){
					bto.maximize();
					bto.loadUrl((String)object);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Error occured while opening browser.");
			}
			
		}
	
	}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		int j = 1, i = 1;
		if (((String)param).equalsIgnoreCase("Open") || ((String)param).length()==0) {
			while (i<8 || BrowserOps.findBrowser()!=null) {
				try {
					++i;
					BrowserOps.closeAllBrowsers();
					Thread.sleep(500);
					if (BrowserOps.findBrowser()!=null) {
						BrowserOps.findBrowser().close();
					}
				} catch (Exception e) {
					continue;
				}
				break;
			}
			try {
				BrowserTestObject bto = null;
				BrowserOps.startBrowser((String)object);
				/*------------- Begin: Added by Jeffrey again -------------*/
				// Wait for 5 secs
				Thread.sleep(5000);
				// Look for Security alert if any
	            try {
	                 ITopWindow g = (ITopWindow)AutoObjectFactory.getHtmlDialog("ITopWindow");
	                 if (g != null) {
	                     g.inputKeys("Y");
	                     Thread.sleep(100);
	                     g.inputKeys("~");
	                     Thread.sleep(100);
	                 }
	            } catch (Exception e) {
	                //  Do nothing, just continue
	                //  System.out.println("No security alert found.");
	            }
	            /*------------- End: Added by Jeffrey again -------------*/
	            // Continue to find browser
				while (bto==null && i < 30 ) {
					Thread.sleep(2000);
					bto=BrowserOps.findBrowser();
					i++;
				}
				if(bto != null){
					Thread.sleep(1000);
					bto.maximize();
				}
				AutoObjectFactory.browserReady(bto,30,3);
				Browse.setBrowserStart(true);
			} catch (Exception e) {
				System.out.println("Error occured while opening browser.");
			}
			
		} else if (((String)param).equalsIgnoreCase("Exist")) {
			try {
				BrowserTestObject bto = null;
				/*------------- Begin: Added by Jeffrey again -------------*/
				// Wait for 2 secs
				Thread.sleep(2000);
//				// Look for Security alert if any
//	            try {
//	                 ITopWindow g = (ITopWindow)AutoObjectFactory.getHtmlDialog("ITopWindow");
//	                 if (g != null) {
//	                     g.inputKeys("Y");
//	                     Thread.sleep(100);
//	                     g.inputKeys("~");
//	                     Thread.sleep(100);
//	                 }
//	            } catch (Exception e) {
//	                //  Do nothing, just continue
//	                //  System.out.println("No security alert found.");
//	            }
	            /*------------- End: Added by Jeffrey again -------------*/
	            // Continue to find browser
				while ((bto=BrowserOps.findBrowser())==null && i < 30) {
					Thread.sleep(1000);
					i++;
				}
				if(bto != null){
					bto.maximize();
					bto.loadUrl((String)object);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Error occured while opening browser.");
			}
			
		}
	}
	/**
	 * End: Added by Jeffrey bian
	 */
	public static void main(String[] args) {
	}
}