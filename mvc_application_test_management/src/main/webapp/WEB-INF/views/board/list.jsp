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
					<h3 class="box-title">TEST LIST PAGE - ${pageMaker.totalCount}</h3>
					<div class="col-md-1  pull-right text-right">
						<a href="${pageContext.request.contextPath}" class="btn btn-primary">HOME</a>
					</div>
				</div>
				<div class="box-body ">
					<div class="col-md-1  pull-right text-right">
						<form action="reg">
							<button class="btn btn-primary">NEW BOARD</button>
						</form>
					</div>
					<div class="col-md-2">
						<select class="form-control" id="select"> 
							<c:forEach begin="5" end="50" step="5" var="i">
								<option value="${i}" ${pageMaker.cri.perPageNum == i ? 'selected' : ''}>${i}개씩</option>	
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="box-body">
					<table class="table table-bordered text-center">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:forEach items="${list}" var="board">
							<tr>
								<td>${board.bno}</td>
								<td>
									<a href="read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${board.bno}">
										<c:out value="${board.title}"/>
									</a>
								</td>
								<td>${board.writer}</td>
								<td><f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/></td>
								<td><span class="badge bg-red">${board.viewcnt}</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="box-footer">
					<!-- paging 처리 -->
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
								<li><a href="1">1</a></li>
								<li><span>...</span></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li ${pageMaker.cri.page == idx ? ' class=active' : ''}>
									<a href="${idx}">${idx}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li><span>...</span></li>
								<li><a href="${pageMaker.maxPage}">${pageMaker.maxPage}</a></li>
								<li><a href="${pageMaker.endPage + 1}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
	
	<form id="jobForm">
		<input type="hidden" name="page" value="${pageMaker.cri.page}"/>
		<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
	</form>

	<script>
		var result = '${result}';
		if(result != null && result != '') alert(result);
		
		$(".pagination li a").on("click",function(event){
			event.preventDefault();
			// page
			var targetPage = $(this).attr("href");
			
			var jobForm = $("#jobForm");
			
			jobForm.find("[name='page']").val(targetPage);
			jobForm.attr("action","list").attr("method","GET");
			jobForm.submit();
		});
		
		$("#select").on("change",function(){
			var jobForm = $("#jobForm");
			jobForm.find("[name='page']").val(1);
			jobForm.find("[name='perPageNum']").val($(this).val());
			jobForm.attr("action","list").attr("method","GET");
			jobForm.submit();
		});
	</script>
<jsp:include page="../include/footer.jsp" />