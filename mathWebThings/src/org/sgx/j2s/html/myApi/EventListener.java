package org.sgx.j2s.html.myApi;

public interface EventListener {

	 void dispatch_onmouseout(Event e);
	 void dispatch_onmouseover(Event e);
	 void dispatch_onmousedown(Event e);
	 void dispatch_onmouseup(Event e);
	 void dispatch_onclick(Event e);
	 void dispatch_onmousemove(Event e);

	 void dispatch_onkeydown(Event e);
//	 void dispatch_onkeypress(Event e);
	 void dispatch_onkeyup(Event e);

}
