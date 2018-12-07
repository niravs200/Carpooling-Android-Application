<?php
#Program to reset password 
#Created By Bhavneet Sachdeva


include 'connect.php';

#Establishing connection
$connect = mysqli_connect(hostname,user,password, database);

#Fetch username and password from the application
$name = $_GET['username'];
$password = $_GET['password'];



#Reseting Password
$sql = "update user set password = '$password' where User_Name='$name'";



$result = mysqli_query($connect,$sql);

echo $name , $password;

mysqli_close($connect);



?>