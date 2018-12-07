<?php
#Program to update user ride request in the database
#Created By Bhavneet Sachdeva

	require 'connect.php';
	createUserJourney();

function createUserJourney(){
	global $connect;

	#Fetching Locations and Time
	$pickupLatitude = $_GET["pickupLatitude"];
	$pickUpLongitude = $_GET["pickupLongitude"];
	$dropLatitude = $_GET["dropLatitude"];
	$dropLongitude = $_GET["dropLongitude"];
	$time = $_GET["time"];
	$name = $_GET["name"];
	
	$loc = rand(00000,99999);
	$jid = rand(00000,99999);

	#Inserting location in Location table
	$insertQuery = "INSERT INTO location (Start_Latitude, Start_Longitude, Destination_Latitude, Destination_Longitude,Location_ID) VALUES('$pickupLatitude', '$pickUpLongitude', '$dropLatitude', '$dropLongitude',$loc);";

		mysqli_query($connect, $insertQuery);
		echo "DOne";
	
		#Making Journey ID and associating it with Location ID
	   $insertQuery2 = "INSERT INTO journey (Journey_ID,Passenger_Location_ID,Time) VALUES($jid,$loc,'$time');";
	   mysqli_query($connect, $insertQuery2);
	   echo "DOne";
	   #Associating Journey ID with User
	   $insertQuery3 = "UPDATE user set Journey_ID = $jid where User_name = '$name'";
	   mysqli_query($connect,$insertQuery3);
	   echo "DOne";
	   mysqli_close($connect);

}

?>