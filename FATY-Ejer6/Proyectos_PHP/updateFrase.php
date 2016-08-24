<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $idFrase = $_POST['id'];
 $autor = $_POST['autor'];
 $frase = $_POST['frase'];
 $tipo = $_POST['tipo'];
 $rating = $_POST['rating'];
 
 //importing database connection script 
 require_once('dbConnect.php');
 
 //Creating sql query 
 $sql = "UPDATE frases SET autor = '$autor', frase = '$frase', tipoFrase = '$tipo', rating = '$rating' WHERE idFrase = $idFrase;";
 
 //Updating database table 
 if(mysqli_query($con,$sql)){
 echo 'Frase actualizada correctamente.';
 }else{
 echo 'No se pudo actualizar la frase, intenta otra vez.';
 }
 
 //closing connection 
 mysqli_close($con);
 }
?>