<?php
	include_once('../include/conn.php');
	include_once('../include/response_objects.php');
	include_once('../include/common_functions.php');

	$r = new resGeneral();

	$apikey = pickup('apikey');
	$email = pickup('email');
	$password = pickup('password');

	if($apikey != $REST_API_KEY){

	}else if($email == null || $email != null && strlen($email) < 3){

	}else if($password == null || $password != null && strlen($password) < 3){

	}else{

	}
	echo json_encode($r);
?>