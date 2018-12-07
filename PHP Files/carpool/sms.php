<?php
#Program to Send SMS for Authentication
#Created By Nirav Solanki
#Refrence Used: "Twilio SMS PHP Quickstart - Send & Receive SMS", Twilio.com, 2018. [Online]. Available: #https://www.twi#lio.com/docs/sms/quickstart/php. [Accessed: 06- Dec- 2018].

	#Fetching Twilio Library
	require __DIR__ . '/twilio-php-master/Twilio/autoload.php';
	use Twilio\Rest\Client;

	include 'connect.php';

	#Establishing Connection
	$connect = mysqli_connect(hostname,user,password, database);

	$username = $_GET['username'];

	
	#Credentials
	$account_sid = 'ACef8dc255ae7fe19115cc00382d412004';
	$auth_token = '4affa504f5dfe49d0ef72ee606a664b1';
	#Fetching contact number from the database
	$sql = "select Contact from user where User_Name = '$username'";
	$result = mysqli_query($connect,$sql);
	$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
	

	$count = mysqli_num_rows($result);
	$twilio_number = "+19029070350"; #Twillio Number from which message is sent
	
	#Sending SMS
	if($count==1){
		$var = $row['Contact'];
	$otp = rand(1000,9999);
	$client = new Client($account_sid, $auth_token);
	$client->messages->create(
		$var,
		array(
			'from' => $twilio_number,
			'body' => 'Your OTP is '.$otp
		)
	);
	
	echo $otp;
	}
else{
echo "false";
}	


	

	
	
	
?>
