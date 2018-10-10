<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.jsp"%>
<script>
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".btn-default").on("click", function() {
			self.location = "/board/listPage";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});
	});
</script>
<form role="form" action="/board/update${cri.makeQuery() }" method="post">
	<input type="hidden" name="bno" value="${ board.bno }" />
	<div class="box-body">
		<div class="form-group">
			<label for="text">제목</label> <input type="text" name='title'
				value="${ board.title }" class="form-control"
				placeholder="Enter Title" id="text" />
		</div>

		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" rows="3" class="form-control"
				placeholder="Enter ..." id="content">${ board.content }</textarea>
		</div>


	</div>

	<div class="box-footer">
		<a href="/board/read${cri.makeQuery()}&bno=${ board.bno }" class="btn btn-default">취소</a>
		<button type="submit" class="btn btn-primary">확인</button>
	</div>
</form>

<%@include file="../footer.jsp"%>

