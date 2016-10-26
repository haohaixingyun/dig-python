package actions;

import hlta.testexec.platform.rft7.web.common.AutoObjectFactory;
import hlta.testexec.testcontrol.java.TestCase;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.*;


public class SapVerifyImp {
	public static int isStringInCtrl(Object control, String val,String param,Object obj){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		int result = TestCase.IResult.NOT_RUN;
		try{
		TestObject testObj = factory.getSapObjectByName((String)control, null);
		if(testObj!=null&&testObj instanceof SAPGuiTextTestObject){
			String value = ((SAPGuiTextTestObject)testObj).getText();
			if(value!=null&&value.compareTo(val)==0){
				result = TestCase.IResult.PASSED;
			}else
				result = TestCase.IResult.FAILED;
				
		}
		else
			result = TestCase.IResult.FAILED;
		}catch(NullPointerException e){
			System.err.println(e.getCause().toString());
		}catch(ClassCastException e){
			System.err.println(e.getCause().toString());
		}
		return result;
	}
	
	public static int isCheck(Object control, String val){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		int result = TestCase.IResult.NOT_RUN;
		//((SAPGuiCtrlGridViewTestObject)control).getCellValue(arg0, arg1);
		try{
			TestObject testObj = factory.getSapObjectByName((String)control, null);
			if(testObj!=null&&testObj instanceof SAPGuiToggleTestObject){
				State state = ((SAPGuiToggleTestObject)testObj).getState();
				boolean ischeck = state.isSelected();
				if("checked".equalsIgnoreCase(val)){
					if(ischeck)
						result = TestCase.IResult.PASSED;
					else
						result = TestCase.IResult.FAILED;
				} else if("unchecked".equalsIgnoreCase(val)){
					if(!ischeck)
						result = TestCase.IResult.PASSED;
					else
						result = TestCase.IResult.FAILED;
				}
			}else {
				result = TestCase.IResult.FAILED;
			}
		}catch(NullPointerException e){
			System.err.println(e.getCause());
		}catch(ClassCastException e){
			System.err.println(e.getCause());
		}
		return result;
	}
	
	public static int isStringInCell(Object control, String val, String rowcol){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		int result = TestCase.IResult.NOT_RUN;
		try{
			String tempStr = rowcol;
			int pos = tempStr.indexOf(":", 0);
			String row = tempStr.substring(0, pos);
			String col = tempStr.substring(pos+1);
			Integer irow = new Integer(row);
			Integer icol = new Integer(col);
			TestObject testObj = factory.getSapObjectByName((String)control, null);
			if(testObj!=null&&(testObj instanceof SAPGuiCtrlGridViewTestObject||testObj instanceof SAPGuiTableControlTestObject)){
				ITestDataTable table;
				if(testObj instanceof SAPGuiCtrlGridViewTestObject)
					table = (ITestDataTable)((SAPGuiCtrlGridViewTestObject)testObj).getTestData("list");
				else
					table = (ITestDataTable)((SAPGuiTableControlTestObject)testObj).getTestData("list");
				String value = (String)table.getCell(irow.intValue(),icol.intValue());
				if(value.equals(val))
					result = TestCase.IResult.PASSED;
				else
					result = TestCase.IResult.FAILED;
			} else 
				result = TestCase.IResult.FAILED;
		}catch(NumberFormatException e){
			System.err.println(e.getCause());
		}catch(NullPointerException e){
			System.err.println(e.getCause());
		}catch(ClassCastException e){
			System.err.println(e.getCause());
		}catch(IndexOutOfBoundsException e){
			System.err.println(e.getCause());
		}
		return result;
	}
	
	public static int isContainInCombobox(Object control, String val){
		return 1;
	}
	public static int isCheckInGridView(Object control, String rwocol){
		return 1;
	}
	public static int checkStatusOnStatusbar(Object control, String status){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		int result = TestCase.IResult.NOT_RUN;
		try{
			TestObject testObj = factory.getSapObjectByName((String)control, null);
			if(testObj!=null&&testObj instanceof SAPGuiStatusbarTestObject){
				ITestDataText data = (ITestDataText)((SAPGuiStatusbarTestObject)testObj).getTestData("text");
				String allStatus = data.getText();
				if(allStatus.contains(status))
					result = TestCase.IResult.PASSED;
				else
					result = TestCase.IResult.FAILED;
			}else
				result = TestCase.IResult.FAILED;
		}catch(NullPointerException e){
			System.err.println(e.getCause());
		}catch(ClassCastException e){
			System.err.println(e.getCause());
		}
		return result;
	}
	
	public static int checkTreeNode(Object control, String value, String param){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		try{
			TestObject testObj = factory.getSapObjectByName((String)control, null);
			if(testObj!=null&&testObj instanceof SAPGuiCtrlTreeTestObject){
				System.out.println(((SAPGuiCtrlTreeTestObject)testObj).getTestDataTypes());
				System.out.println(((SAPGuiCtrlTreeTestObject)testObj).getTestData("tree").getClass());
				System.out.println("Tree type: "+((SAPGuiCtrlTreeTestObject)testObj).getTreeType());
				ITestDataTree text= (ITestDataTree)((SAPGuiCtrlTreeTestObject)testObj).getTestData("selectedItem");
				
				ITestDataTreeNodes nodes = text.getTreeNodes();
				System.out.println("********"+nodes.getNodeCount());
				ITestDataTreeNode []node = nodes.getRootNodes();
				System.out.println(node[0].getNode());
				//System.out.println(((SAPGuiCtrlTreeTestObject)testObj).getTestData("tree").getClass());
//				ITestDataTree tree = (ITestDataTree)((SAPGuiCtrlTreeTestObject)testObj).getTestData("tree");
//				ITestDataTreeNodes nodes= tree.getTreeNodes();
				SAPGuiCtrlTreeTestObject tree = (SAPGuiCtrlTreeTestObject)testObj;
				String key = tree.getFocusedNodeKey();
				String val = tree.getNodeTextByKey(key);
				String path = tree.getNodePathByKey(key);
				//System.out.println(tree.get);
			
				String ttext = tree.getNodeTextByPath("1/1/4/1");
				Subitem item = new Column(1);
				String nnn = tree.getNextNodeKeyByKey(key);
				String s = tree.getNodeTextByKey(nnn);
				//System.out.println("ssssssssss:"+tree.get(key));
				//String cc = tree.getNodeKeyByPath("1/1/4/1");
				
				//tree.getColumnCol(arg0)
				System.out.println("---------"+nnn+"---"+s);
				System.out.println(path);
				System.out.println(ttext);
				System.out.println(key+":"+val);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public static int isControlExist(Object control, String val){
		AutoObjectFactory factory = AutoObjectFactory.getInstance();
		int result = TestCase.IResult.NOT_RUN;
		String defaultVal = "";
		if(val==null||val=="")
			defaultVal = "true";
		else
			defaultVal = val;
		try{
			TestObject testObj = factory.getSapObjectByName((String)control, null);
			if(testObj!=null){
				if("true".equalsIgnoreCase(defaultVal)){
					if(testObj.exists()){
						result = TestCase.IResult.PASSED;
					} else
						result = TestCase.IResult.FAILED;
				} else if("false".equalsIgnoreCase(defaultVal)){
					if(testObj.exists()){
						result = TestCase.IResult.FAILED;
					} else
						result = TestCase.IResult.PASSED;
				}
			}
		}catch(NullPointerException e){
			System.err.println(e.getCause());
		}catch(ClassCastException e){
			System.err.println(e.getCause());
		}
		return result;
	}
}
