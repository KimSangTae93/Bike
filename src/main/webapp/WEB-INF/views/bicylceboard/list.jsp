<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html lang="en">

<%@include file="../include/header.jsp"%>



<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Giant</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					게시글
					<button id='regBtn' type="button" class="btn btn-xs pull-right">게시글 등록</button>
				</div>

				<!-- /.panel-heading -->
				<div class="panel-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>#번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								
							</tr>
						</thead>

						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								<td><a class='moveOriginallyList' href='<c:out value="${board.bno}"/>'>
										<c:out value="${board.title}" /><b>[<c:out value="${board.replyCnt}"/>]</b>
								</a></td>

								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate}" /></td>
							</tr>
						</c:forEach>
					</table>
					<div class='row'>
					<div class="col-lg-12">

<%-- 						<form id='searchForm' action="/bicylceboard/list" method='get'>
							<select name='type'>
								<option value="">--</option>
								<option value="T">제목</option>
								<option value="C">내용</option>
								<option value="W">작성자</option>
								<option value="TC">제목or 내용</option>
								<option value="TW">제목or 작성자</option>
								<option value="TWC">제목	or 내용 or 작성자</option>
							</select> <input type='text' name='keyword'/> 
							<input type='hidden' name='pageNum'value='<c:out value="${bicylcePageMarker.cri.pageNum}"/>' /> 
							<input type='hidden' name='amount' value='<c:out value="${bicylcePageMarker.cri.amount}"/>' />
							<button class='btn btn-default'>Search</button>
						</form>
					</div> --%>
					<form id='searchForm' action="/bicylceboard/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value="${bicylcePageMarker.cri.type == null?'selected':''}"/>>--</option>
								<option value="T"
									<c:out value="${bicylcePageMarker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
									<c:out value="${bicylcePageMarker.cri.type eq 'C'?'selected':''}"/>>내용</option>
								<option value="W"
									<c:out value="${bicylcePageMarker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
								<option value="TC"
									<c:out value="${bicylcePageMarker.cri.type eq 'TC'?'selected':''}"/>>제목
									or 내용</option>
								<option value="TW"
									<c:out value="${bicylcePageMarker.cri.type eq 'TW'?'selected':''}"/>>제목
									or 작성자</option>
								<option value="TWC"
									<c:out value="${bicylcePageMarker.cri.type eq 'TWC'?'selected':''}"/>>제목
									or 내용 or 작성자</option>
							</select> <input type='text' name='keyword'
								value='<c:out value="${bicylcePageMarker.cri.keyword}"/>' /> <input
								type='hidden' name='pageNum'
								value='<c:out value="${bicylcePageMarker.cri.pageNum}"/>' /> <input
								type='hidden' name='amount'
								value='<c:out value="${bicylcePageMarker.cri.amount}"/>' />
							<button class='btn btn-default'>Search</button>
						</form>
					</div>
				</div>
					
				</div>
					<div class='pull-right'>
						<ul class="pagination">
						
						<c:if test="${bicylcePageMarker.prev}">
							<li class="paginate_button previous"><a
								href="${bicylcePageMarker.startPage -1} ">이전페이지</a></li>
						</c:if>

						<c:forEach var="num" begin="${bicylcePageMarker.startPage}"
							end="${bicylcePageMarker.endPage}">
							<li class="paginate_button ${bicylcePageMarker.cri.pageNum == num ? "active":"" } "><a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${bicylcePageMarker.next}">
							<li class="paginate_button next"><a
								href="${bicylcePageMarker.endPage +1 }">다음페이지</a></li>
						</c:if>

					</ul>
					
				</div>
				<!--  end Pagination -->
				<!-- 페이지 이동 -->
				<form id="actionForm" action="/bicylceboard/list" method='get'>
						<input type='hidden' name='pageNum' value="${bicylcePageMarker.cri.pageNum}">
						<input type='hidden' name='amount' value="${bicylcePageMarker.cri.amount}">
						<input type='hidden' name='type' value="${bicylcePageMarker.cri.type}">
						<input type='hidden' name='keyword' value="${bicylcePageMarker.cri.keyword}">
					</form>	
					<!-- Modal 추가 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">알림창</h4>
								</div>
								<div class="modal-body">처리가 완료되었습니다.</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">닫기</button>
									<!-- <button type="button" class="btn btn-primary">수정하기</button> -->
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script type="text/javascript">
	$(document).ready(function() {
				//모달창 띄우기 시작
				var result = '<c:out value="${result}"/>';

				checkModal(result);
				
				history.replaceState({}, null, null);

				function checkModal(result) {

					if (result === '' || history.state) {
						return;
					}
					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글" + parseInt(result) + "번이 등록 되었습니다.");
					}
					$("#myModal").modal("show");
					//모달창 띄우기 끝
				}//게시글 등록 버튼
				$("#regBtn").on("click",function(){
					self.location="/bicylceboard/register"
				//게시글 등록 버튼 끝
				});
				//페이지 클릭시 이동
				var actionForm= $("#actionForm");
				
				$(".paginate_button a" ).on("click",function(e){
					e.preventDefault();
					
					console.log('click');
					
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					actionForm.submit();
				});//페이지 클릭시 이동 끝
				//조회 클릭시 원래 게시물의 pageNum과 amount 파라미터 추가
				$(".moveOriginallyList").on("click",function(e){
				
					e.preventDefault();
					
					actionForm.append("<input type='hidden' name='bno' value='"+
							$(this).attr("href")+"'>");
					actionForm.attr("action","/bicylceboard/get");
					actionForm.submit();
				});
				//검색버튼 이벤트 처리[검색은 1페이지,화면에 검색 조건 키워드 보이게 처리]
				
			var searchForm = $("#searchForm");
				
				$("#searchForm button").on("click",function(e){
					
					if(!searchForm.find("option:selected").val()){
						alert("검색 종류를 선택 하십시오");
						return false;
					}
					 //화면에서 키워드가 없다면 검색을 하지 않도록 제한한다.
					if(!searchForm.find("input[name='keyword']").val()){
						alert("키워드를 입력 하십시오");
						return false;
						
					}
					//브라우저에서 검색 버튼을 클릭하면 태그의 전송은 막고,페이지 번호는 1이 되도록 처리한다.
					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();
					
					searchForm.submit();
				});
			});
</script>
<%@include file="../include/footer.jsp"%>