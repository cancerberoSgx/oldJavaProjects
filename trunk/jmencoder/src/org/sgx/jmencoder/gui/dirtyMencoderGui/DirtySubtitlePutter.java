package org.sgx.jmencoder.gui.dirtyMencoderGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sgx.jmencoder.mplayer.Format;
import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
//import org.sgx.jmencoder.trash.Console;
import org.sgx.swing.gui.fontChooser.FontChooserDialog;
import org.sgx.swing.gui.fontChooser.FontChooserListener;
import org.sgx.utils.FSUtils;
import org.sgx.utils.WindowsUtils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;



public class DirtySubtitlePutter extends JFrame {
	private Executor executor, playerExecutor;
	private Font subFont;
	private Color subColor = Color.black;
	private Timer timer;

	private MpegOptionesPanel mpegOptPanel=null;
	private static final long serialVersionUID = 1L;

	private static final String defaultBitrate = "800";  //  @jve:decl-index=0:

	private static final String defaultSubDelay = "0";  //  @jve:decl-index=0:

	private String defaultSubFontScale="5";  //  @jve:decl-index=0:
	
	private static final String defaultAudioDelay = "0";

	protected static final String PROGRAM_MENCODER = "mencoder";  //  @jve:decl-index=0:
	protected static final String PROGRAM_MPLAYER = "mplayer";  //  @jve:decl-index=0:
	
	/**container format: -of */
	private static final String[] outputContainers = {Format.AVI, Format.MPEG, Format.MPEG_4};
	static int defaultOutputContainer = 1;

	private Object[] outAudioFormats = {Format.MP3};

	private JPanel jContentPane = null;

	private InputPanel inputPanel = null;

	private JPanel jPanel1 = null;

	private JButton startbt = null;

	private JButton stop = null;

	private JPanel jPanel = null;

	private JPanel jPanel22 = null;

	private JLabel jLabel2 = null;

	private JTextField subDelay = null;

	private JPanel jPanel23 = null;

	private JLabel jLabel3 = null;

	private JTextField audioDelay = null;

	private JButton playbt = null;

	private JPanel jPanel211 = null;

	private JLabel jLabel11 = null;

	private JComboBox audioFormat = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JLabel jLabel4 = null;

	private JTextField subVPos = null;


	private JButton helpMplayerBt = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenuItem aboutmenuitem = null;

	private JMenuItem jMenuItem = null;

	private JPanel jPanel5 = null;

