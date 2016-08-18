<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="orange lighten-3" role="navigation">
    <div class="nav-wrapper container">
        <a href="${pageContext.request.contextPath}/">
            <img id="sublogo" src="${pageContext.request.contextPath}/assets/images/sublogo-brown.png">
        </a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:choose>
                <c:when test='${empty authClient}'>
                    <li><a class="brown-text bold" href="${pageContext.request.contextPath}/client/signinform">sign
                        in</a></li>
                    <li><a class="brown-text bold" href="${pageContext.request.contextPath}/client/signupform">sign
                        up</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/client/signout">sign out</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
