<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 정보 수정창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		alert("메뉴로 돌아갑니다.");
		obj.method = "post"
		obj.action = "${contextPath}/admin/listGrades.do";
		obj.submit();
	}
	function fn_sub(obj) {
		document.getElementById("inputId").disabled=false;
		obj.submit();
	}
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<style>
	body {
		background-color: #e6ffe6;
	}
	
	.mb-3 {
		margin: 0 auto;
		align: center;
	}
	
	.card {
		bgcolor: white;
		border: 1px solid #ccc;
		width: 50%;
		margin: 5% auto;
		padding: 5%;
	}
</style>
</head>
<body>
<section class="card shadow-sm bg-body-tertiary rounded">
	<form class="mb-3" name="frm" method="post" action="${contextPath }/admin/updateGrade.do">
		<h1 style="text-align: center"><b>성적 정보 수정창</b></h1><br>
		<div class="mb-3">
				<label for="inputStdNO" class="form-label">학번</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputStdNO" placeholder="${grade.studentNO}" readonly>
					<input type="hidden" name="studentNO" value="${grade.studentNO}"
				</div>
			</div>
			<div class="mb-3">
				<label for="inputPassword" class="form-label">국어</label>
				<div class="col-sm-10">
					<input type="text" name="korean" class="form-control"
						id="inputPassword" value="${grade.korean}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputName" class="form-label">영어</label>
				<div class="col-sm-10">
					<input type="text" name="english" class="form-control" id="inputName" value="${grade.english}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputEmail" class="form-label">수학</label>
				<div class="col-sm-10">
					<input type="text" name="math" class="form-control"
						id="inputEmail" value="${grade.math}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputEmail" class="form-label">한국사</label>
				<div class="col-sm-10">
					<input type="text" name="history" class="form-control"
						id="inputEmail" value="${grade.history}">
				</div>
			</div>
			<div class="btn-group" role="group"
				aria-label="Basic outlined example">
				<button type="submit" class="btn btn-outline-success" 
					onClick="fn_sub(this.form)">수정하기</button>
				<button type="reset" class="btn btn-outline-secondary">다시입력</button>
				<button type="button" class="btn btn-outline-success"
					onClick="backToList(this.form)">돌아가기</button>
			</div>
	</form>
</section>
</body>
</html>