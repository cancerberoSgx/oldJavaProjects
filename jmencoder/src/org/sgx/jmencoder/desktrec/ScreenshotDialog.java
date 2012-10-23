package org.sgx.jmencoder.desktrec;
import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.sgx.swing.utils.ImageUtils;


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
public class ScreenshotDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JButton jButton1;
	private JLabel jLabel3;
	private JTextField outputFileInput;
	private JButton jButton3;
	private JPanel jPanel5;
	private ButtonGroup buttonGroup1;
	private JButton jButton2;
	private JPanel jPanel4;
	private JLabel jLabel2;
	private JTextField delayInput;
	private JRadioButton delaySecondsRadioButton;
	private JRadioButton nowRadioButton;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private DeskRecConfig config;
	private DeskRecMainPanel mainPanel;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				JFrame frame = new JFrame();
//				ScreenshotDialog inst = new ScreenshotDialog(frame);
//				inst.setVisible(true);
//			}
//		});
//	}
	
	public ScreenshotDialog(DeskRecMainPanel deskRecMainPanel) {
		super((Frame) deskRecMainPanel.getTopLevelAncestor());
		this.mainPanel=deskRecMainPanel;		
		config = DeskRecConfig.getInstance(deskRecMainPanel);
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setTitle("Take Screenshot");
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.columnWidths = new int[] {7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1};
				thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
				thisLayout.rowHeights = new int[] {54, 40, 7};
				getContentPane().setLayout(thisLayout);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel1.setText("take screenshot:");
				}
				{
					jPanel1 = new JPanel();
					BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS);
					jPanel1.setLayout(jPanel1Layout);
					getContentPane().add(jPanel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanel2 = new JPanel();
						FlowLayout jPanel2Layout = new FlowLayout();
						jPanel2Layout.setAlignment(FlowLayout.LEFT);
						jPanel2.setLayout(jPanel2Layout);
						jPanel1.add(jPanel2);
						{
							nowRadioButton = new JRadioButton();
							jPanel2.add(nowRadioButton);
							nowRadioButton.setText("now");
							nowRadioButton.setSelected(true);
							getButtonGroup1().add(nowRadioButton);
						}
					}
					{
						jPanel3 = new JPanel();
						FlowLayout jPanel3Layout = new FlowLayout();
						jPanel3Layout.setAlignment(FlowLayout.LEFT);
						jPanel3.setLayout(jPanel3Layout);
						jPanel1.add(jPanel3);
						{
							delaySecondsRadioButton = new JRadioButton();
							jPanel3.add(delaySecondsRadioButton);
							delaySecondsRadioButton.setText("in ");
							getButtonGroup1().add(delaySecondsRadioButton);
						}
						{
							delayInput = new JTextField();
							jPanel3.add(delayInput);
							delayInput.setText("3");
							delayInput.setPreferredSize(new java.awt.Dimension(28, 22));
						}
						{
							jLabel2 = new JLabel();
							jPanel3.add(jLabel2);
							jLabel2.setText("seconds");
						}
					}
				}
				{
					jPanel4 = new JPanel();
					getContentPane().add(jPanel4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					getContentPane().add(getJLabel3(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					getContentPane().add(getJPanel5(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					{
						jButton1 = new JButton();
						jPanel4.add(jButton1);
						jButton1.setText("cancel");
						jButton1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("jButton1.actionPerformed, event="+evt);
								//TODO add your code for jButton1.actionPerformed
							}
						});
					}
					{
						jButton2 = new JButton();
						jPanel4.add(jButton2);
						jButton2.setText("accept");
						jButton2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								int secondsToWait = 0;
								if(delaySecondsRadioButton.isSelected())
									secondsToWait = Integer.parseInt(delayInput.getText());
								
									if(config.minimizeWindowOnRecording) {
										JFrame parentFrame = (JFrame) mainPanel.getTopLevelAncestor();										
										parentFrame.setState(JFrame.ICONIFIED);
										ScreenshotDialog.this.setVisible(false);									
									}
									
									new Timer().schedule(new TimerTask() {										
										@Override
										public void run() {
											try {
												ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(
														mainPanel.captureArea, config.DEFAULT_IMAGE_FORMAT, new File(outputFileInput.getText()));
											} catch (AWTException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}, secondsToWait*1000);
									
								
//								
//								//minimize this window and start recording

								
								
								//TODO add your code for jButton2.actionPerformed
							}
						});
					}
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}
	
	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("output file");
		}
		return jLabel3;
	}
	
	private JPanel getJPanel5() {
		if(jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.add(getOutputFileInput());
			jPanel5.add(getJButton3());
		}
		return jPanel5;
	}
	
	private JTextField getOutputFileInput() {
		if(outputFileInput == null) {
			outputFileInput = new JTextField();
			outputFileInput.setPreferredSize(new java.awt.Dimension(187, 22));
			outputFileInput.setText(config.getDefaultScreenshotFileName());
		}
		return outputFileInput;
	}
	
	private JButton getJButton3() {
		if(jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("...");
		}
		return jButton3;
	}

}
