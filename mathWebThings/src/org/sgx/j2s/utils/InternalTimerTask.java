package org.sgx.j2s.utils;
/**
 * @j2sIgnore
 * 
 * @author SGURIN
 *
 */
public class InternalTimerTask extends java.util.TimerTask{
	private Runnable r;

	public InternalTimerTask(Runnable r) {
		super();
		this.r=r;
	}
	@Override
	public void run() {
		r.run();
	}
}


