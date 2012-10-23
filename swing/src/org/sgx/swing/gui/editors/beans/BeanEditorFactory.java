package org.sgx.swing.gui.editors.beans;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sgx.swing.gui.editors.color.ColorEditor1;
import org.sgx.swing.gui.editors.font.FontFamilyEditor;
import org.sgx.swing.j2s.model.base.Color;
import org.sgx.swing.j2s.model.base.FontFamily;

public class BeanEditorFactory {
	public static JComponent createEditorFor(Object o, String name, boolean editable) {
		if(o!=null)
		System.out.println(o.getClass());
		
		if(o instanceof String || o instanceof Double || o instanceof Integer || o instanceof Float) {		
			return editable?new JTextField(o.toString()):new JLabel(o.toString());
		}
		else if(o instanceof Boolean ) {
			JCheckBox c = new JCheckBox();
			c.setSelected((Boolean)o);
			return editable?c:new  JLabel(o.toString());
		}
		else if(o instanceof Object[]) {
			JComboBox c = new JComboBox(new DefaultComboBoxModel((Object[])o));
			return editable?c:new  JLabel(o.toString());
		}
		else if(o instanceof Color) {
			ColorEditor1 c = new ColorEditor1();
			c.setValue((Color) o);
			return c;
		}
		else if(o instanceof FontFamily) {
			FontFamilyEditor c = new FontFamilyEditor();
			c.setValue((FontFamily) o);
			return c;
		}
		
		else if(o==null) {
			return new JPanel();
		}
		else {
			try {
				SimpleBeanEditor beanEditor = new SimpleBeanEditor(o, editable);
				beanEditor.setBorder(BorderFactory.createTitledBorder(name));
				return beanEditor;
			}catch (Exception e) {
				throw new RuntimeException("type not supported "+o);
			}
		}
	}

	public  static Object getSelectedValueIn(JComponent c) {
		if(c==null)
			return null;
		else if(c instanceof JTextField)
			return ((JTextField)c).getText();
		else if(c instanceof JCheckBox)
			return ((JCheckBox)c).isSelected();
		else if(c instanceof JComboBox)
			return ((JComboBox)c).getSelectedItem();
		else if(c instanceof JLabel)
			return ((JLabel)c).getText();
		else if(c instanceof ColorEditor1) {
			try {
				return ((ColorEditor1)c).getCurrentValue();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else if(c instanceof SimpleBeanEditor)
			return ((SimpleBeanEditor)c).getCurrentValue();
		else if(c instanceof JPanel) {
			return null;
		}
		else 
			throw new RuntimeException("component type not supported "+c.getClass().getCanonicalName());
	}
}
