package org.sgx.j2s.yui.widget;

import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.LWMap;

public class WidgetHome {
	static WidgetHome instance;
	public static WidgetHome getInstance() {
		if(instance==null)
			instance=new WidgetHome();
		return instance;
	}
	Map<String, WidgetImpl> map;
	private WidgetHome() {
		map=new LWMap<String, WidgetImpl>();
	}
	public void register(WidgetImpl w) {
		map.put(w.getId(), w);
	}
	public WidgetImpl get(String id) {
		return map.get(id);
	}
}
