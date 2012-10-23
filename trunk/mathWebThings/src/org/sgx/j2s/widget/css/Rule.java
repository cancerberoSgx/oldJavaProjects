package org.sgx.j2s.widget.css;

import java.util.Map;
import java.util.Set;

public class Rule {
	String name;
	Map<String, String> cssProps;

	public Rule(String name, Map<String, String> cssProps) {
		super();
		this.name = name;
		this.cssProps = cssProps;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getCssProps() {
		return cssProps;
	}
	public void setCssProps(Map<String, String> cssProps) {
		this.cssProps = cssProps;
	}
public boolean equals(Object r) {
	return (r instanceof Rule) && ((Rule)r).name.equals(name);
}
public Set<String> keySet() {
	return cssProps.keySet();
}
public String put(String key, String value) {
	return cssProps.put(key, value);
}
public void putAll(Map<? extends String, ? extends String> t) {
	cssProps.putAll(t);
}
public int size() {
	return cssProps.size();
}
public String toString(){
	return "Rule("+name+" - "+cssProps+")";
}
}
