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

    <title>signin</title>
    <link href="http://fonts.googleapis.com/css?family=Cabin:700,400,600|Raleway:100,700|Noto+Sans:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/standardize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/client/signin-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/client/signin.css">
    <meta charset="utf-8"/>
</head>
<body class="body page-signin clearfix">
<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>
<div class="signin-form signin-form-1 clearfix">
    <div class="signin-form"></div>
    <h1 class="text-signin">Sign in</h1>
    <input class="useremail" placeholder="User email" type="text">
    <input class="password" placeholder="Password" type="text">
    <button class="signin">Sign in</button>
</div>
<div class="not-codit-user clearfix">
    <div class="notacodituser"></div>
    <h1 class="text-notcodituser">Not a Codit user?</h1>
    <button class="tryusnow">Try us now!</button>
</div>
</body>
</html>
