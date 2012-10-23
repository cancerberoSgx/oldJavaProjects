package org.sgx.j2s.widget.events;

public abstract class KeyListener implements EventListener{

	public void handleEvent(Event evt_) {
		if(!(evt_ instanceof UIEvent))
			return;
		UIEvent evt = (UIEvent)evt_;
		if(evt.type==UIEvent.ONKEYPRESSED_TYPE) {
			keyPressed(evt);
		}
		else if(evt.type==UIEvent.ONKEYRELEASE_TYPE){
			keyReleased(evt);
		}		
	}
	
	public abstract void keyPressed(UIEvent e) ;
	public void keyReleased(UIEvent e){}

}
