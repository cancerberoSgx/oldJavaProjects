package org.sgx.swt.editor.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.j2s.widget.base.ListSelection;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.basic.ListSelectionEditor;

public class ListSelectionEditorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testMultiple();
		testSingle();
	}

	private static void testSingle() {
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(new FillLayout());
		ListSelection<String> list = new ListSelection<String>(new String[]{"one", "two", "three"}, new String[]{"two"}, false);
		final ListSelectionEditor ed = new ListSelectionEditor(shell, SWT.NONE);
		ed.setValue(list);
		shell.addShellListener(new ShellAdapter() {

			@Override
			public void shellIconified(ShellEvent e) {
				super.shellIconified(e);
				System.out.println(ed.getValue());				
			}
			
		});
		SWTUtils.showShell(shell);
	}

	private static void testMultiple() {
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(new FillLayout());
		ListSelection<String> list = new ListSelection<String>(new String[]{"one", "two", "three"}, new String[]{"one", "two"}, true);
		ListSelectionEditor ed = new ListSelectionEditor(shell, SWT.NONE);
		ed.setValue(list);
		SWTUtils.showShell(shell);
	}

}

