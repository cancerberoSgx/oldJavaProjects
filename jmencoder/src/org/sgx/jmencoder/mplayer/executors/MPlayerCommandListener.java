package org.sgx.jmencoder.mplayer.executors;

public interface MPlayerCommandListener {
	void notifyCommandOutput(String commandId, String value);
}
