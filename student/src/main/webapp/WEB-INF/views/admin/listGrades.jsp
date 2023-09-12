<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 정보 출력창</title>
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
	
	function listMembers(obj){
		obj.method = "post"
		obj.action = "${contextPath}/admin/listMembers.do";
		obj.submit();
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
		width: 95%;
		margin: 0 auto;
	}
	
	.card {
		bgcolor: white;
		border: 1px solid #b3b3b3;
		margin: 4% auto;
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
</style>

<body>
	<section>
	<form class="card shadow-sm bg-body-tertiary rounded">
		<h1 style="text-align: center"><b>성적 목록</b></h1><br>
		<table class="table table-hover" border="1" align="center" width="80%">
			<thead>
				<tr align="center">
					<td><b>학번</b></td>
					<td><b>이름</b></td>
					<td><b>국어</b></td>
					<td><b>영어</b></td>
					<td><b>수학</b></td>
					<td><b>한국사</b></td>
					<td><b>합계</b></td>
					<td><b>평균</b></td>
					<td><b>석차</b></td>
					<td><b>수정</b></td>
					<td><b>삭제</b></td>
				</tr>
			</thead>
			<thead>
				<tr align="center">
					<td><b>전체</b></td>
					<td><b>학생 평균</b></td>
					<td><b>${totalVO.t_korean}</b></td>
					<td><b>${totalVO.t_english}</b></td>
					<td><b>${totalVO.t_math}</b></td>
					<td><b>${totalVO.t_history}</b></td>
					<td><b>${totalVO.t_total}</b></td>
					<td><b>${totalVO.t_avrg}</b></td>
					<td><b></b></td>
					<td><b>전체 학생수</b></td>
					<td><b>${totalVO.t_count}</b></td>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="grade" items="${gradesList}" varStatus="gradeNum">	
					<tr align="center">
						<td>${grade.studentNO}</td>
						<td>${grade.name}</td>
						<td>${grade.korean}</td>
						<td>${grade.english}</td>
						<td>${grade.math}</td>
						<td>${grade.history}</td>
						<td>${grade.total}</td>
						<td>${grade.avrg}</td>
						<td>${grade.rank}</td>
						<td><a
							class="link-success link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover"
							href="${contextPath}/admin/modGrade.do?studentNO=${grade.studentNO}">수정하기</a>
						</td>
						<td><a
							class="link-success link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover"
							href="${contextPath}/admin/removeGrade.do?studentNO=${grade.studentNO}">삭제하기</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btns">
		<button type="button" id="btn" class="btn btn-outline-success btn1" onClick="listMembers(this.form)">학생정보보기</button>
		<button type="button" id="btn" class="btn btn-outline-success btn1" onClick="memberForm(this.form)">학생등록</button>
		<button type="button" id="btn" class="btn btn-outline-secondary btn1" onClick="logout(this.form)">로그아웃</button>
		</div>
	</form>
	</section>
</body>
</html>