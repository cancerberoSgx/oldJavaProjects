package org.sgx.jmencoder.gui.player;

public class SubLogInfo {
	String txt, subStart, subEnd, subFile;


	public SubLogInfo(String txt, String subStart, String subEnd, String subFile) {
		super();
		this.txt = txt;
		this.subStart = subStart;
		this.subEnd = subEnd;
		this.subFile = subFile;
	}

	public static SubLogInfo parse(String s) {
		String[] a = s.split("\\n");
		String info = a[1];
		if(!info.contains("N: ")||!info.contains("S: ")||!info.contains("E: "))
			throw new RuntimeException("Error parsing subtitle log file contents: "+s);
		
		String[] a1 = s.split("N: ")[1].split("S: ");
		String fileName = a1[0];
		String[] a2 = a1[1].split("E: ");
		String start = a2[0], end = a2[1];
		String txt = "";
		for(int i=2; i<a.length; i++)
			txt+=a[i]+"\n";
		System.out.println("***\nfile:"+fileName+"\nstart:"+start+"\nend:"+end+"\ntxt:"+txt+"\n***");
		return new SubLogInfo(txt, start, end, fileName);
	}

	public String getSubEnd() {
		return subEnd;
	}

	public void setSubEnd(String subEnd) {
		this.subEnd = subEnd;
	}

	public String getSubFile() {
		return subFile;
	}

	public void setSubFile(String subFile) {
		this.subFile = subFile;
	}

	public String getSubStart() {
		return subStart;
	}

	public void setSubStart(String subStart) {
		this.subStart = subStart;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

}
