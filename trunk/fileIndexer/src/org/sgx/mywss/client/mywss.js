/* mywss native javascript client api
 * @author: sgurin
 * with this little, independent script included one can invoke WS published by org.sgx.mywss servlet like:
 
var mywss = new MyWssClient("http://localhost/myapp/MYWS");//servlet url

mywss.sendMessage("org.sgx.mathObject",               //endpoint
	{name:"exp", params:{operand1:12, operand2:0.3}}, //mensaje
	function(response) {                              //response handler (the message will be dispatched asynchronously)
		if(response.error!=false) { 
			alert(response.error)
			return;
		}
		resultInput.value=response.response            //show json result
		//if you need, you can obtain a javascript object from the response like:
		var obj=null;
		try{
			obj = eval("("+this.xmlHttp.responseText+")");
		}catch(e){
			alert("error evaluating json response: "+this.xmlHttp.responseText);
			return;
		} 
	}	
)
*/
MyWssClient = function(servletUrl){
	this.servletUrl=servletUrl;
}
MyWssClient.prototype.sendMessage=function(endpoint, msg, handler) {	
	var url = this.servletUrl+"?endpoint="+endpoint+"&msg="+JSON.stringify(msg);
	var ajaxm = new MyWssClient.AJAXManager(url, handler);
	document.write(url);
	//ajaxm.send();
}

MyWssClient.AJAXManager=function(url,handler){
	this.url=url;
	this.userHandler=handler
	this.xmlHttp=null;
	try  {	  //Firefox, Opera 8.0+, Safari
	  this.xmlHttp=new XMLHttpRequest();
	}catch (e)  {	  //Internet Explorer
		try {
			this.xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				this.xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				throw "this browser doesn't support ajax";	    	
			}
		}
	}
	this.realHandler.xmlHttp=this.xmlHttp;
	this.realHandler.userHandler=this.userHandler;
	this.xmlHttp.onreadystatechange=this.realHandler;
}
MyWssClient.AJAXManager.prototype.realHandler=function(){
	if(this.xmlHttp.readyState==4){
		var obj = null;
		try{
			obj = eval("("+this.xmlHttp.responseText+")");
		}catch(e){
			alert("error evaluating json response: "+this.xmlHttp.responseText);
			return;
		}
		this.userHandler(obj);
	}
}
MyWssClient.AJAXManager.prototype.send=function(method,data){
	if(!data)
		data=null;
	if(!method)
		method="GET";
	this.xmlHttp.open(method,this.url,true);
	this.xmlHttp.send(data);
}
