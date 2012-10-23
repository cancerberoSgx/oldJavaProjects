package org.sgx.swt.editor.generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.j2s.utils.ModelHelper;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.SWTEditorFactory;
import org.sgx.swt.editor.basic.ArrayWrapper;
/**
 * i accept Maps, ArrayWrappers and Object (java beans) as a model representation
 * @author sgurin
 *
 */
public class MapEditor_2columns extends Composite implements Editor<Map<String, Object>>{

	GridData labelGridData, valueGridData;
	private String lastError;
	Map<String, Editor> propsEditors;
	private GridData compleValueGridData;
	
	public MapEditor_2columns(Composite parent, int style) {
		super(parent, style);
		init();
	}


	private void init() {
		propsEditors=new HashMap<String, Editor>();
		GridLayout glayout = new GridLayout();
		glayout.numColumns=2;
		setLayout(glayout);		
		labelGridData=new GridData();
		valueGridData=new GridData();
		valueGridData.grabExcessHorizontalSpace=true;
		valueGridData.horizontalAlignment = GridData.FILL;
//		valueGridData.grabExcessVerticalSpace=true;
//		valueGridData.verticalAlignment = GridData.FILL;
		
		compleValueGridData = new GridData();
		compleValueGridData.grabExcessHorizontalSpace=true;
		compleValueGridData.horizontalAlignment = GridData.FILL;
		compleValueGridData.grabExcessVerticalSpace=true;
		compleValueGridData.verticalAlignment = GridData.CENTER;		
	
	}

	
	
	public void addEditorListener(EditorListener l) {
		// TODO Auto-generated method stub
		
	}

	public String getLastError() {
		return lastError;
	}

	public Map<String, Object> getValue() {
		Map m = new HashMap<String, Object>();
		for(String k : propsEditors.keySet()) {
			Editor ped = propsEditors.get(k);
			Object pval = ped.getValue();
			if(pval==null) {
				lastError = "property "+k+" : "+ped.getLastError();
//				return null;
			}
			m.put(k, pval);
		}
		return m;
	}

	public void removeEditorListener(EditorListener l) {
		// TODO Auto-generated method stub
		
	}

	public void setValue(Object obj_) {
		if(obj_ == null) {
//			/**
//			 * @j2sNative
//			 * debugger;
//			 */{}
			throw new RuntimeException("MapEditor_2columns : cannot set null value");
		}
		propsEditors=new HashMap<String, Editor>();
		Map obj=null;
		if(obj_ instanceof ArrayWrapper) {
			ArrayWrapper a = (ArrayWrapper)obj_;
			if(a.getDimension()!=2) {
				throw new RuntimeException("MapEditor_2columns : bad arraywrapper. must be 2d");
			}
			obj = Utils.toMap((Object[][])a.getValue());
		}
		else if(obj_ instanceof Map) {
			obj = (Map)obj_;
		}
		else if(obj_ instanceof Object) {
			try {
				obj = ModelHelper.objectToMap(obj_);				
			} catch (Exception e) {
				throw new RuntimeException("MapEditor_2columns can't set value "+obj_);				
			}
		}
		else{
			throw new RuntimeException("this editor requires a Map value");
		}
		for(Object k : obj.keySet()) {
			Object val = obj.get(k);
			Label label = new Label(this, SWT.NONE);
			label.setText(k + ":");
			label.setLayoutData(labelGridData);
			Editor ed = SWTEditorFactory.getInstance().getEditorForObject(val, this, SWT.NONE);
			ed.setValue(val);
			if(!(ed instanceof Control))
				throw new RuntimeException("editor "+ed+"for object "+val+" of type "+val.getClass().getName()+" is not a swt Control");

			propsEditors.put((String)k, ed);
			Control valc = (Control)ed;
			
			if(ed instanceof MapEditor_2columns){
				valc.setLayoutData(compleValueGridData);
			}
			else {
				valc.setLayoutData(valueGridData);
			}
//			valc.setBackground(SWTUtils.colorToSWT(Color.RED));
		}
	}

}
