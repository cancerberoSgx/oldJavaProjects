package org.sgx.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.sgx.utils.zip.ZipListener;


public class FSUtilsTests {

	public static void main(String[] args) {		
		try {
//			zipFileTest();
//			testValidFileName();
			copyDirAndReplaceRecursivelyTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void copyDirAndReplaceRecursivelyTest() throws FileNotFoundException, IOException {
		File srcDir = new File("/home/sebastian/desarrollo/portal4/blogsJavas/src2"), 
			destDir = new File("/home/sebastian/desarrollo/portal4/blogsJavas/src");
		String str = "com.liferay.portlet.blogs", repl = "org.softpoint.portlet.circulares";
		FSUtils.copyDirAndReplaceRecursively(srcDir, destDir, ".java", str, repl);
	}

	private static void testValidFileName() {
		String s="abc12-3_ABX  ---¿?¿-_-?¿";
		System.out.println(FSUtils.transformValidFileName(s, "_"));
	}

	private static void zipFileTest() {
		try {
			FSUtils.zipFile(new File("C:\\tablaturas"),new File("C:\\tablaturas\\abc.zip"), null, new ZipListener() {
				public void notifyFileZip(String abspath, long numOfFiles, long size) {	
					System.out.println("hola:"+Utils.toString(new Object[] {abspath, numOfFiles, size}));
				}			
			} );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
