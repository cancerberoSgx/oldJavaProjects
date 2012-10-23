package org.sgx.j2s.model.editor.selection;
/**
 * being able to emit selection events
 * 
 * @author sgurin
 *
 * @param <T>
 */
public interface SelectionTarget<T> {
void addSelectionListener(SelectionListener<T>l);
void removeSelectionListener(SelectionListener<T>l);
}
