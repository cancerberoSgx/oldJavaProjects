package org.sgx.j2s.widgetNatives;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.widget.Widget;

/** 
 * user can deribe its concrete widget native i
 * mpl from here if it wish
 * 
 * @author sgurin
 *
 * @param <K>
 */
public abstract class AbstractWidgetNatives<K> implements WidgetNatives<K> {

	Map<K,Widget> M = new HashMap<K,Widget>();
	public AbstractWidgetNatives() {
		M = new HashMap<K,Widget>();
	}
	public Widget getWidget(K k) {
		return M.get(k);
	}

	public void registerWidget(K k, Widget w) {
		M.put(k, w);
	}
	
	
}