	private JPanel consolePanel = null;

//	private Console console = null;
	private JTextField outputText = null;
	private JPanel jPanel6 = null;
	private JLabel subFontLabel = null;
	private JButton jButton = null;
	private JPanel jPanel7 = null;
	private JPanel jPanel8 = null;
	private JCheckBox useDefaultSubFontCheck = null;
	private JLabel jLabel5 = null;
	private JPanel jPanel41 = null;
	private JLabel jLabel41 = null;
	private JTextField subVPos1 = null;
	private String defaultSubWidth="95";  //  @jve:decl-index=0:
	private JPanel jPanel411 = null;
	private JLabel jLabel411 = null;
	private JTextField subVPos11 = null;
	private String subTranspDefault="255";  //  @jve:decl-index=0:
	private JButton jbutton5 = null;
	private JPanel jPanel9 = null;
	private JPanel jPanel412 = null;
	private JLabel jLabel412 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel = null;
	private JTextField bitrateField = null;
	private JTextField scaleX = null;
	private JTextField scaleY = null;
	private JPanel jPanel10 = null;
	private JPanel jPanel11 = null;
	private JLabel jLabel6 = null;
	private JPanel jPanel24 = null;
	private JLabel jLabel7 = null;
	private JCheckBox rebuildIndexCheck = null;
	private JPanel jPanel221 = null;
	private JLabel jLabel21 = null;
	private JTextField subFontScale = null;
	private JPanel jPanel212 = null;
	private JLabel jLabel12 = null;
	private JComboBox outputContainer = null;
	private JPanel formatOptsPanel = null;
	private JButton jButton1 = null;
	public DirtySubtitlePutter() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(692, 511);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.X_AXIS));
			jContentPane.add(getJPanel5(), null);
			jContentPane.add(getConsolePanel(), null);
		}
		return jContentPane;
	}
	
	private InputPanel getInputPanel() {
		if (inputPanel == null) {
			inputPanel = new InputPanel();
		}
		return inputPanel;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS));
			jPanel1.add(getStartbt(), null);
			jPanel1.add(getStop(), null);
			jPanel1.add(getPlaybt(), null);
			jPanel1.add(getHelpMplayerBt(), null);
			jPanel1.add(getJButton1(), null);
		}
		return jPanel1;
	}

	private JButton getStartbt() {
		if (startbt == null) {
			startbt = new JButton("start");
			startbt.addActionListener(new java.awt.event.ActionListener() {				
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final List<File> invids = inputPanel.getInputVideoSelected();
					List<File> insubs = inputPanel.getInputSubtitleSelected();
					
					List<File> outFolder = inputPanel.getOutputVideoFolder();
					if(outFolder==null || outFolder.size()==0 ||outFolder.get(0)==null) {
						outFolder=new LinkedList<File>();
						outFolder.add(new File(System.getProperty("user.home")));
					}
					if(outFolder.size()==0) {
						JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
				    		"Your output movie will be stored in \n"+System.getProperty("user.home"));
					}

					try {
						String out=outFolder.get(0).getAbsolutePath()+File.separator+invids.get(0).getName();
						if(new File(out).exists()) {
							JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
								"Error: el archivo de salida ya existe");
							return ;
						}
						
						String cmd = getCmdForProgram();						
						System.out.println(cmd);
						
						timer = new Timer(1000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String s = ((MencoderExecutorImpl)executor).getCurrentOutput();
								System.out.println("out: "+s);
								if(s==null)
									s="";
								outputText.setText(s);
							}
							
						});
						timer.start();
						
						executor = MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							int i=0, i_max=20;

							private String MENCODER_PROGRESS_PATTERN_STR = 
								"\\s*Pos\\:\\s+(\\S+)\\s+(\\S+)(.....)\\s+(\\S+)\\s+Trem\\:\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
							Pattern pat = Pattern.compile(MENCODER_PROGRESS_PATTERN_STR);
							
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
								JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"F I N + "+invids.get(0).getName()+"\nErrors:"+endExecutionInformation.getErrorList());
								System.out.println("output:\n\n"+endExecutionInformation.getOutputList());
								System.out.println("error:\n\n"+endExecutionInformation.getErrorList());
								timer.stop();
							}
							public void notifyError(String str) {
								System.out.println("ERROR: "+str);
//								console.appendOuput("ERROR: "+str);
							}
							public void notifyOuput(String str) {
								i++;
								if(i>i_max) {
									i=0;
//									System.out.println(str);
//									console.setOutput(str);
									outputText.setText(str);
//									 java.util.regex.Matcher m = pat.matcher(str);
//									 if(m.matches())
//										 System.out.println(" - "+m.group(1));
									 
								}
							}
							public void notifyMovieHeader(String movieHeader) {
//								JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"movieHeader: "+movieHeader);
//								System.out.println(movieHeader);
//								System.exit(0);
							}						
						});
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"ERROR: "+e1.getLocalizedMessage());
						e1.printStackTrace();
					}
				}

				
			
				
				
			});
		}
		return startbt;
	}

	protected String getCmdForProgram() {
		
		/* obtaining GUI data */	
		final List<File> invids = inputPanel.getInputVideoSelected();
		if(invids==null||invids.size()==0) 
			JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
				"debe ingresar un video");
		List<File> insubs = inputPanel.getInputSubtitleSelected();
		List<File> outFolder = inputPanel.getOutputVideoFolder();
		
		/* in / out files */
		
		String out=System.getProperty("user.home");
		String oc = outputContainers[outputContainer.getSelectedIndex()];
		if(outFolder==null) {
			JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"the video will be stored in "+out);
		} else {
			out=outFolder.get(0).getAbsolutePath();
		}
		
		out=out+File.separator+FSUtils.getFileName(invids.get(0).getName())+"."+MPlayerUtils.getAppopiateFileExtensionFor(oc);//org.sgx.utils.getFileExtension(invids.get(0).getName());
		
		
		/* subtitles */
		
		String sub = "";
		if(inputPanel.getPasteSub().isSelected())
			sub+="-sub \""+insubs.get(0).getAbsolutePath()+"\" " +
			"-ass-font-scale "+subFontScale.getText();			
		
		String subDelaySegs = "0";
		try {
			subDelaySegs=""+Math.round(((float)(new Integer(subDelay.getText()).intValue()/1000)));
		} catch(Exception e) {
			JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"subtitle delay must be an integer");
			return "";
		}
		
		String subFontScaleB = defaultSubFontScale;
		try {
			subFontScaleB=new Integer(subFontScale.getText()).toString();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"subtitle font scale parameter must be an integer");
			return "";
		}					
		
		String mpegFormat = getMpegOptPanel().getSelectedMpegFormat();
		String mpegPalNtsc =  getMpegOptPanel().getSelectedPalNtscFormat();
		
		
		/* building the command */
		
		String videoParams = "";
		String audioParams ="";//each video formats can modify audio params. if they aren't selected audio format will be used.
		if(oc.equals(Format.MPEG)) {
			
			if(mpegFormat.equals(Format.MPEG_XVCD)) {
				if(mpegPalNtsc.equals(Format.PAL)) {
					audioParams+= " -oac lavc ";
					videoParams+=" -ovc lavc -of mpeg -mpegopts format=xvcd -vf scale=352:288,harddup " +
						" -srate 44100 -af lavcresample=44100 " +
						" -lavcopts vcodec=mpeg1video:keyint=15:vrc_buf_size=327:vrc_minrate=1152:vbitrate=1152:" +
						"vrc_maxrate=1152:acodec=mp2:abitrate=224 " +
						" -ofps 25";
				}
				else if(mpegPalNtsc.equals(Format.NTSC)) {
					audioParams+= " -oac lavc ";
					videoParams+=" -ovc lavc -of mpeg -mpegopts format=xvcd -vf scale=352:240,harddup -srate " +
						"44100 -af lavcresample=44100 " +
						"-lavcopts vcodec=mpeg1video:keyint=18:vrc_buf_size=327:vrc_minrate=1152:vbitrate=1152:" +
						"vrc_maxrate=1152:acodec=mp2:abitrate=224 " +
						"-ofps 30000/1001";
				}
				else {
					throw new RuntimeException("pal ntsc option not recognized");
				}
			}
			else if(mpegFormat.equals(Format.MPEG_XSVCD)) {
				JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
						Format.MPEG_XSVCD+" format not implemented yet :P");
				videoParams+=" -of mpeg -mpegopts format=xvcd ";
			}
			else if(mpegFormat.equals(Format.MPEG_DVD)) {
				if(mpegPalNtsc.equals(Format.PAL)) {
					audioParams+= " -oac lavc ";
					videoParams+=" -ovc lavc -of mpeg -mpegopts format=dvd:tsaf " +
							"-vf scale=720:576,harddup -srate 48000 -af lavcresample=48000 " +
							"-lavcopts vcodec=mpeg2video:vrc_buf_size=1835:vrc_maxrate=9800:vbitrate=5000:" +
							"keyint=15:vstrict=0:acodec=ac3:abitrate=192:aspect=16/9 -ofps 25 ";
				}
				else if(mpegPalNtsc.equals(Format.NTSC)) {
					audioParams+= " -oac lavc ";
					videoParams+=" -ovc lavc -of mpeg -mpegopts format=dvd:tsaf " +
							"-vf scale=720:480,harddup -srate 48000 -af lavcresample=48000 " +
							"-lavcopts vcodec=mpeg2video:vrc_buf_size=1835:vrc_maxrate=9800:vbitrate=5000:" +
							"keyint=18:vstrict=0:acodec=ac3:abitrate=192:aspect=16/9 -ofps 30000/1001 ";
				}
				else {
					throw new RuntimeException("pal ntsc option not recognized");
				}
			}
