package org.sgx.swt.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorCreator;

/**
 * must be singleton for registering classes. see StringEditor1 for see 
 * how to use this for registering editor
 * 
 * in this factory we use java types names (i.e java.lang.String for Strings)
 * for naming types and type[] for 1d arrays, type[] for 2d arraym, etc. 
 * (i.e "java.lang.String[][]" for a matrix string)
 *
 * @author sgurin
 * 
 * @param <T>
 */
public class SWTEditorFactory<T> /*implements EditorFactory<T>*/{
	static SWTEditorFactory instance;
	 public static SWTEditorFactory getInstance(){
		 if(instance==null) {
			 instance=new SWTEditorFactory(); 
		 }
		 return instance;
	 }
	 Map<String, EditorCreator<T>> editorCreators;
	 private  SWTEditorFactory(){
		 editorCreators = new HashMap<String, EditorCreator<T>>();		 
	 }
	public Editor<T> getEditorFor(String className, Composite parent, int style) {		
		EditorCreator<T> creator = editorCreators.get(className);
		if(creator!=null)
			return creator.create(parent, style); 
		else
			return null;
	}
	public void registerEditor(String typeName, EditorCreator<T> creator) {
		editorCreators.put(typeName, creator);
	}

}
