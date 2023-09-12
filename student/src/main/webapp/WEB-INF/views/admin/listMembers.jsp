<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 출력창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function logout(obj) {
		alert("로그아웃 되었습니다.");
		obj.method = "get"
		obj.action = "${contextPath}/member/logout.do";
		obj.submit();
	}
	
	function memberForm(obj){
		obj.method = "post"
		obj.action = "${contextPath}/member/memberForm.do";
		obj.submit();
	}

	function listGrade(obj){
		obj.method = "post"
		obj.action = "${contextPath}/grade/listGrades.do";
		obj.submit();
	}

	function check() {   
		var adminId = prompt("관리자 비밀번호를 입력하세요.", "");
		
		if(adminId == null || adminId==""){
			alert("비밀번호가 입력되지 않았습니다.");
			location.href="${contextPath}/member/listMembers.do";
		} else if($(adminId == "admin1234")) {
			alert("관리자로 로그인 되었습니다.");
			location.href="${contextPath}/admin/listGrades.do?id=admin";
		}
	}
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<style>
	body {
		background-color: #e6ffe6;
	}
	
	section {
		width: 100%;
		margin: 0 auto;
	}
	
	.card {
		bgcolor: white;
		border: 1px solid #b3b3b3;
		margin: 1% auto;
	}
	
	form {
		padding: 3% 1%;
	}
	
	.btns {
		text-align: right;
	}
	
	.btn1 {
		display: inline-block;
	}
	#preview {
		width: 50%;
		height: auto;
	}
</style>

<body>
	<section>
	<form class="card shadow-sm bg-body-tertiary rounded">
		<h1 style="text-align: center"><b>학생 목록</b></h1><br>
		<table class="table table-hover" border="1" align="center" width="95%">
			<thead>
				<tr align="center">
					<td><b>아이디</b></td>
					<td><b>비밀번호</b></td>
					<td><b>이름</b></td>
					<td><b>이메일</b></td>
					<td><b>가입일</b></td>
					<td><b>사진</b></td>
					<td><b>수정</b></td>
					<td><b>삭제</b></td>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="member" items="${membersList}">
					<tr align="center">
						<td>${member.id}</td>
						<td>${member.pwd}</td>
						<td><a
							class="link-success link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover"
							href="${contextPath}/member/viewDetail.do?id=${member.id}">${member.name}</a></td>
						<td>${member.email}</td>
						<td>${member.joinDate}</td>
						<td><input type="hidden" name="originalFileName" value="${member.imageFileName}"/>
							<img src="${contextPath}/download.do?studentNO=${member.studentNO}&imageFileName=${member.imageFileName}" id="preview"/></td>
						<td><a
							class="link-success link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover"
							href="${contextPath}/member/modMember.do?id=${member.id}">수정하기</a>
						</td>
						<td><a
							class="link-success link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover"
							href="${contextPath}/member/removeMember.do?id=${member.id}">삭제하기</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btns">
		<button type="button" id="btn" class="btn btn-outline-success btn1" onClick="listGrade(this.form)">성적보기</button>
		<button type="button" id="btn" class="btn btn-outline-success btn1" onClick="memberForm(this.form)">학생등록</button>
		<button type="button" id="btn" class="btn btn-outline-secondary btn1" onClick="logout(this.form)">로그아웃</button>
		</div>
	</form>
	</section>
</body>
</html>