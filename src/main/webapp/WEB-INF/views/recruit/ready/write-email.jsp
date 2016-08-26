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
    <title>write email format</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css" media="screen,projection"/>
</head>

<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <br>
    <br>
    <div class="row">
        <div class="col s6 offset-s3">
            <div class="card-panel white">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Write email format</h5>
                            </div>
                            <br>
                            <p class="grey-text">*please mark applicant's name as '#name', test link as '#link'. '#link' must be included in mail</p>
                            <form class="col s12" action ="${pageContext.request.contextPath}/recruit/${recruitId}/save-email" method="post">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <c:choose>
                                            <c:when test="${ not empty recruitVo.emailFormat}">
                                                <textarea name = "emailFormat" id="textarea1" class="materialize-textarea" > ${recruitVo.emailFormat} </textarea>
                                            </c:when>
                                            <c:otherwise>
                                                <textarea name = "emailFormat" id="textarea1" class="materialize-textarea" > Hello, #name. Thank you for apply for our company. You can start test by following link #link</textarea>
                                            </c:otherwise>
                                        </c:choose>
                                        <label class="brown-text" for="textarea1">Email Format</label>
                                    </div>
                                    <div class="row center">
                                        <button type="submit" class="btn brown white-text">submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<script>
    var fillEmailFormat = function() {
        var mailTextArea = document.getElementById();
    }
</script>

</body>
</html>
