<?php
	include_once('../../include/conn.php');

	$data = array();

	mysqli_query($con, "CREATE TABLE users (id int NOT NULL AUTO_INCREMENT, username varchar(255), email varchar(255), encryptedPassword varchar(255), hash varchar(255), verified int, isPrivate int, createdDateTime datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE loginTokens (id int NOT NULL AUTO_INCREMENT, userId int, token varchar(255), device varchar(255), datetime_tab datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE posts (id int NOT NULL AUTO_INCREMENT, userId int, caption varchar(255), locationId int, imgPath varchar(255), isVideo int, datetime_tab datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE locations (id int NOT NULL AUTO_INCREMENT, latitude varchar(255), longitude varchar(255), name varchar(255), PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE postLikes (id int NOT NULL AUTO_INCREMENT, postId int, userIdWhoLiked int, datetime_tab datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE videoViews (id int NOT NULL AUTO_INCREMENT, postId int, userIdWhoViewed int, datetime_tab datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
	mysqli_query($con, "CREATE TABLE postComments (id int NOT NULL AUTO_INCREMENT, postId int, comment varchar(255), byId int, datetime_tab datetime, PRIMARY KEY (id))") or die($data['success']=false;echo json_encode($data););
		
	$data['success'] = true;

	echo json_encode($data);
?>