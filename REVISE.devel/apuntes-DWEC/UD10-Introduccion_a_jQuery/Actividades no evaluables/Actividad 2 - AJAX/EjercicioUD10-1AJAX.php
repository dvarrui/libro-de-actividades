<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");

function esPrimo($number)
{
    $flag = true;
    $max_count = sqrt($number);
    for($i=2; $i<=$max_count; $i++)
    {
        if($number%$i==0)
        {
            $flag = false;
                    break;
        }
    }
    return $flag;
}



// Recogemos con POST
$numero = $_POST["numero"];

// Establecemos semilla aleatoria para simular un falso retardo
srand((double)microtime()*1000000);
$numeroAleatorio = rand(0, 10);

// Simular un falso retardo por la red y el servidor
sleep($numeroAleatorio % 2);



// Con una objeto pasaremos el JSON
// Creamos el JSON http://php.net/manual/es/ref.json.php
// Si es primo  YES
// Si no es primo  NO

class envioDatos {
   var $esPrimo;
}


$jsonData=new envioDatos;

if(esPrimo($numero)) {
    $jsonData->esPrimo="YES";
}
else {
    $jsonData->esPrimo="NO";
}

echo(json_encode($jsonData));



?>