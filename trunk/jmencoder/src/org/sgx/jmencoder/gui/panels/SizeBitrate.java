package org.sgx.jmencoder.gui.panels;

import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
/**
 * here we try to represent the ecuation:
 * 
 *  
          $videobitrate * 1000       
$bpp = -----------------------
       $width * $height * $fps
       
       
    and show the next scale
       
< 0.10: don't do it. Please. I beg you!
< 0.15: It will look bad.
< 0.20: You will notice blocks, but it will look ok.
< 0.25: It will look really good.
> 0.25: It won't really improve visually.
> 0.30: Don't do that either - try a bigger resolution instead.

these data was taken from mplayer documentation

 * @author SGURIN
 *
 */
public class SizeBitrate extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel = null;
	private JTextField videoBitrateEntry = null;
	private JPanel jPanel21 = null;
	private JLabel jLabel2 = null;
	private JTextField widthEntry1 = null;
	private JLabel jLabel11 = null;
	private JTextField heightEntry1 = null;
	private JPanel jPanel22 = null;
	private JLabel jLabel1 = null;
	private JTextField fpsEntry = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel221 = null;
	private JLabel jLabel12 = null;
	private JTextField lengthEntry = null;
	private JLabel jLabel3 = null;
	private JPanel jPanel23 = null;
	private JLabel jLabel4 = null;
	private JTextField bppEntry = null;
	private JPanel jPanel4 = null;
	private JPanel jPanel231 = null;
	private JLabel jLabel41 = null;
	private JTextField fileSizeEntry = null;
	private JPanel jPanel5 = null;
	private JLabel jLabel5 = null;
	private JComboBox screeScaleCombo = null;
	private String[] screeScaleDefaults = {"640x480", "800x600"};
	public SizeBitrate() {
		super();
		initialize();
	}
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getJPanel1(), null);
		this.add(getJPanel(), null);
		this.add(getJPanel3(), null);
	}
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
		}
		return jPanel;
	}


	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			BoxLayout l = new BoxLayout(getJPanel1(), BoxLayout.Y_AXIS);
		
			jPanel1.setLayout(l);
			jPanel1.add(getJPanel22(), null);
			jPanel1.add(getJPanel221(), null);
			jPanel1.add(getJPanel2(), null);
			jPanel1.add(getJPanel23(), null);
			jPanel1.add(getJPanel5(), null);
		}
		return jPanel1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel = new JLabel();
			jLabel.setText("video bitrate");
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.X_AXIS));
			jPanel2.add(jLabel, null);
			jPanel2.add(getVideoBitrateEntry(), null);
		}
		return jPanel2;
	}

	private JTextField getVideoBitrateEntry() {
		if (videoBitrateEntry == null) {
			videoBitrateEntry = new JTextField();
			videoBitrateEntry.setEditable(false);
		}
		return videoBitrateEntry;
	}
	private JPanel getJPanel21() {
		if (jPanel21 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("x");
			jLabel2 = new JLabel();
			jLabel2.setText("screen scale");
			jPanel21 = new JPanel();
			jPanel21.setLayout(new BoxLayout(getJPanel21(), BoxLayout.X_AXIS));
			jPanel21.add(jLabel2, null);
			jPanel21.add(getWidthEntry1(), null);
			jPanel21.add(jLabel11, null);
			jPanel21.add(getHeightEntry1(), null);
			jPanel21.add(getScreeScaleCombo(), null);
		}
		return jPanel21;
	}
	private JTextField getWidthEntry1() {
		if (widthEntry1 == null) {
			widthEntry1 = new JTextField();
		}
		return widthEntry1;
	}
	private JTextField getHeightEntry1() {
		if (heightEntry1 == null) {
			heightEntry1 = new JTextField();
		}
		return heightEntry1;
	}
	private JPanel getJPanel22() {
		if (jPanel22 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("frames per second");
			jLabel1.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jPanel22 = new JPanel();
			jPanel22.setLayout(new BoxLayout(getJPanel22(), BoxLayout.X_AXIS));
			jPanel22.add(jLabel1, null);
			jPanel22.add(getFpsEntry(), null);
		}
		return jPanel22;
	}
	private JTextField getFpsEntry() {
		if (fpsEntry == null) {
			fpsEntry = new JTextField();
			fpsEntry.setEditable(false);
		}
		return fpsEntry;
	}
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new BoxLayout(getJPanel3(), BoxLayout.Y_AXIS));
			jPanel3.add(getJPanel21(), null);
			jPanel3.add(getJPanel231(), null);
			jPanel3.add(getJPanel4(), null);
		}
		return jPanel3;
	}
	private JPanel getJPanel221() {
		if (jPanel221 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("seconds");
			jLabel12 = new JLabel();
			jLabel12.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel12.setText("length");
			jPanel221 = new JPanel();
			jPanel221.setLayout(new BoxLayout(getJPanel221(), BoxLayout.X_AXIS));
			jPanel221.add(jLabel12, null);
			jPanel221.add(getLengthEntry(), null);
			jPanel221.add(jLabel3, null);
		}
		return jPanel221;
	}
	private JTextField getLengthEntry() {
		if (lengthEntry == null) {
			lengthEntry = new JTextField();
			lengthEntry.setEditable(false);
		}
		return lengthEntry;
	}
	private JPanel getJPanel23() {
		if (jPanel23 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("bits per pixel");
			jPanel23 = new JPanel();
			jPanel23.setLayout(new BoxLayout(getJPanel23(), BoxLayout.X_AXIS));
			jPanel23.add(jLabel4, null);
			jPanel23.add(getBppEntry(), null);
		}
		return jPanel23;
	}
	private JTextField getBppEntry() {
		if (bppEntry == null) {
			bppEntry = new JTextField();
			bppEntry.setEditable(false);
		}
		return bppEntry;
	}
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
		}
		return jPanel4;
	}
	private JPanel getJPanel231() {
		if (jPanel231 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("MB");
			jLabel41 = new JLabel();
			jLabel41.setText("file size");
			jPanel231 = new JPanel();
			jPanel231.setLayout(new BoxLayout(getJPanel231(), BoxLayout.X_AXIS));
			jPanel231.add(jLabel41, null);
			jPanel231.add(getFileSizeEntry(), null);
			jPanel231.add(jLabel5, null);
		}
		return jPanel231;
	}
	private JTextField getFileSizeEntry() {
		if (fileSizeEntry == null) {
			fileSizeEntry = new JTextField();
		}
		return fileSizeEntry;
	}
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GridBagLayout());
		}
		return jPanel5;
	}
	private JComboBox getScreeScaleCombo() {
		if (screeScaleCombo == null) {
			screeScaleCombo = new JComboBox();
			screeScaleCombo.setModel(new DefaultComboBoxModel(screeScaleDefaults  ));
			screeScaleCombo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String s = (String)screeScaleCombo.getSelectedItem();
					String[] a = s.split("x");
					widthEntry1.setText(a[0]);
					heightEntry1.setText(a[1]);
				}
			});
		}
		return screeScaleCombo;
	}

	public void setFps(String fps) {
		fpsEntry.setText(fps);
	}
	public void setLength(String s) {
		lengthEntry.setText(s);
	}
	public void setVideoBitrate(String s) {
		lengthEntry.setText(s);
	}
	public void setBpp(String s) {
		bppEntry.setText(s);
	}
	public  int getSelectedVideoBitrate() {
		int i =0;
		try {
			i=Integer.parseInt(videoBitrateEntry.getText());
		}catch(Exception e) {
			throw new RuntimeException("invalid integer : "+videoBitrateEntry.getText());
		}
		return i;
	}
	public  int getSelectedWidth() {
		int i =0;
		try {
			i=Integer.parseInt(widthEntry1.getText());
		}catch(Exception e) {
			throw new RuntimeException("invalid integer : "+widthEntry1.getText());
		}
		return i;
	}
	public  int getSelectedHeight() {
		int i =0;
		try {
			i=Integer.parseInt(heightEntry1.getText());
		}catch(Exception e) {
			throw new RuntimeException("invalid integer : "+heightEntry1.getText());
		}
		return i;
	}
}
