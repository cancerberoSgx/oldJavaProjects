package org.sgx.jmencoder.gui.panels;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;


public class VideoFormatOptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JTextField widthEntry = null;
	private JLabel jLabel1 = null;
	private JTextField heightEntry = null;
	/**
	 * This is the default constructor
	 */
	public VideoFormatOptionPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getJPanel(), null);
		this.add(getJPanel1(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.setBorder(BorderFactory.createTitledBorder("quality" +
					""));
			jPanel.add(getJPanel3(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS));
			jPanel1.setBorder(BorderFactory.createTitledBorder("format"));
			jPanel1.add(getJPanel2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new FlowLayout());
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("height: ");
			jLabel = new JLabel();
			jLabel.setText("width:");
			jPanel3 = new JPanel();
			jPanel3.setLayout(new FlowLayout());
			jPanel3.add(jLabel, null);
			jPanel3.add(getWidthEntry(), null);
			jPanel3.add(jLabel1, null);
			jPanel3.add(getHeightEntry(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes widthEntry	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getWidthEntry() {
		if (widthEntry == null) {
			widthEntry = new JTextField();
			widthEntry.setPreferredSize(new Dimension(40, 20));
		}
		return widthEntry;
	}

	/**
	 * This method initializes heightEntry	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHeightEntry() {
		if (heightEntry == null) {
			heightEntry = new JTextField();
			heightEntry.setPreferredSize(new Dimension(40, 20));
		}
		return heightEntry;
	}

}
