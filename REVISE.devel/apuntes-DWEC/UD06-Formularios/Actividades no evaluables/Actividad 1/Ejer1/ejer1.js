// Calcular letraDNI
function calcularLetra(numeroDni){
    var letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
    return letras[numeroDni%23];
}




function comprobar(){
    var t=document.getElementById("miTexto");
    var texto=t.value.toUpperCase();
    t.value=texto;
    var dni=texto.substring(0,8);
    var letra=texto[8];
    
    //alert(letra);
    //alert(dni);
    
    if(letra!=calcularLetra(dni)){
        alert("Letra no coincide con DNI");
        return false;
    }
    // Se hace el evento
    return true;
}


// Cuando se haya cargado la pagina
window.onload=function (){
    
    var f=document.getElementById("miForm");
    var t=document.getElementById("miTexto");
    f.onsubmit=comprobar;
    t.onblur=comprobar;
}