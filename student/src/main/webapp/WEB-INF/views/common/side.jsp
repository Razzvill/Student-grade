<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
	.no-underline {
		text-decoration: none;
		font-size: 15px;
	}
</style>
<title>사이드 메뉴</title>
</head>
<body>
	<h3><font size="13">사이드 메뉴</font></h3>
	<h1>
		<a href="${contextPath}/member/listMembers.do" class="no-underline">학생관리</a><br>
		<a href="${contextPath}/grade/listGrades.do" class="no-underline">성적관리</a><br>
	</h1>
</body>
</html>