<?php
	function grab($box){
		$data = $_GET[$box];
		$data = trim($data);
		$data = addslashes($data);
		$data = htmlspecialchars($data);
		return $data;
	}
	function pickup($box){
		//conLog($box);
		$data = $_POST[$box];
		$data = trim($data);
		$data = addslashes($data);
		$data = htmlspecialchars($data);
		
		return $data;
	}
?>