package org.sgx.swt.editor.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.basic.StringEditor1;

public class StringEditor1Test {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		Shell shell = SWTUtils.getShell();
		StringEditor1 ed = new StringEditor1(shell, SWT.NONE);
		SWTUtils.showShell(shell);	
	}

}