//			else {
//				JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
//			    		"video format mpeg "+mpegFormat+" not implemented, yet");
//			}
		}
		else if(oc.equals(Format.AVI)) {
			videoParams+=" -of avi -ovc xvid -xvidencopts bitrate="+bitrateField.getText()+" ";
		}
//		else
//			JOptionPane.showMessageDialog(DirtySubtitlePutter.this,
//		    		"video container "+oc+" not supported");
		
		if(audioParams.equals(""))
			audioParams=" -oac mp3lame ";
		
		
		boolean rebuildIndex  = rebuildIndexCheck.isSelected();
		
		/* command construction */
		
		if(oc.equals(Format.MPEG) || oc.equals(Format.AVI)) {
			String cmd =
				/*executable */
				"mplayer/mencoder.exe "+
				/* video, subtitle and output files */
				"\""+invids.get(0).getAbsolutePath()+"\" "+
				sub+" -o \""+out+"\""+
				/*rebuild index */
				(rebuildIndex?" -forceidx -idx ":" ")+
				/* video format options*/

				audioParams+
				videoParams+
				
				/*subtitles optiones */
				" -subdelay "+subDelaySegs+ 
				" -subfont-text-scale "+subFontScaleB+
				(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont.getName())+"\" "))+
				" -slang es -subcp latin1 "+
				"";
			
			return cmd;
		}
		
		else if(oc.equals(Format.MPEG_4) ){
			/* this requires 2 pass of mencoder. we build a .bat or a .sh and execute it, like:
rm frameno.avi - remove this file, which can come from a previous 3-pass encoding (it interferes with current one)
mencoder -dvd 2 -ovc lavc -lavcopts vcodec=mpeg4:vpass=1 -oac copy -o movie.avi
mencoder -dvd 2 -ovc lavc -lavcopts vcodec=mpeg4:vpass=2 -oac copy -o movie.avi*/
			String scriptName="mpeg_4.bat";
			try {						
				File f = new File(scriptName);
				String outputFile =  "\""+out+"\"";
				if(f.exists())
					f.delete();
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				String fcontents = "del "+outputFile+"\n"+
				MPlayerUtils.mencoderExe
					+"\""+invids.get(0).getAbsolutePath()+"\" -ovc lavc -lavcopts vcodec=mpeg4:vbitrate=1000:vhq:vqmin=2:vlelim=-4:vcelim=9:lumi_mask=0.05:dark_mask=0.01:vpass=1  -oac mp3lame "+" -o "+outputFile+"\n"+
					MPlayerUtils.mencoderExe+" "+"\""+invids.get(0).getAbsolutePath()+"\" -ovc lavc -lavcopts vcodec=mpeg4:vbitrate=1000:vhq:vqmin=2:vlelim=-4:vcelim=9:lumi_mask=0.05:dark_mask=0.01:vpass=2  -oac mp3lame "+" -o "+outputFile+"\n"+
				"";
				fos.write(fcontents.getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return scriptName;
		}
		else {
			throw new RuntimeException("format not supported");
		}
	}

	private JButton getStop() {
		if (stop == null) {
			stop = new JButton();
			stop.setText("stop");
			stop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DirtySubtitlePutter.this.executor.getProc().destroy();
				}
			});
		}
		return stop;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.setBorder(BorderFactory.createTitledBorder("general options"));
			jPanel.add(getJPanel23(), null);
			jPanel.add(getJPanel211(), null);
			jPanel.add(getJPanel212(), null);
		}
		return jPanel;
	}

	private JPanel getJPanel22() {
		if (jPanel22 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("subtitle delay");
			jPanel22 = new JPanel();
			jPanel22.setLayout(new BoxLayout(getJPanel22(), BoxLayout.Y_AXIS));
			jPanel22.add(jLabel2, null);
			jPanel22.add(getSubDelay(), null);
		}
		return jPanel22;
	}

	private JTextField getSubDelay() {
		if (subDelay == null) {
			subDelay = new JTextField();
			subDelay.setText(defaultSubDelay);
		}
		return subDelay;
	}

	private JPanel getJPanel23() {
		if (jPanel23 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("audio delay");
			jPanel23 = new JPanel();
			jPanel23.setLayout(new BoxLayout(getJPanel23(), BoxLayout.Y_AXIS));
			jPanel23.add(jLabel3, null);
			jPanel23.add(getAudioDelay(), null);
		}
		return jPanel23;
	}

	private JTextField getAudioDelay() {
		if (audioDelay == null) {
			audioDelay = new JTextField();
			audioDelay.setText(defaultAudioDelay);
		}
		return audioDelay;
	}

	private JButton getPlaybt() {
		if (playbt == null) {
			playbt = new JButton();
			playbt.setText("play");
			playbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final List<File> invids = inputPanel.getInputVideoSelected();
					List<File> insubs = inputPanel.getInputSubtitleSelected();
					List<File> outFolder = inputPanel.getOutputVideoFolder();
					
					String sub = "";
					if(inputPanel.getPasteSub().isSelected())
						sub+="-sub \""+insubs.get(0).getAbsolutePath()+"\"";					
					
					String subFontScaleB = defaultSubFontScale;
					try {
						subFontScaleB=new Integer(subFontScale.getText()).toString();
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"subtitle font scale parameter must be an integer");
					}
					
					String cmd = MPlayerUtils.mplayerExe+" \""+invids.get(0).getAbsolutePath()+
						"\" "+sub
						+" -subfont-text-scale "+subFontScaleB+
						(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont.getName())+"\" "));
					
					System.out.println("executing: "+cmd);
					try {
						playerExecutor = MencoderExecutorImpl.execute(cmd, new ExecutorListener() {

							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
//								JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"F I N + "+invids.get(0).getName()+"\nErrors:"+endExecutionInformation.getErrorList());
//								System.out.println("fin mplayer. errors:\n"+endExecutionInformation.getErrorList());
							}

							public void notifyError(String str) {
								
							}

							public void notifyOuput(String str) {
//								System.out.println("mplayeroutput:"+str);
							}

							public void notifyMovieHeader(String movieHeader) {
//								System.out.println(movieHeader);
//								System.exit(0);
							}
							
						});
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"ERROR "+e1.getLocalizedMessage());
						e1.printStackTrace();
					}
				
					
				}
			});
		}
		return playbt;
	}


	private JPanel getJPanel211() {
		if (jPanel211 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("audio format");
			jPanel211 = new JPanel();
			jPanel211.setLayout(new BoxLayout(getJPanel211(), BoxLayout.Y_AXIS));
			jPanel211.add(jLabel11, null);
			jPanel211.add(getAudioFormat(), null);
		}
		return jPanel211;
	}

	
	private JComboBox getAudioFormat() {
		if (audioFormat == null) {
			audioFormat = new JComboBox();
			audioFormat.setModel(new DefaultComboBoxModel(outAudioFormats ));
		}
		return audioFormat;
	}

	
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new BoxLayout(getJPanel3(), BoxLayout.X_AXIS));
			jPanel3.setBorder(BorderFactory.createTitledBorder("subtitle options"));
			jPanel3.add(getJPanel22(), null);
			jPanel3.add(getJPanel4(), null);
			jPanel3.add(getJPanel41(), null);
			jPanel3.add(getJPanel411(), null);
			jPanel3.add(getJPanel6(), null);
			jPanel3.add(getJPanel221(), null);
		}
		return jPanel3;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("vertical position");
			jPanel4 = new JPanel();
			jPanel4.setLayout(new BoxLayout(getJPanel4(), BoxLayout.Y_AXIS));
			jPanel4.add(jLabel4, null);
			jPanel4.add(getSubVPos(), null);
		}
		return jPanel4;
	}

	private JTextField getSubVPos() {
		if (subVPos == null) {
			subVPos = new JTextField();
			subVPos.setText("0.0");
		}
		return subVPos;
	}

	private JButton getHelpMplayerBt() {
		if (helpMplayerBt == null) {
			helpMplayerBt = new JButton();
			helpMplayerBt.setText("using mplayer...");
		}
		return helpMplayerBt;
	}
	
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("help");
			jMenu.add(getAboutmenuitem());
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}

	private JMenuItem getAboutmenuitem() {
		if (aboutmenuitem == null) {
			aboutmenuitem = new JMenuItem();
			aboutmenuitem.setText("about this program...");
			aboutmenuitem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"Si te gusta este programa hacele una donaci�n al seba vo careta");							
				}
			});
		}
		return aboutmenuitem;
	}

	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Help");
		}
		return jMenuItem;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new BoxLayout(getJPanel5(), BoxLayout.Y_AXIS));
			jPanel5.add(getInputPanel(), null);
			jPanel5.add(getJPanel1(), null);
			jPanel5.add(getJPanel(), null);
			jPanel5.add(getFormatOptsPanel(), null);
			jPanel5.add(getJPanel9(), null);
			jPanel5.add(getJPanel3(), null);
			jPanel5.add(getOutputText(), null);
		}
		return jPanel5;
	}


	private JPanel getConsolePanel() {
		if (consolePanel == null) {
			consolePanel = new JPanel();
			consolePanel.setLayout(new BoxLayout(getConsolePanel(), BoxLayout.Y_AXIS));
//			consolePanel.add(getConsole(), null);
		}
		return consolePanel;
	}

