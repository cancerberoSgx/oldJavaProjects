package org.sgx.swing.gui.editors.stringform;

import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.JavaUtils;
import org.sgx.utils.ObjectUtils;

/**
 * 
 * @author sgurin
 *
 */
public class StringForm extends JPanel {
boolean editable = false;
	Map<String,String> data;  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public StringForm() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		for(String id: data.keySet()) {
			this.add(new FormRow(id,data.get(id), editable));
		}
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public StringForm(Map<String, String> data, boolean editable) {
		super();
		this.data = data;
		this.editable=editable;
		initialize();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * test
	 */
	public static void main(String[] args) {
		Map m = ObjectUtils.transformArrayToMap(new String[][] {
				{"uno", "1"},
				{"dos", "2"},
				{"3", "3"},
				{"4", "1asdas"},
				{"5", "ASasAS"},
		});
		StringForm f = new StringForm(m, false);
		SwingUtils.showInFrame(f);
		
	}
}
