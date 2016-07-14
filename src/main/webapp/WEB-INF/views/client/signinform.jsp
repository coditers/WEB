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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/codit.css">
    <meta charset = "utf-8"/>

    <%--=============================temporal code==============================--%>
    <script type="text/javascript">

        var submitForm = function( ){
            var formTag = document.getElementById("signup-form");
            formTag.action = "${pageContext.request.contextPath}/client/signin";
            formTag.submit();
        }

    </script>
    <%--========================================================================--%>

</head>
<body class="body-light clearfix">

<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>

<div id = "signup-form-div" class="center-content clearfix">
    <h1 class="text-signup">Sign in</h1>
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
            <tr><td class="tablebutton" colspan="2"><button id="btn-signin" class="submitbutton" onclick="submitForm()">Sign in</button></td></tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
