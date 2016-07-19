<?php
	function error(){
		$data['success']=false;
		return json_encode($data);
	}
	include_once('../../include/conn.php');

	$data = array();

	mysqli_query($con, "DROP TABLE users") or die(error());
	mysqli_query($con, "DROP TABLE loginTokens") or die(error());
	mysqli_query($con, "DROP TABLE posts") or die(error());
	mysqli_query($con, "DROP TABLE locations") or die(error());
	mysqli_query($con, "DROP TABLE postLikes") or die(error());
	mysqli_query($con, "DROP TABLE videoViews") or die(error());
	mysqli_query($con, "DROP TABLE postComments") or die(error());
	
	$data['success'] = true;

	echo json_encode($data);
?>