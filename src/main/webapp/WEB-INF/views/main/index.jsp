<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>codit</title>
    <!--Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css"-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<a href="${pageContext.request.contextPath}/client/signinform">
    <button type="button">로그인</button>
</a>
<a href="${pageContext.request.contextPath}/client/signupform">
    <button type="button">회원가입</button>
</a>

<c:if test='${authClient != null}'>
<table>
    <tr>
        <th> <a href="#"> + 새로운 채용</a> </th>
    </tr>
    <tr>
        <td>2016 하반기</td>
        <td> <a href="#">></a> </td>
    </tr>
    <tr>
        <td>2016 상반기</td>
        <td> <a href="#">></a> </td>
    </tr>
</table>
</c:if>
    <img src="${pageContext.request.contextPath}/assets/images/Mac-Laptop.jpg">
</body>
</html>
