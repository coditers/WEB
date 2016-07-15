<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icon/0630_favicon_beige.ico">
    <title>main</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize-custom.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
</head>

<body class="brown darken-3">
    <nav class="brown darken-4" role="navigation">
        <div class="nav-wrapper container">
            <a href="${pageContext.request.contextPath}">
            <img id="sublogo" src="${pageContext.request.contextPath}/assets/images/sublogo-beige.png">
            </a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li><a href="${pageContext.request.contextPath}/client/signinform">sign in</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/signupform">sign up</a></li>
            </ul>
        </div>
    </nav>
    <div class="section no-pad-bot" id="index-banner">
        <h3 id ="center-firstblock" class="header center white-text bold">Hire Faster, Hire Easier</h3>
        <div class="row center clearfix">
            <h5 id="catchphrase" class="header col s12 light bold">Online coding test platform</h5>
        </div>
        <div class="row center clearfix">
            <img id="mainlogo" src="${pageContext.request.contextPath}/assets/images/mainlogo-beige.png">
        </div>
    </div>
<%--temporal code--%>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
</body>
</html>