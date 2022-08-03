// Definimos la clase aeropuerto

function aeropuerto(nombre, ciudad, nVuelos){
	// Atributos clase aerpuerto
	this.nombre=nombre;
	this.ciudad=ciudad;
	this.vuelos=new Array();


    // cambia hora llegada
	function cambiarHoraLLegada(n,nuevaHora){
		this.vuelos[n].cambiarHoraLLegada(nuevaHora);
	}
	this.cambiarHoraLLegada=cambiarHoraLLegada;

	// cambia hora salida
	function cambiarHoraSalida(n,nuevaHora){
		this.vuelos[n].cambiarHoraSalida(nuevaHora);
	}
	this.cambiarHoraSalida=cambiarHoraSalida;

    
    

    // Comprueba si llegada posterior a salida
    function compruebaLLegadaMayorSalida(n){
        alert(this.vuelos[n].compruebaLLegadaMayorSalida());
    }
	this.compruebaLLegadaMayorSalida=compruebaLLegadaMayorSalida;
    
    // Inicializamos vuelos
    for(i=0;i<nVuelos;i++){
        this.vuelos[i]=new vuelo(i,"11:00","12:00");
    }
    
    
}

// Definimos la clase vuelo


// Formato horas HH:MM

function vuelo(codigo,horaSalida,horaLLegada) {
	// Atributos del vuelo
	this.codigo=codigo;
    this.horaSalida=horaSalida;
	this.horaLLegada=horaLLegada;
    

	// cambia hora llegada
	function cambiarHoraLLegada(nuevaHora){
		this.horaLLegada=nuevaHora;
	}
	this.cambiarHoraLLegada=cambiarHoraLLegada;


	// cambia hora salida
	function cambiarHoraSalida(nuevaHora){
		this.horaSalida=nuevaHora;
	}
	this.cambiarHoraSalida=cambiarHoraSalida;

    // Comprueba si llegada posterior a salida
    function compruebaLLegadaMayorSalida(){
        lleg=this.horaLLegada.split(":");
        sal=this.horaSalida.split(":");
        
        hhlleg=parseInt(lleg[0]);
        mmlleg=parseInt(lleg[1]);
        
        hhsal=parseInt(sal[0]);
        mmsal=parseInt(sal[1]);
        //alert("hhsal "+hhsal+ " hhlleg "+hhlleg);
        
        if(hhlleg>hhsal){
            return true;
        }
        else if(hhlleg<hhsal){
            return false;
        }
        else{
            if(mmlleg>mmsal){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
	this.compruebaLLegadaMayorSalida=compruebaLLegadaMayorSalida;

}

// Creo un cole

var miAeropuerto=new aeropuerto("Ceed","Valencia",10);
miAeropuerto.compruebaLLegadaMayorSalida(1);
miAeropuerto.cambiarHoraSalida(1,"13:00");
miAeropuerto.compruebaLLegadaMayorSalida(1);
miAeropuerto.cambiarHoraLLegada(1,"14:00");
miAeropuerto.compruebaLLegadaMayorSalida(1);



