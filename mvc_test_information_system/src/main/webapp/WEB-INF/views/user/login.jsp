<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"/>
<div class="container">
		<form id="loginForm" action="login" method="post">
			<table class="container table table-bordered" style="max-width:500px;background-color:white;">
				<tr>
					<th colspan="2" class="text-center">
						<h1>LOGIN PAGE</h1>
					</th>
				</tr>
				<tr>
					<td>
						아이디
					</td>
					<td>
						<input type="text" class="form-control" name="userid"/>
					</td>
				</tr>
				<tr>
					<td>
						패스워드
					</td>
					<td>
						<input type="password" class="form-control" name="userpw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" class="form-control btn btn-primary" value="로그인" />
					</td>			
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="button" class="form-control btn btn-primary" onclick="location.href='join';" value="회원가입" />
					</td>			
				</tr>
			</table>
		</form>
	</div>
<jsp:include page="../include/footer.jsp"/>