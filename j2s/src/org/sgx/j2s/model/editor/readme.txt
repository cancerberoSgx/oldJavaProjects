editores
concepto utilizado en el �mbito de programaci�n de interfaces gr�ficas.

*) objetivo 1) separaci�n de roles

lograr que el programador se centre en el dato (contenido) que en la representaci�n 
(IO). (ej: edici�n de txt en docbook vs wysiwyg). Queremos que la responsabilidad sobre el dato 
y el computo sea del programador y que la responsabilidad de los dise�adores caiga en la IO visual.
Por lo tanto la resp del prog es instanciar editores mientras que la del dise�ador es 
imlementarlos/dise�arlos. 


*) responsabilidades (sem�nticas):
saber como presentar cierto clase de objeto (en modo edici�n y no edici�n)

*) editorfactory y datatype sets
recae mas responsabilidad en tener un conjunto de "datatypes soportados". este conjunto debe definirse formalmente y 
cada tecnolog�a debe proveer un editor. y un mecanismo de registro
y obtenci�n de editores para objeto de cierto tipo. 

*) cada editor define su controller. esto no es mvc!
se define un sistema simple de query/update del dato y un sistema simple de "eventos de 
edici�n" para llos editores "realtime", pero nada m�s. lo que suceda entre el usuario y el 
editor es responsabilidad de cada editor

*) productividad. editores auto
2 cosas. los dise�adores podr�n "escalar un nivel mas" y preguntarse c�mo presentar los (meta)conceptos del paradigma 
como container, collecci�n, objeto, clase, etc para as� luego poder realizar editores autom�ticos. Con lo anterior resuelto
y teniendo una buena base de editores "at�micos" ( 

*) objetivo 2) abaratar:

Abstraen conceptos diarios (color, tama�o, fuente, numero, form-objeto, etc) visuales. 
Si bien la idea es proveer con una infraestructura que aliviane la labor de crear 
editores personalizados hay que considerar la cantidad de editores a los que estamos acostumbrados 
(file/color/font/form/selector/chooser).

reusabilidad. separando al dato de la representaci�n se pueden realizar editores nativos sin tener que 
utilizar una infrastructura com�n. Hay varias implementaciones bastante refinadas de estos editores 
en tecnolog�as claves muy costosas de implementar. Con el concepto de Editor (teniendo en cuenta j2s/gwt)
las apps traducidas a dif tech podr�an hacer uso de la infraestruct. nativa presente para dichos commos - ed




*) objetivo main: editor de editores. imagine a world of people editing its own editors!