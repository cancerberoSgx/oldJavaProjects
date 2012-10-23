package org.sgx.jmencoder.gui.videoInfo;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import org.sgx.jmencoder.mplayer.videoInfoDetector.MediaInfo;
import org.sgx.swing.gui.editors.stringform.StringForm;

import java.awt.BorderLayout;

public class VideoInfoPanel extends JPanel {

	MediaInfo mediaInfo;
	
	private static final long serialVersionUID = 1L;

	private StringForm form = null;

	/**
	 * This is the default constructor
	 */
	public VideoInfoPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getForm(), BorderLayout.CENTER);
	}

	public VideoInfoPanel(MediaInfo mediaInfo) {
		super();
		this.mediaInfo = mediaInfo;
		initialize();
	}

	/**
	 * This method initializes form	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private StringForm getForm() {
		if (form == null) {
			
			form = new StringForm(mediaInfo, false);
		}
		return form;
	}

}
