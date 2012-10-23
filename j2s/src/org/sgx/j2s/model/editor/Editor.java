package org.sgx.j2s.model.editor;

import org.sgx.j2s.model.editor.selection.SelectionListener;

/**
 * una "objeto visual" que interactua con el usuario y al cual le podemos pregunar 
 * el valor seleccionado actualmenteactual
 * 
 * *) para que sirven los editores?
 * *.*) para no tener que dise�ar visualmente;) . 
 * 
 * @author nati
 *
 * @param <T>
 */
public interface Editor<T>{ 
//	public void addEventListener(SelectionListener<T> l);
////
//	public void removeEventListener(SelectionListener<T> l);
	
	public T getCurrentValue() /*throws EditorInvalidStateException*/;

	public abstract void setValue(T value);
}
