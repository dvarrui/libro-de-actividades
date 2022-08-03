// Calcular letraDNI
function letraDNI(numeroDni){
    var letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
    return letras[numeroDni%23];
}



function calcularDNI(evento){
	
	var letra=String.fromCharCode(evento.keyCode);

			document.getElementById("resultado").innerHTML="DNIs con letra "+letra+ " ";
	for(i=0;i<=9999;i++){
		if(letraDNI(i)==letra){
			document.getElementById("resultado").innerHTML+=i+" ; ";
		}
	}
	
	
}


//asignamos a calcular dni a onkeyup
document.onkeyup=calcularDNI;