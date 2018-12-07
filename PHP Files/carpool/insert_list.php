<?php
#Program to Update user ride request acceptance in the database
#Created By Bhavneet Sachdeva

include 'connect.php';

$connect = mysqli_connect(hostname,user,password, database);


$name = $_GET['name'];
$driverName = $_GET['driverName'];



#Query to add Jorney Status, Driver ID in Journey table
$sql = "update journey set status = 1, Driver_ID = (select User_ID from user where User_Name='$driverName') where Journey_ID = (select Journey_ID from user where User_Name = '$name')";

echo $sql;

$result = mysqli_query($connect,$sql);




mysqli_close($connect);



?>