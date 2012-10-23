package org.sgx.fplotter;

import org.sgx.j2s.widget.base.Color;

public class Config {
public Config(int canvasWidth, int canvasHeight, Color background) {
		super();
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.background = background;
	}

int canvasWidth, canvasHeight;
Color background;


public int getCanvasWidth() {
	return canvasWidth;
}

public void setCanvasWidth(int canvasWidth) {
	this.canvasWidth = canvasWidth;
}

public int getCanvasHeight() {
	return canvasHeight;
}

public void setCanvasHeight(int canvasHeight) {
	this.canvasHeight = canvasHeight;
}

public Color getBackground() {
	return background;
}

public void setBackground(Color background) {
	this.background = background;
}


public static Config getDefault(){
return new Config(600,600,Color.WHITE);	
}
}
