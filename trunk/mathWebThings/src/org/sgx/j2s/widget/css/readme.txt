style language for widgets. kind of css subset but not css (it is for widgets not for html elements). 

the rules are:

only .class selectors. 

solamente válidas expresiones del tipo :

.class1, .class2, .., {
	prop1:val1;
	prop2: val2;
	...
}

no soportados ningún otro selector (#id3, ".class1 .classChildren", etc)

se permite: 

formato rgb(2,2,2) para colores
se permite el tipo rect(x,y,w,h) con el mismo significado q widget setBounds();