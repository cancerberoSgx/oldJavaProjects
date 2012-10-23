package org.sgx.jmencoder.options;

import org.sgx.swing.gui.editors.beans.BeanEditorTest;
import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.utils.SwingUtils;

public class OptionBeanEditorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubtitleOption subtitleOpt = new SubtitleOption();
		final SimpleBeanEditor f = new SimpleBeanEditor(subtitleOpt, true);
		SwingUtils.showInFrame(f);
	}

}
