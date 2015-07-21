<?php
	include_once('');
	$username = $_POST['username'];
	$code = $_POST['pass'];
	if($code == "SOME_HIDDEN_CODE"){
		if($username != ""){
			$result = mysqli_query($con, "SELECT * FROM posts ORDER BY date_tab") or die("Failed 003");
			$s = "";
			while($row = mysqli_fetch_array($result)){
				$postUser = $row['user'];
				$result_friends = mysqli_query($con, "SELECT * FROM friends WHERE (user1='$username' AND user2='$postUser') OR (user1='$postUser' AND user2='$username')") or die("Failed 004");
				if(mysqli_num_rows($result_friends)==1){
					//yes friends
					if($s == ""){
						$s .= $row['postCode'] . "|" . $row['title'] . "|" . $row['about'] . "|" . $row['link'] . "|" . $row['likes'];
					}else{
						$s .= "|" . $row['postCode'] . "|" . $row['title'] . "|" . $row['about'] . "|" . $row['link'] . "|" . $row['likes'];
					}
				}
			}	
			echo $s;
		}else{
			echo 'Failed 002';
		}
	}else{
		echo 'Failed 001';
	}
?>