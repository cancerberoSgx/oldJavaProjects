package org.sgx.jmencoder.streamplayer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.sgx.j2s.model.editor.selection.SelectionEvent;
import org.sgx.j2s.model.editor.selection.SelectionListener;
import org.sgx.jmencoder.ResourceManager;
import org.sgx.jmencoder.streamplayer.StreamPlayerConfig;
import org.sgx.jmencoder.streamplayer.ModelUtils;
import org.sgx.jmencoder.streamplayer.model.Section;
import org.sgx.jmencoder.streamplayer.model.StreamDocument;
import org.sgx.jmencoder.streamplayer.model.StreamElement;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;
import org.sgx.swing.utils.SwingUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


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
public class PlayListPanel extends javax.swing.JPanel {
	private JPanel jPanel1;
	private JLabel playListName;
	private JLabel jLabel1;
	private JButton jButton5;
	private JButton jButton4;
	private EditableTree treeUtil;
	private JScrollPane jScrollPane1;
	private JLabel endpointCountLabel;
	private JButton jButton1;
	private JPanel jPanel3;
	private EndpointEditor endpointEditor;
	private JButton jButton7;
	private JButton jButton6;
	private JPanel jPanel2;
	private JTree tree;
	private ModelUtils utils;
	private DefaultTreeModel treeModel;
	protected boolean dirtyPlayList;
	protected EndpointEditorDialog endpointDialog;
	private StreamPlayerFrame frame;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PlayListPanel(null));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	public PlayListPanel(){
		super();
		initGUI();
	}
	public PlayListPanel(StreamPlayerFrame frame) {
		super();
		this.frame=frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.2, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {50, 400, 7, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
//			setPreferredSize(new Dimension(400, 300));
			
			
	    	
			{
				jPanel1 = new JPanel();
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.0};
				jPanel1Layout.rowHeights = new int[] {33};
				jPanel1Layout.columnWeights = new double[] {0.1, 0.1, 0.1};
				jPanel1Layout.columnWidths = new int[] {7, 7, 7};
				jPanel1.setLayout(jPanel1Layout);
				{
					jButton5 = new JButton();
					jPanel1.add(jButton5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton5.setText("open...");
					jButton5.setIcon(ResourceManager.getInstance().getIcon("open"));
					jButton5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							frame.doOpenPlayList();
						}
					});
				}
				//				jPanel1.setPreferredSize(new java.awt.Dimension(401, 118));
				{
					jButton4 = new JButton();
					jPanel1.add(jButton4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton4.setText("save...");
					jButton4.setIcon(ResourceManager.getInstance().getIcon("save"));
					jButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							frame.doSavePlayList(tree);
						}
					});
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel1.setText("Play List Name:");
				}
				{
					playListName = new JLabel();
					jPanel1.add(playListName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					playListName.setText("default");
				}
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("new");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("jButton1.actionPerformed, event="+evt);
							if(JOptionPane.showConfirmDialog(PlayListPanel.this, "All modification to current list will be lost.\nAre you sure you whish to create a new list?")
									==JOptionPane.OK_OPTION) {
								
								String plName = null;
								if((plName=JOptionPane.showInputDialog("Name for new play list: "))!=null) {
									StreamDocument sd;
									try {
										sd = ModelUtils.getInstance().newXMLDocument();
										treeModel = utils.toTreeModel(sd);
										tree.setModel(treeModel);	
										tree.updateUI();
									} catch (Exception e) {
										JOptionPane.showMessageDialog(PlayListPanel.this, "Error creating new document: "+e.getLocalizedMessage());
										e.printStackTrace();
									} 
									
								}
							}
							
						}
					});
					jButton1.setIcon(ResourceManager.getInstance().getIcon("new"));
				}
				{
					endpointCountLabel = new JLabel(".");
					jPanel1.add(endpointCountLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
			{
				
				
//			this.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				{
					jButton6 = new JButton();
					jPanel2.add(jButton6);
					jButton6.setText("apply");
					jButton6.setIcon(ResourceManager.getInstance().getIcon("apply"));
					jButton6.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
							StreamElement el = (StreamElement) node.getUserObject();
							StreamElement newEl = null;
							if(el instanceof Section) {
								newEl = new Section(endpointEditor.getNameEntry().getText(), 
										endpointEditor.getDescriptionEntry().getText(), 
										"", ((Section)el).getChildren());
							}
							else if(el instanceof StreamEndpoint) {
								newEl = new StreamEndpoint(endpointEditor.getNameEntry().getText(), 
										endpointEditor.getDescriptionEntry().getText(), "", 
										endpointEditor.getUrlEntry().getText());
							}
							node.setUserObject(newEl);							
							tree.updateUI();
							dirtyPlayList=true;
							
							TreeModel m = tree.getModel();
						}
					});
				}
				{
					jButton7 = new JButton();
					jPanel2.add(jButton7);
					jButton7.setText("reset");
					jButton7.setIcon(ResourceManager.getInstance().getIcon("reset"));
					jButton7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("jButton7.actionPerformed, event="+evt);
							//TODO add your code for jButton7.actionPerformed
						}
					});
				}
			}
			{
				endpointEditor = new EndpointEditor();
				this.add(endpointEditor, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				
				treeUtil = new EditableTree();
		    	treeUtil.setEditableLabels(true);
		    	
		    	utils = ModelUtils.getInstance();
		    	
		    	jScrollPane1 = treeUtil.getContent();
		    	StreamDocument sd = utils.readXMLDocument(ModelUtils.DEFAULT_PLAY_LIST_FILE);		    
				//creamos el treemodel a partir del modelo cargado
				treeModel = utils.toTreeModel(sd);
				tree = treeUtil.getTree();
				tree.setModel(treeModel);	
				tree.addTreeSelectionListener(new TreeSelectionListener() {					
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						System.out.println("selection evt");
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
						Object ud =  node.getUserObject();
						if(ud instanceof StreamElement){
							StreamElement el = (StreamElement)ud;
							endpointEditor.setSelectedStreamElement(el);
						}
						else {
							tree.updateUI();
						}
																	
					}
				});
				
				//tree context menu
				JPopupMenu popupMenu = new JPopupMenu("Title");
			    JMenuItem addSecMenuItem = new JMenuItem("Add Section");
			    addSecMenuItem.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						endpointDialog = new EndpointEditorDialog(
								(JFrame) SwingUtilities.getRoot(PlayListPanel.this), true);
						endpointDialog.setSize(300,300);
						endpointDialog.setVisible(true);
						endpointDialog.addSelectionListener(new SelectionListener<StreamElement>() {							
							@Override
							public void notify(SelectionEvent<StreamElement> e) {
								StreamElement newEl = e.getData();
								DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
								StreamElement selEl = (StreamElement) selNode.getUserObject();
								if(selEl instanceof Section) {
									DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newEl);
									selNode.add(newNode);
									if(newEl instanceof Section)
										selNode.setAllowsChildren(true);
								}
								endpointDialog.dispose();
								endpointDialog=null;
								tree.updateUI();
							}
						});
					}
				});
			    popupMenu.add(addSecMenuItem);
			    JMenuItem addEndMenuItem = new JMenuItem("Add Endpoint");
			    addEndMenuItem.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						endpointDialog = new EndpointEditorDialog(
								(JFrame) SwingUtilities.getRoot(PlayListPanel.this), false);
						endpointDialog.setSize(300,300);
						endpointDialog.setVisible(true);
						endpointDialog.addSelectionListener(new SelectionListener<StreamElement>() {							
							@Override
							public void notify(SelectionEvent<StreamElement> e) {
								StreamElement newEl = e.getData();
								DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
								StreamElement selEl = (StreamElement) selNode.getUserObject();
								if(selEl instanceof Section) {
									DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newEl);
									selNode.add(newNode);
								}
								endpointDialog.dispose();
								endpointDialog=null;
								tree.updateUI();
							}
						});
					}
				});
			    popupMenu.add(addEndMenuItem);
			    JMenuItem removeMenuItem = new JMenuItem("Remove");
			    removeMenuItem.setIcon(ResourceManager.getInstance().getIcon("remove"));
			    removeMenuItem.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						treeUtil.removeSelectedNodes();
					}
				});
//			    removeMenuItem.setEnabled(false);
			    popupMenu.add(removeMenuItem);
			    popupMenu.addSeparator();
			    tree.setComponentPopupMenu(popupMenu);
			    
				this.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultMutableTreeNode getSelectedNode() {
		return treeUtil.getSelectedNode();
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}

	public JLabel getEndpointCountLabel() {
		return endpointCountLabel;
	}


	
}
