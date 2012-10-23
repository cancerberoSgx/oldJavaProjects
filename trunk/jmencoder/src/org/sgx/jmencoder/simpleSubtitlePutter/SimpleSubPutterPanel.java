package org.sgx.jmencoder.simpleSubtitlePutter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.gui.editors.file.FileListSelector;
import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.WindowsUtils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class SimpleSubPutterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String SCRIPTFILE = "simpleSubPutter.bat";  //  @jve:decl-index=0:
	private FileListSelector videoList = null;
	private FileListSelector subsList = null;
	private JPanel jPanel2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JPanel jPanel = null;
	private JLabel status = null;
	protected MencoderExecutorImpl executor;
	private JPanel jPanel1 = null;
	private JPanel jPanel3 = null;
	private JCheckBox useSrcFolder = null;
	private JLabel jLabel = null;
	private SimpleBeanEditor<SubtitleOption> subtitleEditor;
	

	public SimpleSubPutterPanel() {
		super();
		initialize();
	}


	private void initialize() {
		status = new JLabel();
		status.setText("idle");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(472, 487));
		this.add(getVideoList(), null);
		this.add(getSubsList(), null);
		this.add(getJPanel1(), null);
		this.add(status, null);
		this.add(getJPanel2(), null);
		this.add(getSubtitleEditor());
		this.add(new JPanel());this.add(new JPanel());
	}
	
	private Component getSubtitleEditor() {
		if(subtitleEditor==null) {
			SubtitleOption opts = new SubtitleOption();
			subtitleEditor=new SimpleBeanEditor<SubtitleOption>(opts, true);	
			subtitleEditor.setSize(400,400);
//			subtitleEditor.setValue(opts);
		}
		return subtitleEditor;
	}


	private FileListSelector getVideoList() {
		if (videoList == null) {
			videoList = new FileListSelector();
			videoList.setBorder(BorderFactory.createTitledBorder("video list"));
			videoList.setMaximumSize(new Dimension(1000,200));
		}
		return videoList;
	}

	private FileListSelector getSubsList() {
		if (subsList == null) {
			subsList = new FileListSelector();
			subsList.setBorder(BorderFactory.createTitledBorder("subtitle list"));
		}
		return subsList;
	}



	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new FlowLayout());
			jPanel2.add(getJButton(), null);
			jPanel2.add(getJButton1(), null);
		}
		return jPanel2;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("start");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						String cmd = getCommand();
						executor = new MencoderExecutorImpl();
						executor.addListener(new ExecutorListener() {
							private int i=0;
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
								
							}
							public void notifyError(String str) {
								status.setText(str);
								System.out.println(str);
							}
							public void notifyMovieHeader(String movieHeader) {
								
							}
							public void notifyOuput(String str) {
								status.setText(str);
								System.out.println(str);
							}						
						});
						executor.runCommand(cmd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return jButton;
	}

	protected String getCommand() throws IOException {
		List<File> vids = videoList.getList(), subs = subsList.getList();
//		if(vids.size()!=subs.size()) {
//			JOptionPane.showMessageDialog(SimpleSubPutterPanel.this, "videos and subtitles files must be the same amount");
//			return "";
//		}
		int index=0;
		String s="";
		for(File vid : vids) {			
			if(subs.size()<index)
				break;
			File sub = subs.get(index);
			String audioParams = " -oac copy ";//" -oac lavc ";
			String videoParams =" -ovc copy "; 
//				" -ovc lavc -of mpeg -mpegopts format=xvcd -vf scale=352:288,harddup " +
//				" -srate 44100 -af lavcresample=44100 " +
//				" -lavcopts vcodec=mpeg1video:keyint=15:vrc_buf_size=327:vrc_minrate=1152:vbitrate=1152:" +
//				"vrc_maxrate=1152:acodec=mp2:abitrate=224 " +
//				" -ofps 25";
			
			SubtitleOption subtitle = subtitleEditor.getCurrentValue();		
			s+=MPlayerUtils.mencoderExe+" -o c:\\ss.avi \""+vid.getAbsolutePath()+"\" "+
			audioParams+videoParams+
			
			" -sub \"" +sub.getAbsolutePath()+"\" "+	
				getSubtitleMencoderFormatFor(subtitle);
			
			index++;
		}
		System.out.println(s);
		FileOutputStream fos = new FileOutputStream(new File(SCRIPTFILE));
		fos.write(s.getBytes());
		fos.close();		
		return SCRIPTFILE;
	}

	private String getSubtitleMencoderFormatFor(SubtitleOption subtitle) {
		String subDelaySegs=(Double.parseDouble(subtitle.getSubDelay())/1000.0)+"";
		String subFontScaleB=subtitle.getFont().getSubScale();
		String subFont = subtitle.getFont().getFontFamily().getFontFamily();
		return 			
			" -subdelay "+subDelaySegs+ 
			" -subfont-text-scale "+subFontScaleB+
			(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont)+"\" "))+
			" -slang "+subtitle.getLang()+" -subcp "+subtitle.getCp()+" "+
			"";
	
	}


	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("stop");
		}
		return jButton1;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new FileSelector();
		}
		return jPanel;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.Y_AXIS));

			jPanel1.setBorder(BorderFactory.createTitledBorder("output dir"));
			jPanel1.add(getJPanel(), null);
			jPanel1.add(getJPanel3(), null);
		}
		return jPanel1;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel = new JLabel();
			jLabel.setText("use source folder");
			jPanel3 = new JPanel();
			jPanel3.setLayout(new BoxLayout(getJPanel3(), BoxLayout.X_AXIS));
			jPanel3.add(getUseSrcFolder(), null);
			jPanel3.add(jLabel, null);
			
		}
		return jPanel3;
	}


	private JCheckBox getUseSrcFolder() {
		if (useSrcFolder == null) {
			useSrcFolder = new JCheckBox();
			useSrcFolder.setSelected(true);
		}
		return useSrcFolder;
	}

	public static void main(String[] args) {
		SwingUtils.showInFrame(new SimpleSubPutterPanel());
	}

}  //  @jve:decl-index=0:visual-constraint="2,-5"
