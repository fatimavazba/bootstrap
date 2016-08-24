<?php 
 //Getting Id
 $idFrase = $_GET['id'];
 
 //Importing database
 require_once('dbConnect.php');
 
 //Creating sql query
 $sql = "DELETE FROM frases WHERE idFrase = $idFrase;";
 
 //Deleting record in database 
 if(mysqli_query($con,$sql)){
 echo 'Frase eliminada Correctamente.';
 }else{
 echo 'La frase no se pudo eliminar, intente otra vez.';
 }
 
 //closing connection 
 mysqli_close($con);
?>