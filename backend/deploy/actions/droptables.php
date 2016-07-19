<?php
	include_once('../../include/conn.php');

	$data = array();

	mysqli_query($con, "DROP TABLE users") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE loginTokens") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE posts") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE locations") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE postLikes") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE videoViews") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "DROP TABLE postComments") or die($data['success']=false;echo json_encode($data););
	
	$data['success'] = true;

	echo json_encode($data);
?>