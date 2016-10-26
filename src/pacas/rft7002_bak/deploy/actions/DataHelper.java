package actions;

import hlta.testexec.testcontrol.java.TestCase;
import hlta.testexec.testcontrol.java.interfaces.ITestLogger;
import hlta.testexec.testcontrol.java.interfaces.ITestReporter;
import hlta.testexec.testcontrol.java.interfaces.VC;

import java.text.Collator;
import java.util.Locale;
import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import java.io.IOException;

import java.io.RandomAccessFile;
import java.net.URL;
import com.rational.test.ft.script.IOptionName;
import com.rational.test.ft.script.RationalTestScript;

public class DataHelper {

	/*
	 * -------------------------------------------------------------------
	 * The code follows are added by jeffreybian.
	 * Jan 19, 2007
	 * Modified
	 * 	Jan 22, 2007
	 */
	/**
	 * Code for treating data separtation from code.
	 * 
	 * @version 1.10
	 * @author jeffreybian
	 *
	 */
	protected static Locale currentLocale = Locale.getDefault();
	protected static Locale previousLocale = null;
	protected static ResourceBundle stringReader = null;
	protected static String propertiesPath = null;
	// For locale specific compare
	protected static Collator collator = Collator.getInstance(currentLocale);
	// For global data file
	protected static String globalDataFile = "dsw/Global";
	/*begin: jeffreybian, May 2007*/
	protected ITestLogger logger = null;
	protected ITestReporter reporter = null;
	protected TestCase theCase = null;
	/*end: jeffreybian, May 2007*/
	protected static Map <String,ArrayList<String>> dataMap = new HashMap<String,ArrayList<String>>();
	public static Map<String,String> global_var = new HashMap<String,String>();
	public static String defaultDataPrefix = "web";
	
	protected static String tempDir = System.getProperty("user.home");
	/**
	 * Create a folder under the User's home dir.
	 * And put each global var as a file under the directory.
	 */
	static {
		File f = new File(tempDir);
		tempDir = tempDir+ File.separator + "_PACASTemp";
		if (f.exists() && f.isDirectory()) {
			new File(tempDir).mkdirs();
		}
	}
	
	
	/**
	 * Init testcase with logger and reporter, and anything else that is 
	 * case independent.
	 */
	public void base_init (ITestLogger clogger, ITestReporter creporter) {
		if (theCase==null) {
			theCase = TestCase.getCaseInstance(RationalTestScript.getTopScript().getScriptName(),logger,reporter);
		}
		logger = clogger;
		reporter = creporter;
		if (logger==null) {
			logger = TestCase.getDefaultLogger();
		}
		if (reporter==null) {
			reporter = TestCase.getDefaultReporter();
		}
	}
	
	
	/**
	 * Return a relative folder name with the same parent folder of the 
	 * CURRENT RUNNING script.
	 * E.g., A script in app/BPeOrder/Case call this method with param
	 * "data", the app/BPeOrder/case/data path will return. 
	 * If the top folder reached, "" will be returned.
	 * @param sub The subfolder name.
	 * @return The relative folder name, in form of "dsw/BPeOrder/case/data".
	 */
	public static String getSiblingFolderName(String sub) {
		final String errOutOfRange = " Level out of range (1 to 255).";
		final String errReturn = " Zero-lengthed string is returned.";
		// Modified to use a simpler way
		String str = RationalTestScript.getTopScriptName().replace('.','/');
		str = (str.substring(0,str.lastIndexOf("/")));
		str += ("/"+sub+"/");
		 System.out.println("Data path: " + str);
		return str; 
		
	}
	
	public static void setLanguage(Locale locale) {
		currentLocale = locale;
	}
	
	public static Locale getLauguage() {
		return currentLocale;
	}
	
	public static int compare(String str1, String str2) {
		return compare(str1, str2, currentLocale);
	}
	public static int compare(String str1, String str2, Locale locale) {
		return realCompare(str1, str2, locale, Collator.PRIMARY);
	}
	
	public static int compareExact(String str1, String str2) {
		return compareExact(str1, str2, currentLocale);
	}
	public static int compareExact(String str1, String str2, Locale locale) {
		return realCompare(str1, str2, locale, Collator.IDENTICAL);
	}
	
	public static int realCompare(String str1, String str2, Locale locale, int strength) {
		collator = Collator.getInstance(locale);
		collator.setStrength(strength);
		return collator.compare(str1,str2);
	}

