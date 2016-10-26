/*
 * Created on Aug 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;

import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.rational.test.ft.script.MouseModifiers;
import ibm.widgets.*;
import java.util.regex.*;
/**
 * @author Wang Zheng
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiSelect extends ActionBase {

	/**
	 * @param object	The object.
	 * @param param		The regex or exact string of the item to select.
	 * @param extParam	Matching options: "r" for reg, "i" for ignore case.	
	 */
	public MultiSelect(Object object, Object param, Object extParam,Object... args) {
		super(object, param, extParam,args);
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// TODO Auto-generated method stub
	    try {
	    	if( param==null || extParam==null ) {
	    		return;
	    	}
	        WListBox w = new WListBox((TestObject)object);

	        String[] strs = w.getContents();
	        
	        boolean ic = false, reg = false;
	        String value = (String)param;
	        String opt = (String)extParam;
	        int in = -1;
	        if (opt.indexOf('i')!=-1) {
	        	ic = true;
	        	value = value.toLowerCase();
	        }
	        if (opt.indexOf('r')!=-1) {
	        	reg = true;
	        }
	        
	       
	        
	        if (strs!=null && strs.length > 0) {
	        	int len = strs.length;
	        	
	        	for (int j = 0;j<len;++j) {
	        		if (ic==true) strs[j] = strs[j].toLowerCase();
	        		if (reg==true) {
	        			if(strs[j].matches(value)) {
	        				in = j;
	        				break;
	        			}
	        		} else { 
	        			if(strs[j].compareTo(value)==0) {
	        				in = j;
	        				break;
	        			}
	        		}
	        	}
	        //	System.out.println(in);
	        	// Now get the index "in"
	        	w.multiSelect(in);	        
	        } else {
	        	// No match found
	        }
	        
	        Thread.sleep(500);
	    } catch (ClassCastException e) {
	        
	    } catch (Exception e) {
	        
	    }
	}

	public static void main(String[] args) {
	}
}
