<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>


<section class="signup spad">
	<div class="container-md">
		<div class="row justify-content-center">
			<div class="col-md-auto">
				<div class="login__form">
					<h3>직원 회원가입!</h3>
					<form id="signUpForm" method="post">
						<input type="text" name="rights" value="1" hidden/>
						아이디
						<div class="input__item">
							<span><i class="bi bi-person-circle"></i></span>
							<input type="text" name="id" id="id" placeholder="아이디를 입력 하세요" /> 
							<div class="result"></div>
						</div>
						비밀번호
						<div class="input__item">
							<span class="icon_lock"></span> 
							<input type="password" name="pw" id="pw" placeholder="비밀번호 입력하세요" />
							<div class="result"></div>
						</div>
						비밀번호 확인
						<div class="input__item">
							<span class="icon_lock"></span> 
							<input type="password" name="repw" id="repw" placeholder="비밀번호 재입력하세요" />
							<div class="result"></div>
						</div>
						이름
						<div class="input__item">
							<span class="icon_profile"></span> 
							<input type="text" name="name" id="name" placeholder="이름을 입력하세요" />
							<div class="result"></div>
						</div>
						닉네임
						<div class="input__item">
							<span><i class="bi bi-person-video2"></i></span> 
							<input type="text" name="nickname" id="nickname" placeholder="닉네임을 입력하세요" />
							<div class="result"></div>
						</div>
						생년월일
						<div class="input__item">
							<span><i class="bi bi-calendar3"></i></span>
							<input type="text" name="birth" id="birth" placeholder="생년월일을 선택해주세요" /> 
							<div class="result"></div>
						</div>
						성별
						<div class="btn-group btn-group-toggle w-100"  data-toggle="buttons">
							<label class="btn btn-outline-secondary text-white radio-gender">
								<input type="radio" name="gender" id="male" value="male"> 남성
							</label>
							<label class="btn btn-outline-secondary ml-2 text-white radio-gender">
								<input type="radio" name="gender" id="female" value="female"> 여성
							</label>
						</div>
							<div class="result mb-3"></div>
						
						주소
						<div class="mb-4">
							<div class="row addr-box">
								<div class="col-md-8 mb-4 ">
									<input type="text" class="form-control" name="post" id="post"/>
								</div>
								<div class="col-md-3" style="padding-right:0">
									<input type="button" class="form-control btn btn-light" onclick="sample6_execDaumPostcode()" value="주소찾기"/>
								</div>
							</div>
							<input type="text" class="form-control mb-4" name="addr" id="addr"/>
							<input type="text" class="form-control" name="addrDetail" id="addrDetail" placeholder="상세주소를 입력해주세요"/>
							<div class="result"></div> 
						</div>
						
						전화번호
						<div class="input__item">
							<span><i class="bi bi-phone"></i></span>
							<input type="text" name="phone" id="phone" placeholder="01056556747" /> 
							<input type="button" class="btn btn-danger" value="인증코드 전송" id="sendSMS" disabled/>
							<div class="result"></div>
						</div>
						
						<div class="input-group flex-nowrap justify-content-center mb-4" style="display: none" id="codeWrap">
							<input type="text" class="phone-code" />
							<input type="button" class="btn btn-secondary smsAcceptCode" value="인증" />
						</div> 
						
						이메일
						<div class="input__item">
							<span class="icon_mail"></span>
							<input type="text" name="email" id="email" placeholder="이메일을 입력하세요" /> 
							<input type="button" class="btn btn-danger" value="이메일 인증" id="sendEmail" disabled/>
							<div class="result"></div>
						</div>
						
						<div class="input-group flex-nowrap justify-content-center mb-4" style="display: none" id="emailCodeWrap">
							<input type="text" class="email-code" />
							<input type="button" class="btn btn-secondary emailAcceptCode" value="인증" />
						</div>

						<div class="row justify-content-center">
						<div class="btn-group">
							<button type="submit" class="btn btn-danger mr-3" >회원가입</button>
							<button type="button" class="cancel-btn">취소</button>
						</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<div class="custom-Loader"></div>

