<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0">
    <title>codit</title>
    <link href="http://fonts.googleapis.com/css?family=Cabin:600,400|Raleway:100,700|Noto+Sans:400"
          rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/standardize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ind-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/_index.css">
    <meta charset="utf-8"/>
</head>
<body class="body page-ind clearfix">
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<h1 class="catchphrase">Hire Faster, Hire easier</h1>
<h2 class="shortdesc">Online Coding Test Platform</h2>
<div class="logo"></div>


<c:if test='${authClient != null}'>
    <table>
        <tr>
            <th><a href="${pageContext.request.contextPath}/main/makerecruit"> + 새로운 채용</a></th>
        </tr>
        <tr>
            <td>2016 하반기</td>
            <td><a href="#">></a></td>
        </tr>
        <tr>
            <td>2016 상반기</td>
            <td><a href="#">></a></td>
        </tr>
    </table>
</c:if>

<!-- jQuery (necessary for Bootstrap's Javascript plugins) jQuery  호출
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins, or include individual files as needed  자바 스크립트 호출
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
-->
</body>
</html>



