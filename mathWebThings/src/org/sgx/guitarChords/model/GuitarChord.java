package org.sgx.guitarChords.model;

public class GuitarChord {
GuitarNote [] notes;
String [] names;

public String toXmlRepr() {
	String s = "<guitarChord><names>";
	for (int i = 0; i < names.length; i++) {
		s+="<name>"+names[i]+"</name>";
	}
	s+="</names>";
	for (int i = 0; i < notes.length; i++) {
		GuitarNote n = notes[i];
		
	}
	return s+"</guitarChord>";
}
}
