package org.sgx.jmencoder.streamplayer.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import org.sgx.jmencoder.ResourceManager;
import org.sgx.jmencoder.mplayer.executors.MplayerExecutor;
import org.sgx.jmencoder.streamplayer.IOListener;
import org.sgx.jmencoder.streamplayer.StreamPlayerConfig;
import org.sgx.jmencoder.streamplayer.model.StreamElement;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;

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
public class PlaybackPanel extends javax.swing.JPanel implements IOListener {
	private PlayListPanel playlistPanel;
	private JLabel statusLabel;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JTextArea mplayerOutput;
	private JButton jButton1;
	private JLabel jLabel2;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JLabel jLabel1;
	private JButton jButton3;
	private JSlider volume;
	private JPanel jPanel1;
	private JButton jButton2;
	protected StreamPlayerConfig config;
	private boolean recordingStatus; 

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PlaybackPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public PlaybackPanel() {
		super();
		
		config = StreamPlayerConfig.getInstance();
		config.setIOListener(this);
		initGUI();
	}
	
	public PlayListPanel getPlaylistPanel() {
		return playlistPanel;
	}

	public void setPlaylistPanel(PlayListPanel playlistPanel) {
		this.playlistPanel = playlistPanel;
	}

	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setPreferredSize(new Dimension(400, 300));
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {88, 64, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.0, 0.1};
				jPanel1Layout.rowHeights = new int[] {52, 7};
				jPanel1Layout.columnWeights = new double[] {0.1};
				jPanel1Layout.columnWidths = new int[] {7};
				jPanel1.setLayout(jPanel1Layout);
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					jPanel4 = new JPanel();
					jPanel1.add(jPanel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
					{
						jButton1 = new JButton();
						jPanel4.add(jButton1);
						jButton1.setText("play");
						jButton1.setIcon(ResourceManager.getInstance().getIcon("play"));
						jButton1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StreamElement selEl = (StreamElement)playlistPanel.getSelectedNode().getUserObject();
								if(selEl instanceof StreamEndpoint) {
									try {
										config.doPlay((StreamEndpoint) selEl);
										statusLabel.setText("status: playing: "+((StreamEndpoint) selEl).getName());
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}							
							}
						});										
					}
					{
						jButton2 = new JButton();
						jPanel4.add(jButton2);
						jButton2.setText("stop");
						jButton2.setIcon(ResourceManager.getInstance().getIcon("stop"));
						jButton2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try {
									config.enterCommand("quit");
									statusLabel.setText("status: iddle");
									if(recordingStatus) {
										recordingStatus=false;
										config.exit();
										// "Save" dialog:
											JFileChooser c = new JFileChooser();
										c.setDialogTitle("save as...");
										c.setFileFilter(new FileFilter() {						
											@Override
											public String getDescription() {
												return "mp3 files";
											}						
											@Override
											public boolean accept(File f) {
												return true;
											}
										});
										File videoOutput=null;
										int rVal = c.showSaveDialog(PlaybackPanel.this);
										if (rVal == JFileChooser.APPROVE_OPTION) {
											videoOutput= c.getSelectedFile();
										}
										if (rVal == JFileChooser.CANCEL_OPTION) {
											return;
										}
										if(!videoOutput.getName().contains(".")) { //user forgot extension
											videoOutput = new File(videoOutput.getAbsolutePath()+".mp3");
										}
										System.out.println("video output : "+videoOutput.getAbsolutePath());
										
										
										
										config.doSaveRecordedAsMp3(videoOutput, new ExecutorListener() {									
											@Override
											public void notifyOuput(String str) {System.out.println("out: "+str);}
											@Override
											public void notifyMovieHeader(String movieHeader) {}										
											@Override
											public void notifyError(String str) {System.out.println("err: "+str);}										
											@Override
											public void notifyEnd(EndExecutionInformation endExecutionInformation) {
												JOptionPane.showMessageDialog(PlaybackPanel.this, "end recording");
											}
										});
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
					{
						jButton3 = new JButton();
						jPanel4.add(jButton3);
						jButton3.setText("record");
						jButton3.setIcon(ResourceManager.getInstance().getIcon("record"));
						jButton3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StreamElement endpoint = (StreamElement)playlistPanel.getSelectedNode().getUserObject();
								try {
									config.doQuit();
									config.doRecord((StreamEndpoint) endpoint);
									setRecordingStatus(true);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								//record
								//						      try {
									//config.recordStream
									//									String cmd = ""
									//									executor=MencoderExecutorImpl.execute(cmd, executorListener);
									//									String volumeCommand = "volume "+ volume.getValue()+" 1";
									//									mplayer.enterCommand(volumeCommand);
									//								} catch (IOException e1) {
										//									e1.printStackTrace();
										//								}
							}
						});
					}
				}
				{
					jPanel5 = new JPanel();
					BoxLayout jPanel5Layout = new BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS);
					jPanel5.setLayout(jPanel5Layout);
					jPanel1.add(jPanel5, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					statusLabel = new JLabel();
					jPanel1.add(statusLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					statusLabel.setText("status: ");
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				{
					jLabel1 = new JLabel();
					jPanel2.add(jLabel1);
					jLabel1.setText("volume: ");
				}
				{
					volume = new JSlider();
					jPanel2.add(volume);
					volume.addChangeListener(new ChangeListener() {					
						@Override
						public void stateChanged(ChangeEvent e) {
							try {
								String volumeCommand = "volume "+ volume.getValue()+" 1";
								//							System.out.println(volumeCommand);
								config.enterCommand(volumeCommand);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}						
					});
				}
			}
			{
				jPanel3 = new JPanel();
				BoxLayout jPanel3Layout = new BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS);
				this.add(jPanel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel3.setLayout(jPanel3Layout);
				{
					jLabel2 = new JLabel();
					jPanel3.add(jLabel2);
					jLabel2.setText("mplayer output: ");
				}
				{
					mplayerOutput = new JTextArea();
					jPanel3.add(mplayerOutput);
					mplayerOutput.setText("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void setRecordingStatus(boolean b) {
		recordingStatus=b;
		//TODO: (des)habilitar los botones != stop
	}

	public MplayerExecutor getMplayer() {
		return config;
	}

	@Override
	public void notifyErr(String s) {
		mplayerOutput.append("ERR: "+s+"\n");
		if(mplayerOutput.getText().length()>900)
			mplayerOutput.setText("");
	}

	@Override
	public void notifyOut(String s) {
//		mplayerOutput.append("OUT: "+s+"\n");
//		mplayerOutput.setCaretPosition(mplayerOutput.getCaretPosition()+s.length());
//		if(mplayerOutput.getText().length()>900)
//			mplayerOutput.setText("");
	}

	@Override
	public void notifyEnd() {
		statusLabel.setText("status: iddle");
	}

}
