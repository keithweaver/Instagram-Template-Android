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
	
	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($username == null || $username != null && strlen($username) < 3){
		$r->success = 0;
		$r->message = "Error: User name cannot be less than 3 characters.";
	}else{
		$result = mysqli_query($con, "SELECT * FROM users WHERE username='$username'") or die(error("Error: Loading usernames"));
		if(mysqli_num_rows($result) == 0){
			$r->success = 1;
			$r->message = "User name is available.";
		}else{
			$r->success = 0;
			$r->message = "Error: User name taken.";
		}
	}
	echo json_encode($r);
?>