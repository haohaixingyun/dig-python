// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package ibm.util;

import ibm.loggers.control.PackageLoggingController;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.rational.test.ft.object.interfaces.TestObject;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Description: Performs bitmap operations
 * 
 * @author Tony Venditti 
 * @version 2.2
 * Last Modified: 06/09/04
 */
public class BitmapOps {

	//********************************************************************************************************
	/**
	* Captures entire screen (desktop) image and writes it to the specified file as a jpeg <p>
	* @param filename 	path and filename of file to write image out to
	* @author Tony Venditti
	*/
	//******************************************************************************************************
	public static void captureScreen(String filename)
	{
		//this method captures the whole screen when only one arguement is given
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		doScreenCapture(filename, 0, 0, width, height);
	}

	//******************************************************************************************************
	/**
	* Captures individual test object image and writes it to the specified file as a jpeg <p>
	* @param filename 	path and filename of file to write image out to
	* @param to 		TestObject to capture image of
	* @author Ann Kopren
	*/
	//******************************************************************************************************
	public static void captureScreen(String filename, TestObject to)
	{
		//this method captures an individual TestObject (e.g. browser, div, table, etc.)
		Rectangle r = null;
		//html
		if (to.getProperties().containsKey(".bounds"))
			r = (Rectangle)to.getProperty(".bounds");
		//swing
		else if (to.getProperties().containsKey("bounds"))
		{
			r = (Rectangle)to.getProperty("bounds");
			
//@KAE - ADDED/MODIFIED THE FOLLOWING 5 LINES			
			java.awt.Point point = null;
			if (to.getProperties().containsKey("location"))  //swt
				point = (Point)to.getProperty("location");
			else
				point = (Point)to.getProperty("locationOnScreen");
//@KAE - END OF ADDITIONAL/MODIFICATIONS

			if (point != null)
				r.setLocation(point);
		}
		else
		{
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#captureScreen: could not capture test object");
			return;
		}
		doScreenCapture(filename, r.x, r.y, r.width, r.height);
	}
	
	//******************************************************************************************************
	/**
	* Captures individual test object image and writes it to the specified file as a jpeg <p>
	* @param filename 	path and filename of file to write image out to
	* @param x 		x coordinate of screen location to capture
	* @param y 		y coordinate of screen location to capture
	* @param width 	width coordinate of screen location to capture
	* @param height 	height coordinate of screen location to capture
	* @author Tony Venditti
	*/
	//******************************************************************************************************
	public static void captureScreen(String filename, int x, int y, int width, int height)
	{
		doScreenCapture (filename, x, y, width, height);
	}


	//******************************************************************************************************
	/**
	* Helper function to capture screen image <p>
	* @param filename 	path and filename of file to write image out to
	* @param x 		x coordinate of screen location to capture
	* @param y 		y coordinate of screen location to capture
	* @param width 	width coordinate of screen location to capture
	* @param height 	height coordinate of screen location to capture
	* @author Tony Venditti
	*/
	//******************************************************************************************************
	protected static void doScreenCapture (String filename, int x, int y, int width, int height)
	{
		//screen capture
		try {
			BufferedImage capture = null;
			Rectangle area = new Rectangle(x, y, width, height);
			Robot robot = new Robot();
			capture = robot.createScreenCapture(area);
			FileOutputStream out =
			new FileOutputStream(filename);
			JPEGImageEncoder encoder =
			JPEGCodec.createJPEGEncoder(out);
			encoder.encode(capture);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doScreen: error capturing image: " + e);
		}
	}


	//******************************************************************************************************
	/**
	* Compares two images a pixel at a time
	* @param expectedImage 	path and filename of file to compare to
	* @param actualImage 		path and filename of the captured image
	* @return true if the images are identical, false if not
	* @author Ann Kopren
	*/
	//******************************************************************************************************

	public static boolean compareImages(String expectedImage, String actualImage)
	{
		BufferedImage expected = null, actual = null;
		try
		{
			FileInputStream in = new FileInputStream(expectedImage);
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
			expected = decoder.decodeAsBufferedImage();
			in.close();

			in = new FileInputStream(actualImage);
			decoder = JPEGCodec.createJPEGDecoder(in);
			actual = decoder.decodeAsBufferedImage();
			in.close();
		}
		catch (Exception e) {
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#compareImages: error reading images: " + e);
			return false;
		}

		if (expected == null || actual == null)
			return false;
		if (expected.getHeight() != actual.getHeight() || expected.getWidth() != actual.getWidth())
			return false;

		for (int y = 0; y < expected.getHeight(); ++y)
		{
			for (int x = 0; x < expected.getWidth(); ++x)
			{
				if (expected.getRGB(x, y) != actual.getRGB(x, y))
					return false;
			}
		}

		return true;
	}

	//******************************************************************************************************
	/**
	* Creates a difference image by subtracting the actual image from the expected,
	* with the result being all black if no differences are found, input images must
	* be the same size, intended for human debugging
	* @param expectedImage 	path and filename of file to compare to
	* @param actualImage 		path and filename of the captured image
	* @param diffImage 		path and filename to the image difference
	* @author Ann Kopren
	*/
	//******************************************************************************************************


	public static void doImageDiff(String expectedImage, String actualImage, String diffImage)
	{
		BufferedImage expected = null, actual = null;
		try
		{
			FileInputStream in = new FileInputStream(expectedImage);
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
			expected = decoder.decodeAsBufferedImage();
			in.close();

			in = new FileInputStream(actualImage);
			decoder = JPEGCodec.createJPEGDecoder(in);
			actual = decoder.decodeAsBufferedImage();
			in.close();
		}
		catch (Exception e) {
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doImageDiff: error reading images: " + e);
			return;
		}

		try {
			BufferedImage difference = new BufferedImage(expected.getWidth(), expected.getHeight(), expected.getType());

			if (expected == null || actual == null)
			{
				PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doImageDiff: Expected image or actual image is null");
				return;
			}
			if (expected.getHeight() != actual.getHeight() || expected.getWidth() != actual.getWidth())
			{
				PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doImageDiff: Images are not the same size");
				return;
			}

			for (int y = 0; y < expected.getHeight(); ++y)
			{
				for (int x = 0; x < expected.getWidth(); ++x)
				{
					int rgb = expected.getRGB(x, y) - actual.getRGB(x, y);
					difference.setRGB(x, y, rgb);
				}
			}

			FileOutputStream out = new FileOutputStream(diffImage);
			JPEGImageEncoder encoder =
			JPEGCodec.createJPEGEncoder(out);
			encoder.encode(difference);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BitmapOps#doImageDiff: error writing image: " + e);
		}
	}






}