	/**
	 * Return a language-specific xml file name with the current locale, which is set by
	 * setLanguage.
	 * @author jeffrey
	 * @param baseName The base path name of the xml file, say 
	 *  'f:\my\ProjectsRelated\DSW9_backup\DSW91\dsw\BPeOrder\pages\ShrinkwrapStep1Of3.xml'
	 * @return
	 */
	public static String getXmlFileForLanguage(String baseName) {
		return getXmlFileForLanguage(baseName, currentLocale);
	}
	/**
	 * Return a language-specific xml file name with the specified locale.
	 * @author jeffrey
	 * @param baseName The base path name of the xml file, say 
	 *  'f:\my\ProjectsRelated\DSW9_backup\DSW91\dsw\BPeOrder\pages\ShrinkwrapStep1Of3.xml'
	 * When default locale specified, the baseName with no other suffix is returned.
	 * @param locale The locale.
	 * @return
	 */
	public static String getXmlFileForLanguage(String baseName, Locale locale) {
		String fullName = baseName;
		if (locale!=Locale.getDefault()) {
			String suffix = locale.toString();
			fullName = fullName.substring(0,fullName.lastIndexOf('.')) + "_" + suffix + ".xml";
		} 
		// System.out.println("Page xml " + fullName + " found.");
		return fullName;
	}
	/**
	 * Set the full file path of the base name of the Global file.
	 * Try not to include much language-specific data in Global properties file.
	 * The initial value is hard coded as 'dsw/Global', you should call this method or 
	 * change the initial value when migrating to other projects.
	 * @author jeffrey
	 * @param fullName The full-qualified pathname of the Global.properties.
	 */	
	public static void setGlobal (String fullName) {
		globalDataFile = fullName;
	}
	/**
	 * Get the project home directory.
	 * @return The format is a file system path.
	 */
	public static String getProjectHome() {
		return (String)(RationalTestScript.getOption(IOptionName.DATASTORE));
	}
	
	public static String getAppData(String varName, Object obj) {
		return getAppData(varName, getSiblingFolderName("data")+obj.getClass().getSimpleName());
	}
	
	/**
	 * Retreive the value from properties file according to the Locale setting.
	 * @author jeffrey
	 * @param varName The name of the key.
	 * @param propertiesFile The base name of the properties file.
	 * @return The String data that is marked by the varName. The returned data
	 * is of UTF-8 encoding to adapt most languages. 
	 */
	public static String getAppData (String varName, String propertiesFile) {
		System.out.println("propertiesFile path: " + propertiesFile);
		final String errReturn = " Zero-lengthed string is returned.";
		
		String result = "";
		String errMsg = "";
		String fldName = "";
			
		try {
			// For efficiency
			if ( propertiesPath ==  propertiesFile && 
				currentLocale == previousLocale) {
				// Do nothing at all
			} else {
				stringReader = ResourceBundle.getBundle(
						propertiesFile,
						currentLocale
						);			
				propertiesPath = propertiesFile;
				previousLocale = currentLocale;
			}
		result = stringReader.getString(varName).trim();
		// Use UTF-8
		// byte[] tmp = result.getBytes("UTF-8");
		// result = new String(tmp,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			errMsg = e.getMessage();
		} finally {
			if (errMsg!="") {
				System.err.println(errMsg + errReturn);
				result = "";
			}
		}
		
		return result;
	}
	
