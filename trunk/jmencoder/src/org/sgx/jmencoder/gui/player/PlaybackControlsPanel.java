package org.sgx.jmencoder.gui.player;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.FontFamily;
import org.sgx.j2s.model.events.EditorListener;
import org.sgx.jmencoder.ResourceManager;
import org.sgx.jmencoder.mplayer.MPlayerVideoModes;
import org.sgx.jmencoder.mplayer.executors.MPlayerCommandListener;
import org.sgx.jmencoder.mplayer.executors.MplayerExecutor;
import org.sgx.jmencoder.options.SubtitleFontOption;
import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.jmencoder.options.VideoReproductionOptions;
import org.sgx.swing.gui.editors.file.FileListSelector;
import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.swing.gui.editors.sliders.DiscreteSlider;
import org.sgx.swing.gui.fontChooser.FontChooserDialog;
import org.sgx.swing.gui.fontChooser.FontChooserListener;
import org.sgx.utils.WindowsUtils;

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
/**
 * this file is not readable by humans... open it with eclipse VE
 * 
 * 280508. TODO: un editor con sliders para :
 * 1 contrast -1
2 contrast 1
3 brightness -1
4 brightness 1
5 hue -1
6 hue 1
7 saturation -1
8 saturation 1
( balance -0.1          # adjust audio balance in favor of left
) balance +0.1          #                                  right

 * @author nati
 *
 */
public class PlaybackControlsPanel extends JPanel implements MPlayerCommandListener {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton playbt = null;
	private JButton pausebt = null;
	private JButton stopbt = null;
	private JButton nextBt = null;
	private JButton prevbt = null;
	private final MplayerExecutor mplayer = new MplayerExecutor();  //  @jve:decl-index=0:
	boolean executing=false;
	
	private FileListSelector listedFiles = null;
	private JButton fullscreenbt = null;
	private JPanel jPanel3 = null;
	private JButton subAtrazar = null;
	private JButton jButton = null;
	private JPanel audioPanel = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel7 = null;
	private JPanel jPanel8 = null;
	private JPanel jPanel81 = null;
	private JLabel jLabel = null;
	private JTextField subtitleDelayAmount = null;
	private JPanel jPanel5 = null;
	private JPanel jPanel6 = null;
	private FileSelector subtitleSelector = null;
	private JPanel jPanel9 = null;
	private JLabel jLabel1 = null;
	private JPanel jPanel11 = null;
	private JPanel jPanel10 = null;
	private JPanel jPanel12 = null;
	private JPanel playbackPanel = null;
	private JPanel videoPanel = null;
	private JPanel jPanel21 = null;
	private JButton jButton11 = null;
	private JButton jButton21 = null;
	private JTextField audioDelayEntry = null;
	private JPanel jPanel15 = null;
	private JButton jButton5 = null;
	private JButton jButton6 = null;
	private JButton jButton7 = null;
	private JButton jButton8 = null;
	private JButton jButton9 = null;
	private JPanel speedPanel = null;
	private JButton jButton10 = null;
	private JButton jButton12 = null;
	private JButton jButton13 = null;
	private JButton jButton14 = null;
	private JButton jButton15 = null;
	private JTextField speedSetEntry = null;
	private JPanel jPanel16 = null;
	private JPanel jPanel17 = null;
	private JLabel jLabel2 = null;
	private JButton jButton16 = null;
	private JButton jButton17 = null;
	private JTabbedPane jTabbedPane = null;
	private JButton jButton23 = null;
	protected SubtitleRecorder subtitleRecorder;
	private JPanel jPanel4 = null;
	private JSlider positionSlider = null;
	private JPanel jPanel18 = null;
	private JPanel jPanel19 = null;
	private JPanel videoEqPanel = null;
	private DiscreteSlider contrastsl = null;
	private Timer playPercentTimer;  //  @jve:decl-index=0:
	private boolean sliderIgnoreChange;
	private DiscreteSlider brightSlider = null;
	private DiscreteSlider hueSlider = null;
	private DiscreteSlider saturationsl = null;
	private DiscreteSlider gammasl = null;
	private JPanel videoOutputPanel = null;
	private JComboBox videoOutputCombo = null;
	private JLabel videoOutputDesc = null;

