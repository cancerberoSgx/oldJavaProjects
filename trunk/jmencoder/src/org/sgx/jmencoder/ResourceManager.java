package org.sgx.jmencoder;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceManager {
	private static final String RESOURCE_BASE = "resources" + File.separator
			+ "icons";
	private static final String DEFAULT_ICON_EXTENSION = "png";
	private static ResourceManager instance;

	private ResourceManager() {
	}

	public static ResourceManager getInstance() {
		if (null == instance) {
			instance = new ResourceManager();
		}
		return instance;
	}

	public Icon getIcon(String name) {
		return new ImageIcon(RESOURCE_BASE + File.separator + name + "."
				+ DEFAULT_ICON_EXTENSION);
	}

}
