package org.sgx.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sun.font.FontManager;

public class WindowsUtils {
	/**
	 * total dirty jhardcoding !!! ;)
	 * TODO: standarly get windows font directory  
	 * @return windows font directory  
	 */
	public static String getSystemFontPathWin() {
		String s = System.getProperty("user.home");
		return s.substring(0,1)+":\\windows\\fonts";
	}
	public static String getSystemFontPath() {
		if(File.separator.equals("/"))
			return getSystemFontPathLinux();
		else  //windows
			return getSystemFontPathWin();
		
	}

	private static String getSystemFontPathLinux() {
		return "/usr/share/fonts/truetype";
	}
	public static Map<String, Font> fontsFiles=new HashMap<String, Font>();
	public static String[] fontNames;
	public static Map<String, String> fontNameFile=new HashMap<String, String>();
	 
	static{
		File fdir = new File(getSystemFontPath());
		if(!fdir.exists()||!fdir.isDirectory()){
			throw new RuntimeException("pelotas rojas");
		}
		File[] ffs = fdir.listFiles();
		for(int i=0;i<ffs.length; i++) {
			if(ffs[i].getName().toLowerCase().endsWith("ttf")) {
				try {
					Font font = Font.createFont(Font.TRUETYPE_FONT, ffs[i]);
					if(font!=null)
						fontsFiles.put(ffs[i].getAbsolutePath(), font);
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Set<String> ks = fontsFiles.keySet();
		fontNames = new String[ks.size()];
		int i=0;
		for(String k : ks) {
			fontNames[i]=WindowsUtils.fontsFiles.get(k).getName();
			fontNameFile.put(WindowsUtils.fontsFiles.get(k).getName(), k);	
			i++;
		}
		Arrays.sort(fontNames, new Comparator<String>() {
			public int compare(String o1, String o2) {
				if(o1==null ||o2==null)
					return 0;
				else
					return o1.compareTo(o2);
			}				
		});
	}


}
