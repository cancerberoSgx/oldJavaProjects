package org.sgx.jmencoder.streamplayer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.sgx.jmencoder.streamplayer.model.Section;
import org.sgx.jmencoder.streamplayer.model.StreamElement;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class EndpointEditor extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextPane descriptionEntry;
	private JTextField urlEntry;
	private JTextField nameEntry;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new EndpointEditor());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public EndpointEditor() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setPreferredSize(new Dimension(400, 300));
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {27, 27, 69, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.1};
			thisLayout.columnWidths = new int[] {83, 7};
			this.setLayout(thisLayout);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel1.setText("Name");
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel2.setText("Url");
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel3.setText("Description:");
			}
			{
				nameEntry = new JTextField();
				this.add(nameEntry, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				urlEntry = new JTextField();
				this.add(urlEntry, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				descriptionEntry = new JTextPane();
				this.add(descriptionEntry, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextPane getDescriptionEntry() {
		return descriptionEntry;
	}

	public JTextField getUrlEntry() {
		return urlEntry;
	}

	public JTextField getNameEntry() {
		return nameEntry;
	}

	public void setSelectedStreamElement(StreamElement el) {
		descriptionEntry.setText(el.getDescription());
		nameEntry.setText(el.getName());
		if(el instanceof Section) {
			urlEntry.setText("");
		}
		else if(el instanceof StreamEndpoint) {
			urlEntry.setText(((StreamEndpoint)el).getUrl());
		}	
	}

	public void hideURLEntry() {
		urlEntry.setVisible(false);
		jLabel2.setVisible(false);
		
	}
}
