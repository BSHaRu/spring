<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">SEARCH LIST PAGE - ${pageMaker.totalCount}</h3>
					<div class="col-md-1  pull-right text-right">
						<a href="${pageContext.request.contextPath}" class="btn btn-primary">HOME</a>
					</div>
				</div>
				<div class="box-body">
					<!-- 게시물 목록 -->
					<form>
						<div class="col-md-3">
							<select class="form-control" name="searchType">
								<option value="n" ${cri.searchType == null ? 'selected' : ''}>----------</option>
								<option value="t" ${cri.searchType == 't' ? 'selected' : ''}>TITLE</option>
								<option value="c" ${cri.searchType == 'c' ? 'selected' : ''}>CONTENT</option>
								<option value="w" ${cri.searchType == 'w' ? 'selected' : ''}>WRITER</option>
								<option value="tc" ${cri.searchType == 'tc' ? 'selected' : ''}>TITLE & CONTENT</option>
								<option value="cw" ${cri.searchType == 'cw' ? 'selected' : ''}>CONTENT & WRITER</option>
								<option value="tcw" ${cri.searchType == 'tcw' ? 'selected' : ''}>TITLE & CONTENT & WRITER</option>
							</select>
						</div>
						<div class="col-md-4">
							<input type="text" name="keyword" class="form-control" value="${cri.keyword}"/>
						</div>
						<div class="col-md-4">
							<input type="submit" class="btn btn-warning" value="SEARCH" /> 
							<input id="newBtn" type="button" class="btn btn-primary"
								value="NEW BOARD" />
						</div>
					</form>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:choose>
							<c:when test="${!empty list}">
								<c:forEach items="${list}" var="board">
									<tr>
										<td>${board.bno}</td>
										<td><a
											href="readPage${pageMaker.search(cri.page)}&bno=${board.bno}">${board.title}</a>
										</td>
										<td>${board.writer}</td>
										<td><f:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${board.regdate}" /></td>
										<td><span class="badge bg-red">${board.viewcnt}</span></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan=5 class="text-center">게시물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</div>

				<div class="box-footer">
					<!-- paging 처리 -->
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a
									href="listPage${pageMaker.search(pageMaker.startPage-1)}">&laquo;</a>
								</li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="i">
								<li ${pageMaker.cri.page == i ? ' class=active' : ''}><a
									href="listPage${pageMaker.search(i)}">${i}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li><a href="listPage${pageMaker.search(pageMaker.endPage+1)}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	var result = "${result}";

	if (result == 'SUCCESS') {
		alert('처리 완료!');
	} else if (result == 'FAIL') {
		alert('처리 실패!');
	}

	$(function() {
		$("#newBtn").click(function() {
			location.href = "register";
		});
	});
</script>

<jsp:include page="../include/footer.jsp" />









