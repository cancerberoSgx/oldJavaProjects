package org.sgx.j2s.widget;

/** we say that a widget representation is the native object that actually represents the widget
 * iun the user agent. for example, in swing the widget representation can be a jpanel while in 
 * javascript a widget representation can be a html element 
 * 
 * the factory is responsible of create and asociate representations to widgets
 * 
 */
public interface WidgetFactory {

	/** creates and return a new widget representation for w*/
	Object createRepresentationFor(Widget w);
	
	/**
	 * return the widget representation asociated with w or create it if necessary (using cvreateRepresentationfor())
	 */
	Object getRepresentationOf(Widget w);
	/**
	 * main widget creation method.
	 * @param _class can be only one of Widget, ImageWidget, textWidget or htmlwidtget class object
	 */
	Widget createWidget(Class _class);
}
