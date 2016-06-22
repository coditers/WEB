<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>codit</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<a href="#">
    <button type="button">SignIn</button>
</a>
<a href="#">
    <button type="button">SignUp</button>
</a>

<c:if test='${false}'>
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
