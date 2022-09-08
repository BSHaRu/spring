<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"/>
<style>
	textarea{
		resize:none;
	}
	
	.max-width{
		max-width:500px;
	}
</style>

	<div class="container">
		<form id="joinForm" action="join" method="post">
			<table class="container table table-bordered max-width">
				<tr>
					<th colspan="2" class="text-center">
						<h1>JOIN PAGE</h1>
					</th>
				</tr>
				<tr>
					<td>
						아이디
					</td>
					<td>
						<input type="text" class="form-control" name="userid" required/>
					</td>
				</tr>
				<tr>
					<td>
						비밀번호
					</td>
					<td>
						<input type="password" class="form-control"name="userpw" required/>
					</td>
				</tr>
				<tr>
					<td>
						이름
					</td>
					<td>
						<input type="text" class="form-control" name="username" required/>
					</td>
				</tr>
				<tr>
					<td>
						e-mail
					</td>
					<td>
						<input type="email" class="form-control" name="email" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" class="form-control btn btn-primary" value="회원가입" />
					</td>			
				</tr>
			</table>
		</form>
	</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>