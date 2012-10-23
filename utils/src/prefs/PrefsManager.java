//package prefs;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.sgx.utils.FSUtils;
//
///**
// * 
// * @author SGURIN
// * 
// * preference values must be primitive or javabeans
// *
// */
//public class PrefsManager<T> {
//	
//	public static String prefsRoot = System.getProperty("user.home")+".jmencoder"+File.separator+"Prefs.xml";
//	public static String prefFolderForProgram(String pName) {
//		return prefsRoot+File.separator+"."+pName+File.separator+"pref";
//	}
//
//	public static File getPrefFile(String program, String key) {
//		return new File(prefFolderForProgram(program)+File.separator+key);
//	}
//
//	
//	public static void savePref(String program, String key, Object val) throws FileNotFoundException {
//		File pf = getPrefFile(program, key);
//		FSUtils.serialize(val, pf);
//	}
//
//	public static Object loadPref(String program, String key) throws FileNotFoundException {
//		File pf = getPrefFile(program, key);
//		return FSUtils.deserialize(pf);
//	}
//	
//	public static void saveProgramPrefs(String program, File output) throws IOException {
//		FSUtils.zipFile(new File(prefFolderForProgram(program)), output, null,null);
//	}
//	/**
//	 * warning this will override a previous preference set
//	 * @param 
//	 * @throws IOException 
//	 */
//	public static void loadProgramPrefs(String program, File prefsFile) throws IOException {
//		FSUtils.unzipFile(prefsFile, new File(prefFolderForProgram(program)), null);
//	}
//}
