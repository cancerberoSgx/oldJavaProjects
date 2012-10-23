package org.sgx.j2s.soundManager;

import org.sgx.j2s.smallPianoApp.Recorder;
import org.sgx.j2s.utils.Utils;

public class Tests {

	public static void main(String[] args) {
		recorderPersistenceTest();
	}

	private static void recorderPersistenceTest() {
		String json="[{\"octave\":4,\"duration\":125,\"bemol\":false,\"name\":\"D\",\"time\":1236574808343},{\"octave\":4,\"duration\":94,\"bemol\":false,\"name\":\"E\",\"time\":1236574808734},{\"octave\":4,\"duration\":219,\"bemol\":false,\"name\":\"F\",\"time\":1236574808437},{\"octave\":4,\"duration\":187,\"bemol\":false,\"name\":\"D\",\"time\":1236574808625},{\"octave\":4,\"duration\":235,\"bemol\":false,\"name\":\"D\",\"time\":1236574807921},{\"octave\":4,\"duration\":110,\"bemol\":false,\"name\":\"E\",\"time\":1236574808390},{\"octave\":4,\"duration\":906,\"bemol\":false,\"name\":\"F\",\"time\":1236574808859},{\"octave\":4,\"duration\":266,\"bemol\":false,\"name\":\"E\",\"time\":1236574808062},{\"octave\":4,\"duration\":94,\"bemol\":false,\"name\":\"F\",\"time\":1236574808265}]";
		Recorder r = new Recorder();
		try {
			r.loadFrom(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Utils.pringMap(r.getRecord()));
	}

}
