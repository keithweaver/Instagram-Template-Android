<?php
	function error($msg){
		$r->success = 0;
		$r->message = $msg;
		return json_encode($r);
	}
	include_once('../../include/conn.php');
	include_once('../../include/response_objects.php');
	include_once('../../include/common_functions.php');
	include_once('../../include/login_functions.php');//has to be second
	//there are functions from common functions being used in login_functions
	
	$r = new resGeneral();

	$apikey = pickup('apikey');
	$email = pickup('email');
	$password = pickup('password');

	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($email == null || $email != null && strlen($email) < 3){
		$r->success = 0;
		$r->message = "Error: Email cannot be less than 3 characters.";
	}else if($password == null || $password != null && strlen($password) < 3){
		$r->success = 0;
		$r->message = "Error: Password cannot be less than 3 characters.";
	}else{
		$result_email_look_up = mysqli_query($con, "SELECT * FROM users WHERE email='$email'") or die(error("Error: looking up users"));
		if(mysqli_num_rows($result_email_look_up) > 0){
			$r->success = 0;
			$r->message = "Error: Email taken.";
		}else{

			//Valid sign up
			$hash = generatePasswordHash();//function is in login_functions
			$encryptedPassword = crypt($password, $hash);

			$date = date('Y-m-d');
			mysqli_query($con, "INSERT INTO users (email, encryptedPassword, hash, createdDateTime) VALUES ('$email','$encryptedPassword','$hash','$date')") or die(error("Error: Server error"));

			//send verification email TODO

			$r->success = 1;
			$r->message = "Account added.";
		}
	}
	echo json_encode($r);
?>