package org.sgx.swt.dialog;

import org.eclipse.swt.widgets.Shell;

public interface Dialog {
void addDialogListener(DialogListener l);
void removeDialogListener(DialogListener l);
void open();
Shell getShell();
}
