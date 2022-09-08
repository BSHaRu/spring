<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						HOME PAGE
					</h3>
				</div>
				<div class="box-body">
					<c:choose>
						<c:when test="${!empty user}">
							${user.username}
						</c:when>
						<c:otherwise>	
							<a href="user/login" class="btn btn-primary">로그인</a>
							<a href="user/join" class="btn btn-primary">회원가입</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="./include/footer.jsp"/>