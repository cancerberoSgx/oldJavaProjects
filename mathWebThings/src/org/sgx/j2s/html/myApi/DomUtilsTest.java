package org.sgx.j2s.html.myApi;


//import java2js.morphs.primitive.js.WidgetElement;

public class DomUtilsTest {
	

	
	public static void test1() {
		Element e = DomUtils.newElement("test1", "div");
		
		e.style.position="absolute";
		e.style.width="50px";
		e.style.height="50px";
		e.style.top="50px";
		e.style.left="50px";
		e.style.backgroundColor="rgb(222,22,2)";
		
		System.out.println("finnish test1");	
		//System.out.println("finnish "+e.style+" - "+parent.getAttribute("id"));
		
		DomUtils.setStyleProp(e, "background-color", "rgb(222,22,2)");
		
	}
//	
//	public static void test2() {
//		Element e = DomUtils.newElement("test2", "div");
//		CssStyle st = new CssStyle();
//		st.setBackgroundColor(new Color(2,222,2));
//		//	DomUtils.setCssStyleTo(e, st);ç
//		System.out.println("finnish "+e.id);		
//	}
//	
//	public static void test3() {
//		Widget w = new Widget(null);
//		w.setBounds(new Rectangle(82,52,100,200));
//		w.style.setBorderAll(Border.SOLID);
//		Element e = DomUtils.getElementFor(w);
//		DomUtils.setCssStyleTo(e,w.style);
//		DomUtils.setBounds(e,w);
//		System.out.println("test3"+JsUtils.dump(e));
//	}
	
	public static void testgetJsStylePropertyName() {
		System.out.println("startMayus(background-color)=="+
				DomUtils.getJsStylePropertyName("background-color"));
		}
	
	/**
	 * 
	 * @j2sNative
	 alert("hI")
window.addEvent('domready', function(){ alert('the dom is ready');});
	 	
	 */
	public static void main(String[] args) {
		test1();
//		test2();
//		test3();
		testgetJsStylePropertyName();
		System.out.println("finnish");
	}
}




