package org.sgx.utils.tests;

import org.sgx.utils.WindowsUtils;

public class WindowsUtilsTest {

	public static void main(String[] args) {
		System.out.println(WindowsUtils.getSystemFontPathWin() +
				" - "+ WindowsUtils.fontsFiles.keySet().size()+" fonts found "+
				WindowsUtils.fontsFiles.keySet());
	}

}
