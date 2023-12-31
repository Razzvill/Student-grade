<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
	<table border=0 width="100%">
		<tr>
			<td>
				<a href="${contextPath}/main.do">
				<img src="${contextPath}/resources/image/world-g9476be42f_640.png" width="150px" height="150px"/>
				</a>
			</td>
			<td>
				<h1><font size="20">학사관리 홈페이지</font></h1>
			</td>
			<td>
				<c:choose>
					<c:when test="${isLogOn==true && member!=null}">
						<h3><font size="13"></font>환영합니다. ${member.name}님!</font></h3>
						<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>