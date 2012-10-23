package org.sgx.swt.editor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.sgx.editor.Editor;
import org.sgx.j2s.widget.base.ListSelection;
//import org.sgx.editor.EditorCreator;
import org.sgx.swt.editor.basic.ArrayEditor;
import org.sgx.swt.editor.basic.ArrayWrapper;
import org.sgx.swt.editor.basic.BooleanEditor;
import org.sgx.swt.editor.basic.DoubleEditor1;
import org.sgx.swt.editor.basic.IntegerEditor1;
import org.sgx.swt.editor.basic.ListSelectionEditor;
import org.sgx.swt.editor.basic.StringEditor1;
import org.sgx.swt.editor.generic.MapEditor_2columns;

/**
 * must be singleton for registering classes. see StringEditor1 for see 
 * how to use this for registering editor
 * 
 * in this factory we use java types names (i.e java.lang.String for Strings)
 * for naming types and type[] for 1d arrays, type[][] for 2d arraym, etc. 
 * (i.e "java.lang.String[][]" for a matrix string)
 *
 * @author sgurin
 * 
 * @param <T>
 */
public class SWTEditorFactory {
	
	public  static final String INTEGER_TYPE = "Integer", 
	STRING_TYPE="String", REAL_TYPE="Real", MAP_TYPE="Map", ARRAY_TYPE="Array", 
	BOOLEAN_TYPE = "Boolean", SELECTION_TYPE = "Selection";

	
	static SWTEditorFactory instance;
	 public static SWTEditorFactory getInstance(){
		 if(instance==null) {
			 instance=new SWTEditorFactory(); 
		 }
		 return instance;
	 }
	 Map<String, SWTEditorCreator> editorCreators;
	 private  SWTEditorFactory(){ 
		 init();
	 }
	private void init() {

		 editorCreators = new HashMap<String, SWTEditorCreator>();		
		 
		 registerEditor(new String[]{"java.lang.String", "String"}, 
				new SWTEditorCreator<String>() {
					public Editor<String> create(Composite parent, int style) {
						return new StringEditor1(parent, style);
					}
				}
		); 
		registerEditor(new String[]{"java.lang.Integer", "Integer"},
				new SWTEditorCreator<Integer>() {
					public Editor<Integer> create(Composite parent, int style) {
						return new IntegerEditor1(parent, style);
					}
				}
		); 
		registerEditor(new String[]{REAL_TYPE, "java.lang.Double", "java.lang.Float", "Double", "Float"}, 
				new SWTEditorCreator<Double>() {
					public Editor<Double> create(Composite parent, int style) {
						return new DoubleEditor1(parent, style);
					}
				}
		); 
		registerEditor(new String[]{SELECTION_TYPE, ListSelection.class.getName()}, 
				new SWTEditorCreator<ListSelection<String>>() {
					public Editor<ListSelection<String>> create(Composite parent, int style) {
						return new ListSelectionEditor(parent, style);
					}
				}
		);
		
		registerEditor(new String[]{BOOLEAN_TYPE, "java.lang.Boolean"}, 
				new SWTEditorCreator<Boolean>() {
					public Editor<Boolean> create(Composite parent, int style) {
						return new BooleanEditor(parent, style);
					}
				}
		);
		registerEditor(new String[]{"java.lang.Map", "Map"},
				new SWTEditorCreator<Map>() {
					public Editor<Map> create(Composite parent, int style) {
						return (Editor)new MapEditor_2columns(parent, style);
					}					
				}
		); 
		registerEditor(new String[]{ARRAY_TYPE},
				new SWTEditorCreator<ArrayWrapper>() {
					public Editor<ArrayWrapper> create(Composite parent, int style) {
						return (Editor)new ArrayEditor(parent, style);
					}					
				}
		); 
		
	}
	private void registerEditor(String[] strings,
			SWTEditorCreator creator) {
		for (int i = 0; i < strings.length; i++) {
			registerEditor(strings[i], creator);
		}
	}
	public Editor getEditorFor(String typeName, Composite parent, int style) {		
		if(editorCreators==null)
			init();
		
		SWTEditorCreator creator = editorCreators.get(typeName);
		if(creator!=null){
			return creator.create(parent, style);
		}
		else {
			System.out.println("editor for type "+typeName+" not found");
			return null;
		}
	}
	/**
	 * get a suitable editor for object o and load it with with o
	 */
	public Editor getEditorForObject(Object o, Composite parent, int style) {
		Editor ed = getEditorFor(getTypeNameFor(o), parent, style);
		ed.setValue(o);
		return ed;
	}
	
	public String getTypeNameFor(Object o) {
		if(o instanceof Integer) {
			return INTEGER_TYPE;
		}
		else if(o instanceof String) {
			return STRING_TYPE;
		}
		else if(o instanceof Double || o instanceof Float) {
			return REAL_TYPE;
		}
		else if(o instanceof Boolean) {
			return BOOLEAN_TYPE;
		}
		else if(o instanceof ArrayWrapper) {
			return ARRAY_TYPE;
		}
		else if(o instanceof Map) {
			return MAP_TYPE;
		}
		else if(o instanceof ListSelection) {
			return SELECTION_TYPE;
		}
		else {
			System.out.println("type of object "+o+" not recognized or supported");
			return null;
		}
	}
	
	public void registerEditor(String typeName, SWTEditorCreator creator) {
		editorCreators.put(typeName, creator);
	}

	public Collection<String> getSupportedTypeNames(){
		return editorCreators.keySet();
	}
}
