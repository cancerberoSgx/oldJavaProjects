package org.sgx.j2s.model.editor;

import org.sgx.j2s.model.events.EventListener;
import org.sgx.j2s.model.events.EventTarget;

/**
 * user realtime events suscribable editor
 * 
 * @author sgurin
 *
 * @param <T>
 */
public interface RealtimeEditor<T> extends Editor<T>, EventTarget{
	public void addEventListener(EventListener l);

	public void removeEventListener(EventListener l);
}
