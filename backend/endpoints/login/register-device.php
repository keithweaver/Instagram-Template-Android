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
	$device = pickup('device');
	
	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($username == null || $username != null && strlen($username) < 3){
		$r->success = 0;
		$r->message = "Error: User name cannot be less than 3 characters.";
	}else if($email == null || $email != null && strlen($email) < 3){
		$r->success = 0;
		$r->message = "Error: Email too short.";
	}else if($token == ""){
		$r->success = 0;
		$r->message = "Error: Token cannot be blank.";
	}else if($device == ""){
		$r->success = 0;
		$r->message = "Error: Device cannot be blank.";
	}else{
		//stores the device
		$userId = -1;
		$result = mysqli_query($con, "SELECT * FROM users WHERE username='$username' AND email='$email'") or die(error("Error: Loading user info"));
		while($row = mysqli_fetch_array($result)){
			$userId = $row['id'];
		}
		if($userId == -1){
			$r->success = 0;
			$r->message = "Error: Cannot find user info";
		}else{
			$result = mysqli_query($con, "SELECT * FROM loginTokens WHERE userId='$userId' AND token='$token'") or die(error("Error: loading login info"));
			if(mysqli_num_rows($result) != 1){
				$r->success =0;
				$r->message = "Error: Cannot find log in information";
			}else{

				mysqli_query($con, "UPDATE loginTokens SET device='$device' WHERE token='$token' AND userId='$userId'") or die(error("Error: Saving device"));

				$r->success = 1;
				$r->message = "Stored";
			}
		}
	}
	echo json_encode($r);
?>