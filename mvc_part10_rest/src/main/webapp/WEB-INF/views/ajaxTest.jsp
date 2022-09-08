<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div style="background-color:gray; height:500px;">dummy</div>
	<div>
		<!-- name은 form 태그에서 submit 될때 필요 한거라서 
		form태그 없이 id값만으로  하는거라 여기선 필요없음 -->
		<input type="text" id="name" /><br/>
		<input type="number" id="age" /><br/>
		<input type="button" id="get" value="GET" /> <br/>
		<input type="button" id="post" value="POST" /> <br/>
		<input type="button" id="PUT" value="PUT" /> <br/>
	</div>
	<div id="result" style="border:1px solid black;"></div>
<script>
	$(function(){
		console.log("문서 준비 완료");
		$("#get").click(function(){
			let name = $("#name").val();
			let age = $("#age").val();
			console.log('name : '+name+', age : '+age);
			
			$.ajax({
				async : false,		// 기본값 : true(비동기)
				// -> ajax를 호출하고나서 응답이 모두 완료된 상태에서 해당 로직을 실행함
				type : "GET",		// 전송방식
				url : "getSample",	
				data : {			// 전달 할 파라미터 값
					name : name,
					age : age
				},
				dataType : "json",	// 결과 데이터 타입(전달하는 타입이 아님)
				success : function(data){
					// 요청 처리 결과 - 성공 시(코드값 200~300일 경우) 
					console.log(data);
				},
				error : function(res){
					console.log(res);
				}
			});	// ajax end
			console.log("ajax 호출 완료");
		});	// get click event end
		
		$("#post").click(function(){
			let input_name = $("#name").val();
			let input_age = $("#age").val();
			
			$.ajax({
				type : "POST",
				url : "getSample",
				data:{
					name : input_name,
					age : input_age
				},
				dataTyep : "json",
				success : function(data){	
					// data == List<SampleVO>
					var html ="<table border=1>";
					html +="<tr>";
					html +="<th>이름</th>";
					html +="<tr>나이</th>";
					html +="</tr>";
					for(var i=0; i<data.length; i++){
						html +="<tr>";
						html +="<td>"+data[i].name+"</td>";
						html +="<td>"+data[i].age+"</td>";
						html +="</tr>";
					}
					html +="</table>";
					// innerHTML이랑 같은거임
					$("#result").html(html);
				}
			}); // ajax end
		}); // post click end
		
		$("#PUT").click(function(){
			val obj = {
				name : $("#name").val(),
				age : $("#age").val()
			};
			$.ajax({					
				type : "PUT",
				url : "testPUT",
				// 보통 웹에선 form 태그를 쓰니깐 headers를 지정 안할껀데
				// 그게 아닌 경우가 많기때문에 headers를 통해서 json타입이라고 알려줘야됨
				headers : {
					'Content-Type' : 'application/json'
				},
				// name : $("#name").val(),	age : $("#age").val()
				data : JSON.stringify(obj),	// json으로 바꿔줬기때문에 해당 값을 사용할라면 json으로 받아줘야됨
				dataType : "json",
				success : function(){
					console.log(result);
				}
			}); // ajax end
		}); // click end
	});
</script>	
	
</body>
</html>