//Funciones COOKIES

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function deleteCookie(cname) {
	document.cookie = cname+'=; expires=Thu, 01 Jan 1970 00:00:01 GMT;path=/';
}

// FIN funciones COOKIES

// Funcion para eliminar nuestra Cookie
function eliminarCookie(){
	deleteCookie("visitas");
}


// Codigo al cargarse la pagina para obtener la cookie

window.onload=function (){
    
	var b=document.getElementById("botonEliminar");
	b.onclick=eliminarCookie;
	
	
    var miCookie=getCookie("visitas");
	// Si no esta establecida, la pongo a 0 y si lo esta sumo 1, para luego re-establecerla
	if(miCookie==""){
		miCookie="0";
	}else{
		miCookie=(parseInt(miCookie)+1).toString();	
	}
	setCookie("visitas",miCookie);
	
	
    
	document.getElementById("divVisitas").innerHTML=miCookie;
}

