package org.sgx.swing.utils;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author sgurin
 * 
 */
public class ImageUtils {
	public static final String FORMAT_PNG = "png";

	private static final int DEFAULT_MOUSE_POINTER_LENGTH = 20;

	private static ImageUtils instance;

	private Robot currentRobot=null;
	
	private ImageUtils() {
		
	}

	public static ImageUtils getInstance() {
		if (null == instance) {
			instance = new ImageUtils();
		}
		return instance;
	}
	
	public  boolean write(BufferedImage img, String format, File out)
			throws IOException {

		return ImageIO.write(img, format, out);
	}

	public  boolean writeToPNG(BufferedImage img, File out)
			throws IOException {
		return write(img, "png", out);
	}

	public  BufferedImage takeAWTPictureOf(JComponent comp, Dimension dim) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		f.getContentPane().add(comp);
		f.pack();
		comp.setOpaque(true);
		comp.setSize(dim.width, dim.height);
		BufferedImage image = new BufferedImage(comp.getWidth(), comp
				.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setClip(comp.getBounds());
		comp.paint(g2d);
		g2d.dispose();
		return image;
	}

	public  Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	 
	public  Robot getCurrentRobot() throws AWTException {
		if(currentRobot==null)
			currentRobot = new Robot();
		return currentRobot;
	}

//	public Color getPixelColorOf(MouseEvent mouseEvent) {
//		Point relLoc = new Point(mouseEvent.getX(), mouseEvent.getY()),
//			absLoc = SwingUtilities.convertPointToScreen(relLoc, (Component) mouseEvent.getSource());
//		mouseEvent.getX()
//		Point screenCoords = 
//	}
//	public Color getPixelColorOf(Point screenCoord) {
//	Robot robot = getCurrentRobot();
//	
//	return robot.getPixelColor(1, 2);
//	}
	
	public void takeDesktopScreenshot(Rectangle captureArea, String outFormat, File out) throws AWTException, IOException{
		BufferedImage buffy = takeDesktopScreenshot(captureArea);
		write(buffy, outFormat, out);
	}
	public void takeDesktopScreenshotAndDrawMousePointer(Rectangle captureArea, String outFormat, File out) throws AWTException, IOException{
		BufferedImage buffy = takeDesktopScreenshotAndDrawMousePointer(captureArea, outFormat);
		write(buffy, outFormat, out);
	}
	public BufferedImage takeDesktopScreenshotAndDrawMousePointer(Rectangle captureArea, String outFormat) throws AWTException {
		return takeDesktopScreenshotAndDrawMousePointer(captureArea);
	}
	public BufferedImage takeDesktopScreenshotAndDrawMousePointer(Rectangle captureArea) throws AWTException {
		   Toolkit toolkit = Toolkit.getDefaultToolkit();
		    Dimension screensize = toolkit.getScreenSize();
		    Robot robot = new Robot();
		    BufferedImage screenshot =
		      robot.createScreenCapture(
		    		  captureArea);
		    Cursor cursor = Cursor.getDefaultCursor();
		    Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		  //  double scale = 2d/1d;
		    BufferedImage smallScreen = new BufferedImage(captureArea.width,
		    		captureArea.height, BufferedImage.TYPE_INT_RGB
		      );
		    Graphics g = smallScreen.createGraphics();
		    g.drawImage(screenshot, 0, 0, captureArea.width,
		    		captureArea.height, null   );
		    		    
		    int x = (int)(mouseLocation.getX());
		    int y = (int)(mouseLocation.getY());
		    _drawMousePointer(g, x-captureArea.x, y-captureArea.y, //translate to relative position
		    		DEFAULT_MOUSE_POINTER_LENGTH);
		    
		    g.finalize();
		    g.dispose();
		    return smallScreen;
		
	}
	
	private void _drawLine(Graphics g, int[]p1, int[]p2) {
		  g.drawLine( p1[0], p1[1], p2[0], p2[1]);	
	}
	public void _drawPolygon(Graphics g, int[][] points, Color fg, Color bg) {
		int []X = new int[points.length], Y = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			X[i]=points[i][0];
			Y[i] = points[i][1];
		}
		g.setColor(bg);
		g.fillPolygon(X, Y, points.length);
		g.setColor(fg);
		g.drawPolygon(X, Y, points.length);
	}
	private void _drawMousePointer(Graphics g, int x, int y, int l) {
	  
	    //TODO: draw a better pointer
	    g.setColor( new Color(255,0,127, 127) );
	    int[] p1 = new int[]{x,y},  //top corner
	    	p2 = new int[]{p1[0], p1[1]+l},//left bottom corner 
	    	p3 =  new int[]{p1[0]+l/2, p1[1]+l*3/4} //right bottom corner
	    	; 
	    
	    _drawPolygon(g, new int[][]{p1,p2,p3}, Color.BLACK, Color.YELLOW);
//	    _drawLine(g, p1,p2);
//	    _drawLine(g, p1,p3);
//	    _drawLine(g, p2,p3);
	    
//	    g.drawLine( x-l, y, x+l, y );
//	    g.drawLine( x, y-l, x, y+l );	
	    
	}

	public  BufferedImage takeDesktopScreenshot(Rectangle captureSize)
			throws AWTException {
		Robot robot = getCurrentRobot();
		
		BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		return bufferedImage;

	}
}
