<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>


<%@include file="../header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-8 bordered">
			<div class="form-inline">
				<select id="searchType">
					<option value="" selected>검색조건</option>
					<option value="t">제목</option>
					<option value="c">내용</option>
					<option value="w">작성자</option>
					<option value="tc">제목+내용</option>
					<option value="a">전체조건</option>
				</select>
				<input type="text" id="keyword" name="keyword" class="form-control" value="${pageMaker.cri.keyword}" placeholder="검색어를 입력하세요" />
				<button id="searchBtn" class="btn btn-primary">Search</button>
			</div>
		</div>
		
		<div class="col-md-4 text-right">
			<select id="perPageSel">
				<option value="10" selected>10</option>
				<option value="20">20</option>
				<option value="30">30</option>
			</select>
			<a href="/board/register" class="btn btn-primary">글쓰기</a>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table table-bordered">
				<tr>
					<th style="width: 10px">#</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${list}" var="board">
					<tr>
						<td>${board.bno}</td>
						<td>
							<a href='/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${board.bno}'>${board.title}</a>
							<small class="text-small">[${board.replycnt}]</small>
						</td>
						<td>${board.content}</td>
						<td><strong>${board.writer}</strong></td>
						<td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss"
								value="${ board.regdate }" /></td>
						<td><span class="badge bg-red">${ board.viewcnt }</span></td>
					</tr>
				</c:forEach>
			</table>

			<div class="text-center">
				<ul class="pagination">										
						<li class="pageList" id="page-prev">
							<a href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}" onclickX="gogo(${pageMaker.startPage - 1})" aria-label="Prev"><span aria-hidden="true">&laquo;</span></a>
						</li>
					

					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="idx">
						<li class="pageList ${pageMaker.cri.page == idx ? 'active':''}" id="page${idx}" >					
							<a href="listPage${pageMaker.makeQuery(idx) }"  onclickX="gogo(${idx})">${idx}</a>
						</li>
					</c:forEach>

						<li class="pageList" id="page-next">
							<a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1) }" onclickX="gogo(${pageMaker.endPage + 1})" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
						</li>
				</ul>
			</div>
		</div>
	</div>
</section>

<script>
	$(function() {
		var perPageNum = "${pageMaker.cri.perPageNum}";
		console.log(">>perPageNum=", perPageNum);
		
		var $perPageSel = $("#perPageSel");
		$perPageSel.val(perPageNum).prop("selected", true);
		
		var thisPage = '${pageMaker.cri.page}';
		$perPageSel.on('change', function(){
			console.log(":::::", $perPageSel.val())
			gogo(thisPage, $perPageSel.val())
		});
	})
	
	function gogo(page, perPageNum){
		perPageNum = perPageNum || $("#perPageSel").val();
		window.location.href = "listPage?page=" + page + "&perPageNum=" + perPageNum;
	}
	
	$(function() {
		//setSelect();
		
		var canPrev = "${pageMaker.prev}";
		console.log("canPrev>>"+canPrev);
		if (canPrev !== 'true') {
			$('#page-prev').addClass('disabled');
		}
		
		var canNext = "${pageMaker.next}";
		if (canNext !== 'true'){
			$('#page-next').addClass('disabled');
		}	
		
		var thisPage = "${pageMaker.cri.page}";			// 현재페이지
		$('#page' + thisPage).addClass('active');
		
		$('#searchBtn').on('click', function(event){
			event.preventDefault();
			
			var $keyword = $('#keyword');
			var $searchType = $('#searchType');
			
			var searchTypeVal = $searchType.val();
			var keywordStr = $keyword.val();
			
			if (!searchTypeVal){
				alert('검색조건을 선택하세요!');
				$searchType.focus();
				return;
			} else if (!keywordStr){
				alert("검색어를 입력하세요!");
				$keyword.focus();
				return;
			}
			
			var url = "listPage${pageMaker.makeQuery(1, false)}" + "&searchType=" + searchTypeVal + "&keyword=" + encodeURIComponent(keywordStr); 
			//console.log(">>search.url=", url)
			window.location.href = url
		})
	})
	
</script>

<%@include file="../footer.jsp"%>

