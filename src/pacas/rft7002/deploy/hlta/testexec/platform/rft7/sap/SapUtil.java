package hlta.testexec.platform.rft7.sap;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;

public class SapUtil {
	public static String getFoucedTreeNodeKey(String classProp, String className,String oneProp, String onePropValue){
		Sap sap = Sap.getInstance();
		TestObject testObj = sap.findObject(classProp, className, oneProp, onePropValue);
		try{
			if(testObj!=null && testObj instanceof SAPGuiCtrlTreeTestObject){
				SAPGuiCtrlTreeTestObject obj = (SAPGuiCtrlTreeTestObject)testObj;
				return obj.getFocusedNodeKey();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getTreeNodeTextByPath(String classProp, String className,String oneProp, String onePropValue,String path){
		Sap sap = Sap.getInstance();
		TestObject testObj = sap.findObject(classProp, className, oneProp, onePropValue);
		try{
			if(testObj!=null && testObj instanceof SAPGuiCtrlTreeTestObject){
				SAPGuiCtrlTreeTestObject obj = (SAPGuiCtrlTreeTestObject)testObj;
				return obj.getNodeTextByPath(path);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] arg){
		System.out.println("The key of focused tree node is : "+SapUtil.getFoucedTreeNodeKey(".class", "GuiShell.Tree", "Name", "shell"));
		String path = "1/2/2";
		System.out.println("Text under this directory ["+path+"] is : "+SapUtil.getTreeNodeTextByPath(".class", "GuiShell.Tree", "Name", "shell", path));
		
	}
}
