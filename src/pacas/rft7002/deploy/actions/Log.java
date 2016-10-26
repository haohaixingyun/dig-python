package actions;
import hlta.testexec.testcontrol.java.TestCase;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import hlta.testexec.platform.rft7.web.common.CustomLogger;
import hlta.testexec.platform.rft7.web.common.ScreenImageOps;
import hlta.testexec.platform.win32.Win32IEHelper;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;

import com.rational.test.ft.script.RationalTestScript;
public class Log extends ActionBase {

	/**
	 * Begin: Added by Jeffrey, define screen capture type
	 * NONE: No Screen Capture at all
	 * MIN : Only Manual steps are captured
	 * BEST: Only non-Passed steps are captured
	 * MAX: Capture all steps 
	 */
	public interface CAPTURE_TYPE {
		int MIN = 0,
			MAX = 2,
			BEST = 4,
			NONE = 8;
	}
	// Default to MIN, that is only Manual steps are captured
	private static int captureType = CAPTURE_TYPE.MAX;
	/**
	 * End: Added by Jeffrey
	 */
	
	/**
	 * 
	 * @param object
	 *            The TestCase object.
	 * @param param
	 *            The step index number. If -1, then write final summary.
	 * @param extParam
	 *            CustomLogger.
	 */
	private static CustomLogger logger = null;
    public static ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
    
	public Log(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		
		// Retrieve step info and call CustomLogger
		TestCase t = (TestCase)object;
		int index = ((Integer)param).intValue();
		logger=(CustomLogger)extParam;

		String vpName = "";
		int ifDb2 = 0;
		if (index>0) {
			// Judge what type it is.
			String strFlag = "";
			if(args!=null) {
				strFlag = (String)args[0];
				if(strFlag.equalsIgnoreCase("DB2"))
					ifDb2 = 1;
			}
			vpName = t.getStep(index).getStepVpText();
			// ArrayList<BufferedImage> tempList = null;	
			ArrayList<BufferedImage> tempList = new ArrayList<BufferedImage>(); // Mod by Jeff
			/**
			 * Begin: Added by Jeffrey
			 */ 
			switch (captureType) {
			case Log.CAPTURE_TYPE.MIN:
				if (t.getStepResult(index) == TestCase.IResult.MANUAL) {
					tempList = capture(strFlag);
				}
				break;
			case Log.CAPTURE_TYPE.MAX:
				tempList = capture(strFlag);
				break;
			case Log.CAPTURE_TYPE.BEST:
				if (t.getStepResult(index) != TestCase.IResult.PASSED) {
					tempList = capture(strFlag);
				}
				break;
			case Log.CAPTURE_TYPE.NONE:
				// Do nothing
				break;				
			}
			
			/**
			 * End: Added by Jeffrey
			 */
//			if(strFlag.equalsIgnoreCase("SAP"))
//				tempList = captureSapDocument();
//			else if(strFlag.equalsIgnoreCase("WEB")){
//				tempList = captureDocument();
//			} else if(strFlag.equalsIgnoreCase("db2")) {
//				ifDb2 = 1;
//			}
			//if(ifDb2==0) {
			for (int i=0;i<tempList.size();i++)
			{
				imageList.add(tempList.get(i));
			}
			//}
		    
			if (t.getStepResult(index) == TestCase.IResult.PASSED) {
				if(ifDb2==0)
					logger.compareAndLogVP(vpName, true, true, imageList ,index);
				else
					logger.compareAndLogVP(vpName, true,true,index);
			} else if (t.getStepResult(index) == TestCase.IResult.FAILED) {
				if(ifDb2==0)
					logger.compareAndLogVP(vpName, true, false, imageList ,index);
				else
					logger.compareAndLogVP(vpName, true,false,index);
			} else if (t.getStepResult(index) == TestCase.IResult.MANUAL) {
				if(ifDb2==0)
					logger.logManualTest(imageList, index - 1);
			} else {
				if(ifDb2==0)
					logger.compareAndLogVP(vpName, true, false, imageList ,index);
				else
					logger.compareAndLogVP(vpName, true,false,index);
			}
			imageList.clear();
		}  else {
			RationalTestScript caller = RationalTestScript.getTopScript().getScriptCaller();
			String callerName = "";
			if (caller!=null) {
				callerName = caller.getScriptName();
			} 
			String scriptName = RationalTestScript.getTopScriptName();
			logger.logCurrTestcaseSummary(scriptName, callerName);
		}
	}
	
	/**
	 * Capture different apps by type.
	 * @author Jeffrey 
	 * @param type
	 * @return
	 */
	protected ArrayList<BufferedImage> capture (String type) {
		if (type.equalsIgnoreCase("SAP")) {
			return captureSapDocument();
		} else if (type.equalsIgnoreCase("WEB")) {
			return captureDocument();
		} else if (type.equalsIgnoreCase("DB2")) {
			return captureDB2();
		} else {
			// Fall thru to end of the method
		}
		return new ArrayList<BufferedImage>();
		 
	}
	/**
	 * Capture DB2 document 
	 * @author Jeffrey
	 * @return
	 */
	private ArrayList<BufferedImage> captureDB2() {
		return new ArrayList<BufferedImage>();
	}
	
	private ArrayList<BufferedImage> captureDocument()
	{
		int[] myIEs = Win32IEHelper.getIEWnds();

		ArrayList<BufferedImage> screenList =new ArrayList<BufferedImage>();
		
		for(int j=0;j<myIEs.length;j++)
		{
		
			double docHeight=(Win32IEHelper.getIEDocumentSize(myIEs[j])).getY();
			double clientHeight=(Win32IEHelper.getIEClientSize(myIEs[j])).getY();
			int numScr=(int)((docHeight+clientHeight-1)/clientHeight);
			for (int i=0;i<numScr;i++)
			{
				Win32IEHelper.bringForeground(myIEs[j]);
				Win32IEHelper.setVScroll(myIEs[j], i*(int)(clientHeight));
				screenList.add(ScreenImageOps.captureScreenImage());
			}
		}
//		System.out.println("doc:"+docHeight+",client:"+clientHeight+",Vscroll:"+Vscroll);
		return screenList;
	}
	
	private ArrayList<BufferedImage> captureSapDocument()
	{

		ArrayList<BufferedImage> screenList =new ArrayList<BufferedImage>();
		screenList.add(ScreenImageOps.captureScreenImage());
		return screenList;
	}
	/**
	 * @author Jeffrey
	 * @return
	 */
	public static int getCaptureType() {
		return captureType;
	}
	/**
	 * @author Jeffrey
	 * @param captureType
	 */
	public static void setCaptureType(int captureType) {
		Log.captureType = captureType;
	}
	
}
