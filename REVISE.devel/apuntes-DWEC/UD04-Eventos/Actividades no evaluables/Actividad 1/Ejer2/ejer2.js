var estado=-1;

function comenzar(){
	if(estado!=-1){
		console.log("Ya estamos saludando, se cancela");
	}
	else{
		estado=setInterval("alert('hola');",5000);
	}
}

function parar(){
	
	if(estado!=-1){
		clearInterval(estado);
		estado=-1;
	}
	else{
	console.log("No hay saludo a parar")
	}
	
	
}