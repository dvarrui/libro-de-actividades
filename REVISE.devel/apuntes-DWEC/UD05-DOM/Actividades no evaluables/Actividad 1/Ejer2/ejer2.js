// Funcion que devuelve true si es casi primo (segun la definicion del ejercicio)
function esCasiPrimo(n){
	
	var oportunidad=0;
	for(i=2;i<n;i++){
		if(n%i==0){
			oportunidad++;
			if(oportunidad>1){
				return false;
			}
		}
	}
	
	if(oportunidad==1)
		return true;
	else
		return false;
}

// Creamos la tabla y sus elementos
function crearTabla(){
	
	// Creamos la tabla
	var tabla = document.createElement("TABLE");

	var numeroUnico=1;
	
	for(var i=0;i<100;i++){
		// Creamos un elemento TR		
		var fila=document.createElement("TR");
		for(var j=0;j<100;j++){
			// Creamos TD y texto
			var celda=document.createElement("td");
			var texto=document.createTextNode(numeroUnico);
			// Anexamos texto al TD
			celda.appendChild(texto);
			// anexamos el TD al TR
			fila.appendChild(celda);
			
			// Si es casi primo, cambiamos el fondo
			if(esCasiPrimo(numeroUnico)){
				celda.style.backgroundColor="Yellow";
			}
			
			// Incrementamos el numero unico
			numeroUnico++;
			
		}
		// Anexamos el TR a la tabla
		tabla.appendChild(fila);
	}
	// Anexamos la tabla al body
	document.body.appendChild(tabla);
	
}
