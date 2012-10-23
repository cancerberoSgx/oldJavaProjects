package org.sgx.jmencoder.streamplayer;

public interface IOListener {
void notifyOut(String s);
void notifyErr(String s);
void notifyEnd();
}
