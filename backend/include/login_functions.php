<?php
	function generatePasswordHash(){
		$str = "";
		$con = mysqli_connect("localhost","root","root","instadb") or die("Error: unable to connect");
		$found = false;
		while($found != true){
			$str = generateString(rand(50,100));
			$result = mysqli_query($con, "SELECT * FROM users WHERE hash='$str'") or die("error 001");
			if(mysqli_num_rows($result) == 0){
				$found = true;
				return $str;
			}
		}
		return $str;
	}
	function generateLogInToken(){
		$str = "";
		$con = mysqli_connect("localhost","root","root","instadb") or die("Error: unable to connect");
		$found = false;
		while($found != true){
			$str = generateString(rand(20,100));
			$result = mysqli_query($con, "SELECT * FROM loginTokens WHERE token='$str'") or die("error 001");
			if(mysqli_num_rows($result) == 0){
				$found = true;
				return $str;
			}
		}
		return $str;
	}
?>