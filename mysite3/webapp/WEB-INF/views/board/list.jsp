<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath}/board/list"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr>
							<td>${((curPageNum-1)*5)+1+status.index}</td>
							<td style="padding-left:${20*vo.depth}px;text-align:left">
								<!--  img  src="${pageContext.servletContext.contextPath}/assets/images/reply.png"-->
								<a
								href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}&page=${curPageNum}">${vo.title }</a>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.date }</td>
							<td><c:if test="${authuser.no == vo.user_no }">
									<a
										href="${pageContext.servletContext.contextPath}/board/delete?no=${vo.no}"
										class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>



				<!-- pager 추가 -->
				<div class="pager">
					<ul>

						<c:if test="${ curPageNum > 5 }">
							<li><a href="${pageContext.servletContext.contextPath}/board/list?page=${ blockStartNum - 1 }&kwd=${kwd}">◀</a></li>
						</c:if>
						<c:forEach var="i" begin="${ blockStartNum }"
							end="${ blockLastNum }">
							<c:choose>
								<c:when test="${ i > lastPageNum }">
									<li>${ i }</li>
								</c:when>
								<c:when test="${ i == curPageNum }">
									<li class="selected">${ i }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath}/board/list?page=${ i }&kwd=${kwd}">${ i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${ lastPageNum > blockLastNum }">
							<li><a href="${pageContext.servletContext.contextPath}/board/list?page=${ blockLastNum + 1 }&kwd=${kwd}">▶</a></li>
						</c:if>
					</ul>
				</div>



				<!-- pager 추가 -->




				<div class="bottom">
					<c:if test="${not empty authuser}">
						<a
							href="${pageContext.servletContext.contextPath}/board/write?no=${authuser.no}"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>