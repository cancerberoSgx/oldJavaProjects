package org.sgx.swing.gui.editors.file;

import java.io.File;

public interface FileAction {
	public String getActionName();
	public void executeAction(File f);
}
