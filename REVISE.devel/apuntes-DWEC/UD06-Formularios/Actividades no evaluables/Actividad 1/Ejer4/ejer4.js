
// ACtualizar a los cambios


function actualizar(){
    var s1=document.getElementById("s1");
    var s2=document.getElementById("s2");
    // Obtenemos el valor del seleccionado
    var ind=s1.selectedIndex;
    var val=s1.options[ind].value;

    if(val=="1"){
        // Cambiamos HTML de s2
        s2.innerHTML="<option>Ninguno seleccionado</option><option>Alicante capital</option><option>Elche</option><option>Orihuel</option>";
        
    }
    else if(val=="2"){
        // Cambiamos HTML de s2
        s2.innerHTML="<option>Ninguno seleccionado</option><option>Castellon capital</option><option>Oropesa</option><option>Vinaroz</option>";
        
    }
    else if(val=="3"){
        // Cambiamos HTML de s2
        s2.innerHTML="<option>Ninguno seleccionado</option><option>Valencia capital</option><option selected='selected'>Torrent</option><option>Mislata</option>";
        
    }
}


// Cuando se haya cargado la pagina
window.onload=function (){
    
    var s1=document.getElementById("s1");
    s1.onchange=actualizar;
}