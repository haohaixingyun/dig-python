package actions;

import java.util.regex.PatternSyntaxException;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.ITestDataText;
import com.rational.test.ft.InvalidTestDataTypeException;
import com.rational.test.ft.ObjectIsDisposedException;

import hlta.testexec.testcontrol.java.TestCase;
public class GetNumber extends ActionBase {

	/**
	 * @param object Number's object.
	 * @param param	 Text before Number.
	 * @param extParam The file path.
	 */
	public GetNumber(Object object, Object param, Object extParam) {
		super(object, param, extParam);
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		// Ignore the extParam
		if (object == null)
			return;
		
		String text = "";
		int result = TestCase.IResult.NOT_RUN;
		boolean flag = false;
		for (int i = 0; i < 20 && result != TestCase.IResult.PASSED; ++i) {
			DocumentTestObject dto = ((DocumentTestObject)((BrowserTestObject)object)
					.find(SubitemFactory.atDescendant(".class",
							"Html.HtmlDocument"))[0]);
			text = "";
			try{
				text = ((ITestDataText) dto.getTestData("text")).getText();
				result = TestCase.IResult.PASSED;
			}catch(InvalidTestDataTypeException e){
				continue;
			}catch(ObjectIsDisposedException e){
				continue;
			}
			try {Thread.sleep(1000);} catch (Exception e){};
		}
		for (int i = 0; i < 1 && !flag; i++){
//			String text = "";
			try{
//				TestObject obj = (TestObject) object;
//				text = ((ITestDataText) obj.getTestData("text")).getText();
//				flag = true;				
//				
				int index = 0;
				if(text.contains(param.toString())){
					index = text.indexOf(param.toString());
				}else{
					System.err.println(param.toString() + " is not in the text of the object");
					return;
				}
				
				String number = "";
				//scan the text after param(from index to end)
				for(int m = index + param.toString().length(); m < text.length(); m++){
					if(text.charAt(m) == '0' ||
					   text.charAt(m) == '1' ||
					   text.charAt(m) == '2' ||
					   text.charAt(m) == '3' ||
					   text.charAt(m) == '4' ||
					   text.charAt(m) == '5' ||
					   text.charAt(m) == '6' ||
					   text.charAt(m) == '7' ||
					   text.charAt(m) == '8' ||
					   text.charAt(m) == '9'){
						number += text.charAt(m);
					}else{
						if(!number.equals("")){
							break;
						}
					}
				}
				DataHelper.setGlobalVar(extParam.toString(), number);
			}catch(InvalidTestDataTypeException e){
				continue;
			}catch(ObjectIsDisposedException e){
				continue;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
	}
}

