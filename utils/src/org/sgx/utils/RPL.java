package org.sgx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * replace strings with new strings in multiple text files recursively over
 * directories and supports limiting the search to specific file names.
 * 
 * @author sgurin
 * 
 */
public class RPL {
	public static void copyDirAndReplaceRecursively(File src, File dest,
			String filter, String str, String replacement)
			throws FileNotFoundException, IOException {
		if (src.isDirectory()) {
			dest.mkdirs();
			File[] childs = src.listFiles();
			for (int i = 0; i < childs.length; i++) {
				File child = childs[i];
				File destChild = new File(dest.getAbsolutePath()
						+ File.separator + child.getName());
				copyDirAndReplaceRecursively(child, destChild, filter, str,
						replacement);
			}
		} else {
			if (src.getName().contains(filter)) {
				copyFileAnReplacetext(src, dest, str, replacement);
			} else {
				copyFileAnReplacetext(src, dest, null, null);
			}
		}

	}

	public static void copyFileAnReplacetext(File srcFile, File destFile,
			String str, String replacement) throws FileNotFoundException,
			IOException {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(srcFile);
		readFile(is, sb);
		is.close();
		String s = sb.toString();
		if (str != null && replacement != null)
			s = s.replace(str, replacement);
		FileOutputStream os = new FileOutputStream(destFile);
		os.write(s.getBytes());
		os.close();
	}

	public static void readFile(InputStream in, StringBuffer sb)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
	}

	// test
	public static void main(String[] args) {
		try {
			File srcDir = new File(
					"/home/sebastian/desarrollo/portal4/blogsJavas/src2"), destDir = new File(
					"/home/sebastian/desarrollo/portal4/blogsJavas/src");
			String str = "com.liferay.portlet.blogs", repl = "org.sgx.portlet.circulares";
			FSUtils.copyDirAndReplaceRecursively(srcDir, destDir, ".java", str,
					repl);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
