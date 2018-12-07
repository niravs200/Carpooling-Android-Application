<?php
#Program to fetch poolers starting and destination location for navigation
#Created by Nirav Solanki


include 'connect.php';

#Establishing Connection
$connect = mysqli_connect(hostname,user,password, database);


$name = $_GET['name'];
$flag = $_GET['flag'];



#If flag is true, fetching starting location
if(strcmp($flag,'true')===0){
	$sql = "select Start_Latitude,Start_Longitude from location,journey,user where location_id = journey.passenger_location_id and journey.journey_id = user.journey_id and User_Name = '$name'";
	
	$result = mysqli_query($connect,$sql);
	$response = array();

	if($row = mysqli_fetch_array($result)){
		#Returning latitude and longitude as responce
		echo "$row[Start_Latitude],$row[Start_Longitude]";
	}
	else
		{
			echo "false";
		}	
	}
	else{
	
	$sql = "select Destination_Latitude,Destination_Longitude from location,journey,user where location_id = journey.passenger_location_id and journey.journey_id = user.journey_id and User_Name = '$name'";
	
	
	$result = mysqli_query($connect,$sql);
	$response = array();

	#Returning Destination Location if flag is false
	if($row = mysqli_fetch_array($result)){

		echo "$row[Destination_Latitude],$row[Destination_Longitude]";
	}
	else
		{
			echo "false";
		}	
	
	}










?>