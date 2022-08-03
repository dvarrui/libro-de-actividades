// Funcion que dado un tablero y una posicion, indica cuantas minas hay alrededor
function cuantasMinas(tab,x,y){
     
     var minasEnc=0;
    //Comprobamos adyacentes
    if(x>0){
        if(tab[x-1][y]==-1){
            minasEnc++;
        }
    }
     
    
    if(x<tab.length-1){
        if(tab[x+1][y]==-1){
            minasEnc++;
        }
    }
    if(y>0){
        if(tab[x][y-1]==-1){
            minasEnc++;
        }
    }
     
    
    if(y<tab[x].length-1){
        if(tab[x][y+1]==-1){
            minasEnc++;
        }
    }
    
    
    if(y>0 && x>0){
        if(tab[x-1][y-1]==-1){
            minasEnc++;
        }
    }
    if(y>0 && x<tab.length-1){
        if(tab[x+1][y-1]==-1){
            minasEnc++;
        }
    }
     
    
    if(x>0 && y<tab[x].length-1){
        if(tab[x-1][y+1]==-1){
            minasEnc++;
        }
    }
    
    
    if(x<tab.length-1 && y<tab[x].length-1){
        if(tab[x+1][y+1]==-1){
            minasEnc++;
        }
    }
    
    
    
    
        
    //Devolvemos el resultado
    return minasEnc;
}


function contandoMinas(tablero){
    var i,j;
    // Declaramos el array
    var minas=[]; // Equivale a new Array();
    
    for(i=0;i<tablero.length;i++){
        // Declaramos dinamicamente el sub array
        minas[i]=new Array();
        
        for(j=0;j<tablero[i].length;j++){
            if(tablero[i][j]==-1){
                minas[i][j]=-1;
            }
            else{
                // Contamos las minas con la funcion auxiliar
                minas[i][j]=cuantasMinas(tablero,i,j);
            }
        }
    }
    // DEvolvemos el resultado
    return minas;
    
}

