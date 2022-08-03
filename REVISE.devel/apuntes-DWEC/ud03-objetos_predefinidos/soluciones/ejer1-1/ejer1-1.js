
// Calcular letraDNI
function letraDNI(numeroDni){
    var letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
    return letras[numeroDni%23];
}


var intervalo=setInterval("hacer();",20000);
// [] Equivalente a new Array();
var miArray=[];

function hacer(){
    var cad=prompt("Dime el DNI o -1 para parar","12345678");
    if(cad=="-1"){
        // Usando la variable global intervalo, paramos el setInterval
        clearInterval(intervalo);
        // Mostramos el array
        alert(miArray.join(" ; "))
    }
    else{
        let=letraDNI(cad);
        miArray.push(let);
    }
}