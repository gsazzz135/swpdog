<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "isTest" scope = "page" value = "${false}" />	<!-- QQQ -->

<%@include file="../header.jsp" %>

<c:if test="${true eq isTest}">
<%@include file="../qunit.jsp" %>
</c:if>

<section class="content">
<div class="box-body">
	<div class="form-group">
		<label for="title">제목</label>
		<span>${board.title}</span>
	</div>
	<div class="form-group">
		<label for="writer">내용</label>
		<span>${board.content}</span>
	</div>
	<div class="form-group">
		<label for="writer">작성자</label>
		<span>${board.writer}</span>
	</div>
	<div class="form-group">
		<label for="writer">조회수</label>
		<span>${board.viewcnt}</span>
	</div>
</div>

	<script id="replies" type="text/x-handlebars-template" class="well">
		<ul class="list-group">
			{{#each list}}		
		  		<a href="#" class="list-group-item" onclick="editReply({{rno}}, '{{replyer}}', '{{replytext}}')">
		  			{{replytext}}
		  			<small class="text-muted"></small>
		  			<small class="text-muted pull-right">
						<i class="fa fa-user mr5"></i><span class="mr">{{replyer}}</span>
						{{fromNow regdate}}
					</small>
		 		 </a>
			{{/each}}
		</ul>
		
		<nav aria-label="Page navigation" class="text-center">
			<ul class="pagination">
				{{#if pageData.prevPage}}
					<li>
						<a href="#" onclick="listPage({{pageData.prevPage}})" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				{{/if}}

				{{#each pageData.pages as |page|}}
					<li class="{{#if (eq ../currentPage page)}}active{{/if}}">
					<a href="javascript:" onclick="listPage({{page}})">{{page}}</a>
					</li>
				{{/each}}

				{{#if pageData.nextPage}}
					<li><a href="#" onclick="listPage({{pageData.nextPage}})" aria-label="Next"><span aria-hidden="true">&raquo;</span>
				{{/if}}
				</a></li>
			</ul>
		</nav>
		
		<button class="btn btn-success" onclick="editReply()">댓글쓰기</button>
	</script>

<div class="box-footer text-center">
	<button id="btn-remove-read" class="btn btn-danger">삭제</button>
	<a href="/board/update${cri.makeQuery() }&bno=${ board.bno }" class="btn btn-primary">수정</a>
	<a href="/board/listPage${cri.makeQuery()}" class="btn btn-default">목록</a>
</div>

</section>
  
<script id="myModal" type="text/x-handlebars-template" class="modal fade">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">
			{{#if gIsEdit}}
				댓글 수정
			{{else}}
				댓글 등록
			{{/if}}
		</h4>
      </div>
      <div class="modal-body">
		<div>
			작성자 <input type="text" name="replyer" id="replyer" onkeyup="checkEdit()" {{#if gIsEdit}}readonly{{/if}} class="form-control" value="{{replyer}}"/>
		</div>
		<div>
			내용 <textarea name="replytext" id="replytext" onkeyup="checkEdit()" cols="30" rows="3" class="form-control">{{replytext}}</textarea>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" onclick="save()" id='btnModReply'>	
			{{#if gIsEdit}}
				수정
			{{else}}
				등록
			{{/if}}
		</button>

		{{#if gIsEdit}}
			<button type="button" onclick="removeReply()" id='btnDelReply'>삭제</button>
		{{/if}}
		<button type="button" onclick="closeReply()" id='btnCloseReply'>닫기</button>
      </div>
    </div>
  </div>
</script>



<!-- 스크립트 링크 걸어줄때 순서를 주의 해야 한다 -->
<script src="../resources/handlebars-v4.0.12.js"></script>
<script src="../resources/moment.min.js"></script>
<script src="../resources/test/hbsV1.js"></script>
<script src="../resources/reply.js"></script>

<!-- QQQ: Reply Unit Test -->
<c:if test="${true eq isTest}">
<script src="../resources/test/replytest.js"></script>
</c:if>

<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		$(document).ready(function(){
			$('#btn-remove-read').on('click', function(){
				if(confirm("Are u sure??")){
					self.location.href="/board/remove${cri.makeQuery() }&bno=${board.bno}"
				}
			});
		});
		
		$(".btn-primary").on("click", function(){
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function(){
			formObj.attr("action", "/board/remove");
			formObj.submit();
		});
		
		$(".btn-default").on("click", function(){
			self.location = "/board/listPage";
		});
	
		console.log("bno>>>>", '${board.bno}');
		listPage(1, '${board.bno}');				//DDD
		
	});
</script>





<%@include file="../footer.jsp" %>