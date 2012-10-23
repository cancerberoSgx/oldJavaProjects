package org.sgx.swing.gui.editors;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;


import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;

import java.awt.Color;

/**
 * 
 * @author five minit sgurin visual class ;)
 *
 */
public class FloatEditor1 extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextField input = null;
	private JPanel jPanel = null;
	private JButton upBtt = null;
	private JButton downBtt = null;

	/**
	 * This method initializes input	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInput() {
		if (input == null) {
			input = new JTextField();
			input.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					String currentEntry = input.getText()+e.getKeyChar();
					
					try {
						value = new Double(currentEntry).doubleValue();
						error.setVisible(false);
						System.out.println("sucess()"+input.getText()+" - "+
								e.getKeyChar());
					} catch(Exception e1) {
						error.setVisible(true);
						System.out.println("error"+input.getText()+" - "+
								e.getKeyChar()+" - "+e1.getMessage());
					}
					
				
				}
			});
		}
		return input;
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
			jPanel.add(getUpBtt(), null);
			jPanel.add(getDownBtt(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes upBtt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUpBtt() {
		if (upBtt == null) {
			upBtt = new JButton();
			upBtt.setText("");
			upBtt.setPreferredSize(new Dimension(20, 28));
			upBtt.setIcon(new ImageIcon(getClass().getResource("/images/upArrow.gif")));
			upBtt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FloatEditor1.this.setValue(FloatEditor1.this.value+FloatEditor1.this.step);
				}
			});
		}
		return upBtt;
	}

	/**
	 * This method initializes downBtt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDownBtt() {
		if (downBtt == null) {
			downBtt = new JButton();
			downBtt.setIcon(new ImageIcon(getClass().getResource("/images/downArrow.gif")));
			downBtt.setPreferredSize(new Dimension(20, 28));
			downBtt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FloatEditor1.this.setValue(FloatEditor1.this.value-FloatEditor1.this.step);
				}
			});
		}
		return downBtt;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.Y_AXIS));
			jPanel1.add(getInput(), null);
			jPanel1.add(error, null);
		}
		return jPanel1;
	}

	/**
	 * This is the default constructor
	 */
	public FloatEditor1() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		error = new JLabel();
		error.setText("Invalid Float number format");
		error.setForeground(new Color(167, 51, 51));
		this.setSize(183, 58);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getJPanel1(), null);
		this.add(getJPanel(), null);
		error.setVisible(false);
	}
	
	
	//editor things
	public double step = defaultStep;
	public static final double defaultValue = 0.0, defaultStep = 0.1;
	double value=defaultValue;
	Set<FloatEditor1Listener> listeners=new HashSet<FloatEditor1Listener>();
	private JLabel error = null;
	private JPanel jPanel1 = null;

	public void addListener(FloatEditor1Listener l) {listeners.add(l);}

	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
		input.setText(value+"");
		for (FloatEditor1Listener i : listeners) {
			i.notifyValueChanged(value);
		}
	}
	public FloatEditor1(double value, double step) {
		super();
		this.step = step;
		this.value = value;
	}
	public FloatEditor1(double value, double step, FloatEditor1Listener l) {
		this(value, step);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
