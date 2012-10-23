package org.sgx.swing.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImageUtilsTest {

	public static void main(String[] args) {

		// testDesktopScreenshot();
		try {
//			testDesktopScreenshotWithMouse();
			takeDesktopScreenshotAndDrawMousePointerTest();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void takeDesktopScreenshotAndDrawMousePointerTest()
			throws AWTException, IOException {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle area = new Rectangle(0, 0, (int) screensize.getWidth(),
				(int) screensize.getHeight());
		ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(area,
				"png", new File("/home/sebastian/borrar/screenshot1.png"));

	}

	private static void testDesktopScreenshotWithMouse() throws AWTException {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		Robot robot = new Robot();
		BufferedImage screenshot = robot.createScreenCapture(new Rectangle(0,
				0, (int) screensize.getWidth(), (int) screensize.getHeight()));
		Cursor cursor = Cursor.getDefaultCursor();
		Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		double scale = 2d / 1d;
		BufferedImage smallScreen = new BufferedImage((int) (screensize
				.getWidth() / scale), (int) (screensize.getHeight() / scale),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = smallScreen.createGraphics();
		g.drawImage(screenshot, 0, 0, smallScreen.getWidth(), smallScreen
				.getHeight(), null);
		int x = (int) (mouseLocation.getX() / scale);
		int y = (int) (mouseLocation.getY() / scale);
		int length = 20;

		g.setColor(new Color(255, 0, 127, 127));
		g.drawLine(x - length, y, x + length, y);
		g.drawLine(x, y - length, x, y + length);

		g.setColor(new Color(255, 125, 128));
		String text = " draw mouse pointer at red cross-hairs";
		g.drawString(text, x, y);

		g.setColor(new Color(0, 255, 0));
		g.drawString(text, x + 1, y + 1);

		ImageIcon imageIcon = new ImageIcon(smallScreen);
		JOptionPane.showMessageDialog(null, imageIcon);

	}

	private static void testDesktopScreenshot() {
		try {
			ImageUtils.getInstance().takeDesktopScreenshot(
					new Rectangle(10, 10, 600, 600), ImageUtils.FORMAT_PNG,
					new File("probe.png"));
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
