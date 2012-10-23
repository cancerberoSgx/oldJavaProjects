package org.sgx.swing.gui.editors.color;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sgx.swing.gui.editors.sliders.RealtimeSwingAbstractEditor;
import org.sgx.swing.j2s.model.base.Color;
import org.sgx.swing.j2s.model.editor.EditorInvalidStateException;
import org.sgx.swing.j2s.model.events.EditorEvent;
import org.sgx.swing.utils.SwingUtils;

public class ColorEditor1  extends RealtimeSwingAbstractEditor<Color>{

	private static final long serialVersionUID = 1L;
	private JLabel label=null;
	private Color color=null;
	private JButton button=null;
	private String title="color";
	public ColorEditor1(Color color, String title) {
		super();
		this.color = color;
		this.title=title;
		init();
	}
	public ColorEditor1() {
		super();
		init();
	}
	private void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.add(getLabel());
		
//		this.add(getButton());
	}
//	private JButton getButton() {
//		if(button==null) {
//			button=new JButton("...");
//		}
//		return button;
//	}
	private JLabel getLabel() {
		if(label==null) {
			label=new JLabel("  ...  ");			
			if(color!=null)
				updateColor();			
			label.setOpaque(true);			
			label.addMouseListener(new MouseListener() {
				private JColorChooser dialog;
				private JFrame f;
				public void mouseClicked(MouseEvent arg0) {
					java.awt.Color c = SwingUtils.transformColor(color);
					dialog = new JColorChooser(getLabel().getBackground());
					f = new JFrame("select color");
					f.setContentPane(dialog);
					f.setSize(300,300);
					dialog.getSelectionModel().addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							java.awt.Color c2 = dialog.getColor();
							setValue(SwingUtils.transformColor(c2));
							f.setVisible(false);
							ColorEditor1.this.notifyAll(new EditorEvent<Color>(ColorEditor1.this, color));
						}						
					});
					f.setVisible(true);
				}
				public void mouseEntered(MouseEvent arg0) {	}
				public void mouseExited(MouseEvent arg0) {	}
				public void mousePressed(MouseEvent arg0) {	}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
		}
		return label;
	}
	private void updateColor() {
		java.awt.Color c = SwingUtils.transformColor(color);
		getLabel().setBackground(c);
//		getLabel().setBackground(java.awt.Color.blue);
	}
	public Color getCurrentValue()  {
		color=SwingUtils.transformColor(getLabel().getBackground());
		return color;
	}

	public void setValue(Color value) {
		color=value;
		updateColor();
	}

}
