// Definimos la clase hospital

function hospital(nombre, ciudad, nPacientes){
	// Atributos clase hospital
	this.nombre=nombre;
	this.ciudad=ciudad;
	this.pacientes=new Array();


    // Eliminamos paciente
	function eliminarPaciente(n){
        // Eliminamos el paciente de la posicion n
        this.pacientes.splice(n,1);
	}
	this.eliminarPaciente=eliminarPaciente;

    
    

    // Imprime todos los pacientes
    function imprimirPacientes(){
        for(i=0;i<this.pacientes.length;i++){
            alert(this.pacientes[i].nombre);
        
        }
    }
	this.imprimirPacientes=imprimirPacientes;
    
    // Inicializamos vuelos
    for(i=0;i<nPacientes;i++){
        this.pacientes[i]=new paciente(i,"Paciente"+i,"Constipado");
    }
    
    
}

// Definimos la paciente

function paciente(codigo,nombre, enfermedad) {
	// Atributos del vuelo
	this.codigo=codigo;
    this.nombre=nombre;
	this.enfermedad=enfermedad;
}

// Creo un cole

var miHospi=new hospital("Ceed","Valencia",10);
miHospi.imprimirPacientes();
miHospi.eliminarPaciente(0);
miHospi.imprimirPacientes();
miHospi.eliminarPaciente(0);
miHospi.imprimirPacientes();
miHospi.eliminarPaciente(0);


