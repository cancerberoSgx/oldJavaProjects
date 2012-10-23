//package org.sgx.j2s.html.myApi;
//
//import org.sgx.j2s.widget.events.EventListener;
//
//public class Test1 {
//
//	
//	public static void main(String[] args) {
//		test1();
//	}
//
//	private static void test1() {
//		Element span1 = DomUtils.createElement("span", DomUtils.getBody());
//		span1.innerHTML="hello";
//		span1.id="span1";
//		EventManager.getInstance().addEventListener("span1", Event.onclick, new EventListener(){
//			@Override
//			public void handleEvent(org.sgx.j2s.widget.events.Event evt) {
//				System.out.println("click on span1");
//			}			
//		});
//		
//	}
//}
