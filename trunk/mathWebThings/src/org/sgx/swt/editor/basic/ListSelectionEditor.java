package org.sgx.swt.editor.basic;

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.ListSelection;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.AbstractEditor;

public class ListSelectionEditor extends AbstractEditor<ListSelection<String>>{
Widget select;

	public ListSelectionEditor(Composite parent, int style) {
		super(parent, style | SWT.NONE);
		init();
	}

	private void init() {
//		setLayout(new GridLayout());
		setLayout(new FillLayout());
		GridData gd = new GridData();
//		gd.minimumHeight=100;
		gd.heightHint=100;
		gd.grabExcessHorizontalSpace=true;
		gd.verticalAlignment=GridData.FILL_VERTICAL;
		this.setLayoutData(gd);
		getParent().setLayoutData(gd);
//		setSize(100,100);
	}

	@Override
	public ListSelection<String> getValue() {
		ListSelection<String> l = new ListSelection<String>();
		if(select ==null)
			return null;
		else if(select instanceof Combo) {
			l.setMultiple(false);
			l.setOptions(Utils.toList(((Combo)select).getItems()));
			java.util.List<String> selItemList = new LinkedList<String>();
//			System.out.println(((Combo)select).getSelectionIndex()+" - "+((Combo)select).getItem(((Combo)select).getSelectionIndex()));
			selItemList.add(((Combo)select).getItem(((Combo)select).getSelectionIndex()));
			l.setSelection(selItemList);
//			((Combo)select).setSize(200, 200);
		}
		else if(select instanceof List) {
			l.setMultiple(true);
			l.setOptions(Utils.toList(((List)select).getItems()));
			l.setSelection(Utils.toList(((List)select).getSelection()));
		}
		else throw new RuntimeException("ListSelectionEditor imposible except");
//		((Control)select).setSize(100, 100);
		return l;
	}


	@Override
	public void setValue(Object value) {

		if(value instanceof ListSelection) {
			ListSelection l = ( ListSelection)value;
			SWTUtils.compositeDestroyAllChildrens(this);
			if(l.isMultiple()) {				
				select=new List(this, SWT.MULTI);
				
				String[] items = Utils.toStringArray( Utils.toArray(l.getOptions()));
				((List)select).setItems(items);
				String[] sel = Utils.toStringArray(Utils.toArray(l.getSelection()));
				int[] seli = l.getSelectedIndexesArr();
				((List)select).setSelection(seli);
			}
			else {
				select=new Combo(this, SWT.SINGLE);
				((Combo)select).setItems(Utils.toStringArray(Utils.toArray(l.getOptions())));
				Object[] s = Utils.toArray(l.getSelection());
				if(s.length>0){
					int selInd = SWTUtils.comboIndexOf(((Combo)select), (String) s[0]);
					if(selInd!=-1) 
						((Combo)select).select(selInd);
				}
			}
		}
		else
			throw new RuntimeException("value must be a list selection and was "+value);
		
	}
	
}
