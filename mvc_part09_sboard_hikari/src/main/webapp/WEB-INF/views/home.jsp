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
						Home Page
					</h3>
				</div>
				<div class="box-body">
					<!-- default 흰색 | primary 파랑 | warning 주황 | danger 빨강 -->
					<a class="btn btn-primary" href="sboard/register">검색 게시판 글쓰기</a> <br/>
					<a class="btn btn-danger" href="sboard/listPage">검색 게시판 목록</a>	<br/>
				</div>
				<div class="box-footer">
				
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="./include/footer.jsp"/>