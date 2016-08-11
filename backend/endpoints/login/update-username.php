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
	$password = pickup('password');

	
	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($username == null || $username != null && strlen($username) < 3){
		$r->success = 0;
		$r->message = "Error: User name cannot be blank.";
	}else if($email == null || $email != null && strlen($email) < 3){
		$r->success = 0;
		$r->message = "Error: Email cannot be blank.";
	}else{
		//updates the username
		$result = mysqli_query($con, "SELECT * FROM users WHERE email='$email'") or die(error("Error: Loading user data"));
		if(mysqli_num_rows($result) != 1){
			$r->success = 0;
			$r->message = "Error: Invalid email or password.";
		}else{
			$encryptedPassword = "";
			$hash = "";
			$userId = -1;
			while($row = mysqli_fetch_array($result)){
				$hash = $row['hash'];
				$encryptedPassword = $row['encryptedPassword'];
				$userId = $row['id'];
			}

			$encryptedPasswordFromInput = crypt($password, $hash);
			if($encryptedPasswordFromInput == $encryptedPassword && $userId != -1){
				//Valid log in information

				$result = mysqli_query($con, "SELECT * FROM users WHERE username='$username'") or die(error("Error: Loading usernames"));
				if(mysqli_num_rows($result) == 0){

					mysqli_query($con, "UPDATE users SET username='$username' WHERE id='$userId'") or die(error("Error: Saving user name"));

					$r->success = 1;
					$r->message = "Success! User name updated.";
				}else{
					$r->success = 0;
					$r->message = "Error: User name taken.";
				}

			}else{
				//invalid
				$r->success = 0;
				$r->message = "Error: Invalid email or password.";
			}
		}
	}
	echo json_encode($r);
?>