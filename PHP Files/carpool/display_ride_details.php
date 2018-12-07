<?php
#Program to fetch list of poolers
#Created By Bhavneet Sachdeva


include 'connect.php';

$connect = mysqli_connect(hostname,user,password, database);


$name = $_GET['name'];

$sql = "select User_Name, Contact from user,journey where user_id = (
select Driver_ID from journey,user where user.Journey_ID = journey.Journey_ID and User_Name='$name' and status=1)";


$result = mysqli_query($connect,$sql);
$response = array();

if($row = mysqli_fetch_array($result)){

echo "$row[User_Name],$row[Contact]";
}
else
{
	echo "false";
}	







?>