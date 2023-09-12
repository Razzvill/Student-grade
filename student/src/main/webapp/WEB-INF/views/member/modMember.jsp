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
<title>학생 정보 수정창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		alert("메뉴로 돌아갑니다.");
		obj.method = "post"
		obj.action = "${contextPath}/member/listMembers.do";
		obj.submit();
	}
	function fn_sub(obj) {
		document.getElementById("inputId").disabled=false;
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
	
	#wrap.card {
		bgcolor: white;
		border: 1px solid #ccc;
		width: 70%;
		margin: 5% auto;
		padding: 5%;
	}

	#inner.card {
		bgcolor: white;
		border: 1px solid #ccc;
		width: 85%;
		margin: 5%;
		padding: 5%;
	}
</style>
</head>
<body>
<section id="wrap" class="card shadow-sm bg-body-tertiary rounded">
	<form class="mb-3" name="frm" method="post" action="${contextPath }/member/updateMember.do" encType="multipart/form-data">
		<h1 style="text-align: center"><b>학생 정보 수정창</b></h1><br>
		<div class="mb-3">
				<label for="inputId" class="form-label">아이디</label>
				<div class="col-sm-10">
					<input type="text"class="form-control" id="inputId" placeholder="${member.id}" readonly>
					<input type="hidden" name="id" value="${member.id}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputPassword" class="form-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" name="pwd" class="form-control"
						id="inputPassword" value="${member.pwd}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputName" class="form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="inputName" value="${member.name}">
				</div>
			</div>
			<div class="mb-3">
				<label for="inputEmail" class="form-label">이메일</label>
				<div class="col-sm-10">
					<input type="text" name="email" class="form-control"
						id="inputEmail" value="${member.email}">
				</div>
			</div>
			<div id="inner" class="card">
				<c:if test="${not empty member.imageFileName && member.imageFileName!='null'}">
					<input type="hidden" name="originalFileName" value="${article.imageFileName}"/>
					<img src="${contextPath}/download.do?studentNO=${member.studentNO}&imageFileName=${member.imageFileName}" class="card-img-top">
				</c:if>
				<input type="file" name="imageFileName" id="i_imageFileName" onchange="readURL(this);"/>
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