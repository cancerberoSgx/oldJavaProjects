this a very simple bitmap api for drwaing and the following implementations : raphaeljs, html, canvas. 

Supported basic drawing elements: points and lines with color strokes. its main 
objective is to be efficient (think in plotters, and fractal drawers). 
If you need a richer set of drawing functions please use a svg or similar.

We support efficient drawing in all major browsers using:

<canvas> elemen where if it is supported (FF and opera)
else if <canvas> is not supported (IE) efficient html element (html) 