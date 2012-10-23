package org.sgx.j2s.widget.swt.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.j2s.widget.events.UIEvent;
import org.sgx.j2s.widget.events.EventListener;
import org.sgx.j2s.widget.events.KeyListener;
import org.sgx.j2s.widget.events.MouseListener;
import org.sgx.j2s.widget.swt.SWTWidget;
import org.sgx.swt.SWTUtils;
/**
 * 
 * @author SGURIN
 *
 */
public class SWTWidgetTest {

	
	//testing debug
	
	public static void	showWidgetTest(){
		Shell shell = SWTUtils.createShell("a red widget ...");
		shell.setLayout(null);
		SWTWidget w = new SWTWidget(shell);
		w.setBackground(Color.RED);
		w.setBounds(new Rectangle(0,0,150,160));
//		w.setContentArea(new Rectangle(0,0,100,100));
		
		SWTWidget c = new SWTWidget(w);
		c.setBackground(Color.GREEN);
		c.setBounds(new Rectangle(3,2,20,20));
		c.addEventListener(UIEvent.ONMOUSEUP_TYPE, new MouseListener(){
			@Override
			public void click(UIEvent e) {
				System.out.println("click");				
			}			
		});
		c.addEventListener(UIEvent.ONKEYPRESSED_TYPE, new KeyListener(){

			@Override
			public void keyPressed(UIEvent e) {
				System.out.println("ONKEYPRESSED_TYPE");	
			}
				
		});
		
		w.setData("img32.png");
		w.setType(org.sgx.j2s.widget.IWidget.TYPE_IMAGE);
		
		
		
		SWTWidget w2 = new SWTWidget(shell);
		w2.setBackground(Color.BLUE);
		w2.setBounds(new Rectangle(200,2,50,60));
		
		SWTUtils.showShell(shell);
		
	}
	public static void	showWidgetTest2(){
		Shell shell = SWTUtils.createShell("a red widget ...");
		shell.setLayout(null);
		final SWTWidget w = new SWTWidget(shell);
		w.setBackground(Color.RED);
		w.setBounds(new Rectangle(0,0,250,260));
//		w.setContentArea(new Rectangle(10,20,180,189));
		
		w.setData("img32.png");
		w.setType(org.sgx.j2s.widget.IWidget.TYPE_IMAGE);
		
		

		final SWTWidget c = new SWTWidget(w);
		c.setBackground(Color.GREEN);
		c.setBounds(new Rectangle(50,2,127,127));
//		c.setContentArea(new Rectangle(30,20,90,90));
		
		w.addEventListener(UIEvent.ONMOUSEUP_TYPE, new MouseListener(){
			@Override
			public void click(UIEvent e) {
				System.out.println("click");
				w.setBounds(new Rectangle(Utils.randomNumberBetween(0, 80), 
					Utils.randomNumberBetween(0, 80), 
					Utils.randomNumberBetween(80,180), 
					Utils.randomNumberBetween(90,180)));
			}			
		});
//		c.setData("hello world");
//		c.setType(org.sgx.j2s.widget.IWidget.TYPE_TEXT);
		
		c.setData("img32.png");
		c.setType(org.sgx.j2s.widget.IWidget.TYPE_IMAGE);
		
		SWTUtils.showShell(shell);
		
	}
	public static void main(String[] args) {
		showWidgetTest2();
//		test1();
	}

	private static void test1() {
		Shell shell = SWTUtils.createShell("a red widget ...");
		shell.setLayout(null);
		Composite root = new Composite(shell, SWT.NONE);
		root.setBounds(new org.eclipse.swt.graphics.Rectangle(1,1,40,40));
		root.setBackground(SWTUtils.colorToSWT(new Color(233,3,3)));
		root.addKeyListener(new org.eclipse.swt.events.KeyListener(){

			public void keyPressed(KeyEvent e) {
				System.out.println("ONKEYPRESSED_TYPE");
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		SWTUtils.showShell(shell);
	}

}
