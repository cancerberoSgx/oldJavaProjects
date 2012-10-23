package org.sgx.swing.gui.editors.font;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.gui.fontChooser.FontChooserKnower;
import org.sgx.swing.j2s.model.base.FontFamily;
import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.j2s.model.editor.EditorInvalidStateException;
import org.sgx.swing.utils.SwingUtils;

public class FontFamilyEditor extends SimpleBeanEditor<FontFamily>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox fontNameCombo=null;
	public FontFamilyEditor() {
		super();
		init();
	}
	private void init() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS ));
		add(getFontNameCombo());
	}
	public FontFamily getCurrentValue() {
		return new FontFamily(fontFamilyName);
	}

	public void setValue(FontFamily value) {
		getFontNameCombo().setSelectedItem(value.getFontFamily());
	}
	private String fontFamilyName;
	private JComboBox getFontNameCombo() {
		if (fontNameCombo == null) {
			fontNameCombo = new JComboBox(FontChooserKnower.fontNames);
			fontNameCombo.setPreferredSize(new Dimension(204, 12));
			fontNameCombo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					fontFamilyName = getSelectedFontName();						
				}				
			});
		}
		return fontNameCombo;
	}
	public String getSelectedFontName() {
		Object o = fontNameCombo.getSelectedItem();
		if(o instanceof String) {
			return (String)o;
		}
		else
			return "Courier";
	}
	
}
