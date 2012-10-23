package org.sgx.j2s.widget.layout;

public class LayoutFactory {
private static LayoutFactory instance;

private LayoutFactory() {
}

public static LayoutFactory getInstance() {
	if (null == instance) {
		instance = new LayoutFactory();
	}
	return instance;
}

public void registerLayout(String name, Class layoutClass) {
	
}

}
