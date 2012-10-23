package org.sgx.j2s.yui;

import java.util.Date;

public class BenchMaker {

	public long readTime() {
		return new Date().getTime();
	}
	
	public String printInterval(long t1, long t2) {
		return (t2-t1) +" milliseconds";
	}
	public String printTillNow(long t) {
		return (readTime()-t) +" milliseconds";
	}
	public long readDuration(Runnable r) {
		long t1 = readTime();
		r.run();
		return readTime()-t1;
	}
	public static void main(String[] args) {
		BenchMaker b = new BenchMaker();
		long t1 = b.readTime();
		for(int i=0;i<100;i++) {
			System.out.print("");
		}
		System.out.println();
		System.out.println(b.printTillNow(t1));
		
		long rd = b.readDuration(new Runnable() {
			public void run() {
				for(int i=0;i<100;i++) {
					System.out.print("");
				}
			}			
		});
		System.out.println();
		System.out.println(rd);
	}

}
