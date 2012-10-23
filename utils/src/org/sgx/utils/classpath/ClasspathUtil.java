//package org.sgx.utils.classpath;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * dynamic classpath loader manager. since all sgx projects uses util proj, 
// * each one can statically register, or ask that som other project be loaded.
// * 
// * i recommend the use of one java file in top level project package named CP.java
// * in wich:
// * 		 is defined the id of the project as a constant
// * 		 register it self
// * 		 and even perhaps declare its dependencies (other proj ids)
// * (take a look at \jmencoder\src\org\sgx\jmencoder\CP.java
// * 		 
// * DO NOT LOAD STATICALLY!! 
// * this was designed to help "main programs" to explicily dynamically load required 
// * projects dependencies and so scripts launchers do not have to be classpath knowers...
// * 
// * @author sgurin
// *
// */
//public class ClasspathUtil {
//	private static ClasspathUtil instance;
//	private Map<String, String> map;
//	private Map<String, Boolean> loaded;
//	private Map<String, String[]> dependencies;
//	private ClasspathUtil() {
//		map=new HashMap<String, String>();
//		loaded=new HashMap<String, Boolean>();
//		dependencies=new HashMap<String, String[]>();
//	}
//	public static ClasspathUtil getInstance() {
//		if(instance==null)
//			instance=new ClasspathUtil();
//		return instance;
//	}
//	
//	
//	public void registerClasspth(String name, String location, String[]dep) {
//		map.put(name, location);
//		dependencies.put(name, dep);
//	}
//	
//	public void load(String name) throws IOException {
//		String loc = map.get(name);
//		if(loc!=null) { 
//			if(!loaded.containsKey(name)) {
//				loaded.put(name, true);
//				ClassPathHacker.addFile(loc);
//				//load dependencies as well
//				String[] deps = dependencies.get(name);
//				if(deps!=null)
//					for (int i = 0; i < deps.length; i++) {
//						load(deps[i]);
//					}
//			}
//		}
//	}
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
