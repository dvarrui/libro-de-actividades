



function comprobar(){
    var t1=document.getElementById("miTexto1");
    var t2=document.getElementById("miTexto2");
    
    var texto1=t1.value.split('');
    var texto2=t2.value.split('');
    
    // first call
    var result1 = texto1.sort();
    var result2 = texto2.sort();    
    
    if(result1.toString()==result2.toString()){
        alert("Son anagramas");
        return true;
    }
    else{
        alert("No son anagramas");
        return false;
    }
    
}


// Cuando se haya cargado la pagina
window.onload=function (){
    
    var f=document.getElementById("miForm");
    f.onsubmit=comprobar;
}