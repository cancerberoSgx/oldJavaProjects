package org.sgx.j2s.html.myApi;

public class EventWrapper {
Event event;

public EventWrapper(Event event) {
	super();
	this.event = event;
}

public Event getEvent() {
	return event;
}

public void setEvent(Event event) {
	this.event = event;
}
}
