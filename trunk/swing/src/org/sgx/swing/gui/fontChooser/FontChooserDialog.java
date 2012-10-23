package org.sgx.swing.gui.fontChooser;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
/**
 * 
 * @author SGURIN
 *
 */
public class FontChooserDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private FontPanel1 fcdialog = null;

	private JPanel jPanel = null;

	private JButton acceptbt = null;

	private JButton jButton = null;
	
	List<FontChooserListener>listeners=new LinkedList<FontChooserListener>();

	public void addFontChooserListener(FontChooserListener l) {
		listeners.add(l);
	}
	
	
	private FontPanel1 getFcdialog() {
		if (fcdialog == null) {
			fcdialog = new FontPanel1();
		}
		return fcdialog;
	}

	/**
	 * @param owner
	 */
	public FontChooserDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getFcdialog(), null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getAcceptbt(), null);
			jPanel.add(getJButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes acceptbt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAcceptbt() {
		if (acceptbt == null) {
			acceptbt = new JButton();
			acceptbt.setText("Accept");
			acceptbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					for(FontChooserListener l : listeners) {
						l.notifyFontChoosed(fcdialog.getSelectedFont());
					}
				}
			});
		}
		return acceptbt;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Cancel");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					for(FontChooserListener l : listeners) {
						l.notifyFontChooserCanceled();
					}
				}
			});
		}
		return jButton;
	}

}
