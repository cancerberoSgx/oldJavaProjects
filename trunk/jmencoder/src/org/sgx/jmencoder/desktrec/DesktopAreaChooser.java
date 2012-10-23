package org.sgx.jmencoder.desktrec;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.sgx.j2s.model.editor.selection.SelectionEvent;
import org.sgx.j2s.model.editor.selection.SelectionListener;
import org.sgx.j2s.model.editor.selection.SelectionTarget;
import org.sgx.jmencoder.desktrec.test.DrawRectangle;

/**
 * this is a transparent component that let the user draw a rectangle
 * (observer)
 * 
 * @author sgurin
 * 
 */
public class DesktopAreaChooser extends JComponent implements
		ComponentListener, WindowFocusListener, Runnable,
		SelectionTarget<Rectangle> {

	private static final long serialVersionUID = 1L;
	
	// rectangle drawing part
	private static final Color RECT_FILL_COLOR = Color.blue.brighter()
			.brighter();
	private static final Color RECT_DRAW_COLOR = Color.blue.darker();
	private static final Color DRAGGING_RECT_COLOR = Color.pink;
	private static final Dimension MAIN_SIZE = new Dimension(800, 600);

	// transparent background part
	private JFrame _frame;
	private BufferedImage _background;
	private long _lastUpdate = 0;
	private boolean _refreshRequested = true;
	private Robot _robot;
	private Rectangle _screenRect;
//	private ConvolveOp _blurOp;

	// rectangle drawing and notification
	private List<Rectangle2D> rectList = new ArrayList<Rectangle2D>();
	private Rectangle2D draggingRect = null;
	List<SelectionListener<Rectangle>> selListeners;

	public DesktopAreaChooser(JFrame frame) {

		// transparent background part
		_frame = frame;
		try {
			_robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			return;
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		_screenRect = new Rectangle(dim.width, dim.height);
//		float[] my_kernel = { 0.10f, 0.10f, 0.10f, 0.10f, 0.20f, 0.10f, 0.10f,
//				0.10f, 0.10f };
		
//		float[] my_kernel = {1,0,0,0,1,0,0,0,1};
//		_blurOp = new ConvolveOp(new Kernel(3, 3, my_kernel));
		updateBackground();
		_frame.addComponentListener(this);
		_frame.addWindowFocusListener(this);
		new Thread(this).start();

		// rectangle drawing part:
		setPreferredSize(MAIN_SIZE);
		MouseAdapter mouseAdapter = new MyMouseAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		selListeners = new java.util.LinkedList<SelectionListener<Rectangle>>();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// transparent background part
		Point pos = this.getLocationOnScreen();
		BufferedImage buf = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		buf.getGraphics().drawImage(_background, -pos.x, -pos.y, null);
		Image img = buf;//_blurOp.filter(buf, null);
		g2.drawImage(img, 0, 0, null);
		//g2.setColor(new Color(255, 255, 255, 192));
		//g2.fillRect(0, 0, getWidth(), getHeight());

		
		 g2.setStroke(new BasicStroke(3.0f));
		// drawing rectangle part
		for (Rectangle2D rect : rectList) {
			g2.setColor(RECT_FILL_COLOR);
			g2.fill(rect);			
			g2.setColor(RECT_DRAW_COLOR);
			g2.draw(rect);
		}
		if (draggingRect != null) {
			g2.setColor(DRAGGING_RECT_COLOR);
			g2.draw(draggingRect);
		}
	};

	// transparent background part
	protected void updateBackground() {
		_background = _robot.createScreenCapture(_screenRect);
	}

	protected void refresh() {
		if (_frame.isVisible() && this.isVisible()) {
			repaint();
			_refreshRequested = true;
			_lastUpdate = System.currentTimeMillis();
		}
	}

	public void componentHidden(ComponentEvent e) {
		refresh();
	}

	public void componentMoved(ComponentEvent e) {
		repaint();
	}

	public void componentResized(ComponentEvent e) {
		repaint();
	}

	public void componentShown(ComponentEvent e) {
		repaint();
	}

	public void windowGainedFocus(WindowEvent e) {
		refresh();
	}

	public void windowLostFocus(WindowEvent e) {
		refresh();
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(100);
				long now = System.currentTimeMillis();
				if (_refreshRequested && ((now - _lastUpdate) > 1000)) {
					if (_frame.isVisible()) {
						Point location = _frame.getLocation();
						_frame.setLocation(-_frame.getWidth(), -_frame
								.getHeight());
						updateBackground();
						_frame.setLocation(location);
						refresh();
					}
					_lastUpdate = now;
					_refreshRequested = false;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// drawing rectangle part
	public void addRectangle(Rectangle2D r) { // in case the MouseAdapter is not
												// an inner class
		rectList.add(r);
	}

	public void setDraggingRect(Rectangle2D r) {
		draggingRect = r;
	}

	private class MyMouseAdapter extends MouseAdapter {
		private Point start = null;

		@Override
		public void mousePressed(MouseEvent e) {
			start = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Rectangle2D r2d = createRect2D(e);

			addRectangle(r2d);
			setDraggingRect(null);
			DesktopAreaChooser.this.repaint();

			SelectionEvent<Rectangle> evt = new SelectionEvent<Rectangle>(r2d
					.getBounds(), DesktopAreaChooser.this);
			for (Iterator<SelectionListener<Rectangle>> iterator = selListeners
					.iterator(); iterator.hasNext();) {
				SelectionListener<Rectangle> l = iterator.next();
				l.notify(evt);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			setDraggingRect(createRect2D(e));
			if(e.getButton()!=MouseEvent.NOBUTTON)
				DesktopAreaChooser.this.repaint();

		      DesktopAreaChooser.this.repaint();      
		}

		private Rectangle2D createRect2D(MouseEvent e) {
			Point end = e.getPoint();
			int width = Math.abs(start.x - end.x);
			int height = Math.abs(start.y - end.y);
			int x = Math.min(start.x, end.x);
			int y = Math.min(start.y, end.y);
			Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
			return r;
		}
	}

	@Override
	public void addSelectionListener(SelectionListener<Rectangle> l) {
		selListeners.add(l);
	}

	@Override
	public void removeSelectionListener(SelectionListener<Rectangle> l) {
		selListeners.remove(l);
	}
	
	
	
	
	
	//test

	public static void openInNoDecoratedFrame(Rectangle fbounds) {
		final JFrame frame = new JFrame("...draw a rectangle to define the capture area...");
		frame.setUndecorated(true);
		DesktopAreaChooser chooser = new DesktopAreaChooser(frame);
		chooser.addSelectionListener(new SelectionListener<Rectangle>() {
			@Override
			public void notify(SelectionEvent<Rectangle> e) {
				System.out.println("rectangle selected : "+e.getData());
				frame.dispose();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {		
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)  {
					frame.dispose();
				}
			}			
			@Override
			public void keyReleased(KeyEvent e) {
			}		
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		frame.getContentPane().add(chooser);
		frame.pack();
		frame.setSize(fbounds.getSize());
		frame.setLocation(fbounds.getLocation());
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		openInNoDecoratedFrame(new Rectangle(0,0,screenSize.width, screenSize.height));
//		JFrame frame = new JFrame("Transparent Window");
//		DesktopAreaChooser bg = new DesktopAreaChooser(frame);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(bg);
//		frame.pack();
//		frame.setSize(200, 200);
//		frame.setLocation(500, 500);
//		frame.setVisible(true);
	}
}
