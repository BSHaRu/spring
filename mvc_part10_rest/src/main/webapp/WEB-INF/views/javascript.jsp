<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>javascript.jsp</title>
</head>
<body>
	<h1>자바 스크립트</h1>
	<input type="button" id="btn" value="CLICK" />
	<div id = "box">
	
	</div>
	<div id = "box2">
	
	</div>
	<script>
		/* AJAX(Asynchronous JavaScript and XML) : 비동기 자바 스크립트와 XML
		- 서버와 통신하기 위해서 XMLHttpRequest 객체를 사용하는 것.
		- 특징 : 페이지 전체를 리프레쉬 하지 않고 수행되는 비동기성
		-> ajax는 http 페이지 전체가 아닌 "일부만 갱신"가능하도록, XMLHttpRequest 객체를 통해서 서버에 요청
		*/
		var httpRequest;
		
		document.getElementById("btn").onclick = function(){
			makeRequest('getSample');
		};
		
		function makeRequest(url){
			httpRequest = new XMLHttpRequest();
			if(!httpRequest){
				alert("정상적으로 생성 되지 않았습니다.");
				return false;
			}
			
			httpRequest.open('GET',url+"?name=ㅎㅇ&age=11");
			httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			httpRequest.onreadystatechange = setContents; // setContents를 호출하는게 아니라서 ()를 안한거임
			httpRequest.send();
		}
		
		// 요청에 따른 상태 변경 감지
		function setContents(){
			/* onreadystatechange 5가지 상태
				0 : uninitialized - request가 초기화 되지 않음
				1 : loading - 서버와의 연결이 성사됨
				2 : loaded - 서버가 request를 받음
				3 : interactive - request 요청을 처리하는 중
				4 : complate - request에 대한 처리가 끝났으며, 응답 할 준비가 완료됨
			*/
			
			if(httpRequest.readyState === 4){
				// 상태 코드 200 : 정상적으로 요청 완료
				if(httpRequest.status === 200){
					// 정상 수행 완료
					// httpRequest.responseText : 서버의 응답을 텍스트 문자열로 반환
					// httpRequest.responseXML : 서버의 응답을 XML문서로 반환
					alert(httpRequest.responseText);
					var obj = JSON.parse(httpRequest.responseText); // JSON.parse(): JSON 문자열을 js 객체로 변환
					console.log(obj.name);
					console.log(obj.age);
					console.log(obj);
					
					document.getElementById("box").innerHTML 
					 = JSON.stringify(obj);	// JSON.stringify() : js를 JSON 객체로 변환
					
					var html = "<table border=1>";
					html +="<tr> <th>이름</th> <th>나이</th> </tr>";
					html +="<tr>";
						html +="<td>";
						html +=obj.name;
						html +="</td>";
						
						html +="<td>";
						html +=obj.age;
						html +="</td>";
					html +="</tr>";
					html +="</table>";
					document.getElementById("box2").innerHTML = html;
				}else{
					// 요청을 처리하는 중 오류 발생
					alert("요청 처리 실패");
				}
			}
		}
	</script>
</body>
</html>