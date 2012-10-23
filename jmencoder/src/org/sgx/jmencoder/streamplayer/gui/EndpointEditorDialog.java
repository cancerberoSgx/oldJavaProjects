package org.sgx.jmencoder.streamplayer.gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.sgx.j2s.model.editor.Editor;
import org.sgx.j2s.model.editor.selection.SelectionEvent;
import org.sgx.j2s.model.editor.selection.SelectionListener;
import org.sgx.j2s.model.editor.selection.SelectionTarget;
import org.sgx.j2s.model.events.EditorListener;
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
public class EndpointEditorDialog extends javax.swing.JDialog implements Editor<StreamElement>, SelectionTarget<StreamElement> {
	private EndpointEditor endpointEditorPanel;
	private JPanel jPanel1;
	private JButton jButton1;
	private JButton jButton2;
	private boolean isSection;
	List<SelectionListener<StreamElement>> listeners;
	
	
	public EndpointEditorDialog(JFrame frame, boolean isSection) {
		super(frame);
		initGUI();
		this.isSection=isSection;
		listeners=new LinkedList<SelectionListener<StreamElement>>();
	}
	
	private void initGUI() {
		try {

			GridBagLayout thisLayout = new GridBagLayout();
			{
				thisLayout.rowWeights = new double[] {0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7};
				thisLayout.columnWeights = new double[] {0.1};
				thisLayout.columnWidths = new int[] {7};
			}
			getContentPane().setLayout(thisLayout);
			{
				endpointEditorPanel = new EndpointEditor();
				getContentPane().add(endpointEditorPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("save");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String name =endpointEditorPanel.getNameEntry().getText(), 
								url = endpointEditorPanel.getUrlEntry().getText(), 
								desc = endpointEditorPanel.getDescriptionEntry().getText();
							StreamElement el = null;
							if(isSection)
								el= new Section(name, desc, "", new LinkedList<StreamElement>());
							else
								el = new StreamEndpoint(name, desc, "", url);
							SelectionEvent<StreamElement> ev = new SelectionEvent<StreamElement>(el, EndpointEditorDialog.this);							
							for (Iterator<SelectionListener<StreamElement>> iterator = listeners.iterator(); iterator.hasNext();) {								
								iterator.next().notify(ev);								
							}
							
						}
					});

				}
				{
					jButton2 = new JButton();
					jPanel1.add(jButton2);
					jButton2.setText("cancel");
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							EndpointEditorDialog.this.setVisible(false);
						}
					});
				}
			}
			setSize(400, 300);
			//init
			if(isSection) {
				endpointEditorPanel.hideURLEntry();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public StreamElement getCurrentValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(StreamElement value) {
		// TODO Auto-generated method stub
		
	}

	
	
	//debug
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				EndpointEditorDialog inst = new EndpointEditorDialog(frame, false);
				inst.setVisible(true);
			}
		});
	}

//	@Override
//	public void addEventListener(SelectionListener<StreamElement> l) {
//		listeners.add(l);
//	}
//
//	@Override
//	public void removeEventListener(SelectionListener<StreamElement> l) {
//		listeners.remove(l);
//	}

	@Override
	public void addSelectionListener(SelectionListener<StreamElement> l) {
		listeners.add(l);
	}

	@Override
	public void removeSelectionListener(SelectionListener<StreamElement> l) {
		listeners.add(l);
	}
}
