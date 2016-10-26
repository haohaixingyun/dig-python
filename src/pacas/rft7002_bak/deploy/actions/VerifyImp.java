package actions;
import hlta.testexec.platform.rft7.web.common.AutoObjectFactory;
import hlta.testexec.testcontrol.java.TestCase;
import ibm.widgets.WCheckBox;
import ibm.widgets.WComboBox;
import ibm.widgets.WRadioButton;
import ibm.widgets.WTable;
import ibm.widgets.WTextField;

import java.util.regex.PatternSyntaxException;

import com.rational.test.ft.InvalidTestDataTypeException;
import com.rational.test.ft.ObjectIsDisposedException;
import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.DocumentTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.SubitemFactory;
import com.rational.test.ft.vp.ITestDataText;
import com.rational.test.util.regex.Regex;

import db.AutoQuery;
public class VerifyImp {
	private static boolean debug = false;
	public static String odsResult = "";
	/**
	 * 
	 * @param object string to match
	 * @param value
	 *            The option. "i" for ignore case; "r" for regular expression
	 *            match; "+n" for number of match times.
	 * @param browser
	 * @return
	 */
	public static int verifyStringInPage(String object, String value,Object browser) {
		if (debug) {
			System.out.println(object.getClass());
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
		}
		int result = TestCase.IResult.NOT_RUN;
		String val = "";
		if(object.indexOf(".")==0){
			int pos = object.indexOf(".");
			String varName = object.substring(pos+1);
			val = DataHelper.getAppData(varName, 0);
			
		} else
			val = object;
		String opt = value;
		BrowserTestObject bto = (BrowserTestObject)browser;
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
					} else {
						result = TestCase.IResult.FAILED;
					}
				} catch (PatternSyntaxException e) {
					System.err.println(e.getLocalizedMessage());
					result = TestCase.IResult.NOT_RUN;
				}
			} else {
				if (text.indexOf(val) != -1) {
					result = TestCase.IResult.PASSED;
				} else {
					result = TestCase.IResult.FAILED;
				}
			}
			try {Thread.sleep(1000);} catch (Exception e){};
		}
		return result;
	}
	public static int verifyStringOnControl(Object control, String val,String param,Object browser) {
		if (debug) {
			System.out.println(val.getClass());
			System.out.println(param.getClass());
			System.out.println(browser.getClass());
		}
		int result = TestCase.IResult.NOT_RUN;
		// Assert the property to get
		//	.text
		//  .value
		
		return result;
	}
	/**
	 * 
	 * @param object
	 * @param value The waiting timeout. Default to 30 sec.
	 * @param param number of such objects (could be zero-lengthed string)
	 * @param browser
	 * @return
	 */
	public static int verifyObjectExistence(String object, String value, String param,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(param.getClass());
			System.out.println(browser.getClass());
		}
		int result = TestCase.IResult.NOT_RUN;
		String name = object;
		// TODO: Currently the number of objects is not available.
		//	May add support.
		int num = 1;
		if (param!="") {
			num = Integer.parseInt(param);
		}
		int retries = 150;
        if (value.length()>0) {
            retries = Integer.parseInt(value) / 2;
        }
        System.out.println("Verifying object existence for " + object.toString() +" for " + retries * 2 + " secs.");
        
			
		// 
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		if (factory!=null) {
			TestObject to = null;
			// Wait for the object's existence
			if (to!=null && to.exists()) {
				return TestCase.IResult.PASSED;
			} else {
				// Loop look for the object, every 1 secs
				for (int i = 0; i<retries ; ++i) {
					try {
						Thread.sleep(2000);
						// System.out.println(""+i * 2 +" secs.");
						to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
						if(to==null) {
							result = TestCase.IResult.FAILED;
						}
					} catch (InterruptedException e) {
						result = TestCase.IResult.FAILED;
					}
					if (to==null || !to.exists()) {
						  continue;
					} else {
						result = TestCase.IResult.PASSED;
						break;
					}
				}		
			}
	
		} else {
			// AutoObjectFactory not available
			result = TestCase.IResult.UNKNOWN_STATE;
		}
	
		return result;
	}

	/**
	 * 
	 * @param object the name of the object of select type(defined by user in xml's)
	 * @param value item value(String)
	 * @param param item index, can be empty
	 * @param browser
	 * @return
	 */
	public static int verifyItemInSelect(String object, String value, String param,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(param.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
			System.out.println("param"+param);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		String item = value;
		int index=-1;
		try
		{
			if (param!=null && !"".equalsIgnoreCase(param))
			index=Integer.parseInt(param);
		}
		catch(NumberFormatException ne)
		{
			result = TestCase.IResult.FAILED;
			System.out.println("param:"+param);
			System.out.println("can't parse String to int for item index!");
			return result;
		}
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WComboBox select = new WComboBox(to);
		if(select==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}

		String[] itemList=select.getContents();
		
		if (index==-1)
		{
			for (int i = 0; i < itemList.length; i++) {
				if (itemList[i].equals(item))
					result = TestCase.IResult.PASSED;
			}
		}
		else if (index>itemList.length-1)
			result = TestCase.IResult.FAILED;
		else if (itemList[index].equals(item))
			result = TestCase.IResult.PASSED;
		
		return result;

	}
	
	/**
	 * 
	 * @param object the name of the object of checkbox type(defined by user in xml's)
	 * @param value item value(String)
	 * @param browser
	 * @return
	 */
	public static int verifyCheckboxStatus(String object, String value,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		String status = value;
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WCheckBox checkbox = new WCheckBox(to);
		if(checkbox==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		if ("checked".equalsIgnoreCase(status))
		{
			if (checkbox.isChecked())
					result = TestCase.IResult.PASSED;
		}
		else if ("unchecked".equalsIgnoreCase(status))
		{
			if (!checkbox.isChecked())
					result = TestCase.IResult.PASSED;
		}
		else 
			result = TestCase.IResult.FAILED;
		
		return result;

	}
	
	/**
	 * 
	 * @param object the name of the object of radio type(defined by user in xml's)
	 * @param value item value(String)
	 * @param browser
	 * @return
	 */
	public static int verifyRadioStatus(String object, String value,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		String status = value;
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WRadioButton radio = new WRadioButton(to);
		if(radio==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		if ("selected".equalsIgnoreCase(status))
		{
			if (radio.isSelected())
					result = TestCase.IResult.PASSED;
		}
		else if ("notselected".equalsIgnoreCase(status))
		{
			if (radio.isNotSelected())
					result = TestCase.IResult.PASSED;
		}
		else 
			result = TestCase.IResult.FAILED;
		
		return result;

	}
	
	/**
	 * 
	 * @param browser
	 * @return
	 */
	public static int verifyManual(Object browser) {
		if (debug) {
			System.out.println(browser.getClass());
		}
		
		int result = TestCase.IResult.NOT_RUN;
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		
		result = TestCase.IResult.MANUAL;
		
		return result;

	}

	/**
	 * 
	 * @param object the name of the object of table type(defined by user in xml's)
	 * @param value string value(String)
	 * @param browser
	 * @return
	 */
	public static int verifyStringInTable(String object, String value,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		String item = value;
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WTable table = new WTable(to);
		if(table==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}

		String sTableResult;
		try {
			sTableResult = (String)table.getProperty(".text");
			Regex r = new Regex(item);
			if(r.matches(sTableResult))
				result = TestCase.IResult.PASSED;
			else 
				result = TestCase.IResult.FAILED;
				
		} catch (ObjectNotFoundException e) {
			result = TestCase.IResult.FAILED;
		}
		
		return result;

	}

	/**
	 * 
	 * @param object the name of the object of select type(defined by user in xml's)
	 * @param value item count
	 * @param browser
	 * @return
	 */
	public static int verifyItemCountInSelect(String object, String value, Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		int itemCount = 0;
		if (value!="") {
			itemCount = Integer.parseInt(value);
		}

		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WComboBox select = new WComboBox(to);
		if(select==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}

		if (select.getItemCount()==itemCount)
			result = TestCase.IResult.PASSED;
		else 
			result = TestCase.IResult.FAILED;
		
		return result;

	}

	/**
	 * 
	 * @param object the name of the object of textfield type(defined by user in xml's)
	 * @param value string value(String)
	 * @param browser
	 * @return
	 */
	public static int verifyStringInTextfield(String object, String value,Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;
		String item = value;
		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WTextField textfield = new WTextField(to);
		if(textfield==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}

		System.out.println("item:"+item);
		System.out.println("textfield:"+textfield.getText());
		try {
			if((textfield.getText()).matches(item))
				result = TestCase.IResult.PASSED;
			else 
				result = TestCase.IResult.FAILED;
				
		} catch (ObjectNotFoundException e) {
			result = TestCase.IResult.FAILED;
		}

		return result;

	}

	/**
	 * Modified by Jeffrey
	 * Added the var substitution part
	 */
	public static int verifyExecuteQuery(String object,String value,String index){
		if (debug) {
			System.out.println("object:"+object);
			System.out.println("value:"+value);
			System.out.println("index:"+index);
		}
		odsResult = "";
		int iIndex = 0;
		if(index!=null&&index.equalsIgnoreCase(""))
			iIndex = Integer.valueOf(index).intValue();
		int result = TestCase.IResult.NOT_RUN;
		AutoQuery query = new AutoQuery();
		// Get to analyze real data, assemble the sql statement
		String data = DataHelper.getAppData(object, iIndex);
		// Replace all the variables!
		StringBuffer buf = new StringBuffer(data);
		int i = 0;
		final int  MAXVARS = 16;
		while(replaceAVar(buf)==1 && (i++)<MAXVARS);
		boolean flag = query.executeQuery(new String(buf), value);
		// System.out.println("Real SQL: " + buf.toString());
		if(flag){
			result = TestCase.IResult.PASSED;
			odsResult = query.getResult(value);
		}
		else { 
			result = TestCase.IResult.PASSED;
			odsResult = query.getResult(value);
		}
		if (odsResult==null) {
			odsResult = "[no result]" + System.getProperty("line.separator");
		}
		// System.out.println("odsResult: " + odsResult);
		return result;
	}
	/**
	 * 
	 * @param object the name of the object of select type(defined by user in xml's)
	 * @param value selected item
	 * @param browser
	 * @return
	 */
	public static int verifySelectedItem(String object, String value, Object browser) {
		if (debug) {
			System.out.println(value.getClass());
			System.out.println(browser.getClass());
			System.out.println("value"+value);
		}
		
		int result = TestCase.IResult.NOT_RUN;

		String name = object;

		
		BrowserTestObject bto = (BrowserTestObject)browser;
		AutoObjectFactory.browserReady(bto, 60, 3);
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		TestObject to = (TestObject)factory.createGuiObject(name, (BrowserTestObject)browser);
		if(to==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}
		
		WComboBox select = new WComboBox(to);
		if(select==null) {
			result = TestCase.IResult.FAILED;
			return result;
		}

		String[] selectedList=select.getMultiSelText();
		result = TestCase.IResult.FAILED;
		if (selectedList!=null)
		{
			for (int i=0;i<selectedList.length;i++)
				if (selectedList[i].equals(value))
				{
					result = TestCase.IResult.PASSED;
					break;
				}
		}
	
		
		return result;

	}
	
	
	/**
	 * Added by jeff, replace the $var with DataHelper.getAppData,
	 * for ODS testing
	 * @param data
	 * @return 1 if found else 0
	 */
	private static int replaceAVar(StringBuffer data) {
		char[] buf = new String(data).toCharArray();
		int idx = 0;	
		int flag = 0, flag2 = 0;	// Flag2==1 means a variable found!!
		int start = -1, end = -1;
		int namestart = -1, nameend = -1;
		while (idx<buf.length) {
			if( buf[idx]=='$' ) {
				flag = 1;
				start = idx;
				namestart = idx + 1;
			}
			if ( flag==1 && flag2==0 ) {
				if ( buf[idx]=='{' ) {
					namestart = idx + 1;
				} 
				if ( buf[idx]=='}' ) {
					nameend = idx - 1;
					end = idx;
					flag2 = 1;
				} 
				if ( buf[idx]==' ' || 
					buf[idx]=='\t' ||
					buf[idx]=='\b' || 
					buf[idx]=='\r' || 
					buf[idx]=='\n' || 
					buf[idx]=='\f' ||
					buf[idx]=='\'' ||
					buf[idx]=='\"' 
				) {
					end = idx - 1;
					nameend = idx - 1;
					flag2 = 1;
				} else if (idx==buf.length-1) {
					end = idx;
					nameend = idx;
					flag2 = 1;
				}
				
			}
			
			// 
			if (flag2==1) {
				// Begin replace
				// getName
				String name = new String(buf,namestart,nameend-namestart+1);
				String rep = DataHelper.getAppData(name,0);
				if (rep==null) {
					System.err.println("Var " + name + " not exits!");
					rep = "";
				}
				data.replace(start, end + 1, rep);
				// 
				// System.out.println(name + " R " + start+", "+ end );
				return 1;
			}
			idx++;
		}

		return 0;
	}
	
	/**
	 * Test method for replaceAVar
	 * @param name
	 * @param i0
	 * @return
	 */
	private static String getAppData(String name, int i0) {
		return "TEST"+ name+"TEST";
	}

}
