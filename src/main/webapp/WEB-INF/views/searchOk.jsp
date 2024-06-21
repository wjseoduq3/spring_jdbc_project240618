<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회 결과</title>
</head>
<body>
	<h3>회원 정보</h3>
	<hr>
	<form action="modifyOk" >
	<input type="hidden" name="mid" value="${memberDto.mid}">
	
	아이디 : ${memberDto.mid}<br><br>
	<!-- <input type="text" name="mid" value="${memberDto.mid}" readonly="readonly"> -->
	이름 : <input type="text" name="mname" value="${memberDto.mname}"><br><br>
	<!-- 이름 : ${memberDto.mname}<br><br> -->
	이메일 : <input type="text" name="memail" value="${memberDto.memail}"><br><br>
	<!-- 이메일 : ${memberDto.memail}<br><br> -->	
    가입일 : ${memberDto.mdate}<br><br>
    <input type="submit" value="회원정보 수정 완료">
    </form>
    ${error}
</body>
</html>