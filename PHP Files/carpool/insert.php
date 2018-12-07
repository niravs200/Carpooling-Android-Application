<?php
#Program to insert user profile details into the database
#Created By Bhavneet Sachdeva
	
	include 'connect.php';
	


$connect = mysqli_connect(hostname,user,password, database);

	
	$name = $_GET['name'];
	$password = $_GET['password'];
	$phone = $_GET['contactNo'];
	
$val = rand(00000,99999);	#Generating random 5 digit number for User ID

#Query to store user details in database
$sql = "INSERT INTO USER (User_ID,User_Name,Password,Contact) values ($val,'$name','$password','$phone')";

echo $sql;

$result = mysqli_query($connect,$sql);



mysqli_close($connect);

?>