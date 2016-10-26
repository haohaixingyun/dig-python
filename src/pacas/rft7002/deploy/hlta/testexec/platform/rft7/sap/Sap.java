/*
 * Created on Sep 27, 2007
 * @author kevin.huangfu
 */

package hlta.testexec.platform.rft7.sap;





import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.rational.test.ft.script.RationalTestScript;

import hlta.testexec.platform.rft7.sap.Sap.IConstant;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Sap{
	private Sap(){
		
	}
	private static Sap me = new Sap();
	private static SAPGuiApplicationTestObject theSapApp;
	private static SAPGuiConnectionTestObject theSapConnection;
	private static SAPGuiSessionTestObject theSapSession;
	private static SAPGuiToggleTestObject theSapExeButton;
	private static SAPGuiTextTestObject theSapTansactionText;
	private static SAPGuiMenubarTestObject theSapMenubar;
	private long tiemDelay = 10000;
	private static HashMap<String,String> variables = new HashMap<String,String>();
	
	public static Sap getInstance(){
		return me;
	}
	public interface IConstant{
		/*property name begin*/
		String propProcessName = ".processName";
		String propDotName = ".name";
		String propName = "Name";
		String propDotText = ".text";
		String propText = "Text";
		String propClassGuiButton = "GuiButton";
		String propClassDotPushbutton = ".Pushbutton";
		String propClassGuiOkCodeField = "GuiOkCodeField";
		String propClassSAPGuiToolbar = "SAPGuiToolbar";
		String propClassGuiTextField = "GuiTextField";
		String propClassGuiPasswordField = "GuiPasswordField";
		String propClassButton = "Button";
		String propDotClass = ".class";
		String propClassTypeMenuItem = ".Menuitem";
		String propClassGuiMenubar = "GuiMenubar";
		String propActiveWindow = "ActiveWindow";
		String propDotDomain = ".domain";
		String propConnections = "Connections";
		String propSessions = "Sessions";
		String propClassSysListView = "SysListView32";
		String propClassIndex = ".classIndex";
		String propClassPagetabList = ".Pagetablist";
		/*property name end*/
		String processName = "saplogon.exe";
		String sapLogonName = "SAP Logon 640";
		String SapDomain = "SAP";
		
		/*ctrl type*/
		String ctrl_TextField = "textfield";  //GuiTextField
		
	}
	
	public static void setVariables(String varName,String varValue){
		variables.put(varName, varValue);
	}
	public static String getVariableValueByName(String varName){
		return variables.get(varName);
	}
	
	private void init(){
		try{
		getRootTestObject().enableForTesting(IConstant.processName);
		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotDomain, IConstant.SapDomain));
		if(sapApps!=null&&sapApps.length > 0){
			theSapApp = ((SAPTopLevelTestObject)sapApps[0]).getApplication();
			//logInfo("SAP Application Number:" + theSapApp.getProperty(IConstant.propID));
			TestObject[] cons = (TestObject[])theSapApp.getProperty(IConstant.propConnections);
			theSapConnection = (SAPGuiConnectionTestObject)cons[0];
			//logInfo("SAP Connection Number:" + theSapConnection.getProperty(IConstant.propID));
			TestObject[] sessions = (TestObject[])theSapConnection.getProperty(IConstant.propSessions);
			theSapSession = (SAPGuiSessionTestObject)sessions[0];
			//logInfo("SAP Session Number:" + theSapSession.getProperty(IConstant.propID));
			theSapTansactionText = (SAPGuiTextTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiOkCodeField,IConstant.propName,"okcd");
			theSapExeButton = (SAPGuiToggleTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiButton,IConstant.propName,"btn[0]");
			theSapMenubar = (SAPGuiMenubarTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiMenubar,IConstant.propName,"mbar");
			// = findObject(IConstant.);

			/*common elements in Mainwindow can be added here*/
		}
		}catch(NullPointerException e){
			logError("NullPointerException in init function");
			
		}catch(ArrayIndexOutOfBoundsException e){
			logError("ArrayIndexOutOfBoundsException in init function");
		}catch(ClassCastException e){
			logError("ClassCastException in init function");
		}
		catch(Exception e){
			
		}
	}
	
	public void end(){
		try{
			theSapApp.unregister();
			theSapConnection.unregister();
			theSapSession.unregister();
			theSapExeButton.unregister();
			theSapTansactionText.unregister();
		}catch(NullPointerException e){
			
		}catch(UnregisteredObjectException e){
			
		}
	}
	
	private void getSession(){
		try{
		getRootTestObject().enableForTesting(IConstant.processName);
		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotDomain, IConstant.SapDomain));
		if(sapApps!=null&&sapApps.length > 0){
			theSapApp = ((SAPTopLevelTestObject)sapApps[0]).getApplication();
			TestObject[] cons = (TestObject[])theSapApp.getProperty(IConstant.propConnections);
			theSapConnection = (SAPGuiConnectionTestObject)cons[0];
			TestObject[] sessions = (TestObject[])theSapConnection.getProperty(IConstant.propSessions);
			theSapSession = (SAPGuiSessionTestObject)sessions[0];		
		}
		}catch(NullPointerException e){
			logError("NullPointerException in getSession function");
			
		}catch(ArrayIndexOutOfBoundsException e){
			logError("ArrayIndexOutOfBoundsException in getSession function");
		}catch(ClassCastException e){
			logError("ClassCastException in getSession function");
		}
		catch(Exception e){
			logError(e.getLocalizedMessage());
		}
	}
	
