<?php
	include_once('./include/conn.php');
	$username = $_POST['username'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	$code = $_POST['pass'];
	if($code == "SOME_HIDDEN_CODE"){
		if($username != "" && $password != "" && $email != ""){
			$result = mysqli_query($con, "SELECT * FROM users WHERE username='$username'") or die("Failed 004");
			if(mysqli_num_rows($result) == 0){
				$date = date('Y-m-d');
				mysqli_query($con, "INSERT INTO users (username, password, email, date_tab) VALUES ('$username','$password','$email','$date')") or die("Failed 005");
				echo 'Success';
			}else{
				echo 'Failed 003';
			}
		}else{
			echo 'Failed 002';
		}
	}else{
		echo 'Failed 001';
	}
?>