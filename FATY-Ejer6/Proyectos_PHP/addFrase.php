<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $autor = $_POST['autor'];
 $frase = $_POST['frase'];
 $tipo = $_POST['tipo'];
 $rating = $_POST['rating'];
 
 //Creating an sql query
 $sql = "INSERT INTO frases (autor,frase,tipoFrase,rating) VALUES ('$autor','$frase','$tipo','$rating')";
 
 //Importing our db connection script
 require_once('dbConnect.php');
 
 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Frase agregada Correctamente';
 }else{
 echo 'No se pudo Agregar la Frase';
 }
 
 //Closing the database 
 mysqli_close($con);
 }
?>