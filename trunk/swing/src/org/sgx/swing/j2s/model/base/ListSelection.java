package org.sgx.swing.j2s.model.base;

import java.util.*;

import org.sgx.utils.Utils;

//import org.sgx.j2s.util.List;
//import org.sgx.j2s.util.impl.LinkedList;
//import org.sgx.j2s.util.impl.Utils;

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

public boolean getMultiple() {
	return multiple;
}

public void setMultiple(boolean multiple) {
	this.multiple = multiple;
}

public ListSelection(List<T> options, List<T> selection) {
	super();
	this.options = options;
	this.selection = selection;
}
public ListSelection(T[]options, T[]selections) {	
	this(Utils.toList(options), Utils.toList(selections));
}
}
