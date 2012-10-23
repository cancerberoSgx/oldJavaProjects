package org.sgx.webMatrix.logic;

import java.util.Map;

import org.sgx.math.Jama.Matrix;

public class ResultsHtmlPrinter {
public String printHtmlResults(Map<String, Object>results) {
	String s="<html><head><title></title></head><body>";
	s+="<table><tr>";
	for(String k : results.keySet()) {
		s+="<td><b>"+k+"</b></td>";
	}
	s+="</tr><tr>";
	for(String k : results.keySet()) {
		Object v = results.get(k);
		s+="<td>"+htmlize(v)+"</td>";
	}
	s+="</tr></table>";
	int count = results.keySet().size();
	
	for (int i = 0; i < count; i++) {
		
	}
	return s+"</body></html>";
}

private String htmlize(Object v) {
	if(v instanceof Matrix) {
		return Logic.matrixToHtml(Logic.toStringArray((Matrix)v));
	}
	else
		return v+"";
}
}
