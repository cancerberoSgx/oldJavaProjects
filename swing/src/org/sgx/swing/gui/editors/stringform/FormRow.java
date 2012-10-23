package org.sgx.swing.gui.editors.stringform;

import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormRow extends JPanel {

	private static final long serialVersionUID = 1L;
String label, valueStr;
private JLabel jLabel = null;
boolean editable = false;
private JLabel separator = null;
private JComponent valueEntry = null;
private JPanel jPanel = null;
public boolean isEditable() {
	return editable;
}

public void setEditable(boolean editable) {
	this.editable = editable;
}

	public FormRow(String label, String value, boolean editable2) {
	super();
	this.label = label;
	this.valueStr = value;
	this.editable=editable2;
	initialize();
}

	public String getLabel() {
	return label;
}

public void setLabel(String label) {
	this.label = label;
}

public String getValue() {
	return valueStr;
}

public void setValue(String value) {
	this.valueStr = value;
}

	/**
	 * This is the default constructor
	 */
	public FormRow() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		if(!editable) {
			valueEntry = new JLabel();
			((JLabel)valueEntry).setText(valueStr);
		}
		else {
			valueEntry = new JTextField();
			((JTextField)valueEntry).setText(valueStr);
		}
		separator = new JLabel();
		separator.setText("   ");
		jLabel = new JLabel();
		jLabel.setText(label);
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(jLabel, null);
		this.add(separator, null);
		this.add(getJPanel(), null);
		this.add(valueEntry, null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

}
