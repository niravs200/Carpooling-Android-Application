<?php
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		include 'connect.php';
		showUser();
	}

	function showUser(){
		global $connect;

		$selectQuery = "SELECT * FROM USER;";

		$result = mysqli_query($connect, $selectQuery);
		$numberOfRows = mysqli_num_rows($result);

		$temp_array = array();

		if($numberOfRows > 0){
			while ($row = mysqli_fetch_assoc($result)) {
				$temp_array[] = $row;
			}
		}
		header('Content-Type: application/json');
		echo json_encode(array("user"=>$temp_array));
		mysqli_close($connect);
	}
?>