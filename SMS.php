<?php
	require __DIR__ . '/twilio-php-master/Twilio/autoload.php';
	use Twilio\Rest\Client;

	
	$account_sid = 'ACef8dc255ae7fe19115cc00382d412004';
	$auth_token = '4affa504f5dfe49d0ef72ee606a664b1';
	
	$twilio_number = "+19029070350";

	$client = new Client($account_sid, $auth_token);
	$client->messages->create(
		'+19024122798',
		array(
			'from' => $twilio_number,
			'body' => 'Your OTP is '.rand(1000,9999)
		)
	);
?>