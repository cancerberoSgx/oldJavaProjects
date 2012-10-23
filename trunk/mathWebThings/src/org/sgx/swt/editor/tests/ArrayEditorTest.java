package org.sgx.swt.editor.tests;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.editor.Editor;
import org.sgx.j2s.utils.Utils;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.SWTEditorFactory;
import org.sgx.swt.editor.basic.ArrayEditor;
import org.sgx.swt.editor.basic.ArrayWrapper;

public class ArrayEditorTest {

	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test2() {
		Map<Object, Object> m = Utils.toMap(new Object[][]{
				{"key1", new Integer(2)},
				{"key2", "hello"}
		});
		Object[][] a1 = new Object[][]{{"00", 11, "another"}, {0.123, m, "hello"}};
		Shell shell = new Shell(Display.getDefault());
		shell.setText("2 d array with complex object as item");
		shell.setLayout(new FillLayout());
		
		Editor ed = SWTEditorFactory.getInstance().getEditorForObject(new ArrayWrapper(a1, 2), shell, SWT.BORDER);
		if(ed==null)
			System.out.println("ArrayEditorTest ed==null");
		SWTUtils.showShell(shell);
		
	}

	private static void test1() {
		String[][] s2 = new String[][]{{"00","01", "02" },{"10", "11", "12"}};
		ArrayWrapper w2 = new ArrayWrapper(s2,2);
		Shell shell = new Shell(Display.getDefault());
		shell.setText("simple 2d array editor");
		shell.setLayout(new FillLayout());
		
		ArrayEditor ed2 = new ArrayEditor(shell, SWT.NONE) ;
		ed2.setValue(w2);
		SWTUtils.showShell(shell);
	}

}
