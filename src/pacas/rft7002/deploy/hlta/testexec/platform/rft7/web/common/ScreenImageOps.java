/*
 * Created on May 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.common;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.*;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.script.RationalTestScript;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yangjia
 *
 * Capture screen
 */
public class ScreenImageOps {

	/**
	 * Capture screen
	 * @return BufferedImage's instance
	 */
	public static BufferedImage captureScreenImage()
	{
		//this method captures the whole screen when only one arguement is given
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		return doScreenImageCapture(0, 0, width, height);
	}
	public static void scrollScreen(boolean startFromHome){
		if (startFromHome)
			RationalTestScript.getScreen().inputKeys("{HOME}");
		else
			RationalTestScript.getScreen().inputKeys("{PGDN}");
	}
	/**
	 * Capture screen
	 * 
	 * @param screenCount
	 *            Specifies the number of screens to capture.
	 * @return BufferedImage's instance
	 */
	public static ArrayList<BufferedImage> captureScreenImage(int screenCount)
	{
	    ArrayList<BufferedImage> screenImageArray = new ArrayList<BufferedImage>();
	    scrollScreen(true);
	    for (int i = 0;i < screenCount;i++){
	        screenImageArray.add(ScreenImageOps.captureScreenImage());
	        scrollScreen(false);
	    }
	    return screenImageArray;
	
	}
	/**
	 * Capture screen
	 * @return BufferedImage's instance
	 */
	public static BufferedImage captureScreenImage(String fileName)
	{
		//this method captures the whole screen when only one arguement is given
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		BufferedImage img = doScreenImageCapture(0, 0, width, height);
		
		try {
			File file = new File(fileName);
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			System.err.println("Error creating image file.");
		}
		return img;
	}
	
	/**
	* Helper function to capture screen image <p>
	* @param x 		x coordinate of screen location to capture
	* @param y 		y coordinate of screen location to capture
	* @param width 	width coordinate of screen location to capture
	* @param height 	height coordinate of screen location to capture
	*/
	private static BufferedImage doScreenImageCapture (int x, int y, int width, int height)
	{
		try {
			BufferedImage capture = null;
			Rectangle area = new Rectangle(x, y, width, height);
			Robot robot = new Robot();
			capture = robot.createScreenCapture(area);
			return capture;
		}
		catch (Exception e) {
			//PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doScreen: error capturing image: " + e);
			return null;
		}
	}
	public static void main(String[] args) {
		captureScreenImage("f:/123.jpg");
	}
	
}
