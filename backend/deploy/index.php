<html>
<head>
</head>
<body>
<p id="status-text">

</p>
<br/>
<br/>
<p>
	<a href="#add" onclick="return createTables(false);">
		Create Tables
	</a>
</p>
<p>
	<a href="#drop" onclick="return dropTables(false);">
		Drop Tables
	</a>
</p>
<p>
	<a href="#fake" onclick="return addFakeData();">
		Add Fake Data
	</a>
</p>
<p>
	<a href="#doall" onclick="return doAll();">
		Do all actions (drop, create, add fake data)
	</a>
</p>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
function dropTables(doNext){
	var formData = {};
	$.ajax({
		type        : 'POST',
		url         : './actions/droptables.php',
		data        : formData,
		dataType    : 'json',
		encode      : true
	}).done(function(data) {
		if(!data.success){
			updateStatus("Something went wrong with drop tables");
		}else{
			updateStatus("Dropped tables");
		}
		if(doNext){
			createTables(doNext);
		}
	});
	return false;
}
function createTables(doNext){
	var formData = {};
	$.ajax({
		type        : 'POST',
		url         : './actions/createtables.php',
		data        : formData,
		dataType    : 'json',
		encode      : true
	}).done(function(data) {
		if(!data.success){
			updateStatus("Something went wrong with create tables");
		}else{
			updateStatus("Created tables");
			if(doNext){
				addFakeData();
			}
		}
	});
	return false;
}
function addFakeData(){
	var formData = {};
	$.ajax({
		type        : 'POST',
		url         : './actions/addfakedata.php',
		data        : formData,
		dataType    : 'json',
		encode      : true
	}).done(function(data) {
		if(!data.success){
			updateStatus("Something went wrong with adding fake data");
		}else{
			updateStatus("Fake data added. All setup!");
		}
		
	});
	return false;
}
function doAll(){
	dropTables(true);
	return false;
}
function updateStatus(msg){
	$("#status-text").html(msg);
}
</script>
</body>
</html>