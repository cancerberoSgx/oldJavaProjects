package org.sgx.swing.gui.fontChooser;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

import javax.swing.ListModel;

import org.sgx.utils.WindowsUtils;


/**
 * i'm hurry sorry for the statics ;)
 * @author nati
 *
 */
public class FontChooserKnower {

	public static String[] fontNames;
	public static int [] fontStyles;
	public static int [] fontSizes;
	public static String[] fontStyleNames;
	
	static {
		fontNames = WindowsUtils.fontNames;
		fontStyles = new  int []{Font.PLAIN, Font.BOLD, Font.ITALIC};
		fontStyleNames = new String[] {"Plain", "Bold", "Italic"};
		fontSizes = new  int []{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 24, 26, 28, 30, 32, 36, 40, 44, 50, 60, 80};
	}

//	public static int getFontNameIndex(String f) {
//		for (int i = 0; i < fontNames.length; i++) {
//			if(fontNames[i].toLowerCase().equals(f))
//				return i;
//		}
//		return -1;
//	}
}
