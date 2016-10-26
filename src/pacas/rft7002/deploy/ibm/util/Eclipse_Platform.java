// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import java.util.*;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;

/**
 * Class <code>Eclipse_Platform</code> exposes some useful static methods of
 * </code>org.eclipse.runtime.core.Platform</code>, which is 
 * the central class of the Eclipse Platform Runtime.
 * 
 * <p>
 * Use this class to get runtime information like JRE version,plugin descriptors,platform version,etc.
 * Note: The only <code>TestObject</code> related to any Eclipse UI is ever exposed by this class is the method
 * <code>Eclipse_Platform.getWorkbench()</code>, which is normally passed as an argument to the constructor of
 * <code>SWT_Widgets_Workbench</code>.
 * Any plugin descriptors TestObjects are passed as argument to the static methods of<code>Eclipse_PluginDescriptor</code>.
 * 
 * Some APIs exposed by this class are obsolete that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
 * Those obsoleted APIs will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0). 
 * </p>
 * @author bklau
 * @since 2004
 * @version 2.2
 * Last Modified: 12/08/04
 */
public class Eclipse_Platform {
 	private static DomainTestObject m_domainTO=null;
 	private static TestObject m_pluginRegistryTO=null;
    private static Map m_rtProps=new HashMap();
 	/**
 	 * An inner class to get an array of DomainTestObjects.
 	 */
 	private static class DomainSupplier extends RationalTestScript{
 		public DomainTestObject[] rtsGetDomains()
 		{
 			return super.getDomains();
 		}
 		
 	} 
	/**
	 * Initialize the TestObjects used by this class.
	 */
	private static void setDomainTestObject(){
		DomainTestObject[] domTOs= new DomainSupplier().rtsGetDomains();
		if(domTOs==null || domTOs.length==0){
			throw new RationalTestError("No Rational Functional Tester-enabled Test Domains have been detected.");
		}	
		//
		java.util.List eclipseDomainList= new ArrayList();
		DomainTestObject dTO=null;
		for(int i=0;i<domTOs.length;i++){
			
			if(((String)domTOs[i].getName()).toLowerCase().equals("java")){
				dTO=domTOs[i];								
				if(dTO !=null){
					String fileSep=(String)dTO.getProperty("file.separator");
					String java_classpath=(String)dTO.getProperty("java.class.path");
					String eclipseClassPath="eclipse" + fileSep + "startup.jar";
					if(java_classpath.indexOf(eclipseClassPath)>-1){
						eclipseDomainList.add(dTO);				
					}
				}
			}
		}
		//
		if(eclipseDomainList.isEmpty()){
			throw new RationalTestError("No Rational Functional Tester-enabled Eclipse Test Domains found."
			+ "\n Either Eclipse AUT has not been launched or has not been enabled with Rational Functional Tester plugin: "
			+ "\n{\n	plugin id=com.rational.test.ft.enabler.wsw"
			+ "\n	name=Eclipse 2.x,3.x Enabler for Rational XDE/Functional Tester\n}" );
		}else if(eclipseDomainList.size()>1){
			throw new RationalTestError(eclipseDomainList.size() + " ambiguous Eclipse Test Domains found. Please close all except one designted as AUT.");
		}else{
			m_domainTO=(DomainTestObject)eclipseDomainList.get(0);
		}
									
	}
	/**
	 * Return the <code>DomainTestObject</code> for this Eclipse AUT.
	 * @return the Eclipse AUT <code>DomainTestObject</code>
	 */
	public static DomainTestObject getDomainTestObject(){
		setDomainTestObject();
		return m_domainTO;	
	}
	/**
	 * Returns the TestObject associated with an instance of <code>org.eclispe.core.runtime.IPluginRegistry</code>,
	 * typically <code>org.eclipse.core.internal.plugins.PluginRegistry</code>, at least in Eclipse 2.x.
	 * <p>
	 * 
	 * This is the global instance of Plugin Registry for the Eclipse instance.
	 *	  
	 * Note: This is obsolete API that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
	 * This API will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0). 
	 *	
	 * Note: This is method only works if the runtime compatibility support (see org.eclipse.core.runtime.compatibility) is installed. 	 
	 * </p>
	 * @return an instance of <code>IPluginRegistry</code>
	 * 
	 */
	public static TestObject getPluginRegistry(){
		setDomainTestObject();
		m_pluginRegistryTO=(TestObject)m_domainTO.invokeStaticMethod("org.eclipse.core.runtime.Platform","getPluginRegistry");
		return m_pluginRegistryTO;
	}
	
