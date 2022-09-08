<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/chat.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.member" var="member" />
</sec:authorize>


<style>
	.chatBox{
		height:200px;
		overflow-y:scroll;
		border:1px solid #ccc;
		border-radius:5px;
	}
	
	.chatWrap{
		margin-top:3px;
		margin-bottom:3px;
	}
	
	.chatWrap img{
		width : 30px;
		height : 30px;
		border: 1px solide black;
		border-radius: 15px;
	}
</style>

<!-- sock.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<div class="container">
	<h1>Chat Page -</h1>
	<!-- 누가 어떤 메세지 전달 했는지 u_profile과 함께 출력 -->
	<div class="container chatBox" id="data">
		
	</div>
	<br/>
	<div class="row">
		<div class="col-md-10">
			<input type="text" class="form-control" id="message" />
		</div>
		<div class="col-md-2">
			<input type="button" id="sendBtn" class="form-control btn btn-primary" value="SEND" />
		</div>
	</div>
</div>

<script>
	var sock = new SockJS("chtHandler"); // url을 넣어 줘야됨(root에서 path로 지정한 이름)
	// 서버와 연결이 성사 되었을 때
	// 지금 시큐리티 사용 안한다는 전제하에 이렇게 사용 한거임
	sock.onopen = function(){
		console.log("연결 완료");
//		sock.send("연결 됨");	// send는 서버가 클라이언트에게 메세지 전달해주는 친구
		// 시큐리티를 사용 하지 않을 땐 session에서 사용자 정보를 못가져오기 때문에 이렇게 ,를 구분자로 둬서 전달함
		var msg = "${member.u_profile},${member.u_name},님이 입장하셨습니다.";
		sock.send(msg);
	}
	
	// 서버와 연결이 끊겼을 때 
	sock.onclose = function(){
		console.log("연결 끊김");
	}
	
	// server에서 메세지가 전달되었을 때 호출
	sock.onmessage = function(message){
		console.log(message);
		var msg = message.data;
		var datas = msg.split(",");
//		var msg = "${member.u_profile},${member.u_name},님이 입장하셨습니다.";
		// 위에 msg를 ,를 기준으로
		// 0 : u_profile
		// 1 : u_name
		// 2 : message
		var userProfile = datas[0];
		var userName = datas[1];
		var userMessage = datas[2];
		
		var str = "<div class='chatWrap'>";
		if(userProfile != null && userProfile != ''){
			str += "<img src='${path}/upload"+userProfile+"'/>";
		}else{
			str += "<img src='${path}/resources/img/profile.jpg'/>";
		}
		str += "&nbsp;";
		str += userName + " : " + userMessage;
		str += "</div>";
		
		$("#data").append(str);
		
		$("#data").focus();
		// 해당 스크롤을 $("#data").prop("scrollHeight") 크기에 맞춰 알아서 내려감? 
		$("#data").scrollTop($("#data").prop("scrollHeight"));
		$("#data").focus();
	}
	
	// send버튼 눌렀을 때
	$("#sendBtn").click(function(){
		// 메세지 전달
		sendMessage();
	});
	
	// 메세지 작성칸에서 enter가 눌러졌을 때
	$("#message").keydown(function(e){
		console.log(e.keyCode);
		// 13 == enter
		if(e.keyCode == 13){
			// 메세지 전달
			sendMessage();
		}
	});
	
	function sendMessage(){
		var message = $("#message").val();
		var userProfile = "${member.u_profile}";
		var userName = "${member.u_name}";
		
		sock.send(userProfile + ","+userName+","+message);
		$("#message").val(""); // 메시지 전달 하고나면 해당 값 초기화
	}
</script>

</body>
</html>