	private Font subFont;
	private JPanel jPanel22 = null;
	private JPanel jPanel23 = null;
	private JTabbedPane jTabbedPane1 = null;
	private JSlider volumeSlider = null;
	private JPanel jPanel20 = null;
	private JPanel jPanel26 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel41 = null;
	private JPanel jPanel27 = null;
	private JSlider aspectSlider = null;
	private JPanel aspectPanel = null;
	private JLabel jLabel42 = null;
	private JPanel jPanel28 = null;
//	private JButton jButton4 = null;
	private JPanel jPanel29 = null;
	private JButton jButton19 = null;
	public PlaybackControlsPanel() {
		super();
		initialize();
			
	}

	private void initialize() {
		this.setSize(766, 673);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getPlaybackPanel(), null);
		this.add(getJTabbedPane(), null);
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			BoxLayout jPanelLayout = new BoxLayout(jPanel, javax.swing.BoxLayout.Y_AXIS);
			jPanel.setLayout(jPanelLayout);
			jPanel.add(getListedFiles());
		}
		return jPanel;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS));
			jPanel1.add(getPlaybt(), null);
			jPanel1.add(getPausebt(), null);
			jPanel1.add(getStopbt(), null);
			jPanel1.add(getNextBt(), null);
			jPanel1.add(getPrevbt(), null);
//			jPanel1.add(getJButton4(), null);
		}
		return jPanel1;
	}

	private JButton getPlaybt() {
		if (playbt == null) {
			playbt = new JButton("play");
			playbt.setIcon(ResourceManager.getInstance().getIcon("play"));
			playbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
						doPlay();
						try {
							mplayer.enterCommand("use_master");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}//TODO: BORRAR
					}
				});
		}
		return playbt;
	}
	private VideoReproductionOptions getVideoReproductionOptions() {
		//video reproduction parameters
		File videoFile = null;
	
		SubtitleOption subOpts = getSubtitleOptions();
		
//		String font = 
		
		List<File> files = listedFiles.getSubSelection();
		
		if(files.size()==0) {
			files = listedFiles.getList();
			if(files.size()==0){//empty list. reproduce from dvd device
				return new VideoReproductionOptions(null,subOpts); 
			}
			videoFile = files.get(0);			
		}else {
			videoFile = files.get(0);
		}
		
		return new VideoReproductionOptions(videoFile, subOpts);
	}
	
	private SubtitleOption getSubtitleOptions() {
		String subvfontStr = subFont==null?null:WindowsUtils.fontNameFile.get(subFont.getName());
		String substr = subtitleSelector.getSelection();
		File sub=null;
		if(substr!=null&&!substr.equals(""))
			sub=new File(substr);
		return new SubtitleOption(sub, new SubtitleFontOption(new FontFamily(subvfontStr), "1", Color.RED));
	}
			
	private JButton getPausebt() {
		if (pausebt == null) {
			pausebt = new JButton("pause");
			pausebt.setIcon(ResourceManager.getInstance().getIcon("pause"));
			pausebt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(mplayer.isExecuting()){
						try {
							mplayer.enterCommand("pause");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}			
				}
			});
		}
		return pausebt;
	}

	private JButton getStopbt() {
		if (stopbt == null) {
			stopbt = new JButton("stop");
			stopbt.setIcon(ResourceManager.getInstance().getIcon("stop"));			
			stopbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					doStop();
				}
			});
		}
		return stopbt;
	}
	private JButton getNextBt() {
		if (nextBt == null) {
			nextBt = new JButton("next");
			nextBt.setIcon(ResourceManager.getInstance().getIcon("next"));
			
			nextBt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					advanceVideo(1);
				}
			});
		}
		return nextBt;
	}

	private JButton getPrevbt() {
		if (prevbt == null) {
			prevbt = new JButton("prev");
			prevbt.setIcon(ResourceManager.getInstance().getIcon("previous"));
			
			prevbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					advanceVideo(-1);
				}
			});
