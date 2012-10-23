package org.sgx.swing.gui.fontChooser;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


import java.awt.GridBagConstraints;
import javax.swing.JLabel;

import org.sgx.utils.Utils;

import java.awt.Dimension;
/**
 * 
 * @author sgurin
 *
 */
public class CopyOfFontPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JComboBox fontNameCombo = null;
	private JComboBox fontSizeCombo = null;
	private JList fontStyleList = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel text = null;
	private Font selectedFont;  //  @jve:decl-index=0:

	public CopyOfFontPanel1() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getJPanel(), null);
		this.add(getJPanel3(), null);
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
//			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.setLayout(new FlowLayout());
			jPanel.setPreferredSize(new Dimension(276, 80));
			jPanel.add(getJPanel2(), null);
			jPanel.add(getFontNameCombo(), null);
			jPanel.add(getFontSizeCombo(), null);
		}
		return jPanel;
	}

	private JComboBox getFontNameCombo() {
		if (fontNameCombo == null) {
			fontNameCombo = new JComboBox(FontChooserKnower.fontNames);
			fontNameCombo.setPreferredSize(new Dimension(204, 12));
			fontNameCombo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					updateSampleText();				
				}				
			});
		}
		return fontNameCombo;
	}
	private void updateSampleText() {
		selectedFont = new Font(getSelectedFontName(), getSelectedFontStyle(), getSelectedFontSize() );
		text.setFont(selectedFont);
	}
	private JComboBox getFontSizeCombo() {
		if (fontSizeCombo == null) {
			fontSizeCombo = new JComboBox(
					Utils.intToString(FontChooserKnower.fontSizes));
			fontSizeCombo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateSampleText();		
				}
			});
		}
		return fontSizeCombo;
	}

	private JList getFontStyleList() {
		if (fontStyleList == null) {
			fontStyleList = new JList(FontChooserKnower.fontStyleNames);
			fontStyleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		return fontStyleList;
	}
	
	public String getSelectedFontName() {
		Object o = fontNameCombo.getSelectedItem();
		if(o instanceof String) {
			return (String)o;
		}
		else
			return "Courier";
	}
	public int getSelectedFontStyle() {
		return Font.PLAIN;
	}
	public Font getSelectedFont() {
		return new Font(getSelectedFontName(), getSelectedFontStyle(), getSelectedFontSize() );
	}
	public int getSelectedFontSize() {
		Object o = fontSizeCombo.getSelectedItem();
		if(o instanceof String) {
			return Integer.parseInt((String)o);
		}
		else
			return 12;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.weightx = 1.0;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(getFontStyleList(), gridBagConstraints);
		}
		return jPanel2;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			text = new JLabel();
			text.setText("Ella tiene, una forma de hacerme creer...");
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.setPreferredSize(new Dimension(222, 80));
			jPanel3.add(text, new GridBagConstraints());
		}
		return jPanel3;
	}

	public static void main(String [] a) {
		JFrame f = new JFrame();
		CopyOfFontPanel1 p = new CopyOfFontPanel1();
		f.getContentPane().add(p);
		f.setVisible(true);
	}
}
