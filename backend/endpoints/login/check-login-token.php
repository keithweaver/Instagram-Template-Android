<?php
	include_once('../include/conn.php');
	include_once('../include/response_objects.php');
	include_once('../include/common_functions.php');

	$r = new resGeneral();

	$apikey = pickup('apikey');
	$username = pickup('username');
	$email = pickup('email');
	$token = pickup('token');
	
	if($apikey != $REST_API_KEY){

	}else if($username == null || $username != null && strlen($username) < 3){

	}else if($email == null || $email != null && strlen($email) < 3){
	
	}else if($token == ""){

	}else{
		//validates the token exists
	}
	echo json_encode($r);
?>