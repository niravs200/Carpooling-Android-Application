<?php
#Program to fetch user location
#Created By Bhavneet Sachdeva

	include 'connect.php';
	
	#Fetching location coordinates based on Journey ID
	$query = "select User_Name,Contact,Start_Latitude,Start_Longitude,Destination_Latitude,Destination_Longitude from user,location,journey where user.Journey_ID = journey.Journey_ID and journey.Passenger_Location_ID=location.Location_ID;";
	
	$result = mysqli_query($connect, $query);
	
	$json_array = array();
	
	while($row = mysqli_fetch_assoc($result))
	{
		$json_array[] = $row;
	}
	
	$json_object = array('user' => $json_array);
	$json = json_encode($json_object);
	
	echo $json;
?>