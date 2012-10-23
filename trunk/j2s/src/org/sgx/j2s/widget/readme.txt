una implementación de ejemplo html está en 

dependencies: this package depends on org.sgx.j2s.model 

implementators: 

one testeable  approach is , for example for swing:


widgetImpl extends jpanel impl widget {
	//implement only widget stuff : parentship, bounds, etc
}

HTMLImpl extends jpanel impl htmlwidget {
	//implement only htmlwidget stuff : css style
}

digamos que widget, imagewidget imputwidget, htmlwidget son 
los únicos "widget nativos" y a partir de ellos se crean los 
widget de usuarios como selectlist, combobox, checkbox, button, etc.

solamente implementando inputWidget ya se pueden implementar varios editores...