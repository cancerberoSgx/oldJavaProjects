package org.sgx.jmencoder.gui.dirtyMencoderGui;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.sgx.jmencoder.mplayer.Format;


public class MpegOptionesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int palNtcsDefault = 0;
	public static Object[] palNtcs = {Format.PAL, Format.NTSC};;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JComboBox mpegFormat = null;
	private Object[] mpegFormats = {Format.MPEG_1, Format.MPEG_2, Format.MPEG_XVCD, Format.MPEG_XSVCD, Format.MPEG_DVD };
	static int mpegFormatDefault = 2;
	private JPanel jPanel1 = null;
	private JLabel jLabel1 = null;
	private String bitrateDefault;
	private JComboBox PalNtsc = null;
	
	public MpegOptionesPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getJPanel(), null);
		this.add(getJPanel1(), null);
	}
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("mpeg format");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(jLabel, null);
			jPanel.add(getMpegFormat(), null);
		}
		return jPanel;
	}
	private JComboBox getMpegFormat() {
		if (mpegFormat == null) {
			mpegFormat = new JComboBox();
			mpegFormat.setModel(new DefaultComboBoxModel(mpegFormats ));
			mpegFormat.setSelectedIndex(mpegFormatDefault);
		}
		return mpegFormat;
	}
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("mode");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.Y_AXIS));
			jPanel1.add(jLabel1, null);
			jPanel1.add(getPalNtsc(), null);
		}
		return jPanel1;
	}
	private JComboBox getPalNtsc() {
		if (PalNtsc == null) {
			PalNtsc = new JComboBox();
			PalNtsc.setModel(new DefaultComboBoxModel(palNtcs ));
			PalNtsc.setSelectedIndex(palNtcsDefault);
		}
		return PalNtsc;
	}
	
	public String getSelectedPalNtscFormat() {
		return (String)palNtcs[PalNtsc.getSelectedIndex()];
	}

	public String getSelectedMpegFormat() {
		return (String)mpegFormats[mpegFormat.getSelectedIndex()];
	}
}
