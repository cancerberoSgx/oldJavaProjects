package org.sgx.swt.editor.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.basic.BooleanEditor;
import org.sgx.swt.editor.basic.StringEditor1;

public class BooleanEditor1Test {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		Shell shell = SWTUtils.getShell();
		BooleanEditor ed = new BooleanEditor(shell, SWT.NONE);
		ed.setValue(true);
		SWTUtils.showShell(shell);	
	}

}
