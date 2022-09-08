<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  user/signIn.jsp    -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.alertDiv{
		display:none;
		position:absolute;
		width:100%;
		height:100%;
		background-color:rgba(0,0,0,0.8);
		top:0;
		left:0;
		text-align:center;
	}
	
	.contentWrap{
		border:1px solid white;
		border-top-left-radius:10px;
		border-top-right-radius:10px;
		position:absolute;
		width:410px;
		height:200px;
		top:50%;
		left:50%;
		margin-top:-100px;
		margin-left:-205px;
		color:white;
	}
	
	.textWrap{
		padding:15px;
		height:120px;
		line-height:120px;
	}
	
	.close{
		width:100%;
		color:black;
		background-color:white;
		line-height:50px;
		font-weight:bold;
	}
	
	.close:hover{
		cursor:pointer;
	}
</style>
</head>
<body>
	<div>
		<h2>로그인</h2>
	</div>
	<div>
		<h2><a href="${pageContext.request.contextPath }">Main</a></h2>
	</div>
	
	<article>
		<form action="signInPost" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="uid" required />
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="upw" required />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label>
							<input type="checkbox" name="useCookie"/>
							로그인 정보 저장
						</label>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="로그인" />
						<input type="button" 
							onclick="location.href='signUp';"
							value="회원 가입" />
					</th>
				</tr>
			</table>
		</form>
	</article>
	<div class="alertDiv" id="alertDiv">
		<div class="contentWrap">
			<div class="textWrap" id="textWrap">
				<span id="message"></span> <br/>
				<span id="time"></span>
			</div>
			<div id="closeBtn" class="close">닫기</div>
		</div>
	</div>
	
	<script>
		var msg = '${message}';
		var time = '${time}';
		var interval;
		
		let alertDiv = document.getElementById('alertDiv');
		
		if(msg != ''){
			// alert(msg);
			alertDiv.style.display = "block";
			document.getElementById("message").innerHTML = msg;
			if(time != ''){
				// 숫자 타입으로 변경
				time = Number(time);
				document.getElementById("textWrap").style.lineHeight="60px";
				// document.getElementById("time").innerHTML = "남은시간 - "+time;
				getTime();
				interval = setInterval(getTime,1000);
			}
		}
		
		document.getElementById("closeBtn").onclick = stop;
		
		function stop(){
			clearInterval(interval);
			alertDiv.style.display = "none";
		}
		
		function getTime(){
			time -= 1000;
			if(time < 0){
				stop();
			}
			var date = new Date(time);
			var min = date.getMinutes(); 	// 0~59
			// 0 ~ 9 한자리 , 10 ~ 59 2자리수
			var second = date.getSeconds();	// 0~59
			min = 10 > min ? "0"+min : min;
			second = 10 > second ? "0"+second : second;
			var timeStr = min+":"+second;
			console.log(timeStr);
			document.getElementById("time").innerHTML = "남은시간 - "+timeStr;
		}
	
	</script>
	
</body>
</html>