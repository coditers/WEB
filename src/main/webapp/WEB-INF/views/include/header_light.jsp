<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="header-light" class="header clearfix">
    <img class="sublogo" src="${pageContext.request.contextPath}/assets/images/sublogo-brown.png">
    <c:choose>
        <c:when test="${authUserClient != null}">
            <a href="${pageContext.request.contextPath}/client/signout">
                <button id="signout-beige" class="headerbutton">SIGN OUT</button>
            </a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/client/signinform">
                <button id="signin-beige" class="headerbutton">SIGN IN</button>
            </a>
            <a href="${pageContext.request.contextPath}/client/signupform">
                <button id="signup-beige" class="headerbutton">SIGN UP</button>
            </a>
        </c:otherwise>
    </c:choose>
</header>
