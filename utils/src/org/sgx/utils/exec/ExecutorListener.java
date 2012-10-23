package org.sgx.utils.exec;
import java.util.ArrayList;


public interface ExecutorListener {




	public void notifyEnd(EndExecutionInformation endExecutionInformation);

	public void notifyOuput(String str);

	public void notifyError(String str);

	public void notifyMovieHeader(String movieHeader);
}
