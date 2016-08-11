<?php
	include_once('../../include/conn.php');
	include_once('../../include/response_objects.php');
	include_once('../../include/common_functions.php');
	date_default_timezone_set("US/Eastern");
	
	$r = new resGeneral();

	function error($msg){
		$r->success = 0;
		$r->message = $msg;
		return json_encode($r);
	}

	$apikey = pickup('apikey');
	$username = pickup('username');
	$email = pickup('email');
	$token = pickup('token');
	
	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($username == null || $username != null && strlen($username) < 3){
		$r->success = 0;
		$r->message = "Error: User name too short.";
	}else if($email == null || $email != null && strlen($email) < 3){
		$r->success = 0;
		$r->message = "Error: Email too short.";
	}else if($token == ""){
		$r->success = 0;
		$r->message = "Error: Token cannot be blank.";
	}else{
		//validates the token exists
		$userId = -1;
		$result = mysqli_query($con, "SELECT * FROM users WHERE username='$username' AND email='$email'") or die(error("Error: Loading user info."));
		while($row = mysqli_fetch_array($result)){
			$userId = $row['id'];
		}

		if($userId != -1){

			$result_check_token = mysqli_query($con, "SELECT * FROM loginTokens WHERE userId='$userId' AND token='$token' AND invalid='0'") or die(error("Error: Loading users login info."));
			if(mysqli_num_rows($result_check_token) > 0){
				$r->success = 1;
				$r->message = "Error: Token cannot be blank.";
			}else{
				$r->success = 0;
				$r->message = "Error: Invalid log in. Please log in again.";
			}
		}else{
			$r->success = 0;
			$r->message = "Error: Cannot find users.";
		}
	}
	echo json_encode($r);
?>