//funcion que dice si es palindromo

function esPalindromo(n){

    // recorremos 
    for(var i=0, j=n.length-1;i<j;i++,j--){

        // Si no coinciden, no es palindrom
        if(n[i]!=n[j])
            return false;
    }
    // Si llega al final, es que es palindromo ya que todas las comparaciones son verdaderas
    return true;
}


// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    // Evento de hacer click a comprobar
    $("#comprobar").click( function(){
        var num=$("#numero").val();
        // Enviamos por Ajax
        $.ajax({
            method:"POST",
            url:"http://hispabyte.net/DWEC/EjercicioUD10-1AJAX.php",
            data:{ numero : num }
            })
            .done(function(msg) {
                var x=JSON.parse(msg);
                if(x.esPrimo=="YES" && esPalindromo(num) ){
                    alert(num+" Primo y palindromo!!!!!");
                }else if(x.esPrimo=="NO" && esPalindromo(num) ){
                    alert(num+" No es primo, pero si palindromo");
                } else if(x.esPrimo=="YES" && !esPalindromo(num)){
                    alert(num+" Primo, pero no palindromo");
                } else{
                    alert(num+" No es primo ni palindromo")
                }
            })
            .fail(function() {
            alert("Error");
            })
            .always(function() {
            alert("Trabajo realizado");
            });
        }
    );
});