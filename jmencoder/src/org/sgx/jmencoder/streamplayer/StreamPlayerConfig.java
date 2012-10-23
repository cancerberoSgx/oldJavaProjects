package org.sgx.jmencoder.streamplayer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.jmencoder.mplayer.executors.MplayerExecutor;
import org.sgx.jmencoder.streamplayer.ConnectionUtils.ReadUrlListener;
import org.sgx.jmencoder.streamplayer.gui.PlaybackPanel;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;
import org.sgx.utils.FSUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;
/**
 * main streamplayer logistic and commands. 
 * simple support for playlist files like asx, pls, ram, etc 
 * 
 * @author sgurin
 *
 */
public class StreamPlayerConfig  extends MplayerExecutor  {
	
	private static final String STREAMDUMP_DIR = "streamPlayerTmp";
	private static final String DEFAULT_AUDIO_OUTPUT_FORMAT = "mp3";
	private static final int DEFAULT_AUDIO_OUTPUT_BITRATE = 128;
	public static final String PLAYLIST_FILE_EXTENSION = "xml";
	private static StreamPlayerConfig instance;

	private StreamPlayerConfig() {
		setDebug(false);
	}

	public static StreamPlayerConfig getInstance() {
		if (null == instance) {
			instance = new StreamPlayerConfig();
		}
		return instance;
	}

	private File dumpFile;

	
	//IO notifications from mplayer command
	@Override
	public void notifyError(String str) {
		super.notifyError(str);
		if(ioListener!=null)
			ioListener.notifyErr(str);
	}
	@Override
	public void notifyOuput(String str) {
		super.notifyOuput(str);
//		System.out.println("from config: "+str);
		if(ioListener!=null)
			ioListener.notifyOut(str);
	}
	public void notifyEnd(EndExecutionInformation endExecutionInformation) {
		super.notifyEnd(endExecutionInformation);
//		System.out.println("from config: "+str);
		if(ioListener!=null)
			ioListener.notifyEnd();
	}
	
	// operations
	public void doPlay(StreamEndpoint endpoint) throws IOException {
		doQuit();
		final String url = endpoint.getUrl() ;
		if(isComplexFile(url))
			playComplexFile(url);
		else
			playStream(url);
			
	}
//	protected void playStreams(List<String> endpUrls) {
//		List<String> l = new LinkedList<String>();
//		l.add(e)
//	}

	private static String[]complexExtensions = {".pls", ".m3u", ".wax", ".asx", ".ram"};	
	private boolean isComplexFile(String url) {
		String s = url.toLowerCase();
		for (int i = 0; i < complexExtensions.length; i++) 
//			if(s.endsWith(complexExtensions[i]))
			if(s.contains(complexExtensions[i]))
				return true;		
		return false;
	}

	private void playComplexFile(final String url) {
//		List<String> l = new LinkedList<String>();
		final String urlLowerCase=url.toLowerCase();
		System.out.println("complex3 : "+url);
		ConnectionUtils.readUrlAsync(url, new ConnectionUtils.ReadUrlListener() {
			@Override
			public void notify(StringBuffer sb) throws Exception {	

				String s = sb.toString();
				
				//first of all sometimes, no matter the type, only the endpoint url is in the content...
				if(ConnectionUtils.isURL(s.trim())) {
					playStream(s);
				}
				
				else if (urlLowerCase.contains(".pls")) {
					String[] items = s.split("\\s");

					for (int i = 0; i < items.length; i++) {
						String item = items[i].toLowerCase();
						if (ConnectionUtils.isURL(item)) {
							playStream(items[i]);
						} else if (item.startsWith("file1=")) {
							s = items[i].substring(6, items[i].length());
							if (ConnectionUtils.isURL(s)) {
								playStream(s);
								break;
							}
						}
					}
				}
				else if (urlLowerCase.contains(".m3u")
					|| urlLowerCase.endsWith(".wax")) {
					List<String> urls = ConnectionUtils.findUrlsIn(s);
					Collections.reverse(urls);
					for (Iterator iterator = urls.iterator(); iterator
							.hasNext();) {
						String string = (String) iterator.next();
						if (!string.endsWith(".m3u")) {
							playStream(string);
						} else if (!string.equals(url)
								// anidated m3u references 
								&& urlLowerCase.endsWith(".m3u")) 
							ConnectionUtils.readUrlAsync(string, this);
						break;
					}
				}
				else if(urlLowerCase.contains(".asx")) {
					String[] parts = s.split("\\<");
					for (int i = 0; i < parts.length; i++) {
						String el = parts[i].toLowerCase();
						if(el.startsWith("ref")) {
							List<String> endpUrls = ConnectionUtils.matchUrlsIn(s,"\\\"");
							playStream(endpUrls);
						}
					}
				}
				else if(urlLowerCase.contains(".ram")) {
//					String[] parts = s.split("\\n");
//					if(parts.length>1)
//						playStream(parts[0]);
					
					
					List<String> urls = ConnectionUtils.findUrlsIn(s);
					Collections.reverse(urls);
//					System.out.println("RAM "+urls.size()+": "+s);
					playStream(urls);
					
					
				}
			}
		});
		}
		

//			// TODO: los asx y creo que tb los wax son un xml. si se pudiera
//			// parsear sin validar, el tema sería buscar el promer elmento ref
//			// por ej <ref href="http://69.64.52.60:7048" />:
//
//			ModelUtils.readUrlAsync(url, new ReadUrlListener() {
//				@Override
//				public void notify(StringBuffer sb) throws Exception {
//					String s = sb.toString();
//
//					
//				}
//			});
//		} 
//		
//			//this, sometimes, works:
////			ModelUtils.readUrlAsync(url, new ReadUrlListener() {
////				@Override
////				public void notify(StringBuffer sb) throws Exception {
////					String s = sb.toString();
////			)		List<String> urls = ModelUtils.findUrlsIn(s);
////					Collections.reverse(urls);
////					for (Iterator iterator = urls.iterator(); iterator
////							.hasNext();) {
////						String string = (String) iterator.next();
////						if (!string.endsWith(".m3u")) {
////							playStream(string);
////						} else if (!string.equals(url)
////								// anidated m3u references 
////								&& urlLowerCase.endsWith(".m3u")) 
////							ModelUtils.readUrlAsync(string, this);
////						break;
////					}
////				}
////			});
//			
//			
//			//a very dirty way of extracting endpoints urls from asx files. we can't xml parse this files since people use very dirty xml (invalid)
//			ModelUtils.readUrlAsync(url, new ReadUrlListener() {
//			@Override
//			public void notify(StringBuffer sb) throws Exception {
//				String s = sb.toString();
////				System.out.println(s);
//				//try to partition between xml elements
//				
//			}
//		});
//		}
//	}

	
	public void doQuit() {
		try {
			enterCommand("quit");
			Thread.sleep(200);
		} catch (Exception ex) {
		}
	}

