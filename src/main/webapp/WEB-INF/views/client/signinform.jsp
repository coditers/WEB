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
<h1>로그인</h1>
<div>
    <form action = "#" method = "POST">
        <table>
            <tr><td>
                    <label>이메일</label> <input type="text" name="email"> <button>중복확인</button>
            </td></tr>
            <tr><td>
                <label>비밀번호</label> <input type="password" name="password">
            </td></tr>
            <tr><td>
                <input type="submit" value="로그인">
            </td></tr>
        </table>
    </form>
</div>
<div>
    <h1>Codit의 사용자가 아니신가요?</h1>
    <a href="#">
        <button>회원가입</button>
    </a>
</div>
</body>
</html>
