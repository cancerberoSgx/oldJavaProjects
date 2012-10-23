






Entre otras cosas, es importante tener presente que esto será traducido 
a javascript y correrá en el browser lo q implica:
1) no salirnos de clases no soportadas por j2s 
2) ser efecientes y considerar como cuello de botella a javascript
3) considerar el peso del codigo como indicador de eficiencia (pensar en el browser y la carga js)

¿por qué no SWT ? SWT es multiplataforma y está soportado por j2s. 
En mi opinión la ventaja de widget sería:
1) widget es autocontenido mientras que swt supone varios widgets nativos
2) swt está orientado al desktop. su arquitectura se basa en que los Widgets swt son en realidad widgets nativos .
3) en mi opinión tiene muchas cosas que no se usan en la web y no está diseñado para el diseño web.

en teoría widgets sería mucho más liviano que swt es muchísimo más liviano
puede independizarse de java.util fácilmente utilizando utils que prioricen 
a javascript y que no sean tan pesadas. en teoría se podría lograr

j2slib 70k
widgets y sus dependencias en menos de 50k

y ahí si podríamos considerar reunir todo en un solo .js y no como en j2s + swt+java.util que se nos van mas de 300kb


Como funcionaría ?

tenemos una gran interface que implementar Widgets y que escala todo lo demás.

Hay una implementación en cada tecnología, cada instancia de que implemente widgets se 
comunicará directamente con la representación nativa. por ejemplo, SWTWidget está 
asociada a un Composite sobre el cuál realiza los cambios. Hay una widget factory que 
también tendrá que implementarse en cada tech encargada de fabricar los widgets. 
El estilo se aplica a un widget (un widget puede considerarse como un documento) 

también podría haber alguna clase de utilidades (ver WidgetUtils)