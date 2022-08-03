/* NOTA IMPORTANTE
ESTA SOLUCION ES LA MAS ALGORITMICA Y MAS ENTENDIBLE POR UN HUMANO, PERO
NO LA MAS OPTIMA

AQUI COMO IMPLEMENTAR LA FORMULA MAS OPTIMA

https://codegolf.stackexchange.com/questions/22443/create-a-sudoku-solution-checker
http://elblogdehoracio.blogspot.com.es/2010/08/verificar-si-una-matriz-es-sudoku-con.html
http://www.maths.qmul.ac.uk/~pjc/preprints/sudoku.pdf


function esSudokuCorrecto(sudoku) {
    for (var i=0; i<9; i++) {
        var fila = 0;
        var columna = 0;

        for (var j=0; j<9; j++) {
            fila = fila + Math.pow(2, sudoku[i][j]);
            columna = columna + Math.pow(2, sudoku[j][i]);
        }

        if (columna !== 1022 || fila !== 1022) {
            return false;
        }
    }
    return true;
}


*/





// Funcion que devuelve true si el horizontal de un sudoku es correcto, falso en caso contrario

function esCorrectoSudokuHorizontal(s,x){
    var i;
    // Array para marcar si una posicion esta o no, inicializado a 0
    var posiciones=[0,0,0,0,0,0,0,0,0]
    for(i=0;i<9;i++){
        var pos=s[x][i]-1;
        posiciones[pos]=1;
    }
    
    // Si estan las 9 posiciones marcadas
    for(i=0;i<9;i++){
        // Si alguna no se ha encontrado, devuelve falso
        if(posiciones[i]==0){
            return false;
        }
    }
    // Si estan todas, devuelve verdadero
    return true;
}

// Funcion que devuelve true si el vertical de un sudoku es correcto, falso en caso contrario

function esCorrectoSudokuVertical(s,x){
    var i;
    // Array para marcar si una posicion esta o no, inicializado a 0
    var posiciones=[0,0,0,0,0,0,0,0,0]
    for(i=0;i<9;i++){
        var pos=s[i][x]-1;
        posiciones[pos]=1;
    }
    
    // Si estan las 9 posiciones marcadas
    for(i=0;i<9;i++){
        // Si alguna no se ha encontrado, devuelve falso
        if(posiciones[i]==0){
            return false;
        }
    }
    // Si estan todas, devuelve verdadero
    return true;
}



// Funcion que devuelve true si la caja de un sudoku es correcto, falso en caso contrario

function esCorrectoSudokuCaja(s,x){
    
    var i,j;
    var iniX, finX;
    var iniY, finY;
    
    // Array para marcar si una posicion esta o no, inicializado a 0
    var posiciones=[0,0,0,0,0,0,0,0,0]
   
    
    // Calculamos la iniX finX
    
    if(x==0 || x==1 || x==2){
        iniX=0;
        finX=3;
    }
    else if(x==3 || x==4 || x==5){
        iniX=3;
        finX=6;
    }
    else if(x==6 || x==7 || x==8){
        iniX=6;
        finX=9;
    }
     
            
    /// Calculamos la iniY finY
    if(x==0 || x==3 || x==6){
        iniY=0;
        finY=3;
    }
    else if(x==1 || x==4 || x==7){
        iniY=3;
        finY=6;
    }
    else if(x==2 || x==5 || x==8){
        iniY=6;
        finY=9;
    }
            
        // Comprobamos
            
    for(i=iniX;i<finX;i++){
        for(j=iniY;j<finY;j++){
            var pos=s[i][j]-1;
            posiciones[pos]=1;
        }
    }
    
    
    // Si estan las 9 posiciones marcadas
    for(i=0;i<9;i++){
        // Si alguna no se ha encontrado, devuelve falso
        if(posiciones[i]==0){
            return false;
        }
    }
    // Si estan todas, devuelve verdadero
    return true;
     
}




// Funcion que devuelve true si un sudoku es correcto, falso en caso contrario

function esSudokuCorrecto(s){
    var i;
    // Presuponemos que es correcto e intentamos demostrar lo contrario
    var esCorrecto=true;
    
    for(i=0;i<9;i++){
        // Si no es correcto...
        if(!esCorrectoSudokuHorizontal(s,i)){
            esCorrecto=false;
            break;
        }
    
        // Si no es correcto...
        if(!esCorrectoSudokuVertical(s,i)){
            esCorrecto=false;
            break;
        }
    
        // Si no es correcto...
        if(!esCorrectoSudokuCaja(s,i)){
            esCorrecto=false;
            break;
        }
    
    }
    return esCorrecto;
    
}


