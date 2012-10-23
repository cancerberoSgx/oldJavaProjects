package org.sgx.swing.gui.editors.base;

import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.sgx.swing.j2s.model.base.ListSelection;
import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.j2s.model.editor.EditorInvalidStateException;
import org.sgx.swing.j2s.model.editor.selection.SelectionListener;
import org.sgx.swing.j2s.model.events.EventListener;
import org.sgx.utils.Utils;

/**
 * se puede meter cualquier clase de objetos adentro. se mostrar�n, tanto en la jlist como en la jcombob utilisando toString method
 *  
 * @author sg
 *
 * @param <T>
 */
public class ListSelectionEditor<T> extends JPanel implements Editor<ListSelection<T>>{

	private static final long serialVersionUID = 1L;
	String title;
	ListSelection<T> object;
	JComponent selectable;
	public ListSelectionEditor(){title="untitled";object=new ListSelection();};
	public ListSelectionEditor(String title, ListSelection<T> object) {
		super();
		this.title = title;
		this.object = object;
		init();
	}

	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel(title));
		this.add(getSelectionList());		
	}

	private Component getSelectionList() {
		if(object.getMultiple()) {
			selectable=new JList(new DefaultComboBoxModel(Utils.toArray(object.getOptions())));
		}
		else {
			selectable=new JComboBox(new DefaultComboBoxModel(Utils.toArray(object.getOptions())));
		}
		return selectable;
	}

	public ListSelection<T> getCurrentValue() {
		List<T> opts = object.getOptions();
		T[] sel = null;
		if(object.getMultiple()) {
			sel = (T[]) ((JList)selectable).getSelectedValues();
		}
		else {
			sel=(T[]) ((JComboBox)selectable).getSelectedObjects();
		}
		List<T> sellist=Utils.toList(sel);
		return new ListSelection<T>(sellist, opts);
	}

	

	public void setValue(ListSelection<T> value) {
		if(object.getMultiple()) {
			((JList)selectable).clearSelection();
		}		
		Iterator<T> i = value.getSelection().iterator();		
		while(i.hasNext()) {
		
				if(object.getMultiple()) {
					((JList)selectable).setSelectedValue(i.next().toString(), true);
				}
				else {
					((JComboBox)selectable).setSelectedItem(i.next().toString());
				}
		}
	}
}
