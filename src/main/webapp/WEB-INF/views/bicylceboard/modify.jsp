<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>

<script type="text/javascript">
$(document).ready(function() {


	  var formObj = $("form");

	  $('button').on("click", function(e){
	    
	    e.preventDefault(); 
	    
	    var operation = $(this).data("oper");
	    
	    console.log(operation);
	    
	    if(operation === 'remove'){
	      formObj.attr("action", "/bicylceboard/remove");
	      
	    }else if(operation === 'list'){
	    	//move List
	    	formObj.attr("action","/bicylceboard/list").attr("method","get")
	    	//필요한 부부만 잠시 복사(clone)해서 보관하고,<form>태그의 모든내용을 지우고(empty)
	    	//다시 필요한 태그만 추가한뒤 list로 돌아가는형태
	    	var pageNumTag= $("input[name='pageNum']").clone();
	    	var amountTag= $("input[name='amount']").clone();
	    	var keywordTag= $("input[name='keyword']").clone();
	    	var typeTag= $("input[name='type']").clone();
	    	
	    	formObj.empty();
	    	
	    	formObj.append(pageNumTag);
	    	formObj.append(amountTag);
	    	formObj.append(keywordTag);
	    	formObj.append(typeTag);
	    	
	    	
	    }
	    formObj.submit();
	    });
	  });
</script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">게시글 수정 페이지</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form role="form" action="/bicylceboard/modify" method="post">
								<!--pageNum,amount추가  -->
				<input type="hidden" name='pageNum' value='<c:out value="${cri.pageNum}"></c:out>'>
				<input type="hidden" name='amount' value='<c:out value="${cri.amount}"></c:out>'>
								 <!--type,keyword추가  -->
				<input type="hidden" name='type' value='<c:out value="${cri.type}"></c:out>'>
				<input type="hidden" name='keyword' value='<c:out value="${cri.keyword}"></c:out>'>
					
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno'
							value='<c:out value="${board.bno }"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='<c:out value="${board.title }"/>'>
					</div>

					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'"><c:out
								value="${board.content}" /></textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<c:out value="${board.writer }"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>RegDate</label> <input class="form-control" name='regDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'
							readonly="readonly">
					</div>

				<%-- 	<div class="form-group">
						<label>Update Date</label> <input class="form-control"name='updateDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'
							readonly="readonly">
					</div> --%>

					<button type="submit" data-oper='modify' class="btn btn-default">수정하기</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">삭제하기</button>
					<button type="submit" data-oper='list' class="btn btn-info">게시글 리스트</button>

				</form>
				<%@include file="../include/footer.jsp"%>