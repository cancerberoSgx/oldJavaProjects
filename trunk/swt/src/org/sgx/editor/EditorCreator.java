package org.sgx.editor;

import org.eclipse.swt.widgets.Composite;

public interface EditorCreator<T> {
Editor<T> create(Composite parent, int style);
}