	/**
	 * Returns the TestObject assocaited with the plug-in runtime object for the identified plug-in 
	 * or null if no such plug-in can be found.
	 * <p> 
	 * 
	 * If the plug-in is defined but not yet activated, the plug-in will be activated before being returned. 
	 * Note: This is obsolete API that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
	 * This API will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0). 
	 *	
	 * Note: This is method is only able to find and return plug-in objects for plug-ins described using plugin.xml according to 
	 * the traditional Eclipse conventions. Eclipse 3.0 permits plug-ins to be described in manifest.mf files and
	 * to define their own bundle activators.
 	 * Such plug-ins cannot be discovered by this method
 	 * </p>
 	 * @param id the unique identifier of the desired plug-in (e.g., "org.eclipse.debug.ui").
 	 * @return the plug-in runtime object, or null
 	 * 
	 */
	
	public static TestObject getPlugin(String id){
		setDomainTestObject();
		String sig="(Ljava/lang/String;)Lorg/eclipse/core/runtime/Plugin;";
		return (TestObject)m_domainTO.invokeStaticMethod("org.eclipse.core.runtime.Platform","getPlugin",sig,new Object[]{id});
		
	}
	/**
	 * Returns a TestObject associated with a plugin's descriptor.
	 * <p>
	 * Note: This is obsolete API that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
	 * This API will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0).
	 * Furthermore, this API is only functional if the org.eclipse.core.runtime.compatibility plug-in is present (and resolved) in the system.
	 * </p>
	 * @param id the plugin id that uniquely identifies the plugin
	 * @return a TestObject associated with the plugin's descriptor or <code>null</code>
	 * if a descriptor with <code>id</code> cannot be found.
	 */
	public static TestObject getPluginDescriptor(String id){
		setDomainTestObject();
		TestObject pluginRegTO=getPluginRegistry();
		String sig="(Ljava/lang/String;)Lorg/eclipse/core/runtime/IPluginDescriptor;";
		return (TestObject)pluginRegTO.invoke("getPluginDescriptor",sig,new Object[]{id});
	}
	/**
	 * Returns an array of TestObject associated with all plugin descriptors.
	 * <p>
	 * Note: This is obsolete API that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
	 * This API will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0).
	 * Furthermore, this API is only functional if the org.eclipse.core.runtime.compatibility plug-in is present (and resolved) in the system.
	 * </p>
	 * @return an array of TestObject of plugin's descriptors knowm to the Plugin Registry
	 */
	public static TestObject[] getPluginDescriptors(){
		setDomainTestObject();
		TestObject pluginRegTO=getPluginRegistry();
		return (TestObject[])pluginRegTO.invoke("getPluginDescriptors");
	}
	/**
	 * Returns all versions of the identified plug-in descriptor known to the Plugin Registry.
	 * <p>
	 * Note: This is obsolete API that will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
	 * This API will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0).
	 * Furthermore, this API is only functional if the org.eclipse.core.runtime.compatibility plug-in is present (and resolved) in the system.
	 * </p>
	 * @param id the plugin id that uniquely identifies the plugin
	 * @return a TestObject associated with the plugin's descriptor or 
	 * an empty array if there are no plug-ins with the specified identifier. 
	 */
	public static TestObject[] getPluginDescriptors(String id){
		setDomainTestObject();
		TestObject pluginRegTO=getPluginRegistry();
		String sig="(Ljava/lang/String;)[Lorg/eclipse/core/runtime/IPluginDescriptor;";
		return (TestObject[])pluginRegTO.invoke("getPluginDescriptors",sig,new Object[]{id});
	}
	/**
	 * Returns the Java Runtime Environment(JRE) used to launch the SUT instance of Eclipse platform.
	 * <p>
	 * The info is returned in the format:
	 * 	<code>
	 * 	Client JRE=<java.runtime.name>
	 *  Version= <java.runtime.version>
	 *  Vendor= <java.vendor>
	 *  Location=<java.home>
	 *  VM name=<java.vm.name>
	 *  VM info=<java.vm.info>
	 * </code>
	 * Note: If no JRE is specified when launching the platform, Eclipse will use the first JRE located on the
	 * system classpath.
	 * 
	 * Example:
	 * <i>
	 * 	Client JRE=Java(TM) 2 Runtime Environment, Standard Edition
	 *  Version=1.4.1
	 *	Vendor=IBM Corporation
	 *	Location=C:\IBM_JDK1.4.1\jre
	 *	VM name=Classic VM
	 *	VM info=J2RE 1.4.1 IBM Windows 32 build cn1411-20030930 (JIT enabled: jitc)
	 * </i>
	 * </p>
	 * @return a JRE info string
	 */
	public static String getJREVersionInfo(){
		if(m_rtProps.size()==0){
			m_rtProps=getStandardProperties();		
		}
		String java_vendor="";
		String java_vm_info="";
		String java_vm_name="";
		String java_runtime_name="";
		String java_runtime_version="";
		String java_home="";
		String eclipse_vm="";
		
		java_runtime_name = (String)m_rtProps.get("java.runtime.name");
		java_runtime_version=(String)m_rtProps.get("java.runtime.version");
		java_home=(String)m_rtProps.get("java.home");
		java_vendor=(String)m_rtProps.get("java.vendor");
		java_vm_info=(String)m_rtProps.get("java.vm.info");
		java_vm_name=(String)m_rtProps.get("java.vm.name");
		eclipse_vm=(String)m_rtProps.get("eclipse.vm");
		return "Name=" + java_runtime_name
				+ "\nVersion=" + java_runtime_version
				+ "\nVendor=" + java_vendor
				+ "\nLocation=" + java_home
				+ "\nEclipse vm=" + eclipse_vm
				+ "\nVM name=" + java_vm_name
				+ "\nVM info=" + java_vm_info;
		
	}
	/**
	 * Returns the Eclipse command line string used to lauch the SUT version of Eclipse.
	 * <p>
	 * Valid only for Eclipse 3.x
	 * </p>
	 * @return the commandline string used to launcg Eclipse
	 * 
	 */
	public static String getLaunchCommand(){
		setDomainTestObject();
		return (String)m_domainTO.getProperty("eclipse.commands");
	}
	/**
	 * Returns the version of Eclipse platform launched.
	 * @return the version string of the AUT
	 */
	public static String getEclipseVersionInfo(){
		if(m_rtProps.size()==0){
			m_rtProps=getStandardProperties();		
		}
		//setDomainTestObject();
		String ver=(String)m_rtProps.get("osgi.framework.version");
		if(ver==null){
			TestObject pluginRegTO=getPluginRegistry();
			TestObject pluginDesc=getPluginDescriptor("org.eclipse.platform");
			ver=Eclipse_PluginDescriptor.getPluginVersionString(pluginDesc);
		}
		
		return "Version=" + ver;
		      
	}
	/**
	 * Returns the Plugin information associated with a plugin.
	 * <p>
	 * The format returned is :
	 *  <code>
	 *  Plugin=<pluginName>
	 *  Vendor=<pluginVendor>
	 *  Version=<pluginVersion>
	 *  </code>
	 * Example:
	 * <i>
	 * 		Plugin=ClearCase Remote Client
	 * 		Vendor=IBM Rational software
	 * 		Version=com.ibm.rational.clearcase_0.1.11
	 * </i>
	 * </p>
	 * @param id the plugin id that uniquely identifies the plugin
	 * @return the plugin info 
	 */
	public static String getPluginVersionInfo(String id){
		setDomainTestObject();
		TestObject pluginDescTO=getPluginDescriptor(id);
		if(pluginDescTO !=null){
			String pluginName=Eclipse_PluginDescriptor.getLabel(pluginDescTO);
			String pluginVendor=Eclipse_PluginDescriptor.getProviderName(pluginDescTO);
			String pluginVersion=Eclipse_PluginDescriptor.getPluginVersionString(pluginDescTO);
			return "Name=" + pluginName
				  	+ "\nVendor=" + pluginVendor
				  	+ "\nVersion=" + pluginVersion;
		}
		return null;
	}
	/**
	 * Returns the Workbench object for this instance of Eclipse.
	 * @return the TestObject associated with the workbench of AUT	 
	 */
	public static TestObject getWorkbench(){
		setDomainTestObject();
		return (TestObject)m_domainTO.invokeStaticMethod("org.eclipse.ui.PlatformUI","getWorkbench");
	}
	/**
	 * Returns a Map of all standard properties of the Eclipse AUT Test Domain. 
	 * @return a map of all standard properties of Eclipse AUT
	 */
	public static Map getStandardProperties(){
		setDomainTestObject();	
		return m_domainTO.getStandardProperties();
	}
	/**
	 * Returns a Map of all properties of the Eclipse AUT Test Domain.
	 * @return a map of all properties of Eclipse AUT
	 */
	public static Map getAllProperties(){
		setDomainTestObject();
		return m_domainTO.getProperties();
	}
}