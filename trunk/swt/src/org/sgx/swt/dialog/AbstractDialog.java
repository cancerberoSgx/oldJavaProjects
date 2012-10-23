package org.sgx.swt.dialog;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Shell;

public abstract class AbstractDialog implements Dialog{
	List<DialogListener> listeners = new LinkedList<DialogListener>();
	public void addDialogListener(DialogListener l) {
		listeners.add(l);
	}
	public void removeDialogListener(DialogListener l){
		listeners.remove(l);
	}
	public void notifyListeners(Dialog d, int event) {
		for (Iterator<DialogListener> iterator = listeners.iterator(); iterator.hasNext();) {
			DialogListener l =  iterator.next();
			l.notifyDialogEvent(d, event);
		}
	}
//	public abstract Shell getShell();
//
//	public abstract void open(Shell parent);

}
