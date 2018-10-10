<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.jsp" %>

	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="text">제목</label>
				<input type="text" name='title' class="form-control" placeholder="Enter Title" id="text"/>
			</div>
			
			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" rows="3" class="form-control" placeholder="Enter ..." id="content"></textarea>
			</div>
			
			<div class="writer">
				<label for="writer">작성자</label>
				<textarea name="writer" class="form-control" placeholder="Enter Writer" id="writer"></textarea>
			</div>			
		</div>
		
		<div class="box-footer">
				<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form>
	
<%@include file="../footer.jsp" %>

