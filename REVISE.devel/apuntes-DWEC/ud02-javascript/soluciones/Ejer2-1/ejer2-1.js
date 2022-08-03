//Ejemplo de sudokus
sudoku=new Array();
sudoku[0]=[1,2,3,4,5,6,7,8,9];
sudoku[1]=[7,8,9,1,2,3,4,5,6];
sudoku[2]=[4,5,6,7,8,9,1,2,3];
sudoku[3]=[3,1,2,6,4,5,9,7,8];
sudoku[4]=[9,7,8,3,1,2,6,4,5];
sudoku[5]=[6,4,5,9,7,8,3,1,2];
sudoku[6]=[2,3,1,5,6,4,8,9,7];
sudoku[7]=[8,9,7,2,3,1,5,6,4];
sudoku[8]=[5,6,4,8,9,7,2,3,1];



// Funcion que devuelve true si el horizontal de un sudoku es correcto, falso en caso contrario

function esCorrectoSudokuHorizontal(s,x){
    var i;
    // Array para marcar si una posicion esta o no, inicializado a 0
    posiciones=[0,0,0,0,0,0,0,0,0]
    for(i=0;i<9;i++){
        pos=s[x][i]-1;
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
    posiciones=[0,0,0,0,0,0,0,0,0]
    for(i=0;i<9;i++){
        pos=s[i][x]-1;
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
    posiciones=[0,0,0,0,0,0,0,0,0]
   
    
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
            pos=s[i][j]-1;
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

function esCorrectoSudoku(s){
    var i;
    // Presuponemos que es correcto e intentamos demostrar lo contrario
    esCorrecto=true;
    
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




// Llamamos a la funcion para comprobar el sudoku
if(esCorrectoSudoku(sudoku)){
    alert("El sudoku es correcto");
}
else{
    alert("El sudoku no es correcto");
}
