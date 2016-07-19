<?php
	function error($msg){
		$r->success = 0;
		$r->message = $msg;
		return json_encode($r);
	}
	include_once('../../include/conn.php');
	include_once('../../include/response_objects.php');
	include_once('../../include/common_functions.php');

	$r = new resPosts();

	$apikey = pickup('apikey');
	$token = pickup('token');
	$username = pickup('username');
	
	if($apikey != $REST_API_KEY){
		$r->success = 0;
		$r->message = "Error: Invalid API Key.";
	}else if($token == ""){
		$r->success = 0;
		$r->message = "Error: Blank token.";
	}else if($username == ""){
		$r->success = 0;
		$r->message = "Error: Blank user name.";
	}else{	
		$userId = -1;
		$result_find_user_id = mysqli_query($con, "SELECT * FROM loginTokens WHERE token='$token'") or die(error("Error: loading current user info"));
		while($row_find_user_id = mysql_fetch_array($result_find_user_id)){
			$userId = $row_find_user_id['userId'];
		}

		$validateUserIdResult = mysqli_query($con, "SELECT * FROM users WHERE id='$userId' AND username='$username'") or die(error("Error: loading user info"));

		if($userId == -1 || mysqli_num_rows($validateUserIdResult) == 0){
			$r->success = 0;
			$r->message = "Error: unable to find information";
		}else{
			$posts = array();

			$result = mysqli_query($con, "SELECT * FROM posts ORDER BY id DESC") or die("Error: loading posts");
			while($row = mysqli_fetch_array($result)){
				$userThatPosted = $row['userId'];

				$result_following = mysqli_query($con, "SELECT * FROM following WHERE userId='$userId' AND isFollowing='$userThatPosted'") or die(error("Error: looking up users that youre following"));
				if(mysqli_num_rows($result_following) > 0){
					$postId = $row['id'];

					$p = new post();
					$p->id = $postId;
					$p->userId = $userThatPosted;


					//Get User information
					$profileImg = "";
					$userThatPostedUserName = "";
					$result_user_look_up = mysqli_query($con, "SELECT * FROM users WHERE id='$userThatPosted'") or die(error("Error: looking up posters info"));
					while($row_user_look_up = mysqli_fetch_array($result_user_look_up)){
						$profileImg = $row_user_look_up['profileImg'];
						$userThatPostedUserName = $row_user_look_up['username'];
					}

					$p->profileUserName = $userThatPostedUserName;
					$p->profileImg = $profileImg;


					//Get Location
					$locationId = $row['locationId'];
					$locationName = "";
					if($locationId != "" && $locationId != null && $locationId != -1){
						$result_location = mysqli_query($con, "SELECT * FROM locations WHERE id='$locationId'") or die(error("Error: loading location"));
						while($row_location = mysqli_fetch_array($result_location)){
							$locationName = $row_location['name'];
						}
					}

					$p->locationId = $locationId;
					$p->locationName = $locationName;

					//Get Image Related
					$p->caption = $row['caption'];
					$p->imgPath = $row['imgPath'];
					if($row['isVideo'] == 1){
						$p->isVideo = true;
					}else{
						$p->isVideo = false;
					}
					$p->datetime = $row['datetime_tab'];

					//Get Likes
					$likes = array();
					$result_likes = mysqli_query($con, "SELECT * FROM postLikes WHERE postId='$postId'") or die(error("Error: Unable to look up post likes"));
					while($row_likes = mysqli_fetch_array($row_likes)){
						$pL = new postLike();
						
						$pL->id = $row_likes['id'];
						$pL->postId = $postId;

						$userWhoLikedPost = $row['userIdWhoLiked'];

						$pL->user = $userWhoLikedPost;
						
						$isFollowing = false;
						$result_user_who_liked_post = mysqli_query($con, "SELECT * FROM following following WHERE userId='$userId' AND isFollowing='$userWhoLikedPost'") or die(error("Error: looking up following of user who liked a post"));
						if(mysqli_num_rows($result_user_who_liked_post) > 0){
							$isFollowing = true;
						}

						$pL->isFollowing = $isFollowing;

						array_push($likes, $pL);
					}
					$p->likes = $likes;

					//Get views
					if($p->isVideo == true){
						$result_video = mysqli_query($con, "SELECT * FROM videoViews WHERE postId='$postId'") or die(error("Error: Video views"));
						$p->views = mysqli_num_rows($result_video);
					}

					//Get Comments
					$comments = array();
					$result_comments = mysqli_query($con, "SELECT * FROM postComments WHERE postId='$postId'") or die(error("Error: loading comments"));
					while($row_comments = mysqli_fetch_array($result_comments)){
						$pC = new postComment();
						
						$pC->id = $row_comments['id'];
						$pC->postId = $postId;
						$pC->comment = $row_comments['comment'];
						
						$commenterUserId = $row_comments['byId'];
						$commenterUserName = "";
						$result_comment_user_look_up = mysqli_query($con, "SELECT * FROM users WHERE id='$commenterUserId'") or die(error("Error: looking up commenter user name"));
						while($row_comment_user_look_up = mysqli_fetch_array($result_comment_user_look_up)){
							$commenterUserName = $row_comment_user_look_up['username'];
						}
						$pC->user = $commenterUserName;

						array_push($comments, $pC);
					}
					$p->comments = $comments;

					array_push($posts, $p);
				}
			}


			$r->success = 1;
			$r->message = "Completed loading posts";
			$r->posts = $posts;
		}
	}
	echo json_encode($r);
?>