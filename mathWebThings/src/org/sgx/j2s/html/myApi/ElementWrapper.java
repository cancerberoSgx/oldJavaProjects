package org.sgx.j2s.html.myApi;
/**
 * necesary for treat an html object as a java object (argument passing, 
 * use in datatypes, etc)
 * 
 * @author SGURIN
 *
 */
public class ElementWrapper {
Element element;

public Element getElement() {
	return element;
}

public void setElement(Element element) {
	this.element = element;
}

public ElementWrapper(Element element) {
	super();
	this.element = element;
}
}
