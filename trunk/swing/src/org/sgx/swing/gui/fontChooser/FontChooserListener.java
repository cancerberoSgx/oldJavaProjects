package org.sgx.swing.gui.fontChooser;

import java.awt.Font;

public interface FontChooserListener {

	public void notifyFontChoosed(Font f);
	public void notifyFontChooserCanceled();
}
