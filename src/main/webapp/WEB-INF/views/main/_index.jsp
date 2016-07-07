<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icon/0630_favicon_beige.ico">

    <title>main index</title>
    <link href="http://fonts.googleapis.com/css?family=Cabin:600,400|Raleway:100,700|Noto+Sans:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/standardize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/codit.css">
    <meta charset = "utf-8"/>
</head>
<body class="body-dark clearfix">

<header id="header-dark" class="header clearfix">
    <img class="sublogo" src="${pageContext.request.contextPath}/assets/images/sublogo-beige.png">
    <button id="signin-brown" class="headerbutton">SIGN IN</button>
    <a href="${pageContext.request.contextPath}/client/signupform">
    <button id="signup-brown" class="headerbutton">SIGN UP</button>
    </a>
</header>

<div id="main-centercontent" class="center-content clearfix">
    <div class="container clearfix">
        <h1 id="catchphrase" class="center-content">Hire Faster, Hire easier</h1>
        <h2 id="shortdesc" class="center-content">Online Coding Test Platform</h2>
    </div>
    <img id="mainlogo-beige" class="center-content" src="${pageContext.request.contextPath}/assets/images/mainlogo-beige.png">
</div>

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





