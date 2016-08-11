<?php
	class resGeneral{
		public $success = 0;
		public $message = "";
	}
	class resLogIn{
		public $success = 0;
		public $message = "";
		public $usernames = array();
	}
	class resPosts{
		public $success = 0;
		public $message = "";
		public $posts = array();
	}
	class post{
		public $id = 0;
		public $profileImg = "";
		public $profileUserName = "";
		public $locationId = "";
		public $locationName = "";
		public $imgPath = "";
		public $caption = "";
		public $likes = array();
		public $views = 0;
		public $isVideo = false;
		public $comments = array();
		public $datetime = "";
	}
	class postLike{
		public $id = 0;
		public $postId = 0;
		public $user = "";
		public $isFollowing = "";
	}
	class postComment{
		public $id = 0;
		public $postId = 0;
		public $user = "";
		public $comment = "";
	}
?>