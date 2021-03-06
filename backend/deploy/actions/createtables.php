<?php
	function error(){
		$data['success']=false;
		return json_encode($data);
	}
	include_once('../../include/conn.php');

	$data = array();

	mysqli_query($con, "CREATE TABLE users (id int NOT NULL AUTO_INCREMENT, username varchar(255), email varchar(255), encryptedPassword varchar(255), hash varchar(255), first varchar(255), last varchar(255), profileImg varchar(255), bio varchar(255), website varchar(255), verified int DEFAULT '0', isPrivate int DEFAULT '0', createdDateTime datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE loginTokens (id int NOT NULL AUTO_INCREMENT, userId int, token varchar(255), device varchar(255), invalid int DEFAULT '0', invalidReason varchar(255), datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE posts (id int NOT NULL AUTO_INCREMENT, userId int, caption varchar(255), locationId int, imgPath varchar(255), isVideo int, datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE locations (id int NOT NULL AUTO_INCREMENT, latitude varchar(255), longitude varchar(255), name varchar(255), PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE postLikes (id int NOT NULL AUTO_INCREMENT, postId int, userIdWhoLiked int, datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE videoViews (id int NOT NULL AUTO_INCREMENT, postId int, userIdWhoViewed int, datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE postComments (id int NOT NULL AUTO_INCREMENT, postId int, comment varchar(255), byId int, datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	mysqli_query($con, "CREATE TABLE following (id int NOT NULL AUTO_INCREMENT, userId int, isFollowing int, datetime_tab datetime, PRIMARY KEY (id))") or die(error());
	
	$data['success'] = true;

	echo json_encode($data);
?>