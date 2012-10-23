package org.sgx.jmencoder.desktrec;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.sgx.j2s.model.editor.selection.SelectionEvent;
import org.sgx.j2s.model.editor.selection.SelectionListener;
import org.sgx.jmencoder.gui.multipleVideoConversor.VideoConversionFrame;
import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.swing.utils.ImageUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

/**
 * puff, too much code in me!
 * @author sgurin
 *
 */
public class DeskRecConfig {

	protected static final String DEFAULT_IMAGE_FORMAT = ImageUtils.FORMAT_PNG;
	private static DeskRecConfig instance;
	private DeskRecMainPanel panel;

	
	//configurable attributes
	boolean minimizeWindowOnRecording = false;
	int recordTimeStep =200;
	
	private DeskRecConfig(DeskRecMainPanel deskRecMainPanel) {
		this.panel=deskRecMainPanel;
		executorListener=new ExecutorListener() {
			public void notifyEnd(
					EndExecutionInformation endExecutionInformation) {
				JOptionPane.showMessageDialog(panel.getTopLevelAncestor(),
						"end of the metamorphosis!. enjoy your new video");
			}
			public void notifyError(String str) {
				panel.outputConsoleAppendText("err:" + str);
			}

			public void notifyMovieHeader(String movieHeader) {
			}

			public void notifyOuput(String str) {
				panel.outputConsoleAppendText("out:" + str);
			}
		};
	}

	public static DeskRecConfig getInstance(DeskRecMainPanel deskRecMainPanel) {
		if (null == instance) {
			instance = new DeskRecConfig(deskRecMainPanel);
		}
		return instance;
	}
	public File getWorkDir() {
		return null;
	}
	public File createVideoDir() {
		return null;
	}
	
	
	//recording things
	
	private Recorder recorder;
	public void record(Rectangle captureArea) throws InterruptedException, IOException {
		//clean working directory
		File workingDir = getScreenshoterCleanWorkingDir();
		
		//take screenshots
		recorder = new Recorder(recordTimeStep, workingDir, captureArea);
		recorder.start();
	}

	String screenShoterWorkingDirPath = "/home/sebastian/borrar";
	private Executor executor;
	private ExecutorListener executorListener;
	
	private File getScreenshoterWorkingDir() {
		return new File(screenShoterWorkingDirPath);
	}
	private File getScreenshoterCleanWorkingDir() throws IOException {
		File f = new File(screenShoterWorkingDirPath);	//TODO: do better
		
		if(!f.exists())
			f.mkdirs();
		else if(!f.isDirectory())
			throw new IOException("cannot create working dir on "+f.getAbsolutePath()+ " cause file exists and is not a directory.");
			
		File[]childs=f.listFiles();
		for (int i = 0; i < childs.length; i++) {
			childs[i].delete();
		}
		return f;
	}
	
	/*
 mencoder mf:///home/sebastian/borrar/*.png -mf w=300:h=300:fps=25:type=png -ovc lavc -lavcopts vcodec=mpeg4:mbd=2:trell -oac copy -o output.avi
	 */
	public void createAvi(String imgDir, String imgFormat, int width,
			int height, int fps, String outputVideo) {
		imgFormat = imgFormat.toLowerCase();
		String cmd = MPlayerUtils.mencoderExe + " mf://" + imgDir
				+ File.separator + "*." + imgFormat + " -mf w=" + width + ":h="
				+ height + ":fps=" + fps + ":type=" + imgFormat
				+ " -ovc lavc -lavcopts vcodec=mpeg4:mbd=2:trell -oac copy -o "
				+ Utils.quotePath(outputVideo);
		callMencoder(cmd, executorListener);
	}

	private void createFlv(String absolutePath, String defaultImageFormat,
			int width, int height, int fps, String absolutePath2) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * # first convert an image sequence to a movie
ffmpeg -f image2 -i a%d.jpg b.mp4

# alternative: maintain the exact quality of the input images
ffmpeg -sameq -i %03d.jpg output.mp4

# ... and then convert the movie to a GIF animation
ffmpeg -i file.mov -pix_fmt rgb24 -s qcif -loop_output 0 output.gif

this works:

 ffmpeg -i 2.avi -pix_fmt rgb24 -s 1000x800 -s qcif -loop_output 0 -r 1 2_.gif

	 */
	private void createGif(String absolutePath, String defaultImageFormat,
			int width, int height, int fps, String outputGif) {
		// TODO Auto-generated method stub
		
	}