<%@include file="/WEB-INF/views/include/footer.jsp"%>


<script>

$(function(){
	
	$('.custom-Loader').hide();
	
	const regexEmail =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;  // 이메일
	const regexID = /^[0-9a-zA-Z]{3,10}$/;
	const regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;	
	let code = "";
	let boolSMS = false;
	let boolPhone = false;
	let boolEmail = false;
	let boolEmailCode = false;
	
	 function checkRegex(elP, valP, regexP, messageP, ajaxP){
		if(regexP.test(valP) === false){
			showMessage(elP,messageP,false);
			return false;
		}else if(regexP.test(valP) !== false
					&& ajaxP === null){
			showMessage(elP, "", true);
			return true;
		}else{
			if(ajaxP !== null)ajaxP(elP);
		}
	}
	
	function showMessage(elP,messageP,isCheck){
		$(elP).html(`<sapn style='margin-left:5px;font-size:12px;${isCheck ? 'color:green;' : 'color:red;'}'>${messageP}</span>`);
	}
	
				
	$("#phone").on("input", function(){
		var tempVal = $("#phone").val();
		var elP = $(this).parent().find(".result");
		var message = "-를 제외한 숫자만 입력해주세요.";
		boolPhone = checkRegex(elP, tempVal, regexMobile, message, null);
		
		if(boolPhone){
			$("#sendSMS").attr("disabled", false);
		}
	});
	
	$("#sendSMS").click(function(){
		if(!boolPhone){
			alert("전화번호를 먼저 확인해 주세요.");
			$("#phone").focus();
		}else{
			$("#sendSMS").attr("disabled", true);
			$("#codeWrap").show();
			$.ajax({
				type : "get",
				url : "${path}/sign/up/sms",
				data :{
					phone : $("#phone").val()
				},
				beforeSend : function(){
					$('.custom-Loader').delay(100).fadeIn("slow");
				},
				complete : function(){
				    $(".custom-Loader").delay(100).fadeOut("slow");
				},
				success : function(data){
					alert("전화번호로 인증 코드가 전송되었습니다."  + data);
					$("#codeWrap").show();
				},
				error : function(){
					$("#sendSMS").attr("disabled", false);
				}
			});
		}
	});
	
	$(".smsAcceptCode").click(function(){
		$.ajax({
			type: 'post',
			url: '${path}/sign/up/sms',
			data: { "code" : $(".phone-code").val() },
			success: function(result){
					alert("인증 성공 " + result);
					$("#codeWrap").hide();
					boolSMS = true;
					$(".phone-code").val("");
			},
			error: function(error){
				alert("인증 실패 ");
				$(".phone-code").focus();
			}
		})
		
	});
	
	
	
	// 이메일 인증확인
	$("#email").on("input", function(){
		let tempVal = $("#email").val();
		let elP = $(this).parent().find(".result");
		let message = "이메일을 제대로 확인 주세요";
		boolEmail = checkRegex(elP, tempVal, regexEmail, message, null);
		
		if(boolEmail){
			$("#sendEmail").attr("disabled", false);
		}
	});
	
	
	$("#sendEmail").click(function(){
		if(!boolEmail){
			alert("이메일을 확인해 주세요.");
			$("#email").focus();
		}else{
			$("#sendEmail").attr("disabled", true);
			let userEmail = $("#email").val();
			$.ajax({
				type : "GET",
				url : "${path}/sign/up/email",
				data : {
					email : userEmail
				},
				beforeSend : function(){
					$('.custom-Loader').delay(100).fadeIn("slow");
				},
				complete : function(){
				    $(".custom-Loader").delay(100).fadeOut("slow");
				},
				success : function(data){
					alert(`\${userEmail}로 인증 코드가 전송되었습니다.` + data);
					
					$("#emailCodeWrap").show();
				},
				error : function(){
					alert('error');
					$("#sendSMS").attr("disabled", false);
				}
			});
		}
	});
	
	$(".emailAcceptCode").click(function(){
		$.ajax({
			type: 'post',
			url: '${path}/sign/up/email',
			data: { "code" : $(".email-code").val() },
			success: function(result){
				console.log(result);
					alert("이메일인증 성공 " + result);
					$("#emailCodeWrap").hide();
					boolEmailCode = true;
					$(".email-code").val("");
			},
			error: function(err){
				console.log(err)
				alert("이메일인증 실패 "+ err);
				$(".email-code").focus();
			}
		})
	});
	
	
	$.validator.addMethod("regex",function(value,element,regexpr){
		return regexpr.test(value);
	});
	
	
	$("#signUpForm").validate({
		onkeyup : function(el){
			$(el).valid();
		},
		rules : {	
			id : { 
			    required : true,
			    remote : { type : "GET", url : "${path}/sign/up/idCheck" },
			    regex : regexID
			}, 

			pw : {
			    required : true,
			    minlength : 6,
			    maxlength : 20
			},
			repw : {
			    required : true,
			    minlength : 6,
			    maxlength : 20,
			    equalTo : "#pw"
			},

			name : {
			    required : true,
			    rangelength : [2,6]
			},
			nickname : {
			    required : true,
			    remote :{
			        type : "GET",
			        url : "${path}/sign/up/nicknameCheck"
			    },
			    rangelength : [2,10]
			},
//			birth : { required : true },
			gender : { required : true },
			addr : { required : true },
			phone : { 
			    required : true,
			    minlength : 9	
			},
			email : { 
			    required : true,
			    minlength : 4,
			    remote :{
			        type : "GET",
			        url : "${path}/sign/up/emailCheck"
			    }
			}  
		},
		messages : {
			id : {
				required : "아이디를 작성하세요.",
				remote : "이미 존재하는 아이디입니다.",
				regex : "영어와 숫자로 3~10글자 이내로 작성하세요."
			},
			
			pw : {
				required : "비밀번호를 작성하세요.",
				minlength : "비밀번호는 최소 6자리 이상입니다.",
				maxlength : "비밀번호는 최대 20자리까지 가능합니다."
			},
			repw : {
				required : "비밀번호를 작성하세요.",
				minlength : "비밀번호는 최소 6자리 이상입니다.",
				maxlength : "비밀번호는 최대 20자리까지 가능합니다.",
				equalTo : "비밀번호가 일치하지 않습니다."
			},
			
			name : {
				required : "이름을 입력 하세요.",
				rangelength : "이름은 2~6글자 이내로 작성하세요."
			},
			
			nickname : {
				required : "닉네임을 입력하세요.",
				remote : "이미 존재하는 닉네임 입니다.",
				rangelength : "닉네임은 2~10글자 이내로 작성하세요."
			},
			/* 			
			birth : {
				required : "생년월일을 선택하세요."
			},
			 */
			gender : { required : "성별을 확인하세요." },
			addr : { required : "주소를 입력하세요." },
			phone : { 
				required : "전화번호를 입력하세요.",
				minlength : "전화번호를 입력해주세요."	
			},
			email : { 
				required : "이메일을 입력하세요.",
				minlength : "이메일 형식에 맞춰 입력해주세요.",
				remote : "이미 존재하는 이메일입니다."
				} 
		},
		errorClass : "text-danger",
		errorElement : "div",
		errorPlacement : function(error, element){
			if(element.prop("type") === 'radio'){ 
				element.removeClass("text-danger");
				error.insertAfter(element.parent().parent());
			 } else if($(element).attr('id') === 'addr' || $(element).attr('id') === 'phone' || $(element).attr('id') === 'email'){
				error.insertAfter(element.next());		 		
			} else {
				error.insertAfter(element);
			}
		}
	}); 
	
	$.validator.setDefaults({
		submitHandler : function(){
			if(!boolSMS){
				alert("핸드폰 인증코드 확인 해주세요.");
			}else if(!boolEmailCode){
				alert("이메일 인증코드 확인 해주세요.");
			}else{
				$("#signUpForm").submit();
			}
		}
	});
	
});
</script>
