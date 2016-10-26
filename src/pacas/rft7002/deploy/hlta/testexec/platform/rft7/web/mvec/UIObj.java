/*
 * Created on Apr 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.mvec;

import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.GuiTestObject;

/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UIObj {
	private MVec vec = null;
	private GuiTestObject guiobj = null;
	
	public UIObj(GuiTestObject gto, int n1, int n2, int n3, int n4, int n5, int n6) {
		vec = new MVec(n1,n2,n3,n4,n5,n6);
		this.guiobj = gto;
	}
	public static UIObj newOriginObj(GuiTestObject gto) {
		Rectangle rect = null;
		Rectangle rect1 = null;
		int n1 = -1, n2 = -1, n3 = -1, n4 = -1, n5 = -1, n6 = -1;
		try {
			rect = gto.getScreenRectangle();
			rect1 = ((GuiTestObject)gto.getParent()).getScreenRectangle();
			n1 = rect1.x + rect1.width / 2;
			n2 = rect1.y + rect1.height / 2;
			n3 = rect.x;
			n4 = rect.y;
			n5 = rect.x + rect.width;
			n6 = rect.y + rect.height;
		} catch (Exception e) {
			// System.err.println("Error getting GUI test object information.\r" + e.getMessage());
			return null;
		}
		
		UIObj uiobj = new UIObj(gto, n1,n2,n3,n4,n5,n6);
		
		return uiobj;
	}
	public static UIObj newObj(GuiTestObject gto) {
		Rectangle rect = null;
		Rectangle rect1 = null;
		int n1 = -1, n2 = -1, n3 = -1, n4 = -1, n5 = -1, n6 = -1;
		try {
			rect = gto.getScreenRectangle();
			rect1 = ((GuiTestObject)gto.getParent()).getScreenRectangle();
			n1 = rect1.x + rect1.width / 2;
			n2 = rect1.y + rect1.height / 2;
			n3 = n5 = rect.x + rect.width / 2;
			n4 = n6 = rect.y + rect.height / 2;
		
		} catch (Exception e) {
			System.err.println("Error getting GUI test object information.\r" + e.getMessage());
			return null;
		}
		
		UIObj uiobj = new UIObj(gto, n1,n2,n3,n4,n5,n6);
		
		return uiobj;
	}
	public MVec metricVector() {
		return vec;
	}
	public GuiTestObject guiTestObject() {
		return guiobj;
	}
	public Object getProperty(String propertyName) {
		return guiobj.getProperty(propertyName);
	}
}