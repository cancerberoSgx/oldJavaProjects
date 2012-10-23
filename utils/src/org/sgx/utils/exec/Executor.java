package org.sgx.utils.exec;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * a general utility for running programs from the command line
 * 
 * @author sgurin
 *
 */
public class Executor{

	Set<ExecutorListener> listeners;

	protected ArrayList<String> outputList;

	protected ArrayList<String> errorList;
	
	
	protected OutputStream instrm;
	protected BufferedWriter instrmbw;
	
	protected InputStream outstrm;
	protected BufferedReader outstrmbr;

	protected InputStream errstrm;

	protected BufferedReader errstrmbr;

	protected Process proc;

	private Thread stdoutThread;

	private Thread stderrThread;	
	
	public Executor() {
		super();
		listeners=new HashSet<ExecutorListener>();
	}

	public void addListener(ExecutorListener l) {listeners.add(l);}

	
	public boolean localIntError;
	
	public void inputAppendChar(char c) throws IOException {
		instrmbw.append(c);		
	}
	public void inputFlush() throws IOException {
		instrmbw.flush();		
	}
	
	public static class ExecutorThread extends Thread {
		private String cmd;
		Executor e;
		public ExecutorThread(String cmd, Executor e) {
			super();
			this.cmd = cmd;
			this.e = e;
		}
		public void run() {
			try	{
				e.runCommand(cmd);
			}catch (IOException e) { System.err.println(e); }
		}
	}

	public OutputStream getInstrm() {
		return instrm;
	}
	
	public String[] runCommand(String cmd) throws	IOException {

			outputList = new ArrayList<String>(); 
			errorList = new ArrayList<String>(); 

			
			
			
//			 cmd = "/usr/bin/mplayer\\ -slave\\ /media/Disc_/Lois\\ and\\ Clark\\ The\\ New\\ Adventures\\ of\\ Superman\\ -\\ Season\\ 2/Lois.and.Clark.S02E01.DVDRip.XviD-TOPAZ.avi " +
//					" -sub /media/Disc_/Lois\\ and\\ Clark\\ The\\ New\\ Adventures\\ of\\ Superman\\ -\\ Season\\ 2/Lois.and.Clark.TNAoS.S02E01.Madame.Ex.DVDRip.XviD-TOPAZ.srt -slang es -subcp latin1"; 
			
			
			proc = Runtime.getRuntime().exec(cmd);
			
//			proc = Runtime.getRuntime().exec(command, envp, dir)(cmd);
			
			instrm = proc.getOutputStream();

			outstrm = proc.getInputStream();

			//reader of the program std output stream
			outstrmbr = new BufferedReader(new InputStreamReader(outstrm));

			//writer to the program's std input stream
			instrmbw = new BufferedWriter(new OutputStreamWriter(instrm));

			errstrm = proc.getErrorStream();
			errstrmbr = new BufferedReader(new InputStreamReader(errstrm));

			
			stdoutThread = new Thread() {
				public void run() {
					String movieHeader = "";
					boolean doMovieHeader=true;
					try {
						String str;
						while ( (str = outstrmbr.readLine()) != null) {
							for(ExecutorListener l : getListeners()) {
								l.notifyOuput(str);
							}
							if(doMovieHeader)
								movieHeader+=(str+"\n");
							if(str.startsWith("Pos")) {
								doMovieHeader=false;
								for(ExecutorListener l : getListeners()) 
									l.notifyMovieHeader(movieHeader);
							}
						}
					} catch (IOException e) {
						localIntError=true;
						e.printStackTrace();
					}
					System.out.println("*** End of STDOUT stream reading ***");
				}
			};
			stdoutThread.start();

			stderrThread = new Thread() {
				private String err;

				public void run() {
					try {
						while ( (err = errstrmbr.readLine()) != null) {
							for(ExecutorListener l : getListeners()) {
								l.notifyError(err);
							}
						}
					} catch (IOException e) {
						localIntError=true;
						e.printStackTrace();
//						Thread.currentThread().stop();
					}
					System.out.println("*** End of STDERR stream reading ***");
				}
			};
			stderrThread.start();

//			System.err.println("FIN: asdkajsdjshakjshdkaj\nshdkjas\nhdkjjjjjjjjjjjjjjjjj\njjjjjjjjjjj\njjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\njjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
			int exitVal=-1;


			if(!localIntError) {
				try { 
					exitVal = proc.waitFor();			
				}
				catch (InterruptedException e) {
					System.err.println("Process was interrupted"); 
				}
			}
			else {
				System.err.println("Local interruption exception");
			}
			proc.destroy();

			outstrmbr.close();
			errstrmbr.close();

			for(ExecutorListener e : getListeners()) {
				e.notifyEnd(new EndExecutionInformation(outputList, errorList, exitVal));
			}

			return (String[])outputList.toArray(new String[0]); 
		}
	
	public BufferedWriter getInstrmbr() {
		return instrmbw;
	}
	/**
	 * writes in and an end of line character in the current application stdin
	 * easy and dirty way to type commands in a console / readline apps
	 * @param cmd
	 * @throws IOException 
	 */
	public void writeAndFlushLn(String in) throws IOException {
		getInstrmbr().write(in+"\n");
		inputFlush();
	}

	public ArrayList<String> getErrorList() {
		return errorList;
	}

	public InputStream getErrstrm() {
		return errstrm;
	}

	public BufferedReader getErrstrmbr() {
		return errstrmbr;
	}

	public BufferedWriter getInstrmbw() {
		return instrmbw;
	}

	public Set<ExecutorListener> getListeners() {
		return listeners;
	}

	public ArrayList<String> getOutputList() {
		return outputList;
	}

	public InputStream getOutstrm() {
		return outstrm;
	}

	public BufferedReader getOutstrmbr() {
		return outstrmbr;
	}

	public Process getProc() {
		return proc;
	}
	
	public void killProcess() {
		proc.destroy();
	}

	public void setListeners(Set<ExecutorListener> listeners) {
		this.listeners = listeners;
	}
}
