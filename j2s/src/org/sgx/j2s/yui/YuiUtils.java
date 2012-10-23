package org.sgx.j2s.yui;

import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.html.dom.NodeList;

public class YuiUtils {
	public static boolean isChildren(Object parent, Object children) {
		YuiElement p = YuiElement.build(parent), c = YuiElement.build(children);
		NodeList nl = (NodeList) p.get(YuiElement.ATTR_CHILDS_NODES);
		for(int i=0;i<nl.length; i++) {
			Node c_ = nl.item(i);
			Object c_id = YuiElement.build(c_).get("id");
			if(c_id!=null&& !c_id.equals("")) {
//				System.out.println("eq: "+c.get("id")+" - "+c_id);
				if(c_id.equals(c.get("id")))
					return true;
			}		
		}
		return false;
	}
}
