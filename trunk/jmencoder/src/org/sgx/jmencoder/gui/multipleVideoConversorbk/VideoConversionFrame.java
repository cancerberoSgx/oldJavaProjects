package org.sgx.jmencoder.gui.multipleVideoConversorbk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.sgx.jmencoder.mplayer.Format;
import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.jmencoder.mplayer.videoInfoDetector.MediaInfo;
import org.sgx.utils.FSUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

/**
 * 
 * @author SGURIN
 *
 */
public class VideoConversionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static String audioConversioScriptFilePath = "audioConversor"+new Date().getTime()+".bat";  //  @jve:decl-index=0:
	
	private static final String ffmpegExe = "ffmpeg"+File.separator+"ffmpeg.exe";  //  @jve:decl-index=0:

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenuItem jMenuItem = null;

	private VideoConversionPanel multipleMovieConversionPanel = null;

	private JPanel jPanel = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	protected Executor executor;

	private JMenu jMenu1 = null;

	private JMenuItem jMenuItem1 = null;

	private JPanel jPanel1 = null;

	private JLabel stateEntry = null;



	public VideoConversionFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(503, 404);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("multiple movie conversion tool");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getMultipleMovieConversionPanel());
			jContentPane.add(getJPanel());
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Help");
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}
	
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("About");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(VideoConversionFrame.this, "this is a 5 minits made user graphical " +
							"interface so don't complain. \nThe people who really make this possible are ffmpeg and mplayer teams\n" +
							"\nThese are free programs for the gnu/linux operating system ported to windows" +
							"\nSwitch to linux now and discover the real power of free software" +
							"\n\n sgurin (Cancerbero)");
				}
			});
		}
		return jMenuItem;
	}

	private VideoConversionPanel getMultipleMovieConversionPanel() {
		if (multipleMovieConversionPanel == null) {
			multipleMovieConversionPanel = new VideoConversionPanel();
		}
		return multipleMovieConversionPanel;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Start");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(executor!=null)
						if(executor.getProc()!=null)
							executor.getProc().destroy();
					executor = ExecutorFactory.getInstance().getDefaultExecutor();
					
					String cmd = getCommand();
					try {
						executor=MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							private int i=0;
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {		
//								System.out.println("");
							}
							public void notifyError(String str) {
								System.out.println("ERR:"+str);
//								if(i>10) {
									stateEntry.setText(str);
//									i=0;
//								}
//								i++;
							}
							public void notifyMovieHeader(String movieHeader) {
//								System.out.println(movieHeader);
							}
							public void notifyOuput(String str) {
								System.out.println("out: "+str);
								if(i>100) {
									stateEntry.setText(str);
									i=0;
								}
								i++;
							}							
						});
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton;
	}

	protected String getCommand() {		
		try {			
			List<File> files = multipleMovieConversionPanel.getSelectedInputFiles();
			String outputDir = multipleMovieConversionPanel.getSelectedOutputDir();
			String outputFormat = multipleMovieConversionPanel.getSelectedOutputFormat();
			String ext = Format.getExtensionFor(outputFormat);
			String s ="", outputFile;
			
			stateEntry.setText("inspecting input videos...");
			for(File f : files) {
				
				if(outputDir==null)					
					outputFile=FSUtils.normalizePath(FSUtils.getFileParentName(f.getAbsolutePath()))+File.separator+
							FSUtils.getFileName(f.getName())+"__"+outputFormat+"__."+ext;
				else
					outputFile=FSUtils.normalizePath(outputDir)+File.separator+
							FSUtils.getFileName(f.getName())+"__"+outputFormat+"__."+ext;
				outputFile = Utils.normalizeString(outputFile);
				
				MediaInfo info = MediaInfo.getVideoInfo(f);				
				
				try {
				s+=Format.mencoderCommandFor(f.getAbsolutePath(), outputFile, info, outputFormat, null);
				System.out.println("cmd: "+s);
				}catch (Exception e) {
					JOptionPane.showMessageDialog(VideoConversionFrame.this, "not implemented yet. please select another format");
					return "";
				}
				
				
//				Creating an MPEG-1 file suitable to be played on systems with minimal multimedia support, such as default Windows installs:
//					mencoder input.avi -of mpeg -mpegopts format=mpeg1:tsaf:muxrate=2000 \
//			    -o output.mpg -oac lavc -lavcoptc acodec=mp2:abitrate=224 -ovc lavc \
//			    -lavcopts vcodec=mpeg1video:vbitrate=1152:keyint=15:mbd=2:aspect=4/3
			    

					    
				
				
				//xvid -ovc lavc -lavcopts vcodec=mpeg4:vbitrate=$V_BITRATE:mbd=2:v4mv:autoaspect" -vf pp=lb -oac mp3lame -lameopts fast:preset=standard
				
				
				
				//flv to divx  <--funciona pero queda mal el indice 
//				s+="mplayer"+File.separator+"mencoder.exe  -forceidx  "+
//				org.sgx.utils.quote(f.getAbsolutePath())+
//				" -ofps "+Double.parseDouble(info.get(MediaInfo.ID_VIDEO_FPS,"24"))+
//				" -ovc lavc -lavcopts vcodec=mpeg4:vbitrate="+
//				Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000+
//				":mbd=2:v4mv:autoaspect " +
//				" -vf pp=lb scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+
//				" -oac mp3lame -lameopts fast:preset=standard:br="+
//						Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000+
//				" -idx  -o "+org.sgx.utils.quote(outputFile)+
//				"";
				
				
				
//				flv to xvid funciona bien!
//				s+="mplayer"+File.separator+"mencoder.exe -forceidx "+
//				org.sgx.utils.quote(f.getAbsolutePath())+	
//				" -ofps "+Double.parseDouble(info.get(MediaInfo.ID_VIDEO_FPS,"24"))+
//				" -ovc xvid -xvidencopts bitrate="+
//				Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000+
//				":autoaspect "+
//				" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+
//				" -oac mp3lame -lameopts fast:preset=standard:br="+
//				Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000+
//				
//				" -o "+org.sgx.utils.quote(outputFile)+
//				"";
				
				
				//use simple approach ffmpeg
//				s+="ffmpeg"+File.separator+"ffmpeg.exe -y -i "+org.sgx.utils.quote(f.getAbsolutePath())+" "+org.sgx.utils.quote(outputFile)+""; //no anda con algunos que estan rotos...

//				s+="ffmpeg"+File.separator+"ffmpeg.exe -y -i "+Utils.quote(f.getAbsolutePath())+" -ab "+Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))+ 
//				" -ar 22050 -b " +Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1024000"))+
//				" -s "+info.get(MediaInfo.ID_VIDEO_WIDTH)+"x"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+ " "+Utils.quote(outputFile)+"";
								
//				Finally, convert .FLV files into MPEG:
//					CODE
//
//					ffmpeg -i I_KRmU2dO2M.flv -ab 56 -ar 22050 -b 500 -s 320x240 video.mpg
//
//
//					Convert .FLV into XviD .AVI with MP3 Audio:
//					ffmpeg -i I_KRmU2dO2M.flv -ab 56 -ar 22050 -b 500 -s 320x240 -vcodec xvid -acodec mp3 video.avi
					
			
//			  
				
//				s+="mplayer"+File.separator+"mencoder.exe "+
//					org.sgx.utils.quote(f.getAbsolutePath())+
//					" -oac copy -ovc lavc -lavcopts " +
//							"vcodec=mpeg4" +
////							":vbitrate="+info.get(MediaInfo.ID_VIDEO_BITRATE)+"" +
//							":vhq:vqmin=2:vlelim=-4:vcelim=9:lumi_mask=0.05:dark_mask=0.01:vpass=1" +
//						" -vf "+
////						"crop=716:572:2:2," +
//						"scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+
//					" -o "+org.sgx.utils.quote(outputFile);
				
//				s+="mplayer"+File.separator+"mencoder.exe "+
//				org.sgx.utils.quote(f.getAbsolutePath())+
//				" -oac mp3 -ovc copy -lavcopts " +
//						"vcodec=mpeg4" +
////						":vbitrate="+info.get(MediaInfo.ID_VIDEO_BITRATE)+"" +
//						":vhq:vqmin=2:vlelim=-4:vcelim=9:lumi_mask=0.05:dark_mask=0.01:vpass=1" +
//					" -vf "+
////					"crop=716:572:2:2," +
//					"scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+
//				" -o "+org.sgx.utils.quote(outputFile);
				
//				mencoder dvd://1 -o /dev/null -oac copy -ovc lavc \
//					  -lavcopts vcodec=mpeg4:vbitrate=1000:vhq:vqmin=2:\
//					  vlelim=-4:vcelim=9:lumi_mask=0.05:dark_mask=0.01:vpass=1 \
//					  -vf crop=716:572:2:2,scale=640:480
					  
					  
//				else { //use mencoder
//					/*$ mencoder pelicula.avi -o pelicula.flv -of lavf -oac mp3lame -lameopts br=32 -af lavcresample=22050 -srate 22050 -ovc lavc -lavcopts vcodec=flv:vbitrate=340:autoaspect:mbd=2:trell:v4mv -vf scale=320:240 -lavfopts i_certify_that_my_video_stream_does_not_use_*/
//					s+=mencoderExe+" "+org.sgx.utils.quote(f.getAbsolutePath())+ " +
//							"";
//				}
								
				s+="\n\n";
			}
			File script = new File(audioConversioScriptFilePath);
			FileOutputStream fos = new FileOutputStream(script);
			fos.write(s.getBytes());
			fos.close();
			return audioConversioScriptFilePath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Stop");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(executor!=null)
						executor.getProc().destroy();
				}
			});
		}
		return jButton1;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu("Edit");
			jMenu1.add(getJMenuItem1());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem("Preferences");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			stateEntry = new JLabel();
			stateEntry.setText("state....");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(stateEntry, new GridBagConstraints());
		}
		return jPanel1;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VideoConversionFrame thisClass = new VideoConversionFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
