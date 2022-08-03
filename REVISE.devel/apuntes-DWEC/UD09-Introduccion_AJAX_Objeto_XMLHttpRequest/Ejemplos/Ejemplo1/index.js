
<!-- Esta funcion devuelve el objeto ajax que permite usar el navegador que gastes segun su version ->
function obtainXMLHttpRequest()
{
	var httpRequest;
	if (window.XMLHttpRequest){
		//El explorador implementa la interfaz de forma nativa
		httpRequest = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject){
		//El explorador permite crear objetos ActiveX
		try {
			httpRequest = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e) {
			try {
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	// Si no se puede crear, devolvemos false. En caso contrario, devolvemos el  objeto
	if (!httpRequest){
		return false;
	}
	else{
		return httpRequest;
	}
}

// Funci�n asociada a los botones 
function fajax(parametro)    
{
    var accion,ajax;
    // Cogemos el valor
    accion = parametro;
	// Obtenemos el objeto
    ajax=obtainXMLHttpRequest();
	
	// Indicamos la conexion
    ajax.open("POST","http://hispabyte.net/DWEC/EjemploUD09-1AJAX.php",true);
	
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// Definimos el comportamiendo del evento onreadystatechange
    ajax.onreadystatechange=function() 
    {
		// Si el estado es uno, la capa cambia a CARGANDO
		if (ajax.readyState==1) {
		     	document.getElementById('capa').innerHTML="CARGANDO...";
		}
		// Si el estado es 4 y se ha hecho la conexion correcta, se muestra en la capa la respuesta recibida
		if (ajax.readyState==4) 
		{
		    if (ajax.status==200)
		     {
		     	document.getElementById('capa').innerHTML=ajax.responseText;
		     }
		}
    }
	// Aqui enviamos la peticion asincrona con accion como parametro
    ajax.send("accion="+accion);
} 
