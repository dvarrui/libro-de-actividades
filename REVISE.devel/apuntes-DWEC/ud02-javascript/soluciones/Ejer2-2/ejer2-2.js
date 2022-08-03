// Funcion que recibe un texto y un patron y dice cuantas veces esta el patron
// No distingue mayusculas y minusculas y una letra puede formar parte de mas de un patron encontrado

function buscarPatron(texto,patron)
{
    // Importante declarar la i por el ambito de variables (que no la coja del general)
    var i;
    // Contador de cuantos hemos encontrados
    encontrados=0;
    
    // PAsamos todo a mayusculas para no distinguir mayusculas de minusculas
    textoMAY=texto.toUpperCase();
    patronMAY=patron.toUpperCase();
    
    
    for(i=0;i<textoMAY.length;i++){
        // Sacamos la cadena desde i a i + el tamanyo del patron
        cad=textoMAY.substring(i,i+patronMAY.length);
        
        if(cad==patronMAY){
            // Encontrada coincidencia
            encontrados++;
        }
        
    }
    
    // Devolvemos la solucion
    return encontrados;
}

var texto="000111101000ABCHO";
var patronesABuscar=["00","101","ABC","HO"];
var i;

// Total de coincidencias de todos los patrones

total=0;
// Recorremos el vector patronesABuscar

for(i=0;i<patronesABuscar.length;i++){
    
    
    // Buscamos el patron
    res=buscarPatron(texto,patronesABuscar[i]);
    alert("El partron "+patronesABuscar[i]+" esta "+res+" veces");
    // Acumulamos el total
    total+=res;
}

alert("El total de patrones encontrados es "+total);


