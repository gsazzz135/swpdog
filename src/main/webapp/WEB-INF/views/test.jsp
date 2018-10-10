<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
<link rel="stylesheet" href="/resources/test.css" />
</head>
<body>
	<h2 id = "h2-title" class="point">Ajax Test Page</h2>
	
	
	<ul id="replies">	
	</ul>
	
	<ul class="pagenation">
	</ul>
	
	<div>
		<div>
			작성자 : <input type="text" name="replyer" id="newReplyWriter" />
		</div>
		<div>
			내용 : <textarea name="replytext" id="newReplyText" cols="30" rows="3"></textarea>
		</div>
		<button id="btnReplyAdd" class="btn btn-primary">등록</button>
		<button id="btnshowData" class="btn btn-primary">정보보기</button>
	
	<div id = 'modDiv'>
		<div class='modal-title'>
			<span id = "modRno"></span>
			<input type='text' id='replytext'>
		</div>
		<div class='mt text-right'>
			<button type="button" onclick="editReply()" id='btnModReply'>수정</button>
			<button type="button" onclick="removeReply()" id='btnDelReply'>삭제</button>
			<button type="button" onclick="closeReply()" id='btnCloseReply'>닫기</button>
		</div>
	</div>
	</div>
	
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="/resources/handlebars-v4.0.12.js"></script>
<script src="/resources/test.js"></script>
<script>
	$(document).ready(function(){
		
		$('#btnReplyAdd').on('click',regist);
	    
	    $('#btnshowData').on('click', showJson);
	    
	    
	    $('#defaultValueSetter').on('click', () => {
       		$('#newReplyWriter').val("홍길동");
        	$('#newReplyText').val("댓글내용입니다!!");        
        	$('#btnReplyAdd').click();        
    	})
    	
		movCenterModDiv();
	})
</script>
</body>
</html>
