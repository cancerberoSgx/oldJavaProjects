package org.sgx.utils.exec;
import java.util.ArrayList;


public class EndExecutionInformation {
	ArrayList outputList; ArrayList errorList;
	int exitVal;
	/**duration in miliseconds*/
	long dur;
	public EndExecutionInformation(ArrayList outputList, ArrayList errorList,
			int exitVal) {
		super();
		this.outputList = outputList;
		this.errorList = errorList;
		this.exitVal = exitVal;
	}
	public String  toString() {
		return outputList.toString();
	}
	public long getDur() {
		return dur;
	}
	public ArrayList getErrorList() {
		return errorList;
	}
	public int getExitVal() {
		return exitVal;
	}
	public ArrayList getOutputList() {
		return outputList;
	}

}
