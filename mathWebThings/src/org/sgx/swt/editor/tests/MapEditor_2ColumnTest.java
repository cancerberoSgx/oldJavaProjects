package org.sgx.swt.editor.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.j2s.widget.base.ListSelection;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.basic.ArrayWrapper;
import org.sgx.swt.editor.generic.MapEditor_2columns;

public class MapEditor_2ColumnTest {

	public MapEditor_2ColumnTest(String name, Integer age, Boolean woman) {
		super();
		this.name = name;
		this.age = age;
		this.woman = woman;
	}

	String name;
	Integer age;
	Boolean woman;
	ListSelection<String> hobbies = new ListSelection<String>(new String[]{"1", "2", "3"}, new String[]{"2"}, true);
	
	private static void testObject() {
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(new FillLayout());
		MapEditor_2columns ed = new MapEditor_2columns(shell , SWT.NONE);
		ed.setValue(new MapEditor_2ColumnTest("seba", 18, false));
		SWTUtils.showShell(shell);
	}
	public static void main(String[] args) {
//		testArrayW();
		testObject();
	}

	private static void testArrayW() {
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(new FillLayout());
		MapEditor_2columns ed = new MapEditor_2columns(shell , SWT.NONE);
		ed.setValue(new ArrayWrapper(
			new Object[][]{
				{"numberAttr",new Integer(123)},
				{"stringAttr", "un string"},
				{"an obj", new ArrayWrapper(new Object[][]{
						{"numberAttr",new Integer(123)},
						{"stringAttr", "un string"}}, 2)}
		}, 2));
		SWTUtils.showShell(shell);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getWoman() {
		return woman;
	}

	public void setWoman(Boolean woman) {
		this.woman = woman;
	}

	public ListSelection<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ListSelection<String> hobbies) {
		this.hobbies = hobbies;
	}


	
}
