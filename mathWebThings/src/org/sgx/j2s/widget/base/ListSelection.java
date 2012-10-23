package org.sgx.j2s.widget.base;

import java.util.*;

import org.sgx.j2s.utils.Utils;


/**
 * mmmm.... this is a new concept.... this class represents a (selected) subset from some of (universal) options...
 * 
 * A Listselection instance represents ordered list of options where some of them are diferenced (selected)
 * 
 * natural editors of this datatype are selection list , comboboxes, etc 
 * @author nati
 *
 */
public class ListSelection<T> {
List<T> options;
List<T> selection;
boolean multiple=true;


public ListSelection(List<T> options, List<T> selection) {
	super();
	this.options = options;
	this.selection = selection;
}
public ListSelection(T[]options, T[]selections) {	
	this(Utils.toList(options), Utils.toList(selections));
}
public ListSelection(T[]options, T[]selections, boolean multiple) {	
	this(Utils.toList(options), Utils.toList(selections));
	this.multiple=multiple;
}
public ListSelection(){options=new LinkedList<T>(); selection=new LinkedList<T>(); };
public List<T> getOptions() {
	return options;
}

public void setOptions(List<T> options) {
	this.options = options;
}

public List<T> getSelection() {
	return selection;
}

public void setSelection(List<T> selection) {
	this.selection = selection;
}

public boolean isMultiple() {
	return multiple;
}

public void setMultiple(boolean multiple) {
	this.multiple = multiple;
}

public List<Integer> getSelectedIndexes() {
	LinkedList<Integer> l = new LinkedList<Integer>();
	for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
		T t = (T) iterator.next();
		int ind = options.indexOf(t);
		if(ind==-1)
			throw new RuntimeException("error: a selected value not in options: "+t);
		l.addLast(ind);
	}
	return l;
}
//public int getIndexOf(T t) {
//	int i =0;
//	for (Iterator iterator = options.iterator(); iterator.hasNext();) {
//		T o = (T) iterator.next();
//		if(o.equals(t))
//			return i;
//		i++;
//	}
//	return -1;
//}
public int[] getSelectedIndexesArr() {
	List<Integer> sel = getSelectedIndexes();
	int[]i = new int[sel.size()];
	Iterator<Integer> ite = sel.iterator();
	for (int j = 0; j < i.length; j++) {
		Integer v = ite.next();
		i[j]=v.intValue();
	}
	return i;
}

public String toString() { return Utils.toArray(selection).length+" - "+Utils.toString(Utils.toArray(selection))+
" of "+Utils.toString(Utils.toArray(options));
	
}

}
