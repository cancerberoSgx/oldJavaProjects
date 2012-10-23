this is a java2script porting of stringtree json 
(http://stringtree.org/stringtree-json.html) JSON writer and jsonreader only

notas:

al deserializar (string->object) con jsonreader podemos obtener un Map. 

los tipos de esos campos parecen ser

arrayList para cualquier field de tipo Array
Long para cualquier entero (int, long, short)