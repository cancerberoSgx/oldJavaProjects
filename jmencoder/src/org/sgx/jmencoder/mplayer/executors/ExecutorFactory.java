package org.sgx.jmencoder.mplayer.executors;

import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.IExecutorFactory;

public class ExecutorFactory implements IExecutorFactory {
	static ExecutorFactory inst;
	public static ExecutorFactory getInstance() {
		if(inst==null)
			inst=new ExecutorFactory();
		return inst;
	}
	private ExecutorFactory(){}
	public Executor getExecutor() {
		return new MencoderExecutorImpl();
//		return new ExecutorImpl1();
	}
	
	public static Executor getDefaultExecutor() {
		return getInstance().getExecutor();
	}
}
