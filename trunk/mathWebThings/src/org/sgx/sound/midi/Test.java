package org.sgx.sound.midi;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testPlayerPiano();
		
	}

	private static void testPlayerPiano() {
		try {
			PlayerPiano.main(new String[]{"A B C D E F G +A s/32 D E C D E C /1-->>CEG"});
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
