/*
 * author: kevin.huangfu
 * date: 2008/03/11
 * objective: judge if string exists
 */
package actions;
import hlta.testexec.testcontrol.java.TestCase;
import java.util.regex.PatternSyntaxException;

import com.rational.test.ft.InvalidTestDataTypeException;
import com.rational.test.ft.ObjectIsDisposedException;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.DocumentTestObject;
import com.rational.test.ft.script.SubitemFactory;
import com.rational.test.ft.vp.ITestDataText;

import hlta.testexec.platform.rft7.web.common.AutoObjectFactory;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;

public class StringExist extends ActionBase {

	public StringExist(Object object, Object param, Object extParam, Object[] args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		int result = TestCase.IResult.NOT_RUN;
		String val = (String)object;
		String opt = (String)param;
		BrowserTestObject bto = (BrowserTestObject)args[0];
		AutoObjectFactory.browserReady(bto, 60, 3);
		for (int i = 0; i < 20 && result != TestCase.IResult.PASSED; ++i) {
			DocumentTestObject dto = ((DocumentTestObject) bto
					.find(SubitemFactory.atDescendant(".class",
							"Html.HtmlDocument"))[0]);
			String text = "";
			try{
				text = ((ITestDataText) dto.getTestData("text")).getText();
			}catch(InvalidTestDataTypeException e){
				continue;
			}catch(ObjectIsDisposedException e){
				continue;
			}
			// Find string apperance by given options
			if (opt.indexOf("i") != -1) {
				// Ignore case
				val = val.toLowerCase();
				text = text.toLowerCase();
			}
			if (opt.indexOf("r") != -1) {
				try {
					if (text.matches(val)) {
						result = TestCase.IResult.PASSED;
						DataHelper.setGlobalVar(extParam.toString(), "true");
					} else {
						result = TestCase.IResult.FAILED;
						DataHelper.setGlobalVar(extParam.toString(), "false");
					}
				} catch (PatternSyntaxException e) {
					System.err.println(e.getLocalizedMessage());
					result = TestCase.IResult.NOT_RUN;
				}
			} else {
				if (text.indexOf(val) != -1) {
					result = TestCase.IResult.PASSED;
					DataHelper.setGlobalVar(extParam.toString(), "true");
				} else {
					result = TestCase.IResult.FAILED;
					DataHelper.setGlobalVar(extParam.toString(), "false");
				}
			}
			try {Thread.sleep(1000);} catch (Exception e){};
		}

	}

}
