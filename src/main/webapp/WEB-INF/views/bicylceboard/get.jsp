<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>




<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 조회</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">게시글 조회 페이지</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<div class="form-group">
					<label>Bno</label> <input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Title</label> <input class="form-control" name='title' value='<c:out value="${board.title }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>

				<div class="form-group">
					<label>Writer</label> <input class="form-control" name='writer'value='<c:out value="${board.writer }"/>' readonly="readonly">
				</div>
				
				<button data-oper='modify' class="btn btn-default">수정하기</button>
				<button data-oper='list' class="btn btn-info">게시글 리스트</button>
				
				<div class="row">
			<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>댓글
				<button id='addReplyBtn' class ='btn btn-primary btn-xs pull-rigjt'>댓글 등록</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
			<!--reply 시작  -->
			<ul class ="chat">
			<!--수정이나 삭제시에는 댓글번호(rno)가 필요하므로 data-rno사용  -->
				<li class="left clearfix" data-rno='12'>
					<div>
					<div class="header">
						<strong class="primary-font">user00</strong>
						<small class="pull-right text-muted">2023-04-28</small>
					</div>
					<p>댓글 시작</p>
					</div>
				</li>
			<!--reply 끝  -->
			</ul>
			
			<div class ="panel-footer"> 
			
			</div>
		</div>
	</div>
</div>
</div>			
				<form id='operForm' action="/bicylceboard/modify" method="get">
					<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno}"></c:out>'>
					<input type="hidden" name='pageNum' value='<c:out value="${cri.pageNum}"></c:out>'>
					<input type="hidden" name='amount' value='<c:out value="${cri.amount}"></c:out>'>
					<input type="hidden" name='type' value='<c:out value="${cri.type}"></c:out>'>
					<input type="hidden" name='keyword' value='<c:out value="${cri.keyword}"></c:out>'>

				</form>
				
				 <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                         	<label>댓글 내용</label>
                                         	<input class="form-control" name='reply' value='New Reply'>
                                         	</div>
                                         	
                                         	<div class="form-group">
                                         	<label>작성자</label>
                                         	<input class="form-control" name='replyer' value='replyer'>
                                         	</div>
                                         	
                                         	<div class="form-group">
                                         	<label>날짜</label>
                                         	<input class="form-control" name='replyDate' value=''>
                                         	</div>
                                         </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button id="modalModBtn" type= "button" class="btn btn-warnig" >수정</button>
                                            <button id="modalRemoveBtn" type= "button" class="btn btn-danger" >삭제</button>
                                            <button id="modalRegisterBtn" type= "button" class="btn btn-primary" >등록</button>
                                            <button id="modalCloseBtn" type= "button" class="btn btn-dismiss" >닫기</button>
                                            
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
<script type="text/javascript" src="/resources/js/bIcylceReply.js"></script>

<script>