	private void callMencoder(String cmd, ExecutorListener executorListener) {
		try {	
			System.out.println("cmd : " + cmd);
			executor=MencoderExecutorImpl.execute(cmd, executorListener);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * //TODO:
	 * lo de averiguar el fps, se podría hacer de este modo: 
	 * que la operación de grabación realice un promedio del valor real de duración entre screenshot y escreenshot
	 * cutilizar este promedio para el calculo de fps  Ahora utilizamos una constante K
	 * que representa la demora de la operación de tomar el srceenshot
	 *
	 */
	int K = 50;
	int _getFPS() {
		return 1000/(recordTimeStep+K);
	}
	public void createAvi(int width, int height, File videoOutput) throws IOException {
		int fps = _getFPS();
//		System.out.println("fps : "+fps);
		createAvi(getScreenshoterWorkingDir().getAbsolutePath(), DEFAULT_IMAGE_FORMAT, 
				width, height, fps, videoOutput.getAbsolutePath());
	}
	public void createGif(int width, int height, File videoOutput) throws IOException {
		int fps = _getFPS();
		createGif(getScreenshoterWorkingDir().getAbsolutePath(), DEFAULT_IMAGE_FORMAT, 
				width, height, fps, videoOutput.getAbsolutePath());
	}	
	public void createFlv(int width, int height, File videoOutput) throws IOException {
		int fps = _getFPS();
		createFlv(getScreenshoterWorkingDir().getAbsolutePath(), DEFAULT_IMAGE_FORMAT, 
				width, height, fps, videoOutput.getAbsolutePath());
	}
	
	
	//gui things
	


	private DesktopAreaChooser _currentAreaChooser;
	private JFrame _currentAreaChooserFrame;
	private SelectionListener<Rectangle> _currentListener;
	
	public void openDesktopAreaChooser(SelectionListener<Rectangle> listener) {	
		try {
			if(_currentAreaChooserFrame!=null)
				_currentAreaChooserFrame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		_currentAreaChooserFrame = new JFrame("...draw a rectangle to define the capture area...");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle fbounds = new Rectangle(0,0,screenSize.width, screenSize.height);	
		DesktopAreaChooser chooser = new DesktopAreaChooser(_currentAreaChooserFrame);
		chooser.addSelectionListener(new SelectionListener<Rectangle>() {
			@Override
			public void notify(SelectionEvent<Rectangle> e) {
//				System.out.println("rectangle selected : "+e.getData());
				_currentAreaChooserFrame.dispose();
			}
		});
		chooser.addSelectionListener(listener);
		_currentAreaChooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_currentAreaChooserFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {		
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)  {
					_currentAreaChooserFrame.dispose();
				}
			}	
			@Override
			public void keyReleased(KeyEvent e) {}			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		_currentAreaChooserFrame.setUndecorated(true);
		_currentAreaChooserFrame.getContentPane().add(chooser);
		_currentAreaChooserFrame.pack();
		_currentAreaChooserFrame.setSize(fbounds.getSize());
		_currentAreaChooserFrame.setLocation(fbounds.getLocation());
		_currentAreaChooserFrame.setVisible(true);
	}
	
	
//	public int getRecommendedTimeStepFor(Rectangle area, String format) {
//		int testCount=5;
//		int [] tests=new int[testCount];		
//		long t1 = System.currentTimeMillis(), t2;
//		for (int i = 0; i <testCount; i++) {
//			
//		}
//		ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(area, format);
//			t2 = System.currentTimeMillis();					
//			System.out.print(" buffy created in "+(t2-t1)+"ms");
//			
//		
//	}
	
	
	//getters y setters

	public String getScreenShoterWorkingDirPath() {
		return screenShoterWorkingDirPath;
	}

	public void setScreenShoterWorkingDirPath(String screenShoterWorkingDirPath) {
		this.screenShoterWorkingDirPath = screenShoterWorkingDirPath;
	}

	public boolean isMinimizeWindowOnRecording() {
		return minimizeWindowOnRecording;
	}

	public void setMinimizeWindowOnRecording(boolean minimizeWindowOnRecording) {
		this.minimizeWindowOnRecording = minimizeWindowOnRecording;
	}

	public int getRecordTimeStep() {
		return recordTimeStep;
	}

	public void setRecordTimeStep(int recordTimeStep) {
		this.recordTimeStep = recordTimeStep;
	}

	public Recorder getRecorder() {
		return recorder;
	}

	Rectangle _WholeScreenArea=null;
	public Rectangle getWholeScreenArea() {
		if(_WholeScreenArea==null) {
			Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
			_WholeScreenArea = new Rectangle(0,0,ss.width, ss.height);
		}
		return _WholeScreenArea;		
	}

	public String getDefaultScreenshotFileName() {
		return System.getProperty("user.home")+File.separator+"screenshot_"+new Date().getTime()+"."+DEFAULT_IMAGE_FORMAT;
	}

}
