package org.sgx.j2s.widget_copy;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.js.JsUtils;


public abstract class AbstractWidgetHome  implements WidgetHome{
	
	//native widget factories
	WidgetHome javaFactory=null, jsFactory=null;
	Map<Integer, WidgetHome> nativeWidgetFact = new HashMap<Integer, WidgetHome>();	
	public WidgetHome getNativeFactory() {
		if(JsUtils.isJs()) {
			return jsFactory;
		}
		else
			return javaFactory;
	}
	public Widget createWidget(Class _class) {
		return null;
//		return getNativeFactory().createWidget(_class);
	}
//	private void initDefaultNativeFactories() {
//		javaFactory = new SwingWidgetHome();
//		jsFactory=new HtmlYuiImplWidgetHome();
//	}
	
	/**
	 * subclasses must initialize this singleton
	 */
	protected static AbstractWidgetHome inst;
	public static AbstractWidgetHome getInstance() {
		if(inst==null)
			throw new RuntimeException("subclass responsability: subclasses must initialize AbstractRepresentationFactory singleton");
		else
			return inst;
	}
	protected AbstractWidgetHome() {
//		initDefaultNativeFactories();
	}
	


	private Map<Widget, Object> reprMap = new HashMap<Widget, Object>();
	
	public abstract Object createRepresentationFor(Widget w);

	public abstract Object getRepresentationOf(Widget w);
	public Map<Widget, Object> getReprMap() {
		return reprMap;
	}

}
