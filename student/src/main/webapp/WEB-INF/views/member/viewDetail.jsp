<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	function fn_enable(obj){
		document.getElementById("i_pwd").disabled=false;
		document.getElementById("i_email").disabled=false;
		document.getElementById("i_name").disabled=false;
		document.getElementById("i_imageFileName").disabled=false;
		document.getElementById("tr_btn_modify").style.display="block";
		document.getElementById("tr_btn").style.display="none";
	}
	
	function fn_disable(obj){
		document.getElementById("i_pwd").disabled=true;
		document.getElementById("i_email").disabled=true;
		document.getElementById("i_name").disabled=true;
		document.getElementById("i_imageFileName").disabled=true;
		document.getElementById("tr_btn_modify").style.display="none";
		document.getElementById("tr_btn").style.display="block";
	}
	
	function fn_modify_member(obj){
		obj.action="${contextPath}/member/updateMember.do";
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
<style>
	body {
		width: 90%;
	}
	#tr_btn_modify {
		display: none;
	}
</style>
</head>
<body>
	<form name="frmMember" method="post" action="${contextPath}" encType="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">학번</td>
				<td>
					<input type="text" value="${member.studentNO}" disabled/>
					<input type="hidden" name="studentNO" value="${member.studentNO }"/>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">아이디</td>
				<td>
					<input type="text" value="${member.id}" name="writer" disabled/>
					<input type="hidden" name="id" value="${member.id }"/>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">비밀번호</td>
				<td>
					<input type="password" value="${member.pwd}" name="pwd" id="i_pwd" disabled/>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">이름</td>
				<td>
					<input type="text" value="${member.name}" name="name" id="i_name" disabled/>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">이메일</td>
				<td>
					<input type="text" value="${member.email}" name="email" id="i_email" disabled/>
				</td>
			</tr>
			
			<c:if test="${not empty member.imageFileName && member.imageFileName!='null'}">
				<tr>
					<td width="150" align="center" bgcolor="#FF9933" rowspan="2">이미지</td>
					<td>
						<input type="hidden" name="originalFileName" value="${member.imageFileName}"/>
						<img src="${contextPath}/download.do?imageFileName=${member.imageFileName}&studentNO=${member.studentNO}" id="preview"
							width="200px"/><br>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"/>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">가입일자</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${article.writeDate}'/>" disabled/>
				</td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정반영하기" onClick="fn_modify_member(frmMember)">
					<input type="button" value="취소" onClick="backToList(frmMember)">
				</td>
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onClick="fn_enable(this.form)">
					<input type="button" value="삭제하기" onClick="${contextPath}/member/removeMember.do?id=${member.id}">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>