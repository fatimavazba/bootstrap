<?php

$servidor = 'localhost';
$user = 'root'
$pass = 'root'
$bd = 'mybd'

$conexion = mysqli_connect($servidor, $user, $pass, $bd);
if (mysqli_connect_errno()){
	echo "Fallo la conexion MYSQL:",mysqli_connect_error();
}

echo "Excelente!!";

?>