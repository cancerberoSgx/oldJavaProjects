PROTOCOLO RPC BASADO EN JSON	

problema de los web services: Quien define las operaciones est� limitado al 
uso de ciertos datatypes. en el caso de soap el usuairo define las clases en 
el wsdl. en el caso de json ya est�n definidos los unicos datatypes 
utilizados(boolean,string,null,number,array,map)

El programador java

el objectivo a futuro es q el usuario pueda especificar un bean con las operaciones de servicio y cuyos 
tipos utilizados sean otros beans o tipos simples (Number, String, Boolean, Array, Object). a partir de este bean
se genera un message listener que invocar�sa a ese bean para que atienda al servicio.

Esta ser� la traducci�n de data types

datatype java								datatype mywss
----------------------------------------------------------------
int, Integer, Float, float, etc             number
char, Character, String						string
boolean, Boolean							boolean
Collection, arrays (Object[])				array
Map, dem�s tipos (extends object)			objeto