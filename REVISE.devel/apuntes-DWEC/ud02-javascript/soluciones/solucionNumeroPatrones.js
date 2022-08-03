// Funcion que recibe un texto y un patron y dice cuantas veces esta el patron
// No distingue mayusculas y minusculas y una letra puede formar parte de mas de un patron encontrado

function buscarPatron(texto,patron)
{
    // Importante declarar la i por el ambito de variables (que no la coja del general)
    var i;
    // Contador de cuantos hemos encontrados
    var encontrados=0;
    
    // PAsamos todo a mayusculas para no distinguir mayusculas de minusculas
    var textoMAY=texto.toUpperCase();
    var patronMAY=patron.toUpperCase();
    
    
    for(i=0;i<textoMAY.length;i++){
        // Sacamos la cadena desde i a i + el tamanyo del patron
        var cad=textoMAY.substring(i,i+patronMAY.length);
        
        if(cad==patronMAY){
            // Encontrada coincidencia
            encontrados++;
        }
        
    }
    
    // Devolvemos la solucion
    return encontrados;
}


// Funcion que recibe un texto y un patron y dice cuantas veces esta el patron
// No distingue mayusculas y minusculas y una letra puede formar parte de mas de un patron encontrado

function numeroPatrones(texto)
{
    
    var patronesABuscar=["00","101","ABC","HO"];
    
    var total=0;
    
    var i=0;
    
    for(i=0;i<patronesABuscar.length;i++){
    
    
       // Buscamos el patron
        res=buscarPatron(texto,patronesABuscar[i]);
        
        
        // Acumulamos el total
        total+=res;
    
    
    }
    
    
    
    
    
    
    // Devolvemos la solucion
    return total;
}


