package org.sgx.swing.gui.editors.file;

import java.io.File;

public interface FileSelectorListener {

	public void notifyFileSelection(File[] selectedFiles);

//	public void notifyFileSelection(File selectedFile);

}
