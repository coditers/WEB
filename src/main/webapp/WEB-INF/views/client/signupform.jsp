<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Codit</title>
    <!--Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css"-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<div>
    <h1>회원가입</h1>
    <form action = "${pageContext.request.contextPath}/client/signup" method = "POST">
        <label>대표 이메일</label> <input type="text" name="email"><br>
        <label>비밀번호</label> <input type="password" name="password"><br>
        <label>비밀번호 확인</label> <input type="password" name="re-password"><br><br>

        <label>성</label> <input type="text" name="last-name"><br>
        <label>이름</label> <input type="text" name="first-name"><br>
        <label>회사이름</label> <input type="text" name="corp-name"><br>
        <label>전화번호</label> <input type="text" name="phone"><br>

        <input type="submit" value="가입하기">

    </form>
</div>
</body>
</html>