<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id='form1' action="uploadForm" method="post" enctype="multipart/form-data">
		<input type='file' name='file'>
		<input type='submit'>
	</form>
	savedFileName: ${savedFileName}
	
	<hr />
	<form id='form2' action="uploadForm" method="post" enctype="multipart/form-data" target="ifr">
		<input type='hidden' name='type' value="ifr"/>	
		<input type='file' name='file'/>
		<input type='submit' name="iframe으로 제출"/>	
	</form>
	iframe-savedFileName: ${savedFileName} <span id="upfile"></span>
	<iframe frameborder="1" width="800" height="600" name="ifr"></iframe>
	
	<hr />
	<div class="fileDrop">Drop Hear!!</div>
	<div class="uploadedList"></div>
	<form id='form3' action="uploadAjax" method="post" enctype="multipart/form-data">
		<input type='hidden' name='type' value="ajax"/>	
		<input type='file' name='file' id="ajax-file"/>
		<input type='submit' name="ajax로 제출"/>	
	</form>
	<div id="percent"> 0 % </div>
	<div id="status">ready</div>
	ajax-savedFileName: ${savedFileName} <span id="jax_upfile"></span>
	
	
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="/resources/plugins/jQuery/jQuery.form.min.js"></script>
<script>
console.debug("0000");
window.setUploadedFile = (filename) => {
	document.getElementById('upfile').innerHTML = filename;
	document.getElementById('form2').reset();
};

const $fileDrop = $('div.fileDrop'),
	  $uploadedList = $('div.uploadedList');
	  
$fileDrop.on('dragover dragenter', (evt) =>{
	evt.preventDefault();
	$fileDrop.css("border", "1px dotted green");
});

$fileDrop.on('drop', (evt) =>{
	evt.preventDefault();
	let files = evt.originalEvent.dataTransfer.files;
	$fileDrop.css("drop>>", files);
	$fileDrop.html(files[0].name);
	$("#ajax-file").prop("files", evt.originalEvent.dataTransfer.files);
	$('#form3').submit();
});
$fileDrop.on('dragleave', (evt) =>{
	evt.preventDefault();
	$fileDrop.css("border", "none");
});

const $percent = $('#percent'),
	  $status = $('#status');
$('#form3').ajaxForm({
    beforeSend: function() {
        $status.empty();
        $percent.html('0%');
    },
    uploadProgress: function(event, position, total, percentComplete) {
    	$status.html("uploading....");
    	$percent.html(percentComplete + '%');
    },
    complete: function(xhr) {
        $status.html(xhr.responseText);
    }
});


</script>

<c:if test="${ type eq 'ifr' }">
<script>
	console.debug("-----------------ifr script!!");
	parent.setUploadedFile('${ savedFileName }');
</script>
</c:if>
</body>
</html>