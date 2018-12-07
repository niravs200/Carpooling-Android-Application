<?php
#Program to verify username and password
#Created By Bhavneet Sachdeva

include 'connect.php';


$connect = mysqli_connect(hostname,user,password, database);

$username = $_GET['username'];
$password = $_GET['password'];


#Query to check password of a particular username
$sql = "SELECT User_ID from USER where User_Name = '$username' AND Password = '$password'";
$result = mysqli_query($connect,$sql);
$row = mysqli_fetch_array($result,MYSQLI_ASSOC);


$count = mysqli_num_rows($result);

#If password verfied return true otherwise false
if($count == 1){


 echo "true";

}
else{
	
	echo "false";
}


?>


