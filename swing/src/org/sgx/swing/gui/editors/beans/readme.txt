BeanForm es un widget sucio que muestra las propiedades de un javabean en forma tabular 
y las deja editar. soporta tanto formato vertical como horizontal. 

Soporta los siguientes tipos de dato los cuales mapea a los siguientes widgets de edici�n swing:

int, double, float, string -> JTextEntry
List -> JComboBox
boolean -> jcheckbox

el usuario puede customizar esta asignaci�n pasando un mapa de customizaci�n
donde las claves sean los nombres de las propiedades del javabean y los cvalores 
las constantes que representan componenetes swing en BeanForm.java (ver CustomTest.java)