//	private JPanel getConsole() {
//		if (console == null) {
//			console = new Console();
//
//			console.setSize(300,500);
////			console.setLayout(new GridBagLayout());
//		}
//		return console;
//	}

	private JTextField getOutputText() {
		if (outputText == null) {
			outputText = new JTextField();
		}
		return outputText;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			subFontLabel = new JLabel();
			subFontLabel.setText("Subtitle Font");
			jPanel6 = new JPanel();
			jPanel6.setLayout(new BoxLayout(getJPanel6(), BoxLayout.Y_AXIS));
			jPanel6.setPreferredSize(new Dimension(213, 47));
			jPanel6.add(getJPanel7(), null);
			jPanel6.add(getJPanel8(), null);
		}
		return jPanel6;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("...");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FontChooserDialog fc = new FontChooserDialog(DirtySubtitlePutter.this);
					fc.setVisible(true);
					fc.addFontChooserListener(new FontChooserListener() {
						public void notifyFontChoosed(Font f) {
							subFont = f;
							subFontLabel.setFont(f);
						}
						public void notifyFontChooserCanceled() {
						}						
					});
			
					
				}
			});
		}
		return jButton;
	}



	protected void killAllProcesses() {
		if(executor!=null)
			executor.killProcess();
		if(playerExecutor!=null)
			playerExecutor.killProcess();
	}
	
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(new BoxLayout(getJPanel7(), BoxLayout.X_AXIS));
			jPanel7.add(subFontLabel, null);
			jPanel7.add(getJButton(), null);
			jPanel7.add(getJbutton5(), null);
		}
		return jPanel7;
	}


	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Use default font");
			jPanel8 = new JPanel();
			jPanel8.setLayout(new BoxLayout(getJPanel8(), BoxLayout.X_AXIS));
			jPanel8.add(getUseDefaultSubFontCheck(), null);
			jPanel8.add(jLabel5, null);
		}
		return jPanel8;
	}

	private JCheckBox getUseDefaultSubFontCheck() {
		if (useDefaultSubFontCheck == null) {
			useDefaultSubFontCheck = new JCheckBox();
		}
		return useDefaultSubFontCheck;
	}

	private JPanel getJPanel41() {
		if (jPanel41 == null) {
			jLabel41 = new JLabel();
			jLabel41.setText("width");
			jPanel41 = new JPanel();
			jPanel41.setLayout(new BoxLayout(getJPanel41(), BoxLayout.Y_AXIS));
			jPanel41.add(jLabel41, null);
			jPanel41.add(getSubVPos1(), null);
		}
		return jPanel41;
	}

	private JTextField getSubVPos1() {
		if (subVPos1 == null) {
			subVPos1 = new JTextField();
			subVPos1.setText(defaultSubWidth);
			subVPos1.setToolTipText("value between 10 and 100. Specify the maximum width of subtitles on the screen. Useful for TV-out. The value is the width of the subtitle in % of the screen width.");
		}
		return subVPos1;
	}

	private JPanel getJPanel411() {
		if (jPanel411 == null) {
			jLabel411 = new JLabel();
			jLabel411.setText("transparency");
			jPanel411 = new JPanel();
			jPanel411.setLayout(new BoxLayout(getJPanel411(), BoxLayout.Y_AXIS));
			jPanel411.add(jLabel411, null);
			jPanel411.add(getSubVPos11(), null);
		}
		return jPanel411;
	}

	private JTextField getSubVPos11() {
		if (subVPos11 == null) {
			subVPos11 = new JTextField();
			subVPos11.setText(subTranspDefault);
			subVPos11.setToolTipText("Value between 0 and 255.\n Specify the alpha channel value for subtitles and OSD backgrounds.\n Big values mean more transparency.\n 0 means completely transparent.");
			
		}
		return subVPos11;
	}

	
	private JButton getJbutton5() {
		if (jbutton5 == null) {
			jbutton5 = new JButton();
			jbutton5.setText("color");
			jbutton5.addActionListener(new java.awt.event.ActionListener() {
				private JColorChooser tcc = new JColorChooser(subColor);
				public void actionPerformed(java.awt.event.ActionEvent e) {
					    tcc.getSelectionModel().addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent e) {
								subColor=tcc.getColor();
								subFontLabel.setForeground(subColor);
							}				        	
				        });
				        tcc.setBorder(BorderFactory.createTitledBorder(
				                                             "Choose Text Color"));
				        JDialog d = new JDialog(DirtySubtitlePutter.this);
						d.setContentPane(tcc);
						d.setVisible(true);
						d.setSize(400,400);
					//JColorChooser cc = JColorChooser.createDialog(DirtySubtitlePutter.this, "choose subtitle font color", true, chooserPane, okListener, cancelListener)
				}
			});
		}
		return jbutton5;
	}


	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(new BoxLayout(getJPanel9(), BoxLayout.X_AXIS));
			jPanel9.setBorder(BorderFactory.createTitledBorder("video options"));
			jPanel9.add(getJPanel412(), null);
			jPanel9.add(getJPanel2(), null);
			jPanel9.add(getJPanel24(), null);
		}
		return jPanel9;
	}

	private JPanel getJPanel412() {
		if (jPanel412 == null) {
			jLabel412 = new JLabel();
			jLabel412.setText("Scale Width");
			jPanel412 = new JPanel();
			jPanel412.setLayout(new BoxLayout(getJPanel412(), BoxLayout.X_AXIS));
			jPanel412.add(getJPanel10(), null);
			jPanel412.add(getJPanel11(), null);
		}
		return jPanel412;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel = new JLabel();
			jLabel.setText("bitrate");
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.Y_AXIS));
			jPanel2.add(jLabel, null);
			jPanel2.add(getBitrateField(), null);
		}
		return jPanel2;
	}

	private JTextField getBitrateField() {
		if (bitrateField == null) {
			bitrateField = new JTextField();
			bitrateField.setText(defaultBitrate);
		}
		return bitrateField;
	}

	private JTextField getScaleX() {
		if (scaleX == null) {
			scaleX = new JTextField();
		}
		return scaleX;
	}

	private JTextField getScaleY() {
		if (scaleY == null) {
			scaleY = new JTextField();
		}
		return scaleY;
	}

	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			jPanel10 = new JPanel();
			jPanel10.setLayout(new BoxLayout(getJPanel10(), BoxLayout.Y_AXIS));
			jPanel10.add(jLabel412, null);
			jPanel10.add(getScaleX(), null);
		}
		return jPanel10;
	}

	private JPanel getJPanel11() {
		if (jPanel11 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Scale Height");
			jPanel11 = new JPanel();
			jPanel11.setLayout(new BoxLayout(getJPanel11(), BoxLayout.Y_AXIS));
			jPanel11.add(jLabel6, null);
			jPanel11.add(getScaleY(), null);
		}
		return jPanel11;
	}

	private JPanel getJPanel24() {
		if (jPanel24 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("rebuild index?");
			jPanel24 = new JPanel();
			jPanel24.setLayout(new BoxLayout(getJPanel24(), BoxLayout.X_AXIS));
			jPanel24.add(getRebuildIndexCheck(), null);
			jPanel24.add(jLabel7, null);
		}
		return jPanel24;
	}

	private JCheckBox getRebuildIndexCheck() {
		if (rebuildIndexCheck == null) {
			rebuildIndexCheck = new JCheckBox();
		}
		return rebuildIndexCheck;
	}

	private JPanel getJPanel221() {
		if (jPanel221 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("font scale");
			jPanel221 = new JPanel();
			jPanel221.setLayout(new BoxLayout(getJPanel221(), BoxLayout.Y_AXIS));
			jPanel221.add(jLabel21, null);
			jPanel221.add(getSubFontScale(), null);
		}
		return jPanel221;
	}
	
	private JTextField getSubFontScale() {
		if (subFontScale == null) {
			subFontScale = new JTextField();
			subFontScale.setText(defaultSubFontScale);
		}
		return subFontScale;
	}	
	private JPanel getJPanel212() {
		if (jPanel212 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("container");
			jPanel212 = new JPanel();
			jPanel212.setLayout(new BoxLayout(getJPanel212(), BoxLayout.Y_AXIS));
			jPanel212.add(jLabel12, null);
			jPanel212.add(getOutputContainer(), null);
		}
		return jPanel212;
	}

	private JComboBox getOutputContainer() {
		if (outputContainer == null) {
			outputContainer = new JComboBox();
			outputContainer.setModel(new DefaultComboBoxModel(outputContainers ));
			outputContainer.setSelectedIndex(defaultOutputContainer);
			outputContainer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("hola "+outputContainer.getSelectedIndex());
					formatOptsPanel.removeAll();
					if(outputContainer.getSelectedIndex()==0)
						formatOptsPanel.add(new JLabel("jsadlksjdlfk"));
					else 
						formatOptsPanel.add(getMpegOptPanel());
					DirtySubtitlePutter.this.pack();
				}							
			});
		}
		return outputContainer;
	}
	private MpegOptionesPanel getMpegOptPanel() {
		if(mpegOptPanel==null)
			mpegOptPanel=new MpegOptionesPanel();
		return mpegOptPanel;
	}	
	private JPanel getFormatOptsPanel() {
		if (formatOptsPanel == null) {
			formatOptsPanel = new JPanel();
			formatOptsPanel.setLayout(new GridBagLayout());
			formatOptsPanel.setBorder(BorderFactory.createTitledBorder("video format specific options"));
			formatOptsPanel.add(getMpegOptPanel());			
		}
		return formatOptsPanel;
	}
	
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("video info");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String cmd = getCmdForProgram();
					try {
						executor = MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {}
							public void notifyError(String str) {}
							public void notifyMovieHeader(String movieHeader) {
								System.out.println(movieHeader);
								
								JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"movieHeader: "+movieHeader);
								executor.killProcess();
								
//								MovieInfoDetector detector = new MovieInfoDetector();
//								try {
//									detector.parseMovieHeader(movieHeader);
//								} catch (MovieHeaderParsingException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								System.out.println("maininfo: "+detector.getMainInfo());
								
							}
							public void notifyOuput(String str) {}						
						});
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(DirtySubtitlePutter.this,	"error retrieving movie header");
						e1.printStackTrace();
					}			
				}
			});
		}
		return jButton1;
	}

	public static void main(String[] args) {    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	final DirtySubtitlePutter f = new DirtySubtitlePutter();
            	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	
            	f.addWindowListener(new WindowListener() {
					public void windowActivated(WindowEvent e) {
					}
					public void windowClosed(WindowEvent e) {
						f.killAllProcesses();
					}
					public void windowClosing(WindowEvent e) {
					}
					public void windowDeactivated(WindowEvent e) {
					}
					public void windowDeiconified(WindowEvent e) {
					}
					public void windowIconified(WindowEvent e) {
					}
					public void windowOpened(WindowEvent e) {
					}
            		
            	});
        		f.setVisible(true);
            }
        });
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"
