package org.sgx.j2s.model.editor.selection;

public interface SelectionListener<T> {

	void notify(SelectionEvent<T> e);
}
