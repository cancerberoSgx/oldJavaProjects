package org.sgx.j2s.widget.events;

/**
 * ejemplo de adaptador de event listener para mouse.
 * 
 * @author SGURIN
 *
 */
public abstract class MouseListener implements EventListener{
	public void handleEvent(Event evt_){
		if(!(evt_ instanceof UIEvent))
			return;
		UIEvent evt = (UIEvent)evt_;
		if(evt.type==UIEvent.ONCLICK_TYPE || evt.type==UIEvent.ONMOUSEUP_TYPE){
			click(evt);
			if(evt.count==2)
				doubleClick(evt);
			if(evt.type==UIEvent.ONMOUSEUP_TYPE){
				up(evt);
			}			
		}
		if(evt.type==UIEvent.ONMOUSEDOWN_TYPE){
			down(evt);
		}
	}

	/**
	 * Sent when a mouse button is pressed twice within the 
	 * (operating system specified) double click period.
	 *
	 * @param e an event containing information about the mouse double click
	 *
	 */
	public  void doubleClick(UIEvent e){}
	
	public void click(UIEvent e){}

	/**
	 * Sent when a mouse button is pressed.
	 *
	 * @param e an event containing information about the mouse button press
	 */
	public  void down(UIEvent e){
		
	}

	/**
	 * Sent when a mouse button is released.
	 *
	 * @param e an event containing information about the mouse button release
	 */
	public  void up(UIEvent e){
		
	}
}
