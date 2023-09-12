<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		alert("메뉴로 돌아갑니다.");
		obj.method = "post"
		obj.action = "${contextPath}/member/loginForm.do";
		obj.submit();
	}
	function readURL(input) {
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
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
		<form class="mb-3" method="post"
			action="${contextPath }/member/addMember.do" encType="multipart/form-data">
			<h1 style="text-align: center"><b>학생 등록창</b></h1><br>
			<div class="mb-3">
				<label for="inputId" class="form-label">아이디</label>
				<div class="col-sm-10">
					<input type="text" name="id" class="form-control" id="inputId">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputPassword" class="form-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" name="pwd" class="form-control"
						id="inputPassword">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputName" class="form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="inputName">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputEmail" class="form-label">이메일</label>
				<div class="col-sm-10">
					<input type="text" name="email" class="form-control"
						id="inputEmail">
				</div>
			</div>
			<div>
				<label for="inputImage" class="form-label">사진</label>
				<input type="file" name="imageFileName" id="inputimage" onchange="readURL(this);"/>
				<img id="preview" src="#" width="200" height="200"/>
			</div>
			<div class="btn-group" role="group"
				aria-label="Basic outlined example">
				<button type="submit" class="btn btn-outline-success">등록하기</button>
				<button type="reset" class="btn btn-outline-secondary">다시입력</button>
				<button type="button" class="btn btn-outline-success"
					onClick="backToList(this.form)">돌아가기</button>
			</div>
		</form>
	</section>
</body>
</html>