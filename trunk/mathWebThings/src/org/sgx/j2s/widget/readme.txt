






Entre otras cosas, es importante tener presente que esto ser� traducido 
a javascript y correr� en el browser lo q implica:
1) no salirnos de clases no soportadas por j2s 
2) ser efecientes y considerar como cuello de botella a javascript
3) considerar el peso del codigo como indicador de eficiencia (pensar en el browser y la carga js)

�por qu� no SWT ? SWT es multiplataforma y est� soportado por j2s. 
En mi opini�n la ventaja de widget ser�a:
1) widget es autocontenido mientras que swt supone varios widgets nativos
2) swt est� orientado al desktop. su arquitectura se basa en que los Widgets swt son en realidad widgets nativos .
3) en mi opini�n tiene muchas cosas que no se usan en la web y no est� dise�ado para el dise�o web.

en teor�a widgets ser�a mucho m�s liviano que swt es much�simo m�s liviano
puede independizarse de java.util f�cilmente utilizando utils que prioricen 
a javascript y que no sean tan pesadas. en teor�a se podr�a lograr

j2slib 70k
widgets y sus dependencias en menos de 50k

y ah� si podr�amos considerar reunir todo en un solo .js y no como en j2s + swt+java.util que se nos van mas de 300kb


Como funcionar�a ?

tenemos una gran interface que implementar Widgets y que escala todo lo dem�s.

Hay una implementaci�n en cada tecnolog�a, cada instancia de que implemente widgets se 
comunicar� directamente con la representaci�n nativa. por ejemplo, SWTWidget est� 
asociada a un Composite sobre el cu�l realiza los cambios. Hay una widget factory que 
tambi�n tendr� que implementarse en cada tech encargada de fabricar los widgets. 
El estilo se aplica a un widget (un widget puede considerarse como un documento) 

tambi�n podr�a haber alguna clase de utilidades (ver WidgetUtils)