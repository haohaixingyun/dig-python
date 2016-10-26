// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.loggers.targets;

import ibm.loggers.control.PackageLoggingController;
import ibm.util.FileOps;
import java.io.File;
import java.io.IOException;

/**
 * Description: this class is used to tell a logger to log to a specific file<p>
 * 
 * @author TSnow
 *
 * @version 2.2
 * Last Modified: 04/06/04
 */
public class FileTarget implements ILogTarget {
	
	File gFile = null;

//***********************Constructors***********************	
	/**
	 * Constructor which sets this target to log to a particular file.<p>
	 * If the file or directory entered doesn't exist, the constructor will create them.
	 *
	 * @param sFileNameAndPath the filename and complete absolute directory path (e.g. c:\temp\log.txt, not \\temp\log.txt)
	 */	
	public FileTarget(String sFileNameAndPath)
	{
		setFile(sFileNameAndPath);
	}
	
//********************Setters and Getters************************

	/**
	 * Sets the class to direct output to a particular file.<p>
	 * If the file or directory entered doesn't exist, the constructor will create them.
	 * @param sFileNameAndPath the filename and complete absolute directory path (e.g. c:\temp\log.txt, not \\temp\log.txt)
	*/
	public void setFile(String sFileNameAndPath)
	{
		gFile = new File(sFileNameAndPath);
	
		try {
			File dir = gFile.getParentFile();
			if (!dir.exists())
			{
				dir.mkdirs();
			}	
			gFile.createNewFile();
		} catch(IOException e) {
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in LogTargets#setFile: couldn't create File: "+ sFileNameAndPath);
			gFile = null;
		}

	}

	/**
	 * Gets the file this class is pointing to.<p>
	 * 
	 * @return the file this class is pointing to
	 */	
	public File getFile()
	{
		return gFile;
	}
	
	/**
	 * Deletes all content in the log file which this class points to<p>
	 * WARNING: this will actually delete the file on the physical harddrive, and then recreate it.<br>
	 */	
	public void emptyFile()
	{
		if (gFile!=null)
		{
			gFile.delete();
			setFile(gFile.getAbsolutePath());
		}
			
	}
	
//**************************************************************
	
	
	
	/** 	
	* Logs an informational message <br>
	* @param sInfoMessage the text of the message to log
	*/	
	public void logInfo(String sInfoMessage)
	{
		if (gFile!=null)
			FileOps.appendStringToFile(gFile.getAbsolutePath(), sInfoMessage);
	}

	/** 	
	* Logs a warning message <br>
	* @param sWarningMessage the text of the warning message to log
	*/	
	public void logWarning(String sWarningMessage)
	{
		if (gFile!=null)
			FileOps.appendStringToFile(gFile.getAbsolutePath(), sWarningMessage);	
	}

	/** 	
	* Logs an error message 
	* @param sErrorMessage the text of the error message to log
	*/	
	public void logError(String sErrorMessage)
	{
		if (gFile!=null)
			FileOps.appendStringToFile(gFile.getAbsolutePath(), sErrorMessage);
	}
	
	/**
	 * Determines whether two targets are equivalent.<p>
	 * This is used to prevent a logger from logging twice to the exact same output target,<br>
	 * which would result in the exact same message appearing twice in the log.
	 * 
	 * @param obj the object to compare with this object
	 * @return true if the the object passed in is the same as this object; false otherwise 
	 */
	public boolean equals(Object obj) {
		//check to see if both objects point to the same file,
		//if so, then they are equivalent
		if (obj instanceof FileTarget)
			if (((FileTarget)obj).getFile().equals(this.getFile()))
				return true;
		
		return false;
	}
}


