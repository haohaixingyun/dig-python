/*
 * Created on Jan 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.common;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * @author luoling
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ObjectItem {
	public String value = new String();

	public String propertyName = new String();

	public String classID = new String();

	public String type = new String();

	public String classIndex = new String();
	
	public String pilot = new String();

	public String refObj = new String(); /*
											 * The "by" property, added by
											 * jeffreybian
											 */

	public String posIndex = new String(); /*
											 * The "index" property, position
											 * index, added by jeffreybian
											 */
	public String name = new String();		/* The name of the item's node*/
	
	Vector results = new Vector();

	public String getClassID() {
		return this.classID;
	}

	public String getClassIndex() {
		return this.classIndex;
	}

	/**
	 * @return the posIndex
	 */
	public String getPosIndex() {
		return posIndex;
	}

	public String getProperty() {
		return this.propertyName;
	}

	/**
	 * @return Returns the refObj.
	 */
	public String getRefObj() {
		return refObj;
	}

	public Vector getResult() {
		return this.results;
	}

	public String getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	public void setClassID(String a) {
		classID = a;
	}

	public void setClassIndex(String a) {
		classIndex = a;
	}

	public void setProperty(String a) {
		propertyName = a;
	}

	/**
	 * @param refObj
	 *            The refObj to set.
	 */
	public void setRefObj(String refObj) {
		this.refObj = refObj;
	}

	public void setResult(Vector a) {
		results = a;
	}

	public void setType(String a) {
		type = a;
	}

	public void setValue(String a) {

		// change the encoding from GBK to UTF-8
		try {
			value = a;
			byte[] tmp = value.getBytes("UTF-8");
			value = new String(tmp, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	/**
	 * @param posIndex the posIndex to set
	 */
	public void setPosIndex(String posIndex) {
		this.posIndex = posIndex;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pilot
	 */
	public String getPilot() {
		return pilot;
	}

	/**
	 * @param pilot the pilot to set
	 */
	public void setPilot(String pilot) {
		this.pilot = pilot;
	}

}
