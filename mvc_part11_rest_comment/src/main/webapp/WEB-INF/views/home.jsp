<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	#comments li{
		height : 100px;
		list-style : none;
		padding : 10px;
		border : 1px solid #525252;
		margin : 5px 0;
	}
	
	#modDiv{
		border: 1px solid #727272;
		padding : 10px;
		display : none;
	}
	
	#pagination li{
		list-style : none;
		float : left;
		padding : 3px;
		margin : 3px;
		border : 1px solid skyblue;
	}
	#pagination li a{
		text-decoration : none; 
		color : black;
	}
	#pagination li a.active{
		color : red;
	}
</style>
</head>
<body>
	<div id="modDiv">
		<div id="modCno"></div>
		<div>
			<input type="text" id="modAuth" />
		</div>
		<div>
			<input type="text" id="modText" />
		</div>
		<div>
			<button id="modBtn">수정</button>
			<button id="delBtn">삭제</button>
			<button id="closeBtn">취소</button>
		</div>
	</div>
	
	<h2>AJAX - Rest Test</h2>
	<div>
		<div>
			comment auth : <input type="text" id="cAuth"/>
		</div>
		<div>
			comment text : <input type="text" id="cText"/>
		</div>
		<button id="addBtn">Add Comment</button>
	</div>
	
	<div>
		<!-- 댓글 리스트 -->
		<ul id="comments">
			
		</ul>
		<!-- 페이징 블럭 정보 -->
		<ul id = "pagination">
			
		</ul>
		<br/><br/><br/>
	</div>	
	
	<script>
		var bno = 2;
		
		var page = 1;
		
		listPage(page);
		// 페이징 처리
		function listPage(page){
			$("#modDiv").css("display","none");	// 일단 숨겨놓고
			$("body").prepend($("#modDiv"));	// 수정 | 삭제를 한 후에도 다시 할 수 있게 해주라
			
			var url = "comments/"+bno+"/"+page;	// 페이징 처리할때 필요한 page값이랑 게시글 번호 넘겨줌
			$.getJSON(url,function(data){	//data 안에는 pm과 게시글 list가 같이 있어야됨 -> Map
				console.log(data);
				console.log(data.list);	// 댓글 목록
				console.log(data.pm);
				
				printList(data.list);
				printPage(data.pm);	
			});
		}
		// 페이징 블럭
		function printPage(pm){
			var str = "";
			
			if(pm.prev){
				// 이전 페이지
				str += "<li><a href='"+(pm.startPage-1)+"'> << </a></li>";
			}
			for(var i = pm.startPage; i<=pm.endPage; i++){
				// 현재페이지 == i -> 현재 페이지 보여주셈
				if(pm.cri.page == i){	
					str += "<li><a href='"+i+"'class='active'>"+i+"</a></li>";
				}else{
					str += "<li><a href='"+i+"'>"+i+"</a></li>";
				}
			}
			if(pm.naxt){
				// 다음 페이지
				str += "<li><a href='"+(pm.endPage +1)+"'> >> </a></li>";
			}
			$("#pagination").html(str);
		}
		
		$("#pagination").on("click","li a",function(e){ // e는 발생하는 이벤트(즉, 클릭이벤트)를 가져오는거임
			e.preventDefault();				// 기본 이벤트(a태그가 가지고 있는 하이퍼링크) 무시
			var commentPage = $(this).attr("href"); // this == a태그 | (a).attr(n) : a요소의 n속성 값을 가져옴 
			page = commentPage;						// | (a).attr(x,y) : a요소의 x를 속성을 추가하고, 속성 값은 y로 하겠다.
			listPage(page);				// -> a태그의 href값(page 번호)를 가져와서 listPage에 보여주겠다. 
		});
		