$(document).ready(function () {
  
  var bnoValue = '<c:out value="${board.bno}"/>';
  var replyUL = $(".chat");
  
    showList(1);
    
function showList(page){
	//추가
	  console.log("show list " + page);
    
	  BicylceReplyService.getList({bno:bnoValue,page: page|| 1 }, function(replyCnt,list) {
      
      console.log("replyCnt :" + replyCnt);
      console.log("list :" + list);
      console.log(list);
     //2.page번호가 -1로 전달되면  마지막 페이지를 찾아서 다시 호출하게된다.
      if(page == -1){
    	  pageNum = Math.ceil(replyCnt/10.0);
    	  //1.shoList함수는 파라미터로 전달되는 page변수를 이용해서 원하는 댓글페이지를 가져온다.
    	 
    	  showList(pageNum);
    	  return;
      }
    //추가 끝  
      
     var str="";
     
     if(list == null || list.length == 0){
    	 
    	 replyUL.html("");
       return;
     }
     
     for (var i = 0, len = list.length || 0; i < len; i++) {
       str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
       str +="  <div><div class='header'><strong class='primary-font'>["
    	   +list[i].rno+"] "+list[i].replyer+"</strong>"; 
       str +="    <small class='pull-right text-muted'>"+BicylceReplyService.displayTime(list[i].replyDate)+"</small></div>";
       str +="    <p>"+list[i].reply+"</p></div></li>";
     }
     
     replyUL.html(str);
     
     showReplyPage(replyCnt);
   });//end function
     
 }//end showList
var pageNum = 1;
var replyPageFooter = $(".panel-footer");

function showReplyPage(replyCnt){
  
  var endNum = Math.ceil(pageNum / 10.0) * 10;  
  var startNum = endNum - 9; 
  
  var prev = startNum != 1;
  var next = false;
  
  if(endNum * 10 >= replyCnt){
    endNum = Math.ceil(replyCnt/10.0);
  }
  
  if(endNum * 10 < replyCnt){
    next = true;
  }
  
  var str = "<ul class='pagination pull-right'>";
  
  if(prev){
    str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
  }
  
  for(var i = startNum ; i <= endNum; i++){
    
    var active = pageNum == i? "active":"";
    
    str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
  }
  
  if(next){
    str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
  }
  
  str += "</ul></div>";
  
  console.log(str);
  
  replyPageFooter.html(str);
}//end showReplyPage
//페이지 번호 클릭시 해로운 댓글을 가져오도록 하는 부분
replyPageFooter.on("click","li a", function(e){
    e.preventDefault();
    console.log("page click");
    
    var targetPageNum = $(this).attr("href");
    
    console.log("targetPageNum: " + targetPageNum);
    
    pageNum = targetPageNum;
    
    showList(pageNum);
  });     

var modal = $(".modal");
var modalInputReply = modal.find("input[name='reply']");
var modalInputReplyer = modal.find("input[name='replyer']");
var modalInputReplyDate = modal.find("input[name='replyDate']");

var modalModBtn = $("#modalModBtn");
var modalRemoveBtn = $("#modalRemoveBtn");
var modalRegisterBtn = $("#modalRegisterBtn");

$("#modalCloseBtn").on("click", function(e){
	
	modal.modal('hide');
});

$("#addReplyBtn").on("click", function(e){
  
  modal.find("input").val("");
  modalInputReplyDate.closest("div").hide();
  modal.find("button[id !='modalCloseBtn']").hide();
  
  modalRegisterBtn.show();
  
  $(".modal").modal("show");
  
});
modalRegisterBtn.on("click",function(e){
    
    var reply = {
          reply: modalInputReply.val(),
          replyer:modalInputReplyer.val(),
          bno:bnoValue
        };
    BicylceReplyService.add(reply, function(result){
      
      alert(result);
      
      modal.find("input").val("");
      modal.modal("hide");
      //3.사용자가 새로운 댓글을 추가하면 showList(-1);을호출하여 우선 전체댓글의 숫자를 파악하고
	  //4.이후 다시 마지막 페이지를 호출해서 이동시키는 방식으로 동작시킨다.
     // showList(1);
     showList(-1);
      
    });
});
//댓글 조회 이벤트 처리
$(".chat").on("click","li",function(e){
		
	var rno = $(this).data("rno");
	
	BicylceReplyService.get(rno,function(reply){
		modalInputReply.val(reply.reply);
        modalInputReplyer.val(reply.replyer);
        modalInputReplyDate.val(BicylceReplyService.displayTime( reply.replyDate))
        .attr("readonly","readonly");
        modal.data("rno", reply.rno);
        
        modal.find("button[id !='modalCloseBtn']").hide();
        modalModBtn.show();
        modalRemoveBtn.show();
        
        $(".modal").modal("show");
            
      });
    });
    //댓글 수정
modalModBtn.on("click", function(e){
    
    var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
    
    BicylceReplyService.update(reply, function(result){
          
      alert(result);
      modal.modal("hide");
      showList(1);
      
    });
    
  });
//댓글 삭제
  modalRemoveBtn.on("click", function (e){
  	  
	  var rno = modal.data("rno");
	  
	  BicylceReplyService.remove(rno, function(result){
	        
	      alert(result);
	      modal.modal("hide");
	      showList(1);
	      
	  });
	  
	});
});
</script>
		<script type="text/javascript">
		
		$(document).ready(function() { 
			  
			  var operForm = $("#operForm"); 
			  
			  $("button[data-oper='modify']").on("click", function(e){
			    
			    operForm.attr("action","/bicylceboard/modify").submit();
			    
			  });
			  
			    
			  $("button[data-oper='list']").on("click", function(e){
			    
			    operForm.find("#bno").remove();
			    operForm.attr("action","/bicylceboard/list")
			    operForm.submit();
			    
			  });  
			});
		
		</script>
		
		<!-- <script type="text/javascript" src="/resources/js/bIcylceReply.js"></script>
		<script type="text/javascript">
		
		console.log("JS TEST");
		
		var bnoValue= '<c:out value ="${board.bno}"/>';
		
		
		//BicylceReplyService 등록
		BicylceReplyService.add(
			{reply :"JS TEST", replyer:"TESTER", bno:bnoValue}
			,
			function(result){
				alert("RESULT : "+ result )
			}
		);
		//BicylceReplyService 조회
		BicylceReplyService.getList({bno:bnoValue, page:1},function(list){
			
			for(var i = 0, len = list.length||0; i<len; i++){
				console.log(list[i]);
			}
		});
		//12번 댓글 삭제
		BicylceReplyService.remove(12, function(count) {

			   console.log(count);

			   if (count === "success") {
			     alert("REMOVED");
			   }
			 }, function(err) {
			   alert('ERROR');
			 });
		//1번 댓글 수정
		BicylceReplyService.update({
			
			rno : 1,
			bno : bnoValue,
			reply : "Modify Reply"}, 
				function(result) {
				alert("수정완료");
				
		});
		//단순히 댓글의 번호만 전달
		BicylceReplyService.get(20,function(data){
			console.log(data);
		}); -->
		
		<!-- </script> -->		
				<%@include file="../include/footer.jsp"%>