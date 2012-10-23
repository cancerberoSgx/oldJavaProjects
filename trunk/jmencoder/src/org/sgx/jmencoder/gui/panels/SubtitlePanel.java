package org.sgx.jmencoder.gui.panels;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sgx.j2s.model.base.Color;
import org.sgx.jmencoder.options.SubtitleFontOption;
import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.gui.editors.file.FileListSelector;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.Utils;

public class SubtitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private FileListSelector subFileList;
	private SimpleBeanEditor<SubtitleOption> subtitleOptionEditor;
	private Component label;

	public SubtitlePanel() {
		super();
		init();
	}
	private void init() {
		this.setLayout(SwingUtils.getBoxLayoutX(this));
		subFileList = new FileListSelector();
		
		subtitleOptionEditor = new SimpleBeanEditor<SubtitleOption>(getDefaultSubtitleOption() );

		label = new JLabel("<html><p>tres tristes tigres comen trigo en el trigal...</p></html>");
		
		JPanel p = new JPanel();
		p.setLayout(SwingUtils.getBoxLayoutY(p));
		p.add(subtitleOptionEditor);
		p.add(label);
		
		this.add(subFileList); 
		this.add(p);	
	}
	private SubtitleOption getDefaultSubtitleOption() {
		SubtitleFontOption f = new SubtitleFontOption();
		f.setSubScale("4");
		f.setColor(Color.WHITE);
		 SubtitleOption defSubOp = new SubtitleOption(null,f);
		 return defSubOp;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final SubtitlePanel p = new SubtitlePanel();
		JFrame f = SwingUtils.showInFrame(p);
		f.addWindowListener(new WindowListener() {
			public void windowIconified(WindowEvent arg0) {
				List<File>files = p.getSubFileList().getList();
				SimpleBeanEditor<SubtitleOption> o = p.getSubtitleOptionEditor();
				System.out.println(Utils.toString(o.getSelectedData()));
				System.out.println(Utils.toString(files));
			}
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	public FileListSelector getSubFileList() {
		return subFileList;
	}
	public SimpleBeanEditor<SubtitleOption> getSubtitleOptionEditor() {
		return subtitleOptionEditor;
	}

}
