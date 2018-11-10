<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connect.php';
	createUser();
}

function createUser(){
	global $connect;

	$name = $_POST["name"];
	$phone = $_POST["phone"];
	$password = $_POST["password"];

	$insertQuery = "INSERT INTO USER (name, phone, password) VALUES('$name', '$phone', '$password');";

	mysqli_query($connect, $insertQuery) OR DIE (mysqli_error($connect));
	mysqli_close($connect);
}
?>