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

    <title>signup</title>
    <link href="http://fonts.googleapis.com/css?family=Cabin:700,400,600|Raleway:100,700|Noto+Sans:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/standardize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/codit.css">
    <meta charset = "utf-8"/>
</head>
<body class="body-light clearfix">

<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>

<div id = "signup-form-div" class="center-content clearfix">
    <div class="container clearfix">
    <h1 class="text-signup">Sign up</h1>
    <form method = "post" id="signup-form" >
        <table id="signup-form-table">
            <tbody>
            <tr>
                <td class = "tablelable">
                    <h2>Business email:</h2>
                </td>
                <td class = "tableinput">
                    <input id="email" class="tableinput tableinput-1" name="email" type="email">
                </td>
            </tr>
            <tr>
                <td class = "tablelable">
                    <h2 for="password">Password:</h2>
                </td>
                <td class = "tableinput">
                    <input id="password" class="tableinput tableinput-1" name="password" type="password">
                </td>
            </tr>
            <tr>
                <td class = "tablelable">
                    <h2 for="password-confirm">Confirm password:</h2>
                </td>
                <td class = "tableinput">
                    <input id="password-confirm" class="tableinput tableinput-1" name="password-confirm" type="password">
                </td>
            </tr>
            <tr>
                <td class = "tablelable">
                    <h2 for="firstName">First name:</h2>
                </td>
                <td class = "tableinput">
                    <input id="firstName" class="tableinput tableinput-1" name="firstName" type="text">
                </td>
            </tr>
            <tr>
                <td class = "tablelable">
                    <h2 for="lastName">Last name:</h2>
                </td>
                <td class = "tableinput">
                    <input id="lastName" class="tableinput tableinput-1" name="lastName" type="text">
                </td>
            </tr>
            </tbody>
        </table>
    </form>

    <div id="check-agreement" class="clearfix">
        <label class="checkbox-label clearfix">
            <span class="point-text"></span>
            <input id="chk-agreement" class="checkbox" type="checkbox">
        </label>
        <h3 class="text-agreement">I agree to offer information to Codit</h3>

    </div>
        <button id="btn-signup" class="submitbutton">Sign up</button>
    </div>
</div>
</body>
</html>