//			prevbt.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//
//					int actual = listedFiles.getSubSelectedIndex();
//					listedFiles.setSubSelectedIndex(actual-1);
//					List<File> files = listedFiles.getSubSelection();
//					if(files.size()==1) {
//						File f = files.get(0);
//						try {
//							try {
//								mplayer.enterCommand("quit");
//								Thread.sleep(400);
//							} catch(Exception ex) {}
//								mplayer.openFile(f, null);//..execute(cmd, l)
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//					}
//				}
//			});
		}
		return prevbt;
	}

	private FileListSelector getListedFiles() {
		if (listedFiles == null) {
			listedFiles = new FileListSelector();
			listedFiles.getJList().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					JList list = (JList) evt.getSource();
					if (evt.getClickCount() > 1) {
						doPlay();
//						int index = list.locationToIndex(evt.getPoint());
//						Object f = list.getModel().getElementAt(index);
//						List<File> selection = listedFiles.getSubSelection();
//						System.out.println(f);
					}
				}
			});
			;
			listedFiles.setMinimumSize(new Dimension(100, 200));
		}
		return listedFiles;
	}

	

    /**
	 * This method initializes fullscreenbt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFullscreenbt() {
		if (fullscreenbt == null) {
			fullscreenbt = new JButton("Full Screen");
			fullscreenbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
//						//mplayer.enterCommand("vo_fullscreen");
//						//ta salao... lo comentamos
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
			});
		}
		return fullscreenbt;
	}

	
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			BoxLayout l = new BoxLayout(getJPanel3(), BoxLayout.Y_AXIS);
			
			jPanel3.setLayout(l);
			jPanel3.setBorder(BorderFactory.createTitledBorder("subtitles"));
			jPanel3.add(getJPanel9(), null);
			jPanel3.add(getJPanel6(), null);
			jPanel3.add(getJPanel23(), null);
		}
		return jPanel3;
	}

	private JButton getSubAtrazar() {
		if (subAtrazar == null) {
			subAtrazar = new JButton("delay");
			subAtrazar.setText("<<");
			subAtrazar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_delay -0.1");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return subAtrazar;
	}


	private JButton getJButton() {
		if (jButton == null) {
			jButton =  new JButton("advance");
			jButton.setText(">>");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_delay +0.1");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton;
	}

	
	private JPanel getAudioPanel() {
		if (audioPanel == null) {
			audioPanel = new JPanel();
			audioPanel.setLayout(new BoxLayout(getAudioPanel(), BoxLayout.X_AXIS));
			audioPanel.setBorder(BorderFactory.createTitledBorder("audio"));
			audioPanel.add(getJButton3(), null);
			audioPanel.add(getJPanel2(), null);
			audioPanel.add(getJPanel21(), null);
		}
		return audioPanel;
	}

	
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton("delay");
			jButton1.setText("-");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("volume -1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton1;
	}

	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton("advance");
			jButton2.setText("+");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("volume 1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton2;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton("switch");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("switch_audio");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton3;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.X_AXIS));
			jPanel2.setBorder(BorderFactory.createTitledBorder("volume" +
					""));
			jPanel2.add(getJButton1(), null);
			jPanel2.add(getJButton2(), null);
		}
		return jPanel2;
	}

	
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(new BoxLayout(getJPanel7(), BoxLayout.Y_AXIS));
			jPanel7.setBorder(BorderFactory.createTitledBorder("delay"));
	
			jPanel7.add(getJPanel8(), null);
			jPanel7.add(getJPanel81(), null);
			jPanel7.add(getJPanel22(), null);
		}
		return jPanel7;
	}

	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jPanel8 = new JPanel();
			jPanel8.setLayout(new BoxLayout(getJPanel8(), BoxLayout.X_AXIS));
			jPanel8.add(getSubAtrazar(), null);
			jPanel8.add(getJButton(), null);
			jPanel8.add(getJPanel12(), null);
		}
		return jPanel8;
	}

	private JPanel getJPanel81() {
		if (jPanel81 == null) {
			jLabel = new JLabel();
			jLabel.setText("amount:");
			jPanel81 = new JPanel();
			jPanel81.setLayout(new BoxLayout(getJPanel81(), BoxLayout.X_AXIS));
			jPanel81.add(jLabel, null);
			jPanel81.add(getSubtitleDelayAmount(), null);
			jPanel81.add(getJPanel10(), null);
		}
		return jPanel81;
	}

	private JTextField getSubtitleDelayAmount() {
		if (subtitleDelayAmount == null) {
			subtitleDelayAmount = new JTextField();
		}
		return subtitleDelayAmount;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new BoxLayout(getJPanel5(), BoxLayout.Y_AXIS));
			jPanel5.setBorder(BorderFactory.createTitledBorder("style"));
			jPanel5.add(getJPanel11(), null);
		}
		return jPanel5;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setLayout(new BoxLayout(getJPanel6(), BoxLayout.Y_AXIS));
		}
		return jPanel6;
	}
	private FileSelector getSubtitleSelector() {
		if (subtitleSelector == null) {
			subtitleSelector = new FileSelector();
			subtitleSelector.setMaximumSize(new Dimension(9000,50));
		}
		return subtitleSelector;
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("drop your subtitle files here:");
			jPanel9 = new JPanel();
			jPanel9.setLayout(new BoxLayout(getJPanel9(), BoxLayout.Y_AXIS));
			jPanel9.add(jLabel1, null);
			jPanel9.add(getSubtitleSelector(), null);
			
		}
		return jPanel9;
	}

	private JPanel getJPanel11() {
		if (jPanel11 == null) {
			jPanel11 = new JPanel();
			jPanel11.setLayout(new BoxLayout(getJPanel11(), BoxLayout.X_AXIS));
			jPanel11.add(getJButton23(), null);
		}
		return jPanel11;
	}

	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			jPanel10 = new JPanel();
			jPanel10.setLayout(new GridBagLayout());
		}
		return jPanel10;
	}

	private JPanel getJPanel12() {
		if (jPanel12 == null) {
			jPanel12 = new JPanel();
			jPanel12.setLayout(new GridBagLayout());
		}
		return jPanel12;
	}

	private JPanel getPlaybackPanel() {
		if (playbackPanel == null) {
			playbackPanel = new JPanel();
			playbackPanel.setLayout(new BoxLayout(getPlaybackPanel(), BoxLayout.Y_AXIS));
			playbackPanel.setMaximumSize(new Dimension(900, 300));
			playbackPanel.add(getJPanel(), null);
			playbackPanel.add(getJPanel1(), null);
			playbackPanel.add(getJPanel4(), null);
		}
		return playbackPanel;
	}

	private JPanel getVideoPanel() {
		if (videoPanel == null) {
			videoPanel = new JPanel();
			videoPanel.setLayout(new BoxLayout(getVideoPanel(), BoxLayout.Y_AXIS));
//			videoPanel.setBorder(BorderFactory.createTitledBorder("video"));
			videoPanel.add(getJPanel28(), null);
			videoPanel.add(getAspectPanel(), null);
		}
		return videoPanel;
	}

	private JPanel getJPanel21() {
		if (jPanel21 == null) {
			jPanel21 = new JPanel();
			jPanel21.setLayout(new BoxLayout(getJPanel21(), BoxLayout.X_AXIS));
			jPanel21.setBorder(BorderFactory.createTitledBorder("delay"));
			jPanel21.add(getJButton11(), null);
			jPanel21.add(getJButton21(), null);
			jPanel21.add(getAudioDelayEntry(), null);
		}
		return jPanel21;
	}

	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton("delay");
			jButton11.setText("-");
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("audio_delay -0.100");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		return jButton11;
	}

	private JButton getJButton21() {
		if (jButton21 == null) {
			jButton21 = new JButton("advance");
			jButton21.setText("+");
			jButton21.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("audio_delay 0.100");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton21;
	}

	private JTextField getAudioDelayEntry() {
		if (audioDelayEntry == null) {
			audioDelayEntry = new JTextField();
			audioDelayEntry.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return audioDelayEntry;
	}

	private JPanel getJPanel15() {
		if (jPanel15 == null) {
			jPanel15 = new JPanel();
			jPanel15.setLayout(new BoxLayout(getJPanel15(), BoxLayout.X_AXIS));
			jPanel15.setBorder(BorderFactory.createTitledBorder("bounds"));
			jPanel15.add(getJButton6(), null);
			jPanel15.add(getJButton5(), null);
			jPanel15.add(getJButton7(), null);
			jPanel15.add(getJButton8(), null);
		}
		return jPanel15;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("down");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_pos +1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton5;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("up");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_pos -1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton6;
	}

	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("+");
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_scale +0.1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton7;
	}

	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setText("-");
			jButton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_scale -0.1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton8;
	}

	private JButton getJButton9() {
		if (jButton9 == null) {
			jButton9 = new JButton();
			jButton9.setText("on top");
			jButton9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("vo_ontop");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton9;
	}
	private JPanel getSpeedPanel() {
		if (speedPanel == null) {
			speedPanel = new JPanel();
			speedPanel.setLayout(new BoxLayout(getSpeedPanel(), BoxLayout.Y_AXIS));
			speedPanel.add(getJPanel16(), null);
			speedPanel.setBorder(BorderFactory.createTitledBorder("speed"));
			speedPanel.add(getJPanel27(), null);
		}
		return speedPanel;
	}

	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.setText("<<");
			jButton10.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("speed_mult 0.5");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton10;
	}

	private JButton getJButton12() {
		if (jButton12 == null) {
			jButton12 = new JButton();
			jButton12.setText("<");
			jButton12.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("speed_mult 0.9091");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton12;
	}
	private JButton getJButton13() {
		if (jButton13 == null) {
			jButton13 = new JButton();
			jButton13.setText("|");
			jButton13.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("speed_set 1.0");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			jButton13.setText("reset to normal");
		}
		return jButton13;
	}

	private JButton getJButton14() {
		if (jButton14 == null) {
			jButton14 = new JButton();
			jButton14.setText(">");
			jButton14.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("speed_mult 1.1");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton14;
	}

	private JButton getJButton15() {
		if (jButton15 == null) {
			jButton15 = new JButton();
			jButton15.setText(">>");
			jButton15.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("speed_mult 2.0");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton15;
	}

	private JTextField getSpeedSetEntry() {
		if (speedSetEntry == null) {
			speedSetEntry = new JTextField();
			speedSetEntry.setPreferredSize(new Dimension(50,20));
//			speedSetEntry.setMinimumSize(minimumSize)nimumSize(minimumSize)munSize(new Dimension(50,20));
			speedSetEntry.setToolTipText("a value between 0 and 5. less than 1 is rewing");
			speedSetEntry.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return speedSetEntry;
	}

	private JPanel getJPanel16() {
		if (jPanel16 == null) {
			jPanel16 = new JPanel();
			jPanel16.setLayout(new BoxLayout(getJPanel16(), BoxLayout.X_AXIS));
			jPanel16.setPreferredSize(new Dimension(50, 52));
			jPanel16.add(getJButton10(), null);
			jPanel16.add(getJButton12(), null);
			jPanel16.add(getJButton13(), null);
			jPanel16.add(getJButton14(), null);
			jPanel16.add(getJButton15(), null);
			jPanel16.add(getJPanel19(), null);
			jPanel16.add(getJPanel17(), null);
			jPanel16.add(getJPanel18(), null);
			jPanel16.setBorder(BorderFactory.createTitledBorder("speed"));
		}
		return jPanel16;
	}

	private JPanel getJPanel17() {
		if (jPanel17 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("set speed to:");
			jPanel17 = new JPanel();
			jPanel17.setLayout(new BoxLayout(getJPanel17(), BoxLayout.X_AXIS));
			jPanel17.add(jLabel2, null);
			jPanel17.add(getSpeedSetEntry(), null);
		}
		return jPanel17;
	}

	private JButton getJButton16() {
		if (jButton16 == null) {
			jButton16 = new JButton();
			jButton16.setText("screenshot");
			jButton16.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				}
			});
		}
		return jButton16;
	}

	private JButton getJButton17() {
		if (jButton17 == null) {
			jButton17 = new JButton();
			jButton17.setText("border");
			jButton17.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("vo_border");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		return jButton17;
	}

	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();

			jTabbedPane.addTab("video", null, getJTabbedPane1(), null);
			jTabbedPane.addTab("subtitles", null, getJPanel3(), null);
			jTabbedPane.addTab("audio", null, getAudioPanel(), null);
			jTabbedPane.setSelectedIndex(0);
		}
		return jTabbedPane;
	}
	private JButton getJButton23() {
		if (jButton23 == null) {
			jButton23 = new JButton();
			jButton23.setText("select font");
			jButton23.addActionListener(new java.awt.event.ActionListener() {
				private FontChooserDialog fc=null;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(fc==null)
						fc = new FontChooserDialog(PlaybackControlsPanel.this.getFrame());
					fc.setVisible(true);
					fc.addFontChooserListener(new FontChooserListener() {
						
						public void notifyFontChoosed(Font f) {
							 subFont = f;
							fc.setVisible(false);
							
							//TODO: no hay ning�n comando slave para cambiar la fuente
							//solo ghe conseguido meter otra fuente reiniciando el video con el parametro 
							//	(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont.getName())+"\" "))+
							//" -slang es -subcp latin1 "+

//							TODO: preguntar : JOptionPane.showConfirmDialog(PlaybackControlsPanel.this, "this will restart the movie, are you sure?")
							
							
							
//							" -subfont-text-scale "+subFontScaleB+
//							(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont.getName())+"\" "))+
//							" -slang es -subcp latin1 "+
							
							
							jButton23.setFont(f);
						}
						public void notifyFontChooserCanceled() {
						}						
					});
				}
			});
		}
		return jButton23;
	}

	protected JFrame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new BoxLayout(getJPanel4(), BoxLayout.X_AXIS));
			jPanel4.add(getJPanel26());
			jPanel4.add(getJPanel20());
		}
		return jPanel4;
	}

	private JSlider getPositionSlider() {
		if (positionSlider == null) {
			positionSlider = new JSlider();
			positionSlider.setPreferredSize(new Dimension(300, 45));
			positionSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					int val = positionSlider.getValue();
					try {
						if(!PlaybackControlsPanel.this.sliderIgnoreChange)
							mplayer.enterCommand("set_property percent_pos "+ val);
					} catch (IOException e1) {
						e1.printStackTrace();
					}					
				}
			});
			//instalamos un observer que avance la barra de reproducci�n
			playPercentTimer = new Timer();
			playPercentTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						if(mplayer!=null && mplayer.isExecuting()){
							mplayer.enterCommand("get_property percent_pos");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}				
			}, 500, 500);
		}
		return positionSlider;
	}

	private JPanel getJPanel18() {
		if (jPanel18 == null) {
			jPanel18 = new JPanel();
			jPanel18.setLayout(new GridBagLayout());
		}
		return jPanel18;
	}

	private JPanel getJPanel19() {
		if (jPanel19 == null) {
			jPanel19 = new JPanel();
			jPanel19.setLayout(new GridBagLayout());
		}
		return jPanel19;
	}

	private JPanel getVideoEqPanel() {
		if (videoEqPanel == null) {
			videoEqPanel = new JPanel();
			videoEqPanel.setLayout(new BoxLayout(videoEqPanel, BoxLayout.Y_AXIS));
			videoEqPanel.add(getContrastsl(), null);
			videoEqPanel.add(getBrightSlider(), null);
			videoEqPanel.add(getHueSlider(), null);
			videoEqPanel.add(getSaturationsl(), null);
			videoEqPanel.add(getGammasl(), null);
		}
		return videoEqPanel;
	}

	private DiscreteSlider getContrastsl() {
		if (contrastsl == null) {
			contrastsl= new DiscreteSlider("contrast", -100, 100, 0);
			contrastsl.addEventListener(new EditorListener<Integer>(){
				@Override
				public void notifyValueChanged(Integer val) {					
					try {
						mplayer.enterCommand("set_property contrast "+ val);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});
		}
		return contrastsl;
	}

	private DiscreteSlider getBrightSlider() {
		if (brightSlider == null) {
			brightSlider = new DiscreteSlider("brightness", -100, 100, 0);
			brightSlider.addEventListener(new EditorListener<Integer>(){
				@Override
				public void notifyValueChanged(Integer val) {					
					try {
						mplayer.enterCommand("set_property brightness "+ val);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});
		}
		return brightSlider;
	}

	private DiscreteSlider getHueSlider() {
		if (hueSlider == null) {
			hueSlider = new DiscreteSlider("hue", -100, 100, 0);
			hueSlider.addEventListener(new EditorListener<Integer>(){
				@Override
				public void notifyValueChanged(Integer val) {					
					try {
						mplayer.enterCommand("set_property hue "+ val);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});
		}
		return hueSlider;
	}

	private DiscreteSlider getSaturationsl() {
		if (saturationsl == null) {
			saturationsl = new DiscreteSlider("saturation", -100, 100, 0);
			saturationsl.addEventListener(new EditorListener<Integer>(){
				@Override
				public void notifyValueChanged(Integer val) {					
					try {
						mplayer.enterCommand("set_property saturation "+ val);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});
		}
		return saturationsl;
	}

	private DiscreteSlider getGammasl() {
		if (gammasl == null) {
			gammasl = new DiscreteSlider("gamma", -100, 100, 0);
			gammasl.addEventListener(new EditorListener<Integer>(){
				@Override
				public void notifyValueChanged(Integer val) {					
					try {
						mplayer.enterCommand("set_property gamma "+ val);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}});
		}
		return gammasl;
	}

	private JPanel getVideoOutputPanel() {
		if (videoOutputPanel == null) {
			videoOutputDesc = new JLabel();
			String s = (String)getVideoOutputCombo().getSelectedItem(),
				desc = MPlayerVideoModes.getVideoModeDescriptionOf(s);
			
//			Object desc = MPlayerVideoModes.getVideoModeDescriptionOf((String) getVideoOutputCombo().getSelectedItem());
			videoOutputDesc.setText(desc);
			videoOutputPanel = new JPanel();
			videoOutputPanel.setLayout(new BoxLayout(getVideoOutputPanel(), BoxLayout.Y_AXIS));
			videoOutputPanel.setBorder(BorderFactory.createTitledBorder("output"));
			videoOutputPanel.add(getVideoOutputCombo(), null);
			videoOutputPanel.add(videoOutputDesc, null);
		}
		return videoOutputPanel;
	}

	private JComboBox getVideoOutputCombo() {
		if (videoOutputCombo == null) {
			videoOutputCombo = new JComboBox();
			videoOutputCombo.setModel(new DefaultComboBoxModel(MPlayerVideoModes.getVideoModesIdsFor(MPlayerVideoModes.OS_WIN)));
			videoOutputCombo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String s = (String)videoOutputCombo.getSelectedItem(),
						desc = MPlayerVideoModes.getVideoModeDescriptionOf(s);
					videoOutputDesc.setText(s+" : "+desc);
					
				}
			});
		}
		return videoOutputCombo;
	}

	private JPanel getJPanel22() {
		if (jPanel22 == null) {
			jPanel22 = new JPanel();
			jPanel22.setLayout(new GridBagLayout());
		}
		return jPanel22;
	}

	private JPanel getJPanel23() {
		if (jPanel23 == null) {
			jPanel23 = new JPanel();
			jPanel23.setLayout(new FlowLayout());
			jPanel23.add(getJPanel7(), null);
			jPanel23.add(getJPanel5(), null);
			jPanel23.add(getJPanel15(), null);
			jPanel23.add(getJPanel29(), null);
		}
		return jPanel23;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.addTab("screen", null, getVideoPanel(), null);
			jTabbedPane1.addTab("speed", null, getSpeedPanel(), null);
			jTabbedPane1.addTab("eq", null, getVideoEqPanel(), null);
			jTabbedPane1.addTab("output", null, getVideoOutputPanel(), null);
		}
		return jTabbedPane1;
	}

	private JSlider getVolumeSlider() {
		if (volumeSlider == null) {
			volumeSlider = new JSlider();
			volumeSlider.setPreferredSize(new Dimension(120, 45));
			volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					try {
//							mplayer.enterCommand("set_property volume "+ volumeSlider.getValue());
						

						mplayer.enterCommand("volume "+ volumeSlider.getValue()+" 1");
						System.out.println(volumeSlider.getValue());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return volumeSlider;
	}

	private JPanel getJPanel20() {
		if (jPanel20 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("volume");
			jPanel20 = new JPanel();
			jPanel20.setLayout(new BoxLayout(getJPanel20(), BoxLayout.Y_AXIS));
			jPanel20.add(getVolumeSlider(), null);
			jPanel20.add(jLabel3, null);
		}
		return jPanel20;
	}

	private JPanel getJPanel26() {
		if (jPanel26 == null) {
			jLabel41 = new JLabel();
			jLabel41.setText("progress" +
					"");
			jPanel26 = new JPanel();
			jPanel26.setLayout(new BoxLayout(getJPanel26(), BoxLayout.Y_AXIS));
			jPanel26.add(getPositionSlider(), null);
			jPanel26.add(jLabel41, null);
		}
		return jPanel26;
	}

	private JPanel getJPanel27() {
		if (jPanel27 == null) {
			jPanel27 = new JPanel();
		}
		return jPanel27;
	}

	private JSlider getAspectSlider() {
		if (aspectSlider == null) {
			aspectSlider = new JSlider();
			aspectSlider.setPreferredSize(new Dimension(100, 16));
			aspectSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					int x = aspectSlider.getValue();
					double r = ((double)x)/100.0 + 1.0;
					try {
						mplayer.enterCommand("switch_ratio "+  r);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return aspectSlider;
	}

	private JPanel getAspectPanel() {
		if (aspectPanel == null) {
			jLabel42 = new JLabel();
			jLabel42.setText("aspect ration (0, 1)");
			aspectPanel = new JPanel();
			aspectPanel.setLayout(new BoxLayout(getAspectPanel(), BoxLayout.Y_AXIS));
			aspectPanel.add(jLabel42, null);
			aspectPanel.add(getAspectSlider(), null);
		}
		return aspectPanel;
	}

	private JPanel getJPanel28() {
		if (jPanel28 == null) {
			jPanel28 = new JPanel();
			jPanel28.setLayout(new BoxLayout(getJPanel28(), BoxLayout.X_AXIS));
			jPanel28.add(getFullscreenbt(), null);
			jPanel28.add(getJButton9(), null);
			jPanel28.add(getJButton16(), null);
			jPanel28.add(getJButton17(), null);
		}
		return jPanel28;
	}

//	private JButton getJButton4() {
//		if (jButton4 == null) {
//			jButton4 = new JButton("dvd");
//			jButton4.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
//						try {
//							mplayer.enterCommand("quit");
//							Thread.sleep(100);
//						} catch(Exception ex) {}
//						mplayer.openDVD(getVideoReproductionOptions());//..execute(cmd, l)
//						mplayer.addMplayerCommandListener(PlaybackControlsPanel.this);
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			});
//		}
//		return jButton4;
//	}

	private JPanel getJPanel29() {
		if (jPanel29 == null) {
			jPanel29 = new JPanel();
			jPanel29.setLayout(new BoxLayout(getJPanel29(), BoxLayout.X_AXIS));
			jPanel29.setBorder(BorderFactory.createTitledBorder("language"));
			jPanel29.add(getJButton19(), null);
		}
		return jPanel29;
	}


	private JButton getJButton19() {
		if (jButton19 == null) {
			jButton19 = new JButton();
			jButton19.setText("cycle");
			jButton19.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						mplayer.enterCommand("sub_select");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton19;
	}

//	public static void main(String[] args) {    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//    private static void createAndShowGUI() {        
//      JFrame.setDefaultLookAndFeelDecorated(true);
//      JFrame frame = new JFrame("SimpleTableDemo");
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////      frame.setSize(new Dimension(664, 716));
//      PlaybackControlsPanel newContentPane = new PlaybackControlsPanel();
////      frame.setPreferredSize(newContentPane.getMinimumSize());
//      newContentPane.setOpaque(true); 
//      frame.setContentPane(newContentPane);
//      frame.pack();
//      frame.setVisible(true);
//    }
	public void notifyCommandOutput(String commandId, String value) {
		if(commandId.equals(MPlayerVideoModes.PERCENT_POS)) {
			PlaybackControlsPanel.this.sliderIgnoreChange=true;
			positionSlider.setValue(Integer.parseInt(value));
			PlaybackControlsPanel.this.sliderIgnoreChange=false;			
			try{
				if(Integer.parseInt(value)==100) {
					advanceVideo(1);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public void playListClear() {
		listedFiles.clearList();
	}
	public void playListAdd(File[] args) {
		listedFiles.addSelection(args);
	}

	public void doStop() {
		try {
			mplayer.enterCommand("quit");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void doPlay() {
		try {
			//kill and open again
			try {
				mplayer.enterCommand("quit");
				Thread.sleep(100);
			} catch(Exception ex) {}
			mplayer.playVideo(getVideoReproductionOptions());
			mplayer.addMplayerCommandListener(PlaybackControlsPanel.this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void advanceVideo(int i) {
		int si = listedFiles.getSubSelectedIndex();
		listedFiles.setSubSelectedIndex(si+i);
		doPlay();		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
