<?php 
 //Importing Database Script 
 require_once('conexion.php');
 
 //Creating sql query
 $sql = "SELECT * FROM datos";
 
 //getting result 
 $r = mysqli_query($conexion,$sql);
 
 //creating a blank array 
 $result = array();
 
 //looping through all the records fetched
 while($row = mysqli_fetch_array($r)){
 
 //Pushing name and id in the blank array created 
 array_push($result,array(
 "id"=>$row['id'],
 "nombre"=>$row['nombre'],
 "apellidos"=>$row['apellidos']
 ));
 }
 
 //Displaying the array in json format 
 echo json_encode(array('result'=>$result));
 
 mysqli_close($conexion);
?>