package org.sgx.jmencoder.streamplayer.gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.sgx.jmencoder.ResourceManager;
import org.sgx.jmencoder.streamplayer.StreamPlayerConfig;
import org.sgx.jmencoder.streamplayer.ModelUtils;
import org.sgx.jmencoder.streamplayer.model.StreamDocument;
import org.sgx.swing.utils.SwingUtils;
import org.w3c.dom.Document;


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
public class StreamPlayerFrame extends javax.swing.JFrame {
	protected static final Object ABOUT_MESSAGE = "Un reproductor y grabador de radio y tv online," +
			"\nmuy simple, con listas de algunos canales,\nespecialmente en español. \nAutor: Sebastián Gurin <sgurin@montevideo.com.uy>";
	private JSplitPane jSplitPane1;
	private PlaybackPanel playbackPanel;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem4;
	private JMenu jMenu1;
	private JMenuItem jMenuItem1;
	private JMenu jMenu2;
	private PlayListPanel playListPanel;
	private ModelUtils utils;

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StreamPlayerFrame inst = new StreamPlayerFrame();
				inst.setTitle("stream player by sgurin");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public StreamPlayerFrame() {
		super();
		utils = ModelUtils.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File");
					{
						jMenuItem4 = new JMenuItem();
						jMenu1.add(jMenuItem4);
						jMenuItem4.setText("Exit");
						jMenuItem4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try {
									doQuit();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								 System.exit(0);
							}
						});
						jMenuItem4.setIcon(ResourceManager.getInstance().getIcon("exit"));
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Help");
					{
						jMenuItem1 = new JMenuItem();
						jMenu2.add(jMenuItem1);
						jMenuItem1.setText("About");
						jMenuItem1.addActionListener(new ActionListener() {							
							@Override
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(StreamPlayerFrame.this, ABOUT_MESSAGE);
							}
						});
					}
				}
			}
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						doQuit();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					super.windowClosing(e);
				}
			});
			{
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1, BorderLayout.CENTER);
				{
					playListPanel = new PlayListPanel(this);
					jSplitPane1.add(playListPanel, JSplitPane.LEFT);
				}
				{
					playbackPanel = new PlaybackPanel();
					playbackPanel.setPlaylistPanel(playListPanel);
					jSplitPane1.add(playbackPanel, JSplitPane.RIGHT);
				}
				jSplitPane1.setDividerLocation(0.7);
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	//abstract user operations
	public void doQuit() throws IOException {
		playbackPanel.getMplayer().enterCommand("quit");		
	}

	public void doSavePlayList(JTree tree) {
		File f = SwingUtils.showSaveAsDialog(playListPanel, 
				StreamPlayerConfig.PLAYLIST_FILE_EXTENSION, "Save as...", 
				StreamPlayerConfig.PLAYLIST_FILE_EXTENSION+ " files");
		if(f!=null) {
			DefaultMutableTreeNode model = (DefaultMutableTreeNode)tree.getModel().getRoot();
			StreamDocument doc = ModelUtils.getInstance().documentFromTreeModel(model);
			try {
				Document xmldoc = ModelUtils.getInstance().writeXMLDocument(doc);
				FileOutputStream fos = new FileOutputStream(f);
				ModelUtils.getInstance().writeXML(xmldoc, fos);
				fos.close();				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(playListPanel, "ERROR: "+e.getClass()+". CAUSE: "+e.getMessage()+"\nSee the console stack trace for more information");
				e.printStackTrace();
			}
		}
	}

	public void doOpenPlayList() {
//		StreamPlayerFrame.class.getResourceAsStream(name)
//		String currentdir = System.getProperty("user.dir")+File.separator+ModelUtils.DEFAULT_PLAYLIST_DIR;
		File f = SwingUtils.showOpenFileDialog(playListPanel, 
				StreamPlayerConfig.PLAYLIST_FILE_EXTENSION, new File(ModelUtils.DEFAULT_PLAYLIST_DIR));
		if(f!=null) {
			try {
				ModelUtils.getInstance().resetEndpointCounter();
				StreamDocument sd = ModelUtils.getInstance().readXMLDocument(f);
				DefaultTreeModel treeModel = utils.toTreeModel(sd);
				playListPanel.getTree().setModel(treeModel);	
				playListPanel.getTree().updateUI();
				playListPanel.getEndpointCountLabel().setText(ModelUtils.getInstance().getEndpointCounter()+" endpoints readed.");
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(playListPanel, "ERROR: "+e.getClass()+"\n CAUSE: "+e.getMessage()+"\nSee the console stack trace for more information");
				
			}
		}
	}

}
