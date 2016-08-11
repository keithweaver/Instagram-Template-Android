<?php
	include_once('../../include/conn.php');
	include_once('../../include/response_objects.php');
	include_once('../../include/common_functions.php');
	include_once('../../include/login_functions.php');//this has to be after common_functions
	date_default_timezone_set("US/Eastern");
	
	$r = new resLogIn();

	function error($msg){
		$r->success = 0;
		$r->message = $msg;
		return json_encode($r);
	}

	$apikey = pickup('apikey');
	$email = pickup('email');
	$password = pickup('password');

	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key";
	}else if($email == null || $email != null && strlen($email) < 3){
		$r->success = 0;
		$r->message = "Error: Email blank or too short.";
	}else if($password == null || $password != null && strlen($password) < 3){
		$r->success = 0;
		$r->message = "Error: Password blank or too short.";
	}else{
		$result = mysqli_query($con, "SELECT * FROM users WHERE email='$email'") or die(error("Error: Loading user data"));
		if(mysqli_num_rows($result) != 1){
			$r->success = 0;
			$r->message = "Error: Invalid email or password.";
		}else{
			$encryptedPassword = "";
			$hash = "";
			$userId = -1;
			$userName = "";
			while($row = mysqli_fetch_array($result)){
				$hash = $row['hash'];
				$encryptedPassword = $row['encryptedPassword'];
				$userId = $row['id'];
				$userName = $row['username'];
			}

			$encryptedPasswordFromInput = crypt($password, $hash);
			if($encryptedPasswordFromInput == $encryptedPassword && $userId != -1){
				//Valid log in information

				$logInToken = generateLogInToken();

				$datetime = date('Y-m-d H:i:s');
				mysqli_query($con, "INSERT INTO loginTokens (userId, token, datetime_tab) VALUES ('$userId','$logInToken','$datetime')") or die(error("Error: Saving login info."));

				$r->success = 1;
				$r->message = $logInToken;
				$r->usernames = array($userName);
			}else{
				//invalid
				$r->success = 0;
				$r->message = "Error: Invalid email or password.";
			}
		}
	}
	echo json_encode($r);
?>