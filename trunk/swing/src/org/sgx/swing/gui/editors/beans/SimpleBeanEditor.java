package org.sgx.swing.gui.editors.beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.j2s.model.editor.selection.SelectionListener;
import org.sgx.swing.j2s.model.events.EventListener;
import org.sgx.utils.ObjectUtils;


/**
 * this class takes a java bean, and creates a input form where 
 * the user can edit the bean properties. use getSelectedBean() to obtain the "edited" bean
 *  
 * @author SGURIN
 *
 */
public class SimpleBeanEditor<T> extends JPanel implements Editor<T> {
	boolean editable = true;
	public static final Integer TYPE_TEXT = 1;
	public static final Integer TYPE_TEXTAREA = 2;
	public static final Integer TYPE_LIST = 3;
	public static final Integer TYPE_CHECKBOX = 4;	
	
	Map<String, Object> bean;  //  @jve:decl-index=0:
	Class beanClass;
	
	Map<String, JComponent> fieldsMap=new HashMap<String, JComponent>();
	private Collection<String> props;  //  @jve:decl-index=0:
	
	private static final long serialVersionUID = 1L;

	/**
	 * default constructor - not used by the user
	 */
	public SimpleBeanEditor() {
		super();
	}
	public SimpleBeanEditor(Object bean, boolean editable) {
		this();
		this.bean = (bean instanceof Map)?((Map)bean):ObjectUtils.transformObjectToMap_(bean);
		this.beanClass=bean.getClass();
		this.editable=editable;
		initialize();
	}
	
	public SimpleBeanEditor(Object bean) {
		this(bean, true);
	}
	private void initialize() {
		this.setSize(365, 251);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		props = bean.keySet();
		for(String p : props) {
			try {
				JPanel jp = new JPanel();
				jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
				Object o = bean.get(p);
				JComponent comp = createJComponentFor(o, p);
				fieldsMap.put(p, comp);
				jp.add(new JLabel(p));
				jp.add(comp);
				this.add(jp);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, Object> getSelectedData() {
		Map <String, Object> m = new HashMap<String, Object>();
		for(String p : props) {
			m.put(p, getSelectedValueIn(fieldsMap.get(p)));
		}
		return m;
	}
	
	private JComponent createJComponentFor(Object o, String name) {
		return BeanEditorFactory.createEditorFor(o, name, editable);
	}
	
	private Object getSelectedValueIn(JComponent c) {
		return BeanEditorFactory.getSelectedValueIn(c);
	}
	
	
	public boolean isEditable() {
		return editable;
	}
	
	
	
	public T getCurrentValue() {
		return (T) ObjectUtils.transformMapToBean_(getSelectedData(), beanClass);
	}
	public void removeEventListener(EventListener l) {		
	}
	public void setValue(T value) {
		throw new RuntimeException("makeme");
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
