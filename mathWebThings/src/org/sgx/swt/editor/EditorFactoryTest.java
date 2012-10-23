package org.sgx.swt.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.SWTUtils;

public class EditorFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shell shell = new Shell(Display.getCurrent());
		shell.setLayout(new RowLayout());
		
		Integer i = new Integer(9);
		String className = String.class.getName();
		
		String[]classNames={String.class.getName(), Integer.class.getName()};
		
		Composite c = (Composite) SWTEditorFactory.getInstance().
			getEditorFor(className, shell, SWT.NONE);
		if(c==null)
			System.out.println("none editor registered for "+className);
		SWTUtils.showShell(shell);
	
	}

}
