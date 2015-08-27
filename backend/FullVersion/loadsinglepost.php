<?php

	
	include_once('./include/conn.php');
	$postCode = $_POST['code'];
	$code = $_POST['pass'];
	if($code == "SOME_HIDDEN_CODE"){
		if($username != ""){
			$result = mysqli_query($con, "SELECT * FROM posts WHERE postCode='$postCode'") or die("Failed 003");
			$s = "";
			while($row = mysqli_fetch_array($result)){
				$postUser = $row['user'];
					//yes friends
					if($s != ""){
						$s .= "|";
					}
					$s .= $row['postCode'] . "|" . $row['title'] . "|" . $row['user'] . "|" . $row['link'] . "|" . $row['likes'] . "|" . $row['profileImg'];
					
			
			}	
			echo $s;
		}else{
			echo 'Failed 002';
		}
	}else{
		echo 'Failed 001';
	}
?>