	public static void initAppData(Object obj){
		dataMap.clear();
		String fileName = getSiblingFolderName("data")+obj.getClass().getSimpleName()+".csv";
		URL url = obj.getClass().getResource("/");
		String ss = url.getPath();
		fileName = ss+fileName;
		try{
			String rec = null;
			String[] argsArr = null;
			int row=0;
			int col=0;
			RandomAccessFile fi = new RandomAccessFile(new File(fileName),"r");
			while((rec=fi.readLine())!=null){
				argsArr = rec.split(",");
				col = argsArr.length;
				row++;
			}	
			fi.seek(0);
			String [][]dataArray = new String[row][col];
			int kk = 0;
			while((rec=fi.readLine())!=null){
				argsArr = rec.split(",");
				for(int j=0;j<argsArr.length;j++){
					String temp = argsArr[j]; 
					if(temp.indexOf(";;;",0)>=0){
						temp = temp.replaceAll(";;;", ",");
					}
					dataArray[kk][j] = temp;
				}
				kk++;
			}
			
			for(int i=0;i<dataArray[0].length;i++){
				ArrayList<String> aList = new ArrayList<String>();
				for(int ii=1;ii<dataArray.length;ii++){
					aList.add(dataArray[ii][i]);
				}
				dataMap.put(dataArray[0][i], aList);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getAppData(String varName,String index,TestCase.Step step){
		// Global variable PRI is higher, modified by kevin
		// Added by Jeffrey, user getStr to retrieve global var.
//		if(global_var.containsKey(varName)){  
//			return global_var.get(varName);
//		}
		int iIndex = 1;
		if(index!=null){
			try{
			iIndex = new Integer(index).intValue();
			}catch(ClassCastException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if (step == null)
		{
			String ret = getStr(varName);
			if (ret.length()!=0) {
				return ret;
			}
			if(varName.indexOf(":")<0){
				varName = defaultDataPrefix+":"+varName;
			}
			ArrayList list = dataMap.get(varName);
			if(iIndex<=0||iIndex>list.size()){
				System.err.println("------------ Data ("+varName+") index is out of range ----------");
				return "";
			}
			return (String)list.get(iIndex-1);
		}
		
		String ret = getStr(varName);
		if (ret.length()!=0) {
			step.setStepDataText(varName + "=" + ret + "<br>");
			return ret;
		}
		if(varName.indexOf(":")<0){
			varName = defaultDataPrefix+":"+varName;
		}
		ArrayList list = dataMap.get(varName);
		if(iIndex<=0||iIndex>list.size()){
			//step.setStepDataText(varName + "=" + "null" + "<br>");
			System.err.println("------------ Data ("+varName+") index is out of range ----------");
			return "";
		}
		step.setStepDataText(varName + "=" + list.get(iIndex-1).toString() + "<br>");
		return (String)list.get(iIndex-1);
	}
	public static String getAppData(String varName,int index,TestCase.Step step){
		// Global variable PRI is higher, modified by kevin
		// Added by Jeffrey, user getStr to retrieve global var.
//		if(global_var.containsKey(varName)){  
//			return global_var.get(varName);
//		}
		if (step == null)
		{
			String ret = getStr(varName);
			if (ret.length()!=0) {
				return ret;
			}
			if(varName.indexOf(":")<0){
				varName = defaultDataPrefix+":"+varName;
			}
			ArrayList list = dataMap.get(varName);
			if(index<=0||index>list.size()){
				System.err.println("------------ Data ("+varName+") index is out of range ----------");
				return "";
			}
			return (String)list.get(index-1);
		}
		
		String ret = getStr(varName);
		if (ret.length()!=0) {
			step.setStepDataText(varName + "=" + ret + "<br>");
			return ret;
		}
		if(varName.indexOf(":")<0){
			varName = defaultDataPrefix+":"+varName;
		}
		ArrayList list = dataMap.get(varName);
		if(index<=0||index>list.size()){
			//step.setStepDataText(varName + "=" + "null" + "<br>");
			System.err.println("------------ Data ("+varName+") index is out of range ----------");
			return "";
		}
		step.setStepDataText(varName + "=" + list.get(index-1).toString() + "<br>");
		return (String)list.get(index-1);
	}
	/**
	 * Note: The data properties file must be placed in the application folder,
	 *       namely the folder 1 level up.
	 * @author jeffrey
	 * @param varName The key name.
	 * @return The data indicated by varName in related testdata file.
	 */
	public static String getTestData(String varName) {
		return getAppData(varName,getSiblingFolderName("data") + "testdata");
	}
	
	/**
	 * Note: The data properties file must be placed in the application folder,
	 *       namely the folder 1 level up.
	 * @author jeffrey
	 * @param varName The key name.
	 * @return The data indicated by varName in related vpdata file.
	 */
	public static String getVpData(String varName) {
		return getAppData(varName,getSiblingFolderName("data") + "vpdata");
		
	}

	/**
	 * Added by Jeffrey Bian
	 * @author Jeffrey
	 * @date 2008.4
	 * Rewrite original setGobalVar and getGlobalData.
	 * 
	 * 1. The persistence of global vars. Enabling global variables
	 * shared between different scripts.
	 * 2. getStr mimics the behaviors of ResourceBundle's
	 * 3. putStr is for storing and getStr is for retrieving
	 *  
	 */
	public static void putStr (String key, String value) {
		global_var.put(key, value);
		File f = new File (tempDir + File.separator + key);
		try {
			if (f.createNewFile()) {
				
			} 
			PrintWriter pr = new PrintWriter(f);
			pr.write(value);
			pr.close();
		} catch (IOException e) {
			System.err.println("Error persisting the value.");
		}
	}
	public static String getStr (String key) {
		String result = global_var.get(key);
		if (result==null) {
			File f = new File (tempDir + File.separator + key);
			if (f.exists() && f.isFile()) {
				try {
					FileReader fr = new FileReader(f);
					char[] cc = new char[(int)f.length()];
					fr.read(cc);
					result = new String(cc);
				} catch (FileNotFoundException e) {
					System.err.println("No such value. Zero-lengthed string returned.");
					// e.printStackTrace();
				} catch (IOException e) {
					System.err.println("Error getting the value.");
				}
			} else {
				//System.err.println("No such value. Zero-lengthed string returned.");
				result = "";
			}
		}
		return result.trim();
	}
	
	
	/**
	 * 
	 */
	public static void setGlobalVar(String key, String value){
		putStr(key,value);
	}
	/**
	 * 
	 */
	public static String getGlobalData(String varName) {
		String result = getStr(varName);
		return result;
	}
	/**
	 * End of added by Jeffrey Bian
	 */
	/*
	 * -------------------------------------------------------------------
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
