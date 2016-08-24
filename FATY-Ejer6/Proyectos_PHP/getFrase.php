<?php 
 
 //Getting the requested id
 $idFrase = $_GET['id'];
 
 //Importing database
 require_once('dbConnect.php');
 
 //Creating sql query with where clause to get an specific employee
 $sql = "SELECT * FROM frases WHERE idFrase=$idFrase";
 
 //getting result 
 $r = mysqli_query($con,$sql);
 
 //pushing result to an array 
 $result = array();
 $row = mysqli_fetch_array($r);
 array_push($result,array(
 "id"=>$row['idFrase'],
 "autor"=>$row['autor'],
 "frase"=>$row['frase'],
 "tipo"=>$row['tipoFrase'],
 "rating"=>$row['rating']
 ));
 
 //displaying in json format 
 echo json_encode(array('result'=>$result));
 
 mysqli_close($con);
?>