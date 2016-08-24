<?php

require("conexion.php")
$nombre = $_POST['nombre'];
$apellidos = $_POST['apellidos'];

$query = "Insert into datos (nombre, apellidos) values ('$nombre','$apellidos')";
mysqli_query($conexion, $query) or die (mysqli_error());
mysqli_close();

?>

