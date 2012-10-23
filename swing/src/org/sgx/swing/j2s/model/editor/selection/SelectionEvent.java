package org.sgx.swing.j2s.model.editor.selection;

public class SelectionEvent<T> {
T data;
SelectionTarget<T> src;
public T getData() {
	return data;
}
public void setData(T data) {
	this.data = data;
}
public SelectionTarget<T> getSrc() {
	return src;
}
public void setSrc(SelectionTarget<T> src) {
	this.src = src;
}
public SelectionEvent(T data, SelectionTarget<T> src) {
	super();
	this.data = data;
	this.src = src;
}

}
