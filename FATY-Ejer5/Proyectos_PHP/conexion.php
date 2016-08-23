<?php

 //Defining Constants
 define('HOST','localhost');
 define('USER','root');
 define('PASS','root');
 define('DB','mybd');
 
 //Connecting to Database
$conexion = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
if (mysqli_connect_errno()){
	echo "Fallo la conexion MYSQL:",mysqli_connect_error();
}

?>