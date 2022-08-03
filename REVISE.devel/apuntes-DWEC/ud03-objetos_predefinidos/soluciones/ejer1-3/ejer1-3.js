
// Calcular letraDNI
function letraDNI(numeroDni){
    var letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
    return letras[numeroDni%23];
}


var l=prompt("Dime la letra","A");
// Contaremos cuantos tienen  la letra buscada
var numero=0;

var miArray=[];

for(i=1;i<=999;i++){
    
    // Si coinciden las letras, ya tenemos el numero
    if(l==letraDNI(i)){
        numero++;
	miArray.push(i);
    }
}
alert("hay "+numero+" dnis hasta 999 con la letra "+l);
alert(miArray);
