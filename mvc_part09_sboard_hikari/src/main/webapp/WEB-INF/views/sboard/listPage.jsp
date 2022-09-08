<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">	<!-- 검색 조건에 맞는 게시글이 몇개있는지 확인하기위해서 totalCount 넣음 -->
						Search List Page - ${pm.totalCount}
					</h3>
					<div>
						<!-- 둘다 홈을 가는 버튼인데 2가지 형태로 표시 할 수 있다.(둘다 같은 의미) -->
						<a class="btn btn-default" href="${pageContext.request.contextPath }">Home</a>
						<a class="btn btn-warning" href="<c:url value='/'/>">Home</a>
					</div>
				</div>
					<div class="box-body">
					<!-- 검색 기능 추가 -->	
					<!-- 한행을 표시 -->
					<div class="row">
						<form id="serachForm">
							<!-- 검색 카테고리 만들기 -->
							<!-- grid(여기선 지금 행을 말함)을 12칸으로 나눔 -->
							<!-- -> 12칸중에 3칸을 차지하겠다.  -->
							<div class="col-md-3">
								<select class="form-control" name="searchType">
									<!-- 검색할 때 옵션이  null이면 이거 선택 | 아니면 비워둠 -->
									<option ${pm.cri.searchType == null ? 'selected' : ''} value="n">---------------</option>
									<option ${pm.cri.searchType == 'title' ? 'selected' : ''} value="title">Title</option>
									<option ${pm.cri.searchType == 'content' ? 'selected' : ''} value="content">Content</option>
									<option ${pm.cri.searchType == 'writer' ? 'selected' : ''} value="writer">Writer</option>
									<option ${pm.cri.searchType == 'tc' ? 'selected' : ''} value="tc">Title &amp; Content</option><!-- &amp == & -->
									<option ${pm.cri.searchType == 'cw' ? 'selected' : ''} value="cw">Content &amp; Writer</option>
									<option ${pm.cri.searchType == 'tcw' ? 'selected' : ''} value="tcw">Title &amp; Content &amp; Writer</option>
								</select>
							</div>
							<!-- 어떤 키워드로 검색할지 키워드 작성칸 -->
							<div class="col-md-3">
								<input class="form-control" type="text" name="keyword" value="${pm.cri.keyword}" />
							</div>
							<!-- div가 col형식으로 2개로 나눠진걸 row로 묶은 다음 2개를 4칸으로 나누겠다. -->
							<div class="row col-md-4">
								<!-- 검색 버튼 -->
								<div class="col-md-6">
									<input class="form-control btn btn-warning" type="submit" value="SEARCH" />
								</div>
								<!-- 글 새로 쓰기 -->
								<div class="col-md-6">
									<input id="newBtn" class="form-control btn btn-primary" type="button" value="NEW" />
								</div>
							</div>
							<div class="col-md-2">
								<select class="form-control" id="pageNumSelect" name="perPageNum">
									<option value="${pm.cri.perPageNum }">${pm.cri.perPageNum }개씩 보기</option>
									<option value="5">5개씩 보기</option>
									<option value="10">10개씩 보기</option>
									<option value="15">15개씩 보기</option>
								</select>
							</div>
						</form>
					</div> <!-- 행 -->
				</div> <!-- 검색 종료 -->
				
				<div class="box-body">
					<!-- 게시글 목록 출력 -->
					<table class="table table-bordered" >
						<tr>
							<td>Bno</td>
							<td>Title</td>
							<td>Writer</td>
							<td>Regdate</td>
							<td>Viewcnt</td>
						</tr>
						<c:choose>
							<c:when test="${!empty list }">
								<!-- 오늘 작성된거는 시간만 / 그이후는 날짜까지 표시해주기 -->
								<jsp:useBean id ="n" class="java.util.Date" />
								<f:formatDate var="now" value="${n }" pattern="yyyy-MM-dd" />
								
								<c:forEach var="sb" items="${list }">
									<tr>
										<td>${sb.bno }</td>
										<td>
											<!-- 상세보기 페이지로 이동 -->
											<a href="readPage${pm.makeQuery(pm.cri.page)}&bno=${sb.bno}">${sb.title }</a>
										</td>
										<td>${sb.writer }</td>
										<td>
											<f:formatDate var="reg" pattern="yyyy-MM-dd" value="${sb.regdate }" />
											<c:choose>
												<c:when test="${now eq reg }">
													<f:formatDate value="${sb.regdate }" pattern="HH:mm" />
												</c:when>
												<c:otherwise>
													${reg }
												</c:otherwise>
											</c:choose>
										</td>
										<td>${sb.viewcnt }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">게시물이 존재하지 않음</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</div> <!-- 리스트 출력 end -->
				
				<div class="box-footer">
					<!-- 페이징 블럭 출력 -->
					<div class="text-center">
						<!-- class="pagination" : ul특성상 아래로 쌓이는데 이거 쓰면 우리가 흔히 보는 페이지처리가 됨 -->
						<ul class="pagination">
						<!-- 여기서 링크로 받는 pm은 PageMaker가 아니라 SearchPageMaker임 -->
							<!-- 처음 페이지 -->
							<c:if test="${pm.first }">
								<li>
									<a href="listPage${pm.makeQuery(1) }">&laquo;&laquo;</a>
								</li>
							</c:if>
							<!-- 이전 페이지 -->
							<c:if test="${pm.prev }"> 
								<li>
									<a href="listPage${pm.makeQuery(pm.startPage-1) }">&laquo;</a>
								</li>
							</c:if>
							<!-- 현재 페이지 -->
							<c:forEach var="i" begin="${pm.startPage }" end="${pm.endPage }">
								<!-- 현재 페이지가 우리가 보고 있는 페이지랑 같으면 class=active를 해주고 아니면 비워둬라 -->
								<li ${pm.cri.page == i ? 'class=active' : '' }>
									<a href="listPage${pm.makeQuery(i) }"> ${i }</a>
								</li>
							</c:forEach>
							<!-- 다음 페이지 -->
							<c:if test="${pm.next }"> 
								<li>
									<a href="listPage${pm.makeQuery(pm.endPage+1) }">&raquo;</a>
								</li>
							</c:if>
							<!-- 마지막 페이지 -->
							<c:if test="${pm.last }"> 
								<li>
									<a href="listPage${pm.makeQuery(pm.maxPage) }">&raquo;&raquo;</a>
								</li>
							</c:if>
						</ul> 
					</div> <!-- 페이징 처리 end -->
					
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	// 글 작성 후에 성공 유무 msg 전달 해주는 친구
	var result = '${result}';
	if(result != '') alert(result);

	// jQuery를 따로 지정 안해주는 이유 : header에서(32행) 선언 해놔서 사용가능
	// 옵션을 선택한 상태에서 검색했을 때 그 옵션을 그대로 띄워 주는 방법 
	// -> form태그의 id값(searchForm)과 해당 옵션태그를 감싸고 있는 태그의 id값(pageNumSelect)을 알면됨
	// => pageNumSelect가 바뀌면 form 호출하는데
	// form태그에 action과 method가 없기에 현재 요청 페이지에 기본 method방식인 get방식으로 출력함
	$("#pageNumSelect").on('change',function(){
		$("#searchForm").submit();
	});
	
	// newBtn 누르면 get방식으로 요청 보냄
	$("#newBtn").click(function(){
		location.href="register";
	});
	
</script>

<jsp:include page="../include/footer.jsp"/>