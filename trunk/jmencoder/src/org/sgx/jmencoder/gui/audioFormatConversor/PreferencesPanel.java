package org.sgx.jmencoder.gui.audioFormatConversor;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JCheckBox;

public class PreferencesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JCheckBox autoRename = null;

	public PreferencesPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(414, 200);
		this.add(getJPanel(), null);
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Automatically rename non ISO-8859-1 filenames ");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(jLabel, null);
			jPanel.add(getAutoRename(), null);
		}
		return jPanel;
	}

	private JCheckBox getAutoRename() {
		if (autoRename == null) {
			autoRename = new JCheckBox();
		}
		return autoRename;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
