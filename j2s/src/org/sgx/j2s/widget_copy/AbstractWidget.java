package org.sgx.j2s.widget_copy;

import java.util.*;

import org.sgx.j2s.model.base.*;
import org.sgx.j2s.widget_copy.events.Event;
import org.sgx.j2s.widget_copy.layout.Layout;


/**
 * native implementators can implement only setters for actually make the native 
 * visual transformation  and call super.setX() for the model stuff. see htmlyuiimpl
 * @author sgurin
 *
 */
public abstract class AbstractWidget implements Widget{

	protected Layout layout = null;
	Color background = Color.BLACK;
	List<Widget> children = new LinkedList<Widget>();
	Rectangle bounds= new Rectangle(2,2,50,50);
	Widget parent=null;
	String id;
	private String text;
	private String htmlContent;
	private Font font;
	private String imageSource;
	private String htmlSource;
	int type;
	boolean visible;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean b){
		this.visible=b;
	}
	public int getType() {
		return type;
	}
	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
		layout.setHost(this);
		layout.layout();
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public List<Widget> getChildren() {
		return children;
	}

	public void setChildren(List<Widget> children) {
		this.children = children;
	}
	
	public void appendChild(Widget c) {
		this.children.add(c);
	}
	public void addChildren(int index, Widget c) {
		this.children.add(index, c);
	}
	public void removeChildren(Widget w) {
		this.children.remove(w);
	}
	public void removeChildren(int ind) {
		this.children.remove(ind);
	}
	public void removeAllChildren() {
		this.children.clear();
	}
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	
	public Widget getParent() {
		return parent;
	}

	public void setParent(Widget parent) {
		this.parent = parent;
	}
	

	
	//text type
	public String getText() {
		return text;
	}
	public void setText(String s) {
		this.text=s;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font){
		this.font=font;
	}
	
	//html type
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String html){
		this.htmlContent = html; 
	}
	public String getHtmlSource() {
		return htmlSource;
	}
	public void setHtmlSource(String url) {
		this.htmlSource=url;
	}
	
	//image type
	public String getImageSource(){
		return imageSource;
	}
	public void setImageSource(String url){
		this.imageSource=url;
	}
	
	
	
	//events
	List<EventListener> mouseListeners = new LinkedList<EventListener>();
	public void addMouseListener(EventListener l){
		mouseListeners.add(l);
	}
	public void removeMouseListener(EventListener l){
		mouseListeners.remove(l);
	}
	public void notifyAllMouseListeners(Event e) {
		Iterator<EventListener> i = mouseListeners.iterator();
		while(i.hasNext()) {
//			i.next().handleEvent(e);
		}
	}
}
