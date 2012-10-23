package org.sgx.jmencoder.gui.preferences;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sgx.jmencoder.mplayer.SupportedFiles;
import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.utils.SwingUtils;

public class FileAsociationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SimpleBeanEditor videoExtensionPanel = null;
	private SimpleBeanEditor audioExtensionPanel = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;

	public FileAsociationPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(353, 272);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getJPanel(), null);
	}

	private SimpleBeanEditor getVideoExtensionPanel() {
		if (videoExtensionPanel == null) {
			videoExtensionPanel = new SimpleBeanEditor(getEdittableObjectFor(SupportedFiles.SupportedVideoExtension), true);
			videoExtensionPanel.setBorder(BorderFactory.createTitledBorder("video"));
			videoExtensionPanel.setPreferredSize(new Dimension(200, 200));
		}
		return videoExtensionPanel;
	}

	private Map<String, Object> getEdittableObjectFor(String[] a) {
		Map<String, Object> m = new HashMap<String, Object>();
		for(int i=0; i<a.length; i++)
			m.put(a[i], new Boolean(true));
		return m;
	}

	private SimpleBeanEditor getAudioExtensionPanel() {
		if (audioExtensionPanel == null) {
			audioExtensionPanel = new SimpleBeanEditor(getEdittableObjectFor(SupportedFiles.SupportedAudioExtension), true);
			audioExtensionPanel.setBorder(BorderFactory.createTitledBorder("audio"));
			audioExtensionPanel.setPreferredSize(new Dimension(200, 200));
		}
		return audioExtensionPanel;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
			jPanel.add(getAudioExtensionPanel(), null);
			jPanel.add(getVideoExtensionPanel(), null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
		}
		return jPanel1;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("asociate");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(JOptionPane.showConfirmDialog(FileAsociationPanel.this, "are you sure?")==JOptionPane.YES_OPTION) {
						Map<String, Object> m = audioExtensionPanel.getSelectedData();
						
					}
				}
			});
		}
		return jButton;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("de-asociate");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton1;
	}
	
	public static void main(String[] args) {
		SwingUtils.showInFrame(new FileAsociationPanel());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
