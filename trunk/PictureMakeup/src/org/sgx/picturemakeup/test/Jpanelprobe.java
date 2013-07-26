package org.sgx.picturemakeup.test;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Jpanelprobe extends JPanel {

	private JTextField jTextField = null;

	/**
	 * This is the default constructor
	 */
	public Jpanelprobe() {
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
		this.add(getJTextField(), null);
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					System.out.println("keyTyped()"); // TODO Auto-generated Event stub keyTyped()
				}
			});
		}
		return jTextField;
	}

}
