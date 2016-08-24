<?php
 
 //Defining Constants
 define('HOST','localhost');
 define('USER','root');
 define('PASS','root');
 define('DB','web_service');
 
 //Connecting to Database
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
?>