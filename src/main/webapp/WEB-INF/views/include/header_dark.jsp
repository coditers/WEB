<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="header-dark clearfix">
    <img class="favicon-beige"
         src="${pageContext.request.contextPath}/assets/images/favicon_beige.png">
    <c:choose>
        <c:when test="${authUserClient != null}">
            <a href="${pageContext.request.contextPath}/client/signout">
                <button class="white_noback signup">SIGN OUT</button>
            </a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/client/signinform">
                <button class="white_noback signin">SIGN IN</button>
            </a>
            <a href="${pageContext.request.contextPath}/client/signupform">
                <button class="white_noback signup">SIGN UP</button>
            </a>
        </c:otherwise>
    </c:choose>
</header>
