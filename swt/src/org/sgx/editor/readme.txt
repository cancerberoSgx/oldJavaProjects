editores
concepto utilizado en el ámbito de programación de interfaces gráficas.

*) objetivo 1) separación de roles

lograr que el programador se centre en el dato (contenido) que en la representación 
(IO). (ej: edición de txt en docbook vs wysiwyg). Queremos que la responsabilidad sobre el dato 
y el computo sea del programador y que la responsabilidad de los diseñadores caiga en la IO visual.
Por lo tanto la resp del prog es instanciar editores mientras que la del diseñador es 
imlementarlos/diseñarlos. 


*) responsabilidades (semánticas):
saber como presentar cierto clase de objeto (en modo edición y no edición)

*) editorfactory y datatype sets
recae mas responsabilidad en tener un conjunto de "datatypes soportados". este conjunto debe definirse formalmente y 
cada tecnología debe proveer un editor. y un mecanismo de registro
y obtención de editores para objeto de cierto tipo. 

*) cada editor define su controller. esto no es mvc!
se define un sistema simple de query/update del dato y un sistema simple de "eventos de 
edición" para llos editores "realtime", pero nada más. lo que suceda entre el usuario y el 
editor es responsabilidad de cada editor

*) productividad. editores auto
2 cosas. los diseñadores podrán "escalar un nivel mas" y preguntarse cómo presentar los (meta)conceptos del paradigma 
como container, collección, objeto, clase, etc para así luego poder realizar editores automáticos. Con lo anterior resuelto
y teniendo una buena base de editores "atómicos" ( 

*) objetivo 2) abaratar:

Abstraen conceptos diarios (color, tamaño, fuente, numero, form-objeto, etc) visuales. 
Si bien la idea es proveer con una infraestructura que aliviane la labor de crear 
editores personalizados hay que considerar la cantidad de editores a los que estamos acostumbrados 
(file/color/font/form/selector/chooser).

reusabilidad. separando al dato de la representación se pueden realizar editores nativos sin tener que 
utilizar una infrastructura común. Hay varias implementaciones bastante refinadas de estos editores 
en tecnologías claves muy costosas de implementar. Con el concepto de Editor (teniendo en cuenta j2s/gwt)
las apps traducidas a dif tech podrían hacer uso de la infraestruct. nativa presente para dichos commos - ed




*) objetivo main: editor de editores. imagine a world of people editing its own editors!