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
	function generateString($size){
		$l = array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s");
		$s = "";
		for($i = 0;$i < $size;$i++){
			$s .= $l[rand(0, count($l)-1)];
		}
		return $s;
	}
?>