package org.sgx.jmencoder.options;

import java.io.File;

import org.sgx.j2s.model.base.ListSelection;
/**
 * 
 * @author nati
 *
 */
public class SubtitleOption {
File subtitleFile=null;
SubtitleFontOption font = new SubtitleFontOption();
String cp="latin1", lang="es";//TODO ListSelection<String>
/**
 * in ms
 */
String subDelay="0";

public String getSubDelay() {
	return subDelay;
}

public void setSubDelay(String subDelay) {
	this.subDelay = subDelay;
}

//public SubtitleOption(File subtitleFile, SubtitleFontOption font) {
//	super();
//	this.font=font;
//	this.subtitleFile=subtitleFile;
//}

public SubtitleOption() {
	this(null, new SubtitleFontOption());
}

public SubtitleOption(File subtitleFile, SubtitleFontOption font) {
	super();
	this.subtitleFile = subtitleFile;
	this.font = font;
}
public SubtitleOption(SubtitleFontOption font) {
	this(null, font);
}
//
//public File getSubtitleFile() {
//	return subtitleFile;
//}
//
//public void setSubtitleFile(File subtitleFile) {
//	this.subtitleFile = subtitleFile;
//}

public SubtitleFontOption getFont() {
	return font;
}

public void setFont(SubtitleFontOption font) {
	this.font = font;
}

public String getCp() {
	return cp;
}

public void setCp(String cp) {
	this.cp = cp;
}

public String getLang() {
	return lang;
}

public void setLang(String lang) {
	this.lang = lang;
}


public File getSubtitleFile() {
	return subtitleFile;
}

public void setSubtitleFile(File subtitleFile) {
	this.subtitleFile = subtitleFile;
}

}