	public void doRecord(StreamEndpoint endpoint) throws IOException {
		String streamdumpFileName = FSUtils.transformValidFileName(endpoint
				.getName()
				+ " - " + df.format(new Date()), "_");
		File dumpsDir = new File(STREAMDUMP_DIR);
		if (dumpsDir.exists() && !dumpsDir.isDirectory())
			throw new IOException("internal error: streamdump directory "
					+ STREAMDUMP_DIR + " exists and is not a dir!");
		dumpFile = new File(STREAMDUMP_DIR + File.separator
				+ streamdumpFileName);
		recordStream(endpoint.getUrl(), dumpFile);

	}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	private IOListener ioListener=null;

	/**
	 * convert anything to mp3 ffmpeg -i ficheroEntrada.wma -f mp3 -ab 192
	 * ficheroSalida.mp3
	 * 
	 * @param videoOutput
	 * @throws IOException
	 */
	public void doSaveRecordedAsMp3(File videoOutput, String outputFormat,
			int bitrate, ExecutorListener executorListener) throws IOException {
		if (dumpFile != null) {
			String cmd = MPlayerUtils.ffmpegExe + " -i "
					+ Utils.quotePath(dumpFile.getAbsolutePath())
					+ " -dumpstream -dumpfile "
					+ Utils.quotePath(dumpFile.toString()) + " -f "
					+ outputFormat + " -ab " + bitrate + " "
					+ Utils.quotePath(outputFormat);
			System.out.println("executing: " + cmd);
			MencoderExecutorImpl.execute(cmd, executorListener);
		}
	}

	public void doSaveRecordedAsMp3(File videoOutput,
			ExecutorListener executorListener) throws IOException {
		doSaveRecordedAsMp3(videoOutput, DEFAULT_AUDIO_OUTPUT_FORMAT,
				DEFAULT_AUDIO_OUTPUT_BITRATE, executorListener);
	}

	public void setIOListener(IOListener ioListener) {
		this.ioListener= ioListener;		
	}
	
	
	
	
	
	
	//low level stuf
	public String playStreamCmd(List<String>urls) {
//		if(urls==null||urls.size()==0)
//			return null;
		
		String s = "";
		for (Iterator iterator = urls.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			s+=" "+string;
		}
		s+=" ";
//		return MPlayerUtils.mplayerExe+" -quiet -loop 20 -af extrastereo,equalizer=4:3:2:1:0:0:1:1:0:-1 -cache 312 "+ s;
		
		String mplayerParams = " -slave -loop 5 -cache 312 ";
		
		//fix for .ram files
//		if(urls.get(0).toLowerCase().endsWith(".ram"))
//			mplayerParams=" -playlist ";
		
		return MPlayerUtils.mplayerExe+ mplayerParams + s;	
	}
	//media streaming operations. TODO: put into subclass
	public void playStream(List<String> url) throws IOException {
		if(!executing) {
			String cmd = playStreamCmd(url);
			System.out.println("executing: "+cmd);
			e = MencoderExecutorImpl.execute(cmd, this);	
			executing=true;
		}
	}
	
	public void playStream(String url) throws IOException {
		List<String> l = new LinkedList<String>();
		l.add(url);
		playStream(l);
	}
	/**
	 * mplayer -loop 0 http://streaming.espectador.com/envivo -dumpstream -dumpfile dump1.stream
	 * @param url
	 * @param dumpFile
	 * @throws IOException
	 */
	public void recordStream(String url, File dumpFile) throws IOException {
		if(!executing) {/*-vo directx:noaccel*/
			String cmd = MPlayerUtils.mplayerExe+" -slave -loop 0 ";
				cmd+=" "+url +
				" -dumpstream -dumpfile "+Utils.quotePath(dumpFile.toString()) ;
			System.out.println("executing: "+cmd);
			e = MencoderExecutorImpl.execute(cmd, this);	
			executing=true;
		}	
	}
	
	
	

	//playlist manager stuff
	
}
