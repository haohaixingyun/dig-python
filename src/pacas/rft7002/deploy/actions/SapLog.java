/*
 * Created on Sep 28, 2007
 * @author kevin.huangfu
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.platform.rft7.web.common.CustomLogger;
import hlta.testexec.platform.rft7.web.common.ScreenImageOps;
import hlta.testexec.platform.win32.Win32IEHelper;
import hlta.testexec.testcontrol.java.TestCase;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.rational.test.ft.script.RationalTestScript;

public class SapLog extends Log {
	
	private static CustomLogger logger = null;
    public static ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();

	public SapLog(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		
		// Retrieve step info and call CustomLogger
		TestCase t = (TestCase)object;
		int index = ((Integer)param).intValue();
		logger=(CustomLogger)extParam;

		String vpName = "";

		if (index>0) {
			// Judge what type it is.
			vpName = t.getStep(index).getStepVpText();
			
		    ArrayList<BufferedImage> tempList=captureDocument();
		    for (int i=0;i<tempList.size();i++)
		    {
		    	imageList.add(tempList.get(i));
		    }
		    
		    
			if (t.getStepResult(index) == TestCase.IResult.PASSED) {
				logger.compareAndLogVP(vpName, true, true, imageList ,index);
			} else if (t.getStepResult(index) == TestCase.IResult.FAILED) {

				logger.compareAndLogVP(vpName, true, false, imageList ,index);
			} else if (t.getStepResult(index) == TestCase.IResult.MANUAL) {

				logger.logManualTest(imageList, index - 1);
			}
			imageList.clear();
		}  else {
			RationalTestScript caller = RationalTestScript.getTopScript().getScriptCaller();
			if (caller!=null) {
			} 
			String scriptName = RationalTestScript.getTopScriptName();
			logger.logCurrTestcaseSummary(scriptName, scriptName);
		}
	}
	
	private ArrayList<BufferedImage> captureDocument()
	{

		ArrayList<BufferedImage> screenList =new ArrayList<BufferedImage>();
		screenList.add(ScreenImageOps.captureScreenImage());
		return screenList;
	}
}
