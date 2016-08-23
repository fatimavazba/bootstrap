<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $nombre = $_POST['nombre'];
 $apellidos = $_POST['apellidos'];
 
 //Creating an sql query
 $sql = "INSERT INTO datos (nombre,apellidos) VALUES ('$nombre','$apellidos')";
 
 //Importing our db connection script
 require_once('conexion.php');
 
 //Executing query to database
 if(mysqli_query($conexion,$sql)){
 echo 'Archivo agregada Correctamente';
 }else{
 echo 'No se pudo Agregar la Archivo';
 }
 
 //Closing the database 
 mysqli_close($conexion);
 }
?>