<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
	<h3>회원 탈퇴하기</h3>
	<hr>
	<form action="drawCheck">
		탈퇴할 아이디 : <input type="text" name="mid">
		<input type="submit" value="회원 탈퇴">
	</form>
	<br>
	<h3>${error}</h3>
</body>
</html>