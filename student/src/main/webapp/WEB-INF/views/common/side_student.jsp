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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function check() {   
		var adminId = prompt("관리자 비밀번호를 입력하세요.", "");
		
		if(adminId == null || adminId==""){
			alert("비밀번호가 입력되지 않았습니다.");
			location.href="${contextPath}/member/viewDetail.do?id=${member.id}";
		} else if($(adminId == "admin1234")) {
			alert("관리자로 로그인 되었습니다.");
			location.href="${contextPath}/admin/listGrades.do";
		}
	}
</script>
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
		<a onclick="check()" class="no-underline">관리자 전환</a><br>
	</h1>
</body>
</html>