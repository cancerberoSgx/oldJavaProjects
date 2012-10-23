//package org.sgx.jmencoder.desktrec;
//
//import java.awt.AWTException;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//
//import javax.swing.BoxLayout;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//
//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
//import javax.swing.JToggleButton;
//import javax.swing.WindowConstants;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.filechooser.FileFilter;
//
//import org.sgx.jmencoder.desktrec.sel.SelectionEvent;
//import org.sgx.jmencoder.desktrec.sel.SelectionListener;
//import org.sgx.utils.FSUtils;
//
///**
//* This code was edited or generated using CloudGarden's Jigloo
//* SWT/Swing GUI Builder, which is free for non-commercial
//* use. If Jigloo is being used commercially (ie, by a corporation,
//* company or business for any purpose whatever) then you
//* should purchase a license for each developer using Jigloo.
//* Please visit www.cloudgarden.com for details.
//* Use of Jigloo implies acceptance of these licensing terms.
//* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
//* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
//* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
//*/
//public class CopyOfDeskRecMainPanel extends javax.swing.JPanel {
//	protected static final String STATUS_RECORDING = "status: recording";
//	protected static final String STATUS_LOADED = "status: loaded";
//	protected static final String STATUS_PAUSED = "status: paused";
//	protected static final String STATUS_IDDLE = "status: iddle";
//	private JTextArea consoleArea;
//	private JPanel jPanel4;
//	private JLabel jLabel1;
//	private JButton jButton5;
//	private JPanel jPanel1;
//	private JButton jButton1;
//	private JPanel jPanel3;
//	private JLabel statusLabel;
//	private JButton jButton4;
//	private JToggleButton jButton3;
//	private JButton jButton2;
//	private JPanel jPanel2;
//	private JTextField captureAreaField;
//	private ButtonGroup buttonGroup1;
//	private JRadioButton captureRectangleOption;
//	private JRadioButton captureWholeScreenOption;
//	protected Rectangle captureArea;
//	protected DeskRecConfig config;
//
//	
//	
//	public CopyOfDeskRecMainPanel() {
//		super();
//		initGUI();
//		config = DeskRecConfig.getInstance(this);
//	}
//	
//	private void initGUI() {
//		try {
//			GridBagLayout thisLayout = new GridBagLayout();
//			setPreferredSize(new Dimension(400, 300));
//			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1, 0.1, 0.1};
//			thisLayout.rowHeights = new int[] {45, 71, 7, 7, 7};
//			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0};
//			thisLayout.columnWidths = new int[] {108, 286, 99};
//			this.setLayout(thisLayout);
//			{
//				jLabel1 = new JLabel();
//				this.add(jLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//				jLabel1.setText("Area");
//			}
//			{
//				jPanel1 = new JPanel();
//				GridBagLayout jPanel1Layout = new GridBagLayout();
//				jPanel1Layout.rowWeights = new double[] {0.0, 0.1};
//				jPanel1Layout.rowHeights = new int[] {25, 7};
//				jPanel1Layout.columnWeights = new double[] {0.0, 0.1};
//				jPanel1Layout.columnWidths = new int[] {114, 7};
//				this.add(jPanel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//				this.add(getJPanel2(), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//				this.add(getJPanel3(), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//				this.add(getJPanel4(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//				jPanel1.setLayout(jPanel1Layout);				
//				{
//					captureWholeScreenOption = new JRadioButton();
//					
//					jPanel1.add(captureWholeScreenOption, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//					captureWholeScreenOption.setText("whole screen");
//					getButtonGroup1().add(captureWholeScreenOption);
//					captureWholeScreenOption.setSelected(true);
//				}
//				{
//					captureRectangleOption = new JRadioButton();
//					captureRectangleOption.addActionListener(new ActionListener() {						
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							JOptionPane.showMessageDialog(CopyOfDeskRecMainPanel.this, "draw a rectangle in the screen or press escape");
//							config.openDesktopAreaChooser(new SelectionListener<Rectangle>() {
//								@Override
//								public void notify(SelectionEvent<Rectangle> e) {
//									Rectangle r = e.getData();
//									captureAreaField.setText(Math.round(r.getX())+","+Math.round(r.getY())+","+
//											Math.round(r.getWidth())+","+Math.round(r.getHeight()));
//									CopyOfDeskRecMainPanel.this.captureArea = r;
//								}
//							});
//						}
//					});
//					jPanel1.add(captureRectangleOption, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//					jPanel1.add(getJTextField1(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//					captureRectangleOption.setText("rectangle:");
//					getButtonGroup1().add(captureRectangleOption);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private ButtonGroup getButtonGroup1() {
//		if(buttonGroup1 == null) {
//			buttonGroup1 = new ButtonGroup();
//		}
//		return buttonGroup1;
//	}
//	
//	private JTextField getJTextField1() {
//		if(captureAreaField == null) {
//			captureAreaField = new JTextField();
//			captureAreaField.setText("20,20,300,300");
//		}
//		return captureAreaField;
//	}
//	
//	private JPanel getJPanel2() {
//		if(jPanel2 == null) {
//			jPanel2 = new JPanel();
//			BoxLayout jPanel2Layout = new BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS);
//			jPanel2.setLayout(jPanel2Layout);
//		}
//		return jPanel2;
//	}
//	
//	private JButton getJButton1() {
//		if(jButton1 == null) {
//			jButton1 = new JButton();
//			jButton1.setText("record");
//			jButton1.addActionListener(new ActionListener() {				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					Rectangle area = null;
//					if(CopyOfDeskRecMainPanel.this.captureRectangleOption.isSelected() && CopyOfDeskRecMainPanel.this.captureArea!=null) {
//						area = CopyOfDeskRecMainPanel.this.captureArea;
//					}
//					else {
//						area = new Rectangle(new Point(0,0), Toolkit.getDefaultToolkit().getScreenSize());
//						CopyOfDeskRecMainPanel.this.captureArea=config.getWholeScreenArea();
//					}
//					
//					//minimize this window and start recording
//					if(config.minimizeWindowOnRecording) {
//						JFrame parentFrame = (JFrame) CopyOfDeskRecMainPanel.this.getTopLevelAncestor();
//						parentFrame.setState(JFrame.ICONIFIED);
//					}
//					try {
//						config.record(area);
//						statusLabel.setText(STATUS_RECORDING);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(CopyOfDeskRecMainPanel.this, "error "+e1.getMessage()+" while trying to record ");
//						e1.printStackTrace();
//					}
//				}
//			});
//		}
//		return jButton1;
//	}
//	
//	private JButton getJButton2() {
//		if(jButton2 == null) {
//			jButton2 = new JButton();
//			jButton2.setText("screenshot");
//		}
//		return jButton2;
//	}
//	
//	private JToggleButton getJButton3() {
//		if(jButton3 == null) {
//			jButton3 = new JToggleButton();
//			jButton3.setText("pause");
//			jButton3.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent evt) {
//					config.getRecorder().togglePauseRecording();
//					if(config.getRecorder().isRecordingPaused())
//						statusLabel.setText(STATUS_PAUSED);
//					else
//						statusLabel.setText(STATUS_RECORDING);
//				}
//			});
//		}
//		return jButton3;
//	}
//	
//	private JButton getJButton4() {
//		if(jButton4 == null) {
//			jButton4 = new JButton();
//			jButton4.setText("stop");
//			jButton4.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent evt) {
//					try {
//						config.getRecorder().stopRecording();
//						statusLabel.setText(STATUS_LOADED);
//					} catch (Exception e) {
//						JOptionPane.showMessageDialog(CopyOfDeskRecMainPanel.this, "error message after recording: "+e.getMessage());
//						e.printStackTrace();
//					} 
//				}
//			});
//		}
//		return jButton4;
//	}
//	
//	private JLabel getStatusLabel() {
//		if(statusLabel == null) {
//			statusLabel = new JLabel();
//			statusLabel.setText(STATUS_IDDLE);
//		}
//		return statusLabel;
//	}
//	
//	private JPanel getJPanel3() {
//		if(jPanel3 == null) {
//			jPanel3 = new JPanel();
//			GridBagLayout jPanel3Layout = new GridBagLayout();
//			jPanel3Layout.columnWidths = new int[] {73, 85, 7};
//			jPanel3Layout.rowHeights = new int[] {28, 7};
//			jPanel3Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
//			jPanel3Layout.rowWeights = new double[] {0.0, 0.1};
//			jPanel3.setLayout(jPanel3Layout);
//			jPanel3.add(getJButton4(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//			jPanel3.add(getJButton3(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//			jPanel3.add(getJButton1(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//			jPanel3.add(getJButton2(), new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//			jPanel3.add(getJButton5(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//		}
//		return jPanel3;
//	}
//	
//	
//	
//	/**
//	* Auto-generated main method to display this 
//	* JPanel inside a new JFrame.
//	*/
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.getContentPane().add(new CopyOfDeskRecMainPanel());
//		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}
//	
//	private JButton getJButton5() {
//		if(jButton5 == null) {
//			jButton5 = new JButton();
//			jButton5.setText("save");
//			jButton5.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent evt) {
////					if(DeskRecMainPanel.this.captureWholeScreenOption.isSelected()) 
////						DeskRecMainPanel.this.captureArea = config.getWholeScreenArea();					
//					CopyOfDeskRecMainPanel.this.performSaveAction(config.DEFAULT_IMAGE_FORMAT);
//				    
//				}
//			});
//		}
//		return jButton5;
//	}
//
//	protected void performSaveAction(final String format) {
//		  // "Save" dialog:
//		 JFileChooser c = new JFileChooser();
//		 c.setDialogTitle("save as avi video...");
//		 c.setFileFilter(new FileFilter() {						
//			@Override
//			public String getDescription() {
//				return "avi files";
//			}						
//			@Override
//			public boolean accept(File f) {
//				String name = f.getName();
//				if(name.contains(".")) {
//					return FSUtils.getFileExtension(name).toLowerCase().equals(format);								 
//				}
//				return false;
//			}
//		});
//		 File videoOutput=null;
//	      int rVal = c.showSaveDialog(CopyOfDeskRecMainPanel.this);
//	      if (rVal == JFileChooser.APPROVE_OPTION) {
//	    	  videoOutput= c.getSelectedFile();
////	        dir.setText(c.getCurrentDirectory().toString());
//	      }
//	      if (rVal == JFileChooser.CANCEL_OPTION) {
//	    	  return;
//	      }
//		  if(!videoOutput.getName().contains(".")) { //user forgot extension
//			  videoOutput = new File(videoOutput.getAbsolutePath()+".avi");
//		  }
//	      System.out.println("video output : "+videoOutput.getAbsolutePath());
//	      
//	      
//	      config.createAvi(captureArea.width, captureArea.height, videoOutput);
//	      
//	}
//
//	public void outputConsoleAppendText(String str) {
////		statusLabel.setText(str);
//		consoleArea.append(str+"\n");
//		
//		
//	}
//	
//	private JPanel getJPanel4() {
//		if(jPanel4 == null) {
//			jPanel4 = new JPanel();
//			GridBagLayout jPanel4Layout = new GridBagLayout();
//			jPanel4Layout.columnWidths = new int[] {7};
//			jPanel4Layout.rowHeights = new int[] {18, 7};
//			jPanel4Layout.columnWeights = new double[] {0.1};
//			jPanel4Layout.rowWeights = new double[] {0.0, 0.1};
//			jPanel4.setLayout(jPanel4Layout);
//			jPanel4.add(getStatusLabel(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//			jPanel4.add(getJTextArea1(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//		}
//		return jPanel4;
//	}
//	
//	private JTextArea getJTextArea1() {
//		if(consoleArea == null) {
//			consoleArea = new JTextArea();
//			consoleArea.setText("");
//		}
//		return consoleArea;
//	}
//}
