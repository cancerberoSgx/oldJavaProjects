package org.sgx.jmencoder.desktrec.test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import org.sgx.j2s.model.editor.selection.SelectionEvent;
import org.sgx.j2s.model.editor.selection.SelectionListener;
import org.sgx.j2s.model.editor.selection.SelectionTarget;
public class DrawRectangle extends JPanel implements SelectionTarget<Rectangle>{
  private static final Color RECT_FILL_COLOR = Color.blue.brighter().brighter();
  private static final Color RECT_DRAW_COLOR = Color.blue.darker();
  private static final Color DRAGGING_RECT_COLOR = Color.pink;
  private static final Dimension MAIN_SIZE = new Dimension(800, 600);
  private List<Rectangle2D> rectList = new ArrayList<Rectangle2D>();
  private Rectangle2D draggingRect = null;
  List<SelectionListener<Rectangle>> selListeners;
  public DrawRectangle() {
    setPreferredSize(MAIN_SIZE);
    MouseAdapter mouseAdapter = new MyMouseAdapter();
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);

	selListeners = new java.util.LinkedList<SelectionListener<Rectangle>>();
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
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
  
  // in case the MouseAdapter is not an inner class
  public void addRectangle(Rectangle2D r) {
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
		DrawRectangle.this.repaint();

		SelectionEvent<Rectangle> evt = new SelectionEvent<Rectangle>(r2d
				.getBounds(), DrawRectangle.this);
		for (Iterator<SelectionListener<Rectangle>> iterator = selListeners
				.iterator(); iterator.hasNext();) {
			SelectionListener<Rectangle> l = iterator.next();
			l.notify(evt);
		}
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
      setDraggingRect(createRect2D(e));
      DrawRectangle.this.repaint();      
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
	
	
	
//	 private static void createAndShowUI() {
//		    JFrame frame = new JFrame("JDrawRect2");
//		    frame.getContentPane().add(new DrawRectangle());
//		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		    frame.pack();
//		    frame.setLocationRelativeTo(null);
//		    frame.setVisible(true);
//		  }
  public static void openInNoDecoratedFrame(Rectangle fbounds) {
		final JFrame frame = new JFrame(".");
		DrawRectangle chooser = new DrawRectangle();
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
			public void keyReleased(KeyEvent e) {			}
			
			@Override
			public void keyPressed(KeyEvent e) {			}
		});
		frame.getContentPane().add(chooser);
		frame.pack();
		frame.setSize(fbounds.getSize());
		frame.setLocation(fbounds.getLocation());
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		openInNoDecoratedFrame(new Rectangle(55,5,666,666));
//		JFrame frame = new JFrame("Transparent Window");
//		DesktopAreaChooser bg = new DesktopAreaChooser(frame);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(bg);
//		frame.pack();
//		frame.setSize(200, 200);
//		frame.setLocation(500, 500);
//		frame.setVisible(true);
	}
	
	

//  public static void main(String[] args) {
//    java.awt.EventQueue.invokeLater(new Runnable() {
//      public void run() {
//	createAndShowUI();
//      }
//    });
//  }
}