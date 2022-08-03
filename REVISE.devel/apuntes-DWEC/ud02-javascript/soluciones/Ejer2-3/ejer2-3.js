

// Buscaminas

// Funcion que dado un tablero y una posicion, indica cuantas minas hay alrededor
function cuantasMinas(tab,x,y){
    minasEnc=0;
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


// Declaramos tablero origen y destino vacios
var tablero;
var minas;


tablero=new Array();

tablero[0]=[0,0,-1,0];
tablero[1]=[0,-1,-1,0];


tablero=[[-1,0,0,0],[0,0,0,0],[0,-1,0,0],[0,0,0,0]]

/* Otros ejemplos

    tablero=[[-1,-1,-1],[-1,0,-1]]

    tablero=[[-1,0,0,0],[0,0,0,0],[0,-1,0,0],[0,0,0,0]]



*/


    
    
// Recorremos el vector

// Inicilaizamos el array de minas
minas=new Array();

for(i=0;i<tablero.length;i++){
    // Declaramos dinamicamente el sub array
    minas[i]=new Array();
    
    for(j=0;j<tablero[i].length;j++){
        if(tablero[i][j]==-1){
            minas[i][j]=-1;
        }
        else{
            minas[i][j]=cuantasMinas(tablero,i,j);
        }
    }
}



for(i=0;i<tablero.length;i++){
        document.write(minas[i]+"<br>");
}


