<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#messageList{
		padding-left:0;
	}
	#messageList li{
		list-style:none;
		border : 1px solid black;
		margin-bottom : 3px;	
		padding : 10px;
		height : 50px;
	}
</style>
</head>
<body>
	<h2>Message</h2>
	<input type="text" id="targetid" placeholder="수신자id" /> <br/>
	<input type="text" id="sender" placeholder="발신자id" /> <br/>
	<input type="text" id="message" placeholder="MESSAGE" /> <br/>
	<button id="ADD">발송</button>
	<ul id="messageList">
		
	</ul>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
<script>
	
	getMessageList();

	function getMessageList(){
		$.getJSON("messages/list", function(data){
			console.log(data);
			
			var str = "";
			$(data).each(function(){
				var senddate = getDate(this.senddate);
				console.log(senddate);
				
				var opnedate = "";
				console.log(this.opendate);
				if(this.opendate == null){
					opendate = "미확인";
				}else{
					opendate = getDate(this.opendate);
				}						// targetid는 문자열이라서 감싸줘야되는데, "",''둘다 쓰고 있어서 \로 감싼거임
				str += "<li onclick='readMessage("+this.mno+",\""+this.targetid+"\",this);'>";
				str += this.mno+" | "+this.targetid+" | "+this.sender;
				str += " | "+this.message+" | "+opendate+" | "+senddate;
				str += "</li>";
			}); // each end
			$("#messageList").html(str);
		});
	}
	
	function readMessage(mno, uid, element){
		console.log(mno);
		console.log(uid);
		console.log(element);
		// $(element).html("이게 뭔데 ㅆㄷㅇ"); // 눌렀을때 해당 문구로 바뀜(읽었다는 걸 확인하기 위해 찍은 코드)
		
		$.ajax({
			type : "PATCH",
			url : "messages/read/"+mno+"/"+uid,
			/* 
			url : "messages/read/"+mno,
			headers : {
				"Content-Type" : "application/json"
			},
			data : JSON.stringify({
				uid : uid
			}),
			 */
			dataType : "json",
			success : function(data){
				console.log(data);
				var str = data.mno+" | "+data.targetid+" | "+data.sender;
				str += " | "+data.message+" | "+getDate(data.opendate)+" | "+getDate(data.senddate);
				$(element).html(str);
			},
			error : function(res, status, error){
				alert(res.responseText);
			}
		});
	}
	
	function getDate(date){
		var dateTime = new Date(date);
		var dateStr = dateTime.getFullYear()+"년";
		dateStr += (dateTime.getMonth()+1)+"월";
		dateStr += dateTime.getDate()+"일";
		dateStr += dateTime.getHours()+"시";
		dateStr += dateTime.getMinutes()+"분";
		return dateStr;
	}

	$("#ADD").click(function(){
		$.ajax({
			type : "POST",
			url : "messages/add",
			dataType : "text",
			data : {
				targetid : $("#targetid").val(),
				sender : $("#sender").val(),
				message : $("#message").val()
			},
			success : function(data){
				alert(data);
				$("#targetid").val("");
				$("#sender").val("");
				$("#message").val("");
				$("#targetid").focus();
			},
			error : function(res, status, error){
				console.log("code : " + res.status+"\n message : "+res.responseText);
				alert(res.responseText);
			}
		});	// ADD ajax end
	});	// ADD click end
	
	
</script>	

</body>
</html>