//		getCommentList(); // 여기서 호출해줘야 해당 댓글 리스트가 바로 보여서? // 페이징 처리한다고 이거 주석처리함
		// 전체 게시글 목록 출력
		function getCommentList(){
			// 새로 목록 리스트 불러오기전에 다시 body로 돌리는 작업
			$("body").prepend($("#modDiv"));	// prepend() : 맨 앞으로 나두는 친구
			// -> 수정버튼 누를 때 after를 썼는데 이친구가 body안에 있는 #modDiv를 #comments(ul) 안에 이동시켜버린거임
			// => 그래서 한번 수정 누르고나면 #modDiv는 body에 없으니 다시 body안으로 넣으려고 쓰는 친구임
			
			var url = "comments/all/" + bno;	// comments에 달린 댓글의 모든 정보 가져 올라고 만듬
			$.getJSON(url,function(data){	// ajax로 넘겨 받는데 get방식으로 전달하고, JSON타입(방식)으로 받을꺼다.
				console.log(data);			
				printList(data);	// printList는 밑에 선언해놨는데 여기서 되는이유
			});						// -> printList()는 "함수선언문"이라서 hoisting이 발생해서 
		}							// getCommentList() 내부에서도 써지는거임(var로 선언한 변수도 hoisting 일어남)
		
		// 댓글 목록 출력
		function printList(list){
			// list = [commentDTO, commentDTO ...]
			var str = "";
			
			$(list).each(function(){	// $(A).each() : A 배열을 반복하겠다. (jquery 문법)
				// commentDTO == this
				let cno = this.cno;
				let cAuth = this.commentAuth;
				let cText = this.commentText;
				console.log(cno+" : "+cAuth+" : "+cText);
				
				str +="<li>";
				str += cno+"-"+cAuth+"<br/><hr/>"+cText;
				str += "<br/><br/>";
				str += "<button data-cno='"+cno+"' data-text='"+cText+"' data-auth='"+cAuth+"' >수정</button>";
				str +="</li>";
			});
			$("#comments").html(str);
		}
		
		// $("#comments li button").on("click",function(){}); 
		// 이렇게 하면 댓글리스트 #comments(ul)에 아무 값도 없기 때문에
		// 버튼을 눌려도 클릭이벤트가 발생하지 않는다.
		// -> "#comments"만 하고, on("click","속성 값",function(){});을 해주는거다.
		// => 이렇게 되면 해당 페이지를 다 읽고 나서 그 후 "#comments" > "li button"을 찾기때문에 클릭이벤트 발생함
		$("#comments").on("click","li button",function(){
			console.log("click");
			// this == btn
			var cno = $(this).attr("data-cno");
			var auth = $(this).attr("data-auth");
			var text = $(this).attr("data-text");
			console.log(cno+" : "+auth+" : "+text);
			
			$("#modCno").text(cno);
			$("#modAuth").val(auth);
			$("#modText").val(text);
			
			$(this).parent().after($("#modDiv"));	// this : btn | parent : li | after : li 뒤에
			
			$("#modDiv").toggle("slow"); // toggle : 안보이면 보이게, 보이면 안보이게 해주는 애니메이션 효과
		});
		
		// 수정 버튼 누르고 난 뒤에 취소 버튼 눌렀을 때
		$("#closeBtn").click(function(){
			$("#modDiv").slideUp("slow");
		});
		
		// 수정 버튼 누르고 난 뒤에 해당 칸에서 수정버튼을 눌렀을 때.
		$("#modBtn").click(function(){
			var cno = $("#modCno").text();
			var auth = $("#modAuth").val();
			var text = $("#modText").val();
			
			$.ajax({
				type : "PATCH",
				url : "comments/"+cno,
				headers : {
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					commentAuth : auth,
					commentText : text
				}),
				dataType : "text",
				success : function(data){
					alert(data);
					$("#modDiv").slideUp("slow");
					// getCommentList();
					listPage(page);
				}
			});
		});
		
		$("#addBtn").click(function(){
			var auth = $("#cAuth").val();
			var text = $("#cText").val();
			
			$.ajax({
				type : "POST",
				url : "comments",
				dataType : "text",
				data : {
					bno : bno,
					commentAuth : auth,
					commentText : text
				},
				success : function(result){
					alert(result);
					// getCommentList();
					page = 1;
					listPage(page);
				}
			}); // ajax end
		}); // click end
		
		// 댓글 삭제 요청
		$("#delBtn").click(function(){
			var cno = $("#modCno").text();
			$.ajax({
				type : "DELETE",
				url : "comments/"+cno,
				dataType : "text",
				success : function(data){
					alert(data);
					$("#modDiv").slideUp("slow");
					// getCommentList();
					listPage(page);
				}
			});
		});
	</script>
</body>
</html>