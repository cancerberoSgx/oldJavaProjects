package org.sgx.pickturemakeup.gui.editors;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.sgx.pickturemakeup.model.PropertyChangeObserver;
import org.sgx.pickturemakeup.test.GUI;
import org.sgx.utils.JavaUtils;




/**
 * componente para seleccionar una sublista de una lista de elementos
 * Notificar� una List<String>
 * @author SGURIN
 *
 */
public class ListSelectionEditor extends PropertyEditor{
	
	private static final long serialVersionUID = 1L;

	private int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;//.SINGLE_INTERVAL_SELECTION;

	private int listOrientation = JList.VERTICAL;//.HORIZONTAL_WRAP;
	
	boolean multiple=true;
	boolean vertical=true;
	List<String> selectionList;

	private JList list;
	
	public ListSelectionEditor(String propId, List<String> selectionList, boolean multiple, boolean vertical) {
		super(propId);
		this.selectionList=selectionList;
		this.multiple=multiple;
		init();
	}
	
	public ListSelectionEditor(String propId, List<String> selectionList) {
		this(propId, selectionList, true, true);
	}
	
	void updateListMode() {
		list.setSelectionMode(selectionMode);
        list.setLayoutOrientation(listOrientation);	
	}

	private void init() {
		Object[] data = selectionList.toArray();		
        list = new JList(data);
        updateListMode();
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object[] selection=JavaUtils.subarray(selectionList.toArray(), list.getSelectedIndices());
				ListSelectionEditor.this.notifyAll(JavaUtils.toList(selection));
			}        	
        });
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.add(listPane, BorderLayout.CENTER);
	}
	
	public int getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(int selectionMode) {
		this.selectionMode = selectionMode;
		updateListMode();
	}

	public int getListOrientation() {
		return listOrientation;
	}

	public void setListOrientation(int listOrientation) {
		this.listOrientation = listOrientation;
		updateListMode();
	}
	
	//test
	public static void main(String [] a) {
		List<String >l=new LinkedList<String >();
		l.add("hola"); 
		l.add("mundo");
		l.add("que");
		l.add("tal");
		ListSelectionEditor lse = new ListSelectionEditor("jaja ", l);
		GUI.putInFrameAndShow(lse);
	}

	
	
}
