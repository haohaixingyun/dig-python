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
 * Description: Performs operations on Eclipse
 * 
 * @author endres
 * 
 * @version 2.2
 * Last Modified: 08/05/04
 */
public class EclipseOps {
		
	/**
	* Loads a class in the target Eclipse application and returns a TestObject that references it.
	* To access the class, call getObjectReference().getTarget() against the TestObject that is returned.
	* To create an instance of the class, subsequently call invoke("newInstance") against the TestObject that is returned.<p>
	* 
	* NOTE: This class doesn't work for Eclipse 3.0.
	* 
	* @param topObj 		a TopLevelTestObject such as the one that represents the workbench shell/frame
	* @param pluginName	the name of the plugin that contains the class that is to be loaded.  This plugin must exist in the target Eclipse application.
	* @param className		the fully qualified name of the class that needs to be loaded.
	* @return TestObject 
	* @author Jason Sholl/Kathy Endres
	* */	
	public static TestObject loadClass(TopLevelTestObject topObj, String pluginName, String className) {
		TestObject clazz = (TestObject) topObj.invoke("getClass");
				
		TestObject classLoader = (TestObject) clazz.invoke("getClassLoader");
		clazz.unregister();
		
		TestObject generalDescriptor = (TestObject)classLoader.invoke("getPluginDescriptor");
		classLoader.unregister();
				
		TestObject pluginRegistry = (TestObject)generalDescriptor.invoke("getPluginRegistry");
		generalDescriptor.unregister();
		
		Object [] methodArgs = new Object[1];
		methodArgs[0] = pluginName;
		String methodSignature = "(Ljava/lang/String;)Lorg/eclipse/core/runtime/IPluginDescriptor;";
		TestObject pluginDescriptor = (TestObject)pluginRegistry.invoke("getPluginDescriptor", methodSignature, methodArgs);
		pluginRegistry.unregister();
		
		TestObject pluginClassLoader = (TestObject)pluginDescriptor.invoke("getPluginClassLoader");
		pluginDescriptor.unregister();
		
		methodArgs = new Object[1];
		methodArgs[0] = className;
		methodSignature = "(Ljava/lang/String;)Ljava/lang/Class;";
		TestObject loadedClassObj = (TestObject)pluginClassLoader.invoke("loadClass", methodSignature, methodArgs);
		pluginClassLoader.unregister();
		
		return loadedClassObj;
	}

}
