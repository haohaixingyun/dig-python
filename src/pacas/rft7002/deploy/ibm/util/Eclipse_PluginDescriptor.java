// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import com.rational.test.ft.object.interfaces.*;

/**
 * Class <code>Eclipse_PluginDescriptor</code> exposes the plug-in descriptor which contains information about a plug-in obtained from the plug-in's manifest (plugin.xml) file. 
 * <p>
 * Plug-in descriptors are platform-defined objects that exist in the plug-in registry independent of whether a plug-in has been started.
 * In contrast, a plug-in's runtime object (getPlugin) generally runs plug-in-defined code. 
 * Typically, the TestObject associated with a plugin descriptor  is obtained from the
 * static methods of <code>Eclipse_Plaform</code>.
 * 
 * Note: 
 * Most of plugin descriptor APIs exposed here will be replaced in time with the OSGI-based Eclipse Platform Runtime introduced with Eclipse 3.0.
 * The obsoleted APIs will be deprecated once the APIs for the new Eclipse Platform Runtime achieve their final and stable form (post-3.0).
 * Furthermore, these obsoleted APIs is only functional if the org.eclipse.core.runtime.compatibility plug-in is present (and resolved) in the system.
 * </p>
 * @author bklau
 * @version 2.2
 * @since 2004
 * 
 */
public class Eclipse_PluginDescriptor {
	private static TestObject m_pluginDescTO=null;
	/**
	 * 
	 */
	private static void setDesc(TestObject descTO){
		m_pluginDescTO=descTO;
	}
	/**
	 * Returns the company or provider name that supply this plugin.
	 * @param descTO TestObject associated with this plugin descriptor
	 * @return a company or provider label for this plugin
	 */
	public static String getProviderName(TestObject descTO){
		setDesc(descTO);
		return (String)m_pluginDescTO.invoke("getProviderName");
	}
	/**
	 * Returns the product/label name for this plugin.
	 * @param descTO TestObject associated with this plugin descriptor
	 * @return a label string for this plugin
	 */
	public static String getLabel(TestObject descTO){
		setDesc(descTO);
		return (String)m_pluginDescTO.invoke("getLabel");
	}
	/**
	 * Returns the version identifier object for this plugin.
	 * @param descTO TestObject associated with this plugin descriptor.
	 * @return a TestObject associated with <code>PluginVersionIdentifier</code> for this plugin.
	 */
	public static TestObject getVersionIdentifier(TestObject descTO){
		setDesc(descTO);
		return (TestObject)m_pluginDescTO.invoke("getVersionIdentifier");
	}
	/**
	 * Returns the unique id or this plugin.
	 * @param descTO TestObject associated with this plugin descriptor
	 * @return a unique identifier strring for this plugin.
	 */
	public static String getUniqueIdentifier(TestObject descTO){
		setDesc(descTO);
		return (String)m_pluginDescTO.invoke("getUniqueIdentifier");
	}
	/**
	 * Returns the version string for this plugin.
	 * @param descTO TestObject associated with this plugin
	 * @return a version string of the <code>PluginVersionIdentifier</code>
	 */
	public static String getPluginVersionString(TestObject descTO){
		TestObject verIdentifier=getVersionIdentifier(descTO);
		return (String)m_pluginDescTO.invoke("getVersion");
		
	}
}