//	public  void startSap(String exeDir){
//		try{
//		closeSapApp();
//		Runtime.getRuntime().exec(exeDir);
//		Thread.sleep(5000);
//		getRootTestObject().enableForTesting(IConstant.processName);
//		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotName, IConstant.sapLogonName));
//		if(sapApps.length>0){
//			System.out.println("startSap: "+sapApps[0].getProperties());
//			TestObject[] objs = sapApps[0].find(atDescendant(IConstant.propDotClass,IConstant.propClassDotPushbutton));
//			for(int i=0;i<objs.length;i++){
//				System.out.println("number :"+i+"; property name is: "+objs[i].getProperties()+"; class is"+objs[i].getClass());
//				try{
//				String name = (String)objs[i].getProperty(IConstant.propDotName);
//				if(name!=null&&name.compareTo("&Log on")==0)
//					((GuiTestObject)objs[i]).click();	
//				}catch(UnableToPerformActionException e){
//					continue;
//				}
//			}
//			Thread.sleep(10000);
//			init();
//		}
//		}catch(Exception e){
//			System.err.println(e.getCause().toString());
//		}
//	}
	public  void startSap(String exeDir){
		try{
		closeSapApp();
		if(!isLogonExists())
			Runtime.getRuntime().exec(exeDir);
		Thread.sleep(5000);
		getRootTestObject().enableForTesting(IConstant.processName);
		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotName, IConstant.sapLogonName));
		if(sapApps.length>0){
			System.out.println("startSap: "+sapApps[0].getProperties());
			TestObject[] objs = sapApps[0].find(atDescendant(IConstant.propDotClass,IConstant.propClassButton));
			int flag = 0;
			for(int i=0;i<objs.length;i++){
				System.out.println("number :"+i+"; property name is: "+objs[i].getProperties()+"; class is"+objs[i].getClass());
				try{
				String name = (String)objs[i].getProperty(IConstant.propDotName);
				if(name!=null&&name.contains("Log on")){
					TestObject[] tempObjs = objs[i].getChildren();
					for(int k=0;k<tempObjs.length;k++){
						String ss = (String)tempObjs[k].getProperty(IConstant.propDotName);
						if(ss!=null&&ss.contains("Log on")){
							((GuiTestObject)tempObjs[k]).click();
							flag = 1;
							break;
						}
					}			
				}
				}catch(UnableToPerformActionException e){
					continue;
				}catch(NullPointerException e){
					
				}
				if(flag==1)
					break;
			}
			Thread.sleep(10000);
			init();
		}
		}catch(Exception e){
			System.err.println(e.getCause().toString());
		}
	}
	
	public  void startSap(String exeDir,String system){
		try{
		closeSapApp();
		if(!isLogonExists())
			Runtime.getRuntime().exec(exeDir);
		Thread.sleep(5000);
		getRootTestObject().enableForTesting(IConstant.processName);
		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotName, IConstant.sapLogonName));
		if(sapApps.length>0){
			System.out.println("startSap: "+sapApps[0].getProperties());
			if(system!=null&&!system.equalsIgnoreCase("default")){
				TestObject[] selectObjs = sapApps[0].find(atDescendant(IConstant.propDotClass,IConstant.propClassSysListView));
				
				for(int k=0;k<selectObjs.length;k++){
					try{
						Object idObject = selectObjs[k].getProperty(".id");
						long id = -1;
						if(idObject!=null){
							id = ((Long)idObject).longValue();
						}
						if(id==1008){
							SelectGuiSubitemTestObject textSelectObj = (SelectGuiSubitemTestObject)selectObjs[k];
							int index = Integer.valueOf(system);
							textSelectObj.select(index);
						}
					}catch(ClassCastException e){
						System.err.println(e.getStackTrace());
					}
					catch(UnableToPerformActionException e){
						System.err.println(e.getStackTrace());
					}catch(SubitemNotFoundException e){
						System.err.println(e.getStackTrace());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			
			TestObject[] objs = sapApps[0].find(atDescendant(IConstant.propDotClass,IConstant.propClassButton));
			int flag = 0;
			for(int i=0;i<objs.length;i++){
				System.out.println("number :"+i+"; property name is: "+objs[i].getProperties()+"; class is"+objs[i].getClass());
				try{
				String name = (String)objs[i].getProperty(IConstant.propDotName);
				if(name!=null&&name.contains("Log on")){
					TestObject[] tempObjs = objs[i].getChildren();
					for(int k=0;k<tempObjs.length;k++){
						String ss = (String)tempObjs[k].getProperty(IConstant.propDotName);
						if(ss!=null&&ss.contains("Log on")){
							((GuiTestObject)tempObjs[k]).click();
							flag = 1;
							break;
						}
					}			
				}
				}catch(UnableToPerformActionException e){
					continue;
				}catch(NullPointerException e){
					
				}
				if(flag==1)
					break;
			}
			Thread.sleep(10000);
			init();
		}
		}catch(Exception e){
			System.err.println(e.getCause().toString());
		}
	}
	
	private boolean isLogonExists(){
		try{
		getRootTestObject().enableForTesting(IConstant.processName);
		TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propDotName, IConstant.sapLogonName));
		for(int i=0;i<sapApps.length;i++){
			try{
			String name = (String)sapApps[i].getProperty(IConstant.propDotClass);
			if(name!=null&&name.compareTo("#32770")==0)
				if(!((TopLevelSubitemTestObject)sapApps[i]).isShowing()){
					((TopLevelSubitemTestObject)sapApps[i]).restore();
					return true;
				}
			}catch(PropertyNotFoundException e){
				continue;
			}catch(ClassCastException e){
				continue;
			}
		}
		}catch(NullPointerException e){
			
		}
		return false;
	}
	
	public boolean isSapMainWinExists(){
			getRootTestObject().enableForTesting(IConstant.processName);
			TestObject[] sapApps = getRootTestObject().find(atChild(IConstant.propName, "wnd[0]"));
			if(sapApps!=null&&sapApps.length>0){
				if(!((SAPTopLevelTestObject)sapApps[0]).isShowing()){
					((SAPTopLevelTestObject)sapApps[0]).restore();
				}
				return true;
			}
			else
				return false;
	}
	
	public static void logon(String userName,String pwd){
		
	}
	
	public TestObject sapExist(){
		try{
			//getRootTestObject().getDomains()
			boolean level1 = getRootTestObject().enableForTesting(IConstant.processName);
			if(!level1)
				return null;
			if(theSapSession==null)
				getSession();
			SAPTopLevelTestObject topObject = (SAPTopLevelTestObject)theSapSession.getProperty(IConstant.propActiveWindow);
			if(topObject!=null)
				return topObject;
		}catch(NullPointerException e){
			logError("NullPointerException in sapExist function");
		}catch(ClassCastException e){
			logError("ClassCastException in sapExist function");
		}
		return null;
	}
	public  void logon(String exeDir, String userName, String pwd){
		closeSapApp();
		startSap(exeDir);
		TestObject obj = findObject(IConstant.propDotClass,IConstant.propClassGuiTextField,IConstant.propName,"RSYST-BNAME");
		setText(obj,userName);
		obj = findObject(IConstant.propDotClass,IConstant.propClassGuiPasswordField,IConstant.propName,"RSYST-BCODE");
		setText(obj, pwd);
		clickExecuteButton();
	}
	
	private void setExecutionButton(){
		theSapExeButton = (SAPGuiToggleTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiButton,IConstant.propName,"btn[0]");
	}
	private void setTransactionText(){
		theSapTansactionText = (SAPGuiTextTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiOkCodeField,IConstant.propName,"okcd");
	}
	
	private void setMenuBar(){
		theSapMenubar = (SAPGuiMenubarTestObject)findObject(IConstant.propDotClass,IConstant.propClassGuiMenubar,IConstant.propName,"mbar");
	}
	
	public  TestObject findObject(String classProp, String className,String oneProp, String onePropValue){
		try{
			Thread.sleep(2000);
			if(theSapSession==null)
				getSession();
			SAPTopLevelTestObject topObject = (SAPTopLevelTestObject)theSapSession.getProperty(IConstant.propActiveWindow);
			if(topObject!=null){
				TestObject[] objs = topObject.find(atDescendant(classProp,className));
				for(int i=0;objs!=null&&i<objs.length;i++){
					if(isPropertyExist(objs[i],oneProp)){
						String name = (String)objs[i].getProperty(oneProp);
						if(name!=null&&name.compareTo(onePropValue)==0){
//							TestObject[] tt = objs[i].getChildren();
//							for(int k=0;k<tt.length;k++){
//								System.out.println("MenuItem properties are :"+ tt[k].getProperties());
//							}
							return objs[i];
						}
					}
				}
			}
		}catch(NullPointerException e){
			logError("findObject","theSapSession obj is null");
		}catch(PropertyNotFoundException e){
			logError("findObject",classProp+" or "+oneProp+" does not be found!");
		}
		catch(ObjectNotFoundException e){
			logError("findObject","Could not find object");
		}
		catch(Exception e){
			
		}
		
		return null;
	}
	
	public  TestObject findObjectBasedOnObj(TestObject obj,String classProp, String className,String oneProp, String onePropValue){
		try{
			Thread.sleep(2000);
			if(obj!=null){
				TestObject[] objs = obj.find(atDescendant(classProp,className));
				for(int i=0;objs!=null&&i<objs.length;i++){
					if(isPropertyExist(objs[i],oneProp)){
						String name = (String)objs[i].getProperty(oneProp);
						if(name!=null&&name.compareTo(onePropValue)==0){
							return objs[i];
						}
					}
				}
			}
		}catch(NullPointerException e){
			logError("findObjectBasedOnObj","obj is null");
		}catch(PropertyNotFoundException e){
			logError("findObjectBasedOnObj",classProp+" or "+oneProp+" does not be found!");
		}
		catch(ObjectNotFoundException e){
			logError("findObjectBasedOnObj","Could not find object");
		}
		catch(Exception e){
			
		}
		
		return null;
	}
	
	public  TestObject findChildObjectBasedOneProperty(TestObject obj,String prop,String propValue){
		try{
			Thread.sleep(2000);
			if(obj!=null){
				TestObject[] objs = obj.getChildren();
				for(int i=0;objs!=null&&i<objs.length;i++){
						String name = (String)objs[i].getProperty(prop);
						if(name!=null&&name.compareTo(propValue)==0){
							return objs[i];
						}
				}
			}
		}catch(NullPointerException e){
			logError("findChildObjectBasedOneProperty","obj is null");
		}catch(PropertyNotFoundException e){
			logError("findChildObjectBasedOneProperty",prop+" does not be found!");
		}
		catch(ObjectNotFoundException e){
			logError("findChildObjectBasedOneProperty","Could not find object");
		}
		catch(Exception e){
			
		}
		
		return null;
	}
	public  void setText(TestObject obj,String text){
		try{
			((SAPGuiTextTestObject)obj).setText(text);
		}catch(NullPointerException e){
			logError("setText","Objec is null");
		}catch(ClassCastException e){
			logError("setText","Could not perform this action");
		}
	}
	
	public void setCurrentDate(TestObject obj){
		try{
			String text = "";
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat();
			formater.applyPattern("yyyy/MM/dd");
			text = formater.format(date);
			((SAPGuiTextTestObject)obj).setText(text);
		}catch(NullPointerException e){
			logError("setText","Objec is null");
		}catch(ClassCastException e){
			logError("setText","Could not perform this action");
		}
	}
	
	public String getValueFromControl(String ctrlType,TestObject obj){
		String value = "";
		if(ctrlType!=null&&ctrlType.equalsIgnoreCase(IConstant.ctrl_TextField)){
			value = getValueFromTextField(obj);
		}
		return value;
	}
	
	public String getStatusInfo(TestObject obj, int startIndex, int endIndex){
		String result = "";
		try{
			if(obj != null && obj instanceof SAPGuiStatusbarTestObject){
				ITestDataText data = (ITestDataText)((SAPGuiStatusbarTestObject)obj).getTestData("text");
				result = data.getText().substring(startIndex, endIndex);
			}
		}catch(NullPointerException e){
			System.err.println(e.getCause());
		}catch(ClassCastException e){
			System.err.println(e.getCause());
		}
		return result;
	}

	
	public String getValueFromTextField(TestObject obj){
		String value = "";
		try{
			value = ((SAPGuiTextTestObject)obj).getText();
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(ClassCastException e){
			e.printStackTrace();
		}
		return value;
	}
	
	public void menuSelect(TestObject obj){
		try{
			SAPGuiMenuTestObject menuObj = (SAPGuiMenuTestObject)obj;
			if(menuObj.exists()){
				menuObj.select();
				Thread.sleep(this.tiemDelay);
			}
		}catch(NullPointerException e){
			logError("menuSelect","obj is null");
		}catch(ClassCastException e){
			logError("menuSelect","obj could not be converted to SAPGuiMenuTestObject");
		}catch(UnableToPerformActionException e){
			logError("menuSelect","Could not perform menu select action");
		}catch(Exception e){
		}
	}
	
	public void tabSelect(TestObject obj){
		try{
			SAPGuiTabTestObject tabObj = (SAPGuiTabTestObject)obj;
			tabObj.select();
			Thread.sleep(5000);
		}catch(NullPointerException e){
			logError("tabSelect","obj is null");
		}catch(ClassCastException e){
			logError("tabSelect","obj could not be converted to SAPGuiTabTestObject");
		}catch(ObjectNotFoundException e){
			logError("tabSelect","Could not find object");
		}catch(UnableToPerformActionException e){
			logError("tabSelect","Could not perform menu select action");
		}catch(Exception e){
			
		}
	}
	
	public void menuSelect(TestObject obj,long timeDelay){
		this.tiemDelay = timeDelay;
		menuSelect(obj);
	}
	
	public void menuSelect(String menuName){
		try{
			StringTokenizer tokens = new StringTokenizer(menuName,"/");
			int count = tokens.countTokens();
			String[] menus = new String[count];
			int i = 0;
			while(tokens.hasMoreTokens()){
				menus[i++] = tokens.nextToken();
			}
			if(count>1){
				if(theSapMenubar==null)
					setMenuBar();
				TestObject tempObj = theSapMenubar;
				int time = 1000;
				for(int k=0;k<count;k++){
					tempObj = findChildObjectBasedOneProperty(tempObj,IConstant.propName,menus[k]);
					if(k==count-1)
						time = 10000;
					menuSelect(tempObj,time);
				}	
			}
		}catch(NullPointerException e){
			logError("menuSelect:NullPointerException","menuName ["+menuName+"]");
		}catch(ClassCastException e){
			logError("menuSelect:ClassCastException","obj ["+ "menuName ["+menuName+"]");
		}catch(ArrayIndexOutOfBoundsException e){
			logError("menuSelect:ArrayIndexOutOfBoundsException","menuName ["+menuName+"]");
		}catch(Exception e){
			logError("menuSelect:Exception","obj ["+"menuName ["+menuName+"]");
		}
	}
	
	public boolean isSelected(TestObject obj){
		try{
			SAPGuiToggleTestObject tObj = (SAPGuiToggleTestObject)obj;
			State sta = tObj.getState();
			return sta.isSelected();
		}catch(NullPointerException e){
			logError("isCheck","obj is null");
		}catch(ClassCastException e){
			logError("isCheck","obj could not be converted to SAPGuiTabTestObject");
		}catch(ObjectNotFoundException e){
			logError("isCheck","Could not find object");
		}catch(UnableToPerformActionException e){
			logError("isCheck","Could not perform menu select action");
		}catch(Exception e){
			
		}
		return false;
	}
	
	public void expandTreeByKey(TestObject obj,String key){
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				treeObj.expandNodeByKey(key);
				Thread.sleep(3000);
			}
		}catch(NullPointerException e){
			logError("expandTreeByKey","obj is null");
		}catch(ClassCastException e){
			logError("expandTreeByKey","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(UnableToPerformActionException e){
			logError("expandTreeByKey","Could not perform expand action, maybe key is wrong");
		}catch(Exception e){
			logError("Expanding tree failed");
		}
	}
	
	public void doubleClickNodeByKey(TestObject obj, String key){
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				treeObj.doubleClickNodeByKey(key);
				Thread.sleep(4000);
			}
		}catch(NullPointerException e){
			logError("expandTreeByKey","obj is null");
		}catch(ClassCastException e){
			logError("expandTreeByKey","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(UnableToPerformActionException e){
			logError("expandTreeByKey","Could not perform expand action, maybe key is wrong");
		}catch(Exception e){
			logError("Expanding tree failed");
		}
	}
	
	public void selectTreeNodeByKey(TestObject obj, String key){
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				treeObj.selectNodeByKey(key);
				Thread.sleep(3000);
			}
		}catch(NullPointerException e){
			logError("selectTreeNodeByKey","obj is null");
		}catch(ClassCastException e){
			logError("selectTreeNodeByKey","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(UnableToPerformActionException e){
			logError("selectTreeNodeByKey","Could not perform select action, maybe key is wrong");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getTreeNodeTextByKey(TestObject obj, String key){
		String text = "";
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				text = treeObj.getNodeTextByKey(key);
				Thread.sleep(3000);
			}
		}catch(NullPointerException e){
			logError("getTreeNodeTextByKey","obj is null");
		}catch(ClassCastException e){
			logError("getTreeNodeTextByKey","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(Exception e){
			e.printStackTrace();
		}
		return text;
	}
	
	public String getTreeNodeTextByPath(TestObject obj, String path){
		String text = "";
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				text = treeObj.getNodeTextByPath(path);
				Thread.sleep(3000);
			}
		}catch(NullPointerException e){
			logError("getTreeNodeTextByPath","obj is null");
		}catch(ClassCastException e){
			logError("getTreeNodeTextByPath","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(Exception e){
			e.printStackTrace();
		}
		return text;
	}
	
	public String getSelectedNodeKey(TestObject obj){
		String keyValue = "";
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				keyValue = treeObj.getFocusedNodeKey();
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(ClassCastException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyValue;
		
	}
	
	public void collapseTreeByKey(TestObject obj,String key){
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				treeObj.collapseNodeByKey(key);
				Thread.sleep(3000);
			}
		}catch(NullPointerException e){
			logError("collapseTreeByKey","obj is null");
		}catch(ClassCastException e){
			logError("collapseTreeByKey","obj could not be converted to SAPGuiCtrlTreeTestObject");
		}catch(UnableToPerformActionException e){
			logError("collapseTreeByKey","Could not perform expand action, maybe key is wrong");
		}catch(Exception e){
			logError("Collapsing tree failed");
		}
	}
	
	public void comboboxSelect(TestObject obj,String item){
		try{
			SAPGuiComboBoxTestObject comboboxObj = (SAPGuiComboBoxTestObject)obj;
			if(comboboxObj.exists()){
				comboboxObj.setValue(item);
				Thread.sleep(2000);
			}
		}catch(NullPointerException e){
			logError("comboboxSelect","obj is null");
		}catch(ClassCastException e){
			logError("comboboxSelect","obj could not be converted to SAPGuiComboBoxTestObject");
		}catch(UnableToPerformActionException e){
			logError("comboboxSelect","Could not perform combobox select action");
		}catch(Exception e){
			logError("comboboxSelect","Unhandled exception");
		}
	}
	
	public void textFieldClick(TestObject obj){
		try{
			SAPGuiTextTestObject textObj = (SAPGuiTextTestObject)obj;
			if(textObj.exists()){
				textObj.click();
			}
		}catch(NullPointerException e){
			logError("textFieldClick","obj is null -- ["+obj+"]");
		}catch(ClassCastException e){
			logError("textFieldClick","obj could not be converted to SAPGuiComboBoxTestObject --["+obj+"]");
		}catch(UnableToPerformActionException e){
			logError("textFieldClick","Could not perform textfiled click action -- ["+obj+"]");
		}catch(Exception e){
			logError("textFieldClick","Unhandled exception --["+obj+"]");
		}
	}
	
	public String getTextOfTreeNodeByKey(TestObject obj,String key){
		String value = "";
		try{
			SAPGuiCtrlTreeTestObject treeObj = (SAPGuiCtrlTreeTestObject)obj;
			if(treeObj.exists()){
				value = treeObj.getNodeTextByKey(key);
				return value;
			}
		}catch(NullPointerException e){
			logError("getTextOfTreeNodeByKey","Obj is null");
		}catch(ClassCastException e){
			logError("getTextOfTreeNodeByKey","Obj could not be converted to SAPGuiCtrlTreeTestObject");
		}
		return value;
	}
	
	public void clickExecuteButton(){
		try{
			if(theSapExeButton==null)
				setExecutionButton();
			theSapExeButton.click();
			Thread.sleep(10000);
		}catch(NullPointerException e){
			logError("clickExecuteButton","theSapExeButton obj is null");
		}catch(UnableToPerformActionException e){
			logError("clickExecuteButton","theSapExeButton obj could not perform this action");
		}catch(Exception e){
			
		}
	}
	
	public void setTextToTransaction(String command){
		try{
			if(theSapTansactionText==null)
				setTransactionText();
			theSapTansactionText.setText(command);
		}catch(NullPointerException e){
			logError("inputTransaction","theSapTransactionText obj is null");
		}catch(UnableToPerformActionException e){
			logError("inputTransaction","Could not perform this action");
		}
	}
	
	public void clickButton(TestObject obj){
		try{
		if(obj.exists()){
			((SAPGuiToggleTestObject)obj).click();
			Thread.sleep(10000);
		}
		}catch(NullPointerException e){
			logError("clickButton","obj is null");
		}catch(ClassCastException e){
			logError("clickButton","obj is not one kind of button");
		}catch(ObjectNotFoundException e){
			logError("clickButton","the button you want to click does not exist");
		}catch(UnableToPerformActionException e){
			logError("clickButton","this obj does not perform this action");
		}catch(Exception e){
			
		}
	}
	
	public void closeSapApp(){
		//closeSapLogon();
		closeSapMainWindow();
	}
	public  void closeSapLogon(){
		try{
			TestObject[] sapApps = getRootTestObject().find(atProperty(IConstant.propProcessName,IConstant.processName));
			for(int i=0;sapApps!=null&&i<sapApps.length;i++){
				String name = null;
				//System.out.println("class is : "+sapApps[i].getClass()+";---properties are : "+sapApps[i].getProperties());
				if(isPropertyExist(sapApps[i],IConstant.propDotName)){
					name = (String)sapApps[i].getProperty(IConstant.propDotName);
				}
				if(name!=null&&name.compareTo(IConstant.sapLogonName)==0){
					if(((TopLevelSubitemTestObject)sapApps[i]).isEnabled()){
						((TopLevelSubitemTestObject)sapApps[i]).activate();
						((TopLevelSubitemTestObject)sapApps[i]).inputKeys("%{F4}");
						Thread.sleep(2000);
						break;
					}
				}
			}
		}catch(NullPointerException e){
			logError("closeSapLogon", "NullPointerException, Close sap app failed");
		}catch(ClassCastException e){
			logError("closeSapLogon", "ClassCastException, Close sap app failed");
		}catch(ObjectNotFoundException e){
			logError("closeSapLogon", "ObjectNotFoundException, Close sap app failed");
		}catch(UnableToPerformActionException e){
			logError("closeSapLogon", "UnableToPerformActionException, Close sap app failed");
		}
		catch(Exception e){	
			logError("closeSapLogon",e.getCause().toString());
			logError("closeSapLogon", "Exception, Close sap app failed");
		}
	}
	
	private  Subitem atProperty(String prop,String propValue){
		return SubitemFactory.atProperty(prop,propValue);
	}
	
	private  Subitem atChild(String prop,String propValue){
		return SubitemFactory.atChild(prop,propValue);
	}
	private  Subitem atDescendant(String prop,String propValue){
		return SubitemFactory.atDescendant(prop,propValue);
	}
	private  Subitem atDescendant(String prop1,String propValue1,String prop2,String propValue2){
		return SubitemFactory.atDescendant(prop1,propValue1,prop2,propValue2);
	}
	private  RootTestObject getRootTestObject(){
		return RationalTestScript.getRootTestObject();
	}

	
	public  boolean isPropertyExist(TestObject objUnderCheck, String property){
		if(objUnderCheck!=null){
			Hashtable propHashTable = objUnderCheck.getProperties();
			if(propHashTable!=null){
				boolean isExist = false;
				isExist = propHashTable.containsKey(property); 
				if(isExist)
					return true;
				else {	
					logIntoConsole("isPropertyExist","This property \'"+property+" \'dose not exist!");
					return false;
				}
			}
			
		} else{
			logIntoConsole("isPropertyExist","Object under checking is null");
			return false;
		}
		return false;
	}
	
	public  void logIntoConsole(String logTitle,String logContents){
		System.out.println(logTitle+" : "+logContents);
	}
	public  void logIntoConsole(String contents){
		System.out.println(contents);
	}
	public void logError(String function,String error){
		System.err.println(function+" : "+error);
	}
	public void logError(String error){
		System.err.println(error);
	}
	public  void closeSapMainWindow(){
		try{
		TestObject[] sapApps = getRootTestObject().find(atProperty(IConstant.propProcessName,IConstant.processName));
		for(int i=0;sapApps!=null&&i<sapApps.length;i++){
			if(sapApps[i] instanceof SAPTopLevelTestObject){
				if(true){
					Thread.sleep(3000);
					if(!sapApps[i].exists())
					continue;
				}
				SAPTopLevelTestObject tempTopObj = (SAPTopLevelTestObject)sapApps[i];
				//logIntoConsole("@@@@@@@: "+tempTopObj.getProperties().toString());
				SAPGuiSessionTestObject thisSession = tempTopObj.getSession();
				SAPTopLevelTestObject popupWin =(SAPTopLevelTestObject)thisSession.getProperty(IConstant.propActiveWindow);
				if(popupWin!=null&&popupWin.exists()){
					//popupWin.inputKeys("%{F4}");
					popupWin.close();
				}
				//SthisSession.sendCommand(arg0)
				if(tempTopObj.exists()&&tempTopObj.isEnabled()){
					//tempTopObj.activate();
					tempTopObj.close();
				}
				Thread.sleep(3000);
				if(thisSession.exists())
					popupWin =(SAPTopLevelTestObject)thisSession.getProperty(IConstant.propActiveWindow);
					if(popupWin!=null&&popupWin.exists()){
					TestObject[] tempObjs = popupWin.find(atProperty(IConstant.propDotClass,IConstant.propClassGuiButton));
					for(int k=0;k<tempObjs.length;k++){
						//System.out.println("Popup window "+tempObjs[k].getProperties());
						String textValue = (String)tempObjs[k].getProperty(IConstant.propName);
						if(textValue!=null&&textValue.compareTo("SPOP-OPTION1")==0){
							((SAPGuiToggleTestObject)tempObjs[k]).click();
							break;
						}
					}
				}
			}
		}
		}catch(NullPointerException e){
			logError("closeSapMainWindow", "NullPointerException, Close sap app failed");
		}catch(ClassCastException e){
			logError("closeSapMainWindow", "ClassCastException, Close sap app failed");
		}catch(ObjectNotFoundException e){
			logError("closeSapMainWindow", "ObjectNotFoundException, Close sap app failed");
		}catch(UnableToPerformActionException e){
			logError("closeSapMainWindow", "UnableToPerformActionException, Close sap app failed");
		}catch(Exception e){	
			logError("closeSapMainWindow",e.getCause().toString());
			logError("closeSapMainWindow", "Exception, Close sap app failed");
		}
	}
	
}
