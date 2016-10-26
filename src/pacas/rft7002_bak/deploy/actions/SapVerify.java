package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.interfaces.*;
/**
 * 
 * @author Ryx
 *	Types of verifications
 *		type: Used for classify which kind of verification it is.
 *		retry: The times of retries. 
 *		---------
 *		object: For different types of verification.
 *		value: For different types of verification.
 *		param: For different types of verification.
 *	
 *	1. String in page
 *	"string_in_page"
 *		object: string to match
 *		value: comparison options
 *	2. String in control
 *	"string_in_control"
 *		object: name of the object (defined by user in xml's)
 *		value: string to match
 *		param: comparison options
 *	//	extparam: the property of the object that is to be compared with the string
 *	3. Text the located by labels
 *	"text_by_label"	
 *		object: label text  
 *		value: string to match
 *		param: comparison options
 *		extparam: 
 *	4. Number in control
 *	"number_in_control"
 *		object: the name of the object (defined by user in xml's)
 *		value: number
 *		param: relational comparison options (<, <=, >, >=, ==)
 *	//	extparam: the the property of the object that is to be compared with the number
 *	5. Object existence
 *	"object_existence"
 *		object: the name of the object (defined by user in xml's)
 *		value: longest waiting time (in sec), default to 20 s
 * 		param: number of such objects (could be zero-lengthed string)
 *	6. Item in select
 *	"item_in_select"
 *		object: the name of the object of select type(defined by user in xml's)
 *		value: item value(String)
 * 		param: item index, can be empty
 *	7. Checkbox status
 *	"checkbox_status"
 *		object: the name of the object of checkbox type(defined by user in xml's)
 *		value: checked or unchecked
 *	8. radio status
 *	"radio_status"
 *		object: the name of the object of radio type(defined by user in xml's)
 *		value: selected or notselected
 *	9. manual
 *	"manual"
 *	10. String in table
 *	"string_in_table"
 *		object: the name of the object of table type(defined by user in xml's)
 *		value:string value(String)
 *	11. Item count in select
 *	"item_count_in_select"
 *		object: the name of the object of select type(defined by user in xml's)
 *		value: item count
 *	12. String in textfield
 *	"string_in_textfield"
 *		object: the name of the object of textfield type(defined by user in xml's)
 *		value:string value(String)
 *	13. Selected item
 *	"selected_item"
 *		object: the name of the object of select type(defined by user in xml's)
 *		value: selected item
 *
 * ************************************************
 * following verification type for SAP automation
 * added by kevin.huangfu
 *   1. string in control
 *   "sap_string_in_ctrl"
 *   object: the name of object under checking which is defined in object mapping xml
 *   value: string value needs to be compared
 *   2. whether checked on radio button and checkbox
 *   "sap_is_check"
 *   object: the name of object under checking which is defined in object mapping xml
 *   value: [checked] check if control is checked
 *          [unchecked] check if control is unchecked
 *   3.string in cell
 *   "sap_string_in_cell"
 *   object: the name of object under checking which is defined in boject mapping xml
 *   value: string value needs to be compared
 *   param: [row number:col name] e.g. 2:Area   String
 *   4.status on status bar
 *   "sap_status_check"
 *   object: the name of object under checking which is defined in boject mapping xml
 *   value: status needs to check
 *   5.tree node check
 *   "sap_treenode_check"
 *   object: the name of object under checking which is defined in boject mapping xml
 *   value: value needs to be compared
 *   6. control exist or not
 *   "sap_ctrl_exist"
 *   object: the name of object under checking which is defined in object mapping xml
 *   value: [true](default value) check if control exists
 *          [false] check if control does not exist
 */
public class SapVerify extends ActionBase {

	/**
	 * @param object. The TestCase.Verification object.
	 * @param param	[out]int. The Resulting value.
	 * @param extParam The root test object.
	 */
	public SapVerify(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
		// TODO Auto-generated constructor stub
	}
	public void doAction(Object object, Object param, Object rootObj, Object... args) {
		if (object instanceof TestCase.Verification) {
			TestCase.Verification ver = (TestCase.Verification)object;
			param = new Integer(TestCase.IResult.NOT_RUN);
			String verType = ver.getType().trim();
			boolean neg = false;
			if (verType.startsWith("!")) {
			    neg = true;
			    verType = verType.substring(1);
			}
			// 
			if (verType.equalsIgnoreCase("string_in_page")) {
			    resultInt = VerifyImp.verifyStringInPage(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("object_existence")) {
				resultInt =VerifyImp.verifyObjectExistence(ver.getObject(),ver.getValue(),ver.getParam(),rootObj);
			} else if (verType.equalsIgnoreCase("item_in_select")) {
				resultInt =VerifyImp.verifyItemInSelect(ver.getObject(),ver.getValue(),ver.getParam(),rootObj);
			} else if (verType.equalsIgnoreCase("checkbox_status")) {
				resultInt =VerifyImp.verifyCheckboxStatus(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("radio_status")) {
				resultInt =VerifyImp.verifyRadioStatus(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("manual")) {
				resultInt =VerifyImp.verifyManual(rootObj);
			} else if (verType.equalsIgnoreCase("string_in_table")) {
				resultInt =VerifyImp.verifyStringInTable(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("item_count_in_select")) {
				resultInt =VerifyImp.verifyItemCountInSelect(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("string_in_textfield")) {
				resultInt =VerifyImp.verifyStringInTextfield(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("selected_item")) {
				resultInt =VerifyImp.verifySelectedItem(ver.getObject(),ver.getValue(),rootObj);
			} else if (verType.equalsIgnoreCase("sap_string_in_ctrl")){
				resultInt = SapVerifyImp.isStringInCtrl(ver.getObject(),ver.getValue(),ver.getParam(),null);
			} else if (verType.equalsIgnoreCase("sap_is_check")){
				resultInt = SapVerifyImp.isCheck(ver.getObject(), ver.getValue());
			} else if (verType.equalsIgnoreCase("sap_string_in_cell")){
				resultInt = SapVerifyImp.isStringInCell(ver.getObject(), ver.getValue(), ver.getParam());
			} else if (verType.equalsIgnoreCase("sap_status_check")){
				resultInt = SapVerifyImp.checkStatusOnStatusbar(ver.getObject(), ver.getValue());
			} else if (verType.equalsIgnoreCase("sap_treenode_check")){
				resultInt = SapVerifyImp.checkTreeNode(ver.getObject(), ver.getValue(), ver.getParam());
			} else if (verType.equalsIgnoreCase("sap_ctrl_exist")){
				resultInt = SapVerifyImp.isControlExist(ver.getObject(),ver.getValue());
			}
			
			if (neg==true) {
			    if (resultInt==TestCase.IResult.FAILED) {
			        resultInt = TestCase.IResult.PASSED;
			    } else if (resultInt==TestCase.IResult.PASSED) {
			        resultInt = TestCase.IResult.FAILED;
			    }
			}
		}

	}

}
