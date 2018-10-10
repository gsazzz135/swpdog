<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>

<%@include file="../header.jsp" %>
  
<script>


$('.btX').click(function() { 
	$('.alert').slideUp('slow');
}
</script>


<c:set var="result" value="${msg}" />

<c:if test="${result eq 'success'}">
   <div class="alert alert-info" role="alert">등록되었습니다
   <button type="button" class="close" data-dismiss="alert" aria-label="close"><span class="btX">x</span></button>
   </div>
</c:if>
<c:if test="${result eq 'remove-ok'}">
    <div class="alert alert-warning" role="alert">삭제되었습니다
    <button type="button" class="close" data-dismiss="alert" aria-label="close"><span class="btX">x</span></button>
    </div>
</c:if>


<section class="content">
	<div class="row">
		<div class="col-12 text-right">
			<a href="/board/register" class="btn btn-primary">글쓰기</a>
		</div>
	</div>
	<div class="row">
		<div class="col-12 text-right">
			<table class="table table-bordered">
				<tr>
					<th style="width : 10px">#</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.bno}</td>
					<td><a href="/board/read?bno=${board.bno}">${board.title}</a></td>
					<td><strong>${board.writer}</strong></td>
					<td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${board.regdate}"/></td>
					<td><span class="badge bg-red">${board.viewcnt}</span></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>		
	
	
	
</section>
<%@include file="../footer.jsp" %>

