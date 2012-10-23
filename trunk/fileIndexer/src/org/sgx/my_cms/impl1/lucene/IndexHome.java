package org.sgx.my_cms.impl1.lucene;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.store.Directory;

public class IndexHome {

	private static IndexHome instance;

	private IndexHome() {
		directories=new HashMap<String, Directory>();
	}

	public static IndexHome getInstance() {
		if (null == instance) {
			instance = new IndexHome();
		}
		return instance;
	}
	
	Map<String, Directory> directories;
	
	public Directory get(String dirId) {
		return directories.get(dirId);
	}

	public Set<String> getDirectoryIds() {
		return directories.keySet();
	}

	public Directory put(String key, Directory value) {
		return directories.put(key, value);
	}

	public int directoryCount() {
		return directories.size();
	}

	public Collection<Directory> getDirectories() {
		return directories.values();
	}
	
	
}
