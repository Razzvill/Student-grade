<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="result" value="${param.result }"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<c:choose>
	<c:when test="${result=='loginFailed' }">
		<script>
			window.onload = function(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인 하세요!");
			}
		</script>
	</c:when>
</c:choose>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function memberForm(obj){
		obj.method = "post"
		obj.action = "${contextPath}/member/memberForm.do";
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
	
	.mb-3 {
		margin: 0 auto;
		width: 90%;
	}
	
	.card {
		bgcolor: white;
		border: 1px solid #ccc;
		width: 50%;
		margin: 5% auto;
		padding: 5%;
	}
	
	form {
		padding: 3% 1%;
	}
	
	.btns {
		text-align: center;
	}
</style>
</head>
<body>
	<section class="card shadow-sm bg-body-tertiary rounded">
		<form class="mb-3" name="frmLogin" method="post" action="${contextPath}/member/login.do">
			<h1 style="text-align: center"><b>로그인</b></h1><br>
			<div class="mb-3">
				<label for="inputId" class="form-label">아이디</label>
				<div class="col-sm-12">
					<input type="text" name="id" class="form-control" id="inputId">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputPassword" class="form-label">비밀번호</label>
				<div class="col-sm-12">
					<input type="password" name="pwd" class="form-control"
						id="inputPassword">
					<div class="eyes">
						<i class="bi bi-eye"></i>
					</div>
				</div>
			</div>
			<br>
			<div class="btns">
			<button type="submit" class="btn btn-outline-success">로그인</button>
			<button type="reset" class="btn btn-outline-secondary">다시입력</button>
			<button type="button" class="btn btn-outline-success" onClick="memberForm(this.form)">회원가입</button>
			</div>
		</form>
	</section>
</body>
</html>