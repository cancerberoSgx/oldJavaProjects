package org.sgx.swt.dialog;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractDialog implements Dialog{
	public Shell shell;
	List<DialogListener> listeners = new LinkedList<DialogListener>();
	public AbstractDialog(Shell parentShell) {
		this.shell= new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		
	}
	public void addDialogListener(DialogListener l) {
		listeners.add(l);
	}
	public void removeDialogListener(DialogListener l){
		listeners.remove(l);
	}
	public void notifyListeners(Dialog d, int event) {
		for (Iterator<DialogListener> iterator = listeners.iterator(); iterator.hasNext();) {
			DialogListener l =  iterator.next();
			l.notifyDialogEvent(AbstractDialog.this, event);
		}
	}
	public Shell getShell() {
		return shell;
	}

	public void open() {
		shell.open();
	}

	public void setDialogTitle(String title) {
		shell.setText(title);
	}

	public void setLocation(int x, int y) {
		shell.setLocation(x,y);
	}

	public void setSize(int i, int j) {
		shell.setSize(i,j);
	}
	
	

}
