package org.sgx.jmencoder.gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import org.sgx.j2s.model.editor.EditorInvalidStateException;
import org.sgx.swing.gui.editors.sliders.RealtimeSwingAbstractEditor;

public class VideoInfoEditor<T> extends RealtimeSwingAbstractEditor<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * This is the default constructor
	 */
	public VideoInfoEditor() {
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
		this.setLayout(new GridBagLayout());
	}

	public T getCurrentValue(){
		// TODO Auto-generated method stub
		return null;
	}

	public void setValue(T value) {
		// TODO Auto-generated method stub
		
	}

}
