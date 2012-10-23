package org.sgx.guitarChords.model;

public class GuitarNote {
	int stringNumber; 
	/**
	 * finger number suggestion. can be negative for no sugestion
	 */
	int fingerNumber; 
	/** numero del traste. 0 when no fret is pulsed */
	int fretNumber;

	public Object[][] toArrayRepr() {
		return new Object[][]{
				{"stringNumber", stringNumber},
				{"fingerNumber", stringNumber},
				{"fretNumber", fretNumber}
		};
	}
	
	public String getNotename(){
		return null;}
	
	public String getXmlRepr() {
		return "<guitarNote stringNumber=\""+stringNumber+"\" " +
				"fingerNumber=\""+fingerNumber+"\" " +
				"fretNumber=\""+fretNumber+"\" />";
	}
}
