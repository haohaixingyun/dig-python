package actions;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ITopWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.Anchor;
import com.rational.test.ft.script.Property;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.platform.rft7.web.common.*;



public class AcceptHtmlDialog extends ActionBase {

	/**
	 * 
	 * @param object The value to accept 
	 * @param param Key sequence to perform if not found.
	 * 				such as "{Enter}{tab}"
	 * @param extParam The max time to wait before the dialog shows up.
	 */
	public AcceptHtmlDialog(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);

	}
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {

		// TODO Auto-generated method stub
		String match = "";
		String ks = "";
		GuiTestObject g = null;
		ITopWindow t = null;
		int time = 5000;  // Default wait 3 secs.

		try {
			Thread.sleep(time);
			g = (GuiTestObject)AutoObjectFactory.getHtmlDialog("");
			t = (ITopWindow)g;
			match = (String)object;
			ks = ks + (String)param;
			Property[] props = { new Property(".class", "Html.DialogButton") };
			Anchor anchor = new Anchor(false, props);
			TestObject[] subs = g.find(anchor);
			boolean found = false;
			for (TestObject c : subs) {
				if (c.getProperty(".text").toString().matches(match)) {
					found = true;
					((GuiTestObject)c).click();
				}
			}
			if (!found) {
				// Perform the key sequence
				t.inputKeys(ks);
			}
			Thread.sleep(2000);
		} catch (NullPointerException e ) {
			
		} catch (ClassCastException e) {
			t.inputChars("~");
		} catch (Exception e) {
			
		}
	
		
	}

	@Override
	
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
		String match = "";
		String ks = "";
		GuiTestObject g = null;
		ITopWindow t = null;
		int time = 5000;  // Default wait 3 secs.

		try {
			Thread.sleep(time);
			g = (GuiTestObject)AutoObjectFactory.getHtmlDialog("");
			t = (ITopWindow)g;
			match = (String)object;
			ks = ks + (String)param;
			Property[] props = { new Property(".class", "Html.DialogButton") };
			Anchor anchor = new Anchor(false, props);
			TestObject[] subs = g.find(anchor);
			boolean found = false;
			for (TestObject c : subs) {
				if (c.getProperty(".text").toString().matches(match)) {
					found = true;
					((GuiTestObject)c).click();
				}
			}
			if (!found) {
				// Perform the key sequence
				t.inputKeys(ks);
			}
			Thread.sleep(2000);
		} catch (NullPointerException e ) {
			
		} catch (ClassCastException e) {
			t.inputChars("~");
		} catch (Exception e) {
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
