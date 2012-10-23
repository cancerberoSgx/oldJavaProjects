package org.sgx.jmencoder.gui.dirtyMencoderGui;
//package dirtyMencoderGui;
//
//import gui.fontList.FontChooser;
//
//import java.awt.GridBagLayout;
//
//import javax.swing.BorderFactory;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//
//import java.awt.Component;
//import java.awt.GridBagConstraints;
//import java.awt.Dimension;
//import javax.swing.JCheckBox;
//import java.awt.Rectangle;
//import javax.swing.BoxLayout;
//import javax.swing.JLabel;
//
//public class OutputPanel extends JPanel {
//
//	private static final long serialVersionUID = 1L;
//	private JTabbedPane jTabbedPane = null;
//	private JPanel formatPanel = null;
//	private JPanel subtitlesPanel = null;
//	private JPanel jPanel = null;
//	private JPanel deletesrcs = null;
//	private JCheckBox deletesrcChekbox = null;
//	private JLabel jLabel = null;
//	private JPanel jPanel1 = null;
//	private JLabel jLabel1 = null;
//	private JCheckBox putInSrcFolders = null;
//	private FontChooser subFontChooser;
//	/**
//	 * This is the default constructor
//	 */
//	public OutputPanel() {
//		super();
//		initialize();
//	}
//
//	/**
//	 * This method initializes this
//	 * 
//	 * @return void
//	 */
//	private void initialize() {
//		GridBagConstraints gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.weighty = 1.0;
//		gridBagConstraints.weightx = 1.0;
//		this.setSize(407, 287);
//		this.setLayout(new GridBagLayout());
//		this.add(getJTabbedPane(), gridBagConstraints);
//	}
//
//	/**
//	 * This method initializes jTabbedPane	
//	 * 	
//	 * @return javax.swing.JTabbedPane	
//	 */
//	private JTabbedPane getJTabbedPane() {
//		if (jTabbedPane == null) {
//			jTabbedPane = new JTabbedPane();
//			jTabbedPane.addTab("Format", null, getFormatPanel(), null);
//			jTabbedPane.addTab("Subtitles", null, getSubtitlesPanel(), null);
//			jTabbedPane.addTab("Location", null, getJPanel(), null);
//		}
//		return jTabbedPane;
//	}
//
//	/**
//	 * This method initializes formatPanel	
//	 * 	
//	 * @return javax.swing.JPanel	
//	 */
//	private JPanel getFormatPanel() {
//		if (formatPanel == null) {
//			formatPanel = new JPanel();
//			formatPanel.setLayout(new GridBagLayout());
//		}
//		return formatPanel;
//	}
//
//	/**
//	 * This method initializes subtitlesPanel	
//	 * 	
//	 * @return javax.swing.JPanel	
//	 */
//	private JPanel getSubtitlesPanel() {
//		if (subtitlesPanel == null) {
//			subtitlesPanel = new JPanel();
//			subtitlesPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
//			subtitlesPanel.add(getSubFontChooser());
//		}
//		return subtitlesPanel;
//	}
//
//	private Component getSubFontChooser() {
//		if(subFontChooser==null) {
////			subFontChooser = new FontChooser();
////			subFC.setBorder(BorderFactory.createTitledBorder("Subtitle Font"));
//		}
//		return subFontChooser;
//	}
//
//	/**
//	 * This method initializes jPanel	
//	 * 	
//	 * @return javax.swing.JPanel	
//	 */
//	private JPanel getJPanel() {
//		if (jPanel == null) {
//			jPanel = new JPanel();
//			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
//			jPanel.add(getDeletesrcs(), null);
//			jPanel.add(getJPanel1(), null);
//		}
//		return jPanel;
//	}
//
//	/**
//	 * This method initializes deletesrcs	
//	 * 	
//	 * @return javax.swing.JPanel	
//	 */
//	private JPanel getDeletesrcs() {
//		if (deletesrcs == null) {
//			jLabel = new JLabel();
//			jLabel.setBounds(new Rectangle(26, 9, 135, 16));
//			jLabel.setText("delete source videos");
//			deletesrcs = new JPanel();
//			deletesrcs.setLayout(null);
//			deletesrcs.setPreferredSize(new Dimension(100, 35));
//			deletesrcs.add(jLabel, null);
//			deletesrcs.add(getDeletesrcChekbox(), null);
//		}
//		return deletesrcs;
//	}
//
//	/**
//	 * This method initializes deletesrcChekbox	
//	 * 	
//	 * @return javax.swing.JCheckBox	
//	 */
//	private JCheckBox getDeletesrcChekbox() {
//		if (deletesrcChekbox == null) {
//			deletesrcChekbox = new JCheckBox();
//			deletesrcChekbox.setBounds(new Rectangle(7, 6, 21, 21));
//		}
//		return deletesrcChekbox;
//	}
//
//	/**
//	 * This method initializes jPanel1	
//	 * 	
//	 * @return javax.swing.JPanel	
//	 */
//	private JPanel getJPanel1() {
//		if (jPanel1 == null) {
//			jLabel1 = new JLabel();
//			jLabel1.setText("put output videos in the source folders");
//			jLabel1.setBounds(new Rectangle(32, 11, 217, 16));
//			jPanel1 = new JPanel();
//			jPanel1.setLayout(null);
//			jPanel1.add(jLabel1 );
//			jPanel1.add(getPutInSrcFolders());
//		}
//		return jPanel1;
//	}
//
//	/**
//	 * This method initializes putInSrcFolders	
//	 * 	
//	 * @return javax.swing.JCheckBox	
//	 */
//	private JCheckBox getPutInSrcFolders() {
//		if (putInSrcFolders == null) {
//			putInSrcFolders = new JCheckBox();
//			putInSrcFolders.setBounds(new Rectangle(10, 9, 21, 21));
//		}
//		return putInSrcFolders;
//	}
//
//}  //  @jve:decl-index=0:visual-constraint="10,10"
