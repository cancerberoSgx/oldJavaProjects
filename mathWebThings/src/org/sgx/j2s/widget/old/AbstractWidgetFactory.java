package org.sgx.j2s.widget.old;
//package org.sgx.j2s.widget;
//
////import org.sgx.j2s.js.JsUtils;
////import org.sgx.j2s.util.Map;
////import org.sgx.j2s.util.impl.LWMap;
////import org.sgx.j2s.yui.widget.HtmlYuiImplWidgetFactory;
////import org.sgx.javaWidgets.SwingWidgetFactory;
//
//
//public abstract class AbstractWidgetFactory  implements WidgetFactory{
//	
//	//native widget factories
//	WidgetFactory javaFactory=null, jsFactory=null;
//	Map<Integer, WidgetFactory> nativeWidgetFact = new LWMap<Integer, WidgetFactory>();	
//	public WidgetFactory getNativeFactory() {
//		if(JsUtils.isJs()) {
//			return jsFactory;
//		}
//		else
//			return javaFactory;
//	}
//	public Widget createWidget(Class _class) {
//		return getNativeFactory().createWidget(_class);
//	}
//	private void initDefaultNativeFactories() {
//		javaFactory = new SwingWidgetFactory();
//		jsFactory=new HtmlYuiImplWidgetFactory();
//	}
//	
//	/**
//	 * subclasses must initialize this singleton
//	 */
//	protected static AbstractWidgetFactory inst;
//	public static AbstractWidgetFactory getInstance() {
//		if(inst==null)
//			throw new RuntimeException("subclass responsability: subclasses must initialize AbstractRepresentationFactory singleton");
//		else
//			return inst;
//	}
//	protected AbstractWidgetFactory() {
//		initDefaultNativeFactories();
//	}
//	
//
//
//	private Map<Widget, Object> reprMap = new LWMap<Widget, Object>();
//	
//	public abstract Object createRepresentationFor(Widget w);
//
//	public abstract Object getRepresentationOf(Widget w);
//	public Map<Widget, Object> getReprMap() {
//		return reprMap;
//	}
//
//}
