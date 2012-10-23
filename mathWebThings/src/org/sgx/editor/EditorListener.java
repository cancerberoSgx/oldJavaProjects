package org.sgx.editor;

public interface EditorListener {
	/**
	 *
	 * @param o cada editor o jerarquía de editores tendrá su propia definición (protocolo)
	 */
public void editorNotify(Object o);
}
