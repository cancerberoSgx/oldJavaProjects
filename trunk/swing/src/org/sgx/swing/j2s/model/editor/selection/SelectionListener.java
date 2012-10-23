package org.sgx.swing.j2s.model.editor.selection;

public interface SelectionListener<T> {

	void notify(SelectionEvent<T> e);
}
