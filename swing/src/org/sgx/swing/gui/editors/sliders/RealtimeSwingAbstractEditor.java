package org.sgx.swing.gui.editors.sliders;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.j2s.model.editor.RealtimeEditor;
import org.sgx.swing.j2s.model.events.EditorEvent;
import org.sgx.swing.j2s.model.events.EditorListener;
import org.sgx.swing.j2s.model.events.Event;
import org.sgx.swing.j2s.model.events.EventListener;
import org.sgx.swing.j2s.model.events.EventTarget;

public abstract class RealtimeSwingAbstractEditor<T> extends SimpleBeanEditor<T> implements EventTarget {
	private static final long serialVersionUID = 1L;
	
	List<EditorListener<T>> obs = new LinkedList<EditorListener<T>>();
	boolean _notify=true;
	
	public void notifyAll (EditorEvent<T> e) {
		Iterator<EditorListener<T>> i = obs.iterator();
    	while(i.hasNext()){
    		i.next().notifyEvent(e);
    	}
	}
	
	public void addEventListener(EditorListener<T> l) {
		obs.add(l);
	}

	public void removeEventListener(EditorListener<T> l) {
		obs.remove(l);
	}
	
	
}
